package com.example.rpsrec_proto.data_transfer;

import android.location.Location;

public class UserInfo {
	public static String email, name, phone;
	
	public UserInfo(String newEmail, String newName, String newPhone) {
		name = newName;
		phone = newPhone;
		email = newEmail;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String newEmail) {
		email = newEmail;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String newName) {
		name = newName;
	}

	public static String getPhone() {
		return phone;
	}

	public static void setPhone(String newPhone) {
		phone = newPhone;
	}

}
