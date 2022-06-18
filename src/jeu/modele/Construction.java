package jeu.modele;

import java.util.ArrayList;

public class Construction {

	//Variables
	private int valTabDroitePlacer;
	private int valTabGauchePlacer;	
	private int valTabDroiteCasser;
	private int valTabGaucheCasser;
	private int[] map;
	private Joueur joueur;	
	private ArrayList<Integer> tuileDure ;
	private ArrayList<Integer> tuileCassable;
	private int nbTuileParLigne;
	private Environnement env;
	private int[] pvMap;

	//Construceur
	public Construction(Environnement env ) {
		this.env=env;
		this.tuileDure = new ArrayList<>();
		tuileDure.add(1);
		tuileDure.add(2);
		tuileDure.add(3);
		tuileDure.add(4);
		tuileDure.add(5);
		tuileDure.add(6);
		this.tuileCassable = new ArrayList<>();
		tuileCassable.add(4);
		tuileCassable.add(5);
		tuileCassable.add(6);

		this.map = env.getTabMap();
		this.joueur =env.getJoueur();
		this.pvMap = env.getMap().getTabPvBlock();

		this.valTabDroitePlacer = map[joueur.constructionDroitePlacer()];	
		this.valTabGauchePlacer = map[joueur.constructionGauchePlacer()];	
		this.valTabDroiteCasser = map[joueur.constructionDroiteCasser()];	
		this.valTabGaucheCasser = map[joueur.constructionGaucheCasser()];

		this.nbTuileParLigne = 20;

	}

	//	0 epeeBois
	//	1 epeePierre
	//	2 epeeMetal
	//	3 hacheBois
	//	4 hachePierre
	//	5 hacheMetal
	//	6 piocheBois
	//	7 piochePierre
	//	8 piocheMetal
	//	9 bandage
	//	10 kitDeSoin
	//	11 pistolet
	//	12 carrerVide

	
	//DROITE
	//placer
	public boolean peutPlacerDroite() {
		if(env.getnbResource() > 0  && Collision.graviter(joueur, map) && tuileDure.contains(map[joueur.constructionDroitePlacer()+nbTuileParLigne] ) && joueur.getObjetEquiper()==joueur.getMatChoisi()+13 )
			return (!tuileDure.contains(valTabDroitePlacer));
		return false;
	}
	public void placerTuileDroite() {
		map[joueur.constructionDroitePlacer()] = joueur.getMatChoisi()+4;
		pvMap[joueur.constructionDroitePlacer()] = 5;
		joueur.getEnv().getListeResource().get(joueur.getMatChoisi()).EnleverResource(1);
	}

	//casser
	public boolean peutCasserDroite() {
		//bois
		if (valTabDroiteCasser==4 && (joueur.getObjetEquiper()==3 ||joueur.getObjetEquiper()==4 || joueur.getObjetEquiper()==5 ||joueur.getObjetEquiper()==12 )) {
			System.out.println("on casse du bois");
			return tuileCassable.contains(valTabDroiteCasser);
			//pierre
		}else if (valTabDroiteCasser==5 && (joueur.getObjetEquiper()==6 ||joueur.getObjetEquiper()==7 || joueur.getObjetEquiper()==8 )) {
			System.out.println("on casse de la pierre");
			return tuileCassable.contains(valTabDroiteCasser);
			//metal
		}else if (valTabDroiteCasser==6 && (joueur.getObjetEquiper()==6 || joueur.getObjetEquiper()==7 ||joueur.getObjetEquiper()==8)) {
			System.out.println("on casse du metal");
			return tuileCassable.contains(valTabDroiteCasser);
		}
		return false;
	}
	public void casserTuileDroite() {
		System.out.println(pvMap[joueur.constructionDroiteCasser()]-1);
		if(valTabDroiteCasser==4) {
			if(joueur.getObjetEquiper() == 12) 
				casserDroite(1, "bois");
			else if(joueur.getObjetEquiper() == 3)
				casserDroite(2, "bois");
			else if(joueur.getObjetEquiper() == 4)
				casserDroite(3, "bois");
			else if(joueur.getObjetEquiper() == 5)
				casserDroite(5, "bois");
		}
		else if(valTabDroiteCasser==5) {
			if(joueur.getObjetEquiper() == 6) 
				casserDroite(2, "pierre");
			else if(joueur.getObjetEquiper() == 7)
				casserDroite(3, "pierre");
			else if(joueur.getObjetEquiper() == 8)
				casserDroite(5, "pierre");
		}
		else if(valTabDroiteCasser==6) {
			if(joueur.getObjetEquiper() == 6) 
				casserDroite(1, "metal");
			else if(joueur.getObjetEquiper() == 7)
				casserDroite(2, "metal");
			else if(joueur.getObjetEquiper() == 8)
				casserDroite(5, "metal");
		}

	}

