package com.example.rpsrec_proto.data_transfer;

import android.location.Location;

public class UserInfo {
	private String email, name, phone;
	
	public UserInfo(String email, String name, String phone) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
