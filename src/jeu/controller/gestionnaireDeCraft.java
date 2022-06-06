package jeu.controller;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import jeu.modele.Joueur;

public class gestionnaireDeCraft {

	private ImageView ImageCraftEpeeBois,ImageCraftEpeePierre,ImageCraftEpeeMetal,ImageCraftHacheBois,ImageCraftHachePierre,ImageCraftHacheMetal,ImageCraftPiocheBois,ImageCraftPiochePierre,ImageCraftPiocheMetal,ImageCraftKitDeSoin,ImageCraftBandage;
	private Joueur joueur;
	private Text textCraft;

	public gestionnaireDeCraft(Joueur joueur,Text textCraft,ImageView imageCraftEpeeBois, ImageView imageCraftEpeePierre,ImageView imageCraftEpeeMetal, ImageView imageCraftHacheBois, ImageView imageCraftHachePierre,ImageView imageCraftHacheMetal, ImageView imageCraftPiocheBois, ImageView imageCraftPiochePierre,ImageView imageCraftPiocheMetal, ImageView imageCraftKitDeSoin, ImageView imageCraftBandage) {
		this.joueur=joueur;
		this.textCraft=textCraft;
		this.ImageCraftEpeeBois = imageCraftEpeeBois;
		this.ImageCraftEpeePierre = imageCraftEpeePierre;
		this.ImageCraftEpeeMetal = imageCraftEpeeMetal;
		this.ImageCraftHacheBois = imageCraftHacheBois;
		this.ImageCraftHachePierre = imageCraftHachePierre;
		this.ImageCraftHacheMetal = imageCraftHacheMetal;
		this.ImageCraftPiocheBois = imageCraftPiocheBois;
		this.ImageCraftPiochePierre = imageCraftPiochePierre;
		this.ImageCraftPiocheMetal = imageCraftPiocheMetal;
		this.ImageCraftKitDeSoin = imageCraftKitDeSoin;
		this.ImageCraftBandage = imageCraftBandage;
		crafter();
	}
	
	public void crafter( ) {
		
		
		
		
		ImageCraftEpeeBois.setOnMouseClicked(arg0 -> joueur.crafterEpeeBois());
		ImageCraftEpeeBois.setOnMouseEntered(arg0 -> textPourCraft(ImageCraftEpeeBois));
		ImageCraftEpeeBois.setOnMouseExited(arg0 -> textCraft.setVisible(false));
		
		
//		ImageCraftEpeePierre.setOnMouseClicked(arg0 -> joueur.crafterEpeePierre());
//		ImageCraftEpeePierre.setOnMouseEntered(arg0 -> textPourCraft(ImageCraftEpeePierre));
//		ImageCraftEpeePierre.setOnMouseExited(arg0 -> textCraft.setVisible(false));
//		
//		ImageCraftEpeeMetal.setOnMouseClicked(arg0 -> joueur.crafterEpeeMetal());
//		ImageCraftEpeeMetal.setOnMouseEntered(arg0 -> textPourCraft(ImageCraftEpeeMetal));
//		ImageCraftEpeeMetal.setOnMouseExited(arg0 -> textCraft.setVisible(false));
	}
	
	public void textPourCraft(ImageView img) {
		if (img==ImageCraftEpeeBois) {
			textCraft.setText("3 de bois pour construire une �pee en bois");
		}else if (img==ImageCraftEpeePierre) {
			textCraft.setText("3 de pierre pour construire une �pee en piere");
		}else if (img==ImageCraftEpeeMetal) {
			textCraft.setText("3 de metal pour construire une �pee en metal");
		}
		textCraft.setVisible(true);
	}
}
