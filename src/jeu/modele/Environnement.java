package jeu.modele;

import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jeu.modele.projectile.Projectile;
import jeu.modele.resource.Resource;

public class Environnement {
	private Joueur joueur;
	private Resource bois,pierre,metal;
	private Map mape;
	private ArrayList<Resource> listeResource;
	private ObservableList<Ennemi> listeEnnemi;
	private ObservableList<Projectile> listeProjectile;
	private Timeline gameloop;

	ImageView imballe = new ImageView(new Image("jeu/modele/image/personnage/neutre.png"));
	Ennemi ennemi;
	public Environnement(Timeline gameloop) {
		this.gameloop=gameloop;
		
		this.listeEnnemi= FXCollections.observableArrayList();
		this.listeProjectile= FXCollections.observableArrayList();
		this.joueur=new Joueur(this);
		this.mape = new Map();
		
		bois=new Resource();
		pierre = new Resource();
		metal=new Resource();
		
		listeResource= new ArrayList<>();
		listeResource.add(bois);
		listeResource.add(pierre);
		listeResource.add(metal);
	
	}
	
	
	public void arreterLeJeu() {
		gameloop.stop();
	}
	
	
	
	//collision
	public boolean collisionGauche() {
		return Collision.collisionGauche(joueur,getTabMap());
	}
	public boolean collisionDroite() {
		return Collision.collisionDroite(joueur,getTabMap());
	}
	public boolean collisionHaut() {
		return Collision.collisionHaut(joueur,getTabMap());
	}
	public boolean graviter() {
		return Collision.graviter(joueur,getTabMap());
	}
	
	//map
	public int[] getTabMap() {
		return mape.getTab();
	}
	
	
	// resoruce bois,pierre,metal
	
	public Resource getResource(String matiere) {
		if (matiere.equals("bois")) 
			return this.bois;
		else if (matiere.equals("pierre")) 
			return this.pierre;
		else if (matiere.equals("metal")) 
			return this.metal;
		return null;
	}
	
	public ArrayList<Resource> getListeResource() {
		return listeResource;
	}
	public final IntegerProperty getNbResourceProperty(String matiere){
		if (matiere.equals("bois")) 
			return this.bois.getNbResourceProperty();
		else if (matiere.equals("pierre")) 
			return this.pierre.getNbResourceProperty();
		else if (matiere.equals("metal")) 
			 return this.metal.getNbResourceProperty();
		return null;
	}

	public int getNbResource(String matiere) {
		if (matiere.equals("bois")) 
			return bois.getNbResourceProperty().intValue();
		else if (matiere.equals("pierre")) 
			return pierre.getNbResourceProperty().intValue();
		else if (matiere.equals("metal")) 
			return metal.getNbResourceProperty().intValue();
		return 0;
		
	}
	public void AjouterResource(String matiere) {
		if (matiere.equals("bois")) 
			bois.ajouterResource();
		else if (matiere.equals("pierre")) 
			pierre.ajouterResource();
		else if (matiere.equals("metal")) 
			metal.ajouterResource();
	}
	
