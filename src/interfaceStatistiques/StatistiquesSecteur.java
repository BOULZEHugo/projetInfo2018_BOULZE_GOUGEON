package interfaceStatistiques;

import javax.swing.JOptionPane;
import gestion.Secteur;
import statistiques.StatSecteur;


/**
 * 
 * Permet à l'utilisateur d'éditer des statistiques sur les secteurs en appelant les méthodes de la classe StatSecteur
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see StatSecteur
 * @version 1.0
 *
 */


public class StatistiquesSecteur {

	
	
	/**
	 * Interface qui permet d'obtenir le poids des déchets récoltés par secteur
	 * 
	 * @see Secteur#Secteur(int)
	 * @see StatSecteur#poids(Secteur, boolean, String, String)
	 * @since 1.0
	 * 
	 */
	
	public static void poidsParSecteur() {
		
		int idsecteur  = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du secteur",  "Poids de déchets par secteur", JOptionPane.QUESTION_MESSAGE));
		Secteur secteur = new Secteur(idsecteur);
		
		String[] type0= { "EmballageEtPapier", "Verre" };
		int range0 = JOptionPane.showOptionDialog(null, "Veuillez indiquer le type de déchets",  "Poids de déchets par secteur", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type0, type0[1]);
		String typeCollecte = type0[range0];
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Poids de déchets par secteur", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ) { 
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Poids de déchets par secteur", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.poids(secteur, true , annee, typeCollecte);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois sous la forme MM/AAAA",  "Poids de déchets par secteur", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.poids(secteur, false , mois,typeCollecte);
		}
	}
	
	
	
	/**
	 * Permet d'obtenir le volume des déchets récoltés par secteur
	 * 
	 * @see Secteur#Secteur(int)
	 * @see StatSecteur#volume(Secteur, boolean, String)
	 * @since 1.0
	 * 
	 */
	
	public static void volumeParSecteur() {
		
		int idsecteur  = Integer.parseInt(JOptionPane.showInputDialog(null, "Numéro du secteur",  "Volume de déchets par secteur", JOptionPane.QUESTION_MESSAGE));
		Secteur secteur = new Secteur(idsecteur);
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Volume de déchets par secteur", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ) {
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Volume de déchets par secteur", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.volume(secteur, true , annee);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois sous la forme MM/AAAA",  "Volume de déchets par secteur", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.volume(secteur, false , mois);
		}
	}
	
	
	
	/**
	 * Permet d'obtenir le poids des déchets récoltés pour tous les secteurs sous forme de graphe
	 * 
	 * @see StatSecteur#graphe(boolean, String, String)
	 * @since 1.0
	 * 
	 */
	
	public static void poidsToutSecteur() {
		
		String[] type0= { "EmballageEtPapier", "Verre" };
		int range0 = JOptionPane.showOptionDialog(null, "Veuillez indiquer le type de déchets",  "Poids de déchets par secteur", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type0, type0[1]);
		String typeCollecte = type0[range0];
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Poids de déchets par secteur", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle") ){ 
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Poids Tout Secteurs", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.graphe(true , annee, typeCollecte);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du mois sous la forme MM/AAAA",  "Poids Tout Secteur", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.graphe(false , mois, typeCollecte);
		}	
	}
	
	
	
	/**
	 * Permet d'obtenir le nombre de collectes annulées par secteur 
	 * 
	 * @see StatSecteur#collecteAnnulee(boolean, String)
	 * @since 1.0
	 * 
	 */
	
	public static void collecteAnnuleeSecteur() {
		
		String[] type= { "Annuelle", "Mensuelle" };
		int range = JOptionPane.showOptionDialog(null, "Veuillez indiquer la période",  "Collecte Annulée par Secteur",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		
		if ( str.equals("Annuelle")) { 
			String annee = JOptionPane.showInputDialog(null, "Choix de l'année",  "Collecte Annulée par Secteur", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.collecteAnnulee(true , annee);
		}
		
		else { 
			String mois = JOptionPane.showInputDialog(null, "Choix du Mois MM/AAAA.",  "Collecte Annulée par Secteur", JOptionPane.QUESTION_MESSAGE);
			StatSecteur.collecteAnnulee(false , mois);
		}
	}
	

}