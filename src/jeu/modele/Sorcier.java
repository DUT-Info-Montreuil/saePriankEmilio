package jeu.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import jeu.modele.projectile.Projectile;
import jeu.modele.projectile.ProjectileEnnemi;

public class Sorcier extends Ennemi{

	private BooleanProperty aTirerProperty;
	
	public Sorcier(int i,Environnement env) {
		super(i,env);
		this.aTirerProperty = new SimpleBooleanProperty(false);
	}
	
	public void tirrer() {
		
			if (getX()>getEnv().getJoueur().getX()) {
				getEnv().ajouterProjectileEnnemie(new ProjectileEnnemi(getEnv(), getX(), getY(), 8, 2,this));
				
			}else
				getEnv().ajouterProjectileEnnemie(new ProjectileEnnemi(getEnv(), getX(), getY(), 8, 1,this));
			
			
	}

	public final BooleanProperty getATirerProperty() {
		return aTirerProperty;
	}

	public void setaTirer(boolean aTirer) {
		this.aTirerProperty.set(aTirer);
	}

	

}
