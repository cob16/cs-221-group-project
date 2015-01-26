package com.example.rpsrec_proto.database;

import org.apache.http.client.RedirectException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

	public static final String TABLE_RESERVES = "Reserves";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "reserve";
	  
	public static  final String TABLE_SPECIES = "Species";
	public static final String SPECIES_COLUMN_ID = "_id";
	public static final String SPECIES_COLUMN_NAME = "Species_name";
	
	public static  final String TABLE_RECORDS              		    = "Records";
	public   final static String RECORD_COLUMN_ID 		    		= "_id";
	public final static String RECORD_COLUMN_species               = "species";
	public final static String RECORD_COLUMN_DAFOR                 = "DAFOR ";
	public final static String RECORD_COLUMN_comments	            = "comments";
	public final static String RECORD_COLUMN_date_recorded 	    = "date_recorded";
	public final static String RECORD_COLUMN_photo_path_general	= "photo_path_general";
	public final static String RECORD_COLUMN_photo_path_species    = "photo_path_species";
	public final static String RECORD_COLUMN_reserve_name          = "reserve_name ";
	public final static String RECORD_COLUMN_location              = "location";

	
	  private static final String DATABASE_NAME = "topkek";
	  public static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private final String DATABASE_CREATE = "create table "
	      + TABLE_RESERVES + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_NAME
	      + " text not null);";
	
	  private final String MAKE_Species = "create table "
		  + TABLE_SPECIES + "(" + SPECIES_COLUMN_ID
		  + " integer primary key autoincrement, " + SPECIES_COLUMN_NAME
		  + " text not null);";
	  
	  private static  final String MAKE_Records = "CREATE TABLE " + TABLE_RECORDS + "(" + 
			  RECORD_COLUMN_ID + " INT NOT NULL, " + 
			  RECORD_COLUMN_species + " text NOT NULL, " +
			  RECORD_COLUMN_DAFOR + " char(1) CHARACTER SET utf8mb4 NOT NULL, " +
			  RECORD_COLUMN_comments + " text, " +
			  RECORD_COLUMN_date_recorded + " date NOT NULL, " +
			  RECORD_COLUMN_photo_path_general + " text NOT NULL, " +
			  RECORD_COLUMN_photo_path_species + " text NOT NULL, " +
			  RECORD_COLUMN_reserve_name + " varchar(80) CHARACTER SET utf8 NOT NULL, " +
			  RECORD_COLUMN_location + "varchar(8) NOT NULL);";
	  
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	   // database.execSQL(MAKE_Species);
	   // database.execSQL(MAKE_Records);
	  }
	  
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DatabaseHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVES);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIES);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
	    
	    onCreate(db);
	  }
}
