package gui_elements;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	private JTabbedPane tabs;
	
	public SplitPanel read_tab;
	public SplitPanel edit_tab;
	public DocEditorPanel write_tab;
	
	public MainPanel() {
		setLayout(new GridLayout(1,1));
		
		setBackground(MyColors.BACKGROUND);
		
		tabs = new JTabbedPane();
		add(tabs);
		
		read_tab = new SplitPanel();
		read_tab.init_read();
		ImageIcon read_icon = new ImageIcon("assets/images/read.png");
		tabs.addTab("<html><font size=+1>Read</font></html>", read_icon, read_tab);
		
		edit_tab = new SplitPanel();
		edit_tab.init_edit();
		ImageIcon edit_icon = new ImageIcon("assets/images/edit.png");
		tabs.addTab("<html><font size=+1>Edit Annotations</font></html>", edit_icon, edit_tab);
		
		write_tab = new DocEditorPanel();
		ImageIcon write_icon = new ImageIcon("assets/images/write.png");
		tabs.addTab("<html><font size=+1>Edit Documents</font></html>", write_icon, write_tab);
	
	}

}