package jeu.controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import jeu.modele.Collision;
import jeu.modele.Environnement;
import jeu.modele.Joueur;

public class GestionnaireDeToucheAppuyer implements EventHandler<KeyEvent>{
	private Pane root;
	private Joueur joueur;
	private int[]tabMap;
	private boolean visible =false;
	private Pane menuCraft;
	private ImageView craftInventaire;
	private ImageView matChoisi;
	private Environnement env;
	int test=0;
	public GestionnaireDeToucheAppuyer(Pane root,Environnement env, Pane menuCraft, ImageView InventaireCraft) {
		this.root=root;
		this.env=env;
		this.joueur=env.getJoueur();
		this.tabMap=env.getTabMap();
		this.menuCraft=menuCraft;
		this.craftInventaire = InventaireCraft;

		this.matChoisi = new ImageView();
		matChoisi.setImage(new Image("jeu/image/jaune.png"));
		matChoisi.setTranslateX(497);
		matChoisi.setTranslateY(7);
		matChoisi.setOpacity(0.90);
		matChoisi.setVisible(false);
		root.getChildren().add(matChoisi);
	}
	@Override
	public void handle(KeyEvent arg0) {
		root.setOnKeyPressed(e -> {
			switch(e.getCode()){
			case Q :
				joueur.setGauche(true);
				break;
			case D :
				joueur.setDroite(true);
				break;
			case Z :
				if(Collision.graviter(joueur, tabMap)) {
					this.joueur.setSaute(true);
				}		
				break;
			case I :
				if (visible==false) {
					menuCraft.setVisible(true);
					craftInventaire.setVisible(true);
					visible=true;
				}else if(visible==true) {
					menuCraft.setVisible(false);
					craftInventaire.setVisible(false);
					visible=false;
				}		
				break;
			case F1 : 
				env.getJoueur().setMatChoisi(0);
				matChoisi.setTranslateX(497);
				matChoisi.setTranslateY(7);
				matChoisi.setVisible(true);
				env.getJoueur().setObjetEquiper(13);
				System.out.println("bois choisi");
				break;
			case F2 :
				env.getJoueur().setMatChoisi(1);
				matChoisi.setTranslateX(597);
				matChoisi.setTranslateY(7);
				matChoisi.setVisible(true);
				env.getJoueur().setObjetEquiper(14);
				System.out.println("pierre choisie");
				break;
			case F3 :
				env.getJoueur().setMatChoisi(2);
				matChoisi.setTranslateX(697);
				matChoisi.setTranslateY(7);
				matChoisi.setVisible(true);
				env.getJoueur().setObjetEquiper(15);
				System.out.println("metal choisi");
				break;
			case F4 :
				env.getJoueur().setObjetEquiper(env.getJoueur().getInventaireObjet().getInventaire().get(0).getNumObjetCase().intValue());
				System.out.println("case 1 choisi");
				env.getJoueur().setCaseChoisi(1);
				matChoisi.setTranslateY(545);
				matChoisi.setTranslateX(41);
				matChoisi.setVisible(true);
				break;
			case F5 :
				env.getJoueur().setObjetEquiper(env.getJoueur().getInventaireObjet().getInventaire().get(1).getNumObjetCase().intValue());
				System.out.println("case 2 choisi");
				env.getJoueur().setCaseChoisi(2);
				matChoisi.setTranslateY(545);
				matChoisi.setTranslateX(86);
				matChoisi.setVisible(true);
				break;
			case F6 :
				env.getJoueur().setObjetEquiper(env.getJoueur().getInventaireObjet().getInventaire().get(2).getNumObjetCase().intValue());
				System.out.println("case 2 choisi");
				env.getJoueur().setCaseChoisi(3);
				matChoisi.setTranslateY(545);
				matChoisi.setTranslateX(131);
				matChoisi.setVisible(true);
				break;
			case F7 :
				env.getJoueur().setObjetEquiper(env.getJoueur().getInventaireObjet().getInventaire().get(3).getNumObjetCase().intValue());
				System.out.println("case 4 choisi");
				env.getJoueur().setCaseChoisi(4);
				matChoisi.setTranslateY(545);
				matChoisi.setTranslateX(176);
				matChoisi.setVisible(true);
				break;
			case F8 :
				env.getJoueur().setObjetEquiper(env.getJoueur().getInventaireObjet().getInventaire().get(4).getNumObjetCase().intValue());
				System.out.println("case 5 choisi");
				env.getJoueur().setCaseChoisi(5);
				matChoisi.setTranslateY(545);
				matChoisi.setTranslateX(221);
				matChoisi.setVisible(true);
				break;
			case F9 :
				env.getJoueur().setObjetEquiper(env.getJoueur().getInventaireObjet().getInventaire().get(5).getNumObjetCase().intValue());
				System.out.println("case 6 choisi");
				env.getJoueur().setCaseChoisi(6);
				matChoisi.setTranslateY(545);
				matChoisi.setTranslateX(281);
				matChoisi.setVisible(true);
				break;
			default:
				break;
			}
		});

	}

}
