package text_obj;

import java.io.*;
import java.util.*;

public class Document {
	
	private String format_version = null;
	private int id = 0;
	
	private File file = null;
	private RandomAccessFile reader = null;
	
	private ArrayList<String> tags;
	private HashMap<Integer,Chapter> loaded_chapters; //Loads chapters as needed
	private HashMap<Integer,Long> table_of_contents;
	
	private int loading_size = 10; //number of chapters allowed to be loaded at a time
	
	public Document(){
		
		this.tags = new ArrayList<String>();
		this.loaded_chapters = new HashMap<Integer,Chapter>();
		this.table_of_contents = new HashMap<Integer,Long>();
		
	}
	
	public void load_from_file(File in_file) throws IOException {
		
		this.file = in_file;
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
		
		if(this.loaded_chapters.containsKey(chapter_num)) { //if already loaded
			return;
		}
		if(!this.table_of_contents.containsKey(chapter_num)) { //if not in table of contents
			return;
		}
		
		this.reader.seek(table_of_contents.get(chapter_num));
		
		String line;
		ArrayList<String> paras = new ArrayList<String>();
			
		while(true) {
			line = this.reader.readLine();
			if(line.charAt(0)!=1) {
				paras.add(line);
			}
			if(line.charAt(0)==1 && line.substring(1).contentEquals("EDCH")) {
				break;
			}
		}
			
		this.loaded_chapters.put(chapter_num,new Chapter(paras));
		
		if(loaded_chapters.keySet().size()>=loading_size) { //Removes chapter furthest from last loaded one if there are too many
			Integer mindist = Integer.MAX_VALUE;
			Integer minpos  = null;
			
			for(Integer pos : loaded_chapters.keySet()) {
				
				if( Math.abs(pos-chapter_num) < mindist ) {
					mindist = Math.abs(pos-chapter_num);
					minpos = pos;
				}
				
			}
			
			loaded_chapters.remove(minpos);
		}
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
	
	public Chapter get_chapter(Integer i) {
		
		if(!this.loaded_chapters.containsKey(i)) {
			try {
				this.load_chapter(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return loaded_chapters.get(i);
		
	}

	public void close() {
		// TODO Close document
		
	}
	
	
}