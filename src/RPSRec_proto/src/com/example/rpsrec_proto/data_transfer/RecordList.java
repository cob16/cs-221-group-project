package com.example.rpsrec_proto.data_transfer;

import java.io.File;
import java.util.ArrayList;

/**
 * 
 * Contains the list of all records
 *
 */

public class RecordList {

	private ArrayList<Record> recordList;
	private int numberOfRecords;

	public RecordList() {
		recordList = new ArrayList<Record>();
	}

	public void addRecord(Record newRecord) {
		recordList.add(newRecord);
		numberOfRecords++;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public String getSpecies(int index) {
		return recordList.get(index).getSpecies();
	}

	public String getAdditionalInfo(int index) {
		return recordList.get(index).getAdditionalInfo();
	}

	public char getDaforScale(int index) {
		return recordList.get(index).getDaforScale();
	}

	public String getSpeciesPhoto(int index) {
		return recordList.get(index).getSpeciesPhoto();
	}

	public String getLocationPhoto(int index) {
		return recordList.get(index).getLocationPhoto();
	}
	
	public String getLocation(int index) {
		return recordList.get(index).getLocation();
	}
	
	public String getReserve(int index) {
		return recordList.get(index).getReserve();
	}
	
	public String getDate(int index) {
		return recordList.get(index).getDate();
	}
	
	public UserInfo getUser(int index) {
		return recordList.get(index).getUser();
	}

}
