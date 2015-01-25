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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.widget.Toast;

public class ReserveDataManager {

	private SQLiteDatabase database;
	ArrayList<String> reserves;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_NAME };

	public ReserveDataManager(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public ArrayList<String> getAllReserves() {
		reserves = new ArrayList<String>();

		Cursor cursor = database.query(DatabaseHelper.TABLE_RESERVES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Reserve reserve = cursorToReserve(cursor);
			reserves.add(reserve.toString());
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return reserves;
	}

	public void addReserve(String newReserve) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_NAME, newReserve);
		long insertId = database.insert(DatabaseHelper.TABLE_RESERVES, null,
				values);
		Cursor cursor = database.query(DatabaseHelper.TABLE_RESERVES,
				allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();

		/*
		 * Reserve reserve = cursorToReserve(cursor); cursor.close(); return
		 * newComment;
		 */
	}

	private Reserve cursorToReserve(Cursor cursor) {
		Reserve reserve = new Reserve();
		reserve.setId(cursor.getLong(0));
		reserve.setName(cursor.getString(1));
		return reserve;
	}

	public void createReserveList() {
		JSONArray jsonArray = parseJSONObject();

		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				addReserve(jsonArray.getString(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
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
			// jsonMainArr = new JSONArray(jsonData);
			jsonMainArr = obj.getJSONArray("list");
			System.out.println(jsonMainArr.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMainArr;
	}

}
