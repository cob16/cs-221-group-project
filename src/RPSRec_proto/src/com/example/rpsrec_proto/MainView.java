package com.example.rpsrec_proto;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class MainView extends Activity {
	ActionBar.Tab tab1, tab2;
	Fragment NewRecordFragment = new NewRecordFragment();
	//Fragment RecordViewFragment = new RecordViewFragment();
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
}