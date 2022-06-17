package jeu.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import jeu.modele.inventaire.Inventaire;
import jeu.modele.projectile.Projectile;


public class Joueur extends Personnage{
	//variables//
	private IntegerProperty x,y; //position du joueur
	private IntegerProperty nbCoeursProperty; // nombre de coeur du perso (0 a 5)
	private int nbSaut; //nb saut 
	private int vitesse; //vitesse de deplacement du perso
	private BooleanProperty droiteProperty,gaucheProperty,sauteProperty; //vraie si il se deplace ou saute
	
	private Inventaire inventaireObjet =new Inventaire();

	private IntegerProperty nbBandageProperty, nbKitdeSoinProperty; //nombre de bandage et de kit de soin
	private IntegerProperty nbBouclierProperty; // nombre bouclier du perso (0 a 3)
	private IntegerProperty ObjetEquiperProperty;// numero de l'objet equiper 0 a 15
	private int matChoisi;
	private int caseChoisi;
	protected Environnement env;
	private BooleanProperty directionProperty; //true:droite     false:gauche
	private int xtile;
	private int ytile;	
	
	//CONSTRUCTEUR//
	public Joueur(Environnement env) {
		this.env=env;
		this.nbCoeursProperty=new SimpleIntegerProperty(5);
		this.nbBouclierProperty = new SimpleIntegerProperty(0);
		this.x = new SimpleIntegerProperty(40);
		this.y = new SimpleIntegerProperty(360);
		this.vitesse = 8;
		this.nbBandageProperty=new SimpleIntegerProperty(0);
		this.nbKitdeSoinProperty=new SimpleIntegerProperty(0);
		this.ObjetEquiperProperty=new SimpleIntegerProperty(12);
		
		this.droiteProperty=new  SimpleBooleanProperty();
		this.gaucheProperty=new  SimpleBooleanProperty();
		this.sauteProperty=new  SimpleBooleanProperty();
		
		this.directionProperty=new SimpleBooleanProperty();
		
		this.nbSaut = 0;
		this.matChoisi = 0;
	}
	
	
	public void attaquer() {
		
		for(int i=0;i< env.getListeEnnemi().size();i++){
			Ennemi m=env.getListeEnnemi().get(i);
				if(m.getY()==getY() &&((m.getX()>getX() && m.getX()<getX()+40)||(m.getX()<getX() && m.getX()>getX()-40))){
					System.out.println("jataqqquueee");
					if(getObjetEquiper()==12)
						m.perdrePv(1);
					else if(getObjetEquiper()==0)
						m.perdrePv(2);
					else if(getObjetEquiper()==1)
						m.perdrePv(3);
					else if(getObjetEquiper()==2) 
						m.perdrePv(5);
				}
		}

	}
	
	public void tirer() {
		int directionBalle;
		if(directionProperty.getValue()) {
			directionBalle=1;
		}else {
			directionBalle=2;
		}
		env.ajouterProjectile(new Projectile(env, getX(), getY(), 15, directionBalle));
		
	}

	//CONSTRUCION
	public int constructionDroitePlacer() {
		this.xtile = ((this.getX()+79)/40);
		this.ytile = ((this.getY()+20)/40);
		return (xtile+(ytile*20));
	}
	public int constructionGauchePlacer() {
		this.xtile = ((this.getX()-40)/40);
		this.ytile = ((this.getY()+20)/40);
		return(xtile+(ytile*20));
	}
	public int constructionDroiteCasser() {
		this.xtile = ((this.getX()+40)/40);
		this.ytile = ((this.getY()+20)/40);
		return (xtile+(ytile*20));
	}
	public int constructionGaucheCasser() {
		this.xtile = ((this.getX()-1)/40);
		this.ytile = ((this.getY()+20)/40);
		return (xtile+(ytile*20));
	}

