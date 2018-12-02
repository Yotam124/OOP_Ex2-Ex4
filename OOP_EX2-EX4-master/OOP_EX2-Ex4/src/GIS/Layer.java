package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Layer extends HashSet<Element> {
	
	public HashSet<Element> hashset;
	private String name = "";
	
	public Layer(String name) {
		hashset = new HashSet<Element>();
		this.name = name;
		
	}
	
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	

}
