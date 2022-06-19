package jeu.modele;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import jeu.vue.VueBouclier;

public class ObservateurBouclier implements ChangeListener<Number>  {
	
	private VueBouclier bouclier;
	private Joueur j;
	
	public ObservateurBouclier(VueBouclier bouclier,Joueur j) {
		this.j=j;
		this.bouclier=bouclier;	
	}
	
	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
		switch (j.getNbBouclierProperty().getValue()) {
		case 0:
			bouclier.imageSetVisible(false);
			break;
		case 1:
			bouclier.setImageActive(bouclier.getTabImage()[1]);
			bouclier.imageSetVisible(true);
			break;
		case 2:
			bouclier.setImageActive(bouclier.getTabImage()[2]);			
			break;
		case 3 :
			bouclier.setImageActive(bouclier.getTabImage()[3]);	
		default :
			break;
		} 		
	}	
}
