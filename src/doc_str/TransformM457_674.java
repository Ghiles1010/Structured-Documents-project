package doc_str;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TransformM457_674 extends Transform{

	String noeud;

	TransformM457_674(String source) {
		super(source);
		
		int index = source.lastIndexOf("\\");
		this.noeud= source.substring(index+1, source.length());
		
		
	}
	
	
	

	
	
	@Override
	public Document transformer(Document document_src, Document document_but) throws Exception {
		
		
		Element rac_but= document_but.getDocumentElement();
		
		
		
		Element m457 = document_but.createElement(noeud);
		rac_but.appendChild(m457);
		
		
		
		NodeList infos = document_src.getElementsByTagName("p");
		
		
		for(int p=0; p < infos.getLength(); p++){
			
			NodeList childs=infos.item(p).getChildNodes();
			
			
			for(int c=0; c < childs.getLength(); c++){
				
				Node child=childs.item(c);
				
				if(Filter(child)){
					
					
					Element texte = document_but.createElement("texte");
					m457.appendChild(texte);
					texte.appendChild(document_but.createTextNode(child.getNodeValue().replace("\n", "")));
				}
				
				
			}
			
			
			
		}
		
		
		return document_but;
	}






	@Override
	protected DocumentType doctype(DOMImplementation domimp) {
		// TODO Auto-generated method stub
		return domimp.createDocumentType("doctype", "TEI_S SYSTEM", "dom.dtd");
	}














}
