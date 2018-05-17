package interfaceStatistiques;

import javax.swing.JOptionPane;
import statistiques.StatCollecte;


/**
 * 
 * Permet à l'utilisateur d'éditer des statistiques sur les collectes en appelant les méthodes de la classe StatCollecte
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see StatCollecte
 * @version 1.0
 *
 */


public class StatistiquesCollecte {

	
	
	/**
	 * Permet de connaitre le poids des déchets ramassés durant les collectes pendant une période donnée.
	 * 
	 * @see StatCollecte#poids(boolean, String)
	 * @since 1.0
	 *
	 */

	public static void poidsCollecte() {
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Poids de déchets par secteur", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ) { 
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Poids de déchets des collectes", JOptionPane.QUESTION_MESSAGE);
			StatCollecte.poids(true , annee);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois MM/AAAA",  "Poids de déchets des collectes", JOptionPane.QUESTION_MESSAGE);
			StatCollecte.poids(false , mois);
		}		
	}
	
	
	
	/**
	 * Permet de connaître le volume des déchets ramassés durant les collectes pendant une période
	 * 
	 * @see StatCollecte#volume(boolean, String)
	 * @since 1.0
	 *
	 */

	public static void volumeCollecte() {
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Volume de déchets par secteur", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ) {
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Volume de déchets des collectes", JOptionPane.QUESTION_MESSAGE);
			StatCollecte.volume(true , annee);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois MM/AAAA",  "Volume de déchets des collectes", JOptionPane.QUESTION_MESSAGE);
			StatCollecte.volume(false , mois);
		}
	}
	
	
	
	/**
	 * Permet de connaître le ratio de collectes effectuées (sans problème) sur le nombre de collectes totales, pendant une période
	 * 
	 * @see StatCollecte#collecteEffectuee(boolean, String)
	 * @since 1.0
	 *
	 */
	
	public static void ratioCollecteEffectuee() {
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Ratio de collectes effectuées", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ) {
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Ratio de collectes effectuées", JOptionPane.QUESTION_MESSAGE);
			StatCollecte.collecteEffectuee(true , annee);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois MM/AAAA",  "Ratio de collectes effectuées", JOptionPane.QUESTION_MESSAGE);
			StatCollecte.collecteEffectuee(false , mois);
		}
	}
	
	
	
	/**
	 * Permet de connaître la distance parcourue sur les collectes durant une année
	 * 
	 * @see StatCollecte#distance(String)
	 * @since 1.0
	 *
	 */
	
	public static void distanceAnnuelle() {
		
		String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Volume de déchets des collectes", JOptionPane.QUESTION_MESSAGE);
		StatCollecte.distance(annee);				
	}
	
	
	
}