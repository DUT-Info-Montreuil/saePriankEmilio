package jeu.test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

import jeu.modele.Ennemi;
import jeu.modele.Environnement;
import jeu.modele.Joueur;

class JoueurTest {
	Environnement env = new Environnement(null);
	Joueur j = new Joueur(env);
	Ennemi e = new Ennemi(40);

	//une tuile = 40x40px, pareil pour la taille du joueur

	@Test 
	//l'ennemi est a la meme position que le joueur et demarre avec 5 pv
	final void testAttaquer() {
		env.ajouter(e);
		
		//main
		j.setObjetEquiper(12);
		j.attaquer();
		assertEquals(e.getPv(), 4); 
		
		//epee bois
		e.setPv(5);
		j.setObjetEquiper(0);
		j.attaquer();
		assertEquals(e.getPv(), 3); 
		
		//epee bois
		e.setPv(5);
		j.setObjetEquiper(1);
		j.attaquer();
		assertEquals(e.getPv(), 2); 

		//epee bois
		e.setPv(5);
		j.setObjetEquiper(2);
		j.attaquer();
		assertEquals(e.getPv(), 0); 

		
		
	}
	
	//CONSTRUCTION
	@Test
	//prend la prochaine tuile a droite du joueur
	final void testConctructionDroitePlacer() {
		int tailleTuile = 40;
		int prochaineTuile = 79;
		int milieuJoueur = 20;
		int nbTuileParLigne= 20;

		assertEquals(j.constructionDroitePlacer(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne); 
		tailleTuile = 42;
		assertNotSame(j.constructionDroitePlacer(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne);
	}
	@Test
	//prend la prochaine tuile a gauche du joueur
	final void testConctructionGauchePlacer() {
		int tailleTuile = 40;
		int prochaineTuile = -40;
		int milieuJoueur = 20;
		int nbTuileParLigne= 20;

		assertEquals(j.constructionGauchePlacer(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne); 
		tailleTuile = 42;
		assertNotSame(j.constructionGauchePlacer(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne);
	}
	@Test
	//prend la tuile a droite du joueur
	final void testConctructionDroiteCasser() {
		int tailleTuile = 40;
		int prochaineTuile = 40;
		int milieuJoueur = 20;
		int nbTuileParLigne= 20;

		assertEquals(j.constructionDroiteCasser(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne); 
		tailleTuile = 42;
		assertNotSame(j.constructionDroiteCasser(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne);
	}
	@Test
	//prend la tuile a gauche du joueur
	final void testConctructionGaucheCasser() {
		int tailleTuile = 40;
		int prochaineTuile = -1;
		int milieuJoueur = 20;
		int nbTuileParLigne= 20;

		assertEquals(j.constructionGaucheCasser(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne); 
		tailleTuile = 42;
		assertNotSame(j.constructionGaucheCasser(), ((j.getX()+prochaineTuile)/tailleTuile) + ((j.getY()+milieuJoueur)/tailleTuile)*nbTuileParLigne);
	}

	//PV
	@Test
	//le joueur commence avec 5 pv
	final void perdrePvTest() {
		assertEquals(j.getNbCoeurs(), 5); 
		assertNotSame(j.getNbCoeurs(),4);

		//on enleve 1pv
		j.perdrePv();
		assertEquals(j.getNbCoeurs(), 4); 
		assertNotSame(j.getNbCoeurs(),5);

		//on enleve 2pv
		j.perdrePv();
		j.perdrePv();
		assertEquals(j.getNbCoeurs(), 2); 
		assertNotSame(j.getNbCoeurs(),5);
	}

	@Test
	final void gagnerPvTest() {
		for(int i=0 ; i<=5 ; i++)
			j.perdrePv();
		assertEquals(j.getNbCoeurs(), 0); 
		assertNotSame(j.getNbCoeurs(),4);

		//on ajoute 1pv
		j.gagnerPv();
		assertEquals(j.getNbCoeurs(), 1); 
		assertNotSame(j.getNbCoeurs(),4);

		//on ajoute 2pv
		j.gagnerPv();
		j.gagnerPv();
		assertEquals(j.getNbCoeurs(), 3); 
		assertNotSame(j.getNbCoeurs(),4);

	}

	//BOUCLIER
	@Test
	//le joueur demarre a  bouclier
	final void gagnerBouclierTest() {
		assertEquals(j.getNbBouclier(), 0); 
		assertNotSame(j.getNbBouclier(),3);

		//on ajoute 1 bouclier
		j.gagnerBouclier();
		assertEquals(j.getNbBouclier(), 1); 
		assertNotSame(j.getNbBouclier(),0);

		//on ajoute 2 bouclier
		j.gagnerBouclier();
		j.gagnerBouclier();
		assertEquals(j.getNbBouclier(), 3); 
		assertNotSame(j.getNbBouclier(),1);
	}

	@Test
	final void perdreBouclierTest() {
		j.gagnerBouclier();
		j.gagnerBouclier();
		j.gagnerBouclier();

		assertEquals(j.getNbBouclier(), 3); 
		assertNotSame(j.getNbBouclier(),0);

		//on enleve 1 bouclier
		j.perdreBouclier();
		assertEquals(j.getNbBouclier(), 2); 
		assertNotSame(j.getNbBouclier(),0);

		//on enleve 2 bouclier
		j.perdreBouclier();
		j.perdreBouclier();
		assertEquals(j.getNbBouclier(), 0); 
		assertNotSame(j.getNbBouclier(),2);
	}


	@Test
	//le joueur perd d'abord les bouclier qu'il possede ensuite les pv
	final void blesserTest() {
		j.gagnerBouclier();
		j.gagnerBouclier();
		assertEquals(j.getNbBouclier(), 2); 
		assertNotSame(j.getNbBouclier(),0);

		j.blesser();
		j.blesser();
		assertEquals(j.getNbCoeurs(), 5); 
		assertNotSame(j.getNbCoeurs(),3);
		assertEquals(j.getNbBouclier(), 0); 
		assertNotSame(j.getNbBouclier(),2);
	}

	//DEPLACEMENT
	@Test
	//le joueur demarre a la position 40 et se deplace de 8 en x
	//le joueur ne peut pas depasser -5 et 766 qui est la bordure de la fenetre en x
	final void allerAGaucheTest() {
		assertEquals(j.getX(), 40); 
		assertNotSame(j.getX(),0);

		j.allerAGauche();
		assertEquals(j.getX(), 32); 
		assertNotSame(j.getX(),40);

		j.allerAGauche();
		j.allerAGauche();
		assertEquals(j.getX(), 16); 
		assertNotSame(j.getX(),32);

		j.setX(0);
		j.allerAGauche();
		assertEquals(j.getX(), 0); 
		assertNotSame(j.getX(),-8);
	}
	@Test
	final void allerADroiteTest() {
		assertEquals(j.getX(), 40); 
		assertNotSame(j.getX(),0);

		j.allerADroite();
		assertEquals(j.getX(), 48); 
		assertNotSame(j.getX(),40);

		j.allerADroite();
		j.allerADroite();
		assertEquals(j.getX(), 64); 
		assertNotSame(j.getX(),48);

		j.setX(768);
		j.allerADroite();
		assertEquals(j.getX(), 768); 
		assertNotSame(j.getX(),776);
	}

	@Test
	//le joueur demarre a la position 360 en y et saute et tombe de 10 
	//le joueur ne peut pas depasser 10 qui est la bordure de la fenetre en y quand il fait un minisaut
	//un saut complet est 6 minisaut, il peut donc faire que 6 mini saut d'affile

	final void sauterTest() {
		assertEquals(j.getY(), 360); 
		assertNotSame(j.getY(),0);

		j.sauter();
		assertEquals(j.getY(), 350); 
		assertNotSame(j.getY(),360);

		j.sauter();
		j.sauter();
		assertEquals(j.getY(), 330); 
		assertNotSame(j.getY(),350);

		j.setY(10);
		assertEquals(j.getY(), 10); 
		assertNotSame(j.getY(),0);

		j.setY(360);
		j.setNbSaut(0);
		for(int i=0 ; i<=6 ; i++)
			j.sauter();
		j.sauter();
		assertEquals(j.getY(), 300); 
		assertNotSame(j.getY(),290);
	}

	@Test
	final void tomberTest() {
		assertEquals(j.getY(), 360); 
		assertNotSame(j.getY(),0);

		j.tomber();
		assertEquals(j.getY(), 370); 
		assertNotSame(j.getY(),360);

		j.tomber();
		j.tomber();
		assertEquals(j.getY(), 390); 
		assertNotSame(j.getY(),370);
	}

	//SOIN
	//un kit coute 6 de bois et le joueur peut en avoir que 2 maximum
	//un kit soigne tout les pv
	//deplus il ne peut pas utiliser de kit quand il a 5 pv
	@Test
	final void ajtNbKinDeSoinTest() {
		//ajout 30 de bois 
		for(int i=0 ; i<30 ; i++)
			env.AjouterResource("bois");

		assertEquals(j.getNbKitdeSoin(), 0); 
		assertNotSame(j.getNbKitdeSoin(),1);

		j.ajtNbKitdeSoin();
		assertEquals(j.getNbKitdeSoin(), 1); 
		assertNotSame(j.getNbKitdeSoin(),0);
		assertEquals(env.getNbResource("bois"), 24); 
		assertNotSame(env.getNbResource("bois"), 30);

		j.ajtNbKitdeSoin();
		j.ajtNbKitdeSoin();
		assertEquals(j.getNbKitdeSoin(), 2); 
		assertNotSame(j.getNbKitdeSoin(),3);
		assertEquals(env.getNbResource("bois"), 18); 
		assertNotSame(env.getNbResource("bois"), 12);
	}

	@Test
	final void utiliserKitDeSoinTest() {
		//ajout 30 de bois 
		for(int i=0 ; i<30 ; i++)
			env.AjouterResource("bois");

		//ajout de 2 kit
		j.ajtNbKitdeSoin();
		j.ajtNbKitdeSoin();	
		assertEquals(j.getNbKitdeSoin(), 2); 
		assertNotSame(j.getNbKitdeSoin(),0);

		j.utiliserkitDeSoin();
		assertEquals(j.getNbKitdeSoin(), 2); 
		assertNotSame(j.getNbKitdeSoin(),1);

		//perd 4 pv
		j.perdrePv();
		j.perdrePv();
		j.perdrePv();
		j.perdrePv();
		assertEquals(j.getNbCoeurs(), 1); 
		assertNotSame(j.getNbCoeurs(),5);


		j.utiliserkitDeSoin();
		assertEquals(j.getNbKitdeSoin(), 1); 
		assertNotSame(j.getNbKitdeSoin(),2);
		assertEquals(j.getNbCoeurs(), 5); 
		assertNotSame(j.getNbCoeurs(),1);

		//perd 1 pv
		j.perdrePv();
		assertEquals(j.getNbCoeurs(), 4); 
		assertNotSame(j.getNbCoeurs(),5);


		j.utiliserkitDeSoin();
		assertEquals(j.getNbKitdeSoin(), 0); 
		assertNotSame(j.getNbKitdeSoin(),1);
		assertEquals(j.getNbCoeurs(), 5); 
		assertNotSame(j.getNbCoeurs(),4);
	}

	@Test
	//un bandage coute 3 de bois et soigne 1 pv , le joueur peut en avoir 5 maximum
	//deplus il ne peut pas utiliser de bandage quand il a 5 pv
	final void ajtBandageTest() {
		//ajout 30 de bois 
		for(int i=0 ; i<30 ; i++)
			env.AjouterResource("bois");

		assertEquals(j.getNbBandage(), 0); 
		assertNotSame(j.getNbBandage(),1);

		//ajout d'un bandage
		j.ajtNbBandage();
		assertEquals(j.getNbBandage(), 1); 
		assertNotSame(j.getNbBandage(),0);
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),30);

		//ajout de 5 bandages
		j.ajtNbBandage();
		j.ajtNbBandage();
		j.ajtNbBandage();
		j.ajtNbBandage();
		j.ajtNbBandage();
		assertEquals(j.getNbBandage(), 5); 
		assertNotSame(j.getNbBandage(),6);
		assertEquals(env.getNbResource("bois"), 15); 
		assertNotSame(env.getNbResource("bois"),12);
	}

	@Test
	final void utiliserBandageTest() {
		//ajout 30 de bois 
		for(int i=0 ; i<30 ; i++)
			env.AjouterResource("bois");
		//ajout de 5 bandages
		j.ajtNbBandage();
		j.ajtNbBandage();
		j.ajtNbBandage();
		j.ajtNbBandage();
		j.ajtNbBandage();

		j.utiliserBandage();//le joueur a 5 pv 
		assertEquals(j.getNbBandage(), 5); 
		assertNotSame(j.getNbBandage(),4);

		//perd 4 pv
		j.perdrePv();
		j.perdrePv();
		j.perdrePv();
		j.perdrePv();

		j.utiliserBandage();
		assertEquals(j.getNbBandage(), 4); 
		assertNotSame(j.getNbBandage(),5);
		assertEquals(j.getNbCoeurs(), 2); 
		assertNotSame(j.getNbCoeurs(),1);

		j.utiliserBandage();
		j.utiliserBandage();
		j.utiliserBandage();
		assertEquals(j.getNbBandage(), 1); 
		assertNotSame(j.getNbBandage(),4);
		assertEquals(j.getNbCoeurs(), 5); 
		assertNotSame(j.getNbCoeurs(),2);

		//perd 4 pv
		j.perdrePv();
		j.perdrePv();
		j.perdrePv();
		j.perdrePv();

		//il possede que 1 bandage donc il gagne que 1 pv
		j.utiliserBandage();
		j.utiliserBandage();
		assertEquals(j.getNbBandage(), 0); 
		assertNotSame(j.getNbBandage(),1);
		assertEquals(j.getNbCoeurs(), 2); 
		assertNotSame(j.getNbCoeurs(),1);		
	}

	@Test
	final void mettreAzeroTest() {
		//ajout 30 de bois 
		for(int i=0 ; i<30 ; i++)
			env.AjouterResource("bois");

		j.ajtNbBandage();
		j.ajtNbKitdeSoin();
		assertEquals(j.getNbBandage(), 1); 
		assertEquals(j.getNbKitdeSoin(), 1); 

		j.mettreAzero();
		assertEquals(j.getNbBandage(), 0); 
		assertEquals(j.getNbKitdeSoin(), 0);
	}
	
	//CRAFT
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
//	12 objetVide
	
	//pour craft un objet il faut 3 de resource et ne pas deja posseder l'objet ou un meilleur objet sauf pour les kit, bandages et bouclier
	@Test
	final void crafterEpeeBoisTest() {
		//le joueur ne possede pas assez de bois
		j.crafterEpeeBois();
		assertEquals(j.getInventaireObjet().getObjetCase(1), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(1), 0);
		
		//ajout 30 de bois et pierre
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("bois");
			env.AjouterResource("pierre");
			
		}
		//le joueur craft une epee en bois
		j.crafterEpeeBois();
		assertEquals(j.getInventaireObjet().getObjetCase(1), 0); 
		assertNotSame(j.getInventaireObjet().getObjetCase(1), 12);
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),30);
		
		//le joueur possede deja une epee en bois
		j.crafterEpeeBois();
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),24);
		
		j.crafterEpeePierre();
		//le joueur possede deja une epee en pierre
		j.crafterEpeeBois();
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),24);
		
	}
	
	@Test
	final void crafterEpeePierreTest() {
		//le joueur ne possede pas assez de pierre
		j.crafterEpeePierre();
		assertEquals(j.getInventaireObjet().getObjetCase(1), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(1), 1);
		
		//ajout 30 de metal et pierre
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");
			env.AjouterResource("pierre");
			
		}
		//le joueur craft une epee en pierre
		j.crafterEpeePierre();
		assertEquals(j.getInventaireObjet().getObjetCase(1), 1); 
		assertNotSame(j.getInventaireObjet().getObjetCase(1), 12);
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),30);
		
		//le joueur possede deja une epee en pierre
		j.crafterEpeePierre();
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),24);
		
		j.crafterEpeeMetal();
		//le joueur possede deja une epee en metal
		j.crafterEpeePierre();
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),24);
		
	}
	
	@Test
	final void crafterEpeeMetalTest() {
		//le joueur ne possede pas assez de metal
		j.crafterEpeeMetal();
		assertEquals(j.getInventaireObjet().getObjetCase(1), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(1), 2);
		
		//ajout 30 de metal
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");
			
		}
		//le joueur craft une epee en metal
		j.crafterEpeeMetal();
		assertEquals(j.getInventaireObjet().getObjetCase(1), 2); 
		assertNotSame(j.getInventaireObjet().getObjetCase(1), 12);
		assertEquals(env.getNbResource("metal"), 27); 
		assertNotSame(env.getNbResource("metal"),30);
		
		//le joueur possede deja une epee en metal
		j.crafterEpeeMetal();
		assertEquals(env.getNbResource("metal"), 27); 
		assertNotSame(env.getNbResource("metal"),24);
		
	}
	
	@Test
	final void crafterHacheBoisTest() {
		//le joueur ne possede pas assez de bois
		j.crafterHacheBois();
		assertEquals(j.getInventaireObjet().getObjetCase(2), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(2), 3);
		
		//ajout 30 de bois et pierre
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("bois");
			env.AjouterResource("pierre");
			
		}
		//le joueur craft une hache en bois
		j.crafterHacheBois();
		assertEquals(j.getInventaireObjet().getObjetCase(2), 3); 
		assertNotSame(j.getInventaireObjet().getObjetCase(2), 12);
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),30);
		
		//le joueur possede deja une hache en bois
		j.crafterHacheBois();
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),24);
		
		j.crafterHachePierre();
		//le joueur possede deja une hache en pierre
		j.crafterHacheBois();
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),24);
		
	}
	
	@Test
	final void crafterHachePierreTest() {
		//le joueur ne possede pas assez de pierre
		j.crafterHachePierre();
		assertEquals(j.getInventaireObjet().getObjetCase(2), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(2), 4);
		
		//ajout 30 de metal et pierre
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");
			env.AjouterResource("pierre");
			
		}
		//le joueur craft une hache en pierre
		j.crafterHachePierre();
		assertEquals(j.getInventaireObjet().getObjetCase(2), 4); 
		assertNotSame(j.getInventaireObjet().getObjetCase(2), 12);
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),30);
		
		//le joueur possede deja une hache en pierre
		j.crafterHachePierre();
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),24);
		
		j.crafterHachePierre();
		//le joueur possede deja une hache en metal
		j.crafterHachePierre();
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),24);
		
	}
	
	@Test
	final void crafterHacheMetalTest() {
		//le joueur ne possede pas assez de metal
		j.crafterHacheMetal();
		assertEquals(j.getInventaireObjet().getObjetCase(2), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(2), 5);
		
		//ajout 30 de metal
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");
		}
		//le joueur craft une hache en metal
		j.crafterHacheMetal();
		assertEquals(j.getInventaireObjet().getObjetCase(2), 5); 
		assertNotSame(j.getInventaireObjet().getObjetCase(2), 12);
		assertEquals(env.getNbResource("metal"), 27); 
		assertNotSame(env.getNbResource("metal"),30);
		
		//le joueur possede deja une hache en metal
		j.crafterHacheMetal();
		assertEquals(env.getNbResource("metal"), 27); 
		assertNotSame(env.getNbResource("metal"),24);
	}
	
	
	@Test
	final void crafterPiocheBoisTest() {
		//le joueur ne possede pas assez de bois
		j.crafterPiocheBois();
		assertEquals(j.getInventaireObjet().getObjetCase(3), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(3), 6);
		
		//ajout 30 de bois et pierre
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("bois");
			env.AjouterResource("pierre");
		}
		//le joueur craft une pioche en bois
		j.crafterPiocheBois();
		assertEquals(j.getInventaireObjet().getObjetCase(3), 6); 
		assertNotSame(j.getInventaireObjet().getObjetCase(3), 12);
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),30);
		
		//le joueur possede deja une pioche en bois
		j.crafterPiocheBois();
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),24);
		
		j.crafterPiochePierre();
		//le joueur possede deja une pioche en pierre
		j.crafterPiocheBois();
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),24);
		
	}
	
	@Test
	final void crafterPiochePierreTest() {
		//le joueur ne possede pas assez de pierre
		j.crafterPiochePierre();
		assertEquals(j.getInventaireObjet().getObjetCase(3), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(3), 7);
		
		//ajout 30 de metal et pierre
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");
			env.AjouterResource("pierre");
		}
		//le joueur craft une pioche en pierre
		j.crafterPiochePierre();
		assertEquals(j.getInventaireObjet().getObjetCase(3), 7); 
		assertNotSame(j.getInventaireObjet().getObjetCase(3), 12);
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),30);
		
		//le joueur possede deja une pioche en pierre
		j.crafterPiochePierre();
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),24);
		
		j.crafterPiocheMetal();
		//le joueur possede deja une pioche en metal
		j.crafterPiochePierre();
		assertEquals(env.getNbResource("pierre"), 27); 
		assertNotSame(env.getNbResource("pierre"),24);
		
	}
	
	@Test
	final void crafterPiocheMetalTest() {
		//le joueur ne possede pas assez de metal
		j.crafterPiocheMetal();
		assertEquals(j.getInventaireObjet().getObjetCase(3), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(3), 8);
		
		//ajout 30 de metal
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");
		}
		//le joueur craft une pioche en metal
		j.crafterPiocheMetal();
		assertEquals(j.getInventaireObjet().getObjetCase(3), 8); 
		assertNotSame(j.getInventaireObjet().getObjetCase(3), 12);
		assertEquals(env.getNbResource("metal"), 27); 
		assertNotSame(env.getNbResource("metal"),30);

		//le joueur possede deja une pioche en metal
		j.crafterPiocheMetal();
		assertEquals(env.getNbResource("metal"), 27); 
		assertNotSame(env.getNbResource("metal"),24);
		
	}
	
	@Test
	final void crafterBandageTest() {
		//le joueur ne possede pas assez de bois
		j.crafterBandage();
		assertEquals(j.getInventaireObjet().getObjetCase(5), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(5), 9);
		
		//ajout 30 de bois 
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("bois");		
		}
		//le joueur craft un bandage
		j.crafterBandage();
		assertEquals(j.getInventaireObjet().getObjetCase(5), 9); 
		assertNotSame(j.getInventaireObjet().getObjetCase(5), 12);
		assertEquals(env.getNbResource("bois"), 27); 
		assertNotSame(env.getNbResource("bois"),30);
		
		//le joueur peut posseder que 5 bandage maximum
		j.crafterBandage();
		j.crafterBandage();
		j.crafterBandage();
		j.crafterBandage();
		j.crafterBandage();
		assertEquals(env.getNbResource("bois"), 15); 
		assertNotSame(env.getNbResource("bois"),12);		
	}
	
	@Test
	final void crafterKitDeSoinTest() {
		//le joueur ne possede pas assez de bois
		j.crafterKitDeSoin();
		assertEquals(j.getInventaireObjet().getObjetCase(6), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(6), 10);
		
		//ajout 30 de bois 
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("bois");		
		}
		//le joueur craft un kit
		j.crafterKitDeSoin();
		assertEquals(j.getInventaireObjet().getObjetCase(6), 10); 
		assertNotSame(j.getInventaireObjet().getObjetCase(6), 12);
		assertEquals(env.getNbResource("bois"), 24); 
		assertNotSame(env.getNbResource("bois"),30);
		
		//le joueur peut posseder que 2 Kit maximum
		j.crafterKitDeSoin();
		j.crafterKitDeSoin();
		j.crafterKitDeSoin();
		assertEquals(env.getNbResource("bois"), 18 ); 
		assertNotSame(env.getNbResource("bois"),6);		
	}
	
	@Test
	final void crafterPistoletTest() {
		//le joueur ne possede pas assez de metal
		j.crafterPistolet();
		assertEquals(j.getInventaireObjet().getObjetCase(4), 12); 
		assertNotSame(j.getInventaireObjet().getObjetCase(4), 11);
		
		//ajout 30 de metal
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");		
		}
		//le joueur craft un pistolet
		j.crafterPistolet();
		assertEquals(j.getInventaireObjet().getObjetCase(4), 11); 
		assertNotSame(j.getInventaireObjet().getObjetCase(4), 12);
		assertEquals(env.getNbResource("metal"), 20); 
		assertNotSame(env.getNbResource("metal"),30);
		
		//le joueur possede deja un pistolet
		j.crafterPistolet();
		assertEquals(env.getNbResource("metal"), 20); 
		assertNotSame(env.getNbResource("metal"),10);			
	}
	
	@Test
	final void crafterBouclierTest() {
		//le joueur ne possede pas assez de metal
		j.crafterBouclier();
		assertEquals(j.getNbBouclier(), 0); 
		assertNotSame(j.getNbBouclier(), 1);
		
		//ajout 30 de metal
		for(int i=0 ; i<30 ; i++) {
			env.AjouterResource("metal");		
		}
		//le joueur craft un pistolet
		j.crafterBouclier();
		assertEquals(j.getNbBouclier(), 1); 
		assertNotSame(j.getNbBouclier(), 0);
		assertEquals(env.getNbResource("metal"), 27); 
		assertNotSame(env.getNbResource("metal"),30);
		
		//le joueur peut avoir jusqu'a 3 bouclier maximum
		j.crafterBouclier();
		j.crafterBouclier();
		j.crafterBouclier();
		assertEquals(env.getNbResource("metal"), 21 ); 
		assertNotSame(env.getNbResource("metal"),18);			
	}


}