package jeu.modele;

import javax.management.loading.PrivateClassLoader;
import jeu.modele.*;
import jeu.vue.VuePv;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObeservateurPv implements ChangeListener<Number> {
	

	private VuePv pv;
	private Joueur j;
	public ObeservateurPv(VuePv pv,Joueur j) {

		this.j=j;
		this.pv=pv;
		
	}
	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
		switch (j.nbCoeurProperty().getValue()) {
		case 5:
			
			pv.setImageActive(pv.getTabImage()[5]);
		
			break;
		case 4:
			pv.setImageActive(pv.getTabImage()[4]);
		
			break;
		case 3:
			pv.setImageActive(pv.getTabImage()[3]);
			
			break;
		case 2:
			pv.setImageActive(pv.getTabImage()[2]);
			
			break;
		case 1:
			
			pv.setImageActive(pv.getTabImage()[1]);
		
			break;
		case 0:
			
			pv.setImageActive(pv.getTabImage()[0]);
			
			break;
				
		default:
			break;
		} 
		
	}
	
	

		
}


