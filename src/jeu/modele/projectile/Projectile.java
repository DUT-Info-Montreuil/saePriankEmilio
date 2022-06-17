package jeu.modele.projectile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import jeu.modele.Environnement;

public class Projectile {
	private IntegerProperty xProperty,yProperty;
	private int vitesses;
	private int direction;
	private boolean fini=false;
	private  int xarriver;
	public static int compteur=1;
	private String id;
	
	public Projectile(Environnement env,int x, int y,int vitesses,int direction) {
		this.xProperty=new SimpleIntegerProperty(x);
		this.yProperty=new SimpleIntegerProperty(y);
		this.direction=direction;
		this.vitesses=vitesses;
		this.id="P"+compteur;
		if (direction==1) {
			this.xarriver=xProperty.getValue()+500;
		}else 
			this.xarriver=xProperty.getValue()-500;
	
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
		return fini;
	}
	
	public int getDirection() {
		return direction;
	}






	public void toucher() {
		this.fini=true;
	}
	
	public String toString() {
		return this.id;
	}
}
