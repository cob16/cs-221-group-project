package com.example.rpsrec_proto;

import java.util.ArrayList;

import com.example.rpsrec_proto.data_transfer.Record;
import com.example.rpsrec_proto.database.ReserveDataManager;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RecordViewFragment extends ListFragment implements
		View.OnClickListener {

	View view;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		
		ReserveDataManager dataManager = new ReserveDataManager(getActivity());
		dataManager.open();
		
		ArrayList<Record> tempList = dataManager.getAllRecords();
		String[] nameList = new String[tempList.size()];
		
		for (int i=0; i < tempList.size(); i++) {
			nameList[i] = tempList.get(i).getSpecies();
		}
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameList);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// do something with the data
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
