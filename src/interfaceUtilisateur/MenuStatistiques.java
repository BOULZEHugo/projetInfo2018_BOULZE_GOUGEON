package interfaceUtilisateur;

import javax.swing.JOptionPane;
import interfaceStatistiques.StatistiquesCollecte;
import interfaceStatistiques.StatistiquesEquipe;
import interfaceStatistiques.StatistiquesSecteur;


/**
 * Menu statistiques de WasteAdmin
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see StatistiquesSecteur
 * @see StatistiquesEquipe
 * @see StatistiquesCollecte
 * @version 1.0
 *
 */


public class MenuStatistiques {
	
	
	/**
	 * Permet d'ouvrir le menu statistiques de WasteAdmin	 
	 *
	 * @see #menuStatCollecte()
	 * @see #menuStatEquipe()
	 * @see #menuStatSecteur()
	 * @since 1.0
	 *
	 */
	
	public static void menu() {
		
		String[] type= {"Equipe", "Collecte", "Secteur"};
		String str = (String)JOptionPane.showInputDialog(null, "Sur quoi voulez vous éditer des statistiques ?",  "Statistiques", JOptionPane.QUESTION_MESSAGE,null, type, type[2]);
		
		if ( str.equals("Equipe") ) {
			menuStatEquipe();
		}	
		
		if ( str.equals("Collecte") ) {
			menuStatCollecte();
		}	
		
		if ( str.equals("Secteur") ) {
			menuStatSecteur();
		}	
	}

	
	
	/**
	 * Permet d'ouvrir le menu secteurs du menu statistiques	 
	 *
	 * @see StatistiquesSecteur#poidsParSecteur()
	 * @see StatistiquesSecteur#volumeParSecteur()
	 * @see StatistiquesSecteur#poidsToutSecteur()
	 * @see StatistiquesSecteur#collecteAnnuleeSecteur()
	 * @since 1.0
	 *
	 */
	
	public static void menuStatSecteur() {
		
		String[] type= { "Poids des déchets par secteur" , "Volume des déchets par secteur","Poids pour tous les secteurs", "Nombre de collectes annulées par secteur"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous faire ?",  "Statistiques- Secteurs", JOptionPane.QUESTION_MESSAGE,null, type, type[3]);
		
		if ( str.equals( "Poids des déchets par secteur" )) {
			StatistiquesSecteur.poidsParSecteur();
		}	
		
		if ( str.equals("Volume de déchets par secteur") ) {
			StatistiquesSecteur.volumeParSecteur();
		}	
		
		if ( str.equals("Poids pour tous les secteurs") ) {
			StatistiquesSecteur.poidsToutSecteur();
		}	
		
		if ( str.equals("Nombre de collectes annulées par secteur") ) {
			StatistiquesSecteur.collecteAnnuleeSecteur();
		}
	}
	
	
	
	/**
	 * Permet d'ouvrir le menu collectes du menu statistiques
	 * 
	 * @see StatistiquesCollecte#poidsCollecte()
	 * @see StatistiquesCollecte#volumeCollecte()
	 * @see StatistiquesCollecte#ratioCollecteEffectuee()
	 * @see StatistiquesCollecte#distanceAnnuelle()
	 * @since 1.0
	 *
	 */
	
	public static void menuStatCollecte() {
		
		String[] type= { "Poids des déchets collectés" , "Volume des déchets collectés", "Pourcentage des collectes effectuées", "Distance parcourue en une année"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous faire ?",  " Statistiques - Collectes", JOptionPane.QUESTION_MESSAGE,null, type, type[3]);
		
		if ( str.equals("Poids des déchets collectés" )) {
			StatistiquesCollecte.poidsCollecte();
		}	
		
		if ( str.equals("Volume des déchets collectés") ) {
			StatistiquesCollecte.volumeCollecte();
		}
		
		if ( str.equals("Pourcentage des collectes effectuées") ) {
			StatistiquesCollecte.ratioCollecteEffectuee();
		}
		
		if ( str.equals("Distance parcourue en une année") ) {
			StatistiquesCollecte.distanceAnnuelle();
		}	
	}
	
	
	
	/**
	 * Permet d'ouvrir le menu équipes du menu statistiques	 
	 *
	 * @see StatistiquesEquipe#tempsTravailEquipe()
	 * @see StatistiquesEquipe#nombreCollecteEquipe()
	 * @since 1.0
	 *
	 */
	
	public static void menuStatEquipe() {
		
		String[] type= { "Nombre de collectes réalisées en une année" , "Temps de travail"};
		String str = (String)JOptionPane.showInputDialog(null, "Que voulez vous faire ?",  "Statistiques - Equipes", JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		
		if ( str.equals("Nombre de collectes réalisées en une année")) {
			StatistiquesEquipe.nombreCollecteEquipe();
		}	
		
		if ( str.equals("Temps de travail") ) {
			StatistiquesEquipe.tempsTravailEquipe();
		}	
	}
	
	
}