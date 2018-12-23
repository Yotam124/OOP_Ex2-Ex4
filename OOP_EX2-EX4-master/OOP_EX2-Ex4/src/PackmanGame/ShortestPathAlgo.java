package PackmanGame;

import java.util.ArrayList;
import java.util.Iterator;
import Geom.Point3D;
import Coords.MyCoords;

public class ShortestPathAlgo {
	
	MyCoords mycorrds = new MyCoords();											//to use MyCoords methods.
	
	public ArrayList<Packman> packman;											//list for packmans.
	public ArrayList<Fruit> fruits;												//list for fruits.
	
	private int fruitToRemove = 0;												//the index of the eaten fruit.
	/**
	 * default constructor.
	 */
	public ShortestPathAlgo() {
		this.packman = new ArrayList<>();
		this.fruits = new ArrayList<>();
	}
	/**
	 * build the path that each packman did to eat his fruits.
	 * @param game
	 */
	public void buildPathAlgo(Game game) {
		this.fruits = game.FruitList;
		Fruit currentFruit;
		for (int i=0 ; i<game.PackmanList.size() ; i++) {
			Path path = new Path();
			game.PackmanList.get(i).setPath(path);
		}
		while(!fruits.isEmpty()) {
			for (int i=0 ; i<game.PackmanList.size() ; i++) {
				if (fruits.isEmpty()) {
					break;
				}else {
					currentFruit = eachPackmanBestMove(game.PackmanList.get(i));
					game.PackmanList.get(i).path.add(currentFruit);
					fruits.remove(fruitToRemove);
				}
			}
		}
	}
	/**
	 * takes a packman and checks which fruit is the best to eat first.
	 * @param packman
	 * @return index of the best fruit to eat.
	 */
	public Fruit eachPackmanBestMove(Packman packman) {
		double pacmanSpeed = packman.getSpeed();
		double packmanRadius =packman.getRadius();
		double bestMove = 0;
		double tempMove = 0;
		double distance2d = 0;
		Fruit bestFruit = fruits.get(0);
		for (int i=0 ; i<fruits.size() ; i++) {
			if (i == 0) {
				distance2d = mycorrds.distance2d(packman.getLocation(),fruits.get(i).getLocation());
				bestMove =  (distance2d-packmanRadius)/pacmanSpeed;
				this.fruitToRemove = 0;
			}else {
				distance2d = mycorrds.distance2d(packman.getLocation(), fruits.get(i).getLocation());
				tempMove =  (distance2d-packmanRadius)/pacmanSpeed;
				if (tempMove < bestMove) {
					bestMove = tempMove;
					bestFruit = fruits.get(i);
					this.fruitToRemove = i;
				}
			}
		}
		return bestFruit;
	}
	

}