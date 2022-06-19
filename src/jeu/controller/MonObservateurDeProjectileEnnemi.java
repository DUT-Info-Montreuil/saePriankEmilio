package jeu.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.projectile.Projectile;
import jeu.modele.projectile.ProjectileEnnemi;

public class MonObservateurDeProjectileEnnemi implements ListChangeListener<ProjectileEnnemi>{

	private Pane conteneur;
	
	public MonObservateurDeProjectileEnnemi(Pane conteneur ) {
		super();
		this.conteneur=conteneur;
		
		
		;
		}
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends ProjectileEnnemi> c) {
		System.out.println("�a change");
		
		while(c.next()){
			// on ajoute les nouveau ennemie
			for(ProjectileEnnemi nouveau: c.getAddedSubList()){
			
					
					ImageView	imgBalle= new ImageView(new Image("jeu/modele/image/utilitaires/fireBal.png"));
				
					imgBalle.setId(nouveau.getId());
					imgBalle.setOnMouseClicked(e-> System.out.println("clic sur acteur"+ e.getSource()));		
					imgBalle.translateXProperty().bind(nouveau.getxProperty().add(50));
					imgBalle.translateYProperty().bind(nouveau.getyProperty().add(6));
					imgBalle.setFitWidth(20);
					imgBalle.setFitHeight(20);
					if(nouveau.getDirection()==2)
						imgBalle.setRotate(380);
					else
						imgBalle.setRotate(600);
					conteneur.getChildren().add(imgBalle);	
				
				
			}
			
			for(ProjectileEnnemi mort: c.getRemoved()){
				System.out.println(mort.getId());
					enleverSprite(mort);
			}
		}

	}
		



	
	private void enleverSprite(Projectile mort) {
		this.conteneur.getChildren().remove(this.conteneur.lookup("#"+mort.getId()));

	}

		
}