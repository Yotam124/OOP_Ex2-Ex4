package PackmanGame;

import java.util.ArrayList;

public class Path {
	private ArrayList<Fruit> fruitList;  
	
	public Path() {
		fruitList = new ArrayList<Fruit>();
	}
	
	public void add(Fruit fruit) {
		fruitList.add(fruit);
	}

	public ArrayList<Fruit> getFruitList() {
		return fruitList;
	}

	public void setFruitList(ArrayList<Fruit> fruitList) {
		this.fruitList = fruitList;
	}

	
	
	/*private ArrayList<Point3D> gpsList;
	double pathDistance = 0;
	
	public Path(ArrayList<Point3D> gpsList, Packman packman) {
		Iterator<Point3D> iter = gpsList.iterator();
		while (iter.hasNext()) {
			
		}
	}

	public double getPathDistance() {
		return pathDistance;
	}

	public void setPathDistance(double pathDistance) {
		this.pathDistance = pathDistance;
	}*/
	

}
