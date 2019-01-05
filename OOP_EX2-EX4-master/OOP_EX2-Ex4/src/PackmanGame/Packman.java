package PackmanGame;

import Geom.Point3D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Coords.MyCoords;

public class Packman {
	
	Point3D Location;													//packmans location.
	double Azimuth;														//packmans azimuth.
	double speed;														//packmans speed.
	int ID;																//packmans ID.
	double Radius;														//packmans eat radius.
	Path path;															//path of the packmans eaten fruits.
	
	public static BufferedImage packmanImage;
	
	
	
	/**
	 * packman constructor.
	 * @param point
	 * @param speed
	 * @param ID
	 * @param Radius
	 */
	public Packman(Point3D point, double speed, int ID, double Radius) {
		this.Location = point;
		this.speed = speed;
		this.ID = ID;
		this.Radius = Radius;
	}
	/**
	 * get path.
	 * @return path.
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * set path.
	 * @param path.
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	/**
	 * get packmans location.
	 * @return 3D point
	 */
	public Point3D getLocation() {
		return Location;
	}
	/**
	 * set location.
	 * @param location
	 */
	public void setLocation(Point3D location) {
		Location = location;
	}
	/**
	 * get azimuth.
	 * @return azimuth.
	 */
	public double getAzimuth() {
		return Azimuth;
	}
	/**
	 * set azimuth.
	 * @param azimuth
	 */
	public void setAzimuth(double azimuth) {
		Azimuth = azimuth;
	}
	/**
	 * get packmans speed.
	 * @return speed.
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * set packmans speed.
	 * @param speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * get packmans ID.
	 * @return ID.
	 */
	public int getID() {
		return ID;
	}
	/**
	 * set ID.
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * get packmans eat radius.
	 * @return radius.
	 */
	public double getRadius() {
		return Radius;
	}
	/**
	 * set eat radius.
	 * @param radius
	 */
	public void setRadius(double radius) {
		Radius = radius;
	}

	public static Image getPackmanImage() throws IOException {
		packmanImage = ImageIO.read(new File("packman.png"));
		
		return packmanImage;
	}

}