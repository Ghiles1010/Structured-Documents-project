package doc_str;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;

public class CreateDomParser {
	
	static DocumentBuilder parseur;
	
	public static DocumentBuilder parseur() throws ParserConfigurationException{
		
		parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return parseur;
		
		
	}
	
	public static DOMImplementation imp() throws ParserConfigurationException{
		
		DOMImplementation imp =parseur.getDOMImplementation();
		return imp;
		
	}

}
