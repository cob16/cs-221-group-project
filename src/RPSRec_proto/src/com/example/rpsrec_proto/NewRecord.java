package com.example.rpsrec_proto;

import java.io.File;
import com.example.rpsrec_proto.data_transfer.Record;
import com.example.rpsrec_proto.data_transfer.RecordList;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewRecord extends Activity {

	private EditText et;
	private Record record;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_record);

		final Button specimenGalleryButton = (Button) findViewById(R.id.getSpecimenImage);
		specimenGalleryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getGalleryImage("specimen");
			}
		});

		final Button locationGalleryButton = (Button) findViewById(R.id.getLocationImage);
		locationGalleryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getGalleryImage("location");
			}
		});

		final Button specimenCameraButton = (Button) findViewById(R.id.specimenCamera);
		specimenCameraButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getCameraPhoto("specimen");
			}
		});

		final Button locationCameraButton = (Button) findViewById(R.id.locationCamera);
		locationCameraButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getCameraPhoto("location");
			}
		});

		final Button addRecordButton = (Button) findViewById(R.id.addRecordDataButton);
		addRecordButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				addRecordPressed();
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
		Spinner spinner = (Spinner) findViewById(R.id.dafor_spinner);
		return spinner.getSelectedItem().toString().charAt(0);
	}

	String getTypicalLocation() {
		et = (EditText) findViewById(R.id.typLocation);
		return et.getText().toString();
	}

	String getInfo() {
		et = (EditText) findViewById(R.id.info);
		return et.getText().toString();
	}

	void getGalleryImage(String type) {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, 2);
	}

	void getCameraPhoto(String type) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File f = new File(android.os.Environment.getExternalStorageDirectory(),
				"temp.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		startActivityForResult(intent, 1);
	}

	void addRecordPressed() {
	//	record = new Record(getSpecies(), getTypicalLocation(), getInfo(), getDAFOR());
		/*getSpecies();
		getDAFOR();
		getTypicalLocation();
		getInfo();*/
	}
}
