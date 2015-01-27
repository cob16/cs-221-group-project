package info.cormacbrady.rpsrec;

import info.cormacbrady.rpsrec.data_transfer.RecordList;
import info.cormacbrady.rpsrec.data_transfer.SubmitRecord;
import info.cormacbrady.rpsrec.data_transfer.UserInfo;
import info.cormacbrady.rpsrec.database.Record;
import info.cormacbrady.rpsrec.database.ReserveDataManager;
import info.cormacbrady.rpsrec.exceptions.InvalidFieldException;

import com.example.rpsrec_proto.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserDataView extends Activity {

	SharedPreferences sharedpreferences;
	RecordList list;
	TextView name;
	TextView phone;
	TextView email;
	protected Spinner reserveSpinner;
	protected ReserveDataManager dataManager;
	protected ArrayAdapter<String> reserveAdapter;
	private UserInfo info;

	public static final String Name = "nameKey";
	public static final String Phone = "phoneKey";
	public static final String Email = "emailKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		sharedpreferences = getPreferences(0);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_data_view);
		
		reserveSpinner = (Spinner) findViewById(R.id.reserve_spinner);
		
		final Button addRecordButton = (Button) findViewById(R.id.sign_up_button);
		addRecordButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!(getEnteredName().equals("")
						|| getEnteredPhoneNumber().equals("") || getEnteredEmail()
						.equals("") || reserveSpinner.getAdapter()==null)) {
					pressButton();
					Intent i = new Intent(getApplicationContext(),
							MainView.class);
					startActivity(i);
				}

				else {
					new InvalidFieldException(getApplicationContext(),
							"Sorry, please fill in all fields");
				}
			}
		});

		final Button updateButton = (Button) findViewById(R.id.update_reserves_button);
		updateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Thread t = new Thread(new ReserveGetter());
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
	
	String getEnteredReserve() {
		reserveSpinner = (Spinner) this.findViewById(R.id.reserve_spinner);
		return reserveSpinner.getSelectedItem().toString();
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
		String res = getEnteredReserve();
		System.out.println(res);

		Editor editor = sharedpreferences.edit();
		editor.putString(Name, n);
		editor.putString(Phone, ph);
		editor.putString(Email, e);

		editor.commit();

		new UserInfo(getEnteredEmail(), getEnteredName(), getEnteredPhoneNumber(), res);
		
		Thread t = new Thread(new RecordSubmitter());
		t.start();
		// SubmitRecord submit = new SubmitRecord();
		// submit.sendToDatabase(list);
	}

	void fillSpinner() {
		reserveSpinner.setAdapter(reserveAdapter);
	}

	class ReserveGetter implements Runnable {

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

	class RecordSubmitter implements Runnable {

		@Override
		public void run() {
			SubmitRecord submit = new SubmitRecord();
			submit.sendToDatabase(getApplicationContext());
		}

	}

}
