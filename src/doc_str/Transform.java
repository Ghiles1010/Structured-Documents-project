package doc_str;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;

public abstract class Transform {
	
	String xmlFile;
	DocumentBuilder parseur;
	DocumentBuilderFactory factory;

	
	Transform(String source){
		this.xmlFile=source;
		
		try {
			factory = DocumentBuilderFactory.newInstance();		
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false); 
			this.parseur = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		
			e.printStackTrace();
		}
	}
	
	public static boolean Filter(Node node){
		
		return (node.getNodeName().matches("#text")
				&& 
			   (!node.getNodeValue().matches("(\n|\t| )*"))
		);
	}
	
	
	
	

	
	public void generate()throws Exception{
		
		
		
		Document document_src = parseur.parse(xmlFile);
		DOMImplementation domimp = parseur.getDOMImplementation();
		
//		DocumentType dtd = domimp.createDocumentType("TEI_S",null,"dom.dtd");
		
		Document document_but = domimp.createDocument(null,"TEI_S", null);
		document_but = transformer(document_src, document_but);
		//*************** Print result************//
		DOMSource ds = new DOMSource(document_but);
		StreamResult res = new StreamResult(new File("sortie1.xml"));
		TransformerFactory transform = TransformerFactory.newInstance();
		Transformer tr = transform.newTransformer();
		document_but.setXmlStandalone(true);
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.transform(ds, res);
		
		
	}

	public abstract Document transformer(Document document_src, Document document_but) throws Exception;
	
	

	
	
	

}
