package jeu.modele;

import java.util.ArrayList;

public class Map {

	//0 ciel
	//1 terre
	//2 sol avec herbes
	//3 obsidennes
	//4 bois
	//5 pierre
	//6 metal
	//7 haut de la map
	
	private int[] carte1= {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,		
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,//160
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,
		 0,0,0,0,0,0,0,0,0,0,0,0,4,0,2,2,2,0,4,0,
		 2,2,2,2,0,0,0,2,2,2,2,2,2,2,1,1,1,2,2,2,
		 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
		 3,3,3,1,1,1,1,3,1,1,1,3,3,3,3,3,3,3,3,1,
		 3,3,3,3,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,1,
		 3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
	
	private int[] tabPvBlock = new int[carte1.length];  
	
	public Map () {
		pvBlock(carte1);
	}
	
	public void pvBlock(int [] carte) {
		for(int i=0 ; i < carte.length-1 ; i++) {
			if(carte[i] == 4 || carte[i] == 5 || carte[i] == 6)
				tabPvBlock[i] = 5;
			else
				tabPvBlock[i] = 0;
		}
	}
	
	public void changementMap(int i, int resource) {
		carte1[i] = resource;
		tabPvBlock[i] = 5;
	}

	
	
	
	//getter
	public int[] getTabPvBlock() {
		return tabPvBlock;
	}
	public int getPvBlock(int valTab) {
		return tabPvBlock[valTab];
	}
	public int[] getTab () {
		return this.carte1;
	}
}