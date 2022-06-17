package jeu.controller;
//IMPORT
import java.net.URL;

import jeu.modele.*;
import jeu.modele.projectile.Projectile;
import jeu.vue.*;

import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
	
	@FXML
	private ImageView imgObjetDansLesMains;
	//VARIABLES

	private Timeline gameLoop;//boucle du jeu
	

	private Construction construction; // Placer/Casser 
	private VueMap vueMap; //Vue de la Map
	private ArrayList<ImageView> imagesCraft;
	private Environnement env;
	//INITIALISATION
	@Override
	
	public void initialize(URL location, ResourceBundle resources) {
		
		initAnimation();
		gameLoop.play();
		
	}
	

	
	
	
	
	//GESTION DES TOUCHES
	@FXML
	void gestionDesTouches() {	
		GestionnaireDeToucheAppuyer toucheAppuyer =new GestionnaireDeToucheAppuyer(root, env, menuCraft, craftInventaire);
		GestionnaireDeToucheLacher toucheLacher =new GestionnaireDeToucheLacher(root, env.getJoueur());
		root.setOnKeyPressed(toucheAppuyer);
		root.setOnKeyReleased(toucheLacher);
	}
	
	//DEPLACEMENT DU JOUEUR
	public void deplacementJoueur() {
		if(this.env.getJoueur().getGauche()) {
			env.getJoueur().setDirection(false);
			if(!Collision.collisionGauche(env.getJoueur(), env.getTabMap())) {
				env.getJoueur().allerAGauche();
			}
		}
		if(this.env.getJoueur().getDroite()) {
			env.getJoueur().setDirection(true);
			if(!Collision.collisionDroite(env.getJoueur(), env.getTabMap())) {
				env.getJoueur().allerADroite();
			}
		}
		if(this.env.getJoueur().getSaute()) {
			if(!Collision.collisionHaut(env.getJoueur(), env.getTabMap())) {
				env.getJoueur().sauter();
			}		
		}
	}
	

	
	//BOUCLE DU JEU
	private void initAnimation() {
		gameLoop = new Timeline();
		env=new Environnement(gameLoop);
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
		
		
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		block();
		vueMap = new VueMap(carte, env.getJoueur());
		vueMap.afficherMap();
		
	
		
		//this.ajouterEnnemi();
		this.env.getListeEnnemi().addListener(new MonObservateurEnnemie(conteneur));
		this.env.getListeProjectile().addListener(new MonObservateurDeProjectile(conteneur));
		new VueJoueur(conteneur, env.getJoueur());
		this.env.getJoueur().nbCoeurProperty().addListener(new ObeservateurPv(new VuePv(env.getJoueur(), root), env.getJoueur()));
		this.env.getJoueur().getNbBouclierProperty().addListener(new ObservateurBouclier(new VueBouclier(env.getJoueur(), root), env.getJoueur()));
		new VueInventaire(env, inventaireObjet,labelNbDeBandage,labelNbDeKitDeSoin, labelBois,labelPierre,labelMetal,case1,case2,case3,case4,case5,case6, imgObjetDansLesMains);
		new gestionnaireDeCraft(env.getJoueur(),textCraft,imagesCraft);
		this.gestionDesTouches();
		
		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.05), 
				(ev ->{			
				
					

					deplacementJoueur();
					env.agit();

				}));
		gameLoop.getKeyFrames().add(kf);
	}
	
	//Placer/Casser les blocks de la map
	public void block() {
		root.setOnMouseClicked(ev -> {
			construction = new Construction(env);
			
			if(ev.getButton().equals(MouseButton.PRIMARY)&& (env.getJoueur().getObjetEquiper()==12 || env.getJoueur().getObjetEquiper()==0 || env.getJoueur().getObjetEquiper()==2 || env.getJoueur().getObjetEquiper()==1)) {
				env.getJoueur().attquer();
				
			}
			//utiliser bandage
			else if (ev.getButton().equals(MouseButton.PRIMARY) && env.getJoueur().getObjetEquiperProperty().getValue()==9) {
				env.getJoueur().utiliserBandage();
			}
			else if (ev.getButton().equals(MouseButton.PRIMARY) && env.getJoueur().getObjetEquiperProperty().getValue()==11) {
				env.getJoueur().tirer();
			}
			//utiliser un kit De soin
			else if (ev.getButton().equals(MouseButton.PRIMARY) && env.getJoueur().getObjetEquiperProperty().getValue()==10) {
				env.getJoueur().utiliserkitDeSoin();
			}
			//placer et crafter des bloques a droite
			else if(env.getJoueur().getDirection()) { // droite
				if(ev.getButton().equals(MouseButton.PRIMARY) && construction.peutPlacerDroite()) { //placer des blocks
					construction.placerTuileDroite();
					vueMap.actualiserMapDroite();
				}
				else if(ev.getButton().equals(MouseButton.SECONDARY) && construction.peutCasserDroite()) { // casser des blocks
					construction.casserTuileDroite();
					vueMap.actualiserMapDroiteCasser();
				}
			}
			//placer des block a gauches
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
