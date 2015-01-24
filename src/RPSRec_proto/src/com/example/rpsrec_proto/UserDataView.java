package com.example.rpsrec_proto;

import com.example.rpsrec_proto.exceptions.InvalidFieldException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
		sharedpreferences = getPreferences(0);
		
		
		/*name = (TextView) findViewById(R.id.name);
		phone = (TextView) findViewById(R.id.phone);
		email = (TextView) findViewById(R.id.email);*/

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_data_view);

		/*if (sharedpreferences.contains(Name)) {
			name.setText(sharedpreferences.getString(Name, ""));

		}
		if (sharedpreferences.contains(Phone)) {
			phone.setText(sharedpreferences.getString(Phone, ""));

		}
		if (sharedpreferences.contains(Email)) {
			email.setText(sharedpreferences.getString(Email, ""));

		}*/
		
		final Button button =(Button)findViewById(R.id.sign_up_button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (!(getEnteredName().equals("") || getEnteredPhoneNumber().equals("") ||	 getEnteredEmail().equals(""))) {
				pressButton();
				Intent i = new Intent(getApplicationContext(), MainView.class);
				startActivity(i);
				}
				
				else {
					new InvalidFieldException(getApplicationContext(), "Enter fields or die, dingus");
				}
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
					"Please enter a valid email address", Toast.LENGTH_SHORT).show();
		}
		return email;
	}

	String getEnteredPhoneNumber() {
		String phone = "";
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
	}

}
