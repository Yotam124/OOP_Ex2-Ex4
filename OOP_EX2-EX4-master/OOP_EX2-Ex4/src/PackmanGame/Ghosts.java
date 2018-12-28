package PackmanGame;

import Geom.Point3D;

public class Ghosts extends Packman {

	private static final int dmg = 20;
	
	public Ghosts(Point3D point, double speed, int ID, double Radius) {
		super(point, speed, ID, Radius);
	}
	
	public int getDmg() {
		return dmg;
	}
	

}
