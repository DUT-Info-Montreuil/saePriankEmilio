package jeu.vue;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jeu.modele.Joueur;

public class VuePv {
	
	private Image[] tabImage= {new Image("jeu/image/perdue.png"),new Image("jeu/image/1hearts.png"),new Image("jeu/image/2hearts.png"),
			new Image("jeu/image/3hearts.png"),new Image("jeu/image/4hearts.png"),new Image("jeu/image/hearts.png"),};	
	private ImageView imageActive;
	private Joueur joueur;
	
	public VuePv(Joueur j,Pane root) {
		this.joueur=j;
		this.imageActive=new ImageView(tabImage[5]);
		this.imageActive.setTranslateY(-100);
		root.getChildren().add(imageActive);
		
	}
	
	public IntegerProperty getCoeurs() {
		return this.joueur.nbCoeurProperty();
	}
	
	public Image[] getTabImage(){
	 return this.tabImage;
	}
	
	public void setImageActive(Image image) {
		if(image.equals(tabImage[0])) {
			imageActive.setImage(tabImage[0]);
			imageActive.setTranslateY(0);
			imageActive.setOpacity(0.70);
		}else {
			imageActive.setImage(image);
			imageActive.setTranslateY(-100);
			imageActive.setOpacity(1);
		}
		imageActive.setImage(image);
	}
}
	
 

	

	


