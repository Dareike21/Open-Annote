package main;

import java.io.IOException;

import text_obj.*;
public class Tester {
	
    public static void test() {
    	
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
    
    public static String lorem_ipsum = "<html><div id=\"lipsum\">\r\n" + 
    		"<p>\r\n" + 
    		"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eu tempor tellus. Donec commodo felis quis nunc molestie gravida. Integer et luctus nisi. Donec a nunc sem. Vestibulum suscipit venenatis finibus. Praesent sagittis, augue eu commodo consequat, diam libero ultricies leo, nec vehicula arcu tellus in velit. Morbi id tellus non nunc varius porttitor ac quis elit. Proin lorem nibh, pharetra facilisis gravida et, sodales ac turpis. Nam facilisis mauris vel urna venenatis vehicula. Nulla gravida, tortor vitae tincidunt vulputate, nisi lacus commodo purus, non vestibulum ipsum nunc sit amet augue. Proin efficitur elit at ornare aliquet. Ut venenatis, metus a lacinia tincidunt, orci nulla euismod urna, non interdum magna lorem nec felis. Cras suscipit laoreet nisi nec interdum.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Sed pretium nulla nec libero venenatis dignissim. Donec quis pulvinar eros, vitae scelerisque justo. Fusce odio velit, elementum eu ullamcorper at, hendrerit non risus. Donec dapibus lobortis suscipit. Nunc et lorem sed quam vulputate fermentum. Suspendisse et elit quam. Praesent lorem est, fringilla sit amet vehicula et, mattis non erat. Praesent aliquet dui felis, sed fringilla eros pharetra a. Quisque commodo nibh vitae risus ullamcorper tempor. Maecenas a enim vel nibh tempor bibendum.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Suspendisse viverra id risus vitae egestas. Vestibulum non tempus ex, sed commodo purus. Sed nec congue enim, vitae blandit felis. Curabitur enim ipsum, aliquet sit amet lorem fringilla, tincidunt dapibus mi. Aenean nisl magna, rhoncus eget ipsum sed, ultrices tempus lectus. Nulla id nulla bibendum, facilisis massa et, cursus lectus. Aenean vitae dignissim odio.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Praesent interdum suscipit eros accumsan rhoncus. Donec bibendum mauris ac pulvinar sagittis. Curabitur lectus libero, ultrices sed elit sed, tincidunt pharetra risus. Ut fringilla porta quam ut varius. Nam rutrum lobortis semper. In accumsan orci et auctor efficitur. Proin a sapien volutpat, tincidunt est eget, iaculis nulla. Nulla eget velit ac ligula tincidunt congue. Nulla cursus nisi semper mollis tristique. Aliquam eros libero, pharetra id pharetra eget, dictum at nulla. In sit amet vestibulum augue, sit amet viverra dui. Donec sapien sapien, ultrices et maximus ornare, tincidunt quis dui. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis dui enim, ornare eget diam cursus, tempus luctus metus.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Phasellus interdum sodales odio, elementum vestibulum nunc pharetra ac. Fusce vel lacus eget lectus tempor pharetra. Cras laoreet condimentum ex id posuere. Integer suscipit dapibus arcu, eget condimentum magna vestibulum sed. Aliquam lacinia lectus id mauris blandit feugiat. Suspendisse potenti. Nam facilisis neque vitae nisi tincidunt, at imperdiet mi lacinia. Donec mauris nisl, mollis a pulvinar at, facilisis nec leo. Duis a elementum eros. Donec eu nulla quis leo vestibulum pellentesque vel eget sem. Etiam tempor sollicitudin leo, at fringilla neque sodales vel. Sed mauris turpis, ullamcorper id urna eu, feugiat iaculis velit. Quisque et sem ullamcorper, porta neque sed, molestie enim.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Praesent posuere nulla at tortor tincidunt, cursus tempor arcu vestibulum. Mauris in dignissim sem. Sed sed ornare metus. Vivamus fringilla, urna consequat convallis imperdiet, neque velit aliquam ex, eu auctor tellus nisi vitae metus. Donec tincidunt turpis at orci semper, sed semper sem molestie. In hac habitasse platea dictumst. Etiam commodo tincidunt massa, vel dictum ipsum eleifend eget. Nulla accumsan lectus at augue laoreet, elementum mollis orci mattis. Suspendisse risus purus, egestas sit amet semper a, efficitur et risus. Praesent gravida vitae sem eu volutpat. Aenean fringilla sed mauris tincidunt efficitur. Suspendisse potenti. Phasellus condimentum et elit nec pellentesque. Curabitur dignissim libero sem, id rhoncus metus facilisis at. Phasellus id consectetur velit.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Mauris velit eros, hendrerit et enim quis, dictum posuere lacus. Phasellus lobortis, nunc ut vehicula ultrices, risus arcu accumsan mauris, id dapibus nunc lectus eu quam. Vivamus sed egestas magna. Nunc condimentum libero quis porta gravida. Cras pulvinar pulvinar ipsum, et rhoncus mauris feugiat a. Morbi nisi nisi, venenatis id aliquet vel, ultricies quis nibh. Cras at blandit risus. Integer sit amet feugiat tellus. In tristique fringilla congue. Quisque interdum mattis viverra.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Donec sit amet laoreet ligula. Nunc sed orci sem. Curabitur viverra tellus mi, ac sodales augue sollicitudin at. Aenean tristique dui id lacus maximus, eu semper tellus imperdiet. Vivamus felis felis, dignissim et dolor eget, pharetra pharetra orci. Quisque lacinia, turpis sed vehicula faucibus, orci magna malesuada nulla, at porttitor nibh diam at erat. Nullam euismod dictum tempor. Suspendisse a odio fermentum, tincidunt massa quis, scelerisque ex. In lacinia libero at lacinia semper.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Integer et turpis tempor, accumsan tortor ut, vestibulum turpis. Sed non dolor id enim ornare gravida a a odio. Sed pellentesque fermentum lorem ut sollicitudin. Nunc purus justo, fringilla eu ex nec, viverra ornare mi. Maecenas commodo porta enim, eu mattis erat ullamcorper sit amet. Mauris orci arcu, pellentesque vitae varius in, aliquet ut leo. Nulla tincidunt augue urna, lacinia varius sapien auctor sit amet. Ut aliquet sit amet libero eu vulputate. Maecenas turpis magna, luctus nec tempor nec, aliquet eget libero. Nulla pellentesque commodo arcu, ut luctus libero. Donec sit amet ipsum sed ante suscipit euismod. Nulla facilisi.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Duis molestie metus pretium tortor mollis, non elementum ligula aliquet. Aenean sed libero sed lacus mattis euismod quis non augue. In eget lacus vitae lectus tempus efficitur at vel risus. Pellentesque semper, lacus in pharetra elementum, nibh est eleifend orci, ac gravida felis metus non libero. Fusce nec metus erat. Vestibulum posuere turpis at dignissim volutpat. Sed placerat congue purus, eu cursus massa.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Etiam a lorem id lacus bibendum tempor. Fusce sit amet elit in tellus cursus tempus. Aenean condimentum ante justo, id condimentum urna congue condimentum. Vivamus semper elit et lacus euismod porttitor. Nunc vitae risus lacinia, rutrum enim eu, malesuada urna. Sed malesuada suscipit urna, vel viverra est semper a. Praesent id nisl a ipsum volutpat euismod id dictum neque. Aliquam finibus volutpat leo ac malesuada. Sed pulvinar volutpat purus ut porta. Vestibulum euismod odio non tellus eleifend accumsan. Donec pretium imperdiet metus, a malesuada erat. Integer ornare eu odio et auctor. Phasellus posuere purus quis neque eleifend posuere nec ut dui. Nunc eu urna lacinia, sollicitudin tortor ultrices, mattis nunc.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Fusce dictum aliquam ante in pretium. Mauris consequat neque metus, id condimentum metus blandit ut. Integer ac pharetra libero. Aenean interdum sem ullamcorper molestie semper. Aenean posuere elit erat, non consectetur purus maximus sed. Pellentesque nec convallis ipsum. Etiam pellentesque, tortor non aliquam vulputate, nisl justo tincidunt velit, non efficitur ante ipsum ac ante. Donec pharetra facilisis nisl. Vivamus eu nisi in urna maximus vulputate. Quisque sollicitudin tincidunt mauris porta sollicitudin. Morbi accumsan arcu ut magna viverra ornare. Duis interdum vulputate est, vel dapibus libero porttitor et. Vivamus iaculis nibh eget molestie posuere. Fusce in consequat mi. Maecenas ut posuere dui. Nunc sed egestas velit, vitae volutpat nisi.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Donec hendrerit ex pulvinar pretium scelerisque. Praesent porttitor rhoncus iaculis. Nunc ut turpis ac nunc gravida malesuada. Quisque eros dui, ultrices ut nulla vitae, consectetur dignissim quam. Pellentesque lacinia mollis egestas. Donec lectus leo, aliquet nec ullamcorper vel, rhoncus eu ipsum. Maecenas eu iaculis massa.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Pellentesque porta finibus porta. Aliquam erat volutpat. Morbi nec tincidunt nisi, in laoreet ante. Aenean vel enim sem. Cras quis sagittis justo. Vestibulum eu tempor felis. Donec eget felis arcu. Duis et massa dapibus, pulvinar leo vel, varius tortor. Morbi vel lectus in sapien porttitor mollis. Fusce cursus dapibus arcu, quis condimentum orci suscipit non.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Etiam pharetra sodales pretium. Vivamus porttitor risus vel nunc pulvinar, et accumsan metus laoreet. Phasellus ultrices id nulla a molestie. Duis purus dui, pretium vitae sodales sed, condimentum nec augue. Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas malesuada ut nisi sit amet vestibulum. Phasellus quis aliquam leo. Etiam non gravida nisl. Donec tristique, urna nec luctus porta, ipsum magna congue dolor, viverra luctus purus turpis vitae purus. Nulla ipsum arcu, egestas ut sem sit amet, laoreet maximus lectus. Proin iaculis congue ultrices. Etiam hendrerit lectus eu neque scelerisque, non pellentesque massa venenatis. Aliquam vel consectetur ipsum, vitae laoreet lectus. Etiam rutrum, dui interdum faucibus viverra, justo arcu aliquam leo, quis hendrerit erat mauris eu magna.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Vivamus eleifend sodales ligula, eget consequat ex consectetur ut. Vestibulum quis tortor at risus tincidunt consequat. Nam blandit felis in tincidunt bibendum. Maecenas dictum elementum mi, vitae pharetra mauris consectetur in. In malesuada lorem quis nisi tincidunt, vitae cursus risus elementum. Curabitur venenatis aliquam gravida. Suspendisse bibendum porta nisl, quis dapibus nisi efficitur non. Ut iaculis dui eget ligula gravida, ut ultrices quam sagittis. Nam fringilla sagittis pellentesque. Vestibulum porttitor convallis pellentesque. Donec feugiat purus felis, id faucibus lorem sagittis id. Nulla blandit porttitor lacus, at vehicula enim posuere a. Nunc pulvinar consectetur vestibulum.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Sed ac pulvinar nisi. Cras tincidunt interdum nibh nec suscipit. Praesent ornare orci ut ligula sollicitudin, malesuada suscipit purus dictum. Cras pellentesque, felis et lobortis lacinia, turpis arcu cursus nibh, quis varius dui nibh id libero. Donec vestibulum congue pharetra. Phasellus sem ex, pharetra a lectus sit amet, fermentum consequat ex. Curabitur faucibus consectetur erat, nec dapibus urna consectetur non. Aenean gravida tempor viverra.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Maecenas in neque sem. Proin dolor tellus, congue id augue eu, tristique mollis velit. Fusce porttitor eros ut leo tempor gravida. In accumsan lorem et nunc suscipit, sit amet convallis lacus tincidunt. Donec sed mattis metus. Etiam viverra eu tellus at venenatis. Maecenas vulputate egestas sem sit amet aliquet. Mauris venenatis purus vitae ligula fermentum tristique. Praesent in sem ac urna rhoncus vehicula. Fusce eu dapibus justo. Etiam convallis rhoncus sem, id sodales lacus auctor in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas hendrerit efficitur diam in viverra. Vestibulum vestibulum lacus eget mi consectetur, ac euismod mauris scelerisque. Phasellus gravida metus nisi, quis congue tellus finibus at. Fusce pellentesque justo eu diam gravida, quis vestibulum massa vehicula.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Suspendisse laoreet, diam quis sollicitudin sodales, eros neque lacinia leo, at lacinia metus orci id est. Nam interdum ullamcorper vestibulum. Suspendisse accumsan sapien lectus, vitae vestibulum sapien vehicula ac. In imperdiet, nulla vitae vestibulum interdum, leo magna rhoncus neque, quis blandit odio felis vitae tortor. Quisque augue purus, vulputate elementum nisi id, condimentum placerat metus. Sed in aliquam tortor. Maecenas id dui vitae est tempus ornare at ut neque. Maecenas et augue at dui imperdiet egestas. Nam vitae erat purus. Sed facilisis in purus sit amet efficitur. Nam et dui nec mauris ornare elementum ac at urna. Aenean id mattis eros, at interdum tellus. Donec vitae efficitur nisi. Sed laoreet at est at molestie. Donec vel venenatis ante, ut vulputate libero.\r\n" + 
    		"</p>\r\n" + 
    		"<p>\r\n" + 
    		"<br>Maecenas enim leo, malesuada at sem vel, facilisis pharetra est. Ut tristique purus sed nibh congue hendrerit. Nulla et enim sodales, malesuada dolor eu, faucibus ligula. Maecenas sollicitudin tincidunt arcu vel cursus. Aenean est ipsum, consectetur vitae lectus ac, blandit eleifend nibh. Ut nec nunc imperdiet, egestas erat eu, ultricies nunc. Maecenas sit amet libero vitae sem vestibulum cursus aliquet ut velit.\r\n" + 
    		"</p></div></html>";
}