package com.example.rpsrec_proto.location;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;


/**
 * 
 * Uses WGS84 to OS Grid Reference conversion code by Jonathan Stott (jstott.me.uk)
 *
 */
public class GPSToGrid {
	
	public static String gpsToGrid(double latitude, double longitude) {
		
		    LatLng coordinates = new LatLng(latitude, longitude);
		    coordinates.toOSGB36();
		    OSRef gridRef = coordinates.toOSRef();
		    System.out.println("Converted to OS Grid Ref: " + gridRef.toString() + " - "
		        + gridRef.toSixFigureString());
		    return gridRef.toSixFigureString();
	}
	

	
}