	/////////////Methodes de gestion des PV ///////////////
	@Override
	public void perdrePv() {
		int nbCoeur =this.getNbCoeurs()-1;
		if(getNbCoeurs()>=1) 
			this.nbCoeursProperty.setValue(nbCoeur);
	}
	public void gagnerPv() {
		int nbCoeur =this.getNbCoeurs()+1;
		if(getNbCoeurs()<5)
			this.nbCoeursProperty.setValue(nbCoeur);
	}
	
	/////////////Methodes de gestion de Bouclier ///////////////
	public void perdreBouclier() {
		int nbBouclier = this.getNbBouclier()-1;
		if(getNbBouclier()>=1) 
			this.nbBouclierProperty.setValue(nbBouclier);
	}

	public void gagnerBouclier() {
		int nbBouclier = this.getNbBouclier()+1;
		if(getNbBouclier()<3) 
			this.nbBouclierProperty.setValue(nbBouclier);
	}
	
	public void blesser() {
		if(getNbBouclier()>0)
			perdreBouclier();
		else
			perdrePv();
	}
	
	///////////// Les get Property /////////////
	public final IntegerProperty xProperty(){
		return this.x;
	}
	public final IntegerProperty yProperty(){
		return this.y;
	}
	public final IntegerProperty nbCoeurProperty() {
		return this.nbCoeursProperty;
	}
	public final IntegerProperty getNbBouclierProperty() {
		return this.nbBouclierProperty;
	}
	
	public final IntegerProperty getObjetEquiperProperty() {
	
		return this.ObjetEquiperProperty;
	}
	
	
	public final IntegerProperty getNbKitdeSoinProperty() {
		return nbKitdeSoinProperty;
	}
	public final IntegerProperty getNbBandageProperty() {
		return nbBandageProperty;
	}
	
	public final BooleanProperty getDroiteProperty() {
		return droiteProperty;
	}
	
	public final BooleanProperty getGaucheProperty() {
		return gaucheProperty;
	}
	
	public final BooleanProperty getSauteProperty() {
		return sauteProperty;
	}
	
	///////// Les setters /////////////
	public final void setY(int n){
		y.setValue(n);
	}
	public final void setX(int n){
		x.setValue(n);
	}
	public final void setNbCoeurs(int nb) {
		nbCoeursProperty.setValue(nb);
	}
	public void setNbSaut(int nbSaut) {
		this.nbSaut = nbSaut;
	}
	public void setNbBouclierProperty(IntegerProperty nbBouclierProperty) {
		this.nbBouclierProperty = nbBouclierProperty;
	}
	public void setDroite(boolean droite) {
		this.droiteProperty.setValue(droite);
	}
	public void setSaute(boolean saute) {
		this.sauteProperty.setValue(saute); 
	}
	public void setMatChoisi(int mat) {
		matChoisi = mat;
	}
	public void setDirection(boolean direction) {
		this.directionProperty.setValue(direction);
	}

	public void setCaseChoisi(int caseChoisi) {
		this.caseChoisi = caseChoisi;
	}
	public void setObjetEquiper(int i) {
		this.ObjetEquiperProperty.setValue(i);	
	}


	/////// Les Getters //////////////
	public final int getNbCoeurs() {
		return this.nbCoeursProperty.getValue();
	}
	public final int getNbBouclier() {
		return this.nbBouclierProperty.getValue();
	}
	@Override
	public final int getX() {
		return x.getValue();
	}
	@Override
	public final int getY() {
		return y.getValue();
	}
	public int getNbSaut() {
		return nbSaut;
	}
	public boolean getDroite() {
		return droiteProperty.getValue();
	}
	public boolean getGauche() {
		return gaucheProperty.getValue();
	}
	public void setGauche(boolean gauche) {
		this.gaucheProperty.setValue(gauche);
	}
	public boolean getSaute() {
		return sauteProperty.getValue();
	}
	
	public Inventaire getInventaireObjet() {
		return this.inventaireObjet;
	}
	
	public int getMatChoisi() {
		return matChoisi;
	}
	public boolean getDirection() {
		return directionProperty.getValue();
	}
	
