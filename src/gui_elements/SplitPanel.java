package gui_elements;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import text_obj.*;
import text_obj.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;

public class SplitPanel extends JPanel {

	private static final long serialVersionUID = 2827997432620163107L;
	
	private JPanel panel;
	private JMenuBar doc_menu;
	private JMenuBar ano_menu;
	private JPanel doc_panel;
	private JPanel ano_panel;
	private JTextPane doc_field;
	
	private Chapter chapter;
	private ArrayList<AnnoChapter> annotations;
	private Integer font_size = 5;
	
	public SplitPanel() {
		setBackground(Color.DARK_GRAY);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		add(panel);
		GridBagLayout gbl_edit_panel = new GridBagLayout();
		gbl_edit_panel.columnWidths = new int[] {0, 0};
		gbl_edit_panel.rowHeights = new int[]{0, 0};
		gbl_edit_panel.columnWeights = new double[]{1.0, 0.0};
		gbl_edit_panel.rowWeights = new double[]{0.0, 0.0};
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
		
		JScrollPane scroll_pane = new JScrollPane();
		sl_doc_panel.putConstraint(SpringLayout.NORTH, scroll_pane, 0, SpringLayout.NORTH, doc_panel);
		sl_doc_panel.putConstraint(SpringLayout.WEST, scroll_pane, margin, SpringLayout.WEST, doc_panel);
		sl_doc_panel.putConstraint(SpringLayout.SOUTH, scroll_pane, 0, SpringLayout.SOUTH, doc_panel);
		sl_doc_panel.putConstraint(SpringLayout.EAST, scroll_pane, -margin, SpringLayout.EAST, doc_panel);
		doc_panel.add(scroll_pane);
		
		doc_field = new JTextPane(new HTMLDocument());
		doc_field.setEditable(false);
		scroll_pane.setViewportView(doc_field);
		
		ano_panel = new JPanel();
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
		
		///
		try {
			init_doc_field();
		} catch (BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void init_doc_field() throws BadLocationException, IOException {
		HTMLEditorKit kit = new HTMLEditorKit();
	    HTMLDocument doc = new HTMLDocument();
	    doc_field.setEditorKit(kit);
	    doc_field.setDocument(doc);
	    //kit.insertHTML(doc, doc.getLength(), "<b>hello", 0, 0, HTML.Tag.B);
	    //kit.insertHTML(doc, doc.getLength(), "<font color='red'><u>world</u></font>", 0, 0, null);
		
	}
	
	public void init_read() {
		//TODO initializes split pane with menus/objs for read tab
		
		SplitPanel master_ref = this;
		
		//OPEN DOC
		
		JMenu open_doc = new JMenu("Open document");
		doc_menu.add(open_doc);
		
		JMenuItem open_doc_dia = new JMenuItem("Open from file...");
		open_doc.add(open_doc_dia);
		
		JMenuItem TEST_LOAD = new JMenuItem(new AbstractAction("LOAD TEST") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	Document doc = new Document();
		    	try {
					doc.load_from_file("library/testtext.oad");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	master_ref.set_chapter(doc.get_chapter(1));
		    }
		});
		open_doc.add(TEST_LOAD);
		
		//
	}
	
	public void init_edit() {
		//TODO initializes split pane with menus/objs for edit tab
		
		JMenu open_doc = new JMenu("Open document");
		doc_menu.add(open_doc);
	}

	public void set_chapter(Chapter contents) {
		chapter = contents;
		refresh();
	}
	
	public int add_annotation(AnnoChapter anno) { //returns position that the anno is in the list
		int pos = annotations.size();
		annotations.add(anno);
		return pos;
	}
	
	public void remove_annotation(int pos) {
		
	}
	
	public void set_font_size(int f) {
		font_size = f;
	}

	private void refresh() {
		String font_tag = "<font size="+font_size.toString()+">";
		String html = "";
		
		html = html + "<html><div>";
		
		int i = 0;
		String para;
		while(true) {
			para = chapter.get_paragraph(i);
			if(para==null) {
				break;
			}
			html = html + "<p>"+font_tag+para+"</font></p><br>";
			i += 1;
		}
		
		html = html + "</div></html>";
		
		doc_field.setText(html);
	}
	
}