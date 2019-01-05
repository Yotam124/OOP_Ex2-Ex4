package GUI2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Geom.Point3D;
import PackmanGame.Fruit;
import PackmanGame.Packman;
import Robot.Game;
import Robot.Play;

public class SideFunctions {
	
	public static BufferedImage ghost;
	public static BufferedImage fruit;
	public static BufferedImage packman;
	public static BufferedImage box;
	public static BufferedImage player;
	
	public static PackmanGame.Game game;
	
	public static Image getGhostImage() throws IOException {
		ghost = ImageIO.read(new File("ghostImage.png"));
		
		return ghost;
	}
	
	public static Image getFruitImage() throws IOException {
		fruit = ImageIO.read(new File("fruitImage.png"));
		
		return fruit;
	}
	
	public static Image getPackmanImage() throws IOException {
		packman = ImageIO.read(new File("packman.png"));
		
		return packman;
	}
	
	public static Image getPlayerImage() throws IOException {
		player = ImageIO.read(new File("playerImage.png"));
		
		return player;
	}
	
	public static Game readString(ArrayList<String> list) {
		String[] str = new String[list.size()];
		for(int i = 0 ; i < list.size() ; i++) {
			str[i] = list.get(i);
		}
		game = new Game();
		for(int i = 0 ; i < str.length ; i++) {
			if(i == 0) {
				Robot.Packman m1 = new Robot.Packman(str[i]);
				game.setPlayer(m1);
			}
			else if (str[i].charAt(0) == 'P') {
				Robot.Packman p1 = new Robot.Packman(str[i]);
				game.add(p1);
			}
			else if (str[i].charAt(0) == 'F') {
				Robot.Fruit f1 = new Robot.Fruit(str[i]);  
				game.add(f1);
			}
			else if(str[i].charAt(0) == 'G') {
				Robot.Packman g1 = new Robot.Packman(str[i]);
				game.addGhost(g1);
			}
				else if(str[i].charAt(0) == 'B') {
			Coords.GeoBox b1 = new Coords.GeoBox(str[i]);
				game.add(b1);
			}	
		}
	return game;
	}
	
	
	
	
	public static void main(String[] args) {
		Play play = new Play("Data/Ex4_OOP_example9.csv");
		play.setInitLocation(32.10328,35.253377);
		game = readString(play.getBoard());
		System.out.println(game.getPlayer().toString());
		System.out.println(play.getBoard().toString());

		
		
	}
}