	public Environnement getEnv() {
		return this.env;
	}

	public int getCaseChoisi() {
		return caseChoisi;
	}

	public int getObjetEquiper() {
		return this.ObjetEquiperProperty.getValue();
	}
	public final BooleanProperty getDirectionProperty() {
		return this.directionProperty;
	}
	

	///////// Les methodes de deplacement //////////
	@Override
	public void allerAGauche() {
		int npos = getX()-vitesse;
		if(npos > -5)
			this.x.setValue(npos);
	}
	@Override
	public void allerADroite() {
		int npos = getX()+vitesse;
		if (npos < 766)
			this.x.setValue(npos);
	}
	@Override
	public void sauter() {
		int npos = getY()-10;
		if(npos >0 )
			if(nbSaut<6) {
				this.y.setValue(npos);
				nbSaut++;
			}
	}
	@Override
	public void tomber() {
		int npos = getY()+10;
		this.y.setValue(npos);
	}
	
	
	////////Methodes ajout/supp soin////////
	public void ajtNbKitdeSoin() {
		int nbKDS = this.nbKitdeSoinProperty.getValue();
		if(nbKDS < 2) {
			this.nbKitdeSoinProperty.setValue(nbKitdeSoinProperty.getValue()+1);
			env.EnleverResource("bois",6);
		}
	}
	
	public void ajtNbBandage() {
		int nbBandages = nbBandageProperty.getValue()+1;
		if(nbBandages<6) {
			this.nbBandageProperty.setValue(nbBandages);
			env.EnleverResource("bois",3);
		}
	}
	public void mettreAzero() {
		this.nbBandageProperty.setValue(0);
		this.nbKitdeSoinProperty.setValue(0);
	}

	
	public void utiliserBandage() {
		if (nbCoeursProperty.getValue()<5  && nbBandageProperty.getValue()>0) {
			gagnerPv();
			this.nbBandageProperty.setValue(nbBandageProperty.getValue()-1);
		}
	}
	
	public void utiliserkitDeSoin() {
		if (nbCoeursProperty.getValue()<5  && nbKitdeSoinProperty.getValue()>0) {
			this.nbCoeursProperty.setValue(5);
			this.nbKitdeSoinProperty.setValue(nbKitdeSoinProperty.getValue()-1);
		}
	}

	
	


	//////////////METHODES CRAFT//////////////////
	
	//EPEE
		public void crafterEpeeBois() {
			if (env.getNbResource("bois")<3 && this.inventaireObjet.getObjetCase(1)!=0) 
				System.out.println("pas assez de bois il vous en manque "+(3-env.getNbResource("bois")));
			else if(this.getInventaireObjet().getInventaire().get(0).getNumObjetCase().getValue()==0) {
				System.out.println("deja poseder");
			}
			else if(this.inventaireObjet.getObjetCase(1)==1 ||this.inventaireObjet.getObjetCase(1)==2){
				System.out.println("Vous posseder une meilleur epee");
			}
			else if(env.getNbResource("bois")>=3) {
				System.out.println(this.inventaireObjet.getObjetCase(1));
				this.inventaireObjet.SetObjetCase(1,0);
				if (caseChoisi==1) {
					this.ObjetEquiperProperty.set(0);
				}
				env.EnleverResource("bois",3);

			}
		}

		public void crafterEpeePierre() {
			if (env.getNbResource("pierre")<3 && this.inventaireObjet.getObjetCase(1)!=0) 
				System.out.println("pas assez de pierre il vous en manque "+(3-env.getNbResource("pierre")));
			else if(this.inventaireObjet.getObjetCase(1)==1) 
				System.out.println("deja poseder");	
			else if(this.inventaireObjet.getObjetCase(1)==2)
				System.out.println("Vous posseder une meilleur epee");
			else if(env.getNbResource("pierre")>=3) {
				this.inventaireObjet.SetObjetCase(1,1);
				if (caseChoisi==1) {
					this.ObjetEquiperProperty.set(1);
				}
				env.EnleverResource("pierre",3);
			}
		}

