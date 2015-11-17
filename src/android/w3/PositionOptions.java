package com.eteng.geolocation.w3;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PositionOptions {

  private static final String TAG = "PositionOptions";

  Boolean enableHighAccuracy = false;
  long maximumAge;
  long timeout;
  String coorType;

  public PositionOptions(JSONObject options) {
    try {
      this.enableHighAccuracy = options.getBoolean("enableHighAccuracy");
    } catch (JSONException e) {
      Log.v(TAG, "enableHighAccuracy 未定义");
    }
    try {
      this.coorType = options.getString("coorType");
    } catch (JSONException e) {
      Log.v(TAG, "coorType 未定义");
    }
  }

  public String getCoorType() {
    return this.coorType;
  }

  public PositionOptions setCoorType(String coorType) {
    this.coorType = coorType;
    return this;
  }

  public Boolean isEnableHighAccuracy() {
    return enableHighAccuracy;
  }

  public PositionOptions setEnableHighAccuracy(Boolean enableHighAccuracy) {
    this.enableHighAccuracy = enableHighAccuracy;
    return this;
  }

  public long getMaximumAge() {
    return maximumAge;
  }

  public PositionOptions setMaximumAge(long maximumAge) {
    this.maximumAge = maximumAge;
    return this;
  }

  public long getTimeout() {
    return timeout;
  }

  public PositionOptions setTimeout(long timeout) {
    this.timeout = timeout;
    return this;
  }

}
