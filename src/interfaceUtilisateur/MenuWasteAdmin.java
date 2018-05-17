package interfaceUtilisateur;

import javax.swing.JOptionPane;
import connexion.Connexion;


/**
 * Classe qui permet de lancer l'application WasteAdmin
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see MenuGestion
 * @see MenuStatistiques
 * @version 1.0
 * 
 */

public class MenuWasteAdmin {
	
	
	/**
	 * Lance l'application WasteAdmin
	 * 
	 * @see MenuStatistiques#menu()
	 * @see MenuGestion#menu()
	 * @since 1.0
	 * 
	 */
	
	public static void lancerWasteAdmin() {
	
		String[] type= { "Gérer l'entreprise", "Editer des statistiques" };
		int range = JOptionPane.showOptionDialog(null, "Que voulez-vous faire ?",  "Bienvenue dans WasteAdmin1.0 ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
	
		if ( range == -1) {	
			Connexion.deconnexion();
			System.exit(0);		// ferme le programme en cours
		}
		
		String str = type[range];
	
		if ( str.equals("Gérer l'entreprise") ){
			MenuGestion.menu();
		}
	
		else {
			MenuStatistiques.menu();
		}
	}

	
}