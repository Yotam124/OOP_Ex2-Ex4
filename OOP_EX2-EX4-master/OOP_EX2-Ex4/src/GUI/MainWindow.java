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


public class MainWindow extends JFrame implements MouseListener
{
	private static final MenuItem fruitButton = null;
	ArrayList<Packman> CreatePackmanList = new ArrayList<>();
	ArrayList<Fruit> CreateFruitList = new ArrayList<>();
	public BufferedImage myImage;
	public BufferedImage packmanImage;
	public BufferedImage fruit;
	public Game game;
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
		Menu CreateGame = new Menu("Create Game"); 
		MenuItem packmanButton = new MenuItem("Packman");
		MenuItem fruitButton = new MenuItem("Fruit");
		MenuItem loadGameButton = new MenuItem("Load game");
		MenuItem startButton = new MenuItem("Start");
		MenuItem CreateStartButton = new MenuItem("Create csv file");
		game = new Game();
		
		menuBar.add(menu);
		menuBar.add(CreateGame);
		CreateGame.add(packmanButton);
		CreateGame.add(fruitButton);
		CreateGame.add(CreateStartButton);
		menu.add(loadGameButton);
		menu.add(startButton);
		
		this.setMenuBar(menuBar);
		
		CreateStartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int gameNum = 1;
				if (CreatePackmanList.size() != 0|| CreateFruitList.size() != 0) {
					game = new Game();
					try {
						for (int i = 0; i < CreatePackmanList.size(); i++) {
							System.out.println(CreatePackmanList.get(i).toString() + "packman id" + fruitId);
						}
						for (int i = 0; i < CreateFruitList.size(); i++) {
							System.out.println(CreateFruitList.get(i).toString() + "fruit id" + fruitId);
						}
						game.ListToCsv(CreatePackmanList, CreateFruitList, "Game" + gameNum);
						System.out.println("Created csv file...");
						game.BuildWithCsvFile("Game" + gameNum + ".csv");
						gameNum++;
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
		packmanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pick = 1;
			}
		});
		
		fruitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pick = 2;
			}
		});
		
		
		
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
		        game = new Game();
		        System.out.println("" + chooser.getSelectedFile().toString());
				game.BuildWithCsvFile(chooser.getSelectedFile().toString());
				System.out.println(game.getFruitList().toString());
				System.out.println(game.getPackmanList().toString());
				repaint();
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start Button CLicked");
				
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

	int x = -1;
	int y = -1;

	public void paint(Graphics g) {
		//game = new Game();
		g.drawImage(myImage, 0, 0, this);
		
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}
		double x = 0;
		double y = 0;
		for(int i = 0; i <game.getPackmanList().size() ; i++) {
			x = game.getPackmanList().get(i).getLocation().x();
			y = game.getPackmanList().get(i).getLocation().y();
			Point3D gpsPix = Map.gpsToPixel(x, y);
			g.drawImage(packmanImage, gpsPix.ix(), -gpsPix.iy(), this);
			System.out.println("" + gpsPix.ix() + "," + -gpsPix.iy());
			
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
	
	@Override
	public void mouseClicked(MouseEvent arg) {
	
		if (pick == 1) {
			System.out.println("Packman Added");
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			x = arg.getX();
			y = arg.getY();
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			Point3D p1 = Map.pixelToGps(x,-y);
			Packman pack = new Packman(p1, 1, packId, 1);
			
			CreatePackmanList.add(pack);
			packId++;
			repaint();	
		}
		else if (pick == 2) {
			System.out.println("Fruit Added");
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			x = arg.getX();
			y = arg.getY();
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			Point3D p2 = Map.pixelToGps(x,-y);
			Fruit fruit = new Fruit(p2, fruitId);
			System.out.println("p2 = " + p2.toString() + "Fruit id" + fruitId);
			CreateFruitList.add(fruit);
			fruitId++;
			repaint();	
			
		}
		else {
			System.out.println("mouse Clicked");
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			x = arg.getX();
			y = arg.getY();
			repaint();	
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
