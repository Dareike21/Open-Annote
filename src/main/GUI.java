package main;

import java.awt.*;
import gui_elements.MainPanel;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	//MAIN_OBJS
	
	private GridLayout layout;
	
	private MainPanel main_panel;
	
	public GUI() {
		
		ImageIcon icon = new ImageIcon("assets/images/logo_med.png");
		setIconImage(icon.getImage());
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.getContentPane().setBackground(new Color(20,20,20));
		
		this.main_panel = new MainPanel();
		this.getContentPane().setBackground(new Color(20,20,20));
		this.add(main_panel);
		
		this.layout = new GridLayout(1, 1);
		this.setLayout(layout);
		this.setVisible(true);//making the frame visible
	}

		    
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		GUI gui = new GUI();
		
	}

}
