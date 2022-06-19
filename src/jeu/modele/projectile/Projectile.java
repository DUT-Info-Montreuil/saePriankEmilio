package jeu.modele.projectile;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import jeu.modele.Environnement;

public class Projectile {
	private IntegerProperty xProperty,yProperty;
	private int vitesses;
	private int direction;
	private BooleanProperty finiProperty;
	private  int xarriver;
	public static int compteur=1;
	private String id;
	private Environnement env;
	public Projectile(Environnement env,int x, int y,int vitesses,int direction) {
		this.env=env;
		this.xProperty=new SimpleIntegerProperty(x);
		this.yProperty=new SimpleIntegerProperty(y);
		this.finiProperty=new SimpleBooleanProperty(false);
		this.direction=direction;
		this.vitesses=vitesses;
		this.id="P"+compteur;
		if (direction==1) {
			this.xarriver=xProperty.getValue()+150;
		}else 
			this.xarriver=xProperty.getValue()-150;
	compteur++;
	}


	public void allerAdroite() {
	System.out.println("x:"+getX()+"y:"+getY());
		int npos = getX()+this.vitesses;
			this.xProperty.setValue(npos);
	}
	
	public void allerAGauche() {

		int npos = getX()-this.vitesses;
			this.xProperty.setValue(npos);
	}

	public IntegerProperty getxProperty() {
		return xProperty;
	}

	public IntegerProperty getyProperty() {
		return yProperty;
	}

	public int getX() {
		return xProperty.getValue();
	}

	public int getY() {
		return yProperty.getValue();
	}

	public int getVitesses() {
		return vitesses;
	}
	public String getId() {
		return id;
	}
	public int getXarriver() {
		return xarriver;
	}

	public boolean getFini() {
		return finiProperty.getValue();
	}
	
	public final BooleanProperty getFiniProperty() {
		return finiProperty;
	}
	
	public int getDirection() {
		return direction;
	}





	public void toucher() {
		this.finiProperty.setValue(true);;
	}
	
	public String toString() {
		return this.id;
	}


	public Environnement getEnv() {
		return env;
	}
}
