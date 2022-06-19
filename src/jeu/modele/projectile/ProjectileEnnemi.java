package jeu.modele.projectile;

import jeu.modele.Environnement;
import jeu.modele.Sorcier;

public class ProjectileEnnemi extends Projectile{

	private Sorcier sorcier;
	
	public ProjectileEnnemi(Environnement env, int x, int y, int vitesses, int direction,Sorcier ennemi) {
		super(env, x, y, vitesses, direction);
		this.sorcier=ennemi;
	}
	
	public Sorcier getEnnemi() {
		return sorcier;
	}

}