		public void crafterEpeeMetal() {
			if (env.getNbResource("metal")<3 && this.inventaireObjet.getObjetCase(1)!=0) 
				System.out.println("pas assez de metal il vous en manque "+(3-env.getNbResource("metal")));
			else if(this.inventaireObjet.getObjetCase(1)==2)
				System.out.println("vous avez deja une epee en metal");
			else if(env.getNbResource("metal")>=3) {
				this.inventaireObjet.SetObjetCase(1,2);
				if (caseChoisi==1) {
					this.ObjetEquiperProperty.set(2);
				}
				env.EnleverResource("metal",3);
			}
		}

		//HACHE
		public void crafterHacheBois() {
			if (env.getNbResource("bois")<3 && this.inventaireObjet.getObjetCase(2)!=3) 
				System.out.println("pas assez de bois il vous en manque "+(3-env.getNbResource("bois")));
			else if(this.inventaireObjet.getObjetCase(2)==3) 
				System.out.println("vous avez deja une hache en bois");
			else if(this.inventaireObjet.getObjetCase(2)==4 || this.inventaireObjet.getObjetCase(2)==5)
				System.out.println("Vous posseder une meilleur hache");
			else if(env.getNbResource("bois")>=3) {
				this.inventaireObjet.SetObjetCase(2,3);
				if (caseChoisi==2) {
					this.ObjetEquiperProperty.set(3);
				}
				env.EnleverResource("bois",3);
			}
		}

		public void crafterHachePierre() {
			if (env.getNbResource("pierre")<3 && this.inventaireObjet.getObjetCase(2)!=3) 
				System.out.println("pas assez de pierre il vous en manque "+(3-env.getNbResource("pierre")));
			else if(this.inventaireObjet.getObjetCase(2)==4) 
				System.out.println("vous avez deja une hache en pierre");
			else if(this.inventaireObjet.getObjetCase(2)==5)
				System.out.println("Vous posseder une meilleur hache");
			else if(env.getNbResource("pierre")>=3) {
				this.inventaireObjet.SetObjetCase(2,4);
				if (caseChoisi==2) {
					this.ObjetEquiperProperty.set(4);
				}
				env.EnleverResource("pierre",3);
			}
		}

		public void crafterHacheMetal() {
			if (env.getNbResource("metal")<3 && this.inventaireObjet.getObjetCase(2)!=3) 
				System.out.println("pas assez de metal il vous en manque "+(3-env.getNbResource("metal")));
			else if(this.inventaireObjet.getObjetCase(2)==5)
				System.out.println("vous avez deja une hache en metal");
			else if(env.getNbResource("metal")>=3) {
				this.inventaireObjet.SetObjetCase(2,5);
				if (caseChoisi==2) {
					this.ObjetEquiperProperty.set(5);
				}
				env.EnleverResource("metal",3);
			}
		}

		//PIOCHE
		public void crafterPiocheBois() {
			if (env.getNbResource("bois")<3 && this.inventaireObjet.getObjetCase(3)!=6) 
				System.out.println("pas assez de bois il vous en manque "+(3-env.getNbResource("bois")));
			else if(this.inventaireObjet.getObjetCase(2)==6) 
				System.out.println("vous avez deja une pioche en bois");
			else if(this.inventaireObjet.getObjetCase(3)==7 ||this.inventaireObjet.getObjetCase(3)==8)
				System.out.println("Vous posseder une meilleur pioche");
			else if(env.getNbResource("bois")>=3) {
				this.inventaireObjet.SetObjetCase(3,6);
				if (caseChoisi==3) {
					
					this.ObjetEquiperProperty.set(6);
				}
				env.EnleverResource("bois",3);
			}
		}
		
