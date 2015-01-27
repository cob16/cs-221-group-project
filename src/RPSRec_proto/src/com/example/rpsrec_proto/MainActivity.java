package com.example.rpsrec_proto;

import java.io.IOException;
import java.io.InputStream;

import com.example.rpsrec_proto.database.ReserveDataManager;
import com.example.rpsrec_proto.location.GPSToGrid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView imgView;
	boolean pressed = false;
	MediaPlayer hitmarker,airhorn,triple;
	int X, Y, dx, dy;
	int pressCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		hitmarker = MediaPlayer.create(getApplicationContext(), R.raw.hitmarker);
		airhorn=MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
		triple=MediaPlayer.create(getApplicationContext(), R.raw.atriple);
		imgView = new ImageView(getApplicationContext());
		imgView = (ImageView) findViewById(R.id.hitmarker);

		// View view = findViewById(R.id.main_relative);
		// v=setOnTouchListener(this);
		final Button button = (Button) findViewById(R.id.startButton);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// GPSToGrid.gpsToGrid(52, 123);
				// man.parseJSONObject();
				airhorn.start();
				Intent i = new Intent(getApplicationContext(),
						UserDataView.class);
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
	public boolean onTouchEvent(MotionEvent event) {

		X = (int) event.getRawX();
		Y = (int) event.getRawY() - 240;

		// dx = X-imgView.getX();
		// dy = Y-imgView.getY();

		int eventaction = event.getAction();

		switch (eventaction) {

		case MotionEvent.ACTION_DOWN:

			hitmarker.start();
			imgView.layout(X, Y, X + imgView.getMeasuredWidth(),
					Y + imgView.getMeasuredHeight());
			pressCount++;
			pressed = true;
			
			if (pressCount==3) {
				triple.start();
			}
			break;

		case MotionEvent.ACTION_MOVE:
			
			// Toast.makeText(this, "MOVE "+"X: "+X+" Y: "+Y,
			// Toast.LENGTH_SHORT).show();

			break;

		case MotionEvent.ACTION_UP:
			//airhorn.start();
			// Toast.makeText(this, "ACTION_UP "+"X: "+X+" Y: "+Y,
			// Toast.LENGTH_SHORT).show();

			break;

		}

		return pressed;

	}

}