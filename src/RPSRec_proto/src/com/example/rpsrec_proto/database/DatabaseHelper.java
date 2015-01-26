package com.example.rpsrec_proto.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

	public final String TABLE_RESERVES = "Reserves";
	public final String COLUMN_ID = "_id";
	public final String COLUMN_NAME = "reserve";
	  
	public final String TABLE_SPECIES = "Species";
	public final String SPECIES_COLUMN_ID = "_id";
	public final String SPECIES_COLUMN_NAME = "reserve";

	  private static final String DATABASE_NAME = "topkek";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private final String DATABASE_CREATE = "create table "
	      + TABLE_RESERVES + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_NAME
	      + " text not null);";
	
	  private final String MAKE_Species = "create table "
		  + TABLE_SPECIES + "(" + "_id"
		  + " integer primary key autoincrement, " + "Species"
		  + " text not null);";
	  
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	    database.execSQL(MAKE_Species);
	  }
	  
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DatabaseHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVES);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIES);
	    
	    onCreate(db);
	  }
}
