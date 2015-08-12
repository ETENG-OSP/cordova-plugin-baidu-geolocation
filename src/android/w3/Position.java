package com.eteng.geolocation.w3;

import org.json.JSONException;
import org.json.JSONObject;

public class Position {
	
	Coordinates coords;
	long timestamp;
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		try {
			json.put("timestamp", timestamp);
			json.put("coords", coords.toJSON());
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Coordinates getCoords() {
		return coords;
	}
	public Position setCoords(Coordinates coords) {
		this.coords = coords;
		return this;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public Position setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

}
