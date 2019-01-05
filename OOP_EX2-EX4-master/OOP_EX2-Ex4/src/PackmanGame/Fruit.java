package PackmanGame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Fruit {
	
	public static BufferedImage fruitImage;
	
	Point3D Location;
	int ID;
	/**
	 * fruit constructor.
	 * gets a point 3D and ID.
	 * @param point
	 * @param ID
	 */
	public Fruit(Point3D point, int ID) {
		this.Location = point;
		this.ID = ID;
	}
	/**
	 * get location.
	 * @return the fruits location.
	 */
	public Point3D getLocation() {
		return Location;
	}
	/**
	 * set location.
	 * @param point3D.
	 */
	public void setLocation(Point3D location) {
		Location = location;
	}
	/**
	 * get fruits ID.
	 * @return fruits ID.
	 */
	public int getID() {
		return ID;
	}
	/**
	 * set fruits ID.
	 * @param ID
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * to string method.
	 */
	@Override
	public String toString() {
		return "Fruit [Location=" + Location + ", ID=" + ID + "]";
	}
	
	public static Image getFruitImage() throws IOException {
		fruitImage = ImageIO.read(new File("fruitImage.png"));
		
		return fruitImage;
	}
	
	
}