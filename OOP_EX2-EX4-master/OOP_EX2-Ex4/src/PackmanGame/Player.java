package PackmanGame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Player extends Packman{
	
	public static BufferedImage playerImage;

	private int score = 0;
	/**
	 * constructor.
	 * @param point
	 * @param speed
	 * @param ID
	 * @param Radius
	 */
	public Player(Point3D point, double speed, int ID, double Radius) {
		super(point, speed, ID, Radius);
	}
	/**
	 * add score to the player.
	 * @param score
	 */
	public void addScore(int score) {
		this.score += score;
	}
	/**
	 * subtract score from the player.
	 * @param score
	 */
	public void SubtractionScore(int score) {
		this.score -= score;
	}
	
	public static Image getPlayerImage() throws IOException {
		playerImage = ImageIO.read(new File("playerImage.png"));
		
		return playerImage;
	}
	


}