package com.example.rpsrec_proto;

import com.example.rpsrec_proto.database.ReserveDataManager;
import com.example.rpsrec_proto.location.GPSToGrid;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnTouchListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//View view = getViewById(R.id.relative_number);
		//v=setOnTouchListener(this);		
		final Button button =(Button)findViewById(R.id.startButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//GPSToGrid.gpsToGrid(52, 123);
				//man.parseJSONObject();
				Intent i = new Intent(getApplicationContext(), UserDataView.class);
				startActivity(i);
				
			}
		});
		
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

@Override
public boolean onTouch(View v, MotionEvent event) {
	// TODO Auto-generated method stub
	playSound();
	System.out.println("works");
	return false;
}
	public void playSound() {
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.hitmarker);  
		  mp.start();
	}
	

}
