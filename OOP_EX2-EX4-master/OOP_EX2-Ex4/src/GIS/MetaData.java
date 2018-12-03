package GIS;

import Geom.Point3D;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import Coords.MyCoords;

public class MetaData extends MyCoords implements Meta_data{
	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			
	private String UTC;
	private double CurrentLatitude;
	private double CurrentLongitude;
	private double AltitudeMeters;
	private String color;
	
	public MetaData(String UTC, double lat, double lon, double alt) {
		this.UTC = UTC;
		this.CurrentLatitude = lat;
		this.CurrentLatitude = lon;
		this.AltitudeMeters = alt;
		this.color = null;
		
		
	}
	@Override
	public long getUTC() {
		long ts = 0;
		try {
			ts = dateFormat.parse(this.UTC).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	@Override
	public String get_Color() {
		return this.color;
	}

}
