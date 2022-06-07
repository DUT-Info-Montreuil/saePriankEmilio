package jeu.modele.inventaire.objet;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ObjetInventaire {

//	0 epeeBois
//	1 epeePierre
//	2 epeeMetal
//	3 hacheBois
//	4 hachePierre
//	5 hacheMetal
//	6 piocheBois
//	7 piochePierre
//	8 piocheMetal
//	9 bandage
//	10 kitDeSoin
//	11 carrerVide
	private String typeobjet;
	private IntegerProperty numObjetProperty;
	
	public ObjetInventaire(String typeobjet,int numObjet) {
		this.typeobjet=typeobjet;
		numObjetProperty=new SimpleIntegerProperty(numObjet);
	}

	public String getTypeObjet() {
		return typeobjet;
	}

	public String toString() {
		return this.typeobjet;
	}
	
	public ObjetInventaire getObjetInventaire() {
		return this;	
	}
	
	public IntegerProperty getNumProperty() {
		return this.numObjetProperty;
	}
	
}
