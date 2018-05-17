package interfaceGestion;

import javax.swing.JOptionPane;
import gestion.Conducteur;
import gestion.Eboueur;
import gestion.Equipe;
import gestion.Ramasseur;


/**
 * Cette classe permet à l'utilisateur de gérer les éboueurs.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see Equipe
 * @see Eboueur
 * @version 1.0
 * 
 */


public class GestionEboueur {
	
	
	
	/**
	 * Permet d'ajouter un éboueur à l'entreprise
	 * 
	 * @see Equipe#Equipe(int)
	 * @see Conducteur#Conducteur(String, String, boolean, String, Equipe, String)
	 * @see Ramasseur#Ramasseur(String, String, boolean, String, Equipe)
	 * @see Eboueur#ajouterEboueur(String)
	 * @since 1.0
	 * 
	 */
	
	public static void creerUnEboueur() {
		
		String datePermis = "";
		String[] type= { "Conducteur", "Ramasseur" };
		int range = JOptionPane.showOptionDialog(null, "Renseignez le type d'éboueur",  " Gestion des Eboueurs - Création", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		
		String str = type[range];
		String nom = JOptionPane.showInputDialog(null, "Nom de l'éboueur", " Gestion des Eboueurs - Création" , JOptionPane.QUESTION_MESSAGE);
		String prenom = JOptionPane.showInputDialog(null, "Prénom de l'éboueur",  " Gestion des Eboueurs - Création", JOptionPane.QUESTION_MESSAGE);
		
		boolean disponibilite = true;
		String dateArrivee = JOptionPane.showInputDialog(null, "Date d'arrivée dans l'entreprise",  " Gestion des Eboueurs - Création", JOptionPane.QUESTION_MESSAGE);
		Equipe equipe = new Equipe(0);		//par défaut l'éboueur n'a pas d'équipe à son arrivée
		
		if ( str == "Conducteur") {
			datePermis = JOptionPane.showInputDialog(null, "Date du permis du conducteur",  " Gestion des Eboueurs - Création", JOptionPane.QUESTION_MESSAGE);
			Eboueur conducteur = new Conducteur(nom,prenom,disponibilite,dateArrivee, equipe, datePermis);
			conducteur.ajouterEboueur(datePermis);
		}
		
		else {
			datePermis = "0";
			Eboueur ramasseur = new Ramasseur(nom, prenom, disponibilite, dateArrivee, equipe);
			ramasseur.ajouterEboueur(datePermis);
		}	
	}
	
	

	
	/**
	 * Permet de supprimer un éboueur de l'entreprise
	 * 
	 * @see Equipe#Equipe(int)
	 * @see Conducteur#Conducteur(String, String)
	 * @see Ramasseur#Ramasseur(String, String, boolean, String, Equipe)
	 * @see Eboueur#ajouterEboueur(String)
	 * @see Eboueur#existenceEboueur()
	 * @since 1.0
	 * 
	 */

	public static void supprimerUnEboueur() {
	
		String nom = JOptionPane.showInputDialog(null, "Nom de l'éboueur", " Gestion des Eboueurs - Suppression" , JOptionPane.QUESTION_MESSAGE);
		String prenom = JOptionPane.showInputDialog(null, "Prénom de l'éboueur",  " Gestion des Eboueurs - Supression", JOptionPane.QUESTION_MESSAGE);
		Eboueur eboueur = new Ramasseur(nom,prenom); 	//peu importe le type d'éboueur, son nom et prénom suffir pour le supprimer
		eboueur.existenceEboueur();
		eboueur.eboueurOccupe();
		eboueur.supprimerEboueur();
	}
	
	
	
	/**
	 * 
	 * Permet de calculer le salaire d'un éboueur
	 * 
	 * @see Conducteur#Conducteur(String, String)
	 * @see Ramasseur#Ramasseur(String, String)
	 * @see Conducteur#salaire(int)
	 * @see Ramasseur#salaire(int)
	 * @see Eboueur#existenceEboueur()
	 * @since 1.0
	 * 
	 */
	
	public static void salaireEboueur() {
		
		String nom = JOptionPane.showInputDialog(null, "Nom de l'éboueur", " Gestion des Eboueurs - Salaire" , JOptionPane.QUESTION_MESSAGE);
		String prenom = JOptionPane.showInputDialog(null, "Prénom de l'éboueur",  " Gestion des Eboueurs - Salaire", JOptionPane.QUESTION_MESSAGE);
		int annee = Integer.parseInt(JOptionPane.showInputDialog(null, "Année actuelle", " Gestion des Eboueurs - Salaire" , JOptionPane.QUESTION_MESSAGE));
		
		String[] type= { "Conducteur", "Ramasseur" };
		int range = JOptionPane.showOptionDialog(null, "Renseignez le type d'éboueur",  " Gestion des Eboueurs - Salaire", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Conducteur") ) {
			Eboueur conducteur = new Conducteur(nom,prenom);
			conducteur.existenceEboueur();
			conducteur.salaire(annee);
		}
		
		else {
			Eboueur ramasseur = new Ramasseur(nom, prenom);
			ramasseur.existenceEboueur();
			ramasseur.salaire(annee);
		}
	}
		
}