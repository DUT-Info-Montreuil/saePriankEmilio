package jeu.controller;
//IMPORT
import java.net.URL;

import jeu.modele.*;
import jeu.vue.VueInventaire;
import jeu.vue.VueJoueur;
import jeu.vue.VueMap;
import jeu.vue.VuePv;
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
	//VARIABLES
	private Joueur joueur =new Joueur();//creation du joueur
	private Timeline gameLoop;//boucle du jeu
	private ImageView coeurs;	//image du joueur et du nb de coeurs
	private int[]tabMap; //map (tableau)
	private boolean direction; // direction du joueur true=droite false=gauche
	private VueJoueur vueJ;
	private HitBox hitBox;
	private VueMap vueMap;

    @FXML
    private HBox inventaireObjet;

    @FXML
    private Label labelBois;

    @FXML
    private Label labelMetal;

    @FXML
    private Label labelPierre;
    
    @FXML
    private Label labelNbDeBandage;

    @FXML
    private Label labelNbDeKitDeSoin;
    
    @FXML
    private ImageView case1;

    @FXML
    private ImageView case2;

    @FXML
    private ImageView case3;

    @FXML
    private ImageView case4;

    @FXML
    private ImageView case5;

    @FXML
    private ImageView case6;
	

    @FXML
    private ImageView ImageCraftBandage;

    @FXML
    private ImageView ImageCraftEpeeBois;

    @FXML
    private ImageView ImageCraftEpeeMetal;

    @FXML
    private ImageView ImageCraftEpeePierre;

    @FXML
    private ImageView ImageCraftHacheBois;

    @FXML
    private ImageView ImageCraftHacheMetal;

    @FXML
    private ImageView ImageCraftHachePierre;

    @FXML
    private ImageView ImageCraftKitDeSoin;

    @FXML
    private ImageView ImageCraftPiocheBois;

    @FXML
    private ImageView ImageCraftPiocheMetal;

    @FXML
    private ImageView ImageCraftPiochePierre;
	
	//INITIALISATION
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initAnimation();
		gameLoop.play();
		
	}
	
	//JOUEUR
	public  void joueur() {	
		vueJ = new VueJoueur(conteneur, joueur);
		vueJ.getImgActive().translateXProperty().bind(joueur.xProperty());
		vueJ.getImgActive().translateYProperty().bind(joueur.yProperty());
		vueJ.ajouterImageDuJoueur(); 
	}
	
	//GESTION DES TOUCHES
	@FXML
	void gestionDesTouches() {	
		GestionnaireDeToucheAppuyer toucheAppuyer =new GestionnaireDeToucheAppuyer(root, joueur, tabMap,vueJ.getImgActive(), menuCraft);
		GestionnaireDeToucheLacher toucheLacher =new GestionnaireDeToucheLacher(root, joueur,vueJ.getImgActive());
		root.setOnKeyPressed(toucheAppuyer);
		root.setOnKeyReleased(toucheLacher);
	}
	
	//DEPLACEMENT DU JOUEUR
	public void deplacement() {
		if(this.joueur.getGauche()) {
			direction = false;
			if(!Collision.collisionGauche(joueur, tabMap)) {
				joueur.allerAGauche();
			}
		}
		if(this.joueur.getDroite()) {
			direction = true;
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
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		int pxl = 40;
		int taille = 20;
		carte.setMaxSize(pxl*taille, pxl*taille);
		block();
		vueMap = new VueMap(carte);
		vueMap.afficherMap();
		tabMap=vueMap.getTabMap();
		joueur();
		this.joueur.nbCoeurProperty().addListener(new ObeservateurPv(new VuePv(joueur, root), joueur));
		new VueInventaire(joueur, inventaireObjet,labelNbDeBandage,labelNbDeKitDeSoin, labelBois,labelPierre,labelMetal,case1,case2,case3,case4,case5,case6);
		new gestionnaireDeCraft(joueur,textCraft,ImageCraftEpeeBois,ImageCraftEpeePierre,ImageCraftEpeeMetal,ImageCraftHacheBois,ImageCraftHachePierre,ImageCraftHacheMetal,ImageCraftPiocheBois,ImageCraftPiochePierre,ImageCraftPiocheMetal,ImageCraftKitDeSoin,ImageCraftBandage);
		this.gestionDesTouches();
		KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(0.05), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{			
					if(!Collision.graviter(joueur, tabMap)&& !this.joueur.getSaute() || joueur.getNbSaut()==6 || Collision.collisionHaut(joueur, tabMap) && this.joueur.getSaute() ) 
						joueur.tomber();
					if(Collision.graviter(joueur, tabMap)) 
						joueur.setNbSaut(0);
					deplacement();
				}));
		gameLoop.getKeyFrames().add(kf);
	}

	public  void afficherCoeurs() {
		coeurs = new ImageView(new Image("jeu/modele/image/hearts.png"));
		coeurs.translateXProperty().setValue(0);
		coeurs.translateYProperty().setValue(-100);
		conteneur.getChildren().add(coeurs);  
	}
	
	public void block() {
		root.setOnMouseClicked(ev -> {
			hitBox = new HitBox(joueur, carte, tabMap);
			if(direction) { // droite
				if(ev.getButton().equals(MouseButton.PRIMARY) && hitBox.peutPlacerDroite()) //placer des blocks
					hitBox.placerTuileDroite();
				else if(ev.getButton().equals(MouseButton.SECONDARY) && hitBox.peutCasserDroite()) // casser des blocks
					hitBox.casserTuileDroite();
			}
			else{ // gauche
				if(ev.getButton().equals(MouseButton.PRIMARY) && hitBox.peutPlacerGauche())  // placer des blocks
					hitBox.placerTuileGauche();
				else if(ev.getButton().equals(MouseButton.SECONDARY) && hitBox.peutCasserGauche()) // casser des blocks
					hitBox.casserTuileGauche();
			}
		});
	}
	
	
	
}
