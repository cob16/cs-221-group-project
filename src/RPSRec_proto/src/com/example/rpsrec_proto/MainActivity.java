package com.example.rpsrec_proto;

import com.example.rpsrec_proto.database.ReserveDataManager;
import com.example.rpsrec_proto.location.GPSToGrid;

import android.app.Activity;
import android.content.Intent;
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

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		//getActionBar().setDisplayHomeAsUpEnabled(true);
//		return true;
//	}
	
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
	return false;
}
	public void playSound() {
		
	}
	

}
