package PackmanGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Geom.Point3D;


public class Map {
	static final int mapWidth = 1200, mapHeight = 642;
	// offsets
	static final double mapLongitudeStart = 35.202369;
	static final double mapLatitudeStart = 32.105728;
	static final double mapLongitudeEnd = 35.212416; 
	static final double mapLatitudeEnd = 32.101898;
	static final double mapLongitude = mapLongitudeEnd-mapLongitudeStart;
	static final double mapLatitude = mapLatitudeEnd-mapLatitudeStart;
	
	
	/**
	 * Convert from gps point (lat,lon) to pixel point (x,y)
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static Point3D gpsToPixel(double latitude, double longitude){
	    // use offsets
	    longitude -= mapLongitudeStart;
	    // do inverse because the latitude increases as we go up but the y decreases as we go up.
	    // if we didn't do the inverse then all the y values would be negative.
	    latitude = mapLatitudeStart-latitude;

	    // set x & y using conversion
	    int x = (int) (mapWidth*(longitude/mapLongitude));
	    int y = (int) (mapHeight*(latitude/mapLatitude));

	    return new Point3D(x, y);
	}
	
	/**
	 * Convert from pixel point (x,y) to gps point (lat,lon)
	 * @param x
	 * @param y
	 * @return
	 */
	public static Point3D pixelToGps(double x, double y) {
		double lon = (x/mapWidth)*mapLongitude + mapLongitudeStart;
		double lat = -(y/mapHeight)*mapLatitude + mapLatitudeStart;
		
		return new Point3D(lat, lon);
	}
	
	
	/**
	 * Calculating the distance (2D) and the azimuth between two pixel points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	 public static double[] disBet2Pix(int x1, int y1,int x2,int y2) {
		 int earthRadius = 6371000;
		 double[] details = new double[2];
		 double distance = 0;
		 double azimuth = 0;
		 Point3D p1 = pixelToGps(x1,y1);
		 Point3D p2 = pixelToGps(x2,y2);
		 distance =  p1.distance2D(p2);
		 
		 double lonNorm = Math.cos(Math.toRadians(p1.x()));
			double dx = Math.sin(Math.toRadians(p2.x() - p1.x())) * earthRadius;
			double dy = Math.sin(Math.toRadians(p2.y() - p1.y())) * earthRadius * lonNorm ;
			azimuth = Math.atan(dy/dx);
			azimuth = Math.toDegrees(azimuth);
			if (azimuth < 0) {
				azimuth = 360 + azimuth;
			}
			
			details[0] = distance;
			details[1] = azimuth;
			
		 return details;
	 }
	 
	 
	
	 public static void main(String args[]) {
		  JFrame frame = new JFrame();
		  ImageIcon icon = new ImageIcon("Ariel1.png");
		  JLabel label = new JLabel(icon);
		  frame.add(label);
		  frame.setDefaultCloseOperation
		         (JFrame.EXIT_ON_CLOSE);
		  frame.pack();
		  frame.setVisible(true);
		  
		  
		  label.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			         double x = e.getX();
			         double y = e.getY();
			         System.out.println("X = "+x+", Y = "+y);
			         System.out.println(pixelToGps(x, y));  
			         Point3D ss = pixelToGps(x, y);
			         System.out.println(gpsToPixel(ss.x(), ss.y()));
			    }
			});
		  
	 }
}