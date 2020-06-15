package doc_str;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class TransformFiches extends Transform {

	public final static int FICHE_1=1;
	public final static int FICHE_2=2;
	
	String source;
	int mode;
	
	TransformFiches(String source, int fiche) {
		super(source);
		this.source = source;
		this.mode=fiche;
	}
	

	private List<String> traiter_ligne(String ligne){
		
		List<String> infos = new ArrayList<String>(Arrays.asList(ligne.split("\t+|:")));
		
		for(int i=0;i<infos.size();i++){
			
			if(infos.get(i).matches("")){
				infos.remove(i);
			}

		}
		
		
		
		
		return infos;
	}
	
	
	public Document fiche(Scanner myReader, Document document_but, String data, int id ){
		
		
		Element rac_but= document_but.getDocumentElement();

		
		Element FICHE = document_but.createElement("FICHE");
		FICHE.setAttribute("id", Integer.toString(id));

	    rac_but.appendChild(FICHE);
	    
	    Element BE = document_but.createElement("BE");
	    BE.appendChild(document_but.createTextNode(data.split("\t")[0]));
	    FICHE.appendChild(BE);
	    
	    Element TY = document_but.createElement("TY");
	    TY.appendChild(document_but.createTextNode("TY : " + myReader.nextLine().split("\t")[0]));
	    FICHE.appendChild(TY);
	    

	    
	    String DO_str = myReader.nextLine().split("\t")[0];
	    String SD_str = myReader.nextLine().split("\t")[0];
	    

	    
	    if(mode==FICHE_2){
	    	Element DO = document_but.createElement("DO");
    		DO.appendChild(document_but.createTextNode("DO : "+DO_str));
	    
	    
    		Element SD = document_but.createElement("SD");
    		SD.appendChild(document_but.createTextNode("SD : "+SD_str));
	    
	    
    		FICHE.appendChild(DO);
    		FICHE.appendChild(SD);
	    }
	    
	    
	    Element AU = document_but.createElement("AU");
	    AU.appendChild(document_but.createTextNode("AU : "+myReader.nextLine()));
	    FICHE.appendChild(AU);
	    
	    
	    data=myReader.nextLine();
	    
	    while(!data.matches("")){
	    
	    	Element Langue = document_but.createElement("Langue");
	    	Langue.setAttribute("id", data.substring(0,2));
	    	
	    	 
	    	
	    	FICHE.appendChild(Langue);
	    	
	    	
	    	
	    	if(mode==FICHE_1){
	    		Element DO = document_but.createElement("DO");
	    		DO.appendChild(document_but.createTextNode("DO : "+DO_str));
		    
		    
	    		Element SD = document_but.createElement("SD");
	    		SD.appendChild(document_but.createTextNode("SD : "+SD_str));
		    
		    
	    		Langue.appendChild(DO);
	    		Langue.appendChild(SD);
		    
	    	}
	    
	    
	    	data=myReader.nextLine();
	    	List<String> infos ;
	    	
	    	
	    	while (!data.matches("FR") && !data.matches("")) {
	    		
	    		infos=traiter_ligne(data);
	    		
	    		String tag=infos.get(infos.size()-1);

	    		tag=tag.replace("\t", "");
    			tag=tag.replace("\n", "");
    			tag=tag.replace(" ", "");
	    		
	    		if(tag.startsWith("RF")){

	    			

	    			while (!data.matches("FR") && !data.matches("") ) {
	    				
	    				String tags="";
	    				String txt = "";
	    				int stop = 0;
	    				
	    				for(int i=infos.size()-1; i>=0; i--){
	    					
	    					if(infos.get(i).contains("RF")){
	    						i--;
	    					}
	    					
	    					if(infos.get(i).matches(".?[A-Z]{2}.?")){
	    						tags+=infos.get(i)+": ";
	    					}
	    					else{
	    						stop = i;
	    						break;
	    					}
	    				}
	    				txt = infos.get(0);
	    				
	    				for(int j = 1; j <= stop ; j++){
	    					
	    					txt = txt + " : " +infos.get(j);
	    				}
	    				
	    			
	    				
	    				Element text = document_but.createElement(tag);
	    				String s="RF | "+tags+txt;
		    		    text.appendChild(document_but.createTextNode(s.replace("  ", " ")));
		    		    Langue.appendChild(text);
		    		    data=myReader.nextLine();
		    		    
		    		    infos=traiter_ligne(data);
	    				
	    			}

	    		}
	    	
	    	
	    		
	    		else{
	
	    			tag=tag.substring(0,2);

	    			
	    			Element text = document_but.createElement(tag);
	    		    text.appendChild(document_but.createTextNode(tag+" : "+infos.get(0)));
	    			Langue.appendChild(text);
	    			

	    
	    			data=myReader.nextLine();
	    			
	    		}
	    		
	    	
	    		
	    	
	    	}
	    	
	    }
	    return document_but;
		
	}
	
	
	
	@Override
	public Document transformer(Document document_src, Document document_but)throws Exception {
		
		
		int fiche_id=1;
		

		
		
		File myObj = new File(source);
		Scanner myReader = new Scanner(myObj,"utf8");
	    
	    String data=myReader.nextLine();
	    
	    int id=1;
	    
	    while(myReader.hasNextLine()){
		

	    	fiche(myReader, document_but, data , id++);
	    	
	    	
	  
	    	while(myReader.hasNextLine()){
	    	
	    		data=myReader.nextLine();
	    		
	    		if(!data.matches("\t* *")){
	    			break;
	    		}
	    	}
	    
	    }
	        
		myReader.close();
		

		
	
		 
		return document_but;
		
		
	}










	@Override
	protected DocumentType doctype(DOMImplementation domimp) {
		return null;
	}
	
	

}
