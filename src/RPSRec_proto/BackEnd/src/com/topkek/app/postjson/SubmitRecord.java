package com.topkek.app.postjson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class SubmitRecord {
	private static final String TAG = "MainActivity";
	private static final String URL = "";
	private AddRecord ar;
	private UserInfo ui;

	protected void sendToDatabase() {		
		JSONObject jsonobj = new JSONObject();
		
		try {
			//add the user information to the json
			jsonobj.put("name", ui.getName());
			jsonobj.put("email", ui.getEmail());
			jsonobj.put("phone", ui.getPhone());
			//looks like this: Latitude:37.422005,Longitude:-122.084095
			jsonobj.put("location", ui.getLocation());
			
			
			//loop through an array of the added records, format them into a json
			//then add that json to the main json
			// this could be done in the addRecord class and then added to the main
			//json here instead
			for (numberOfRecords) {
				JSONObject recordJson = new JSONObject();
				recordJson.put("species", ar.getSpecies());
				recordJson.put("daforScale", ar.getDaforScale());
				recordJson.put("typicalLocation", ar.getTypicalLocation());
				recordJson.put("additionalInfo", ar.getAdditionalInfo());
				recordJson.put("speciesPhoto", ar.getSpeciesPhoto());
				recordJson.put("locationPhoto", ar.getLocationPhoto());
				jsonobj.put("Records", recordJson);
			}
			//print the json on the app. PLEASE DELETE!!!
			Log.i(TAG, jsonobj.toString(2));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Send the json to the specified URL as a http post
		HttpClient.sendHttpPost(URL, jsonobj);
	}
}
