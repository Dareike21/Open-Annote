package gui_elements;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import javax.swing.text.html.*;

import text_obj.*;
import text_obj.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
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
	
	private JTextPane anno_field_r;
	private JPanel     anno_field_e;
	
	private Document document;
	private ArrayList<AnnotationSet> annotations;
	private Integer font_size = 5;
	private Integer current_chap = 1;
	private JPanel holder;
	
	private ArrayList<String> open_annos;
	private String open_annos_url;
	
	private int scroll_target = 0;
	
	private int mode;
	
	public SplitPanel() {
		setBackground(Color.DARK_GRAY);
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
		doc_panel.setBackground(Color.DARK_GRAY);
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
		ano_panel.setBackground(Color.GRAY);
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
		
		button_back = new JButton("<");
		nav_panel.add(button_back, BorderLayout.WEST);
		
		button_forward = new JButton(">");
		nav_panel.add(button_forward, BorderLayout.EAST);
		
		holder = new JPanel();
		nav_panel.add(holder, BorderLayout.CENTER);
		SpringLayout sl_holder = new SpringLayout();
		holder.setLayout(sl_holder);
		
		margin = (int) Math.round( (3.0/8.0)*w - 100 );
		
		chapter_field = new JTextField();
		sl_holder.putConstraint(SpringLayout.NORTH, chapter_field, 0, SpringLayout.NORTH, holder);
		sl_holder.putConstraint(SpringLayout.WEST, chapter_field, margin, SpringLayout.WEST, holder);
		sl_holder.putConstraint(SpringLayout.SOUTH, chapter_field, 0, SpringLayout.SOUTH, holder);
		sl_holder.putConstraint(SpringLayout.EAST, chapter_field, -margin, SpringLayout.EAST, holder);
		holder.add(chapter_field);
		chapter_field.setColumns(10);
		chapter_field.setHorizontalAlignment(JTextField.CENTER);
		
		///
		try {
			init_doc_field();
		} catch (BadLocationException | IOException e) {
			// TODO Auto-generated catch block
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
	    
	    doc_field.addHyperlinkListener(new HyperlinkListener() {

            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {

                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                	String url = e.getDescription();
                	
                	if(url.equals(master_ref.open_annos_url)) {
                		master_ref.open_annos_url = null;
                		master_ref.open_annos = null;
                	}
                	else {
	                	master_ref.open_annos_url = url;
	                	master_ref.open_annos = new ArrayList<String>();
	                	
	                	String[] anno_ids = url.substring(1, url.length()-1).split("/");
	                		                	
	                	for( String id : anno_ids ) {
	                		String[] split = id.split("\\.");
	                		
	                		int annoset = Integer.parseInt(split[1]);      		
	                		int chap = Integer.parseInt(split[0]);
	                		int hash = Integer.parseInt(split[2]);
	                		
	                		String text = master_ref.annotations.get(annoset).get_annochapter(chap).get_annotation_from_hash(hash);
	                		
	                		master_ref.open_annos.add(text);
	                		
	                	}
                	}
                	refresh();
                	
                }
            }
        });
	    
	    //TODO move scroll to scroll_target when doc_field updates its text, to maintain position
	    
	}
	
	private void init_buttons() {
		
		SplitPanel master_ref = this;
		
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
		
		//ANNOFIELD
		
		this.anno_field_r = new JTextPane();
		ano_panel.add(anno_field_r);
		
		HTMLEditorKit kit = new HTMLEditorKit();
	    HTMLDocument doc = new HTMLDocument();
	    anno_field_r.setEditorKit(kit);
	    anno_field_r.setDocument(doc);
	    anno_field_r.setPreferredSize(new Dimension(0,1));
	    anno_field_r.setEditable(false);
	    
		JLabel label1 = new JLabel("    ");      //THESE ARE JUST HERE FOR VISUAL PADDING
		ano_panel.add(label1, BorderLayout.WEST);
		JLabel label2 = new JLabel("    ");
		ano_panel.add(label2, BorderLayout.EAST);
		JLabel label3 = new JLabel("    ");
		ano_panel.add(label3, BorderLayout.NORTH);
		JLabel label4 = new JLabel("    ");
		ano_panel.add(label4, BorderLayout.SOUTH);
	    
	    anno_field_r.setVisible(false);
	    
		//OPEN DOC
		
		this.mode = SplitPanel.READ_MODE;
		
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
		    	refresh();
		    }
		});
		open_doc.add(CLOSE_DOC);
		
		JMenuItem TEST_LOAD = new JMenuItem(new AbstractAction("LOAD TEST") { //TODO Remove test option

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	Document doc = new Document();
		    	try {
					doc.load_from_file(new File("library/testtext_long.oad"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	master_ref.set_doc(doc);
		    	
		    	//The following code tests the initialize_empty() and save_to_file() functions. 
				//If successful, two new files should be created in the "library" folder. 
				//One is named "savetest.oad" and is identical to "testtext.oad". 
				//The other is named "blanktest.oad" and should be a blank document.
				//Before testing, if the files "savetest.oad" and "blanktest.oad" should be deleted from the "library" folder if they exist. 
		    	//The program will still run if they exist, but (as of now) the program will return a "file already exists" error and not save. 
		    	/*
		    	try {
					doc.save_to_file("savetest", "library/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		    	
		    	doc.initialize_empty();
		    	try {
					doc.save_to_file("blanktest", "library/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
		    }
	
		});
		

		JMenuItem TEST_MOVE = new JMenuItem(new AbstractAction("MOVE TEST") { //TODO Remove test option

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				scroll_to(200);
				//scroll_pane.getViewport().setViewPosition(new Point(0,200));
		    }
	
		});
	    
		open_doc.add(TEST_LOAD);
		open_doc.add(TEST_MOVE);
		
		/////////////////////////////////////////////////////////////////
		
		JMenu open_ano = new JMenu("Open annotation");
		ano_menu.add(open_ano);
		
		//TODO Load annotations
		//TODO Remove specific annotation
		//TODO Close all annotations
		
		JMenuItem TEST_ANO = new JMenuItem(new AbstractAction("LOAD TEST") { //TODO Remove test option

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				
		    	AnnotationSet ano = new AnnotationSet();
		    	try {
		    		ano.load_from_file(new File("library/testano_long.ano"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	master_ref.add_annotation(ano);
				
		    }
	
		});
		open_ano.add(TEST_ANO);
		
		refresh();
		
	}
	
	public void init_edit() {
		
		SplitPanel master_ref=this;
		
		//initializes split pane with menus/objs for edit tab
		
		this.mode = SplitPanel.EDIT_MODE;
		
		JMenu open_doc = new JMenu("Open document");
		doc_menu.add(open_doc);
		
		//////////////////////////
		
		JMenu open_ano = new JMenu("Open annotation");
		ano_menu.add(open_ano);
		
		//TODO Load annotations
		//TODO Remove specific annotation
		//TODO Close all annotations
		
		JMenuItem TEST_ANO = new JMenuItem(new AbstractAction("LOAD TEST") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				
		    	AnnotationSet ano = new AnnotationSet();
		    	try {
		    		ano.load_from_file(new File("library/testano.ano"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	master_ref.add_annotation(ano);
				
		    }
	
		});
		open_ano.add(TEST_ANO);
	}

	public void set_doc(Document contents) {
		current_chap = 1;
		document = contents;
		refresh(true);
	}
	
	public int add_annotation(AnnotationSet anno) { //returns position that the anno is in the list
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
		
		scroll_target = current_scroll();
		
		if(document==null) {
			doc_field.setText("<html></html>");
			chapter_field.setText("");
			return;
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
		
		int para_ind = 0;
		String para;
		while(true) {
			
			para = chap.get_paragraph(para_ind);
			if(para==null||para=="") {
				break;
			}
			
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
					base_color = "#87CEFA";
					select_color = "#57A5FF";
				}
				if(this.mode == EDIT_MODE) {
					base_color = "#90EE90";
					select_color = "4EDA5C";
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
		chapter_field.setText(current_chap.toString());
		
		//ANNO
		if(this.mode==SplitPanel.READ_MODE) {
			if(open_annos != null) {
				anno_field_r.setVisible(true);
				anno_field_r.setText(open_annos.get(0)); //TODO multiple annos
			}
			else {
				anno_field_r.setVisible(false);
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
		JScrollBar verticalScrollBar = scroll_pane.getVerticalScrollBar();
	    return verticalScrollBar.getValue();
	}
	
	private void scroll_to(int i) {
		scroll_pane.getViewport().setViewPosition(new Point(0,i));
	}

}