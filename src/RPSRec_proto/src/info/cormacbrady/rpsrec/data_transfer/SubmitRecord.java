package info.cormacbrady.rpsrec.data_transfer;

import info.cormacbrady.rpsrec.database.Record;
import info.cormacbrady.rpsrec.database.ReserveDataManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubmitRecord {
	private static final String TAG = "MainActivity";
	private static final String URL = "cormacbrady.info/~tkek/add_record.php";

	// private RecordList list;

	public void sendToDatabase(Context context) {

		JSONObject mainObj = new JSONObject();
		JSONObject userObj = new JSONObject();
		String json = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(
				"http://cormacbrady.info/~tkek/add_record.php");

		ReserveDataManager dataManager = new ReserveDataManager(context);
		dataManager.open();

		ArrayList<Record> list = new ArrayList<Record>();
		list = dataManager.getAllRecords();
		JSONObject recordJson = null;
		for (int i = 0; i < list.size(); i++) {
			try {
				// add the user information to the json
				userObj.put("name", UserInfo.getName());
				userObj.put("email", UserInfo.getEmail());
				userObj.put("phone", UserInfo.getPhone());
				// looks like this: Latitude:37.422005,Longitude:-122.084095

				// loop through an array of the added records, format them into
				// a
				// json
				// then add that json to the main json
				// this could be done in the addRecord class and then added to
				// the
				// main
				// json here instead

				recordJson = new JSONObject();
				recordJson.put("species", list.get(i).getSpecies());
				recordJson.put("DAFOR",
						Character.toString(list.get(i).getDaforScale()));
				recordJson.put("comments", list.get(i).getAdditionalInfo());
				recordJson.put("date_recorded", list.get(i).getDate());
				recordJson.put("reserve_name", list.get(i).getReserve());
				// recordJson.put("species_photo", list.getSpeciesPhoto(i));
				// recordJson.put("locationPhoto", list.getLocationPhoto(i));
				recordJson.put("location", list.get(i).getLocation());
				mainObj.put("record", recordJson);

				mainObj.put("user", userObj);

				System.out.println(mainObj);
				// print the json on the app. PLEASE DELETE!!!
				Log.i(TAG, mainObj.toString(2));
			} catch (JSONException e) {
				e.printStackTrace();
			}

			json = mainObj.toString();
			StringEntity se = null;
			try {
				se = new StringEntity(json);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			httpPost.setEntity(se);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			ResponseHandler responseHandler = new BasicResponseHandler();
			String derp = null;
			try {
				derp = httpclient.execute(httpPost, responseHandler);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(derp);
			// Send the json to the specified URL as a http post
			// HttpClient.sendHttpPost(URL, jsonobj);
		}
		
		dataManager.flushDataBase();
	}

}
