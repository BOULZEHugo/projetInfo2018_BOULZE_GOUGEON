package statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import connexion.Connexion;


/**
*
* Permet de réaliser diverses statistiques sur les collectes.
*
* @author Hugo Boulze, Matthieu Gougeon
* @see connexion.Connexion
* @version 1.0
*
*/


public class StatCollecte {
	
	
	
	/**
	 * Permet de connaître le poids des déchets collectés par l'entreprise sur une période
	 * 
	 * @param periode - True si annuel, False si mensuel
	 * @param date
	 * @since 1.0
	 * 
	 */
	
	public static void poids(boolean periode, String date) {
		
		try {
				
			String datum = "";
				
			if ( periode ) {
				datum = "de l'année";
			}
			
			else {
				datum = "du mois";
			}
				
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT sum(poids) FROM collecte WHERE date LIKE '%" + date +"'");
			ResultSet result = state.executeQuery();
			
			String str = result.getDouble(1) + " kg de déchets ont été récoltés au cours " + datum + " " + date + ".";
			JOptionPane.showMessageDialog(null, str, "Statistiques - Collectes", JOptionPane.INFORMATION_MESSAGE);
				
			result.close();
			state.close();
		}
				
		catch (SQLException e) {
			e.printStackTrace();	
		}	
		
	}
	
	
	/**
	 * Permet de connaître le volume des déchets collectés par l'entreprise sur une période
	 * 
	 * @param periode - True si annuel, False si mensuel
	 * @param date
	 * @since 1.0
	 * 
	 */
	
	public static void volume(boolean periode, String date) {
			
		try {
				
			String datum = "";
				
			if ( periode ) {
				datum = "de l'année";
			}
			
			else {
				datum = "du mois";	
			}
				
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT sum(volume) FROM collecte WHERE date LIKE '%" + date +"'");
			ResultSet result = state.executeQuery();
				
			String str = result.getDouble(1) + " m³ de déchets ont été récoltés au cours "+ datum +" " + date +".";
			JOptionPane.showMessageDialog(null, str, "Statistiques - Collectes", JOptionPane.INFORMATION_MESSAGE);
				
			result.close();
			state.close();	
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Permet de connaître le ratio de collectes effectuées sans encombre par l'entreprise sur une période
	 * 
	 * @param periode - True si annuel, False si mensuel
	 * @param date
	 * @since 1.0
	 * 
	 */
	
	
	public static void collecteEffectuee(boolean periode, String date) {
		
		try {
				 
			String datum = "";
				
			if ( periode) {
				datum = "l'année";
			}
				
			else {
				datum = "le mois";
			}
				
			Connection connection = Connexion.getInstance();
			PreparedStatement stateEffectuee = connection.prepareStatement("SELECT count(*) FROM collecte WHERE statut = 'Effectuee' AND date LIKE '%" + date +"'");
			ResultSet resultEffectuee = stateEffectuee.executeQuery();
			double effectuee = resultEffectuee.getInt(1);
				
			PreparedStatement stateTotale = connection.prepareStatement("SELECT count(*) FROM collecte WHERE date LIKE '%" + date +"'");
			ResultSet resultTotale = stateTotale.executeQuery();
			double total = resultTotale.getInt(1);
				
			double ratio = (effectuee / total)*100;
			
			DecimalFormat pourcentage = new DecimalFormat();
			pourcentage.setMaximumFractionDigits(2);		// pour afficher le pourcentage jusqu'à 2 chiffres après la virgule
			
			String str =  pourcentage.format(ratio) + " % de collectes effectuées sans problèmes sur " + datum + " "+ date ;
			
			JOptionPane.showMessageDialog(null, str, "Statistiques - Collectes", JOptionPane.INFORMATION_MESSAGE);
				
			resultEffectuee.close();
			stateEffectuee.close();
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Permet de connaître la distance totale parcourue pendant les collectes sur une année
	 * 
	 * @param annee - choix de l'année
	 * @since 1.0
	 * 
	 */
	
	public static void distance(String annee) {
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT SUM(secteur.distance_trajet) FROM secteur,collecte "
					+ "WHERE collecte.secteur = secteur.id AND collecte.statut = 'Effectuee' OR 'Inachevee' AND collecte.date LIKE '%" + annee + "'");
			ResultSet result = state.executeQuery();
			
			double distance  = result.getDouble(1);
			
			DecimalFormat longueur = new DecimalFormat();
			longueur.setMaximumFractionDigits(1);		// pour afficher 1 chiffre après le virgule
			
			String str = longueur.format(distance) + " km parcourus sur l'année " + annee ;
			JOptionPane.showMessageDialog(null, str, "Statistiques - Collectes", JOptionPane.INFORMATION_MESSAGE);
			
			result.close();
			state.close();
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
}