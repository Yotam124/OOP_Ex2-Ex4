package PackmanGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Geom.Point3D;

public class Game {
	
	ArrayList<Packman> PackmanList;											//packman list
	ArrayList<Fruit> FruitList;												//fruit list
	ArrayList<Ghosts> GhostsList;
	ArrayList<Block> BlockList;
	Player player;
	
	/**
	 * default constructor
	 */
	public Game() {
		PackmanList = new ArrayList<>();
		FruitList = new ArrayList<>();
		GhostsList = new ArrayList<>();
		BlockList = new ArrayList<>();
	}
	/**
	 * read a 'csv' file and creates two array lists, one for fruit and one for packman.
	 * @param filename
	 */
	public void BuildWithCsvFile(String filename) {
		int i;
		double Lat = 0,Lon = 0,Alt = 0,SpeedForSec = 0,Radius = 0;
		String Name = null;
		int ID = 0, Line = 0;
		String csvFileName = filename;
		String temp = "";
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFileName)))
        {
        	temp = bufferedReader.readLine();
            while ((temp=bufferedReader.readLine())!=null) {
            	 String[] data = temp.split(",");

            		try {
            			Name = data[0];
            			ID = Integer.parseInt(data[1]);
            			Lat = Double.parseDouble(data[2]);
            			Lon = Double.parseDouble(data[3]);
            			Alt = Double.parseDouble(data[4]);
            			SpeedForSec = Double.parseDouble(data[5]);
            			Radius = Double.parseDouble(data[6]);
            			Line++;
						
					} catch (Exception e) {
						
					}
            		try {
						if (Name.equals("P")) {
							Point3D P = new Point3D(Lat, Lon, Alt);
							Packman p1 = new Packman(P, SpeedForSec, ID, Radius);
							PackmanList.add(p1);
						}
						else if (Name.equals("F")) {
							Point3D F = new Point3D(Lat, Lon, Alt);
							Fruit F1 = new Fruit(F,ID);  
							FruitList.add(F1);
						}
						else if (Name.equals("G")) {
							Point3D G = new Point3D(Lat, Lon, Alt);
							Ghosts G1 = new Ghosts(G, SpeedForSec, ID, Radius);  
							GhostsList.add(G1);
						}
						else if (Name.equals("B")) {
							Point3D b1 = new Point3D(Lat, Lon, Alt);
							Point3D b2 = new Point3D(SpeedForSec, Radius, 0);
							Block B = new Block(b1,b2); 
							BlockList.add(B);
						}
						else if (Name.equals("M")) {
							Point3D M = new Point3D(Lat, Lon, Alt);
							this.player = new Player(M, SpeedForSec, ID, Radius);  
						}
						else {
							System.out.println("Faild to create lane: " + Line + ",there is no 'Type' for the data...");
						}
					} catch (Exception e) {
						//handle exception
					}
            }
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
	}
	/**
	 * takes two array lists and builds a 'csv' file.
	 * @param packmanArray
	 * @param fruitArray
	 * @param FileName
	 * @throws IOException
	 */
	public void ListToCsv(ArrayList<Packman> packmanArray,ArrayList<Fruit> fruitArray ,String FileName) throws IOException {
		FileWriter writer = new FileWriter(FileName + ".csv");
		writer.append("Type");
		writer.append(',');
		writer.append("id");
		writer.append(',');
		writer.append("Lat");
		writer.append(',');
		writer.append("Lon");
		writer.append(',');
		writer.append("Alt");
		writer.append(',');
		writer.append("Speed/Weight");
		writer.append(',');
		writer.append("Radius");
		writer.append("\n");
		for (int i = 0; i < packmanArray.size(); i++) {
			writer.append("P");
			writer.append(',');
			writer.append("" + packmanArray.get(i).getID());
			writer.append(',');
			Point3D p = new Point3D(packmanArray.get(i).getLocation());
			writer.append("" + p.x());
			writer.append(',');
			writer.append("" + p.y());
			writer.append(',');
			writer.append("" + p.z());
			writer.append(',');
			writer.append("" + packmanArray.get(i).getSpeed());
			writer.append(',');
			writer.append("" + packmanArray.get(i).getRadius());
			writer.append(',');
			writer.append('\n');
		}
		for (int i = 0; i < fruitArray.size(); i++) {
			writer.append("F");
			writer.append(',');
			writer.append("" + fruitArray.get(i).getID());
			writer.append(',');
			Point3D p = new Point3D(fruitArray.get(i).getLocation());
			writer.append("" + p.x());
			writer.append(',');
			writer.append("" + p.y());
			writer.append(',');
			writer.append("" + p.z());
			writer.append(',');
			writer.append('\n');
		}
		writer.flush();
		writer.close();
		
	}
	/**
	 * @return packman list.
	 */
	public ArrayList<Packman> getPackmanList() {
		return PackmanList;
	}
	/**
	 * @param packman list.
	 */
	public void setPackmanList(ArrayList<Packman> packmanList) {
		PackmanList = packmanList;
	}
	/**
	 * @return fruit list.
	 */
	public ArrayList<Fruit> getFruitList() {
		return FruitList;
	}
	/**
	 * @param fruit list.
	 */
	public void setFruitList(ArrayList<Fruit> fruitList) {
		FruitList = fruitList;
	}
	public ArrayList<Ghosts> getGhostsList() {
		return GhostsList;
	}
	public void setGhostsList(ArrayList<Ghosts> ghostsList) {
		GhostsList = ghostsList;
	}
	public ArrayList<Block> getBlockList() {
		return BlockList;
	}
	public void setBlockList(ArrayList<Block> blockList) {
		BlockList = blockList;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	

}