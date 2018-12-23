package GUI;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import Geom.Point3D;
import PackmanGame.Fruit;
import PackmanGame.Game;
import PackmanGame.Map;
import PackmanGame.Packman;
import PackmanGame.ShortestPathAlgo;
import javafx.scene.text.Font;


public class MainWindow extends JFrame implements MouseListener
{
	ArrayList<Packman> CreatePackmanList = new ArrayList<>();						//packman array list to create a game
	ArrayList<Fruit> CreateFruitList = new ArrayList<>();							//fruit array list to create a game
	public BufferedImage myImage;													//map image
	public BufferedImage packmanImage;												//packman image
	public BufferedImage fruit;														//fruit image
	public Game game = new Game();
	int pick = 0;																	//pick what to create (packman/fruit)
	int gameNum = 1;
	int printPath = 0;
	boolean load = true;															//flag
	
	/**
	 * 
	 */
	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}
	/**
	 * button management.
	 */
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		Menu CreateGame = new Menu("Create Game"); 
		MenuItem packmanButton = new MenuItem("Packman");
		MenuItem fruitButton = new MenuItem("Fruit");
		MenuItem loadGameButton = new MenuItem("Load game");
		MenuItem startButton = new MenuItem("Start");
		MenuItem SaveGameButton = new MenuItem("Save Game");
		MenuItem ClearGameButton = new MenuItem("Clear");
		
		menuBar.add(menu);
		menuBar.add(CreateGame);
		CreateGame.add(packmanButton);
		CreateGame.add(fruitButton);
		CreateGame.add(SaveGameButton);
		CreateGame.add(ClearGameButton);
		menu.add(loadGameButton);
		menu.add(startButton);
		
		this.setMenuBar(menuBar);
		/**
		 *clear button: removes all items from the map. 
		 */
		ClearGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		/**
		 * save button: once packmans and fruits is added, press this to save as 'csv'.
		 */
		SaveGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (CreatePackmanList.size() != 0|| CreateFruitList.size() != 0) {
					game = new Game();
					try {
						game.ListToCsv(CreatePackmanList, CreateFruitList, "Game" + gameNum);
						System.out.println("Created csv file that called: Game" + gameNum + ".csv");
						game.BuildWithCsvFile("Game" + gameNum + ".csv");
						gameNum++;
						CreatePackmanList.removeAll(CreatePackmanList);
						CreateFruitList.removeAll(CreateFruitList);
						remove();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					System.out.println("There is no packman or fruit, cannot start the game..");
				}
			}
		});
		/**
		 * press to add a packman in the clicked location.
		 * pick = 1.
		 */
		packmanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pick = 1;
			}
		});
		/**
		 * press to add a fruit in the clicked location.
		 * pick = 2
		 */
		fruitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pick = 2;
			}
		});
		/**
		 * press to load a game that is saved on your computer.
		 * reads the 'csv' file and show it on the map. 
		 * part of the code is taken from: http://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
		 */
		loadGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
		        chooser.setFileFilter(filter);
		        int returnVal = chooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            System.out.println("You choose to open this file: " + chooser.getSelectedFile().getName());
		        }
		        game.getFruitList().removeAll(game.getFruitList());
				game.getPackmanList().removeAll(game.getPackmanList());
				game.BuildWithCsvFile(chooser.getSelectedFile().toString());
				load = false;
				repaint();
			}
		});
		/**
		 * press to start the game.
		 * starts the algorithm, and printPath = 1 to print.
		 */
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShortestPathAlgo shortestPathAlgo = new ShortestPathAlgo();
				shortestPathAlgo.buildPathAlgo(game);
				printPath = 1;
				load = true;
				repaint();
				load = false;
			}
		});
		try {
			packmanImage = ImageIO.read(new File("packman.png"));
			fruit = ImageIO.read(new File("fruitImage.png"));
			myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * clears the array lists and repaints.
	 * the map is clear now.
	 */
	public void remove() {
		game.getFruitList().removeAll(game.getFruitList());
		game.getPackmanList().removeAll(game.getPackmanList());
		repaint();
	}
	int x = -1;
	int y = -1;
	/**
	 * print method have three parts.
	 * 1. if printPath = 1: the user pressed start.
	 * 2. prints the packmans.
	 * 3. prints the fruits. 
	 */
	public void paint(Graphics g) {
		g.drawImage(myImage, 0, 0, this);
		
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}
		
		if (printPath == 1) {
			g.setColor(Color.red);
			for (int i = 0; i < game.getPackmanList().size(); i++) {
				for (int j = 0; j < game.getPackmanList().get(i).getPath().getFruitList().size(); j++) {
					double x1 = game.getPackmanList().get(i).getLocation().x();
					double y1 = game.getPackmanList().get(i).getLocation().y();
					Point3D packmanPix = Map.gpsToPixel(x1, y1);
					double x2 = game.getPackmanList().get(i).getPath().getFruitList().get(j).getLocation().x();
					double y2 = game.getPackmanList().get(i).getPath().getFruitList().get(j).getLocation().y();
					Point3D fruitPix = Map.gpsToPixel(x2, y2);
					g.drawLine(packmanPix.ix(), -packmanPix.iy(), fruitPix.ix(), -fruitPix.iy());
					game.getPackmanList().get(i).setLocation(game.getPackmanList().get(i).getPath().getFruitList().get(j).getLocation());
					printPath = 0;
				}
			}
			
		}
		double x = 0;
		double y = 0;
		
		for(int i = 0; i <game.getPackmanList().size() ; i++) {
			x = game.getPackmanList().get(i).getLocation().x();
			y = game.getPackmanList().get(i).getLocation().y();
			Point3D gpsPix = Map.gpsToPixel(x, y);
			g.drawImage(packmanImage, gpsPix.ix(), -gpsPix.iy(), this);
		}
		
		for(int i = 0; i <game.getFruitList().size() ; i++) {
			x = game.getFruitList().get(i).getLocation().x();
			y = game.getFruitList().get(i).getLocation().y();
			Point3D gpsPix = Map.gpsToPixel(x, y);
			g.drawImage(fruit, gpsPix.ix(), -gpsPix.iy(), this);
			
		}
		
		
	}
	int packId = 0;
	int fruitId = 0;
	/**
	 *  mouseClicked method have three parts.
	 *  1. pick = 1: the user wants to add a packman.
	 *  2. pick = 2: the user wants to add a fruit.
	 *  3. pick = 0: the user don't want to add anything.
	 *  in every click the system prints the pixels and what added.
	 */
	@Override
	public void mouseClicked(MouseEvent arg) {
		if (!load) {
			remove();
		}
		if (pick == 1) {
			
			System.out.println("Packman Added");
			x = arg.getX();
			y = arg.getY();
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			Point3D p1 = Map.pixelToGps(x,-y);
			Packman pack = new Packman(p1, 1, packId, 1);
			
			CreatePackmanList.add(pack);
			packId++;
			
		}
		else if (pick == 2) {
			System.out.println("Fruit Added");
			x = arg.getX();
			y = arg.getY();
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			Point3D p2 = Map.pixelToGps(x,-y);
			Fruit fruit = new Fruit(p2, fruitId);
			CreateFruitList.add(fruit);
			fruitId++;
			
			
		}
		else {
			System.out.println("mouse Clicked");
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			x = arg.getX();
			y = arg.getY();
				
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("mouse entered");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}