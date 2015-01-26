package com.example.rpsrec_proto.database;

import java.util.ArrayList;

import com.example.rpsrec_proto.data_transfer.Record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomRecordAdapter extends ArrayAdapter<Record>{
    private final LayoutInflater mInflater;

	 public CustomRecordAdapter(Context context) {
	        super(context, android.R.layout.simple_list_item_2);
	        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
	 
	    public void setData(ArrayList<Record> data) {
	        clear();
	        if (data != null) {
	            for (Record appEntry : data) {
	                add(appEntry);
	            }
	        }
	    }
	 
	    /**
	     * Populate new items in the list.
	     */
	    @Override public View getView(int position, View convertView, ViewGroup parent) {
	        View view;
	 
	        if (convertView == null) {
	            view = mInflater.inflate(R.layout.single_item, parent, false);
	        } else {
	            view = convertView;
	        }
	 
	        Record item = getItem(position);
	       // ((TextView)view.findViewById(R.id.tv_label)).setText(item.getName());
	      //  ((TextView)view.findViewById(R.id.tv_id)).setText(item.getId());
	 
	        return view;
	    }
	} 
