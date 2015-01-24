package com.example.rpsrec_proto.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.widget.Toast;

public class ReserveDataManager extends SQLiteOpenHelper {

	private Context appContext;

	private static final String DATABASE_NAME = "reserveDB";
	private static final int DATABASE_VERSION = 1;
	public static final String KEY_ID = "ID";
	public static final String KEY_NAME = "keyName";

	private static final String RESERVE_TABLE_NAME = "reserve";
	private static final String RESERVE_TABLE_CREATE = "CREATE TABLE "
			+ RESERVE_TABLE_NAME + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME  + " TEXT);";

	public ReserveDataManager(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		appContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(RESERVE_TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	// public void insertDataBase() {
	// JSONObject json = new JSONObject();
	// json.put("uniqueArrays", new JSONArray(items));
	// String arrayList = json.toString();
	// }

	// public void readDataBasa() {
	// JSONObject json = new JSONObject(stringreadfromsqlite);
	// ArrayList items = json.optJSONArray("uniqueArrays");
	// }

	public void getWritableDataBase() {

	}

	public void getReadableDataBase() {

	}

	public ArrayList<String> createReserveList() {
		JSONArray jsonArray = parseJSONObject();
		ArrayList<String> reserveList = new ArrayList<String>(50);

		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				reserveList.add(jsonArray.getString(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (String s : reserveList) {
			System.out.println(s);
		}

		return reserveList;
	}

	public JSONArray parseJSONObject() {

		// TOTES ILLEGAL
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		// /////////////

		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			URL reserveUrl = new URL(
					"http://cormacbrady.info/~tkek/json/reserves.json");
			br = new BufferedReader(new InputStreamReader(
					reserveUrl.openStream()));
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("File Content: \n" + jsonData);
		JSONObject obj;
		JSONArray jsonMainArr = null;
		try {
			 obj = new JSONObject(jsonData);
			//jsonMainArr = new JSONArray(jsonData);
			jsonMainArr = obj.getJSONArray("list");
			System.out.println(jsonMainArr.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMainArr;
	}

}
