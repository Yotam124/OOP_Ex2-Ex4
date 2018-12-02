package GIS;

import Geom.Point3D;

import java.util.Iterator;

import Coords.MyCoords;

public class MetaData extends MyCoords implements Meta_data{

	private long UTF;
	private double yaw;
	private double pitch;
	private double roll;
	
	public MetaData(String UTF, double lat, double lon, double alt) {
		Point3D gps0 = new Point3D(0,0,0);
		Point3D gps1 = new Point3D(lat,lon,alt);
		double[] details = azimuth_elevation_dist(gps0, gps1);
		yaw = details[0];
		pitch = details[1];
		roll = details[2];
		String[] UTF1 = UTF.split(" ");
		UTF1 = UTF1[0].split("[/-]");
		String UTF2 = "";
		for (int i = 0; i < UTF1.length; i++) {
			UTF2 += UTF1[i];
		}
		this.UTF = Long.parseLong(UTF2);
	}
	@Override
	public long getUTC() {
		return this.UTF;
	}

	@Override
	public String toString() {
		return "MetaData [UTF=" + UTF + ", yaw=" + yaw + ", pitch=" + pitch + ", roll=" + roll + "]";
	}
	@Override
	public Point3D get_Orientation() {
		return null;
	}

}