		public void crafterPiochePierre() {
			if (env.getNbResource("pierre")<3 && this.inventaireObjet.getObjetCase(3)!=7) 
				System.out.println("pas assez de Pierre il vous en manque "+(3-env.getNbResource("pierre")));
			else if(this.inventaireObjet.getObjetCase(3)==7) 
				System.out.println("vous avez deja une pioche en pierre");
			else if(this.inventaireObjet.getObjetCase(3)==7 || this.inventaireObjet.getObjetCase(3)==8)
				System.out.println("Vous posseder une meilleur pioche");
			else if(env.getNbResource("pierre")>=3) {
				this.inventaireObjet.SetObjetCase(3,7);
				if (caseChoisi==3) {
					this.ObjetEquiperProperty.set(7);
				}
				env.EnleverResource("pierre",3);
			}
		}

		public void crafterPiocheMetal() {
			if (env.getNbResource("metal")<3 && this.inventaireObjet.getObjetCase(3)!=8) 
				System.out.println("pas assez de metal il vous en manque "+(3-env.getNbResource("metal")));
			else if(this.inventaireObjet.getObjetCase(3)==8) 
				System.out.println("vous avez deja une pioche en metal");
			else if(env.getNbResource("metal")>=3) {
				this.inventaireObjet.SetObjetCase(3,8);
				if (caseChoisi==3) {
					this.ObjetEquiperProperty.set(8);
				}
				env.EnleverResource("metal",3);
			}
		}

		//BANDAGES
		public void crafterBandage() {
			if (env.getNbResource("bois")<3 && this.inventaireObjet.getObjetCase(5)!=9) 
				System.out.println("pas assez de bois");
			else if(this.inventaireObjet.getObjetCase(5)==9 && env.getNbResource("bois")>=3) {
				ajtNbBandage();	
				System.out.println("bandage :"+this.nbBandageProperty.getValue());
			}
			else if(env.getNbResource("bois")>=3) {
				this.inventaireObjet.SetObjetCase(5,9);
				if (caseChoisi==5) {
					this.ObjetEquiperProperty.set(9);
				}
				ajtNbBandage();
				System.out.println("bandage :"+this.nbBandageProperty.getValue());
			}
		}

		//KITS DE SOINS
		public void crafterKitDeSoin() {
			if (env.getNbResource("bois")<6 && this.inventaireObjet.getObjetCase(6)!=10) 
				System.out.println("pas assez de bois");
			else if(this.inventaireObjet.getObjetCase(6)==10 && env.getNbResource("bois")>=6) {
				ajtNbKitdeSoin();
				System.out.println("kit de soin :"+this.nbKitdeSoinProperty.getValue());
			}
			else if(env.getNbResource("bois")>=6 ) {
				this.inventaireObjet.SetObjetCase(6,10);
				if (caseChoisi==6) {
					this.ObjetEquiperProperty.set(10);
				}
				ajtNbKitdeSoin();
				System.out.println("kit de soin :"+this.nbKitdeSoinProperty.getValue());
			}
		}
		
		//PISTOLET
		public void crafterPistolet() {
			if (env.getNbResource("metal")<10 && this.inventaireObjet.getObjetCase(4)!=11) 
				System.out.println("pas assez de metals");
			else if(this.inventaireObjet.getObjetCase(4)==11) 
				System.out.println("vous avez deja un pistolet");
			else if(env.getNbResource("metal")>=10) {
				this.inventaireObjet.SetObjetCase(4,11);
				if (caseChoisi==4) {
					this.ObjetEquiperProperty.set(11);
				}
				env.EnleverResource("metal",10);
			
			}
		}
		
		//BOUCLIER
		public void crafterBouclier() {
			if (env.getNbResource("metal") < 3) 
				System.out.println("pas assez de metals");
			else if(nbBouclierProperty.getValue() == 3) 
				System.out.println("Vous avez deja le maximum de bouclier");
			else if(env.getNbResource("metal")>=3) {	
				env.EnleverResource("metal",3);
				this.gagnerBouclier();
				System.out.println(nbBouclierProperty.getValue());
				
			}
		}


}
