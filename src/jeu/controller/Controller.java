package jeu.controller;
//IMPORT
import java.net.URL;

import jeu.modele.*;
import jeu.vue.*;

import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller implements Initializable{
	//VARIABLES FXML
    @FXML
    private Text textCraft;
    @FXML
	private Pane menuCraft;
	@FXML
	private Pane root;//ROOT
	@FXML
	private TilePane carte;//MAP
	@FXML
	private Pane conteneur;//JOUEUR
	@FXML
	private HBox inventaireObjet;
	@FXML
	private Label labelBois, labelMetal, labelPierre, labelNbDeBandage, labelNbDeKitDeSoin;
	@FXML
	private ImageView case1, case2, case3, case4, case5, case6;
	@FXML
	private ImageView ImageCraftBandage, ImageCraftEpeeBois, ImageCraftEpeeMetal, ImageCraftEpeePierre, ImageCraftHacheBois, ImageCraftHacheMetal, 
	ImageCraftHachePierre, ImageCraftKitDeSoin, ImageCraftPiocheBois, ImageCraftPiocheMetal, ImageCraftPiochePierre, ImageCraftPistolet, ImageCraftBouclier;
	@FXML
	private ImageView craftInventaire;
	@FXML
	private ImageView matSelectioner;
	
	//VARIABLES
	private Joueur joueur ;//creation du joueur
	private Timeline gameLoop;//boucle du jeu
	private int[]tabMap; //map (tableau)
	private VueJoueur vueJ; //Vue du joueur
	private Construction construction; // Placer/Casser 
	private VueMap vueMap; //Vue de la Map
	private Ennemi ennemi;
	private VueEnnemi vueEnnemi;
	private ArrayList<ImageView> imagesCraft;
	private Environnement env;
	//INITIALISATION
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		env=new Environnement();
		this.imagesCraft = new ArrayList<>();
		imagesCraft.add(ImageCraftEpeeBois);
		imagesCraft.add(ImageCraftEpeePierre);
		imagesCraft.add(ImageCraftEpeeMetal);	
		imagesCraft.add(ImageCraftHacheBois);
		imagesCraft.add(ImageCraftHachePierre);
		imagesCraft.add(ImageCraftHacheMetal);
		imagesCraft.add(ImageCraftPiocheBois);
		imagesCraft.add(ImageCraftPiochePierre);
		imagesCraft.add(ImageCraftPiocheMetal);
		imagesCraft.add(ImageCraftKitDeSoin);
		imagesCraft.add(ImageCraftBandage);
		imagesCraft.add(ImageCraftPistolet);
		imagesCraft.add(ImageCraftBouclier);
		initAnimation();
		gameLoop.play();
		
	}
	
	//JOUEUR
	public  void ajouterJoueur() {	
		vueJ = new VueJoueur(conteneur, joueur);
		vueJ.getImgActive().translateXProperty().bind(joueur.xProperty());
		vueJ.getImgActive().translateYProperty().bind(joueur.yProperty());
		vueJ.ajouterImageDuJoueur(); 
	}
	
	//ENNEMI
	public void ajouterEnnemi() {
		vueEnnemi = new VueEnnemi(conteneur, ennemi);
		vueEnnemi.getImgActive().translateXProperty().bind(ennemi.getXProperty());
		vueEnnemi.getImgActive().translateYProperty().bind(ennemi.getYProperty());
		vueEnnemi.ajouterImageEnnemi();
	}
	
	//GESTION DES TOUCHES
	@FXML
	void gestionDesTouches() {	
		GestionnaireDeToucheAppuyer toucheAppuyer =new GestionnaireDeToucheAppuyer(root, joueur, tabMap, menuCraft, craftInventaire);
		GestionnaireDeToucheLacher toucheLacher =new GestionnaireDeToucheLacher(root, joueur,vueJ.getImgActive());
		root.setOnKeyPressed(toucheAppuyer);
		root.setOnKeyReleased(toucheLacher);
	}
	
	//DEPLACEMENT DU JOUEUR
	public void deplacement() {
		if(this.joueur.getGauche()) {
			joueur.setDirection(false);
			if(!Collision.collisionGauche(joueur, tabMap)) {
				joueur.allerAGauche();
			}
		}
		if(this.joueur.getDroite()) {
			joueur.setDirection(true);
			if(!Collision.collisionDroite(joueur, tabMap)) {
				joueur.allerADroite();
			}
		}
		if(this.joueur.getSaute()) {
			if(!Collision.collisionHaut(joueur, tabMap)) {
				joueur.sauter();
			}		
		}
	}
	
	//BOUCLE DU JEU
	private void initAnimation() {
		joueur = env.getJoueur();
		ennemi = new Ennemi(joueur);
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		int pxl = 40;
		int taille = 20;
		carte.setMaxSize(pxl*taille, pxl*taille);
		block();
		vueMap = new VueMap(carte, joueur);
		vueMap.afficherMap();
		tabMap=vueMap.getTabMap();
		this.ajouterJoueur();
		this.ajouterEnnemi();
		this.joueur.nbCoeurProperty().addListener(new ObeservateurPv(new VuePv(joueur, root), joueur));
		this.joueur.getNbBouclierProperty().addListener(new ObservateurBouclier(new VueBouclier(joueur, root), joueur));
		new VueInventaire(joueur, inventaireObjet,labelNbDeBandage,labelNbDeKitDeSoin, labelBois,labelPierre,labelMetal,case1,case2,case3,case4,case5,case6);
		new gestionnaireDeCraft(joueur,textCraft,imagesCraft);
		this.gestionDesTouches();
		
		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.05), 
				(ev ->{			
					if(!Collision.graviter(joueur, tabMap)&& !this.joueur.getSaute() || joueur.getNbSaut()==6 || Collision.collisionHaut(joueur, tabMap) && this.joueur.getSaute() ) 
						joueur.tomber();
					if(Collision.graviter(joueur, tabMap)) 
						joueur.setNbSaut(0);
					if(Collision.collisionDroite(ennemi, tabMap) || Collision.collisionGauche(ennemi, tabMap) ) {
						ennemi.sauter();
					}
					if(!Collision.graviter(ennemi, tabMap))
						ennemi.tomber();
					if(Collision.graviter(ennemi, tabMap)) 
						ennemi.setNbSaut(0);
					deplacement();
					ennemi.suivreJoueur();
					this.vueEnnemi.actualiserImage();
					this.vueJ.actualiserImage();
					
				}));
		gameLoop.getKeyFrames().add(kf);
	}
	
	//Placer/Casser les blocks de la map
	public void block() {
		root.setOnMouseClicked(ev -> {
			construction = new Construction(joueur, tabMap);
			if(joueur.getDirection()) { // droite
				if(ev.getButton().equals(MouseButton.PRIMARY) && construction.peutPlacerDroite()) { //placer des blocks
					construction.placerTuileDroite();
					vueMap.actualiserMapDroite();
				}
				else if(ev.getButton().equals(MouseButton.SECONDARY) && construction.peutCasserDroite()) { // casser des blocks
					construction.casserTuileDroite();
					vueMap.actualiserMapDroiteCasser();
				}
			}
			else{ // gauche
				if(ev.getButton().equals(MouseButton.PRIMARY) && construction.peutPlacerGauche()) {  // placer des blocks
					construction.placerTuileGauche();
					vueMap.actualiserMapGauche();
				}
				else if(ev.getButton().equals(MouseButton.SECONDARY) && construction.peutCasserGauche()) { // casser des blocks
					construction.casserTuileGauche();
					vueMap.actualiserMapGaucheCasser();
				}
			}
		});
	}
	
	
}
