package wasteAdmin;

import javax.swing.JOptionPane;
import connexion.Connexion;
import interfaceUtilisateur.MenuWasteAdmin;


/**
 * Permet à l'utilisateur de lancer WasteAdmin
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see MenuWasteAdmin#lancerWasteAdmin()
 * @version 1.0
 *
 */


public class WasteAdmin {


/**
 * Lance l'application WasteAdmin en demandant l'url à l'utilisateur
 * 
 * @since 1.0 
 * 
 */
	
	public static void lancer() {
		
		try {
	
			Connexion.getInstance();
			MenuWasteAdmin.lancerWasteAdmin();
			
			String[] type= { "Oui", "Non" };
			int range = JOptionPane.showOptionDialog(null, "Voulez-vous continuer vos opérations ?",  "WasteAdmin1.0", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
			
			if ( range == -1) {	            // ferme la fenetre quand on clique sur la croix
				Connexion.deconnexion();
				System.exit(0);		
			}
			
			String str = type[range];
			
			while (str.equals("Oui")) {
				MenuWasteAdmin.lancerWasteAdmin();
				int range2 = JOptionPane.showOptionDialog(null, "Voulez-vous continuer vos opérations ?",  "WasteAdmin1.0", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
				
				if ( range2 == -1) {	
					Connexion.deconnexion();
					System.exit(0);		
				}
				
				str = type[range2];
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			Connexion.deconnexion();
		}
	}
	

	
	public static void main(String[] args) {
		lancer();
	}

	
}