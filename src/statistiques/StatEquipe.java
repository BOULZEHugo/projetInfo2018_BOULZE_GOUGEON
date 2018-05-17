package statistiques;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import connexion.Connexion;
import gestion.Equipe;


/**
 *
 * Permet de réaliser diverses statistiques sur les équipes
 *
 * @author Hugo Boulze, Matthieu Gougeon
 * @see connexion.Connexion
 * @version 1.0
 *
 */


public class StatEquipe {

	
	
	/**
	 * Permet de connaître le nombre de collectes effectuées (sans encombre) par une équipe sur une année
	 * 
	 * @param equipe
	 * @param annee
	 * @since 1.0
	 * 
	 */
	
	public static void nombreCollecte(Equipe equipe, String annee) {
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT count(collecte.id) FROM collecte,secteur"
					+ " WHERE collecte.secteur = secteur.id AND secteur.equipe = ? AND collecte.statut = 'Effectuee' "
					+ "AND collecte.date LIKE '%" + annee +"'");
			state.setInt(1, equipe.getIdentifiant());
			ResultSet result = state.executeQuery();
			
			String str = result.getInt(1) + " collectes réalisées par l'équipe " + equipe.getIdentifiant() +" au cours de l'année " + annee +".";
			JOptionPane.showMessageDialog(null, str, "Statistiques - Equipes", JOptionPane.INFORMATION_MESSAGE);
			
			result.close();
			state.close();	
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * Permet de connaître le temps de travail d'une équipe sur une période
	 * 
	 * @param equipe
	 * @param periode - True si annuel, False si mensuel.
	 * @param date
	 * @since 1.0
	 * 
	 */
	
	public static void tempsTravail(Equipe equipe, boolean periode, String date) {

		try {
				
			String datum = "";
				
			if ( periode ) {
				datum = "de l'année";
			}
			
			else {
				datum = "du mois";	
			}
				
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT SUM(collecte.temps) FROM collecte,secteur"
					+ " WHERE collecte.secteur = secteur.id AND secteur.equipe = ? "
					+ "AND collecte.date LIKE '%" + date +"'");
			state.setInt(1, equipe.getIdentifiant());
			ResultSet result = state.executeQuery();
				
			// il faut convertir le resultat en heure car c'est plus parlant
			int minuteBrut= result.getInt(1)%60;
			String minute ;
				
			if (minuteBrut<10) {
				minute = "0" + Integer.toString(minuteBrut);
			}
				
			else {
				minute = Integer.toString(minuteBrut);
			}
				
			int heure = result.getInt(1)/60;
			String str = "L'équipe " + equipe.getIdentifiant() + " a travaillé pendant " +heure+"h" + minute + " au cours "+ datum+ " " + date +".";
			JOptionPane.showMessageDialog(null, str, "Statistiques - Equipes", JOptionPane.INFORMATION_MESSAGE);
			result.close();
			state.close();
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}