package interfaceGestion;

import javax.swing.JOptionPane;
import gestion.Conducteur;
import gestion.Eboueur;
import gestion.Equipe;
import gestion.Ramasseur;
import gestion.Secteur;


/**
 * Cette classe permet à l'utilisateur de gérer les équipes.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see Conducteur
 * @version 1.0
 * 
 */


public class GestionEquipe {

	
	
	/**
	* Permet de créer une équipe
	* 
	* @see Conducteur#Conducteur(String, String, String)
	* @see Ramasseur#Ramasseur(String, String)
	* @see Eboueur#eboueurOccupe()
	* @see Eboueur#existenceEboueur()
	* @see Equipe#Equipe(Conducteur, Ramasseur, Ramasseur, Secteur, Secteur, boolean)
	* @see Equipe#ajouterEquipe(Equipe)
	* @since 1.0
	* 
	*/
		
	public static void creerUneEquipe() {
			
		String nomC = JOptionPane.showInputDialog(null, "Nom du conducteur", " Gestion des Equipes - Création" , JOptionPane.QUESTION_MESSAGE);
		String prenomC = JOptionPane.showInputDialog(null, "Prénom du conducteur",  " Gestion des Equipes - Création", JOptionPane.QUESTION_MESSAGE);
		String dateC = JOptionPane.showInputDialog(null, "Date du permis du conducteur",  " Gestion des Equipes - Création", JOptionPane.QUESTION_MESSAGE);
		Conducteur conducteur = new Conducteur(nomC , prenomC, dateC);
		conducteur.existenceEboueur();
		conducteur.eboueurOccupe();
			
		String nomR1 = JOptionPane.showInputDialog(null, "Nom du ramasseur 1", " Gestion des Equipes - Création" , JOptionPane.QUESTION_MESSAGE);
		String prenomR1 = JOptionPane.showInputDialog(null, "Prénom du ramasseur 1",  "Gestion des Equipes - Création", JOptionPane.QUESTION_MESSAGE);
		Ramasseur ramasseur1 = new Ramasseur(nomR1, prenomR1);
		ramasseur1.existenceEboueur();
		ramasseur1.eboueurOccupe();
			
		String nomR2 = JOptionPane.showInputDialog(null, "Nom du ramasseur 2", " Gestion des Equipes - Création" , JOptionPane.QUESTION_MESSAGE);
		String prenomR2 = JOptionPane.showInputDialog(null, "Prénom du ramasseur 2",  " Gestion des Equipes - Création ", JOptionPane.QUESTION_MESSAGE);
		Ramasseur ramasseur2 = new Ramasseur(nomR2, prenomR2);
		ramasseur2.existenceEboueur();
		ramasseur2.eboueurOccupe();
			
		Secteur secteur1 = new Secteur(0);
		Secteur secteur2 = new Secteur(0);
			
		Equipe equipe = new Equipe(conducteur,ramasseur1, ramasseur2, secteur1, secteur2, true);
		Equipe.ajouterEquipe(equipe);
	}
		
	
	
	/**
	* Permet de supprimer une equipe
	* 
	* @see Equipe#Equipe(int)
	* @see Equipe#supprimerEquipe()
	* @since 1.0
	* 
	*/
		
	public static void supprimerUneEquipe() {
			
		int idequipe = Integer.parseInt(JOptionPane.showInputDialog(null, "Identifiant de l'Equipe", " Gestion des Equipes" , JOptionPane.QUESTION_MESSAGE));
		Equipe equipe = new Equipe(idequipe);
		equipe.supprimerEquipe();	
	}
		
		
	/**
	* Permet d'affecter un secteur à une équipe
	* 
	* @see Equipe#Equipe(int)
	* @see Equipe#equipeOccupee()
	* @see Equipe#affectationSecteur(Secteur)
	* @see Secteur#Secteur(int)
	* @see Secteur#secteurOccupe()
	* @since 1.0
	* 
	*/
		
	public static void affecterSecteur() {
			
		int idequipe = Integer.parseInt(JOptionPane.showInputDialog(null, "Identifiant de l'Equipe", " Gestion des Equipes" , JOptionPane.QUESTION_MESSAGE));
		int idsecteur = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du secteur",  "Gestion des Equipes" , JOptionPane.QUESTION_MESSAGE));
			
		Equipe equipe = new Equipe(idequipe);
		Secteur secteur = new Secteur(idsecteur);
			
		secteur.secteurOccupe(); 		// on vérifie que le secteur n'est pas déjà attribué à une équipe
		equipe.equipeOccupee(); 		// on vérifie que l'équipe ne possède pas déjà 2 secteurs de collectes
		equipe.affectationSecteur(secteur);		
	}
			

}