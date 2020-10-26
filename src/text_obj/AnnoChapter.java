package text_obj;

import java.util.*;

public class AnnoChapter {
	
	private HashMap<Integer[],String> annotations; //key = {START PARAGRAPH, START CHARACTER, END PARAGRAPH, END CHARACTER}
	
	public AnnoChapter() {
		this.annotations = new HashMap<Integer[],String>();
	}
	
	public void add_annotation(Integer[] key, String anno) {
		this.annotations.put(key, anno);
	}
	
	public String get_annotation(Integer[] key) {
		return this.annotations.get(key);
	}
	
	public Set<Integer[]> get_anno_positions() {
		return this.annotations.keySet();
	}
	
}
