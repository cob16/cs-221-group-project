package com.example.rpsrec_proto;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDataView extends Activity {

	SharedPreferences sharedpreferences;

	TextView name;
	TextView phone;
	TextView email;
	public static final String Name = "nameKey";
	public static final String Phone = "phoneKey";
	public static final String Email = "emailKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		name = (TextView) findViewById(R.id.name);
		phone = (TextView) findViewById(R.id.phone);
		email = (TextView) findViewById(R.id.email);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_data_view);

		if (sharedpreferences.contains(Name)) {
			name.setText(sharedpreferences.getString(Name, ""));

		}
		if (sharedpreferences.contains(Phone)) {
			phone.setText(sharedpreferences.getString(Phone, ""));

		}
		if (sharedpreferences.contains(Email)) {
			email.setText(sharedpreferences.getString(Email, ""));

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_data_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Retrieve the value from the name box as a string.
	 */
	String getEnteredName() {
		String name = "";
		;
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
		;
		try {
			EditText et = (EditText) findViewById(R.id.email);
			email = et.getText().toString();
		} catch (Exception e) {
			// failed to get the name string

			Toast.makeText(getApplicationContext(),
					"Please enter an email address", Toast.LENGTH_SHORT).show();
		}
		return email;
	}

	String getEnteredPhoneNumber() {
		String phone = "";
		;
		try {
			EditText et = (EditText) findViewById(R.id.phone);
			phone = et.getText().toString();
		} catch (Exception e) {
			// failed to get the name string

			Toast.makeText(getApplicationContext(),
					"Please enter a phone number", Toast.LENGTH_SHORT).show();
		}
		return phone;
	}

	void pressButton(View button) {
		// check all the data is correct and put it in cache
		String n = name.getText().toString();
		String ph = phone.getText().toString();
		String e = email.getText().toString();

		Editor editor = sharedpreferences.edit();
		editor.putString(Name, n);
		editor.putString(Phone, ph);
		editor.putString(Email, e);

		editor.commit();
	}

}
