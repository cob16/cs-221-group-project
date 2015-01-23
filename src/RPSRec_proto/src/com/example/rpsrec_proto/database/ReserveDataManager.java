package com.example.rpsrec_proto.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ReserveDataManager extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "reserveDB";
	private static final int DATABASE_VERSION = 1;
	public static final String KEY_ID = "ID";
	public static final String KEY_NAME = "keyName";
	public static final String KEY_LOCATION = "keyLocation";

	private static final String RESERVE_TABLE_NAME = "reserve";
	private static final String RESERVE_TABLE_CREATE = "CREATE TABLE "
			+ RESERVE_TABLE_NAME + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "
			+ KEY_LOCATION + " TEXT);";

	public ReserveDataManager(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(RESERVE_TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void insertDataBase() {
		JSONObject json = new JSONObject();
		json.put("uniqueArrays", new JSONArray(items));
		String arrayList = json.toString();
	}
	
	
	public void readDataBasa() {
		JSONObject json = new JSONObject(stringreadfromsqlite);
		  ArrayList items = json.optJSONArray("uniqueArrays");
	}

	public void getWritableDataBase() {

	}

	public void getReadableDataBase() {

	}

}
