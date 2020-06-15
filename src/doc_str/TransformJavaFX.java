package doc_str;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TransformJavaFX extends Transform {
	
	
	TransformJavaFX(String source){
		
		super(source);
		
	}

public static boolean Filter(Node node){
		
		return (!node.getNodeName().matches("#text"));

	
	}
	
	
public static void Parcour_rec(Node node, Document doc){
		

		NodeList NL = node.getChildNodes();

		
		for(int i=0; i < NL.getLength();i++){
				
			if(Filter(NL.item(i))){
				
				
				if(NL.item(i).hasAttributes()){
					traitement(doc, NL.item(i).getAttributes());
				}
			}
			
			if(node.hasChildNodes()){
				Parcour_rec(NL.item(i), doc);
		
			}
			else{
				
			}
			
		}
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void traitement(Document doc, NamedNodeMap NM) {
	
		
		for(int e=0; e < NM.getLength(); e++){
			
			Attr atr = (Attr) NM.item(e);
			
			Element texte = doc.createElement("texte");
			texte.setAttribute(atr.getName(), "x");
			texte.appendChild(doc.createTextNode(atr.getValue()));
			
			Element rac = doc.getDocumentElement();
			rac.appendChild(texte);
			

			
			
		}
	
	}




	@Override
	public Document transformer(Document document_src, Document document_but)throws Exception {

		String x = "x";
		Element rac_but= document_but.getDocumentElement();
		rac_but.setAttribute("xmlns:fx", "http://javafx.com/fxml");
		
		Element rac = document_src.getDocumentElement();
		
		NodeList infos = rac.getChildNodes();
		
		traitement(document_but, rac.getAttributes());
		Parcour_rec(rac, document_but);
		
		return document_but;
	}

	@Override
	protected DocumentType doctype(DOMImplementation domimp) {
		// TODO Auto-generated method stub
		return null;
	}

}
