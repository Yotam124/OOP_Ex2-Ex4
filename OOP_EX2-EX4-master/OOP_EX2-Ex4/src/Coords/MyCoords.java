package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter{
	
	
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D newGps = new Point3D(gps);
		newGps.toCartesian();
		newGps.add(local_vector_in_meter);
		
		return newGps;
	}
	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3d(Point3D gps0, Point3D gps1) {
		int radius = 6371000;
		double dy = gps0.y() - gps1.y();
		double dx = gps0.x() - gps1.x();
		dy = (dy*Math.PI)/180;
		dx = (dx*Math.PI)/180;
		dy = Math.sin(dy)*radius;
		dx = Math.sin(dx)*radius;
		return Math.sqrt(Math.pow(dy, 2) + Math.pow(dx, 2));
	}
	/** computes the 3D vector (in meters) between two gps like points */
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		gps0.toCartesian();
		gps1.toCartesian();
		double a = gps0.x() - gps1.x();
		double b = gps0.y() - gps1.y();
		double c = gps0.z() - gps1.z();
		Point3D gpsAns = new Point3D(a, b, c);
		return gpsAns;
	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] details = new double[3];
		double dy = gps1.x() - gps0.x();
		double dx = gps1.y() - gps0.y();
		double azimuth = Math.tan(dy/dx);
		details[0] = azimuth;
	}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	public boolean isValid_GPS_Point(Point3D p) {
	}
	
}

