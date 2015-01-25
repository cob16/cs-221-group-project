package com.example.rpsrec_proto;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainView extends FragmentActivity {
	ActionBar.Tab tab1, tab2;
	Fragment NewRecordFragment = new NewRecordFragment();

	
	// Fragment RecordViewFragment = new RecordViewFragment();
	Fragment FragmentTab2 = new FragmentTab2();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_test);

		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tab1 = actionBar.newTab().setText("New Record");
		tab2 = actionBar.newTab().setText("Records"); // dis da queue

		tab1.setTabListener(new MyTabListener(NewRecordFragment));
		tab2.setTabListener(new MyTabListener(FragmentTab2));

		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(getApplicationContext(), UserDataView.class);
			startActivity(i);

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}