package File_format;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Csv2kml { 
	
	
	
	public static ArrayList<String[]> csv2kml(String filename) throws IOException {
		ArrayList<String[]> stringArray= new  ArrayList<String[]>();
		  String csvFileName = filename;
	        String temp = "";
	        
	        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFileName)))
	        {
	        	temp = bufferedReader.readLine();
	        	temp = bufferedReader.readLine();
	        	
	            while ((temp=bufferedReader.readLine())!=null) {
	            	 String[] data = temp.split(",");
	            	 stringArray.add(data);
	            }
	           

	        } catch (IOException e) {
	        	
	            e.printStackTrace();
	        }
	        String kml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +  "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n";
	        for (int i = 0; i < stringArray.size(); i++) {
	        	kml+="<Placemark>\n"+"<Point>"+"\n"+"<coordinates>"+stringArray.get(i)[7]+","+stringArray.get(i)[6]+"</coordinates></Point>"+"\n"+"<description><![CDATA[BSSID: <b></b><br/>Capabilities:</b><br/>Frequency: <b></b><br/>Timestamp: <b></b><br/>Date: <b></b>]]></description><styleUrl>#red</styleUrl>"+"\n"+"<name></name>"+"\n"+"</Placemark>";
			}
	        kml+="\n"+"</Folder>\r\n" + "</Document></kml>";
			
	        java.io.FileWriter fileWriter = new java.io.FileWriter(filename+".kml");
			fileWriter.write(kml);
			fileWriter.close();
			return stringArray;
		
	}	
}