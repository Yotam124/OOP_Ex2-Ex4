package PackmanGame;

import Geom.Point3D;

public class Player extends Packman{
	
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
	
	

}
