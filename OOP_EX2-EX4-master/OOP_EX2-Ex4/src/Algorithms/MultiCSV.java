package Algorithms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

import File_format.Csv2kml;
import File_format.FileFinder;
import GIS.Element;
import GIS.Layer;
import GIS.Meta_data;
import GIS.project;

public class MultiCSV extends Csv2kml{
	
	static ArrayList<String[]> stringArray= new  ArrayList<String[]>();

	public static void main(String[] args) throws IOException {
		
		
	    Path pathToFile = Paths.get("csvfiles");
		
		FileFinder finder = new FileFinder("*.csv");
		Files.walkFileTree(pathToFile, finder);
		
		
		ArrayList<Path> foundFile = finder.foundpaths;
		
		if (foundFile.size() > 0) {
			for (Path path : foundFile) {
				String name = path.toRealPath(LinkOption.NOFOLLOW_LINKS) + "";
				String separator = "\\";
				String[] clearNames = name.replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
				name = clearNames[clearNames.length-1];
				stringArray = Csv2kml(name);
				Layer layer = new Layer(name);
				
				for (int i = 0; i < stringArray.size(); i++) {
					
					Element element = new Element(stringArray.get(i));
					layer.add(element);
					project pro = new project(layer);
				}
			}
		}else {
			System.out.println("no files were found!");
		}
		

	}


}