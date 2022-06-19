package jeu.vue;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.Joueur;

public class VueJoueur {
	
	//variables
	private ImageView imgActive;
	private Pane conteneur;
	private Joueur joueur;
	private ArrayList<Image> images;
	
	//constructeur
	public VueJoueur (Pane conteneur, Joueur joueur) {
		this.imgActive = new ImageView(new Image("jeu/modele/image/personnage/neutre.png"));
		this.conteneur = conteneur;
		this.joueur = joueur;
		this.images = new ArrayList<>();
		images.add(new Image("jeu/modele/image/personnage/neutre.png"));
		images.add(new Image("jeu/modele/image/personnage/droite.png"));
		images.add(new Image("jeu/modele/image/personnage/gauche.png"));
		images.add(new Image("jeu/modele/image/personnage/saut.png"));
		this.imgActive = new ImageView(images.get(0));
		this.imgActive.translateXProperty().bind(joueur.xProperty());
		this.imgActive.translateYProperty().bind(joueur.yProperty());
		this.ajouterImageDuJoueur(); 
		
		//listerner qui change l'image en fonction de la direction : gauche droite saute
		this.joueur.getDroiteProperty().addListener( e->actualiserImage());
		this.joueur.getGaucheProperty().addListener( e->actualiserImage());
		this.joueur.getSauteProperty().addListener( e->actualiserImage());
	}
	
	//getter
	public ImageView getImgActive() {
		return imgActive;
	}
	
	//setter
	public void setImgActive(ImageView imgActive) {
		this.imgActive = imgActive;
	}
	
	//methode
	public void ajouterImageDuJoueur() {
		conteneur.getChildren().add(imgActive);
	}
	
	public void actualiserImage() {		
		if(this.joueur.getDroite()) 
			this.imgActive.setImage(images.get(1));
		else if(this.joueur.getGauche()) 
			this.imgActive.setImage(images.get(2));
		else if(this.joueur.getSaute()) 
			this.imgActive.setImage(images.get(3));
		else 
			this.imgActive.setImage(images.get(0));
	}
}
