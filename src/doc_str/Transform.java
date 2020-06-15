package doc_str;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	
	
	

	
	public void generate(TransInfo infos)throws Exception{
		
		
		Document document_src = null;
		if(infos.isXML){
			document_src = parseur.parse(infos.getPaFile());
		}
		
	
		DOMImplementation domimp = parseur.getDOMImplementation();
		Document document_but = domimp.createDocument(null,infos.getRacine(), null);
		

		// if the doc is a text file, then document_src is null
		document_but = transformer(document_src, document_but);
		
		
		//*************** Print result************//
		
		DOMSource ds = new DOMSource(document_but);
		StreamResult res = new StreamResult(new File(infos.getTarget()));
		TransformerFactory transform = TransformerFactory.newInstance();
		Transformer tr = transform.newTransformer();
		
		DocumentType doctype = doctype(domimp);
		
		boolean isalone;
		
		if(doctype != null){
			
			isalone=true;
			tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
			document_but.appendChild(doctype);
		}
		else{
			
			isalone=false;
			document_but.setXmlStandalone(true);
		}
		

		tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		tr.transform(ds, res);
		
		format(infos.getTarget(),isalone);
	}

	private void format(String target, boolean isalone) {
		String line = "";
		List<String> lines = new ArrayList<String>();
		 try {
			 
	            File f1 = new File(target);
	            
	            Scanner rd = new Scanner(f1,"utf-8");
	            
	            while ( rd.hasNextLine()) {
	            	
	            	line=rd.nextLine();
	            	
	                if (line.contains("    ")){
	                    line = line.replace("    ", "\t");
	                   // System.out.println(line);
	                }
	                	lines.add(line);
	            }
	            rd.close();

	            FileWriter fw = new FileWriter(f1);
	            BufferedWriter out = new BufferedWriter(fw);
	            
	            
	            OutputStream os = new FileOutputStream(f1);
	            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "utf8"));
	            
	
	            
	            int i = 0;
	            if(!isalone){
	            	String l = lines.get(0);
	            	String [] strs = l.split("\\?><");
	            	
	            	strs[0]=strs[0]+"?>";
	            	strs[1]="<"+strs[1];
	            	
	            	lines.set(0,strs[0]);
	            	lines.add(1,strs[1]);
	            }

	            
	            while(i<lines.size()){
	                 writer.print(lines.get(i)+"\r\n");
	                 i++;
	            }
	            	writer.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}

	protected abstract DocumentType doctype(DOMImplementation domimp);

	//pour modifier un fichier, li suffit de modifier cette méthode
	protected abstract Document transformer(Document document_src, Document document_but) throws Exception;
	

}
