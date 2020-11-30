package gui_elements;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import main.Tester;
import text_obj.*;
import text_obj.Document;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SplitPanel extends JPanel {

	private static final long serialVersionUID = 2827997432620163107L;
	
	private static final int READ_MODE = 0;
	private static final int EDIT_MODE = 1;
	
	private JPanel panel;
	private JMenuBar doc_menu;
	private JMenuBar ano_menu;
	private JPanel doc_panel;
	private JPanel ano_panel;
	private JScrollPane scroll_pane;
	private JTextPane doc_field;
	private JPanel nav_panel;
	private JButton button_back;
	private JButton button_forward;
	private JTextField chapter_field;
	
	private JTextPane anno_field;
	
	private JPanel    edit_anno_pane; //THESE ARE ONLY USED IN EDIT MODE
	private JButton   save_anno_button;
	private JButton   delete_anno_button;
	
	private Document document;
	private ArrayList<AnnotationSet> annotations;
	private Integer font_size = 5;
	private Integer current_chap = 1;
	private JPanel holder;
	
	private ArrayList<String> open_annos;
	private String open_annos_url;
	private ArrayList<Integer[]> open_annos_pos;
	
	private int document_text_len;
	
	private int mode;
	
	public SplitPanel() {
		setBackground(MyColors.BACKGROUND);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		add(panel);
		GridBagLayout gbl_edit_panel = new GridBagLayout();
		gbl_edit_panel.columnWidths = new int[] {0, 0};
		gbl_edit_panel.rowHeights = new int[]{0, 0, 0};
		gbl_edit_panel.columnWeights = new double[]{1.0, 0.0};
		gbl_edit_panel.rowWeights = new double[]{0.0, 0.0, 1.0};
		panel.setLayout(gbl_edit_panel);
		
		doc_menu = new JMenuBar();
		doc_menu.setBorderPainted(false);
		GridBagConstraints gbc_edit_doc_menu = new GridBagConstraints();
		gbc_edit_doc_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_edit_doc_menu.anchor = GridBagConstraints.NORTH;
		gbc_edit_doc_menu.gridx = 0;
		gbc_edit_doc_menu.gridy = 0;
		panel.add(doc_menu, gbc_edit_doc_menu);
		
		ano_menu = new JMenuBar();
		ano_menu.setBorderPainted(false);
		GridBagConstraints gbc_edit_ano_menu = new GridBagConstraints();
		gbc_edit_ano_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_edit_ano_menu.anchor = GridBagConstraints.NORTH;
		gbc_edit_ano_menu.gridx = 1;
		gbc_edit_ano_menu.gridy = 0;
		panel.add(ano_menu, gbc_edit_ano_menu);
		
		doc_panel = new JPanel();
		doc_panel.setBorder(null);
		doc_panel.setBackground(MyColors.BACKGROUND);
		GridBagConstraints gbc_edit_doc_panel = new GridBagConstraints();
		gbc_edit_doc_panel.weighty = 100.0;
		gbc_edit_doc_panel.weightx = 3.0;
		gbc_edit_doc_panel.anchor = GridBagConstraints.WEST;
		gbc_edit_doc_panel.fill = GridBagConstraints.BOTH;
		gbc_edit_doc_panel.gridx = 0;
		gbc_edit_doc_panel.gridy = 1;
		panel.add(doc_panel, gbc_edit_doc_panel);
		SpringLayout sl_doc_panel = new SpringLayout();
		doc_panel.setLayout(sl_doc_panel);
		
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		int margin = (int) Math.round(.375*(w-h)); //these margins should make the document appear 8.5x11 no matter the resolution
		
		scroll_pane = new JScrollPane();
		sl_doc_panel.putConstraint(SpringLayout.NORTH, scroll_pane, 0, SpringLayout.NORTH, doc_panel);
		sl_doc_panel.putConstraint(SpringLayout.WEST, scroll_pane, margin, SpringLayout.WEST, doc_panel); 
		sl_doc_panel.putConstraint(SpringLayout.SOUTH, scroll_pane, 0, SpringLayout.SOUTH, doc_panel);
		sl_doc_panel.putConstraint(SpringLayout.EAST, scroll_pane, -margin, SpringLayout.EAST, doc_panel);
		scroll_pane.getVerticalScrollBar().setUnitIncrement(10);//TODO make scroll speed editable
		doc_panel.add(scroll_pane);
		
		doc_field = new JTextPane(new HTMLDocument());
		doc_field.setEditable(false);
		scroll_pane.setViewportView(doc_field);
				
		ano_panel = new JPanel(new BorderLayout());
		ano_panel.setBorder(null);
		ano_panel.setBackground(MyColors.SIDEBAR);
		GridBagConstraints gbc_edit_ano_panel = new GridBagConstraints();
		gbc_edit_ano_panel.anchor = GridBagConstraints.EAST;
		gbc_edit_ano_panel.weighty = 100.0;
		gbc_edit_ano_panel.weightx = 1.0;
		gbc_edit_ano_panel.fill = GridBagConstraints.BOTH;
		gbc_edit_ano_panel.gridx = 1;
		gbc_edit_ano_panel.gridy = 1;
		panel.add(ano_panel, gbc_edit_ano_panel);
		
		nav_panel = new JPanel();
		GridBagConstraints gbc_nav_panel = new GridBagConstraints();
		gbc_nav_panel.fill = GridBagConstraints.BOTH;
		gbc_nav_panel.gridx = 0;
		gbc_nav_panel.gridy = 2;
		panel.add(nav_panel, gbc_nav_panel);
		nav_panel.setLayout(new BorderLayout(0, 0));
		nav_panel.setPreferredSize(new Dimension(0,25));
		
		holder = new JPanel();
		nav_panel.add(holder, BorderLayout.CENTER);
		holder.setLayout(new BorderLayout());
		
		button_back = new JButton("<");
		holder.add(button_back, BorderLayout.WEST);
		
		button_forward = new JButton(">");
		holder.add(button_forward, BorderLayout.EAST);
		
		chapter_field = new JTextField();

		holder.add(chapter_field);
		chapter_field.setColumns(10);
		chapter_field.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel padding1 = new JLabel("                                            ");
		JLabel padding2 = new JLabel("                                            ");
		
		margin = (int) Math.round(.30*w);
		padding1.setPreferredSize(new Dimension(margin,0));
		padding2.setPreferredSize(new Dimension(margin,0));
		
		nav_panel.add(padding1,BorderLayout.WEST);
		nav_panel.add(padding2,BorderLayout.EAST);
		
		try {
			init_doc_field();
		} catch (BadLocationException | IOException e) {
			e.printStackTrace();
		}
		init_buttons();
		annotations = new ArrayList<AnnotationSet>();
	}
	
	private void init_doc_field() throws BadLocationException, IOException {
		
		SplitPanel master_ref = this;
		
		HTMLEditorKit kit = new HTMLEditorKit();
	    HTMLDocument doc = new HTMLDocument();
	    doc_field.setEditorKit(kit);
	    doc_field.setDocument(doc);
	    
	    HyperlinkListener click_annote_listener = new HyperlinkListener() {

            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {

                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                	String url = e.getDescription();
                	
                	if(url.equals(master_ref.open_annos_url)) {
                		master_ref.clear_open_annos();
                	}
                	else {
	                	master_ref.open_annos_url = url;
	                	master_ref.open_annos = new ArrayList<String>();
	                	master_ref.open_annos_pos = new ArrayList<Integer[]>();
	                	
	                	String[] anno_ids = url.substring(1, url.length()-1).split("/");
	                		                	
	                	for( String id : anno_ids ) {
	                		String[] split = id.split("\\.");
	                		
	                		int annoset = Integer.parseInt(split[1]);      		
	                		int chap = Integer.parseInt(split[0]);
	                		int hash = Integer.parseInt(split[2]);
	                		
	                		String text = master_ref.annotations.get(annoset).get_annochapter(chap).get_annotation_from_hash(hash);
	                		Integer[] pos = master_ref.annotations.get(annoset).get_annochapter(chap).get_position_from_hash(hash);
	                		
	                		master_ref.open_annos.add(text);
	                		master_ref.open_annos_pos.add(pos);
	                		System.out.println(text);
	                		
	                	}
	                	
	                	if(mode == EDIT_MODE) {
	                		master_ref.save_anno_button.setEnabled(false);
	                	}
                	}
                	refresh();
                	
                }
            }
        };
	    
	    doc_field.addHyperlinkListener(click_annote_listener);
	    	    
	}
	
	private void init_buttons() {
		
		SplitPanel master_ref = this;
		
		ano_menu.setPreferredSize(new Dimension(0,23));
		doc_menu.setPreferredSize(new Dimension(0,23));
		
		button_forward.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            master_ref.to_chapter(master_ref.current_chap+1);
	         }
	      });
		
		button_back.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            master_ref.to_chapter(master_ref.current_chap-1);
	         }
	      });
		
		chapter_field.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(master_ref.document==null) {
		    		refresh();
		    	}
		    	
		    	try {
		    		int i = Integer.parseInt(chapter_field.getText());
		    		master_ref.to_chapter(i);
		    	} catch (NumberFormatException nfe) {
		    		refresh();
		    	}
		    }
		});
	}
	
	public void init_read() {
		//initializes split pane with menus/objs for read tab
		
		SplitPanel master_ref = this;
		
		this.mode = SplitPanel.READ_MODE;
		
		//ANNOFIELD
		
		this.anno_field = new JTextPane();
		anno_field.setFont(new Font("SansSerif", Font.PLAIN, 15));
		ano_panel.add(anno_field);
		
		/*HTMLEditorKit kit = new HTMLEditorKit();
	    HTMLDocument doc = new HTMLDocument();
	    anno_field.setEditorKit(kit);
	    anno_field.setDocument(doc);*/
	    anno_field.setPreferredSize(new Dimension(0,1));
	    anno_field.setEditable(false);
	    
		JLabel label1 = new JLabel("    ");      //THESE ARE JUST HERE FOR VISUAL PADDING
		ano_panel.add(label1, BorderLayout.WEST);
		JLabel label2 = new JLabel("    ");
		ano_panel.add(label2, BorderLayout.EAST);
		JLabel label3 = new JLabel("    ");
		ano_panel.add(label3, BorderLayout.NORTH);
		JLabel label4 = new JLabel("    ");
		ano_panel.add(label4, BorderLayout.SOUTH);
	    
	    anno_field.setVisible(false);
	    
		//OPEN DOC
		

		JMenu open_doc = new JMenu("Open document");
		doc_menu.add(open_doc);
		
		JMenuItem OPEN_DOC = new JMenuItem(new AbstractAction("Open file...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				
		        int returnVal = fc.showOpenDialog(master_ref);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            
		            Document doc = new Document();
			    	try {
						doc.load_from_file(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    	master_ref.set_doc(doc);
		            
		        }
		    }
		});
		open_doc.add(OPEN_DOC);
		
		JMenuItem CLOSE_DOC = new JMenuItem(new AbstractAction("Close") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				master_ref.document.close();
		    	master_ref.document = null;
		    	
		    	//Closes annotations when a document is closed 
					for(int i = 0; i < annotations.size(); i++)
					{
						annotations.get(i).close(); 
					}
					annotations.clear(); 
						
		    	refresh();
		    }
		});
		open_doc.add(CLOSE_DOC);
	
		
		/////////////////////////////////////////////////////////////////
		
		JMenu open_ano = new JMenu("Open annotation set");
		ano_menu.add(open_ano);
		
		//TODO Remove specific annotation
		
		JMenuItem OPEN_ANO = new JMenuItem(new AbstractAction("Open file...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				
		        int returnVal = fc.showOpenDialog(master_ref);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            
		            AnnotationSet ano = new AnnotationSet();
			    	try {
						ano.load_from_file(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    	master_ref.add_annotation(ano);
		            
		        }
		    }
		});
		open_ano.add(OPEN_ANO);
		
		JMenuItem CLOSE_ANO = new JMenuItem(new AbstractAction("Close") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < annotations.size(); i++)
				{
					annotations.get(i).close(); 
				}
				annotations.clear(); 
		    	refresh();
		    }
		});
		open_ano.add(CLOSE_ANO);
		
		refresh();
		
	}
	
	public void init_edit() {
		
		SplitPanel master_ref = this;
		
		this.mode = SplitPanel.EDIT_MODE;
		
		//ANNOFIELD
		
		edit_anno_pane = new JPanel( new BorderLayout());
		ano_panel.add(edit_anno_pane, BorderLayout.CENTER);
		
		this.anno_field = new JTextPane();
		anno_field.setPreferredSize(new Dimension(0,1));
		anno_field.getDocument().addDocumentListener( new DocumentListener() {
		    
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
		    	String cur_text = master_ref.anno_field.getText();
		    	if(master_ref.open_annos.get(0).equals(cur_text)) {
		    		save_anno_button.setEnabled(false);
		    	}
		    	else {
		    		save_anno_button.setEnabled(true);
		    	}
		    }
		});
		anno_field.setFont(new Font("SansSerif", Font.PLAIN, 15));
		edit_anno_pane.add(anno_field);
	    
	    JPanel button_holder = new JPanel( new GridLayout(0,2));
	    
		save_anno_button = new JButton("Save");
		delete_anno_button = new JButton("Delete");
		
		save_anno_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int curchap = master_ref.current_chap;
            	Integer[] curpos = master_ref.open_annos_pos.get(0);
            	String newtext = master_ref.anno_field.getText();
            	master_ref.annotations.get(0).get_annochapter(curchap).overwrite_annotation(curpos,newtext);
            	
            	open_annos.set(0, newtext);
            	
				String url = "/"; //Generating this url should probably have its own function, but it's fine for now
				url += Integer.toString(current_chap); //chapter
				url += ".";
				url += Integer.toString(0); //which of the loaded annotation sets
				url += ".";
				url += Integer.toString(AnnoChapter.hash(curpos,newtext)); //hashed id of the annotations
				url += "/";
				
				open_annos_url = url;
            	
            	refresh();
            }
        });
		
		delete_anno_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int curchap = master_ref.current_chap;
            	Integer[] curpos = master_ref.open_annos_pos.get(0);
            	master_ref.annotations.get(0).get_annochapter(curchap).delete_annotation_by_pos(curpos);
            	master_ref.clear_open_annos();
            	refresh();
            }
        });
		
		save_anno_button.setPreferredSize(new Dimension(0,30));
		delete_anno_button.setPreferredSize(new Dimension(0,30));
		
		button_holder.add(save_anno_button);
		button_holder.add(delete_anno_button);
		
		edit_anno_pane.add(button_holder, BorderLayout.SOUTH);
		
		JLabel label1 = new JLabel("    ");      //THESE ARE JUST HERE FOR VISUAL PADDING
		ano_panel.add(label1, BorderLayout.WEST);
		JLabel label2 = new JLabel("    ");
		ano_panel.add(label2, BorderLayout.EAST);
		JLabel label3 = new JLabel("    ");
		ano_panel.add(label3, BorderLayout.NORTH);
		JLabel label4 = new JLabel("    ");
		ano_panel.add(label4, BorderLayout.SOUTH);
	    
		edit_anno_pane.setVisible(false);
	    
		//OPEN DOC
				
		JMenu open_doc = new JMenu("Open document");
		doc_menu.add(open_doc);
				
		JMenuItem OPEN_DOC = new JMenuItem(new AbstractAction("Open file...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				
		        int returnVal = fc.showOpenDialog(master_ref);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            
		            Document doc = new Document();
			    	try {
						doc.load_from_file(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    	master_ref.set_doc(doc);
		            
		        }
		    }
		});
		open_doc.add(OPEN_DOC);
		
		//TODO Save annotations warning 
		JMenuItem CLOSE_DOC = new JMenuItem(new AbstractAction("Close") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				master_ref.document.close();
		    	master_ref.document = null;
		    	
		    	//Closes annotations when document is closed 
		    	for(int i = 0; i < annotations.size(); i++)
				{
					annotations.get(i).close(); 
				}
				annotations.clear(); 
		    	refresh();
		    }
		});
		open_doc.add(CLOSE_DOC);
		
		/////////////////////////////////////////////////////////////////
		
		JButton add_button = new JButton("+");
		add_button.setPreferredSize(new Dimension(0,0));
		add_button.setFont(new Font("Arial", Font.PLAIN, 30));
		
		add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(doc_field.getSelectedText() != null) {
	            	int start = doc_field.getSelectionStart();
	            	int end = doc_field.getSelectionEnd();
	            	
	            	Point start_point = document.get_chapter(current_chap).pos_to_para_pos(start); //TODO PREVENT ANNO COLLISIONS
	            	Point end_point   = document.get_chapter(current_chap).pos_to_para_pos(end);
	            	
	            	Integer[] key = { start_point.x,start_point.y,
	            			          end_point.x,  end_point.y-1   };
	            		            	
	            	annotations.get(0).get_annochapter(current_chap).add_annotation(key, "");
	            	
	            	String url = "/";
					url += Integer.toString(current_chap); //chapter
					url += ".";
					url += Integer.toString(0); //which of the loaded annotation sets
					url += ".";
					url += Integer.toString(AnnoChapter.hash(key, "")); //hashed id of the annotations
					url += "/";
					
					open_annos = new ArrayList<String>();
                	open_annos_pos = new ArrayList<Integer[]>();
					
                	open_annos_url = url;
                	open_annos.add("");
                	open_annos_pos.add(key);
	            	
	            	refresh();
            	}	
            }
        });
		
		ano_menu.add(add_button);
		
		JMenu new_ano = new JMenu("Clear");
		ano_menu.add(new_ano);
		
		JMenuItem NEW_ANO = new JMenuItem(new AbstractAction("New annotation set") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				
		    	AnnotationSet ano = new AnnotationSet();
		    	ano.initialize_empty();
		    	master_ref.add_annotation(ano);
		    	
		    	System.out.println(master_ref.annotations.size());
				
		    }
	
		});
		new_ano.add(NEW_ANO);
		
		JMenu open_ano = new JMenu("Open annotation set");
		ano_menu.add(open_ano);
		
		//TODO Remove specific annotation
		
		JMenuItem OPEN_ANO = new JMenuItem(new AbstractAction("Open file...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				
		        int returnVal = fc.showOpenDialog(master_ref);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            
		            AnnotationSet ano = new AnnotationSet();
			    	try {
						ano.load_from_file(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    	master_ref.add_annotation(ano);
		            
		        }
		    }
		});
		open_ano.add(OPEN_ANO);
		
		JMenuItem CLOSE_ANO = new JMenuItem(new AbstractAction("Close") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < annotations.size(); i++)
				{
					annotations.get(i).close(); 
				}
				annotations.clear();
				clear_open_annos();
		    	refresh();
		    	
		    	
		    }
		});
		open_ano.add(CLOSE_ANO);
		
		JMenu save_ano = new JMenu("Save");
		ano_menu.add(save_ano);
		
		JMenuItem SAVE_ANO = new JMenuItem(new AbstractAction("Save annotation set...") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser savefile = new JFileChooser();
		        savefile.showSaveDialog(savefile);
		        
		        File toSave = savefile.getSelectedFile(); 
		        
		        AnnotationSet curAnnos = annotations.get(0); 

		        curAnnos.save_to_file(toSave);

		    	refresh();
		    }
		});
		save_ano.add(SAVE_ANO);
		
		//Start with a new blank annotation
		
		AnnotationSet ano = new AnnotationSet();
    	ano.initialize_empty();
    	master_ref.add_annotation(ano);
    		
		//
		refresh();
				
	}

	public void set_doc(Document contents) {
		current_chap = 1;
		document = contents;
		refresh(true);
	}
	
	public int add_annotation(AnnotationSet anno) { //returns position that the anno is in the list
		
		if(mode == EDIT_MODE && annotations.size() > 0) {
			//TODO are you sure you want to overwrite?
			annotations = new ArrayList<AnnotationSet>();
		}
		
		int pos = annotations.size();
		annotations.add(anno);
		refresh();
		return pos;
	}
	
	public void remove_annotation(int pos) {
		annotations.remove(pos);
		refresh();
	}
	
	public void set_font_size(int f) {
		font_size = f;
	}
	
	private void refresh() { //By default, refreshes without resetting scroll
		refresh(false);
	}
	
	private void refresh(boolean reset_scroll) {
		
		int scroll_target;
		
		if(reset_scroll) {
			scroll_target = 0;
		}
		else {
			scroll_target = current_scroll();
		}
		
		if(document==null) {
			doc_field.setText("<html></html>");
			chapter_field.setText("");
			return;
		}
		
		if(current_chap == 1) { //handling forward/backward chapter buttons
			button_back.setEnabled(false);
		} 
		else {
			button_back.setEnabled(true);
		}
		
		if(document.get_chapter(current_chap+1)==null) {
			button_forward.setEnabled(false);
		} 
		else {
			button_forward.setEnabled(true);
		}
		
		//Load Chapter
		Chapter chap = document.get_chapter(current_chap);
		ArrayList<AnnoChapter> annos = new ArrayList<AnnoChapter>();
		for( AnnotationSet a : annotations ) {
			annos.add(a.get_annochapter(current_chap));
		}
		
		String font_tag = "<font size="+font_size.toString()+">";
		String html = "";
		
		html += "<html><div>";
		
		document_text_len = 0;
		int para_ind = 0;
		String para;
		while(true) {
			
			para = chap.get_paragraph(para_ind);
			if(para==null||para=="") {
				break;
			}
			
			document_text_len += para.length();
			
			String linked_para = "";
			String last_url = "/";
			
			boolean inside_mark = false; //whether you're inside a <a> tag as it scans across the characters
			
			for(int pos = 0; pos < para.length(); pos++) {
				
				String url = "/";
				
				for( int a_chap = 0; a_chap < annos.size(); a_chap++) {
					if(annos.get(a_chap) == null) {
						continue;
					}
					for( int hash : annos.get(a_chap).at_pos(para_ind, pos)) {
						url += Integer.toString(current_chap); //chapter
						url += ".";
						url += Integer.toString(a_chap); //which of the loaded annotation sets
						url += ".";
						url += Integer.toString(hash); //hashed id of the annotations
						url += "/";
					}
				}
				
				String base_color = "red"; //if you see red, its a problem
				String select_color = "red";
				
				if(this.mode == READ_MODE) {
					base_color = MyColors.READ_UNSELECTED_COLOR;
					select_color = MyColors.READ_SELECTED_COLOR;
				}
				if(this.mode == EDIT_MODE) {
					base_color = MyColors.EDIT_UNSELECTED_COLOR;
					select_color = MyColors.EDIT_SELECTED_COLOR;
				}
				
				String opening_tag = "<a style=\"background-color:"+base_color+"\" href=\""+url+"\">";
				String opening_tag_sel = "<a style=\"background-color:"+select_color+"\" href=\""+url+"\">";
				
				if     ( last_url.equals("/") && !url.equals("/")) {
					if(url.equals(open_annos_url)) {
						linked_para += opening_tag_sel;
					}
					else {
						linked_para += opening_tag;
					}
					inside_mark=true;
				}
				else if( !last_url.equals("/") && url.equals("/") ) {
					linked_para += "</a>";
					inside_mark=false;
				}
				else if( !last_url.equals(url) ) {
					linked_para += "</a>";
					if(url.equals(open_annos_url)) {
						linked_para += opening_tag_sel;
					}
					else {
						linked_para += opening_tag;
					}
					inside_mark=true;
				}
				
				linked_para += para.charAt(pos);
				last_url = url;
			}
			
			if(inside_mark) {
				linked_para += "</a>";
			}
						
			html += "<p>"+font_tag+linked_para+"</font></p><br>";
			para_ind += 1;
		}
		
		html += "</div></html>";
		
		//output
				
		//DOC
		doc_field.setText(html);
		scroll_to(scroll_target);
		
		
		chapter_field.setText(current_chap.toString());
		
		//ANNO
		if(this.mode==SplitPanel.READ_MODE) {
			if(open_annos != null) {
				anno_field.setVisible(true);
				anno_field.setText(open_annos.get(0)); //TODO multiple annos
			}
			else {
				anno_field.setVisible(false);
			}
		}
		else if(this.mode==SplitPanel.EDIT_MODE) { //Right now these are the same, but they may need to be different in the future
			if(open_annos != null) {
				edit_anno_pane.setVisible(true);
				anno_field.setText(open_annos.get(0)); //TODO multiple annos
			}
			else {
				edit_anno_pane.setVisible(false);
			}
		}
	}
	
	private void to_chapter(int i) {
		if(document==null) {
			refresh();
			return;
		}
		if(document.get_chapter(i)==null) {
			refresh();
			return;
		}
		if(current_chap != i) {
			open_annos = null;
			open_annos_url = null;
			current_chap = i;
			refresh(true);
		}
	}
	
	private int current_scroll() {
	    return scroll_pane.getVerticalScrollBar().getValue();
	}
	
	private float scroll_percentage(int i) {
		JScrollBar bar = scroll_pane.getVerticalScrollBar();
		int mid = scroll_pane.getHeight()/2;
		int max = bar.getMaximum();
		int current = i;
		
		if(i <= mid) {
			mid = 0;
		}
		
		float perc = ((float) current + (float) mid)/max;
		
		return perc;
	}
	
	private void scroll_to(int i) {
		doc_field.setCaretPosition(Math.round(scroll_percentage(i)*document_text_len));  //This is not guaranteed to move the caret onto the current view, but it should work fine.
		   																				 //I can do something smarter in the future, when I have time
		   																			     //Although at that point I should probably entirely rewrite this UI
		scroll_pane.getViewport().setViewPosition(new Point(0,i));
		
	}
	
	private void clear_open_annos() {
		open_annos_url = null;
		open_annos = null;
		open_annos_pos = null;
	}
}