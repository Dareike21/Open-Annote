package text_obj;

import java.awt.Point;
import java.util.*;

public class AnnoChapter {
	
	private HashMap<Integer[],String> annotations;    //key = {START PARAGRAPH, START CHARACTER, END PARAGRAPH, END CHARACTER}
	private HashMap<Integer,String> hash_annotations; //HASH:STRING
	private HashMap<Integer,Integer[]> hash_positions;//HASH:key
	
	//TODO FIX HASHING COLLISIONS FOR SAME TEXT (shouldn't be a problem for now, though);
	
	public AnnoChapter() {
		this.annotations = new HashMap<Integer[],String>();
		this.hash_annotations = new HashMap<Integer,String>();
		this.hash_positions   = new HashMap<Integer,Integer[]>();
	}
	
	public void add_annotation(Integer[] key, String anno) {
		this.annotations.put(key, anno);
		this.hash_annotations.put(anno.hashCode(),anno);
		this.hash_positions.put(anno.hashCode(),key);
	}
	
	public void overwrite_annotation(Integer[] key, String anno) {
		String old_anno = annotations.remove(key);
		if(old_anno != null) {
			Integer hash = old_anno.hashCode();
			hash_annotations.remove(hash);
			hash_positions.remove(hash);
		}
		add_annotation(key,anno);
	}
	
	public String get_annotation(Integer[] key) {
		return this.annotations.get(key);
	}
	
	public String get_annotation_from_hash(Integer hash) {
		return this.hash_annotations.get(hash);
	}
	
	public Integer[] get_position_from_hash(Integer hash) {
		return this.hash_positions.get(hash);
	}
	
	public void delete_annotation_by_pos(Integer[] pos) {
		String anno = annotations.remove(pos);
		if(anno != null) {
			Integer hash = anno.hashCode();
			hash_annotations.remove(hash);
			hash_positions.remove(hash);
		}
	}
	
	public Set<Integer[]> get_anno_positions() {
		return this.annotations.keySet();
	}
	
	public ArrayList<Integer> at_pos(int para, int i){
		ArrayList<Integer> pos = new ArrayList<Integer>();
		
		for( Integer[] range : this.annotations.keySet() ) {
			
			if(range[0] == range[2] && para == range[0]) {
				if(range[1] <= i && i <= range[3]) {
					pos.add( annotations.get(range).hashCode() );
				}
			}
			
			if(range[0] < range[2] && para == range[0]) {
				if(range[1] <= i) {
					pos.add( annotations.get(range).hashCode() );
				}
			}
			
			if(range[0] < range[2] && para == range[2]) {
				if(i <= range[3]) {
					pos.add( annotations.get(range).hashCode() );
				}
			}
			
			if(range[0] < para && para < range[2]) {
				pos.add( annotations.get(range).hashCode() );
			}
			
		}
		
		return pos;
	}
	
}
