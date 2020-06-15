package doc_str;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Main {
	
	private static ArrayList<String> files = new ArrayList();
	
	
	public static void parcour(String path){
		
		File repertoire = new File(path);
		String liste[] = repertoire.list();      
		 
        if (liste != null) {         
            for (int i = 0; i < liste.length; i++) {
            	
            	
                String new_path=path+"\\"+liste[i];
                File fils = new File(new_path);

                
                if(fils.isDirectory()){
                	
                	parcour(new_path);
                }
                else{
                	
                	files.add(new_path);
                }
    
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
		
		
	}
	
	

	public static void main(String[] args) throws Exception {
		
		parcour(args[0]);
		Transform_files T = new Transform_files(files);
		T.transform_files();
		
	}
	


}
