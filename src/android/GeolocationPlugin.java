package com.eteng.geolocation.baidu;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class GeolocationPlugin extends CordovaPlugin {

  private static final String TAG = "BaiduGeoLocationPlugin";

  private static final String ACTION_GET_CURRENT_POSITION = "getCurrentPosition";
  private static final String ACTION_WATCH_POSITION = "watchPosition";
  private static final String ACTION_CLEAR_WATCH = "clearWatch";

  private Map<int, CDVLocationListener> watches = new HashMap<int, CDVLocationListener>();

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (ACTION_GET_CURRENT_POSITION.equals(action)) {
      JSONObject options = args.getJSONObject(0);
      return getCurrentPosition(options, callbackContext);
    } else if (ACTION_WATCH_POSITION.equals(action)) {
      JSONObject options = args.getJSONObject(0);
      return watchPosition(options, callbackContext);
    } else if (ACTION_CLEAR_WATCH.equals(action)) {
      int watchId = args.getInt(0);
      return clearWatch(watchId, callbackContext);
    }
    return false;
  }

  private boolean clearWatch(int watchId, CallbackContext callback) {
    Log.i(TAG, "停止监听");
    return false;
  }

  private boolean watchPosition(JSONObject options, CallbackContext callback) {
    Log.i(TAG, "监听位置变化");
    Context ctx = cordova.getActivity().getApplicationContext();
    PositionOptions positionOpts = new PositionOptions(options);
    CDVLocationListener listener = new CDVLocationListener(ctx);
    return listener.watchPosition(positionOpts, new CDVPositionListener() {
      @Override
      public void onReceivePosition(JSONArray message) {
        callback.setNoStop(true);
        callback.send(message);
      }
    });
  }

  private boolean getCurrentPosition(JSONObject options, final CallbackContext callback) {
    Log.i(TAG, "请求当前地理位置");
    Context ctx = cordova.getActivity().getApplicationContext();
    PositionOptions positionOpts = new PositionOptions(options);
    CDVLocationListener listener = new CDVLocationListener(ctx);

    return listener.getCurrentPosition(positionOpts, new CDVPositionListener() {
      @Override
      public void onReceivePosition(JSONArray message) {
        callback.send(message);
      }
    });
  }

}
