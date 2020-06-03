package doc_str;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TransformRenault extends Transform {

	TransformRenault(String source) {
		super(source);
		
	}
	
	
public static String Parcour_rec(Node node){
		

		NodeList NL = node.getChildNodes();
		String ch="";
		
		for(int i=0; i < NL.getLength();i++){
				
			if(Filter(NL.item(i))){
				ch+=(NL.item(i).getNodeValue());
			}
			
			if(node.hasChildNodes()){
				ch+=Parcour_rec(NL.item(i));
		
			}
			else{
				return ch;
			}
			
		}
		
			return ch;
	}


public static boolean Filter(Node node){
	
	return (node.getNodeName().matches("#text")
			&& 
		   (!node.getNodeValue().matches("(\n|\t| )*"))
	);
}

	
	

	@Override
	public Document transformer(Document document_src)throws Exception {
		
		DOMImplementation domimp = CreateDomParser.imp();
		Document document_but = domimp.createDocument(null,"Concessionnaires", null);
		Element rac_but= document_but.getDocumentElement();
		
		
		NodeList infos = document_src.getElementsByTagName("p");
		
		
		
		
		for(int i=1;i<14;i++){
			
				NodeList childs = infos.item(i).getChildNodes();
				String contenu="";
				
				for(int j=0; j<childs.getLength();j++){
					
						
			
						String ch="";
						
						Node child= childs.item(j);
						
						if(child.hasChildNodes()){
							
							ch=Parcour_rec(child);
					
							contenu+=ch.replaceAll("(\n|\t| |:)*","");
							contenu+="\n";
						}
						
						else{
							
							if(Filter(child)){
								
								contenu+=childs.item(j).getNodeValue().replaceAll("(\n|\t| |:)*","");
								contenu+="\n";
							}
							
						}
				}
				
				System.out.println(contenu);
				String[] info=contenu.split("\n");
				
				Element texte = document_but.createElement("Nom");
				rac_but.appendChild(texte);
				texte.appendChild(document_but.createTextNode(info[0]));
				
				texte = document_but.createElement("Adresse");
				rac_but.appendChild(texte);
				texte.appendChild(document_but.createTextNode(info[2]));
				
				texte = document_but.createElement("Num_t�l�phone");
				rac_but.appendChild(texte);
				texte.appendChild(document_but.createTextNode(info[4]));
				
				
			}
		
		
		return document_but;
		}
	}


