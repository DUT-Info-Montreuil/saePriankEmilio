package jeu.vue;

import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.Joueur;

public class VueBouclier {
	
	private Image[] tabImage= {new Image("jeu/modele/image/0bouclier.png"),new Image("jeu/modele/image/1bouclier.png"),new Image("jeu/modele/image/2bouclier.png"), new Image("jeu/modele/image/3bouclier.png")};
	private ImageView imageActive;
	private Joueur joueur;
	
	public VueBouclier(Joueur j,Pane root) {
		this.joueur=j;
		this.imageActive=new ImageView(tabImage[0]);
		this.imageActive.setVisible(false);
		this.imageActive.setTranslateY(12);
		this.imageActive.setTranslateX(160);
		root.getChildren().add(imageActive);
		
	}
	
	public IntegerProperty getBouclier() {
		return this.joueur.getNbBouclierProperty();
	}
	public Image[] getTabImage(){
	 return this.tabImage;
	}
	
	public void setImageActive(Image image) {
		imageActive.setImage(image);
	}
	public void imageSetVisible(boolean visible) {
		imageActive.setVisible(visible);
	}
}
