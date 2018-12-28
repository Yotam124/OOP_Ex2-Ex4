package PackmanGame;

import Coords.MyCoords;
import Geom.Point3D;

public class Block {
	
	private Point3D gps1;
	private Point3D gps2;
	
	private static final int dmg = 1;
	
	public Block(Point3D gps1, Point3D gps2) {
		this.gps1 = gps1;
		this.gps2= gps2;
	}

	public static int getDmg() {
		return dmg;
	}

	public Point3D getGps1() {
		return gps1;
	}

	public Point3D getGps2() {
		return gps2;
	}

	/*public Point3D getGps3() {
		return gps3;
	}

	public Point3D getGps4() {
		return gps4;
	}*/
	

}
