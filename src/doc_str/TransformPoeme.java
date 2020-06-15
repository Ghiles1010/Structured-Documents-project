package doc_str;

import java.io.File;
import java.util.Scanner;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TransformPoeme extends Transform{

	String source;
	
	TransformPoeme(String source) {
		super(source);
		this.source=source;
		
	}



	@Override
	public Document transformer(Document document_src, Document document_but)throws Exception {
		
		
		
		Element rac_but= document_but.getDocumentElement();
		
		File myObj = new File(source);
	    Scanner myReader = new Scanner(myObj,"utf-8");
	    
	    String data="";

	    //first line
	    data = myReader.nextLine();
		Element titulo = document_but.createElement("titulo");
		titulo.appendChild(document_but.createTextNode(data));
		rac_but.appendChild(titulo);
		
		Element estrofa = null, verso;


	    
	    while (myReader.hasNextLine()) {
	    	
	    	data = myReader.nextLine();
	    	
	    	if(data.length()!=0){
	    		
	    		estrofa=document_but.createElement("estrofa");
	    		rac_but.appendChild(estrofa);
	    	}
	    	
	    	while(data.length()!=0 && myReader.hasNextLine()){
	    	
	    		verso = document_but.createElement("verso");
	    		estrofa.appendChild(verso);
	    		verso.appendChild(document_but.createTextNode(data));
	    		
	    		
	    		data=myReader.nextLine();
	    	}
	    	
	    }	      
		myReader.close();
		

		
	
		 
		return document_but;
	}



	@Override
	protected DocumentType doctype(DOMImplementation domimp) {
		return domimp.createDocumentType("doctype", "poema SYSTEM", "neruda.dtd");
	}
}
