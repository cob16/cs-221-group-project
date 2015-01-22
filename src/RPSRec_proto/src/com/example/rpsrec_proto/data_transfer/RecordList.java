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

	public void addRecord(String species, String typicalLocation,
			String additionalInfo, char daforScale, File speciesPhoto,
			File locationPhoto) {
		recordList.add(new Record(species, typicalLocation, additionalInfo,
				daforScale, speciesPhoto, locationPhoto));
		numberOfRecords++;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public String getSpecies(int index) {
		return recordList.get(index).getSpecies();
	}

	public String getTypicalLocation(int index) {
		return recordList.get(index).getTypicalLocation();
	}

	public String getAdditionalInfo(int index) {
		return recordList.get(index).getAdditionalInfo();
	}

	public char getDaforScale(int index) {
		return recordList.get(index).getDaforScale();
	}

	public File getSpeciesPhoto(int index) {
		return recordList.get(index).getSpeciesPhoto();
	}

	public File getLocationPhoto(int index) {
		return recordList.get(index).getLocationPhoto();
	}

}
