package jeu.modele.projectile;

import jeu.modele.Ennemi;
import jeu.modele.Environnement;
import jeu.modele.Sorcier;

public class ProjectileEnnemi extends Projectile{

	private Sorcier ennemi;
	public ProjectileEnnemi(Environnement env, int x, int y, int vitesses, int direction,Sorcier ennemi) {
		super(env, x, y, vitesses, direction);
		this.ennemi=ennemi;
	}
	public Sorcier getEnnemi() {
		return ennemi;
	}

}
