package gui_elements;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import text_obj.Document;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class DocEditorPanel extends JPanel {

	private static final long serialVersionUID = 5004766195140504233L;
	
	private ArrayList<String> doc; //array of chapters. Converted to doc obj when saving.
	private int current_chapter;
	
	private JTextField chap_field;
	private JMenuBar menu_bar;
	
	private JButton forward_button;
	private JButton back_button;
	
	private JTextPane doc_field;
	private JScrollPane scroll_pane;
	
	public DocEditorPanel() {
		setLayout(new BorderLayout(0, 0));
		
		menu_bar = new JMenuBar();
		add(menu_bar, BorderLayout.NORTH);
		
		JPanel main_pane = new JPanel();
		main_pane.setBackground(MyColors.BACKGROUND);
		add(main_pane, BorderLayout.CENTER);
		GridBagLayout gbl_main_pane = new GridBagLayout();
		gbl_main_pane.columnWidths = new int[] {0, 0, 0};
		gbl_main_pane.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_main_pane.rowWeights = new double[]{1.0, 0.0};
		main_pane.setLayout(gbl_main_pane);
		
		scroll_pane = new JScrollPane();
		GridBagConstraints gbc_scroll_pane = new GridBagConstraints();
		gbc_scroll_pane.gridheight = 2;
		gbc_scroll_pane.fill = GridBagConstraints.BOTH;
		gbc_scroll_pane.gridx = 1;
		gbc_scroll_pane.gridy = 0;
		main_pane.add(scroll_pane, gbc_scroll_pane);
		
		doc_field = new JTextPane();
		scroll_pane.setViewportView(doc_field);
		
		Dimension strut_size = new Dimension (400,0);
		
		Component doc_field_strut1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_doc_field_strut1 = new GridBagConstraints();
		gbc_doc_field_strut1.insets = new Insets(0, 0, 5, 0);
		gbc_doc_field_strut1.gridx = 2;
		gbc_doc_field_strut1.gridy = 0;
		doc_field_strut1.setPreferredSize(strut_size);
		main_pane.add(doc_field_strut1, gbc_doc_field_strut1);
		
		Component doc_field_strut2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_doc_field_strut2 = new GridBagConstraints();
		gbc_doc_field_strut2.insets = new Insets(0, 0, 5, 5);
		gbc_doc_field_strut2.gridx = 0;
		gbc_doc_field_strut2.gridy = 0;
		doc_field_strut2.setPreferredSize(strut_size);
		main_pane.add(doc_field_strut2, gbc_doc_field_strut2);
		
		JPanel chapter_control = new JPanel();
		add(chapter_control, BorderLayout.SOUTH);
		GridBagLayout gbl_chapter_control = new GridBagLayout();
		gbl_chapter_control.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_chapter_control.rowHeights = new int[]{0, 0};
		gbl_chapter_control.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_chapter_control.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		chapter_control.setLayout(gbl_chapter_control);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.weightx = 4.0;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 0;
		chapter_control.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		back_button = new JButton("<");
		GridBagConstraints gbc_back_button = new GridBagConstraints();
		gbc_back_button.weightx = 1.0;
		gbc_back_button.insets = new Insets(0, 0, 0, 5);
		gbc_back_button.gridx = 1;
		gbc_back_button.gridy = 0;
		chapter_control.add(back_button, gbc_back_button);
		
		chap_field = new JTextField("No document loaded.");
		GridBagConstraints gbc_chap_field = new GridBagConstraints();
		gbc_chap_field.weightx = 0.5;
		gbc_chap_field.insets = new Insets(0, 0, 0, 5);
		gbc_chap_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_chap_field.gridx = 2;
		gbc_chap_field.gridy = 0;
		chapter_control.add(chap_field, gbc_chap_field);
		chap_field.setColumns(10);
		
		forward_button = new JButton("+");
		GridBagConstraints gbc_forward_button = new GridBagConstraints();
		gbc_forward_button.insets = new Insets(0, 0, 0, 5);
		gbc_forward_button.weightx = 1.0;
		gbc_forward_button.gridx = 3;
		gbc_forward_button.gridy = 0;
		chapter_control.add(forward_button, gbc_forward_button);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.weightx = 4.0;
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut_2.gridx = 4;
		gbc_horizontalStrut_2.gridy = 0;
		chapter_control.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		post_setup();
	}
	
	private void post_setup() {
		chap_field.setPreferredSize(new Dimension(0,35));
		forward_button.setPreferredSize(new Dimension(50,35));
		back_button.setPreferredSize(new Dimension(50,35));
		
		forward_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(doc!= null) {
					
					doc.set(current_chapter, doc_field.getText());
					
					if(current_chapter + 1 == doc.size())
					{
						doc.add(""); 
					}
					
					current_chapter++;
					refresh();
				}
			}
		});
		
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(doc != null)
				{
					doc.set(current_chapter, doc_field.getText());
					
					if(current_chapter != 1)
					{
						current_chapter -= 1; 
					}
					
					refresh();
				}
			}
		});
		
		//MENU
		
		JMenu new_menu = new JMenu("New Document");
		menu_bar.add(new_menu);
		
		JMenuItem NEW_DOC = new JMenuItem(new AbstractAction("New document...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				new_doc();
				
		    }
	
		});
		new_menu.add(NEW_DOC);
		
		/*
		JMenuItem SAVE_DOC = new JMenuItem(new AbstractAction("Save Document...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				
				String nameFile = JOptionPane.showInputDialog("Name file");

				JFileChooser savefile = new JFileChooser();
		        savefile.setSelectedFile(new File(nameFile));
		        savefile.showSaveDialog(savefile);
		        
		        File toSave = savefile.getSelectedFile(); 
		        
		        Document curDoc = new Document();
		        
		        for(int i = 0; i < doc.size(); i++) {
		        	curDoc.
		        }

		        curDoc.save_to_file(toSave);

		    	refresh();
		    }
		});
		menu_bar.add(SAVE_DOC);
		*/
		
		
		JMenu load_menu = new JMenu("Load");
		menu_bar.add(load_menu);
		
		//
		
		new_doc();
	}
	
	private void new_doc() {
		doc = new ArrayList<String>();
		doc.add(null); //position zero isn't used
		doc.add("");
		current_chapter = 1;
		refresh();
	}
	
	private void load_doc(ArrayList<String> new_doc) {
		doc = new_doc;
		current_chapter = 1;
		refresh();
	}
	
	private void refresh() {
		if(doc==null) {
			doc_field.setText("");
			chap_field.setText("No document loaded.");
		}
		else {					//we don't allow a chapter zero for clarity
			
			if(current_chapter + 1 == doc.size()){
				forward_button.setText("+");
			}
			else
			{
				forward_button.setText(">");
			}
			
			/*
			int chap = Integer.parseInt(chap_field.getText());
			if(current_chapter == doc.size()) { 
				current_chapter=chap;
			}
			*/
				
			chap_field.setText(Integer.toString(current_chapter));
			doc_field.setText(doc.get(current_chapter));
		}
	}
	
}
