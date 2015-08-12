package com.eteng.geolocation.w3;

import org.json.JSONException;
import org.json.JSONObject;

public class Coordinates {
	
	/**
	 * http://www.w3.org/TR/geolocation-API/#coordinates_interface
	 */
	double latitude;
	double longitude;
	double altitude;
	double accuracy;
	double altitudeAccuracy;
	double heading;
	double speed;
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		try {
			json.put("latitude", latitude);
			json.put("longitude", longitude);
			json.put("altitude", altitude);
			json.put("accuracy", accuracy);
			json.put("altitudeAccuracy", altitudeAccuracy);
			json.put("heading", heading);
			json.put("speed", speed);
			
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public Coordinates setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}
	public double getLongitude() {
		return longitude;
	}
	public Coordinates setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}
	public double getAltitude() {
		return altitude;
	}
	public Coordinates setAltitude(double altitude) {
		this.altitude = altitude;
		return this;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public Coordinates setAccuracy(double accuracy) {
		this.accuracy = accuracy;
		return this;
	}
	public double getAltitudeAccuracy() {
		return altitudeAccuracy;
	}
	public Coordinates setAltitudeAccuracy(double altitudeAccuracy) {
		this.altitudeAccuracy = altitudeAccuracy;
		return this;
	}
	public double getHeading() {
		return heading;
	}
	public Coordinates setHeading(double heading) {
		this.heading = heading;
		return this;
	}
	public double getSpeed() {
		return speed;
	}
	public Coordinates setSpeed(double speed) {
		this.speed = speed;
		return this;
	}

}