	public void EnleverResource(String matiere,int i) {
		if (matiere.equals("bois")) 
			bois.EnleverResource(i);
		else if (matiere.equals("pierre")) 
			pierre.EnleverResource(i);
		else if (matiere.equals("metal")) 
			metal.EnleverResource(i);
		
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public ObservableList<Ennemi> getListeEnnemi() {
		return listeEnnemi;
	}
	public ObservableList<Projectile> getListeProjectile() {
		return listeProjectile;
	}

	public void ajouter(Ennemi e) {
		this.listeEnnemi.add(e);
	}

	public void ajouterProjectile(Projectile e) {
		this.listeProjectile.add(e);
	}
	
	public void agit() {
		
//		System.out.println(listeProjectile.toString());
		
			for (int i = 0; i < listeProjectile.size(); i++) {
				Projectile projectile = listeProjectile.get(i);
				
				switch (projectile.getDirection()) {
				case 1:
					if (Collision.collisionBalleDroite(projectile.getxProperty().get(), projectile.getyProperty().get(), getTabMap())) {
						projectile.toucher();
						System.out.println("mur");
					}
					for(int j=listeEnnemi.size()-1; j>=0;j--){
						Ennemi ennemi = listeEnnemi.get(j);
						
						if((projectile.getX()>ennemi.getX() || projectile.getX()==ennemi.getX())&&projectile.getY()==ennemi.getY()) {
							ennemi.perdrePv(1);
							projectile.toucher();
						}
						
					}
						
					if (projectile.getFini()) {
						System.out.println("finito");
						listeProjectile.remove(projectile);
					}
					else if (projectile.getX()<projectile.getXarriver()) {
						projectile.allerAdroite();
					}else {
						listeProjectile.remove(projectile);
					}
		
					break;
					
				case 2:
					if (Collision.collisionBalleGauche(projectile.getxProperty().get(), projectile.getyProperty().get(), getTabMap())) {
						projectile.toucher();
						System.out.println("mur");
					}
					for(int j=listeEnnemi.size()-1; j>=0;j--){
						Ennemi a = listeEnnemi.get(j);
						if((projectile.getX()<(a.getX()+40) || projectile.getX()==a.getX())&&projectile.getY()==a.getY()) {
							a.perdrePv(1);
							projectile.toucher();
						}
					}
						
					if (projectile.getFini()) {
						System.out.println("finito");
						listeProjectile.remove(projectile);
					}
					else if (projectile.getX()>projectile.getXarriver()) {
						projectile.allerAGauche();
					}else {
						listeProjectile.remove(projectile);
					}
		
					break;

				default:
					break;
				}
			}
				
		//graviter du joueur
		if(!Collision.graviter(this.joueur,getTabMap())&& !this.joueur.getSaute() || this.joueur.getNbSaut()==6 || Collision.collisionHaut(this.joueur,getTabMap()) &&this.joueur.getSaute() ) 
			this.joueur.tomber();
		if(Collision.graviter(this.joueur,getTabMap())) 
			this.joueur.setNbSaut(0);
		
		//gestion des déplacements des ennemi
		for (int i = 0; i < listeEnnemi.size(); i++) {
			ennemi=listeEnnemi.get(i);
			if(getJoueur().getX() < this.ennemi.getX() ) {
				ennemi.setGauche(true);
				ennemi.setDroite(false);
				ennemi.setDirection(2);
				if (!Collision.collisionGauche(ennemi,getTabMap())) 
					this.ennemi.allerAGauche();
			}
			
			else if(getJoueur().getX() > this.ennemi.getX()) {
				ennemi.setDroite(true);
				ennemi.setGauche(false);
				ennemi.setDirection(1);
				if(!Collision.collisionDroite(ennemi,getTabMap()))
					this.ennemi.allerADroite();
				
			}
			else {
				ennemi.setDroite(false);
				ennemi.setGauche(false);
			}
			//graviter des ennemi
			if(Collision.collisionDroiteEnnemi(ennemi,getTabMap()) && ennemi.isDroite()  || Collision.collisionGaucheEnnemi(ennemi,getTabMap()) && ennemi.isGauche() ) { 
				ennemi.sauter();
				ennemi.setDirection(3);
			
			}else if(!Collision.graviter(ennemi,getTabMap()) || Collision.collisionHaut(ennemi,getTabMap()) ) 
				ennemi.tomber();	
				
		}
		//gestion des ennemi mort et l'attaque du joueur
		for(int i=listeEnnemi.size()-1; i>=0;i--){
			
			Ennemi a = listeEnnemi.get(i);
			if(a.getPv()==0){
				listeEnnemi.remove(i);
			}
			if(a.getX()==getJoueur().getX()) {
				getJoueur().blesser();
			}
		}

	}
	
}