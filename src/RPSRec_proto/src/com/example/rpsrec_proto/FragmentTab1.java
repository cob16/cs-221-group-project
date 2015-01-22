package com.example.rpsrec_proto;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTab1 extends Fragment implements View.OnClickListener{
	
	View view;
	
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	                           Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.tab2, container, false);
		TextView textview = (TextView) view.findViewById(R.id.tabtextview);
		textview.setText("flluurb");
		
		final Button button =(Button)view.findViewById(R.id.addRecordButton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), NewRecord.class);
				startActivity(i);
				
			}
		});
		
		return view;
	  }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	}