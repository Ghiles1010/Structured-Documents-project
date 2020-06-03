package doc_str;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public abstract class Transform {
	
	String xmlFile;

	
	Transform(String source){
		this.xmlFile=source;
	}
	
	
	
	

	
	public void generate()throws Exception{
		
		
		DocumentBuilder parseur =CreateDomParser.parseur();
		Document document_src = parseur.parse(xmlFile);
		Document document_but = transformer(document_src);
		//***************Impression du résultat************//
		DOMSource ds = new DOMSource(document_but);
		StreamResult res = new StreamResult(new File("renault.xml"));
		TransformerFactory transform = TransformerFactory.newInstance();
		Transformer tr = transform.newTransformer();
		document_but.setXmlStandalone(true);
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.transform(ds, res);
		
		
	}

	public abstract Document transformer(Document document_src) throws Exception;
	
	

	
	
	

}
