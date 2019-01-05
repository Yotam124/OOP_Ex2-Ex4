package PackmanGame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Ghosts extends Packman {
	
	public static BufferedImage ghost;

	private static final int dmg = 20;

	public Ghosts(Point3D point, double speed, int ID, double Radius) {
		super(point, speed, ID, Radius);
	}

	public int getDmg() {
		return dmg;
	}
	
	public static Image getGhostImage() throws IOException {
		ghost = ImageIO.read(new File("ghostImage.png"));
		
		return ghost;
	}


}