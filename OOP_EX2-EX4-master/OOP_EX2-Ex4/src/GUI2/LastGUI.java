 package GUI2;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
 import javax.swing.JFrame;
 import javax.swing.filechooser.FileNameExtensionFilter;
 
 import Geom.Point3D;
 import PackmanGame.Fruit;
import PackmanGame.Game;
import PackmanGame.Ghosts;
import PackmanGame.Map;
import PackmanGame.Packman;
import PackmanGame.Player;
import PackmanGame.ShortestPathAlgo;
import Robot.Play;
//import javafx.scene.text.Font;


public class LastGUI extends JFrame implements MouseListener
{
	ArrayList<Packman> CreatePackmanList = new ArrayList<>();						//packman array list to create a game
	ArrayList<Fruit> CreateFruitList = new ArrayList<>();							//fruit array list to create a game

	public BufferedImage myImage;													//map image

	public Game game = new Game();
	int pick = 0;																	//pick what to create (packman/fruit)
	boolean load = true;															//flag
	boolean a = false;	
	double azimuth = 0;
	
	String file_name;
	ArrayList<String> board_data;
	
	Player player;
	Play play;
	/**
	 * 
	 */
	public LastGUI() 
	{
		initGUI();		
		this.addMouseListener(this); 
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
 		MenuItem SetLucation = new MenuItem("SetLucation");
 		
 		menuBar.add(menu);
 		menuBar.add(CreateGame);
 		CreateGame.add(packmanButton);
 		CreateGame.add(fruitButton);
 		CreateGame.add(SaveGameButton);
 		CreateGame.add(ClearGameButton);
 		menu.add(loadGameButton);
 		menu.add(SetLucation);
		menu.add(startButton);

		this.setMenuBar(menuBar);
		
		try {
			 myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SetLucation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pick = 1;
				
			}
		});

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
 		        file_name = chooser.getSelectedFile().toString();
		        game.getFruitList().removeAll(game.getFruitList());
				game.getPackmanList().removeAll(game.getPackmanList());
				game.BuildWithCsvFile(chooser.getSelectedFile().toString());
				play = new Play(file_name);
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
				
 				pick = 2;
 				
 				
 				new Thread(new Runnable() {
 					
					@Override
					public void run() {
						play.setIDs(1);
						
						String map_data = play.getBoundingBox();
						System.out.println("Bounding Box info: "+map_data);
						
						ArrayList<String> board_data = play.getBoard();
						for(int i=0;i<board_data.size();i++) {
							System.out.println(board_data.get(i));
						}
						System.out.println();
						
						play.start();
						
						int i=0;
						while(play.isRuning()) {
							System.out.println(azimuth);
							play.rotate(azimuth); 
							String info = play.getStatistics();
							//System.out.println(info);
							board_data = play.getBoard();
							Game tempGame = new Game(board_data);
							game = tempGame;
							repaint();
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				}).start();
			}
		});
 	}
 	/**
 	 * clears the array lists and repaints.
 	 * the map is clear now.
 	 */
 	public void remove() {
 		/*game.getFruitList().removeAll(game.getFruitList());
 		game.getPackmanList().removeAll(game.getPackmanList());*/
 		repaint();
 	}
 	int x = -1;
 	int y = -1;
 	
 	public void paint(Graphics g) {
 		g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
 		
 		if(x!=-1 && y!=-1)
 		{
 			int r = 10;
 			x = x - (r / 2);
 			y = y - (r / 2);
 			g.fillOval(x, y, r, r);
 		}
 	
		double x = 0;
		double y = 0;
		double x2 = 0;
		double y2 = 0;

		for(int i = 0; i <game.getBlockList().size() ; i++) {
			double temp;
			x = game.getBlockList().get(i).getGps1().x();
			y = game.getBlockList().get(i).getGps1().y();
			Point3D gpsPix1 = Map.gpsToPixel(x, y);
			x2 = game.getBlockList().get(i).getGps2().x();
			y2 = game.getBlockList().get(i).getGps2().y();
			Point3D gpsPix2 = Map.gpsToPixel(x2, y2);
			Color color = new Color(0, 0, 0);
			g.setColor(color);
			if (gpsPix1.x() < gpsPix2.x() && gpsPix1.y() <gpsPix2.y()) {
				g.fillRect((int)gpsPix1.x(), (int)gpsPix1.y(), (int)(gpsPix2.x() - gpsPix1.x()), (int)(gpsPix2.y() - gpsPix1.y()));
			}else if (gpsPix1.x() < gpsPix2.x() && gpsPix1.y() > gpsPix2.y()) {
				g.fillRect((int)gpsPix1.x(), (int)(gpsPix2.y()), (int)(gpsPix2.x() - gpsPix1.x()), (int)(gpsPix1.y() - gpsPix2.y()));
			}else if (gpsPix1.x() > gpsPix2.x() && gpsPix1.y() < gpsPix2.y()) {
				g.fillRect((int)gpsPix2.x(), (int)(gpsPix1.y()), (int)(gpsPix1.x() - gpsPix2.x()), (int)(gpsPix2.y() - gpsPix1.y()));
			}else {
				g.fillRect((int)gpsPix2.x(), (int)(gpsPix2.y()), (int)(gpsPix1.x() - gpsPix2.x()), (int)(gpsPix1.y() - gpsPix2.y()));
			}
		}
		for(int i = 0; i <game.getFruitList().size() ; i++) {
			x = game.getFruitList().get(i).getLocation().x();
			y = game.getFruitList().get(i).getLocation().y();
			Point3D gpsPix = Map.gpsToPixel(x, y);
			//g.drawImage(fruit, gpsPix.ix(), -gpsPix.iy(), this);
			try {
				g.drawImage(Fruit.getFruitImage(), gpsPix.ix(), gpsPix.iy(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i <game.getPackmanList().size() ; i++) {
			x = game.getPackmanList().get(i).getLocation().x();
 			y = game.getPackmanList().get(i).getLocation().y();
 			Point3D gpsPix = Map.gpsToPixel(x, y);
 			try {
				g.drawImage(Packman.getPackmanImage(), gpsPix.ix(), gpsPix.iy(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 		
 		
		for(int i = 0; i <game.getGhostsList().size() ; i++) {
			x = game.getGhostsList().get(i).getLocation().x();
			y = game.getGhostsList().get(i).getLocation().y();
			Point3D gpsPix = Map.gpsToPixel(x, y);
			try {
				g.drawImage(Ghosts.getGhostImage(), gpsPix.ix(), gpsPix.iy(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (a) {
			x = game.getPlayer().getLocation().x();
			y = game.getPlayer().getLocation().y();
			Point3D gpsPix = Map.gpsToPixel(x, y);
			try {
				g.drawImage(Player.getPlayerImage(), gpsPix.ix(), gpsPix.iy(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		if(pick == 1) {
			
			System.out.println("Player Added");
			x = arg.getX();
 			y = arg.getY();
 			Point3D location = Map.pixelToGps(x,y);
 			System.out.println(location.x() +" ,,, "+  location.y());
			//play.setInitLocation(location.x(),location.y());
			play.setInitLocation(location.x(),location.y());
			game = new Game(play.getBoard());
			player = new Player(location, game.getPlayer().getSpeed(), game.getPlayer().getID(), game.getPlayer().getRadius());
			game.setPlayer(player);
			pick = 0;
			a = true;
			
		}
		else if(pick == 2) {
			x = arg.getX();
 			y = arg.getY();
			Point3D direction = Map.pixelToGps(x, y);
			azimuth = Map.azimutBetweenGps(game.getPlayer().getLocation(), direction);
			
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