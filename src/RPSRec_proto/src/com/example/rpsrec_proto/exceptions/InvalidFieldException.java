package com.example.rpsrec_proto.exceptions;

import android.content.Context;
import android.widget.Toast;

public class InvalidFieldException extends Exception {

	
	public InvalidFieldException(Context appContext, String msg) {
		Context context = appContext;
		CharSequence text = msg;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
