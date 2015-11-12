package com.eteng.geolocation.baidu;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class WatchListener implements BDLocationListener {

  private static final String TAG = "WatchListener";

  CallbackContext callback;

  WatchListener(CallbackContext callback) {
    this.callback = callback;
  }

  @Override
  public void onReceiveLocation(BDLocation position) {
    Log.i(TAG, "位置改变");

    JSONArray reply = new MessageBuilder(position).build();

    PluginResult result = new PluginResult(PluginResult.Status.OK, reply);
    result.setKeepCallback(true);
    callback.sendPluginResult(result);
  }

}
