package com.eteng.geolocation.baidu;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import android.content.Context;
import android.util.Log;

public class CDVLocationListener implements BDLocationListener {

  private CallbackContext callback;
  private String TAG = "CDVLocationListener";
  private LocationClient client;

  public static final String COORD_BD09LL = "bd09ll";
  public static final String COORD_BD09 = "bd09";
  public static final String COORD_GCJ02 = "gcj02";

  CDVLocationListener(Context context, CallbackContext callback) {
    client = new LocationClient(context);
    this.callback = callback;
  }

  @Override
  public void onReceiveLocation(BDLocation location) {
    client.unRegisterLocationListener(this);
    client.stop();
    this.client = null;

    Log.i(TAG, "地理位置更新");
    JSONArray reply = new MessageBuilder(location).build();
    Log.i(TAG, "reply: " + reply);
    callback.success(reply);

    this.callback = null;
  }

  public void getCurrentPosition() {
    LocationClientOption options = new LocationClientOption();
    options.setLocationMode(LocationMode.Hight_Accuracy);
    options.setCoorType(COORD_BD09LL);
    client.setLocOption(options);

    client.start();
    client.registerLocationListener(this);
  }

}
