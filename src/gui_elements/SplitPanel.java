package gui_elements;

import javax.swing.*;
import java.awt.*;

public class SplitPanel extends JPanel {

	private static final long serialVersionUID = 2827997432620163107L;
	
	public JPanel panel;
	public JMenuBar doc_menu;
	public JMenuBar ano_menu;
	public JPanel doc_panel;
	public JPanel ano_panel;
	public JMenu open_doc;
	public JMenu open_ano;
	
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
		
		open_doc = new JMenu("Open document");
		doc_menu.add(open_doc);
		
		ano_menu = new JMenuBar();
		ano_menu.setBorderPainted(false);
		GridBagConstraints gbc_edit_ano_menu = new GridBagConstraints();
		gbc_edit_ano_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_edit_ano_menu.anchor = GridBagConstraints.NORTH;
		gbc_edit_ano_menu.gridx = 1;
		gbc_edit_ano_menu.gridy = 0;
		panel.add(ano_menu, gbc_edit_ano_menu);
		
		open_ano = new JMenu("Open annotation");
		ano_menu.add(open_ano);
		
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
	}
}
