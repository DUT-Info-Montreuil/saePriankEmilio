package jeu.controller;

import java.util.ArrayList;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.Ennemi;
import jeu.modele.Environnement;

public class MonObservateurEnnemie implements ListChangeListener<Ennemi>{

	private Pane conteneur;
	private ArrayList<Image> images;
	private Environnement env;
	public MonObservateurEnnemie(Pane conteneur ,Environnement env) {
		super();
		this.conteneur=conteneur;
		this.env=env;
		this.images = new ArrayList<>();
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiNeutre.png"));
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiDroite.png"));
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiGauche.png"));
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiSaut.png"));
		}
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Ennemi> c) {
			System.out.println("ça change");
			
			while(c.next()){
				// on ajoute les nouveau ennemie
				for(Ennemi nouveau: c.getAddedSubList()){
					ImageView r = new ImageView(images.get(0));
						r.setOnMouseClicked(e-> System.out.println("clic sur acteur"+ e.getSource()));		
						r.setId(nouveau.getId());
						r.translateXProperty().bind(nouveau.getXProperty());
						r.translateYProperty().bind(nouveau.getYProperty());
						nouveau.getDirection().addListener((obse,old,nouv)-> changerImageDirection(r,nouv.intValue()));
						conteneur.getChildren().add(r);	
						
						nouveau.getPvProperty().addListener(e-> enleverSprite(nouveau,nouveau.getPv()));
					 	
				}
				
			}

		}
		


	private void changerImageDirection(ImageView img, int intValue) {
		
		img.setImage(images.get(intValue));
		
	}
	private void enleverSprite(Ennemi mort,int pv) {
		if (mort.getPv()==0) {
			mort.setMort(true);
			env.getListeEnnemi().remove(mort);
			
			this.conteneur.getChildren().remove(this.conteneur.lookup("#"+mort.getId()));
		}

	}

		
}
