package PackmanGame;

import Geom.Point3D;

public class Fruit {
	
	Point3D Location;
	int ID;
	
	public Fruit(Point3D point, int ID) {
		this.Location = point;
		this.ID = ID;
		
	}

	public Point3D getLocation() {
		return Location;
	}

	public void setLocation(Point3D location) {
		Location = location;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "Fruit [Location=" + Location + ", ID=" + ID + "]";
	}
	
	
}
