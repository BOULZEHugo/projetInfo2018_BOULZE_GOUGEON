package interfaceStatistiques;

import javax.swing.JOptionPane;
import gestion.Equipe;
import statistiques.StatEquipe;


/**
 * 
 * Permet à l'utilisateur d'éditer des statistiques sur les Equipes en appelant les méthodes de la classe StatEquipe
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see StatEquipe
 * @version 1.0
 *
 */


public class StatistiquesEquipe {

	
	
	/**
	 * Permet de connaître le nombre de collectes réalisées pour une équipe pendant une année
	 * 
	 * @see StatEquipe#nombreCollecte(Equipe, String)
	 * @since 1.0
	 * 
	 */
	
	public static void nombreCollecteEquipe() {
		
		int idequipe = Integer.parseInt(JOptionPane.showInputDialog(null, "Choix de l'équipe",  "Nombre de collectes annuel par Equipe", JOptionPane.QUESTION_MESSAGE));
		String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Nombre de collectes annuel par Equipe", JOptionPane.QUESTION_MESSAGE);
		Equipe equipe = new Equipe(idequipe);
		StatEquipe.nombreCollecte(equipe, annee);
	}
	

	
	/**
	 * Permet de connaître le temps de travail d'équipe sur une période
	 * 
	 * @see StatEquipe#tempsTravail(Equipe, boolean, String)
	 * @since 1.0
	 * 
	 */
	
	public static void tempsTravailEquipe() {
		
		int idequipe = Integer.parseInt(JOptionPane.showInputDialog(null, "Choix de l'équipe",  "Temps de Travail par Equipe", JOptionPane.QUESTION_MESSAGE));
		Equipe equipe = new Equipe(idequipe);
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Temps de Travail par Equipe", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ) { 
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Temps de Travail par Equipe", JOptionPane.QUESTION_MESSAGE);
			StatEquipe.tempsTravail(equipe ,true , annee);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois MM/AAAA.",  "Temps de travail par Equipe", JOptionPane.QUESTION_MESSAGE);
			StatEquipe.tempsTravail(equipe , false , mois);
		}
	}
	
	
}