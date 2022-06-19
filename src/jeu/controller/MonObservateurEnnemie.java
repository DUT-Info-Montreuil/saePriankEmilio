package jeu.controller;

import java.util.ArrayList;
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
		images.add(new Image("jeu/image/personnage/ennemi/ennemiNeutre.png"));
		images.add(new Image("jeu/image/personnage/ennemi/ennemiDroite.png"));
		images.add(new Image("jeu/image/personnage/ennemi/ennemiGauche.png"));
		images.add(new Image("jeu/image/personnage/ennemi/ennemiSaut.png"));
		env.getNbEnnemiProperty().addListener((arg0, arg1, nouv) ->changerManche(nouv));
	}

	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Ennemi> c) {
		while(c.next()){
			// on ajoute les nouveau ennemie
			for(Ennemi nouveau: c.getAddedSubList()){
				ImageView chapeauSorcier=new ImageView(new Image("jeu/image/personnage/ennemi/chapeau.png"));
				if (nouveau instanceof Sorcier) {						
					chapeauSorcier.translateXProperty().bind(nouveau.getXProperty().add(14));
					chapeauSorcier.translateYProperty().bind(nouveau.getYProperty().add(-19));		
					chapeauSorcier.setFitWidth(20);
					chapeauSorcier.setFitHeight(20);
					conteneur.getChildren().add(chapeauSorcier);					
				}
				ImageView r = new ImageView(images.get(0));
				r.setOnMouseClicked(e-> System.out.println("clic sur acteur"+ e.getSource()));		
				r.setId(nouveau.getId());
				r.translateXProperty().bind(nouveau.getXProperty());
				r.translateYProperty().bind(nouveau.getYProperty());
				nouveau.getDirectionProperty().addListener((obse,old,nouv)-> changerImageDirection(r,nouv.intValue()));
				conteneur.getChildren().add(r);	
				env.ajouterUnEnnemiAucompteur();
				if (nouveau instanceof Sorcier) 
					nouveau.getPvProperty().addListener(e-> enleverSpriteSorcier(nouveau,nouveau.getPv(),chapeauSorcier));
				else
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

	private void enleverSpriteSorcier(Ennemi mort,int pv,ImageView imgChapeau) {
		if (mort.getPv()==0) {
			mort.setMort(true);
			env.getListeEnnemi().remove(mort);
			env.enleverUnEnnemiAucompteur();
			this.conteneur.getChildren().remove(this.conteneur.lookup("#"+mort.getId()));
			this.conteneur.getChildren().remove(imgChapeau);
		}
	}

	private void changerManche(Number nouv) {
		if (env.getNummeroMancheProperty().intValue()==20 && nouv.intValue()==0) {
			System.out.println("Vous avez gagner !");
			root.getChildren().removeAll();
			ImageView imgWin=new ImageView("jeu/image/gg.png");
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


}