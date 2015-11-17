package com.eteng.geolocation.baidu;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.eteng.geolocation.w3.PositionOptions;

import android.content.Context;

public class BDGeolocation {

  private String TAG = "BDGeolocation";
  private LocationClient client;

  public static final String COORD_BD09LL = "bd09ll";
  public static final String COORD_BD09 = "bd09";
  public static final String COORD_GCJ02 = "gcj02";

  private BDLocationListener listener;

  BDGeolocation(Context context) {
    client = new LocationClient(context);
  }

  private void setOptions(PositionOptions options) {
    // set default coorType
    String coorType = options.getCoorType();
    if (coorType == null || coorType.trim().isEmpty()) {
      coorType = COORD_GCJ02;
    }

    // set default locationMode
    LocationMode locationMode = LocationMode.Battery_Saving;
    if (options.isEnableHighAccuracy()) {
      locationMode = LocationMode.Hight_Accuracy;
    }

    LocationClientOption bdoptions = new LocationClientOption();
    bdoptions.setCoorType(coorType);
    bdoptions.setLocationMode(locationMode);
    client.setLocOption(bdoptions);
  }

  public boolean getCurrentPosition(PositionOptions options, final BDLocationListener callback) {
    listener = new BDLocationListener() {
      @Override
      public void onReceiveLocation(BDLocation location) {
        callback.onReceiveLocation(location);
        clearWatch();
      }
    };
    setOptions(options);
    client.registerLocationListener(listener);
    client.start();
    return true;
  }

  public boolean watchPosition(PositionOptions options, BDLocationListener callback) {
    listener = callback;
    setOptions(options);
    client.registerLocationListener(listener);
    client.start();
    return true;
  }

  public boolean clearWatch() {
    client.stop();
    client.unRegisterLocationListener(listener);
    listener = null;
    return true;
  }

}