	public void casserDroite( int degat, String resource) {
		if(pvMap[joueur.constructionDroiteCasser()] > 0) 
			pvMap[joueur.constructionDroiteCasser()] = pvMap[joueur.constructionDroiteCasser()]-degat;
		if(pvMap[joueur.constructionDroiteCasser()] <=0) {
			map[joueur.constructionDroiteCasser()] = 0;
			joueur.getEnv().AjouterResource(resource);
		}
	}
	
	//GAUCHE
	//placer
	public boolean peutPlacerGauche() {
		if(env.getnbResource() > 0 &&  Collision.graviter(joueur, map) && tuileDure.contains(map[joueur.constructionGauchePlacer()+nbTuileParLigne] )&& joueur.getObjetEquiper()==joueur.getMatChoisi()+13)
			return !tuileDure.contains(valTabGauchePlacer);
		return false;
	}
	public void placerTuileGauche() {
		map[joueur.constructionGauchePlacer()] = joueur.getMatChoisi() + 4;
		pvMap[joueur.constructionGauchePlacer()] = 5;
		joueur.getEnv().getListeResource().get(joueur.getMatChoisi()).EnleverResource(1);
	}
	
	//casser
	public boolean peutCasserGauche() {
		if (valTabGaucheCasser==4 && (joueur.getObjetEquiper()==3 ||joueur.getObjetEquiper()==4 || joueur.getObjetEquiper()==5 ||joueur.getObjetEquiper()==12 )) {
			System.out.println("on casse du bois");
			return tuileCassable.contains(valTabGaucheCasser);
		}else if (valTabGaucheCasser==5 && (joueur.getObjetEquiper()==6 ||joueur.getObjetEquiper()==7 || joueur.getObjetEquiper()==8 )) {
			System.out.println("on casse de la pierre");
			return tuileCassable.contains(valTabGaucheCasser);
		}else if (valTabGaucheCasser==6 && (joueur.getObjetEquiper()==6 || joueur.getObjetEquiper()==7 ||joueur.getObjetEquiper()==8)) {
			System.out.println("on casse du metal");
			return tuileCassable.contains(valTabGaucheCasser);
		}return false;
	}

	public void casserTuileGauche() {
		//bois
		if(valTabGaucheCasser==4) {
			if(joueur.getObjetEquiper() == 12) 
				casserGauche(1, "bois");
			else if(joueur.getObjetEquiper() == 3)
				casserGauche(2, "bois");
			else if(joueur.getObjetEquiper() == 4)
				casserGauche(3, "bois");
			else if(joueur.getObjetEquiper() == 5)
				casserGauche(5, "bois");
		}
		//pierre
		else if(valTabGaucheCasser==5) {
			if(joueur.getObjetEquiper() == 6) 
				casserGauche(2, "pierre");
			else if(joueur.getObjetEquiper() == 7)
				casserGauche(3, "pierre");
			else if(joueur.getObjetEquiper() == 8)
				casserGauche(5, "pierre");
		}
		//metal
		else if(valTabGaucheCasser==6) {
			if(joueur.getObjetEquiper() == 6) 
				casserGauche(1, "metal");
			else if(joueur.getObjetEquiper() == 7)
				casserGauche(2, "metal");
			else if(joueur.getObjetEquiper() == 8)
				casserGauche(5, "metal");
		}
	}
	public void casserGauche( int degat, String resource) {
		if(pvMap[joueur.constructionGaucheCasser()] > 0) 
			pvMap[joueur.constructionGaucheCasser()] = pvMap[joueur.constructionGaucheCasser()]-degat;
		if(pvMap[joueur.constructionGaucheCasser()] <=0) {
			map[joueur.constructionGaucheCasser()] = 0;
			joueur.getEnv().AjouterResource(resource);
		}
	}

	

	//Getters
	public int getValTabDroite() {
		return valTabDroitePlacer;
	}
	public int getValTabGauche() {
		return valTabGauchePlacer;
	}
	public int[] getMap() {
		return map;
	}
	public int getValTabDroitePlacer() {
		return valTabDroitePlacer;
	}
	public int getValTabGauchePlacer() {
		return valTabGauchePlacer;
	}
	public int getValTabDroiteCasser() {
		return valTabDroiteCasser;
	}
	public int getValTabGaucheCasser() {
		return valTabGaucheCasser;
	}
	public ArrayList<Integer> getTuileDure() {
		return tuileDure;
	}
	public ArrayList<Integer> getTuileCassable() {
		return tuileCassable;
	}
	public int getNumObjetEquipe() {
		return joueur.getObjetEquiper();
	}


	//Setters
	public void setValTabDroite(int valTabDroite) {
		this.valTabDroitePlacer = valTabDroite;
	}
	public void setValTabGauche(int valTabGauche) {
		this.valTabGauchePlacer = valTabGauche;
	}
	public void setMap(int[] map) {
		this.map = map;
	}
}