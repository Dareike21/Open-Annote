package text_obj;

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
	
}