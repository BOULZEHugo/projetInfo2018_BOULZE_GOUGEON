package interfaceGestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connexion.Connexion;
import gestion.Collecte;
import gestion.Decharge;
import gestion.Secteur;
import gestion.StatutCollecte;
import gestion.TypeCollecte;
import gestion.Vehicule;


/**
 * Cette classe permet à l'utilisateur de gérer les collectes (planification,annulation,compte rendu).
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see Collecte
 * @see Secteur
 * @see Decharge
 * @see Vehicule
 * @version 1.0
 * 
 */


public class GestionCollecte {
	
	

	/**
	 * Permet à l'utilsateur de planifier une collecte en renseignant la date, l'heure de début, le type, le secteur et le véhicule de collecte
	 * 
	 * @see Collecte#planifierCollecte()
	 * @see Collecte#Collecte(TypeCollecte, Vehicule, Secteur, String, String)
	 * @see Secteur#Secteur(int)
	 * @see Vehicule#Vehicule(String)
	 * @see Vehicule#vehiculeUtilise(String)
	 * @see Vehicule#existenceVehicule()
	 * @since 1.0
	 * 
	 */
	
	public static void planificationCollecte() {
	
		TypeCollecte nature ;
		String date = JOptionPane.showInputDialog(null, "Date de la collecte (au format JJ/MM/AAAA)", " Gestion des Collectes - Planification d'une collecte" , JOptionPane.QUESTION_MESSAGE);
		String heure = JOptionPane.showInputDialog(null, "Heure de début de collecte (au format ..h..)",  " Gestion des Collectes - Planification d'une collecte", JOptionPane.QUESTION_MESSAGE);
		
		String[] type = { "Emballages et Papiers", "Verre" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer le type de collecte",  " Gestion des Collectes - Planification d'une collecte", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Verre") ) {
			nature = TypeCollecte.Verre;
		}
		
		else {
			nature = TypeCollecte.EmballageEtPapier;
		}
		
		int idsecteur = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du secteur",  " Gestion des Collectes - Planification d'une collecte" , JOptionPane.QUESTION_MESSAGE));
		String immatriculation = JOptionPane.showInputDialog(null, "Immatriculation du véhicule",  " Gestion des Collectes - Planification d'une collecte" , JOptionPane.QUESTION_MESSAGE);
		
		Secteur secteur = new Secteur(idsecteur);
		Vehicule vehicule = new Vehicule(immatriculation);
		Collecte collecte = new Collecte(nature,vehicule,secteur,date,heure);
		vehicule.existenceVehicule();
		vehicule.vehiculeUtilise(date); 		// dit si le vehicule est déjà utilisé à la date du jour
		collecte.planifierCollecte();
	}
	
	
	
	/**
	 * Permet à l'utilsateur de mettre à jour une collecte en renseignant la date, le secteur, le statut, le temps, le poids et le volume, et la décharge
	 * 
	 * @see Collecte#compteRendu()
	 * @see Collecte#Collecte(StatutCollecte, Secteur, Decharge, String, int, double, double, String)
	 * @see connexion.Connexion
	 * @see Secteur#Secteur(int)
	 * @see Decharge#Decharge(String, String)
	 * @since 1.0
	 * 
	 */
	
	public static void mettreAJourCollecte() {
		
		StatutCollecte statut;
		
		String date = JOptionPane.showInputDialog(null, "Date de la collecte (au format JJ/MM/AAAA)", " Gestion des Collectes - Compte Rendu Collecte" , JOptionPane.QUESTION_MESSAGE);
		int idsecteur = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du secteur",  " Gestion des Collectes - Compte Rendu Collecte" , JOptionPane.QUESTION_MESSAGE));
		
		String[] type= { "Effectuee","Inachevee" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer le statut de la collecte",  " Gestion des Collectes - Compte Rendu Collecte", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Effectuee") ){
			statut = StatutCollecte.Effectuee;
		}
		
		else {
			statut = StatutCollecte.Inachevee;
		}
		
