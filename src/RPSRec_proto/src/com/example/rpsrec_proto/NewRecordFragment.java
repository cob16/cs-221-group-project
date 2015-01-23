package com.example.rpsrec_proto;

import java.io.File;

import com.example.rpsrec_proto.exceptions.InvalidFieldException;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NewRecordFragment extends Fragment implements View.OnClickListener {

	private EditText et;
	View view;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_new_record, container,
				false);

		final ImageButton specimenGalleryButton = (ImageButton) view
				.findViewById(R.id.getSpecimenImage);
		specimenGalleryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getGalleryImage("specimen");
			}
		});

		final ImageButton locationGalleryButton = (ImageButton) view
				.findViewById(R.id.getLocationImage);
		locationGalleryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getGalleryImage("location");
			}
		});

		final ImageButton specimenCameraButton = (ImageButton) view
				.findViewById(R.id.specimenCamera);
		specimenCameraButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getCameraPhoto("specimen");
			}
		});

		final ImageButton locationCameraButton = (ImageButton) view
				.findViewById(R.id.locationCamera);
		locationCameraButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getCameraPhoto("location");
			}
		});

		final Button addRecordButton = (Button) view
				.findViewById(R.id.addRecordDataButton);
		addRecordButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!(getSpecies().equals("")
						|| getTypicalLocation().equals("") || getInfo().equals(
						""))) {
					addRecordPressed();
					Intent i = new Intent(getActivity(), RecordViewFragment.class);
					startActivity(i);
				} else {
					new InvalidFieldException(getActivity(),
							"Fill those fields, you dungbeetle");
				}
			}

		});

		return view;
	}

	@Override
	public void onClick(View v) {
		// THE STUFF THAT SAVES RECORDS

	}

	String getSpecies() {
		et = (EditText) view.findViewById(R.id.speciesName);
		return et.getText().toString();
	}

	char getDAFOR() {
		Spinner spinner = (Spinner) view.findViewById(R.id.dafor_spinner);
		return spinner.getSelectedItem().toString().charAt(0);
	}

	String getTypicalLocation() {
		et = (EditText) view.findViewById(R.id.typLocation);
		return et.getText().toString();
	}

	String getInfo() {
		et = (EditText) view.findViewById(R.id.info);
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
		// record = new Record(getSpecies(), getTypicalLocation(), getInfo(),
		// getDAFOR());
		/*
		 * getSpecies(); getDAFOR(); getTypicalLocation(); getInfo();
		 */
	}
}
