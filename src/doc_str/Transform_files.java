package doc_str;

import java.util.ArrayList;

public class Transform_files {
	
	
	ArrayList<String> files;

	
	public Transform_files(ArrayList<String> files){
		
		this.files=files;
		
	}
	
	public void transform_files(){
		
		Transform T;
		
		TransInfo infos;
		
		for(String file : files){
		
			int index = file.lastIndexOf("\\");
			String fileName= file.substring(index+1, file.length());

			
			try {
				
				
			switch(fileName){
					
				case "M457.xml":
					

					T = new TransformM457_674(file);
					infos = new TransInfo(fileName,file,"sortie2.xml","TEI_S");
					T.generate(infos);
					
					break;
				
				case "M674.xml":
					
					T = new TransformM457_674(file);
					infos = new TransInfo(fileName,file,"sortie1.xml","TEI_S");
					T.generate(infos);
					
					break;
				
				case "poeme.txt":
					
				   T = new TransformPoeme(file); //probleme encodage
				   infos = new TransInfo(fileName, file,"neruda.xml", "poema", false);
				   T.generate(infos);
					
					break;
				
				case "fiches.txt":
					
					T = new TransformFiches(file,TransformFiches.FICHE_1); 
					infos = new TransInfo(fileName,file,"fiche1.xml","FICHES",false);
					T.generate(infos);
					
					
					T = new TransformFiches(file,TransformFiches.FICHE_2);
					infos = new TransInfo(fileName,file,"fiche2.xml","FICHES",false);
					T.generate(infos);
					break;
				
				case "renault.html":
					
					T = new TransformRenault(file);
					infos = new TransInfo(fileName,file,"renault.xml","Concessionaires");
					T.generate(infos);
					
					break;
				
				case "boitedialog.fxml":
					
     				T = new TransformJavaFX(file);
					infos = new TransInfo(fileName,file,"javafx.xml","Racine");
					T.generate(infos);
					
					
					break;
				
			}
				
				
				System.out.println(fileName+" Done.");
				
				
			} catch (Exception e) {
	
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}
