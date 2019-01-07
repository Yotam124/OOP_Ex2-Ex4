package GUI2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import Coords.LatLonAlt;
import Geom.Circle;
import Geom.Point3D;
import PackmanGame.Map;
import PackmanGame.MyFrame;
import PackmanGame.Player;
import Robot.Game;
import Robot.Packman;
import Robot.Play;


public class MainWindow extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	
	Game game;
	Play play;
	Packman player;
	String file_name;
	Boolean flag = false;
	//MyFrame startThread;
	ArrayList<String> board_data;
	
	int pick = 0;
	
	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}
	
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem loadGameButton = new MenuItem("Load Game");
		MenuItem playerLocationButton = new MenuItem("Set My Location");
		MenuItem startButton = new MenuItem("Start");
		
		menuBar.add(menu);
		menu.add(loadGameButton);
		menu.add(playerLocationButton);
		menu.add(startButton);
		this.setMenuBar(menuBar);
		
		try {
			 myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
		        chooser.setFileFilter(filter);
		        int returnVal = chooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            System.out.println("You choose to open this file: " + chooser.getSelectedFile().getName());
		        }
		        file_name = "data/" + chooser.getSelectedFile().getName();
		        play = new Play(file_name);
		        game = new Game(file_name);
		        repaint();
			}
		});
		
		playerLocationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pick = 1;
				
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Play play1 = new Play(file_name);
				
				new Thread (new Runnable() {
					@Override
					public void run() {
						play1.setIDs(1111,2222,3333);
						
						// 3)Get the GPS coordinates of the "arena"
						String map_data = play1.getBoundingBox();
						System.out.println("Bounding Box info: "+map_data);
						
						// 4) get the game-board data
						ArrayList<String> board_data = play1.getBoard();
						for(int i=0;i<board_data.size();i++) {
							System.out.println(board_data.get(i));
						}
						System.out.println();
						System.out.println("Init Player Location should be set using the bounding box info");
						
						// 5) Set the "player" init location - should be a valid location
						
						play1.setInitLocation(loc.x(),loc.y());
						System.out.println(loc.x() +", "+ loc.y());
						// 6) Start the "server"
						play1.start(); // default max time is 100 seconds (1000*100 ms).
						
						// 7) "Play" as long as there are "fruits" and time
						//	for(int i=0;i<10;i++) {
						int i=0;
							while(play1.isRuning()) {
								i++;
						// 7.1) this is the main command to the player (on the server side)
							play1.rotate(36*i); 
							System.out.println("***** "+i+"******");
							
						// 7.2) get the current score of the game
							String info = play1.getStatistics();
							System.out.println(info);
						// 7.3) get the game-board current state
							board_data = play1.getBoard();
							for(int a=0;a<board_data.size();a++) {
								System.out.println(board_data.get(a));
							}
							Game tempGame = new Game(SideFunctions.readString(board_data));
							game = tempGame;
							repaint();
							System.out.println();
						}
						// 8) stop the server - not needed in the real implementation.
						//play1.stop();
						System.out.println("**** Done Game (user stop) ****");
						
						// 9) print the data & save to the course DB
						String info = play1.getStatistics();
						System.out.println(info);
					}
				}).start();;
					
			}
		});
	}

	double x = -1;
	double y = -1;
	double x1 = 0;
	double y1 = 0;
	Point3D loc;
	public void paint(Graphics g)
	{
		
		g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval((int)x, (int)y, r, r);
		}
		
		for (int i = 0 ; i < game.getGhosts().size() ; i++) {
			try {
				x = game.getGhosts(i).getLocation().x();
				y = game.getGhosts(i).getLocation().y();
				Point3D location = Map.gpsToPixel(x, y);
				g.drawImage(SideFunctions.getGhostImage(), location.ix(), -location.iy(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i=0 ; i<game.getTargets().size() ; i++) {
			try {
				x = game.getTarget(i).getLocation().x();
				y = game.getTarget(i).getLocation().y();
				Point3D location = Map.gpsToPixel(x, y);
				g.drawImage(SideFunctions.getFruitImage(), location.ix(), -location.iy(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i=0 ; i<game.getRobots().size() ; i++) {
			try {
				x = game.getRobots().get(i).getLocation().x();
				y = game.getRobots().get(i).getLocation().y();
				Point3D location = Map.gpsToPixel(x, y);
				g.drawImage(SideFunctions.getPackmanImage(), location.ix(), -location.iy(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*for (int i = 0; i < game.sizeB() ; i++) {
			LatLonAlt max = game.getBox(i).getMax();
			LatLonAlt min = game.getBox(i).getMax();
			int x2 = max.ix();
			int y2 = max.iy();
			double width = game.getBox(i).getWeight();
			g.drawRect(x2, y2, (int)width , 0);
		}*/
		if(flag) {
			try {
				x = game.getPlayer().getLocation().x();
				y = game.getPlayer().getLocation().y();
				Point3D loc1 = Map.gpsToPixel( loc.x(), loc.y());
				g.drawImage(SideFunctions.getPlayerImage(), loc1.ix(), loc1.iy(), this);
				System.out.println(loc1.ix()+"," +loc1.iy());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		x = arg.getX();
		y = arg.getY();
		
		if (pick == 1) {
			 loc = Map.pixelToGps(x, y);
			
			play.setInitLocation(32.10400390342679, 35.2060931887218);
			game = SideFunctions.readString(play.getBoard());
			player = game.getPlayer();
			System.out.println(loc.x()+","+ loc.y());
			System.out.println("Player Location set");
			flag = true;
			repaint();
		}
		
		else if (pick == 2) {
			double[] details;
			double xP = game.getPlayer().getLocation().x();
			double yP = game.getPlayer().getLocation().y();
			Point3D location = Map.gpsToPixel(xP, yP);
			
			details = Map.disBet2Pix(location.ix(), location.iy(), (int)x, (int)y);
			
			game.getPlayer().set_radius(details[0]);
		}
		
		
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
