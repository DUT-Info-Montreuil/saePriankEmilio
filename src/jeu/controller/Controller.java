package jeu.controller;
//IMPORT
import java.net.URL;
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
import jeu.modele.Collision;
import jeu.modele.Construction;
import jeu.modele.Environnement;
import jeu.modele.ObeservateurPv;
import jeu.modele.ObservateurBouclier;
import jeu.vue.VueBouclier;
import jeu.vue.VueInventaire;
import jeu.vue.VueJoueur;
import jeu.vue.VueMap;
import jeu.vue.VuePv;

public class Controller implements Initializable{
	//VARIABLES FXML
    @FXML
    private Text textCraft; //text qui affiche le prix des objets
    @FXML
	private Pane menuCraft; //Panel de craft
	@FXML
	private Pane root;//ROOT
	@FXML
	private TilePane carte;//MAP
	@FXML
	private Pane conteneur;//JOUEUR
	@FXML
	private HBox inventaireObjet; //inventaire des objets du joueur
	@FXML
	private Label labelBois, labelMetal, labelPierre, labelNbDeBandage, labelNbDeKitDeSoin,labelNumManches,labelNbennemiRestant; //label
	@FXML
	private ImageView case1, case2, case3, case4, case5, case6; //image des cases de l'inventaire objet
	@FXML
	private ImageView ImageCraftBandage, ImageCraftEpeeBois, ImageCraftEpeeMetal, ImageCraftEpeePierre, ImageCraftHacheBois, ImageCraftHacheMetal, 
	ImageCraftHachePierre, ImageCraftKitDeSoin, ImageCraftPiocheBois, ImageCraftPiocheMetal, ImageCraftPiochePierre, ImageCraftPistolet, ImageCraftBouclier; //images des objets
	@FXML
	private ImageView craftInventaire; //image du panel de craft
	@FXML
	private ImageView matSelectioner; //materiaux selectionner
	@FXML
	private ImageView imgObjetDansLesMains; //objet que tien le joueur
	
