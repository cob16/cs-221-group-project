package com.example.rpsrec_proto;

import java.util.ArrayList;

import com.example.rpsrec_proto.data_transfer.Record;
import com.example.rpsrec_proto.data_transfer.RecordList;
import com.example.rpsrec_proto.data_transfer.SubmitRecord;
import com.example.rpsrec_proto.database.ReserveDataManager;
import com.example.rpsrec_proto.exceptions.InvalidFieldException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserDataView extends Activity {

	SharedPreferences sharedpreferences;

	TextView name;
	TextView phone;
	TextView email;
	protected Spinner reserveSpinner;
	protected ReserveDataManager dataManager;
	protected ArrayAdapter<String> reserveAdapter;

	public static final String Name = "nameKey";
	public static final String Phone = "phoneKey";
	public static final String Email = "emailKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		sharedpreferences = getPreferences(0);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_data_view);

		// fillSpinner();

		final Button addRecordButton = (Button) findViewById(R.id.sign_up_button);
		addRecordButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!(getEnteredName().equals("")
						|| getEnteredPhoneNumber().equals("") || getEnteredEmail()
						.equals(""))) {
					pressButton();
					Intent i = new Intent(getApplicationContext(),
							MainView.class);
					startActivity(i);
				}

				else {
					new InvalidFieldException(getApplicationContext(),
							"Enter fields or die, dingus");
				}
			}
		});

		final Button updateButton = (Button) findViewById(R.id.update_reserves_button);
		updateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Thread t = new Thread(new Task());
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reserveSpinner = (Spinner) findViewById(R.id.reserve_spinner);
				reserveSpinner.setAdapter(null);
				fillSpinner();

			}
		});

	}

	/**
	 * Retrieve the value from the name box as a string.
	 */
	String getEnteredName() {
		String name = "";

		try {
			EditText et = (EditText) findViewById(R.id.name);
			name = et.getText().toString();
		} catch (Exception e) {
			// failed to get the name string

			Toast.makeText(getApplicationContext(), "Please enter a name",
					Toast.LENGTH_SHORT).show();
		}
		return name;
	}

	String getEnteredEmail() {
		String email = "";
		try {
			EditText et = (EditText) findViewById(R.id.email);
			email = et.getText().toString();
		} catch (Exception e) {
			// failed to get the name string

			Toast.makeText(getApplicationContext(),
					"Please enter a valid email address", Toast.LENGTH_SHORT)
					.show();
		}
		return email;
	}

	String getEnteredPhoneNumber() {
		String phone = "";
		// try {
		EditText et = (EditText) findViewById(R.id.phone);
		phone = et.getText().toString();
		/*
		 * } catch (Exception e) { // failed to get the name string
		 * 
		 * Toast.makeText(getApplicationContext(),
		 * "Please enter a phone number", Toast.LENGTH_SHORT).show(); }
		 */
		return phone;
	}

	void pressButton() {
		// check all the data is correct and put it in cache
		String n = getEnteredName();
		String ph = getEnteredPhoneNumber();
		String e = getEnteredEmail();

		Editor editor = sharedpreferences.edit();
		editor.putString(Name, n);
		editor.putString(Phone, ph);
		editor.putString(Email, e);

		editor.commit();

		Record record = new Record("Abella uniflora", "AB1234", "testing", 'a',
				"121212", "example name", "dsada", "dsfsf");
		RecordList list = new RecordList();
		list.addRecord(record);
		//SubmitRecord submit = new SubmitRecord();
		//submit.sendToDatabase(list);
	}

	void fillSpinner() {
		// dataManager = new ReserveDataManager(getApplicationContext());
		// dataManager.open();
		// dataManager.createReserveList();
		// reserveSpinner = (Spinner) findViewById(R.id.reserve_spinner);
		// reserveSpinner.setAdapter(null);
		// new Thread(new Task()).start();

		reserveSpinner.setAdapter(reserveAdapter);

		// ArrayAdapter<String> reserveAdapter = new
		// ArrayAdapter<String>(UserDataView.this,
		// android.R.layout.simple_spinner_item, dataManager.getAllReserves());
		// reserveSpinner.setAdapter(reserveAdapter);
	}

	class Task implements Runnable {

		@Override
		public void run() {
			dataManager = new ReserveDataManager(getApplicationContext());
			dataManager.open();
			dataManager.flushDataBase();
			dataManager.createReserveList();
			// dataManager.createSpeciesList();
			reserveAdapter = new ArrayAdapter<String>(UserDataView.this,
					android.R.layout.simple_spinner_item,
					dataManager.getAllReserves());

		}

	}

}
