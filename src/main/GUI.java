package main;

import java.awt.*;

import javax.swing.*;

public class GUI extends JFrame{
	
	//MAIN_OBJS
	
	private GridLayout layout;
	
	private MainPanel main_panel;
	
	public GUI() {
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.getContentPane().setBackground(new Color(20,20,20));
		
		this.main_panel = new MainPanel();
		this.getContentPane().setBackground(new Color(20,20,20));
		this.add(main_panel);
		
		
		this.layout = new GridLayout(1, 1);
		this.setLayout(layout);
		this.setVisible(true);//making the frame visible
	}
	
	private class MainPanel extends JPanel {
		
		JTabbedPane Tabs;

		JPanel Read_tab;

		JPanel Read_ano_panel;

		JPanel Read_doc_panel;
		JTextArea Read_doc_field;
		JLabel Read_doc_panel_padW;
		JLabel Read_doc_panel_padE;
		JMenuBar Read_doc_bar;
		JMenuBar Read_ano_bar;

		JPanel Edit_tab;

		JPanel Edit_ano_panel;

		JPanel Edit_doc_panel;
		JLabel Edit_doc_panel_padW;
		JLabel Edit_doc_panel_padE;
		JTextArea Edit_doc_field;
		JMenuBar Edit_doc_bar;
		JMenuBar Edit_ano_bar;
		
		JLabel Main_paddingN;
		
