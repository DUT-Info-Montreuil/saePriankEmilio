package jeu.modele.resource;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Resource {
	
	private IntegerProperty nbResourceProperty;	
	
	public Resource() {	
		nbResourceProperty = new SimpleIntegerProperty(0);
	}

	public  void ajouterResource(){
		int c;
		c=this.nbResourceProperty.getValue()+1;
		if (this.nbResourceProperty.getValue() < 99)
		this.nbResourceProperty.setValue(c);
	}
	
	public IntegerProperty getNbResourceProperty() {
		return this.nbResourceProperty;	
	}
	
	public  void EnleverResource(int nbR){
		int c;
		c=this.nbResourceProperty.getValue()-nbR;
		this.nbResourceProperty.setValue(c);
	}
	
	


}
