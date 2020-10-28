package main;

import java.io.IOException;

import text_obj.*;
public class Tester {
	
    public static void main(String[] args) {
    	
    	Document doc = new Document();
    	try {
			doc.load_from_file("library/testtext.oad");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.print("ID: ");
    	System.out.println(doc.get_id());
    	System.out.print("VR: ");
    	System.out.println(doc.get_format_ver());
    	System.out.println("Sample paragraph: ");
		System.out.println(doc.get_chapter(1).get_paragraph(0));
		System.out.print("\n");
		
		AnnotationSet ano = new AnnotationSet();
    	try {
    		ano.load_from_file("library/testano.ano");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.print("ID: ");
    	System.out.println(ano.get_id());
    	System.out.print("VR: ");
    	System.out.println(ano.get_format_ver());
    	System.out.println("Annotations: ");
		for(int i = 0; i < 2; i++) {
			AnnoChapter chap = ano.get_annochapter(i);
			for( Integer[] pos : chap.get_anno_positions()) {
				System.out.print(pos[0]);
				System.out.print(", ");
				System.out.print(pos[1]);
				System.out.print(", ");
				System.out.print(pos[2]);
				System.out.print(", ");
				System.out.print(pos[3]);
				System.out.print("\n\t");
				System.out.println(chap.get_annotation(pos));
			}
		}

    	
    }
}
