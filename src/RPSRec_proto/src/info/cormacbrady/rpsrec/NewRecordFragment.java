package info.cormacbrady.rpsrec;

import info.cormacbrady.rpsrec.data_transfer.RecordList;
import info.cormacbrady.rpsrec.data_transfer.SubmitRecord;
import info.cormacbrady.rpsrec.data_transfer.UserInfo;
import info.cormacbrady.rpsrec.database.Record;
import info.cormacbrady.rpsrec.database.ReserveDataManager;
import info.cormacbrady.rpsrec.exceptions.InvalidFieldException;
import info.cormacbrady.rpsrec.location.GPSToGrid;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.rpsrec_proto.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/*
 * @NewRecordFragment.java 1.1 2015-1-18
 *
 * Copyright (c) 2013 Aberystwyth University.
 * All rights reserved.
 *
 */
public class NewRecordFragment extends Fragment implements
		View.OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {

	private EditText et;
	View view;
	GoogleApiClient mGoogleApiClient;
	Location mLastLocation;
	String location;
	String galleryPhoto, cameraPhoto;
	String locationPhoto=" "; 
	String speciesPhoto="";
	String typePhoto;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		view = inflater.inflate(R.layout.fragment_new_record, container, false);

		buildGoogleApiClient();
		final ImageButton specimenGalleryButton = (ImageButton) view
				.findViewById(R.id.getSpecimenImage);
		specimenGalleryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				typePhoto="species";
				getGalleryImage("specimen");
			}
		});

		final ImageButton locationGalleryButton = (ImageButton) view
				.findViewById(R.id.getLocationImage);
		locationGalleryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				typePhoto="location";
				getGalleryImage("location");
			}
		});

		final Button locationButton = (Button) view
				.findViewById(R.id.get_location_button);
		locationButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mGoogleApiClient.connect();

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
				if (!(getSpecies().equals("") || getInfo().equals(""))) {
					addRecordPressed();
					Toast.makeText(getActivity(), "Record added", Toast.LENGTH_LONG);
					/*
					 * Intent i = new Intent(getActivity(), UserDataView.class);
					 * startActivity(i);
					 */

				} else {
					new InvalidFieldException(getActivity(),
							"Sorry, please fill all fields");
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

	String getInfo() {
		et = (EditText) view.findViewById(R.id.info);
		return et.getText().toString();
	}

	void getGalleryImage(String type) {
		/*Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, 2);*/
		
		startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 2);
		
		/*if (type.equals("location")) {
			locationPhoto= galleryPhoto;
		}
		else {
			speciesPhoto = galleryPhoto;
		}*/
				
	}
	
	public void setGalleryPhoto(String photo) {
		if (typePhoto.equals("location")) {
		locationPhoto=photo;
		}
		else {
			speciesPhoto=photo;
		}
	}
	
	public String getGalleryPhoto(String type) {
		if (type.equals("location")) {
		return locationPhoto;
		}
		else {
			return speciesPhoto;
		}
	}
	
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);


	    //Detects request codes
	    if(requestCode==2 && resultCode == Activity.RESULT_OK) {
	        Uri selectedImage = data.getData();
	       String photo = selectedImage.toString();
	       setGalleryPhoto(photo);
	    }
	}

	void getCameraPhoto(String type) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File f = new File(android.os.Environment.getExternalStorageDirectory(),
				"temp.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		startActivityForResult(intent, 1);
	}

	String getDate() {
		Date cDate = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(cDate);
	}

	int getTime() {
		int hour = Time.HOUR;
		int minute = Time.MINUTE;
		int seconds = Time.SECOND;
		return (hour + minute + seconds);
	}

	void addRecordPressed() {
		System.out.println(getGalleryPhoto("species"));
		System.out.println(getGalleryPhoto("location"));

		Record record = new Record(getSpecies() ,getLocation(), getInfo(),
				getDAFOR(), getDate(), UserInfo.getReserve(), getGalleryPhoto("species"), getGalleryPhoto("location"));

		ReserveDataManager dataManager = new ReserveDataManager(getActivity());
		dataManager.open();
		dataManager.addRecord(record);

	}

	public String getLocation() {
		if (location == null) {
			return "";
		}
		return location;
	}

	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Toast toast = Toast.makeText(getActivity(), "Please turn GPS on",
				Toast.LENGTH_LONG);
		toast.show();

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		mLastLocation = LocationServices.FusedLocationApi
				.getLastLocation(mGoogleApiClient);
		if (mLastLocation != null) {
			// mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
			// mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
			location = GPSToGrid.gpsToGrid(mLastLocation.getLatitude(),
					mLastLocation.getLongitude());
			Toast toast = Toast.makeText(getActivity(),
					"Location: " + location, Toast.LENGTH_LONG);
			toast.show();
		} else {
			Toast toast = Toast.makeText(getActivity(),
					"Failed to get location", Toast.LENGTH_LONG);
			toast.show();
		}
		mGoogleApiClient.disconnect();
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	}
}
