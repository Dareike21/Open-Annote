package gui_elements;

import javax.swing.*;

import main.Tester;

import java.awt.*;

public class MainPanel extends JPanel {
	
	private JTabbedPane tabs;
	
	private SplitPanel read_tab;
	private SplitPanel edit_tab;
	
	public MainPanel() {
		setLayout(new GridLayout(1,1));
		
		tabs = new JTabbedPane();
		add(tabs);
		
		read_tab = new SplitPanel();
		ImageIcon read_icon = new ImageIcon("assets/images/read.png");
		tabs.addTab("<html><font size=+1>Read</font></html>", read_icon, read_tab);
		
		edit_tab = new SplitPanel();
		ImageIcon edit_icon = new ImageIcon("assets/images/edit.png");
		tabs.addTab("<html><font size=+1>Edit</font></html>", edit_icon, edit_tab);
		
		test();
	}
	
	private void test() {
		read_tab.set_doc_field(Tester.lorem_ipsum);
		read_tab.set_doc_field_font(20);
	}
	
}
