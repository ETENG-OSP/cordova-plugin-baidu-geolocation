package com.eteng.geolocation.w3;

import org.json.JSONObject;

public class PositionOptions {

  boolean enableHighAccuracy;
  long maximumAge;
  long timeout;
  String coorType;

  public PositionOptions(JSONObject options) {
    try {
      this.coorType = options.getString("coorType");
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public String getCoorType() {
    return this.coorType;
  }
  public void setCoorType(String coorType) {
    this.coorType = coorType;
  }
  public boolean isEnableHighAccuracy() {
    return enableHighAccuracy;
  }
  public PositionOptions setEnableHighAccuracy(boolean enableHighAccuracy) {
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
