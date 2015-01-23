package com.example.rpsrec_proto.data_transfer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class SubmitRecord {
	private static final String TAG = "MainActivity";
	private static final String URL = "cormacbrady.info";
	private RecordList list;
	private UserInfo ui;

	protected void sendToDatabase() {
		JSONObject jsonobj = new JSONObject();
		String json ="";
		 HttpClient httpclient = new DefaultHttpClient();
		 HttpPost httpPost = new HttpPost(URL);

		try {
			// add the user information to the json
			jsonobj.put("name", ui.getName());
			jsonobj.put("email", ui.getEmail());
			// looks like this: Latitude:37.422005,Longitude:-122.084095
			jsonobj.put("location", ui.getLocation());

			// loop through an array of the added records, format them into a
			// json
			// then add that json to the main json
			// this could be done in the addRecord class and then added to the
			// main
			// json here instead
			for (int i = 0; i < list.getNumberOfRecords(); i++) {
				JSONObject recordJson = new JSONObject();
				recordJson.put("species", list.getSpecies(i));
				recordJson.put("daforScale", list.getDaforScale(i));
				recordJson.put("typicalLocation", list.getTypicalLocation(i));
				recordJson.put("additionalInfo", list.getAdditionalInfo(i));
				recordJson.put("speciesPhoto", list.getSpeciesPhoto(i));
				recordJson.put("locationPhoto", list.getLocationPhoto(i));
				jsonobj.put("Records", recordJson);
			}
			// print the json on the app. PLEASE DELETE!!!
			Log.i(TAG, jsonobj.toString(2));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		json = jsonobj.toString();
		StringEntity se = null;
		try {
			se = new StringEntity(json);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  httpPost.setEntity(se);
		  
		  try {
			HttpResponse httpResponse = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Send the json to the specified URL as a http post
		//HttpClient.sendHttpPost(URL, jsonobj);
	}
}
