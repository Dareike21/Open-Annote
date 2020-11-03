package text_obj;

import java.io.*;
import java.util.*;

public class AnnotationSet {
	
	private String format_version = null;
	private int id = 0;
	
	private File file = null;
	private RandomAccessFile reader = null;
	
	private ArrayList<String> tags;
	private HashMap<Integer, AnnoChapter> loaded_annochapters;
	private HashMap<Integer,Long> table_of_contents;
	
	public AnnotationSet() {
		
		this.tags = new ArrayList<String>();
		this.loaded_annochapters = new HashMap<Integer, AnnoChapter>();
		this.table_of_contents = new HashMap<Integer,Long>();
		
	}
	
	public void load_from_file(String path) throws IOException {
		
		this.file = new File(path); 
		this.reader = new RandomAccessFile(this.file,"r");
		
		String line;
		String[] command;
		while(true) {
			line = this.reader.readLine();
			if(line.charAt(0)!=1) {
				continue;
			}
			command = line.substring(1).split(" ");
						
			if(command[0].contentEquals("ID")) {
				this.id = Integer.parseInt(command[1]);
			}
			
			if(command[0].contentEquals("TG")) {
				for(int i = 1; i < command.length; i++) {
					this.tags.add(command[i]);
				}
			}
			
			if(command[0].contentEquals("VR")) {
				this.format_version = command[1];
			}
			
			if(command[0].contentEquals("EDHD")){ //this command marks end of header
				break;
			}
			
		}
		long place;
		while(true) {
			place = reader.getFilePointer();
			line  = reader.readLine();
			
			if(line==null) {
				break;
			}
			if(line.charAt(0)!=1) {
				continue;
			}
			
			command = line.substring(1).split(" ");

			if(command[0].contentEquals("CH")) {
				this.table_of_contents.put(Integer.parseInt(command[1]),place);
			}
			
		}
		
	}
	
	public void load_chapter( Integer chapter_num ) throws IOException {
				
		if(this.loaded_annochapters.containsKey(chapter_num)) { //if already loaded
			return;
		}
		if(!this.table_of_contents.containsKey(chapter_num)) { //if not in table of contents
			return;
		}
		
		this.reader.seek(table_of_contents.get(chapter_num));
		
		String line;
		String[] split;
		ArrayList<Integer> pos;
		AnnoChapter new_annochap = new AnnoChapter();
		
		while(true) {
			line = this.reader.readLine();
			if(line.charAt(0)!=1) {
				split = line.split("\2");
				pos = new ArrayList<Integer>();
				for( String element : split[0].split(" ") ) {
					pos.add(Integer.parseInt(element));
				}
				Integer[] actualpos = {0,0,0,0};
				pos.toArray(actualpos);
				new_annochap.add_annotation(actualpos, split[1]);
			}
			if(line.charAt(0)==1 && line.substring(1).contentEquals("EDCH")) {
				break;
			}
		}
		
		this.loaded_annochapters.put(chapter_num,new_annochap);

		
		//TODO unloading in case of large documents
	}
	
	public void initialize_empty() {
		
		//TODO prepare new blank document
		
	}
	
	public void save_to_file(String name, String path) {
		
		//TODO save to file
		
	}
	
	public int get_id() {
		
		return this.id;
		
	}
	
	public String get_format_ver() {
		
		return this.format_version;
		
	}
	
	public ArrayList<String> get_tags(){
		return this.tags;
	}
	
	public AnnoChapter get_annochapter(Integer i) {
		
		if(!this.loaded_annochapters.containsKey(i)) {
			try {
				this.load_chapter(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return loaded_annochapters.get(i);
		
	}
}
