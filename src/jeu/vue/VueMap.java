package jeu.vue;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import jeu.modele.Environnement;
import jeu.modele.Joueur;
import jeu.modele.Map;

public class VueMap {
	private int[] tabMap;
	private TilePane carte;
	private ImageView imageActive;
	private ArrayList<Image> imagesMap;
	private ArrayList<Image> imageRessources;
	private Map map;
	private Joueur joueur;
	
	public VueMap(TilePane carte , Environnement env) {
		imageActive = new ImageView();
		imagesMap = new ArrayList<>();
		imagesMap.add(new Image("jeu/modele/image/map/ciel.png")); //0 ciel
		imagesMap.add(new Image("jeu/modele/image/map/terre.png")); //1 terre
		imagesMap.add(new Image("jeu/modele/image/map/sol.png")); //2 sol avec herbes
		imagesMap.add(new Image("jeu/modele/image/map/obsidienne.png")); //3 obsidennes
		imagesMap.add(new Image("jeu/modele/image/map/bois.png")); //4 bois
		imagesMap.add(new Image("jeu/modele/image/map/pierre.png")); //5 pierre
		imagesMap.add(new Image("jeu/modele/image/map/metal.png")); //6 metal
		imagesMap.add(new Image("jeu/modele/image/map/haut.png")); //7 haut de la map
		
		this.map = env.getMap();
		this.tabMap = map.getTab();
		this.carte = carte; 
		this.joueur = env.getJoueur();
		
		this.imageRessources = new ArrayList<>();
		this.imageRessources.add(new Image("jeu/modele/image/map/bois.png"));
		this.imageRessources.add(new Image("jeu/modele/image/map/pierre.png"));
		this.imageRessources.add(new Image("jeu/modele/image/map/metal.png"));
	}
	
	public void afficherMap() {
		for(int a=0 ; a<tabMap.length; a++) {
			switch(tabMap[a]) {
			case 0 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			case 1 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			case 2 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			case 3 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			case 4 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			case 5 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			case 6 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			case 7 :
				imageActive = new ImageView(imagesMap.get(tabMap[a]));
				break;
			}
			carte.getChildren().add(imageActive);
		}	
	}
	
	public void actualiserMapDroite() {
		carte.getChildren().remove(joueur.constructionDroitePlacer());
		carte.getChildren().add(joueur.constructionDroitePlacer(), new ImageView(imageRessources.get(joueur.getMatChoisi()) ));
	}	
	public void actualiserMapGauche() {
		carte.getChildren().remove(joueur.constructionGauchePlacer());
		carte.getChildren().add(joueur.constructionGauchePlacer(), new ImageView (imageRessources.get(joueur.getMatChoisi()) ));
	}
	
	
	public void actualiserMapDroiteCasser() {
		if(map.getPvBlock(joueur.constructionDroiteCasser()) <=0) {
			carte.getChildren().remove(joueur.constructionDroiteCasser());
			carte.getChildren().add(joueur.constructionDroiteCasser(), new ImageView(imagesMap.get(0)));
		}
	}
	public void actualiserMapGaucheCasser() {
		if(map.getPvBlock(joueur.constructionGaucheCasser()) <=0) {
			carte.getChildren().remove(joueur.constructionGaucheCasser());
			carte.getChildren().add( joueur.constructionGaucheCasser(), new ImageView(imagesMap.get(0)));
		}
	}

	public void actualiser(int i, int resource) {
		carte.getChildren().remove(i);
		carte.getChildren().add( i, new ImageView(imagesMap.get(resource)));
	}
	
	public int[] getTabMap() {
		return tabMap;
	}
	public void setTabMap(int[] tabMap) {
		this.tabMap = tabMap;
	}
	public Map getEnv() {
		return map;
	}
	public void setEnv(Map env) {
		this.map = env;
	}
		
}