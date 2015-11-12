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
    LocationClientOption options = new LocationClientOption();
    options.setLocationMode(LocationMode.Hight_Accuracy);
    options.setCoorType(COORD_GCJ02);
    client.setLocOption(options);
  }

  private void setOptions(PositionOptions options) {
	  LocationClientOption bdoptions = client.getLocOption();
	  bdoptions.setCoorType(options.getCoorType());
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
