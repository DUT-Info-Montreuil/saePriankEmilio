package jeu.modele;

import java.util.ArrayList;

public class Collision {

	public static boolean collisionDroite(Personnage personnage,int[] tabMap){
		int xtile;
		int ytile,ytile2,ytile3;
		int ValeurTile,ValeurTil2, ValeurTil3;
		
		xtile=(personnage.getX()+41)/40;
		ytile=(personnage.getY()+20)/40;
		ytile3=(personnage.getY()+39)/40;
		ytile2=personnage.getY()/40;
		ValeurTile=tabMap[(xtile+(ytile*20))];
		ValeurTil2=tabMap[(xtile+(ytile2*20))];
		ValeurTil3=tabMap[(xtile+(ytile3*20))];
		if (ValeurTile!=0 && ValeurTile!=7 || ValeurTil2!=0 && ValeurTil2!=7  || ValeurTil3!=0 && ValeurTil3!=7  ) {
			return true;
		}
		return false;
	}
	
	public static boolean collisionGauche(Personnage personnage,int[] tabMap){
		int xtile;
		int ytile,ytile2,ytile3;
		int ValeurTile,ValeurTil2, ValeurTil3;
		
		xtile=(personnage.getX()-1)/40;
		ytile=personnage.getY()/40;
		ytile2=(personnage.getY()+20)/40;
		ytile3=(personnage.getY()+39)/40;
		ValeurTile=tabMap[(xtile+(ytile*20))];
		ValeurTil2=tabMap[(xtile+(ytile2*20))];
		ValeurTil3=tabMap[(xtile+(ytile3*20))];
		if(ValeurTile!=0 && ValeurTile!=7 || ValeurTil2!=0 && ValeurTil2!=7  || ValeurTil3!=0 && ValeurTil3!=7  ) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	public static boolean collisionHaut(Personnage personnage,int[] tabMap){
		int xtile,xtile2,xtile3;
		int ytile;
		int ValeurTile,ValeurTil2, ValeurTil3;
		
		xtile=(personnage.getX()+20)/40;
		xtile2=(personnage.getX())/40;
		xtile3=(personnage.getX()+39)/40;
		ytile=(personnage.getY()-1)/40;
		ValeurTile=tabMap[(xtile+(ytile*20))];
		ValeurTil2=tabMap[(xtile2+(ytile*20))];
		ValeurTil3=tabMap[(xtile3+(ytile*20))];
		if (ValeurTile!=0 && ValeurTile!=7 || ValeurTil2!=0 && ValeurTil2!=7  || ValeurTil3!=0 && ValeurTil3!=7  ) {
			return true;
		}
		return false;
	}
	
	
	public static boolean graviter(Personnage personnage,int[] tabMap) {
		int xtile,xtile2,xtile3;
		int ytile;
		int ValeurTile,ValeurTil2, ValeurTil3;
		
		xtile=personnage.getX()/40;
		xtile2=(personnage.getX()+20)/40;
		xtile3=(personnage.getX()+39)/40;
		ytile=(personnage.getY()+40)/40;
		ValeurTile=tabMap[(xtile+(ytile*20))];
		ValeurTil2=tabMap[(xtile2+(ytile*20))];
		ValeurTil3=tabMap[(xtile3+(ytile*20))];
		if (ValeurTile!=0 && ValeurTile!=7 || ValeurTil2!=0 && ValeurTil2!=7  || ValeurTil3!=0 && ValeurTil3!=7  ) {
			return true;
		}
		return false;
	}
	public static boolean collisionDroiteEnnemi(Personnage personnage,int[] tabMap){
		int xtile;
		int ytile,ytile2,ytile3;
		int ValeurTile,ValeurTil2, ValeurTil3;		
		ArrayList<Integer> tuileSauter =  new ArrayList<>();
		tuileSauter.add(0);
		tuileSauter.add(4);
		tuileSauter.add(5);
		tuileSauter.add(6);
		tuileSauter.add(7);	
		
		xtile=(personnage.getX()+41)/40;
		ytile=(personnage.getY()+20)/40;
		ytile3=(personnage.getY()+39)/40;
		ytile2=personnage.getY()/40;
		ValeurTile=tabMap[(xtile+(ytile*20))];
		ValeurTil2=tabMap[(xtile+(ytile2*20))];
		ValeurTil3=tabMap[(xtile+(ytile3*20))];
		if (!tuileSauter.contains(ValeurTile)  || !tuileSauter.contains(ValeurTil2)  || !tuileSauter.contains(ValeurTil3) ) {
			return true;
		}
		return false;
	}
	
	public static boolean collisionGaucheEnnemi(Personnage personnage,int[] tabMap){
		int xtile;
		int ytile,ytile2,ytile3;
		int ValeurTile,ValeurTil2, ValeurTil3;
		ArrayList<Integer> tuileSauter =  new ArrayList<>();
		tuileSauter.add(0);
		tuileSauter.add(4);
		tuileSauter.add(5);
		tuileSauter.add(6);
		tuileSauter.add(7);
		
		xtile=(personnage.getX()-1)/40;
		ytile=personnage.getY()/40;
		ytile2=(personnage.getY()+20)/40;
		ytile3=(personnage.getY()+39)/40;
		ValeurTile=tabMap[(xtile+(ytile*20))];
		ValeurTil2=tabMap[(xtile+(ytile2*20))];
		ValeurTil3=tabMap[(xtile+(ytile3*20))];
		if(!tuileSauter.contains(ValeurTile)  || !tuileSauter.contains(ValeurTil2)  || !tuileSauter.contains(ValeurTil3) ) {
			return true;
		}
		return false;
	}

	public static boolean collisionBalleDroite(int x,int y,int[] tabMap){
		int xtile;
		int ytile;
		int ValeurTile;
		
		xtile=(x+40)/40;
		ytile=(y+20)/40;
	
		ValeurTile=tabMap[(xtile+(ytile*20))];
		if (ValeurTile!=0 && ValeurTile!=7 ) {
			return true;
		}
		return false;
	}
	public static boolean collisionBalleGauche(int x,int y,int[] tabMap){
		int xtile;
		int ytile;
		int ValeurTile;
		
		xtile=(x-1)/40;
		ytile=y/40;
		
		ValeurTile=tabMap[(xtile+(ytile*20))];
	
		if(ValeurTile!=0 && ValeurTile!=7 ) {
			return true;
		}
		return false;
	}
}
