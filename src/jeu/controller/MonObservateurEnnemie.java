package jeu.controller;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.Ennemi;
import jeu.modele.Environnement;
import jeu.modele.Sorcier;

public class MonObservateurEnnemie implements ListChangeListener<Ennemi>{

	private Pane conteneur;
	private Pane root;
	private ArrayList<Image> images;
	private Environnement env;
	public MonObservateurEnnemie(Pane conteneur,Pane root ,Environnement env) {
		super();
		this.conteneur=conteneur;
		this.root=root;
		this.env=env;
		this.images = new ArrayList<>();
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiNeutre.png"));
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiDroite.png"));
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiGauche.png"));
		images.add(new Image("jeu/modele/image/personnage/ennemi/ennemiSaut.png"));
		env.getNbEnnemiProperty().addListener((arg0, arg1, nouv) ->changerManche(nouv));
		}
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Ennemi> c) {
			System.out.println("on change");
			
			while(c.next()){
				// on ajoute les nouveau ennemie
				for(Ennemi nouveau: c.getAddedSubList()){
					
					if (nouveau instanceof Sorcier) 
						((Sorcier) nouveau).getATirerProperty().addListener((obse,old,nouv)-> methodePourSorcier(nouveau,nouv));
						ImageView r = new ImageView(images.get(0));
						r.setOnMouseClicked(e-> System.out.println("clic sur acteur"+ e.getSource()));		
						r.setId(nouveau.getId());
						r.translateXProperty().bind(nouveau.getXProperty());
						r.translateYProperty().bind(nouveau.getYProperty());
						nouveau.getDirectionProperty().addListener((obse,old,nouv)-> changerImageDirection(r,nouv.intValue()));
						conteneur.getChildren().add(r);	
						env.ajouterUnEnnemiAucompteur();
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
			env.enleverUnEnnemiAucompteur();
			this.conteneur.getChildren().remove(this.conteneur.lookup("#"+mort.getId()));
		}

	}

	private void changerManche(Number nouv) {
		if (env.getNummeroMancheProperty().intValue()==20 && nouv.intValue()==0) {
			System.out.println("c'est gagner");
			root.getChildren().removeAll();
			ImageView imgWin=new ImageView("jeu/modele/image/kelawin.png");
			imgWin.setFitWidth(800);
			imgWin.setFitHeight(600);
			root.getChildren().add(imgWin);
			env.arreterLeJeu();
		}
		else if (nouv.intValue()==0) {
			env.ajtmanche();
			env.lancerManche();
		}

	}
	
	private void methodePourSorcier(Ennemi ennemi,Boolean nouv) {
		
	
	}
	
		
}