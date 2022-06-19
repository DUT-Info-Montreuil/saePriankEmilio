package jeu.modele.inventaire;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import jeu.modele.inventaire.objet.ObjetVide;
import jeu.modele.inventaire.objet.caseInventaire;
import jeu.modele.inventaire.objet.arme.EpeeBois;
import jeu.modele.inventaire.objet.arme.EpeeMetal;
import jeu.modele.inventaire.objet.arme.EpeePierre;
import jeu.modele.inventaire.objet.arme.HacheBois;
import jeu.modele.inventaire.objet.arme.HacheMetal;
import jeu.modele.inventaire.objet.arme.HachePierre;
import jeu.modele.inventaire.objet.arme.PiocheBois;
import jeu.modele.inventaire.objet.arme.PiocheMetal;
import jeu.modele.inventaire.objet.arme.PiochePierre;
import jeu.modele.inventaire.objet.arme.Pistolet;
import jeu.modele.inventaire.objet.objetSoin.Bandage;
import jeu.modele.inventaire.objet.objetSoin.KitDeSoin;

public class Inventaire {

	private caseInventaire case1= new caseInventaire(new ObjetVide(),1);
	private caseInventaire case2=new caseInventaire(new ObjetVide(),2);
	private caseInventaire case3=new caseInventaire(new ObjetVide(),3);
	private caseInventaire case4=new caseInventaire(new ObjetVide(),4);
	private caseInventaire case5=new caseInventaire(new ObjetVide(),5);
	private caseInventaire case6=new caseInventaire(new ObjetVide(),6);
	private ArrayList<caseInventaire> inventaire;

	public Inventaire() {
		inventaire =new ArrayList<caseInventaire>();
		inventaire.add(case1);
		inventaire.add(case2);
		inventaire.add(case3);
		inventaire.add(case4);
		inventaire.add(case5);
		inventaire.add(case6);
	}

	public void ajouterDansLinventaire(caseInventaire obj) {
		this.inventaire.add(obj);
	}

	public void SetObjetCase(int numCase,int NumObjet){
		switch (NumObjet) {
		case 0:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new EpeeBois());			
			break;
		case 1:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new EpeePierre());			
			break;
		case 2:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new EpeeMetal());		
			break;
		case 3:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new HacheBois());				
			break;
		case 4:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new HachePierre());				
			break;
		case 5:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new HacheMetal());				
			break;
		case 6:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new PiocheBois());				
			break;
		case 7:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new PiochePierre());					
			break;
		case 8:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new PiocheMetal());		
			break;
		case 9:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new Bandage());	
			break;				
		case 10:
			this.inventaire.get(numCase-1).setObjetDeLaCase(new KitDeSoin());	
			break;
		case 11 : 
			this.inventaire.get(numCase-1).setObjetDeLaCase(new Pistolet());	
			break;
		}

	}


	public IntegerProperty getCaseObjetCaseProperty(int i) {
		return this.inventaire.get(i-1).getNumObjetCase();
	}
	public ArrayList<caseInventaire> getInventaire() {
		return this.inventaire;
	}
	public int getObjetCase(int i){
		return this.inventaire.get(i-1).getNumObjetCase().get();	
	}
	public IntegerProperty getObjetCaseProperty(int i){
		return this.inventaire.get(i-1).getNumObjetCase();	
	}

}
