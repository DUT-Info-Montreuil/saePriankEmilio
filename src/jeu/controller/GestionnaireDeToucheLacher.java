package jeu.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import jeu.modele.Joueur;

public class GestionnaireDeToucheLacher implements EventHandler<KeyEvent>{
	
	private Pane root;
	private Joueur joueur;

	public GestionnaireDeToucheLacher(Pane root,Joueur joueur) {
		this.root=root;
		this.joueur=joueur;
	}
	
	@Override
	public void handle(KeyEvent arg0) {
		root.setOnKeyReleased(ev -> {
			switch(ev.getCode()){
			case Q :
				this.joueur.setGauche(false);
				break;
			case D :
				this.joueur.setDroite(false);
				break;
			case Z :
				this.joueur.setSaute(false);
				break;
			default :
				break;
			}
		});
	}

}
