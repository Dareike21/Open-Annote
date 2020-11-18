package text_obj;

import java.awt.Point;
import java.util.ArrayList;

public class Chapter {
	
	private ArrayList<String> paragraphs;
	
	public Chapter(ArrayList<String> para) {
		
		this.paragraphs = para;
		
	}
	
	public String get_paragraph(int i) {
		
		if(i<paragraphs.size()) {
			return paragraphs.get(i);
		}
		else {
			return null;
		}
		
	}
	
	public Point pos_to_para_pos(int i) {
		int pos = i-1;
		int cha = 0;
		for( String para : paragraphs) {
			if(pos > para.length()) {
				pos -= para.length() + 3;
				cha += 1;
			}
			else {
				return new Point(cha,pos);
			}
		}
		return null;
	}
	
}