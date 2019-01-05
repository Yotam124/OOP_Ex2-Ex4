package GUI2;

import javax.swing.JFrame;


public class LastGUIMain 
{
	public static void main(String[] args)
	{
		LastGUI window = new LastGUI();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
