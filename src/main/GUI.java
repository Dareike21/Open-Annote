package main;

import java.awt.*;

import javax.swing.*;

public class GUI extends Frame{

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JFrame main_window;
	private JTabbedPane tabs;
	
	private GridLayout layout;
	
	private JPanel editPanel;
	private JPanel readPanel;
	
	public GUI() {

        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled+Pressed].backgroundPainter", Color.white);
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled].backgroundPainter", Color.white);
		UIManager.put("TabbedPane.selected", Color.WHITE);
		UIManager.put("TabbedPane.selectedBackground", Color.WHITE);
		
		this.main_window = new JFrame();//creating instance of JFrame          
		this.main_window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.main_window.getContentPane().setBackground(Color.DARK_GRAY);
		
		ImageIcon logo = new ImageIcon("assets/images/logo_med.png");
		this.main_window.setIconImage(logo.getImage());
		
		this.tabs = new JTabbedPane();
		this.tabs.setBorder( BorderFactory.createEmptyBorder(20, 0, 0, 0) );
		this.tabs.setBackground(Color.GRAY);
		this.tabs.setFocusable(false);

		ImageIcon read_icon = new ImageIcon("assets/images/read.png");
		this.readPanel = new JPanel(new BorderLayout());
		this.tabs.addTab("<html><font size=+1>Read\t\t\t</font></html>", read_icon, this.readPanel);
		
		ImageIcon edit_icon = new ImageIcon("assets/images/edit.png");
		this.editPanel = new JPanel(new BorderLayout());
		this.tabs.addTab("<html><font size=+1>Edit\t\t\t</font></html>", edit_icon, this.editPanel);	
		
		this.main_window.getContentPane().add(this.tabs,BorderLayout.WEST);
		
		this.layout = new GridLayout(1, 1);
		
		this.main_window.setLayout(layout);//using no layout managers  
		this.main_window.setVisible(true);//making the frame visible
		
	}
	
	private void setup_read_panel() {
		//TODO
	}
	
	private void setup_edit_panel() {
		//TODO
	}
    
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		
	}

}
