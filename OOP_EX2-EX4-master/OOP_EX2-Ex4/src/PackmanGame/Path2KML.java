package PackmanGame;

import java.io.IOException;
import java.util.ArrayList;

import Geom.Point3D;

public class Path2KML {
	private String[] str;
	
	
	public Path2KML(Path path) {
		ArrayList<Fruit> path1 = path.getFruitList();
		 for (int i=0 ; i<path1.size() ; i++) {
			  Point3D gps = new Point3D(path1.get(i).getLocation());
			  str[i] = gps.x()+","+gps.y()+","+gps.z(); 
		 }
		
	}

}
