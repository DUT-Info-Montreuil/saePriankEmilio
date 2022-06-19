package jeu.modele.inventaire.objet;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class caseInventaire {
	
	private IntegerProperty numObj = new SimpleIntegerProperty();
	private ObjetInventaire obj;
	private BooleanProperty estSelectionnerProperty ;
	private int numCase;
	
	public caseInventaire(ObjetInventaire obj,int numcase) {
		this.numObj=obj.getNumProperty();
		this.obj=obj;
		this.estSelectionnerProperty=new SimpleBooleanProperty(false);
	}
	
	public void selectionnerCase() {
		boolean b;
		b=true;
		this.estSelectionnerProperty.set(b);
	}
	
	public void deSelectionner() {
		boolean b;
		b=false;
		this.estSelectionnerProperty.set(b);
	}
	
	public BooleanProperty estSelectionnerProperty(){
		return this.estSelectionnerProperty;
	}
	
	public void setObjetDeLaCase(ObjetInventaire obj) {
		this.obj=obj;
		this.numObj.setValue(obj.getNumProperty().getValue());
	}
	
	
	public ObjetInventaire getObjetDeLaCase() {
		return this.obj;
	}
	public IntegerProperty getNumObjetCase() {
		return this.numObj;	
	}
	public String toString() {
		return this.obj.toString();
	}
	public int getNumCase() {
		return numCase;
	}
}
