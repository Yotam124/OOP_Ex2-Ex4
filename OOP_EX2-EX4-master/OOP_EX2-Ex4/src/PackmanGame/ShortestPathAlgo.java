package PackmanGame;

import java.util.ArrayList;
import java.util.Iterator;
import Geom.Point3D;

public class ShortestPathAlgo {
	
	private ArrayList<Packman> packman;
	private ArrayList<Fruit> fruits;
	
	private int fruitToRemove = 0;
	
	public void ShortestPathAlgo(Game game) {
		this.packman = game.getPackmanList();
		this.fruits = game.getFruitList();
		
		Fruit currentFruit;
		while(!fruits.isEmpty()) {
			for (int i=0 ; i<packman.size() ; i++) {
				currentFruit = eachPackmanBestMove(packman.get(i));
				packman.get(i).path.add(currentFruit);
				fruits.remove(fruitToRemove);
			}
		}
	}
	
	public Fruit eachPackmanBestMove(Packman packman) {
		double pacmanSpeed = packman.getSpeed();
		double packmanRadius =packman.getRadius();
		double bestMove = 0;
		double tempMove = 0;
		double distance2d = 0;
		Fruit bestFruit = this.fruits.get(0);
		for (int i=0 ; i<this.fruits.size() ; i++) {
			distance2d = packman.getLocation().distance2D(this.fruits.get(i).getLocation());
			tempMove = (distance2d-packmanRadius)/pacmanSpeed;
			if (tempMove > bestMove) {
				bestMove = tempMove;
				bestFruit = fruits.get(i);
				this.fruitToRemove = i;
			}
		}
		return bestFruit;
	}
	

}
