package Coords;

import com.sun.javafx.geom.PickRay;

import Geom.Point3D;

public class MyCoords implements coords_converter{
	public static final int earthRadius = 6371000;
	
	/** 
	 * computes a new point which is the gps point transformed by a 3D vector (in meters)
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double lonNorm = Math.cos(Math.toRadians(gps.x()));
			
		double newX = Math.toDegrees(Math.asin((local_vector_in_meter.x()/earthRadius))) + gps.x();
		double newY = Math.toDegrees(Math.asin((local_vector_in_meter.y()/earthRadius)/lonNorm)) + gps.y();
		double newZ = local_vector_in_meter.z() + gps.z();
		
		Point3D newGps = new Point3D(newX, newY, newZ);
		return  newGps;
	}
	
	/** 
	 * computes the 3D distance (in meters) between the two gps like points 
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double altDiff = gps1.z()-gps0.z();
		double distance2d =  distance2d(gps0, gps1);
		double distance3d = Math.sqrt(Math.pow(altDiff, 2) + Math.pow(distance2d, 2));
		return distance3d;
	}
	
	/**
	 * Help function
	 * Calculating the 2d distance.
	 * @param gps0
	 * @param gps1
	 * @return
	 */
	public static double distance2d(Point3D gps0, Point3D gps1) {
		double lonNorm = Math.cos(Math.toRadians(gps0.x()));
		double dx = gps1.x() - gps0.x();
		double dy = gps1.y() - gps0.y();
		dx = Math.toRadians(dx);
		dy = Math.toRadians(dy);
		dx = Math.sin(dx)*earthRadius;
		dy = Math.sin(dy)*earthRadius*lonNorm;
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
	
	/** 
	 * computes the 3D vector (in meters) between two gps like points 
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double lonNorm = Math.cos(Math.toRadians(gps0.x()));
		double x = Math.sin(Math.toRadians(gps1.x() - gps0.x())) * earthRadius;
		double y = Math.sin(Math.toRadians(gps1.y() - gps0.y())) * earthRadius * lonNorm ;
		double z = gps1.z() - gps0.z();
		Point3D gpsAns = new Point3D(x, y, z);
		return gpsAns;
	}
	
	/**
	 * computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance
	 * @param gps0, gps1
	 * @return details
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] details = new double[3];
		double lonNorm = Math.cos(Math.toRadians(gps0.x()));
		double dx = Math.sin(Math.toRadians(gps1.x() - gps0.x())) * earthRadius;
		double dy = Math.sin(Math.toRadians(gps1.y() - gps0.y())) * earthRadius * lonNorm ;
		double azimuth = Math.atan(dy/dx);
		azimuth = Math.toDegrees(azimuth);
		if (azimuth < 0) {
			azimuth = 360 + azimuth;
		}
		double altDiff = gps1.z()-gps0.z();
		double distance2d = distance2d(gps0, gps1);
		double elevation = Math.toDegrees(Math.tan(altDiff/distance2d));
		double distance3d = distance3d(gps0, gps1);
		
		details[0] = azimuth;
		details[1] = elevation;
		details[2] = distance3d;
		return details;
	}
	
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if (p.x() < -180 || p.x() > 180) {
			return false;
		}
		if (p.y() < -90 || p.y() > 90 ) {
			return false;
		}
		if (p.z() < -450) {
			return false;
		}
		return true;
	}
	
}

