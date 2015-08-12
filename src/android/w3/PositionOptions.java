package com.eteng.mobile.plugin.geolocation;

public class PositionOptions {
	
	boolean enableHighAccuracy;
	long maximumAge;
	long timeout;
	
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
