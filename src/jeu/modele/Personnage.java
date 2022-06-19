package jeu.modele;

public abstract class Personnage {

	public Personnage() {	
	}	

	public abstract void allerAGauche();
	
	public abstract void allerADroite();
	
	public abstract void sauter();
	
	public abstract void tomber();
	
	public abstract int getX();
	
	public abstract int getY();
	
	public abstract String getId();
	
}
