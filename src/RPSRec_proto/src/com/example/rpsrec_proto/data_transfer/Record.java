package com.example.rpsrec_proto.data_transfer;

/**
 * Contains the data of a single record
 */
import java.io.File;

public class Record {

	private String species, additionalInfo, reserve, location, date;
	private char daforScale;
	private String speciesPhoto, locationPhoto, reserveName;
	private UserInfo user;

	public Record() {
		species=additionalInfo=reserve=location=speciesPhoto=locationPhoto=date=reserveName="";
		daforScale=0;
		user=null;
		
	}
	
	public Record(String newSpecies, String newLocation, String newInfo,
			char newDafor, String newDate, String newReserve,String newSpeciesPhoto, String newLocationPhoto) {
		species = newSpecies;
		location = newLocation;
		additionalInfo = newInfo;
		daforScale = newDafor;
		date = newDate;
		reserve = newReserve;
		speciesPhoto = newSpeciesPhoto;
		locationPhoto = newLocationPhoto;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public void setlocation(String newLocation) {
		this.location = newLocation;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setDaforScale(char daforScale) {
		this.daforScale = daforScale;
	}
	
	public void setReserveName(String reserveName) {
		this.reserveName= reserveName;
	}

	public void setSpeciesPhoto(String speciesPhoto) {
		this.speciesPhoto = speciesPhoto;
	}

	public void setLocationPhoto(String locationPhoto) {
		this.locationPhoto = locationPhoto;
	}

	public String getSpecies() {
		return species;
	}

	public char getDaforScale() {
		return daforScale;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public String getSpeciesPhoto() {
		return speciesPhoto;
	}

	public String getLocationPhoto() {
		return locationPhoto;
	}

	public String getReserve() {
		return reserve;
	}

	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}
	
	public UserInfo getUser() {
		return user;
	}
	
	public String getReserveName() {
		return reserveName;
	}

	public void setDate(String newDate) {
		this.date = newDate;
		
	}
}