	//VARIABLES
	private Timeline gameLoop;//boucle du jeu
	private Construction construction; // Placer/Casser 
	private VueMap vueMap; //Vue de la Map
	private ArrayList<ImageView> imagesCraft; //les images des objets
	private Environnement env; //environnement du jeu
	private ArrayList<ImageView> imagesCase; //images des cases
	private ArrayList<Label> labels; //les labels

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
			if(!Collision.collisionGauche(env.getJoueur(), env.getTabMap())) 
				env.getJoueur().allerAGauche();
		}
		if(this.env.getJoueur().getDroite()) {
			env.getJoueur().setDirection(true);
			if(!Collision.collisionDroite(env.getJoueur(), env.getTabMap())) 
				env.getJoueur().allerADroite();
		}
		if(this.env.getJoueur().getSaute()) 
			if(!Collision.collisionHaut(env.getJoueur(), env.getTabMap())) 
				env.getJoueur().sauter();	
	}
	

	
	//BOUCLE DU JEU
	private void initAnimation() {
		this.gameLoop = new Timeline();
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);
		this.env=new Environnement(gameLoop);
		this.env.getNummeroMancheProperty().addListener((obse,old,nouv)-> this.labelNumManches.setText("manche :"+nouv.intValue()));
		if (env.getListeEnnemi().isEmpty()) 
			this.env.ajtmanche();
		this.env.getNbEnnemiProperty().addListener((obse,old,nouv)-> this.labelNbennemiRestant.setText("zombies restant:"+nouv.intValue()));
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
		
		imagesCase = new ArrayList<>();
		imagesCase.add(case1);
		imagesCase.add(case2);
		imagesCase.add(case3);
		imagesCase.add(case4);
		imagesCase.add(case5);
		imagesCase.add(case6);
		
		labels = new ArrayList<>();
		labels.add(labelBois);
		labels.add(labelPierre);
		labels.add(labelMetal);
		labels.add(labelNbDeBandage);
		labels.add(labelNbDeKitDeSoin);
		
		this.gestionSouris();
		this.vueMap = new VueMap(carte, env);
		this.vueMap.afficherMap();	
		new VueJoueur(conteneur, env.getJoueur());
		
		//this.ajouterEnnemi();
		this.env.getListeEnnemi().addListener(new MonObservateurEnnemie(conteneur,root,env));
		this.env.getListeProjectile().addListener(new MonObservateurDeProjectile(conteneur));
		this.env.getListeProjectileEnnemi().addListener(new MonObservateurDeProjectileEnnemi(conteneur));
		this.env.getJoueur().nbCoeurProperty().addListener(new ObeservateurPv(new VuePv(env.getJoueur(), root), env.getJoueur()));
		this.env.getJoueur().getNbBouclierProperty().addListener(new ObservateurBouclier(new VueBouclier(env.getJoueur(), root), env.getJoueur()));
		
		new VueInventaire(env, inventaireObjet,labels,imagesCase, imgObjetDansLesMains);
		new gestionnaireDeCraft(env.getJoueur(),textCraft,imagesCraft);
		this.gestionDesTouches();
	
		env.lancerManche();
				
		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.05), 
				(ev ->{			
					if(env.isMancheLancer()==true) {
						env.ajouterNEnnemi(env.getNummeroMancheProperty().getValue()+2);
						if(env.getNummeroMancheProperty().getValue() ==6) {
							changementMap(env.getMap().getCarte2());
						}
						if(env.getNummeroMancheProperty().getValue() == 11) {
							changementMap(env.getMap().getCarte3());
						}
						if(env.getNummeroMancheProperty().getValue() == 16) {
							changementMap(env.getMap().getCarte4());
						}
						
						if(env.getNummeroMancheProperty().getValue() >=2 && env.getNummeroMancheProperty().getValue() <6) {
							ajouterResource(175, 4);
							ajouterResource(192, 4);
							ajouterResource(198, 4);
						}else if(env.getNummeroMancheProperty().getValue() >=6 && env.getNummeroMancheProperty().getValue() <11) {
							ajouterResource(205, 5);
							ajouterResource(194, 5);
							ajouterResource(188, 5);
						}
						else if(env.getNummeroMancheProperty().getValue() >=11 && env.getNummeroMancheProperty().getValue() <16) {
							ajouterResource(197, 6);
							ajouterResource(211, 6);
							ajouterResource(195, 6);
							ajouterResource(176, 6);
						}
						
						else if(env.getNummeroMancheProperty().getValue() >=16 && env.getNummeroMancheProperty().getValue() <21) {
							ajouterResource(150, 4);
							ajouterResource(186, 5);
							ajouterResource(196, 5);
							ajouterResource(224, 6);
							ajouterResource(198, 6);
						}
						env.setFalsemancheLancer();
					}
					
					this.deplacementJoueur();
					env.agit();
				}));
		gameLoop.getKeyFrames().add(kf);
	}
	
	public void gestionSouris() {
		root.setOnMouseClicked(ev -> {
			//attaquer
			construction = new Construction(env);
			if(ev.getButton().equals(MouseButton.PRIMARY)&& (env.getJoueur().getObjetEquiper()==12 || env.getJoueur().getObjetEquiper()==0 || env.getJoueur().getObjetEquiper()==2 || env.getJoueur().getObjetEquiper()==1)) {
				if (env.getNbEnnemiProperty().get()>0 )
					env.getJoueur().attaquer();
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
			//Placer/Casser les blocks de la map
			
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
	
	//permet de rajouter une resource sur la map
	public void ajouterResource(int i, int resource) {
		env.getMap().ajouterResource(i,resource);
		vueMap.ajouterResource(i,resource);
	}
	
	//permet de changer de map
	public void changementMap(int[] tabMap) {
		env.getMap().changementMap(tabMap);
		vueMap.actualiser();
		env.getMap().removeResource();
		vueMap.removeResource();
		env.getJoueur().setX(40);
		env.getJoueur().setY(360);
	}
	
}
