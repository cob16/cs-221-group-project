package com.example.rpsrec_proto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewRecord extends Activity {

	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_record);
		
		final Button button =(Button)findViewById(R.id.addRecordDataButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				buttonPressed();
				Intent i = new Intent(getApplicationContext(), RecordView.class);
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_record, menu);
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
	
	String getSpecies() {
		et = (EditText) findViewById(R.id.speciesName);
		return et.getText().toString();
	}
	
	char getDAFOR() {
		et = (EditText) findViewById(R.id.dafor_spinner);
		return et.getText().toString().charAt(0);
	}
	
	String getTypicalLocation() {
		et = (EditText) findViewById(R.id.typLocation);
		return et.getText().toString();
	}
	
	String getInfo() {
		et = (EditText) findViewById(R.id.info);
		return et.getText().toString();
	}
	
	void buttonPressed() {
		getSpecies();
		getDAFOR();
		getTypicalLocation();
		getInfo();
	}
}
