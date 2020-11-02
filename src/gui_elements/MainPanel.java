package gui_elements;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;


public class MainPanel extends JPanel {
	
	private JPanel read_panel;
	private JPanel edit_panel;
	private JPanel read_doc_panel;
	private JPanel read_ano_panel;
	private JMenuBar read_doc_menu;
	private JMenuBar read_ano_menu;
	private JMenu read_open_doc;
	private JMenu read_open_ano;
	private JMenuBar edit_doc_menu;
	private JMenuBar edit_ano_menu;
	private JPanel edit_doc_panel;
	private JPanel edit_ano_panel;
	private JMenu edit_open_doc;
	private JMenu edit_open_ano;
	
	public MainPanel() {
		setBackground(Color.DARK_GRAY);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBackground(Color.LIGHT_GRAY);
		
		this.read_panel = new JPanel();
		read_panel.setBorder(null);
		ImageIcon read_icon = new ImageIcon("assets/images/read.png");
		tabs.addTab("<html><font size=+1>Read</font></html>", read_icon, read_panel);
		GridBagLayout gbl_read_panel = new GridBagLayout();
		gbl_read_panel.columnWidths = new int[]{0, 0};
		gbl_read_panel.rowHeights = new int[]{0, 0};
		gbl_read_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_read_panel.rowWeights = new double[]{1.0, 1.0};
		read_panel.setLayout(gbl_read_panel);
		
		read_doc_menu = new JMenuBar();
		read_doc_menu.setBorderPainted(false);
		GridBagConstraints gbc_read_doc_menu = new GridBagConstraints();
		gbc_read_doc_menu.insets = new Insets(0, 0, -10, 0);
		gbc_read_doc_menu.anchor = GridBagConstraints.NORTH;
		gbc_read_doc_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_read_doc_menu.gridx = 0;
		gbc_read_doc_menu.gridy = 0;
		read_panel.add(read_doc_menu, gbc_read_doc_menu);
		
		read_open_doc = new JMenu("Open document");
		read_doc_menu.add(read_open_doc);
		
		read_ano_menu = new JMenuBar();
		read_ano_menu.setBorderPainted(false);
		GridBagConstraints gbc_read_ano_menu = new GridBagConstraints();
		gbc_read_ano_menu.insets = new Insets(0, 0, -10, 0);
		gbc_read_ano_menu.anchor = GridBagConstraints.NORTH;
		gbc_read_ano_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_read_ano_menu.gridx = 1;
		gbc_read_ano_menu.gridy = 0;
		read_panel.add(read_ano_menu, gbc_read_ano_menu);
		
		read_open_ano = new JMenu("Open annotation");
		read_ano_menu.add(read_open_ano);
		
		read_doc_panel = new JPanel();
		read_doc_panel.setBorder(null);
		read_doc_panel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_read_doc_panel = new GridBagConstraints();
		gbc_read_doc_panel.anchor = GridBagConstraints.WEST;
		gbc_read_doc_panel.weighty = 100.0;
		gbc_read_doc_panel.weightx = 3.0;
		gbc_read_doc_panel.fill = GridBagConstraints.BOTH;
		gbc_read_doc_panel.gridx = 0;
		gbc_read_doc_panel.gridy = 1;
		read_panel.add(read_doc_panel, gbc_read_doc_panel);
		
		read_ano_panel = new JPanel();
		read_ano_panel.setBorder(null);
		read_ano_panel.setBackground(Color.GRAY);
		GridBagConstraints gbc_read_ano_panel = new GridBagConstraints();
		gbc_read_ano_panel.anchor = GridBagConstraints.EAST;
		gbc_read_ano_panel.weighty = 100.0;
		gbc_read_ano_panel.weightx = 1.0;
		gbc_read_ano_panel.fill = GridBagConstraints.BOTH;
		gbc_read_ano_panel.gridx = 1;
		gbc_read_ano_panel.gridy = 1;
		read_panel.add(read_ano_panel, gbc_read_ano_panel);
		
		this.edit_panel = new JPanel();
		ImageIcon edit_icon = new ImageIcon("assets/images/edit.png");
		tabs.addTab("<html><font size=+1>Edit</font></html>", edit_icon, edit_panel);
		GridBagLayout gbl_edit_panel = new GridBagLayout();
		gbl_edit_panel.columnWidths = new int[] {0, 0};
		gbl_edit_panel.rowHeights = new int[]{0, 0};
		gbl_edit_panel.columnWeights = new double[]{1.0, 0.0};
		gbl_edit_panel.rowWeights = new double[]{0.0, 0.0};
		edit_panel.setLayout(gbl_edit_panel);
		
		edit_doc_menu = new JMenuBar();
		edit_doc_menu.setBorderPainted(false);
		GridBagConstraints gbc_edit_doc_menu = new GridBagConstraints();
		gbc_edit_doc_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_edit_doc_menu.anchor = GridBagConstraints.NORTH;
		gbc_edit_doc_menu.gridx = 0;
		gbc_edit_doc_menu.gridy = 0;
		edit_panel.add(edit_doc_menu, gbc_edit_doc_menu);
		
		edit_open_doc = new JMenu("Open document");
		edit_doc_menu.add(edit_open_doc);
		
		edit_ano_menu = new JMenuBar();
		edit_ano_menu.setBorderPainted(false);
		GridBagConstraints gbc_edit_ano_menu = new GridBagConstraints();
		gbc_edit_ano_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_edit_ano_menu.anchor = GridBagConstraints.NORTH;
		gbc_edit_ano_menu.gridx = 1;
		gbc_edit_ano_menu.gridy = 0;
		edit_panel.add(edit_ano_menu, gbc_edit_ano_menu);
		
		edit_open_ano = new JMenu("Open annotation");
		edit_ano_menu.add(edit_open_ano);
		
		edit_doc_panel = new JPanel();
		edit_doc_panel.setBorder(null);
		edit_doc_panel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_edit_doc_panel = new GridBagConstraints();
		gbc_edit_doc_panel.weighty = 100.0;
		gbc_edit_doc_panel.weightx = 3.0;
		gbc_edit_doc_panel.anchor = GridBagConstraints.WEST;
		gbc_edit_doc_panel.fill = GridBagConstraints.BOTH;
		gbc_edit_doc_panel.gridx = 0;
		gbc_edit_doc_panel.gridy = 1;
		edit_panel.add(edit_doc_panel, gbc_edit_doc_panel);
		
		edit_ano_panel = new JPanel();
		edit_ano_panel.setBorder(null);
		edit_ano_panel.setBackground(Color.GRAY);
		GridBagConstraints gbc_edit_ano_panel = new GridBagConstraints();
		gbc_edit_ano_panel.anchor = GridBagConstraints.EAST;
		gbc_edit_ano_panel.weighty = 100.0;
		gbc_edit_ano_panel.weightx = 1.0;
		gbc_edit_ano_panel.fill = GridBagConstraints.BOTH;
		gbc_edit_ano_panel.gridx = 1;
		gbc_edit_ano_panel.gridy = 1;
		edit_panel.add(edit_ano_panel, gbc_edit_ano_panel);
		
		add(tabs);
	}
}