		int temps = Integer.parseInt(JOptionPane.showInputDialog(null, "Temps de Collecte (en min)",  " Gestion des Collectes - Compte Rendu Collecte" , JOptionPane.QUESTION_MESSAGE));
		double poids = Integer.parseInt(JOptionPane.showInputDialog(null, "Poids des déchets collectés (en kg)",  " Gestion des Collectes - Compte Rendu Collecte" , JOptionPane.QUESTION_MESSAGE));
		double volume = Integer.parseInt(JOptionPane.showInputDialog(null, "Volume des déchets collectés (en m3)",  " Gestion des Collectes - Compte Rendu Collecte" , JOptionPane.QUESTION_MESSAGE));
		String remarque = JOptionPane.showInputDialog(null, "Remarque sur la collecte",  " Gestion des Collectes - Compte Rendu Collecte" , JOptionPane.QUESTION_MESSAGE);
		
		String[] liste = { "Decharge Municipale de Toutlemonde", "Decharge Municipale d'Yzernay", "Decharge Municipale de Valleraugue", "Decharge de Génolhac"};
		String nomDecharge = (String)JOptionPane.showInputDialog(null, "Veuillez indiquer la décharge",  " Gestion des Collectes - A planifier", 
				JOptionPane.QUESTION_MESSAGE,null, liste, liste[3]);

		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT adresse FROM decharge WHERE nom = ?");
			state.setString(1, nomDecharge);
			ResultSet result = state.executeQuery();
			
			Secteur secteur = new Secteur(idsecteur);
			Decharge decharge = new Decharge(nomDecharge , result.getObject(1).toString());
			Collecte collecte = new Collecte(statut,secteur,decharge,date,temps,poids,volume,remarque);
			state.close();
			collecte.compteRendu();
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Permet à l'utilsateur d'annuler une collecte en renseignant la date, le secteur et la raison de l'annulation
	 * 
	 * @see Collecte#annulerCollecte()
	 * @see Collecte#Collecte(String, Secteur, String)
	 * @see Secteur#Secteur(int)
	 * @since 1.0
	 * 
	 */
	
	public static void annulerUneCollecte() {
		
		String date = JOptionPane.showInputDialog(null, "Date de la collecte (au format JJ/MM/AAAA)", " Gestion des Collectes - Annulation d'une Collecte" , JOptionPane.QUESTION_MESSAGE);
		int idsecteur = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du secteur",  " Gestion des Collectes - Annulation d'une Collecte" , JOptionPane.QUESTION_MESSAGE));
		String remarque = JOptionPane.showInputDialog(null, "Remarque sur la collecte",  " Gestion des Collectes - Annulation d'une Collecte" , JOptionPane.QUESTION_MESSAGE);
		
		Secteur secteur = new Secteur(idsecteur);
		Collecte collecte = new Collecte(date, secteur, remarque);
		collecte.annulerCollecte();
	}
	
	
	
	/**
	 * Indique à l'utilistateur les secteurs à collecter  à un jour J
	 * 
	 * @see Secteur#jourSecteur(String)
	 * @since 1.0
	 * 
	 */
	
	public static void collecteAPlanifier() {
		
		String[] type= { "lundi", "mardi", "mercredi", "jeudi", "vendredi" };
		String jour = (String)JOptionPane.showInputDialog(null, "Veuillez indiquer le jour de collecte",  " Gestion des Collectes - A planifier", 
				JOptionPane.QUESTION_MESSAGE,null, type, type[4]);
		Secteur.jourSecteur(jour);
	}
	
	
	
	/**
	 * Affiche la liste des collectes sur une année ou sur un mois
	 * 
	 * @see Collecte#listeCollecte(boolean, String)
	 * @since 1.0
	 */
	
	public static void inventaireCollecte() {
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Liste des collectes", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ) { 
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Lites des collectes", JOptionPane.QUESTION_MESSAGE);
			Collecte.listeCollecte(true , annee);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois MM/AAAA",  "Liste des collectes ", JOptionPane.QUESTION_MESSAGE);
			Collecte.listeCollecte(false , mois);
		}
	}
		
}