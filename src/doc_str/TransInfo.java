package doc_str;

public class TransInfo {
	
	//sert à regrouper les informations de transformations
	
	private String nameFile, pathFile, target, racine ;
	boolean isXML; //precise si c'est un .xml or .txt
	
	
	public TransInfo(String nameFile, String pathFile, String target, String racine) {

		this.nameFile = nameFile;
		this.pathFile = pathFile;
		this.target = target;
		this.racine = racine;
		this.isXML=true; //false by default
		
		
	}
	
	
	
	
	
	public TransInfo(String nameFile, String pathFile, String target,String racine, boolean isXML) {

		this.nameFile = nameFile;
		this.pathFile = pathFile;
		this.target = target;
		this.racine = racine;
		this.isXML = isXML;
		
		
	}

	
	
	
	
	
	
	
	
	
	             ///////////////////// Getters and Setter ////////////////////////////
	
	public String getNameFile() {
		return nameFile;
	}


	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}


	public String getPaFile() {
		return pathFile;
	}


	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public String getRacine() {
		return racine;
	}


	public void setRacine(String racine) {
		this.racine = racine;
	}
	
	

	
	

}
