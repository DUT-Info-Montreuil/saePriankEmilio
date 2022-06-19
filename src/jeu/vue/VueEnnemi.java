package jeu.vue;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.Ennemi;

public class VueEnnemi {

	//variables
	private ImageView imgActive;
	private Pane conteneur;
	private Ennemi ennemi;
	private ArrayList<Image> images;

	//constructeur
	public VueEnnemi (Pane conteneur, Ennemi ennemi) {
		this.conteneur = conteneur;
		this.ennemi = ennemi;
		this.images = new ArrayList<>();
		images.add(new Image("jeu/image/personnage/ennemi/ennemiNeutre.png"));
		images.add(new Image("jeu/image/personnage/ennemi/ennemiDroite.png"));
		images.add(new Image("jeu/image/personnage/ennemi/ennemiGauche.png"));
		images.add(new Image("jeu/image/personnage/ennemi/ennemiSaut.png"));
		this.imgActive = new ImageView(images.get(0));
	}

	//methode
	public void ajouterImageEnnemi() {
		conteneur.getChildren().add(imgActive);
	}

	public void actualiserImage() {
		if(this.ennemi.isDroite()) 
			this.imgActive.setImage(images.get(1));
		else if(this.ennemi.isGauche()) 
			this.imgActive.setImage(images.get(2));
		else if(this.ennemi.isSaute()) 
			this.imgActive.setImage(images.get(3));
		else 
			this.imgActive.setImage(images.get(0));
	}
	
	//getter
	public ImageView getImgActive() {
		return imgActive;
	}

	//setter
	public void setImgActive(ImageView imgActive) {
		this.imgActive = imgActive;
	}


}
