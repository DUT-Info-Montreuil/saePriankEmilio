package jeu.vue;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import jeu.modele.Environnement;
import jeu.modele.Joueur;


public class VueInventaire {
		
	private Image[] tabImage  = {new Image("jeu/modele/image/utilitaires/epeeBois.png"),new Image("jeu/modele/image/utilitaires/epeePierre.png"),
			new Image("jeu/modele/image/utilitaires/epeeMetal.png"),new Image("jeu/modele/image/utilitaires/hacheBois.png"),
			new Image("jeu/modele/image/utilitaires/hachePierre.png"),new Image("jeu/modele/image/utilitaires/hacheMetal.png"),
			new Image("jeu/modele/image/utilitaires/piocheBois.png"),new Image("jeu/modele/image/utilitaires/piochePierre.png"),
			new Image("jeu/modele/image/utilitaires/piocheMetal.png"),new Image("jeu/modele/image/utilitaires/bandage.png"),
			new Image("jeu/modele/image/utilitaires/kitDeSoin.png"),new Image("jeu/modele/image/utilitaires/pistolet.png"),
			new Image("jeu/modele/image/utilitaires/vide.png"),new Image("jeu/modele/image/utilitaires/bois.png"),
			new Image("jeu/modele/image/utilitaires/pierre.png"),new Image("jeu/modele/image/utilitaires/metal.png"),new Image("jeu/modele/image/utilitaires/pistoletInverser.png")};	
	private Joueur joueur;
	private Environnement env;
	private Label labelBois,labelPierre,labelMetal,labelNbDeBandage,labelNbDeKitDeSoin;
	private ImageView case1,case2,case3,case4,case5,case6;
	private ImageView imgObjetDansLesMains;
	
	public VueInventaire(Environnement env,HBox boxInv,Label labelNbDeBandage,Label labelNbDeKitDeSoin,Label labelBois,Label labelPierre,Label labelMetal,
			ImageView case1,ImageView case2,ImageView case3,ImageView case4,ImageView case5,ImageView case6,ImageView imgObjetDansLesMains) {
		this.env=env;
		this.joueur=env.getJoueur();
		this.labelBois=labelBois;
		this.labelPierre=labelPierre;
		this.labelMetal=labelMetal;
		this.labelNbDeBandage=labelNbDeBandage;
		this.labelNbDeKitDeSoin=labelNbDeKitDeSoin;
		this.case1=case1;
		this.case2=case2;
		this.case3=case3;
		this.case4=case4;
		this.case5=case5;
		this.case6=case6;
		afficherInventaireObjet();
		this.imgObjetDansLesMains=imgObjetDansLesMains;
		this.imgObjetDansLesMains.translateXProperty().bind(joueur.xProperty().add(40));
		this.imgObjetDansLesMains.translateYProperty().bind(joueur.yProperty());
		
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
		
		for (int i = 0; i < joueur.getInventaireObjet().getInventaire().size(); i++) {
			
			joueur.getInventaireObjet().getInventaire().get(i).getNumObjetCase().addListener((obse,old,nouv)-> actualiser(nouv));
		}				
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
			System.out.println(joueur.getInventaireObjet().getInventaire().toString());
		}	
	
	public void equiperObjetDansLesMains(int i) {
		System.out.println("Objet équiper :"+i );
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
			if (joueur.getObjetEquiper()==11) {
				imgObjetDansLesMains.setImage(tabImage[11]);
				imgObjetDansLesMains.setRotate(0);
			}
			else
				imgObjetDansLesMains.setRotate(350);
		}
		
	}
	
}
