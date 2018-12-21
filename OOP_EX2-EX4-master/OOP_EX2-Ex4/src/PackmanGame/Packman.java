package PackmanGame;

import Geom.Point3D;

public class Packman {
	
	Point3D Location;
	double Azimuth;
	double speed;
	int ID;
	double Radius;
	Path path;
	
	public Packman(Point3D point, double speed, int ID, double Radius) {
		this.Location = point;
		this.speed = speed;
		this.ID = ID;
		this.Radius = Radius;
	}
	
	public Path getPath() {
		return path;
	}


	public void setPath(Path path) {
		this.path = path;
	}


	public Point3D getLocation() {
		return Location;
	}

	public void setLocation(Point3D location) {
		Location = location;
	}

	public double getAzimuth() {
		return Azimuth;
	}

	public void setAzimuth(double azimuth) {
		Azimuth = azimuth;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getRadius() {
		return Radius;
	}

	public void setRadius(double radius) {
		Radius = radius;
	}

}
