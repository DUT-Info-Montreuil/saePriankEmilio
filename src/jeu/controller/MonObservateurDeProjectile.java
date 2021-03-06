package jeu.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.projectile.Projectile;

public class MonObservateurDeProjectile implements ListChangeListener<Projectile>{

	private Pane conteneur;
	
	public MonObservateurDeProjectile(Pane conteneur ) {
		super();
		this.conteneur=conteneur;
		}
	
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Projectile> c) {
		while(c.next()){
			// on ajoute les nouveau ennemie
			for(Projectile nouveau: c.getAddedSubList()){	
					ImageView	imgBalle= new ImageView(new Image("jeu/image/utilitaires/balle.png"));	
					imgBalle.setId(nouveau.getId());
					imgBalle.setOnMouseClicked(e-> System.out.println("clic sur acteur"+ e.getSource()));		
					imgBalle.translateXProperty().bind(nouveau.getxProperty().add(50));
					imgBalle.translateYProperty().bind(nouveau.getyProperty().add(6));
					if(nouveau.getDirection()==2)
						imgBalle.setRotate(180);
					conteneur.getChildren().add(imgBalle);	
			}
			for(Projectile mort: c.getRemoved()){
				System.out.println(mort.getId());
					enleverSprite(mort);
			}
		}
	}
		
	private void enleverSprite(Projectile mort) {
		this.conteneur.getChildren().remove(this.conteneur.lookup("#"+mort.getId()));
	}	
}