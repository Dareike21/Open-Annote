package main;

import java.awt.*;

import javax.swing.*;

public class GUI extends Frame{
	
	//MAIN_OBJS
	
	private JFrame main_window;
	private JTabbedPane tabs;
	
	private JPanel edit_panel;
	private JPanel read_panel;
	
	private GridLayout main_layout;
	
	//READ_OBJS
	
	private JMenuBar read_bar;
	private JPanel read_field;
	
	private JMenu read_open_menu;
	private JMenuItem read_open_option;
	
	//EDIT_OBJS
	
	private JMenuBar edit_bar;
	private JPanel edit_field;
	
	public GUI() {

		this.uimanager_setup();
		
		this.main_window = new JFrame();//creating instance of JFrame          
		this.main_window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.main_window.getContentPane().setBackground(new Color(20,20,20));
		
		ImageIcon logo = new ImageIcon("assets/images/logo_med.png");
		this.main_window.setIconImage(logo.getImage());
		
		this.tabs = new JTabbedPane();
		this.tabs.setBorder( BorderFactory.createEmptyBorder(20, 0, 0, 0) );
		this.tabs.setBackground(Color.GRAY);
		this.tabs.setFocusable(false);

		ImageIcon read_icon = new ImageIcon("assets/images/read.png");
		this.read_panel = new JPanel(new BorderLayout());
		this.tabs.addTab("<html><font size=+1>Read</font></html>", read_icon, this.read_panel);
		this.setup_read_panel();
		
		ImageIcon edit_icon = new ImageIcon("assets/images/edit.png");
		this.edit_panel = new JPanel(new BorderLayout());
		this.tabs.addTab("<html><font size=+1>Edit</font></html>", edit_icon, this.edit_panel);	
		this.setup_edit_panel();
		
		this.main_window.getContentPane().add(this.tabs,BorderLayout.WEST);
		
		this.main_layout = new GridLayout(1, 1);
		
		this.main_window.setLayout(main_layout);//using no layout managers  
		this.main_window.setVisible(true);//making the frame visible
		
	}
	
	private void uimanager_setup() {
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled+Pressed].backgroundPainter", Color.white);
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled].backgroundPainter", Color.white);
		UIManager.put("TabbedPane.selected", Color.WHITE);
		UIManager.put("TabbedPane.selectedBackground", Color.WHITE);
		
	}

	private void setup_read_panel() {
		this.read_field = new JPanel(new BorderLayout());
		this.read_field.setBackground(Color.DARK_GRAY);
		
		JPanel subfield = new JPanel(new BorderLayout());
		
		this.read_bar = new JMenuBar();
		
		this.read_open_menu = new JMenu("Open");
		this.read_bar.add(this.read_open_menu);
		
		this.read_open_option = new JMenuItem("Open..."); //TODO add action
		this.read_open_menu.add(this.read_open_option);
		
		subfield.add(this.read_bar, BorderLayout.NORTH);
		subfield.add(this.read_field, BorderLayout.CENTER);
		
		this.read_panel.add(subfield);
		this.read_panel.add(new Label("   "), BorderLayout.SOUTH);
		this.read_panel.add(new Label("   "), BorderLayout.WEST);
		this.read_panel.add(new Label("   "), BorderLayout.EAST);
		
		//TODO
	}
	
	private void setup_edit_panel() {
		//TODO
	}
    
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		
	}

}
