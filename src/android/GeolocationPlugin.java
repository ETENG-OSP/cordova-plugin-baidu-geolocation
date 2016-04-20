package com.eteng.geolocation.baidu;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.eteng.geolocation.w3.PositionOptions;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.Manifest;
import android.content.pm.PackageManager;

public class GeolocationPlugin extends CordovaPlugin {

  private static final String TAG = "GeolocationPlugin";

  private static final int GET_CURRENT_POSITION = 0;
  private static final int WATCH_POSITION = 1;
  private static final int CLEAR_WATCH = 2;

  private SparseArray<BDGeolocation> store = new SparseArray<BDGeolocation>();
  private String [] permissions = { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION };

  private JSONArray requestArgs;
  private CallbackContext context;

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    Log.i(TAG, "插件调用");
    JSONObject options = new JSONObject();

    requestArgs = args;
    context = callbackContext;

    if (action.equals("getCurrentPosition")) {
      getPermission(GET_CURRENT_POSITION);
      try {
        options = args.getJSONObject(0);
      } catch (JSONException e) {
        Log.v(TAG, "options 未传入");
      }
      return getCurrentPosition(options, callbackContext);
    } else if (action.equals("watchPosition")) {
      getPermission(WATCH_POSITION);
      try {
        options = args.getJSONObject(0);
      } catch (JSONException e) {
        Log.v(TAG, "options 未传入");
      }
      int watchId = args.getInt(1);
      return watchPosition(options, watchId, callbackContext);
    } else if (action.equals("clearWatch")) {
      getPermission(CLEAR_WATCH);
      int watchId = args.getInt(0);
      return clearWatch(watchId, callbackContext);
    }
    return false;
  }

  private boolean clearWatch(int watchId, CallbackContext callback) {
    Log.i(TAG, "停止监听");
    BDGeolocation geolocation = store.get(watchId);
    store.remove(watchId);
    geolocation.clearWatch();
    callback.success();
    return true;
  }

  private boolean watchPosition(JSONObject options, int watchId, final CallbackContext callback) {
    Log.i(TAG, "监听位置变化");
    Context ctx = cordova.getActivity().getApplicationContext();
    PositionOptions positionOpts = new PositionOptions(options);
    BDGeolocation geolocation = new BDGeolocation(ctx);
    store.put(watchId, geolocation);
    return geolocation.watchPosition(positionOpts, new BDLocationListener() {
      @Override
      public void onReceiveLocation(BDLocation location) {
        JSONArray message = new MessageBuilder(location).build();
        PluginResult result = new PluginResult(PluginResult.Status.OK, message);
        result.setKeepCallback(true);
        callback.sendPluginResult(result);
      }
    });
  }

  private boolean getCurrentPosition(JSONObject options, final CallbackContext callback) {
    Log.i(TAG, "请求当前地理位置");
    Context ctx = cordova.getActivity().getApplicationContext();
    PositionOptions positionOpts = new PositionOptions(options);
    BDGeolocation geolocation = new BDGeolocation(ctx);
    return geolocation.getCurrentPosition(positionOpts, new BDLocationListener() {
      @Override
      public void onReceiveLocation(BDLocation location) {
        JSONArray message = new MessageBuilder(location).build();
        callback.success(message);
      }
    });
  }

  /**
   * 获取对应权限
   * int requestCode Action代码
   */
  public void getPermission(int requestCode){
    if(!hasPermisssion()){
      PermissionHelper.requestPermissions(this, requestCode, permissions);
    }
  }

  /**
   * 权限请求结果处理函数
   * int requestCode Action代码
   * String[] permissions 权限集合
   * int[] grantResults 授权结果集合
   */
  public void onRequestPermissionResult(int requestCode, String[] permissions,
                                         int[] grantResults) throws JSONException
   {
       PluginResult result;
       //This is important if we're using Cordova without using Cordova, but we have the geolocation plugin installed
       if(context != null) {
           for (int r : grantResults) {
               if (r == PackageManager.PERMISSION_DENIED) {
                   Log.d(TAG, "Permission Denied!");
                   result = new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION);
                   context.sendPluginResult(result);
                   return;
               }

           }
           switch(requestCode)
           {
               case GET_CURRENT_POSITION:
                   getCurrentPosition(this.requestArgs.getJSONObject(0), this.context);
                   break;
               case WATCH_POSITION:
                   watchPosition(this.requestArgs.getJSONObject(0), this.requestArgs.getInt(1), this.context);
                   break;
               case CLEAR_WATCH:
                   clearWatch(this.requestArgs.getInt(0), this.context);
                   break;
           }
       }
   }

   /**
    * 判断是否有对应权限
    */
   public boolean hasPermisssion() {
       for(String p : permissions)
       {
           if(!PermissionHelper.hasPermission(this, p))
           {
               return false;
           }
       }
       return true;
   }

   /*
    * We override this so that we can access the permissions variable, which no longer exists in
    * the parent class, since we can't initialize it reliably in the constructor!
    */

   public void requestPermissions(int requestCode)
   {
       PermissionHelper.requestPermissions(this, requestCode, permissions);
   }

}
