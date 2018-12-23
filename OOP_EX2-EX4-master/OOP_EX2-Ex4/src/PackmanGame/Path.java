package PackmanGame;

import java.util.ArrayList;

public class Path {

	private ArrayList<Fruit> fruitList;  											//list for fruits.
	/**
	 * default constructor.
	 */
	public Path() {
		fruitList = new ArrayList<Fruit>();
	}
	/**
	 * adds a fruit to the path.
	 * @param fruit
	 */
	public void add(Fruit fruit) {
		fruitList.add(fruit);
	}
	/**
	 * get array list.
	 * @return fruits array list.
	 */
	public ArrayList<Fruit> getFruitList() {
		return fruitList;
	}
	/**
	 * set array list.
	 * @param fruitList
	 */
	public void setFruitList(ArrayList<Fruit> fruitList) {
		this.fruitList = fruitList;
	}
	/**
	 * to string method to the path.
	 */
	@Override
	public String toString() {
		return "Path [fruitList=" + fruitList + "]";
	}

}