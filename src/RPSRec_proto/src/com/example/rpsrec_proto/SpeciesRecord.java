package com.example.rpsrec_proto;

import java.io.File;

public class SpeciesRecord {

	private String speciesName;
	private char dafor;
	private String comments;
	private String typLocation;
	private File locationImage;
	private File speciesImage;

	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public char getDafor() {
		return dafor;
	}

	public void setDafor(char dafor) {
		this.dafor = dafor;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTypLocation() {
		return typLocation;
	}

	public void setTypLocation(String typLocation) {
		this.typLocation = typLocation;
	}

	public File getLocationImage() {
		return locationImage;
	}

	public void setLocationImage(File locationImage) {
		this.locationImage = locationImage;
	}

	public File getSpeciesImage() {
		return speciesImage;
	}

	public void setSpeciesImage(File speciesImage) {
		this.speciesImage = speciesImage;
	}

}
