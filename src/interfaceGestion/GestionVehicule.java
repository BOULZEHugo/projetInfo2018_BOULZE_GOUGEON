package interfaceGestion;

import javax.swing.JOptionPane;
import gestion.Vehicule;


/**
 * 
 * Permet à l'utilisateur de gérer les véhicules de l'entreprise.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see Vehicule
 * @version 1.0
 *
 */

public class GestionVehicule {

	/**
	 * Permet d'ajouter un vehicule à l'entreprise
	 * 
	 * @see Vehicule#Vehicule(String, String)
	 * @see Vehicule#ajouterVehicule()
	 * @since 1.0
	 * 
	 */
	
	public static void creerUnVehicule() {
		
		String[] type= { "Benne", "Basculeur" };
		int range = JOptionPane.showOptionDialog(null, "Renseignez le type de véhicule",  " Gestion des Véhicules - Création", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, type, type[1]);
		String str = type[range];
		String immatriculation = JOptionPane.showInputDialog(null, "Immatriculation du véhicule (format XX-XXX-XX)", " Gestion des Véhicules - Création" , JOptionPane.QUESTION_MESSAGE);
		
		Vehicule vehicule = new Vehicule(immatriculation,str);
		vehicule.ajouterVehicule();		
	}
	
	

	/**
	 * Permet de supprimer un vehicule de l'entreprise
	 * 
	 * @see Vehicule#Vehicule(String)
	 * @see Vehicule#ajouterVehicule()
	 * @see Vehicule#existenceVehicule()
	 * @since 1.0
	 * 
	 */
	
	public static void supprimerUnVehicule() {
		
		String immatriculation = JOptionPane.showInputDialog(null, "Immatriculation du véhicule", " Gestion des Véhicules - Supression" , JOptionPane.QUESTION_MESSAGE);
		Vehicule vehicule = new Vehicule(immatriculation);
		vehicule.existenceVehicule();
		vehicule.supprimerVehicule();	
	}
	
	
}