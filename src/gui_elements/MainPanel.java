package gui_elements;

import javax.swing.*;

import main.Tester;
import text_obj.Document;

import java.awt.*;
import java.io.IOException;

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
		Document doc = new Document();
    	try {
			doc.load_from_file("library/testtext.oad");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	read_tab.set_chapter(doc.get_chapter(1));
	}
	
}