package Algorithms;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.regex.Pattern;

import File_format.Csv2kml;
import File_format.FileFinder;
import GIS.Element;
import GIS.Layer;
import GIS.Meta_data;
import GIS.Project;

public class MultiCSV extends Csv2kml{
	public static ArrayList<String> fileNames;
	static ArrayList<String[]> stringArray;
		  public static void main(String... args) throws IOException{
			fileNames=new ArrayList<String>();
			Layer layer;
		    String ROOT = "C:\\Users\\yotam\\git\\OOP_Ex2-Ex4\\OOP_EX2-EX4-master";
		    FileVisitor<Path> fileProcessor = new ProcessFile();
		    Files.walkFileTree(Paths.get(ROOT), fileProcessor);
		    for (int i = 0; i < fileNames.size(); i++) {
		    	stringArray=Csv2kml(fileNames.get(i));
		    	layer=new Layer(fileNames.get(i));
		    	for (int j = 0; j < stringArray.size(); j++) {
					Element element=new Element(stringArray.get(j));
					layer.add(element);
				}
		    	Project project=new Project(layer);
		    	project.project2csv();
			}
		  }

		  private static final class ProcessFile extends SimpleFileVisitor<Path> {
		    public FileVisitResult visitFile(
		      Path file, BasicFileAttributes attrs
		    ) throws IOException {
		      System.out.println("Processing file:" + file);
		      if(file.toString().contains(".csv")) {
		      fileNames.add(file.toString());
		      }
		      return FileVisitResult.CONTINUE;
		    }
		    
		    public FileVisitResult preVisitDirectory(
		      Path dir, BasicFileAttributes attrs
		    ) throws IOException {
		      System.out.println("Processing directory:" + dir);
		      return FileVisitResult.CONTINUE;
		    }
		  }
	
	/*

	public static void main(String[] args) throws IOException {
		
		
	    Path pathToFile = Paths.get("csvfiles");
		
		FileFinder finder = new FileFinder("*.csv");
		Files.walkFileTree(pathToFile, finder);
		
		
		ArrayList<Path> foundFile = finder.foundpaths;
		for (int i = 0; i < foundFile.size(); i++) {
			System.out.println(foundFile);
		}
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
		

	}*/


}