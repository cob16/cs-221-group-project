package info.cormacbrady.rpsrec.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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


/*
 * @ReserveDataManager.java 1.1 2015-101-18
 *
 * Copyright (c) 2013 Aberystwyth University.
 * All rights reserved.
 *
 */
public class ReserveDataManager {

	public ReserveDataManager(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	private SQLiteDatabase database;
	ArrayList<String> reserves;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_NAME };

	private String[] Species_allColumns = { dbHelper.SPECIES_COLUMN_ID,
			dbHelper.SPECIES_COLUMN_NAME };

	private String[] Record_allColumns = { dbHelper.RECORD_COLUMN_ID,
			dbHelper.RECORD_COLUMN_species, dbHelper.RECORD_COLUMN_DAFOR,
			dbHelper.RECORD_COLUMN_comments,
			dbHelper.RECORD_COLUMN_date_recorded,
			dbHelper.RECORD_COLUMN_photo_path_general,
			dbHelper.RECORD_COLUMN_photo_path_species,
			dbHelper.RECORD_COLUMN_reserve_name,
			dbHelper.RECORD_COLUMN_location };

	public void open() {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void flushDataBase() {
		dbHelper.onUpgrade(database, dbHelper.DATABASE_VERSION,
				dbHelper.DATABASE_VERSION + 1);
	}

	public ArrayList<String> getAllReserves() {
		reserves = new ArrayList<String>();

		Cursor cursor = database.query(dbHelper.TABLE_RESERVES, allColumns,
				null, null, null, null, null);

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

	public ArrayList<Record> getAllRecords() {
		ArrayList<Record> records = new ArrayList<Record>();

		Cursor cursor = database.query(dbHelper.TABLE_RECORDS,
				Record_allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Record cursorRecord = cursorToRecord(cursor);
			records.add(cursorRecord);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return records;
	}

	public void addReserve(String newReserve) {

		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, newReserve);
		long insertId = database.insert(dbHelper.TABLE_RESERVES, null, values);
		Cursor cursor = database.query(dbHelper.TABLE_RESERVES, allColumns,
				dbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();

		/*
		 * Reserve reserve = cursorToReserve(cursor); cursor.close(); return
		 * newComment;
		 */
	}

	// public void addSpecies(String newSpecies) {
	// ContentValues values = new ContentValues();
	// values.put(dbHelper.SPECIES_COLUMN_NAME, newSpecies);
	// long insertId = database.insert(dbHelper.TABLE_SPECIES, null,
	// values);
	// Cursor cursor = database.query(dbHelper.TABLE_SPECIES,
	// Species_allColumns, dbHelper.SPECIES_COLUMN_ID + " = " + insertId, null,
	// null, null, null);
	// cursor.moveToFirst();
	//
	// /*
	// * Reserve reserve = cursorToReserve(cursor); cursor.close(); return
	// * newComment;
	// */
	// }

	public void addRecord(Record newRecord) {
		ContentValues values = new ContentValues();
		values.put(dbHelper.RECORD_COLUMN_comments,
				newRecord.getAdditionalInfo());
		values.put(dbHelper.RECORD_COLUMN_species, newRecord.getSpecies());
		values.put(dbHelper.RECORD_COLUMN_DAFOR, newRecord.getDaforScale() + "");
		values.put(dbHelper.RECORD_COLUMN_reserve_name, newRecord.getReserve());
		values.put(dbHelper.RECORD_COLUMN_date_recorded, newRecord.getDate());
		values.put(dbHelper.RECORD_COLUMN_location, newRecord.getLocation());

		values.put(dbHelper.RECORD_COLUMN_photo_path_general,
				newRecord.getLocationPhoto());
		values.put(dbHelper.RECORD_COLUMN_photo_path_species,
				newRecord.getSpeciesPhoto());

		long insertId = database.insert(dbHelper.TABLE_RECORDS, null, values);
		// cursor.moveToFirst();

		/*
		 * Reserve reserve = cursorToReserve(cursor); cursor.close(); return
		 * newComment;
		 */
	}

	public void editRecord(Record record) {
		ContentValues cv = new ContentValues();
		cv.put(dbHelper.RECORD_COLUMN_species, record.getSpecies()); // These
																		// Fields
																		// should
																		// be
																		// your
																		// String
																		// values
																		// of
																		// actual
																		// column
																		// names
		cv.put(dbHelper.RECORD_COLUMN_location, record.getLocation());
		cv.put(dbHelper.RECORD_COLUMN_comments, record.getAdditionalInfo());
		cv.put(dbHelper.RECORD_COLUMN_DAFOR,
				Character.toString(record.getDaforScale()));
		cv.put(dbHelper.RECORD_COLUMN_time, record.getDate());
		cv.put(dbHelper.RECORD_COLUMN_reserve_name, record.getReserve());
		cv.put(dbHelper.RECORD_COLUMN_photo_path_species,
				record.getSpeciesPhoto());
		cv.put(dbHelper.RECORD_COLUMN_photo_path_general,
				record.getLocationPhoto());

		database.update(dbHelper.TABLE_RECORDS, cv, dbHelper.RECORD_COLUMN_ID
				+ "=" + record.getId(), null);
	}

	private Record cursorToRecord(Cursor cursor) {

		Record record = new Record();
		record.setAdditionalInfo(cursor.getString(0));
		record.setSpecies(cursor.getString(1));
		record.setDaforScale(cursor.getString(2).charAt(0));
		record.setReserveName(cursor.getString(3));
		record.setDate(cursor.getString(4));
		record.setlocation(cursor.getString(7));
		record.setLocationPhoto(cursor.getString(6));
		record.setSpeciesPhoto(cursor.getString(5));

		//record.setId(Integer.parseInt(cursor.getString(8)));

		return record;
	}

	private Reserve cursorToReserve(Cursor cursor) {
		Reserve reserve = new Reserve();
		reserve.setId(cursor.getLong(0));
		reserve.setName(cursor.getString(1));
		return reserve;
	}

	public void createReserveList() {
		JSONArray jsonArray = null;
		// try {
		jsonArray = parseJSONObject("reserve");// new
												// URL("http://cormacbrady.info/~tkek/json/reserves.json"));
		// } catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				addReserve(jsonArray.getString(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	// public void createSpeciesList() {
	// JSONArray jsonArray = null;
	// // try {
	// jsonArray = parseJSONObject("species");
	// /*} catch (MalformedURLException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }*/
	//
	// for (int i = 0; i < jsonArray.length(); i++) {
	// try {
	// addSpecies(jsonArray.getString(i));
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	public JSONArray parseJSONObject(String speciesOrReserve) {

		// TOTES ILLEGAL
		// StrictMode.ThreadPolicy policy = new
		// StrictMode.ThreadPolicy.Builder()
		// .permitAll().build();
		// StrictMode.setThreadPolicy(policy);
		// /////////////

		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			URL reserveUrl = null;
			if (speciesOrReserve.equals("species")) {
				reserveUrl = new URL(
						"http://cormacbrady.info/~tkek/json/species.json");
			}

			else if (speciesOrReserve.equals("reserve")) {
				reserveUrl = new URL(
						"http://cormacbrady.info/~tkek/json/reserves.json");
			}
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
