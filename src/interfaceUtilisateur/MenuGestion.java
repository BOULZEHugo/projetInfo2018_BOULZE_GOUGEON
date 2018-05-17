package interfaceUtilisateur;

import javax.swing.JOptionPane;
import gestion.Decharge;
import gestion.Eboueur;
import gestion.Equipe;
import gestion.Secteur;
import gestion.Vehicule;
import interfaceGestion.GestionCollecte;
import interfaceGestion.GestionEboueur;
import interfaceGestion.GestionEquipe;
import interfaceGestion.GestionVehicule;


/**
 * Menu gestion de WasteAdmin.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see Secteur
 * @see Decharge
 * @see Eboueur
 * @see Equipe
 * @see GestionEboueur
 * @see GestionEquipe
 * @see GestionVehicule
 * @see GestionCollecte
 * @version 1.0
 *
 */


public class MenuGestion {
	
	
	/**
	 * Permet d'ouvrir le menu gestion de WasteAdmin
	 * 
	 * @see #menuCollecte()
	 * @see #menuEboueur()
	 * @see #menuEquipe()
	 * @see #menuVehicule()
	 * @see Secteur#listeSecteur()
	 * @see Decharge#listeDecharge()
	 * @since 1.0
	 * 
	 */
	
	public static void menu() {
	
		String[] type= { "Collecte", "Eboueur", "Equipe", "Vehicule", "Secteur", "Décharge"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous gérer ?",  "Gestion", JOptionPane.QUESTION_MESSAGE,null, type, type[5]);
		
		if ( str.equals("Collecte") ) {
			menuCollecte();
		}	
		
		if ( str.equals("Eboueur") ) {
			menuEboueur();
		}
		
		if ( str.equals("Equipe") ) {
			menuEquipe();
		}
		
		if ( str.equals("Vehicule") ) {
			menuVehicule();
		}	
		
		if ( str.equals("Secteur") ) {
			Secteur.listeSecteur();
		}
		
		if ( str.equals("Décharge") ) {
			Decharge.listeDecharge();
		}	
	}
	
	
	
	/**
	 * Permet d'ouvrir le menu collecte du menu gestion
	 * 
	 * @see GestionCollecte#collecteAPlanifier()
	 * @see GestionCollecte#planificationCollecte()
	 * @see GestionCollecte#mettreAJourCollecte()
	 * @see GestionCollecte#annulerUneCollecte()
	 * @see GestionCollecte#inventaireCollecte()
	 * @since 1.0
	 * 
	 */
	
	public static void menuCollecte() {
			
		String[] type= { "Planifier une collecte" , "Secteurs où les collectes doivent être planifiées", "Mettre à jour", "Annuler une collecte", "Inventaire de collectes"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous faire ?",  " Gestion - Collectes", JOptionPane.QUESTION_MESSAGE,null, type, type[4]);
			
		if ( str.equals("Secteurs où les collectes doivent être planifiées")) {
			GestionCollecte.collecteAPlanifier();
		}	
		
		if ( str.equals("Planifier une collecte") ) {
			GestionCollecte.planificationCollecte();
		}	
		
		if ( str.equals("Mettre à jour") ) {
			GestionCollecte.mettreAJourCollecte();
		}	
		
		if ( str.equals("Annuler une collecte") ) {
			GestionCollecte.annulerUneCollecte();
		}
		
		if ( str.equals("Inventaire de collectes") ) {
			GestionCollecte.inventaireCollecte();
		}	
	}
		
	
		
	/**
	* Permet d'ouvrir le menu éboueur du menu gestion
	* 
	* @see GestionEboueur#creerUnEboueur()
	* @see GestionEboueur#supprimerUnEboueur()
	* @see GestionEboueur#salaireEboueur()
	* @see Eboueur#listeEboueur()
	* @since 1.0
	* 
	*/
		
	public static void menuEboueur() {
			
		String[] type= { "Ajouter un éboueur" ,"Supprimer un éboueur", "Calculer le salaire", "Liste des éboueurs"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous faire ?",  " Gestion - Eboueurs", JOptionPane.QUESTION_MESSAGE,null, type, type[3]);
			
		if ( str.equals("Ajouter un éboueur")) {
			GestionEboueur.creerUnEboueur();
		}	
		
		if ( str.equals("Supprimer un éboueur") ) {
			GestionEboueur.supprimerUnEboueur();
		}	
			
		if ( str.equals("Calculer le salaire") ) {
			GestionEboueur.salaireEboueur();
		}	
			
		if ( str.equals("Liste des éboueurs") ) {
			Eboueur.listeEboueur();
		}	
	}
		
		
	
	/**
	* Permet d'ouvrir le menu équipe du menu gestion
	* 
	* @see GestionEquipe#creerUneEquipe()
	* @see GestionEquipe#supprimerUneEquipe()
	* @see GestionEquipe#affecterSecteur()
	* @see Equipe#listeEquipe()
	* @since 1.0
	* 
	*/
		
	public static void menuEquipe() {
			
		String[] type= { "Créer une équipe" , "Supprimer une équipe", "Affecter un secteur", "Liste des équipes"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous faire ?",  " Gestion - Equipes", JOptionPane.QUESTION_MESSAGE,null, type, type[3]);
			
		if ( str.equals("Créer une équipe")) {
			GestionEquipe.creerUneEquipe();
		}	
			
		if ( str.equals("Supprimer une équipe") ) {
			GestionEquipe.supprimerUneEquipe();
		}	
			
		if ( str.equals("Affecter un secteur") ) {
			GestionEquipe.affecterSecteur();
		}	
			
		if ( str.equals("Liste des équipes") ) {
			Equipe.listeEquipe();
		}	
	}
		
		
		
	/**
	* Permet d'ouvrir le menu véhicule du menu gestion
	* 
	* @see GestionVehicule#creerUnVehicule()
	* @see GestionVehicule#supprimerUnVehicule()
	* @see Vehicule#listeVehicule()
	* @since 1.0
	* 
	*/
		
	public static void menuVehicule() {
			
		String[] type= { "Ajouter un véhicule" , "Supprimer un véhicule", "Liste des véhicules"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous faire ?",  " Gestion - Véhicules", JOptionPane.QUESTION_MESSAGE,null, type, type[2]);
			
		if ( str.equals("Ajouter un véhicule")) {
			GestionVehicule.creerUnVehicule();
		}	
			
		if ( str.equals("Supprimer un véhicule") ) {
			GestionVehicule.supprimerUnVehicule();
		}	
			
		if ( str.equals("Liste des véhicules") ) {
			Vehicule.listeVehicule();
		}	
	}
		
	
}