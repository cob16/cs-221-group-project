package com.example.rpsrec_proto.data_transfer;

/**
 * Contains the data of a single record
 */
import java.io.File;

public class Record {

	private String species, typicalLocation, additionalInfo;
	private char daforScale;
	private File speciesPhoto, locationPhoto;

	public Record(String newSpecies, String newLocation, String newInfo,
			char newDafor, File newSpeciesPhoto, File newLocationPhoto) {
		species = newSpecies;
		typicalLocation = newLocation;
		additionalInfo = newInfo;
		daforScale = newDafor;
		speciesPhoto = newSpeciesPhoto;
		locationPhoto = newLocationPhoto;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public void setTypicalLocation(String typicalLocation) {
		this.typicalLocation = typicalLocation;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setDaforScale(char daforScale) {
		this.daforScale = daforScale;
	}

	public void setSpeciesPhoto(File speciesPhoto) {
		this.speciesPhoto = speciesPhoto;
	}

	public void setLocationPhoto(File locationPhoto) {
		this.locationPhoto = locationPhoto;
	}

	public String getSpecies() {
		return species;
	}

	public char getDaforScale() {
		return daforScale;
	}

	public String getTypicalLocation() {
		return typicalLocation;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public File getSpeciesPhoto() {
		return speciesPhoto;
	}

	public File getLocationPhoto() {
		return locationPhoto;
	}
}
