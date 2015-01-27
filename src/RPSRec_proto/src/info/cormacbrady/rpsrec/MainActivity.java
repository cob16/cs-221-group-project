package info.cormacbrady.rpsrec;

import com.example.rpsrec_proto.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * @MainActivity.java 1.1 2014-12-18
 *
 * Copyright (c) 2013 Aberystwyth University.
 * All rights reserved.
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button button = (Button) findViewById(R.id.startButton);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						UserDataView.class);
				startActivity(i);

			}
		});

	}

}