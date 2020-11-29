package gui_elements;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.Box;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import text_obj.AnnotationSet;
import text_obj.Chapter;
import text_obj.Document;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class DocEditorPanel extends JPanel {

	private static final long serialVersionUID = 5004766195140504233L;
	
	private ArrayList<String> doc; //array of chapters. Converted to doc obj when saving.
	private int current_chapter = 1;
	
	private JTextField chap_field;
	private JMenuBar menu_bar;
	
	private JButton forward_button;
	private JButton back_button;
	private JButton new_chapter;
	
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
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.weightx = 8.0;
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 0;
		chapter_control.add(horizontalStrut, gbc_horizontalStrut);
		
		back_button = new JButton("<");
		GridBagConstraints gbc_back_button = new GridBagConstraints();
		gbc_back_button.weightx = 1.0;
		gbc_back_button.gridx = 1;
		gbc_back_button.gridy = 0;
		chapter_control.add(back_button, gbc_back_button);
		
		chap_field = new JTextField();
		GridBagConstraints gbc_chap_field = new GridBagConstraints();
		gbc_chap_field.weightx = 0.5;
		gbc_chap_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_chap_field.gridx = 2;
		gbc_chap_field.gridy = 0;
		chapter_control.add(chap_field, gbc_chap_field);
		chap_field.setColumns(10);
		
		forward_button = new JButton(">");
		GridBagConstraints gbc_forward_button = new GridBagConstraints();
		gbc_forward_button.weightx = 1.0;
		gbc_forward_button.gridx = 3;
		gbc_forward_button.gridy = 0;
		chapter_control.add(forward_button, gbc_forward_button);
		
		new_chapter = new JButton("+");
		GridBagConstraints gbc_new_chapter = new GridBagConstraints();
		gbc_new_chapter.gridx = 4;
		gbc_new_chapter.gridy = 0;
		chapter_control.add(new_chapter, gbc_new_chapter);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.weightx = 8.0;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut_1.gridx = 5;
		gbc_horizontalStrut_1.gridy = 0;
		chapter_control.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		post_setup();
	}
	
	private void post_setup() {
		
		DocEditorPanel master_ref = this;
		
		chap_field.setHorizontalAlignment(JTextField.CENTER);
		
		chap_field.setPreferredSize(new Dimension(0,35));
		forward_button.setPreferredSize(new Dimension(50,35));
		back_button.setPreferredSize(new Dimension(50,35));
		new_chapter.setPreferredSize(new Dimension(50,35));
		
		forward_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(doc!= null) {
					String text = Integer.toString(current_chapter + 1);
					chap_field.setText(text);
					refresh();
				}
			}
		});
		
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = Integer.toString(current_chapter - 1);
				chap_field.setText(text);
				refresh();
			}
		});
		
		new_chapter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(doc!= null) {
					String text = Integer.toString(current_chapter + 1);
					chap_field.setText(text);
					refresh();
				}
			}
		});
		
		//MENU
		
		JMenu save_menu = new JMenu("Save");
		menu_bar.add(save_menu);
		
		JMenuItem save_to_file = new JMenuItem(new AbstractAction("Save as...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				
		        int returnVal = fc.showOpenDialog(master_ref);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            
		            Document out_doc = new Document();
		            
		            boolean write = false;
		            ArrayList<String> paraholder;
		            
		            for(int chap_index = doc.size()-1; chap_index > 0; chap_index--) {
		            	if(doc.get(chap_index).length() > 0) {
		            		write= true;
		            	}
		            	if(write) {
		            		paraholder = new ArrayList<String>();
		            		for(String para : doc.get(chap_index).split("\\r?\\n")) {
		            			if(para.length()>0) {
		            				paraholder.add(para);
		            				System.out.println(para);
		            			}
		            		}
		            		out_doc.add_chapter(chap_index, new Chapter(paraholder));
		            	}
		            }
		            try {
						out_doc.save_to_file(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		        }
				
		    }
	
		});
		save_menu.add(save_to_file);
		
		
		JMenu load_menu = new JMenu("Load");
		menu_bar.add(load_menu);
		
		//DOC UPDATE
		
		doc_field.getDocument().addDocumentListener( new DocumentListener() {
			
		    public void insertUpdate(DocumentEvent e) {
		    	change_check();
		    }
		    public void removeUpdate(DocumentEvent e) {
		    	change_check();
		    }
		    public void changedUpdate(DocumentEvent e) {
		    	change_check();
		    }

		    public void change_check() {
		    	doc.set(current_chapter, doc_field.getText());
		    }
			
		});
		
		//
		
		new_doc();
	}
	
	private void new_doc() {
		doc = new ArrayList<String>();
		doc.add(null); //position zero isn't used
		doc.add("");
		chap_field.setText("1");
		refresh();
	}
	
	private void load_doc(ArrayList<String> new_doc) {
		doc = new_doc;
		chap_field.setText("1");
		refresh();
	}
	
	private void refresh() {
		if(doc==null) {
			doc_field.setText("");
			chap_field.setText("");
		}
		else {
			int chap = Integer.parseInt(chap_field.getText());
			if(chap < 1) {              //we don't allow a chapter zero for clarity
				chap=current_chapter;
				forward_button.setEnabled(false);
			}
			else {
				current_chapter=chap;
				forward_button.setEnabled(true);
			}
			chap_field.setText(Integer.toString(current_chapter));
			while(doc.size()<=current_chapter) {
				doc.add("");
			}
			
			if(doc.size()==current_chapter+1) {
				forward_button.setEnabled(false);
				new_chapter.setEnabled(true);
			}
			else {
				forward_button.setEnabled(true);
				new_chapter.setEnabled(false);
			}
			
			doc_field.setText(doc.get(current_chapter));
		}
	}
	
}
