package jeu.modele;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jeu.modele.projectile.Projectile;
import jeu.modele.projectile.ProjectileEnnemi;
import jeu.modele.resource.Resource;

public class Environnement {
	private Joueur joueur;
	private Resource bois,pierre,metal;
	private Map mape;
	private ArrayList<Resource> listeResource;
	private ObservableList<Ennemi> listeEnnemi;
	private ObservableList<Projectile> listeProjectile;
	private ObservableList<ProjectileEnnemi> listeProjectileEnnemi;
	private Timeline gameloop;
	private IntegerProperty nummeroMancheProperty;
	private IntegerProperty nbEnnemiProperty;
	private Ennemi ennemi;
	private boolean toucher;
	private Timer chrono;
	private int nbTimer;
	private int tempsPourmanche=0;
	private boolean mancheLancer;
	private boolean ennemiMort;
	public Environnement(Timeline gameloop) {
		this.gameloop=gameloop;
		this.listeEnnemi= FXCollections.observableArrayList();
		this.listeProjectile= FXCollections.observableArrayList();
		this.listeProjectileEnnemi= FXCollections.observableArrayList();
		this.joueur=new Joueur(this);
		this.mape = new Map();
		this.nummeroMancheProperty=new SimpleIntegerProperty(0);
		this.nbEnnemiProperty=new SimpleIntegerProperty(0);

		bois=new Resource();
		pierre = new Resource();
		metal=new Resource();

		listeResource= new ArrayList<>();
		listeResource.add(bois);
		listeResource.add(pierre);
		listeResource.add(metal);
		this.nbTimer = 0;



	}
	
	
	
	
	//getter
		public Map getMap() {
			return mape;
		}
		public int getnbResource() {
			return listeResource.get(joueur.getMatChoisi()).getNbResourceProperty().getValue();
		}
		public int getTempsPourmanche() {
			return tempsPourmanche;
		}
		public boolean isMancheLancer() {
			return mancheLancer;
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
		
		public ObservableList<ProjectileEnnemi> getListeProjectileEnnemi() {
			return listeProjectileEnnemi;
		}
		public final IntegerProperty getNbEnnemiProperty() {
			return nbEnnemiProperty;
		}
		
		
		
		
		public Ennemi getEnnemi() {
			return listeEnnemi.get(getnbResource());
		}
		public final IntegerProperty getNummeroMancheProperty() {
			return nummeroMancheProperty;
		}
		public int[] getTabMap() {
			return mape.getTab();
		}
		//getter resource
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

	//setter
		public void setFalsemancheLancer() {
			this.mancheLancer=false;
		}
	
	

	public void lancerManche() {
		Timer chronom=new Timer();
		chronom.schedule(new TimerTask() {
			@Override
			public void run() {
				tempsPourmanche=30;
				mancheLancer=true;
			}
		}, 5000);
	}

	public void enleverUnEnnemiAucompteur() {
		this.nbEnnemiProperty.setValue(this.nbEnnemiProperty.getValue()-1);
	}
	public void ajouterUnEnnemiAucompteur() {
		this.nbEnnemiProperty.setValue(this.nbEnnemiProperty.getValue()+1);
	}

	public void arreterLeJeu() {
		gameloop.stop();
	}

	public void ajtmanche() {
		this.nummeroMancheProperty.setValue(nummeroMancheProperty.getValue()+1);
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
	
	
	public void ajouterEnnemi(Ennemi e) {
		this.listeEnnemi.add(e);
	}

	public void ajouterTroisEnnemis() {
		//this.listeEnnemi.add(new Ennemi(140,this));
		this.listeEnnemi.add(new Sorcier(220,this));
		//this.listeEnnemi.add(new Ennemi(280,this));

	}

	
	public void ajouterProjectile(Projectile e) {
		this.listeProjectile.add(e);
	}
	public void ajouterProjectileEnnemie(ProjectileEnnemi e) {
		this.listeProjectileEnnemi.add(e);
	}

	public void agit() {

		for (int i = 0; i < listeEnnemi.size(); i++) {
			ennemi=listeEnnemi.get(i);
			//deplacement et attaque ennemie sorcier
			if (ennemi instanceof Sorcier) {
				if(getJoueur().getX()+350 < this.ennemi.getX()  ) {
					ennemi.setGauche(true);
					ennemi.setDroite(false);
					ennemi.setDirection(2);
					if (!Collision.collisionGauche(ennemi,getTabMap()) && !Collision.collisionEnnemiGauche(ennemi, listeEnnemi)) 
						this.ennemi.allerAGauche();
				}
				else if(getJoueur().getX() > this.ennemi.getX()+350  ) {
					ennemi.setDroite(true);
					ennemi.setGauche(false);
					ennemi.setDirection(1);
					if(!Collision.collisionDroite(ennemi,getTabMap()) && !Collision.collisionEnnemiDroite(ennemi, listeEnnemi))
						this.ennemi.allerADroite();
				}
				else {
					ennemi.setDroite(false);
					ennemi.setGauche(false);
				}
				if (!((Sorcier) ennemi).getATirerProperty().get()) {
					((Sorcier) ennemi).tirrer();
				((Sorcier) ennemi).setaTirer(true);
			}
			
			}else {
				// deplacement et attaque ennemi normal
				if(getJoueur().getX()+40 < this.ennemi.getX()  ) {
					ennemi.setGauche(true);
					ennemi.setDroite(false);
					ennemi.setDirection(2);
					if (!Collision.collisionGauche(ennemi,getTabMap()) && !Collision.collisionEnnemiGauche(ennemi, listeEnnemi)) 
						this.ennemi.allerAGauche();
				}
				else if(getJoueur().getX() > this.ennemi.getX()+40  ) {
					ennemi.setDroite(true);
					ennemi.setGauche(false);
					ennemi.setDirection(1);
					if(!Collision.collisionDroite(ennemi,getTabMap()) && !Collision.collisionEnnemiDroite(ennemi, listeEnnemi))
						this.ennemi.allerADroite();
				}
				else {
					ennemi.setDroite(false);
					ennemi.setGauche(false);
				}
				if(ennemi.getY()==joueur.getY() &&((ennemi.getX()>=joueur.getX() && ennemi.getX()<=joueur.getX()+40)||(ennemi.getX()<=joueur.getX() && ennemi.getX()>=joueur.getX()-40))) {
					nbTimer++;
					if(nbTimer ==1) {
						chrono = new Timer();
						ennemiMort = false;
						chrono.schedule(new TimerTask() {
							int temp = 5;
							@Override
							public void run() {
								
								//if(ennemi.getY()==joueur.getY() &&((ennemi.getX()>=joueur.getX() && ennemi.getX()<=joueur.getX()+40)||(ennemi.getX()<=joueur.getX() && ennemi.getX()>=joueur.getX()-40))) {
								if(Collision.collisionEnnemiDroite(joueur, listeEnnemi) || Collision.collisionEnnemiGauche(joueur, listeEnnemi) ) {
									temp--;
									System.out.println(temp);
								}
								else {
									toucher = false;
									cancel();
									nbTimer = 0;
								}
								if(ennemi.isMort()) {
									toucher = false;
									ennemiMort = true;
									cancel();
									nbTimer = 0;
								}
								if (temp==0 ) {
									toucher = true;
									cancel();
									nbTimer = -1;
								}
							}
						}, 0,100);
					}
					if(toucher && nbTimer==0 && !ennemiMort) {
						getJoueur().blesser();
					}
				}
			}	
			
			//graviter des ennemi
			if(Collision.collisionDroiteEnnemi(ennemi,getTabMap()) && ennemi.isDroite()  || Collision.collisionGaucheEnnemi(ennemi,getTabMap()) && ennemi.isGauche() ) { 
				ennemi.sauter();
				ennemi.setDirection(3);
			}else if(!Collision.graviter(ennemi,getTabMap()) || Collision.collisionHaut(ennemi,getTabMap()) ) 
				ennemi.tomber();	
		
			
		}
	
		//projectile joueur
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
				else if (projectile.getX()<projectile.getXarriver()) 
					projectile.allerAdroite();
				else 
					listeProjectile.remove(projectile);
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
				else if (projectile.getX()>projectile.getXarriver())
					projectile.allerAGauche();
				else 
					listeProjectile.remove(projectile);
				break;
			default:
				break;
			}
		}
		
		//projectile ennemi
		for (int i = 0; i < listeProjectileEnnemi.size(); i++) {
			
				ProjectileEnnemi projectile = listeProjectileEnnemi.get(i);
				Sorcier ennemiSorcier=projectile.getEnnemi();
			switch (projectile.getDirection()) {
			
			//Projectile tirer a droite
			case 1:
				if (Collision.collisionBalleDroite(projectile.getxProperty().get(), projectile.getyProperty().get(), getTabMap())) {
					projectile.toucher();
					System.out.println("mur");
				}
				if(((projectile.getX()>joueur.getX() && projectile.getX()<joueur.getX()+8) || projectile.getX()==joueur.getX())&&projectile.getY()==joueur.getY()) {
						System.out.println("on blesse");
						joueur.blesser();
						projectile.toucher();
				}
				if (projectile.getFini()) {
					System.out.println("finito ennemi");
					listeProjectileEnnemi.remove(projectile);
					ennemiSorcier.setaTirer(false);
					
				}
				else if (projectile.getX()<projectile.getXarriver()) 
					projectile.allerAdroite();
				
				else {
					listeProjectileEnnemi.remove(projectile);
					ennemiSorcier.setaTirer(false);
				}
				break;
				//Projectile tirer a gauche
			case 2:
				if (Collision.collisionBalleDroite(projectile.getxProperty().get(), projectile.getyProperty().get(), getTabMap())) {
					projectile.toucher();
					System.out.println("mur");
				}
				if(((projectile.getX()<(joueur.getX())&& (projectile.getX()>joueur.getX()-10) || projectile.getX()==joueur.getX())&&projectile.getY()==joueur.getY())) {
						joueur.blesser();
						projectile.toucher();
						
					}
				if (projectile.getFini()) {
					System.out.println("finito ennemi");
					listeProjectileEnnemi.remove(projectile);
					ennemiSorcier.setaTirer(false);
				}
				else if (projectile.getX()>projectile.getXarriver())
					projectile.allerAGauche();
				else {
					listeProjectileEnnemi.remove(projectile);
					ennemiSorcier.setaTirer(false);
				}
				break;
			default:
				break;
				
			}
			
			
		}
		
		listeProjectileEnnemi.removeAll();
		
		//graviter du joueur
		if(!Collision.graviter(this.joueur,getTabMap())&& !this.joueur.getSaute() || this.joueur.getNbSaut()==6 || Collision.collisionHaut(this.joueur,getTabMap()) &&this.joueur.getSaute() ) 
			this.joueur.tomber();
		if(Collision.graviter(this.joueur,getTabMap())) 
			this.joueur.setNbSaut(0);
		
	}
	
}