		public MainPanel() {
						
			GridBagLayout gbthis = new GridBagLayout();
			GridBagConstraints gbcthis = new GridBagConstraints();
			this.setLayout(gbthis);

			Tabs = new JTabbedPane();
			Tabs.setBackground(new Color(150,150,150));

			Read_tab = new JPanel();
			GridBagLayout gbRead_tab = new GridBagLayout();
			GridBagConstraints gbcRead_tab = new GridBagConstraints();
			Read_tab.setLayout(gbRead_tab);

			Read_ano_panel = new JPanel();
			Read_ano_panel.setBackground(new Color(128,128,128));
			GridBagLayout gbRead_ano_panel = new GridBagLayout();
			GridBagConstraints gbcRead_ano_panel = new GridBagConstraints();
			Read_ano_panel.setLayout(gbRead_ano_panel);
			gbcRead_tab.gridx = 14;
			gbcRead_tab.gridy = 1;
			gbcRead_tab.gridwidth = 6;
			gbcRead_tab.gridheight = 19;
			gbcRead_tab.fill = GridBagConstraints.BOTH;
			gbcRead_tab.weightx = 1;
			gbcRead_tab.weighty = 1;
			gbcRead_tab.anchor = GridBagConstraints.NORTH;
			gbRead_tab.setConstraints(Read_ano_panel, gbcRead_tab);
			Read_tab.add(Read_ano_panel);

			Read_doc_panel = new JPanel();
			Read_doc_panel.setBackground(new Color(96,96,96));
			GridBagLayout gbRead_doc_panel = new GridBagLayout();
			GridBagConstraints gbcRead_doc_panel = new GridBagConstraints();
			Read_doc_panel.setLayout(gbRead_doc_panel);

			Read_doc_field = new JTextArea(2,10);
			gbcRead_doc_panel.gridx = 1;
			gbcRead_doc_panel.gridy = 0;
			gbcRead_doc_panel.gridwidth = 11;
			gbcRead_doc_panel.gridheight = 19;
			gbcRead_doc_panel.fill = GridBagConstraints.BOTH;
			gbcRead_doc_panel.weightx = 10;
			gbcRead_doc_panel.weighty = 1;
			gbcRead_doc_panel.anchor = GridBagConstraints.NORTH;
			gbRead_doc_panel.setConstraints(Read_doc_field, gbcRead_doc_panel);
			Read_doc_panel.add(Read_doc_field);

			Read_doc_panel_padW = new JLabel("");
			Read_doc_panel_padW.setBackground(new Color(96,96,96));
			gbcRead_doc_panel.gridx = 0;
			gbcRead_doc_panel.gridy = 0;
			gbcRead_doc_panel.gridwidth = 1;
			gbcRead_doc_panel.gridheight = 19;
			gbcRead_doc_panel.fill = GridBagConstraints.BOTH;
			gbcRead_doc_panel.weightx = 1;
			gbcRead_doc_panel.weighty = 1;
			gbcRead_doc_panel.anchor = GridBagConstraints.NORTH;
			gbRead_doc_panel.setConstraints(Read_doc_panel_padW, gbcRead_doc_panel);
			Read_doc_panel.add(Read_doc_panel_padW);

			Read_doc_panel_padE = new JLabel("");
			Read_doc_panel_padE.setBackground(new Color(96,96,96));
			gbcRead_doc_panel.gridx = 12;
			gbcRead_doc_panel.gridy = 0;
			gbcRead_doc_panel.gridwidth = 1;
			gbcRead_doc_panel.gridheight = 19;
			gbcRead_doc_panel.fill = GridBagConstraints.BOTH;
			gbcRead_doc_panel.weightx = 1;
			gbcRead_doc_panel.weighty = 1;
			gbcRead_doc_panel.anchor = GridBagConstraints.NORTH;
			gbRead_doc_panel.setConstraints(Read_doc_panel_padE, gbcRead_doc_panel);
			Read_doc_panel.add(Read_doc_panel_padE);
			
			gbcRead_tab.gridx = 0;
			gbcRead_tab.gridy = 1;
			gbcRead_tab.gridwidth = 14;
			gbcRead_tab.gridheight = 19;
			gbcRead_tab.fill = GridBagConstraints.BOTH;
			gbcRead_tab.weightx = 3;
			gbcRead_tab.weighty = 1;
			gbcRead_tab.anchor = GridBagConstraints.NORTH;
			gbRead_tab.setConstraints(Read_doc_panel, gbcRead_tab);
			Read_tab.add(Read_doc_panel);

			Read_doc_bar = new JMenuBar();
			gbcRead_tab.gridx = 0;
			gbcRead_tab.gridy = 0;
			gbcRead_tab.gridwidth = 14;
			gbcRead_tab.gridheight = 1;
			gbcRead_tab.fill = GridBagConstraints.BOTH;
			gbcRead_tab.weightx = 1;
			gbcRead_tab.weighty = 0;
			gbcRead_tab.anchor = GridBagConstraints.NORTH;
			gbRead_tab.setConstraints(Read_doc_bar, gbcRead_tab);
			Read_tab.add(Read_doc_bar);

			Read_ano_bar = new JMenuBar();
			gbcRead_tab.gridx = 14;
			gbcRead_tab.gridy = 0;
			gbcRead_tab.gridwidth = 6;
			gbcRead_tab.gridheight = 1;
			gbcRead_tab.fill = GridBagConstraints.BOTH;
			gbcRead_tab.weightx = 1;
			gbcRead_tab.weighty = 0;
			gbcRead_tab.anchor = GridBagConstraints.NORTH;
			gbRead_tab.setConstraints(Read_ano_bar, gbcRead_tab);
			Read_tab.add(Read_ano_bar);
			Tabs.addTab("<html><font size=+1>Read</font></html>",Read_tab);

			Edit_tab = new JPanel();
			GridBagLayout gbEdit_tab = new GridBagLayout();
			GridBagConstraints gbcEdit_tab = new GridBagConstraints();
			Edit_tab.setLayout(gbEdit_tab);

			Edit_ano_panel = new JPanel();
			Edit_ano_panel.setBackground(new Color(128,128,128));
			GridBagLayout gbEdit_ano_panel = new GridBagLayout();
			GridBagConstraints gbcEdit_ano_panel = new GridBagConstraints();
			Edit_ano_panel.setLayout(gbEdit_ano_panel);
			gbcEdit_tab.gridx = 14;
			gbcEdit_tab.gridy = 1;
			gbcEdit_tab.gridwidth = 6;
			gbcEdit_tab.gridheight = 19;
			gbcEdit_tab.fill = GridBagConstraints.BOTH;
			gbcEdit_tab.weightx = 1;
			gbcEdit_tab.weighty = 1;
			gbcEdit_tab.anchor = GridBagConstraints.NORTH;
			gbEdit_tab.setConstraints(Edit_ano_panel, gbcEdit_tab);
			Edit_tab.add(Edit_ano_panel);

			Edit_doc_panel = new JPanel();
			Edit_doc_panel.setBackground(new Color(96,96,96));
			GridBagLayout gbEdit_doc_panel = new GridBagLayout();
			GridBagConstraints gbcEdit_doc_panel = new GridBagConstraints();
			Edit_doc_panel.setLayout(gbEdit_doc_panel);

			Edit_doc_panel_padW = new JLabel("");
			Edit_doc_panel_padW.setBackground(new Color(96,96,96));
			gbcEdit_doc_panel.gridx = 0;
			gbcEdit_doc_panel.gridy = 0;
			gbcEdit_doc_panel.gridwidth = 1;
			gbcEdit_doc_panel.gridheight = 19;
			gbcEdit_doc_panel.fill = GridBagConstraints.BOTH;
			gbcEdit_doc_panel.weightx = 1;
			gbcEdit_doc_panel.weighty = 1;
			gbcEdit_doc_panel.anchor = GridBagConstraints.NORTH;
			gbEdit_doc_panel.setConstraints(Edit_doc_panel_padW, gbcEdit_doc_panel);
			Edit_doc_panel.add(Edit_doc_panel_padW);

			Edit_doc_panel_padE = new JLabel("");
			Edit_doc_panel_padE.setBackground(new Color(96,96,96));
			gbcEdit_doc_panel.gridx = 13;
			gbcEdit_doc_panel.gridy = 0;
			gbcEdit_doc_panel.gridwidth = 1;
			gbcEdit_doc_panel.gridheight = 19;
			gbcEdit_doc_panel.fill = GridBagConstraints.BOTH;
			gbcEdit_doc_panel.weightx = 1;
			gbcEdit_doc_panel.weighty = 1;
			gbcEdit_doc_panel.anchor = GridBagConstraints.NORTH;
			gbEdit_doc_panel.setConstraints(Edit_doc_panel_padE, gbcEdit_doc_panel);
			Edit_doc_panel.add(Edit_doc_panel_padE);

			Edit_doc_field = new JTextArea(2,10);
			gbcEdit_doc_panel.gridx = 1;
			gbcEdit_doc_panel.gridy = 0;
			gbcEdit_doc_panel.gridwidth = 12;
			gbcEdit_doc_panel.gridheight = 19;
			gbcEdit_doc_panel.fill = GridBagConstraints.BOTH;
			gbcEdit_doc_panel.weightx = 10;
			gbcEdit_doc_panel.weighty = 1;
			gbcEdit_doc_panel.anchor = GridBagConstraints.NORTH;
			gbEdit_doc_panel.setConstraints(Edit_doc_field, gbcEdit_doc_panel);
			Edit_doc_panel.add(Edit_doc_field);
			gbcEdit_tab.gridx = 0;
			gbcEdit_tab.gridy = 1;
			gbcEdit_tab.gridwidth = 14;
			gbcEdit_tab.gridheight = 19;
			gbcEdit_tab.fill = GridBagConstraints.BOTH;
			gbcEdit_tab.weightx = 3;
			gbcEdit_tab.weighty = 1;
			gbcEdit_tab.anchor = GridBagConstraints.NORTH;
			gbEdit_tab.setConstraints(Edit_doc_panel, gbcEdit_tab);
			Edit_tab.add(Edit_doc_panel);

			Edit_doc_bar = new JMenuBar();
			gbcEdit_tab.gridx = 0;
			gbcEdit_tab.gridy = 0;
			gbcEdit_tab.gridwidth = 14;
			gbcEdit_tab.gridheight = 1;
			gbcEdit_tab.fill = GridBagConstraints.BOTH;
			gbcEdit_tab.weightx = 1;
			gbcEdit_tab.weighty = 0;
			gbcEdit_tab.anchor = GridBagConstraints.NORTH;
			gbEdit_tab.setConstraints(Edit_doc_bar, gbcEdit_tab);
			Edit_tab.add(Edit_doc_bar);

			Edit_ano_bar = new JMenuBar();
			gbcEdit_tab.gridx = 14;
			gbcEdit_tab.gridy = 0;
			gbcEdit_tab.gridwidth = 6;
			gbcEdit_tab.gridheight = 1;
			gbcEdit_tab.fill = GridBagConstraints.BOTH;
			gbcEdit_tab.weightx = 1;
			gbcEdit_tab.weighty = 0;
			gbcEdit_tab.anchor = GridBagConstraints.NORTH;
			gbEdit_tab.setConstraints(Edit_ano_bar, gbcEdit_tab);
			Edit_tab.add(Edit_ano_bar);
			Tabs.addTab("<html><font size=+1>Edit</font></html>",Edit_tab);
			gbcthis.gridx = 1;
			gbcthis.gridy = 1;
			gbcthis.gridwidth = 18;
			gbcthis.gridheight = 18;
			gbcthis.fill = GridBagConstraints.BOTH;
			gbcthis.weightx = 1;
			gbcthis.weighty = 1;
			gbcthis.anchor = GridBagConstraints.NORTH;
			gbthis.setConstraints(Tabs, gbcthis);
			this.add(Tabs);

			Main_paddingN = new JLabel("     ");
			Main_paddingN.setBackground(new Color(64,64,64));
			gbcthis.gridx = 0;
			gbcthis.gridy = 0;
			gbcthis.gridwidth = 20;
			gbcthis.gridheight = 1;
			gbcthis.fill = GridBagConstraints.BOTH;
			gbcthis.weightx = 0;
			gbcthis.weighty = 0;
			gbcthis.anchor = GridBagConstraints.NORTH;
			gbthis.setConstraints(Main_paddingN, gbcthis);
			this.add(Main_paddingN);
			
			this.setBackground(new Color(45,45,45));
			
			this.menu_setup();
			
		}

		private void menu_setup() {
			
			//TODO
			
		}


		
	}
    
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		
	}

}
