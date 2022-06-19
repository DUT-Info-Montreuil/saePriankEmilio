package jeu.vue;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import jeu.modele.Environnement;
import jeu.modele.Joueur;


public class VueInventaire {
		
	private Image[] tabImage  = {new Image("jeu/image/utilitaires/epeeBois.png"),new Image("jeu/image/utilitaires/epeePierre.png"),
			new Image("jeu/image/utilitaires/epeeMetal.png"),new Image("jeu/image/utilitaires/hacheBois.png"),
			new Image("jeu/image/utilitaires/hachePierre.png"),new Image("jeu/image/utilitaires/hacheMetal.png"),
			new Image("jeu/image/utilitaires/piocheBois.png"),new Image("jeu/image/utilitaires/piochePierre.png"),
			new Image("jeu/image/utilitaires/piocheMetal.png"),new Image("jeu/image/utilitaires/bandage.png"),
			new Image("jeu/image/utilitaires/kitDeSoin.png"),new Image("jeu/image/utilitaires/pistolet.png"),
			new Image("jeu/image/utilitaires/vide.png"),new Image("jeu/image/utilitaires/bois.png"),
			new Image("jeu/image/utilitaires/pierre.png"),new Image("jeu/image/utilitaires/metal.png"),
			new Image("jeu/image/utilitaires/pistoletInverser.png")};	
	private Joueur joueur;
	private Environnement env;
	private Label labelBois,labelPierre,labelMetal,labelNbDeBandage,labelNbDeKitDeSoin;
	private ImageView case1,case2,case3,case4,case5,case6;
	private ImageView imgObjetDansLesMains;
	
	public VueInventaire(Environnement env, HBox boxInv, ArrayList<Label> labels, ArrayList<ImageView> imagesCase, ImageView imgObjetDansLesMains) {
		this.env=env;
		this.joueur=env.getJoueur();
		this.labelBois=labels.get(0);
		this.labelPierre=labels.get(1);
		this.labelMetal=labels.get(2);
		this.labelNbDeBandage=labels.get(3);
		this.labelNbDeKitDeSoin=labels.get(4);
		this.case1=imagesCase.get(0);
		this.case2=imagesCase.get(1);
		this.case3=imagesCase.get(2);
		this.case4=imagesCase.get(3);
		this.case5=imagesCase.get(4);
		this.case6=imagesCase.get(5);
		afficherInventaireObjet();
		this.imgObjetDansLesMains=imgObjetDansLesMains;
		this.imgObjetDansLesMains.translateXProperty().bind(joueur.xProperty().add(40));
		this.imgObjetDansLesMains.translateYProperty().bind(joueur.yProperty().add(5));
		
	}
	
	public void afficherInventaireObjet() {		
		env.getNbResourceProperty("bois").addListener((obse,old,nouv)-> this.labelBois.setText(nouv.toString()));
		env.getNbResourceProperty("pierre").addListener((obse,old,nouv)-> this.labelPierre.setText(nouv.toString()));
		env.getNbResourceProperty("metal").addListener((obse,old,nouv)-> this.labelMetal.setText(nouv.toString()));		
		
		joueur.getNbBandageProperty().addListener((obse,old,nouv)-> this.labelNbDeBandage.setText(nouv.toString()));
		joueur.getNbKitdeSoinProperty().addListener((obse,old,nouv)-> this.labelNbDeKitDeSoin.setText(nouv.toString()));
		//listenner qui sert a changer l'objet équiper par le joueur
		joueur.getObjetEquiperProperty().addListener((obse,old,nouv)-> equiperObjetDansLesMains(nouv.intValue()));		
		joueur.getDirectionProperty().addListener((obse,old,nouv)-> retournerObjet(nouv));
		
		for (int i = 0; i < joueur.getInventaireObjet().getInventaire().size(); i++) 		
			joueur.getInventaireObjet().getInventaire().get(i).getNumObjetCase().addListener((obse,old,nouv)-> actualiser(nouv));				
	}

	public void actualiser(Number nouv) {	
			switch (nouv.intValue()) {
			case 0:
				case1.setImage(tabImage[0]);			
				break;
			case 1:
				case1.setImage(tabImage[1]);		
				break;
			case 2:
				case1.setImage(tabImage[2]);		
				break;
			case 3:
				case2.setImage(tabImage[3]);				
				break;
			case 4:
				case2.setImage(tabImage[4]);				
				break;
			case 5:
				case2.setImage(tabImage[5]);				
				break;
			case 6:
				case3.setImage(tabImage[6]);				
				break;
			case 7:
				case3.setImage(tabImage[7]);				
				break;
			case 8:
				case3.setImage(tabImage[8]);			
				break;
			case 9:
				case5.setImage(tabImage[9]);
				break;				
			case 10:
				case6.setImage(tabImage[10]);
				break;
			case 11 : 
				case4.setImage(tabImage[11]);
				break;	
			}
		}	
	
	public void equiperObjetDansLesMains(int i) {
		imgObjetDansLesMains.setImage(tabImage[i]);
	}
	
	private void retournerObjet(Boolean nouv) {
		if(!nouv) {
			imgObjetDansLesMains.translateXProperty().bind(joueur.xProperty().add(-20));
			if (joueur.getObjetEquiper()==11) { 
				imgObjetDansLesMains.setImage(tabImage[16]);
				imgObjetDansLesMains.setRotate(0);
			}
			else
				imgObjetDansLesMains.setRotate(240);
			
		}else {
			imgObjetDansLesMains.translateXProperty().bind(joueur.xProperty().add(40));
			imgObjetDansLesMains.translateYProperty().bind(joueur.yProperty().add(5));
			if (joueur.getObjetEquiper()==11) {
				imgObjetDansLesMains.setImage(tabImage[11]);
				imgObjetDansLesMains.setRotate(0);
			}
			else
				imgObjetDansLesMains.setRotate(350);
		}
		
	}
	
}
