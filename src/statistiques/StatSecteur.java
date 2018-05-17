package statistiques;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import connexion.Connexion;
import gestion.Secteur;
import interfaceGraphique.Graphique;


/**
 * Permet de réaliser diverses statistiques sur les secteurs
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see connexion.Connexion
 * @version 1.0
 *
 */


public class StatSecteur {


	
	/**
	 * Retourne le poids des déchets récoltés sur un secteur sur une période
	 * 
	 * @param secteur
	 * @param periode - True si annuel, False si mensuel
	 * @param date
	 * @param typeCollecte
	 * @return double correspondant au poids
	 * @since 1.0
	 *
	 */
	
	public static double poids(Secteur secteur, boolean periode, String date, String typeCollecte) {
		
		double poids = 0.0;

		try {
				
			String datum = "";
				
			if ( periode ) {
				datum = "de l'année";
			}
			
			else {
				datum = "du mois";
			}
				
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT sum(poids) FROM collecte WHERE secteur = ? "
					+ "AND nature = ? AND date LIKE '%" + date +"'");
				
			state.setInt(1, secteur.getId());
			state.setString(2, typeCollecte);
			ResultSet result = state.executeQuery();
				
			String str = result.getDouble(1) + " kg de déchets de type " + typeCollecte +" ont été récoltés dans le secteur " +secteur.getId() + " au cours " + datum +" " + date +".";
			System.out.println(str);
				
			poids = result.getDouble(1) ;
				
			result.close();
			state.close();
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return poids;
	}
	

	
	/**
	 * Retourne le volume des déchets récoltés sur un secteur sur une période
	 * 
	 * @param secteur
	 * @param periode - True si annuel, False si mensuel
	 * @param date
	 * @return double correspondant au volume
	 * @since 1.0
	 *
	 */
	
	public static double volume(Secteur secteur, boolean periode, String date) { //prb avec connexions
		
		double volume = 0.0;
			
		try {
				
			String datum = "";
				
			if ( periode ) {
				datum = "de l'année";	
			}
			
			else {
				datum = "du mois";
				}
				
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT sum(volume) FROM collecte WHERE secteur = ? AND date LIKE '%" + date +"'");
			state.setInt(1, secteur.getId());

			ResultSet result = state.executeQuery();
			volume = result.getDouble(1);
			String str = volume + " m³ de déchets ont été récoltés dans le secteur " + secteur.getId() + " au cours "+datum +" " + date +".";
		
			result.close();
			state.close();
			System.out.println(str);
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}

		return volume;
	}
	
	
	
	/**
	 * Affiche le poids des déchets récoltés par secteur sous forme de graphique
	 * 
	 * @param periode
	 * @param date
	 * @param typeCollecte
	 * @see interfaceGraphique.Graphique
	 * @see StatSecteur#poids(Secteur, boolean, String, String)
	 * @see Secteur#Secteur(int)
	 * @since 1.0
	 * 
	 */
	
	public static void graphe(boolean periode , String date, String typeCollecte) {
		
		List<Double> listepoids = new ArrayList<Double>();
		List<String> idsecteurs = new ArrayList<String>();
		List<String> data = new ArrayList<String>();
		
		String datum = "";
		
		if ( periode ) {
			datum = "l'année";
		}
	
		else {
			datum = "le mois";
		}
		
		for (int i=1 ; i<=16 ; i++) {
			Secteur secteur = new Secteur(i);
			idsecteurs.add(Integer.toString(i));
			listepoids.add(poids(secteur, periode, date,typeCollecte));
		}
	
		data.add("poids");
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(1000,10,1000,1000);
		Graphique g = new Graphique("Poids des déchets de type "+ typeCollecte + " récoltés en fonction des secteurs sur " + datum + " " + date, 
				"Secteurs", "Poids de déchets (en kg) ", listepoids, Color.white, data, idsecteurs, false);
		f.add(g);
		f.setVisible(true);	
	}
	
	
	
	/**
	 * Retourne le nombre de collectes annulées pour un secteur
	 * 
	 * @param secteur
	 * @param periode
	 * @param date
	 * @return double le nombre de collectes annulées
	 * @since 1.0
	 * 
	 */
	
	public static double collecteAnnuleeParSecteur(Secteur secteur , boolean periode , String date) {
	
		double nbrannulee = 0;
	
		try {
				
			String datum = "";
				
			if ( periode ) {
				datum = "de l'année";
			}
			
			else {
				datum = "du mois";
					
			}
				
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT count(*) FROM collecte WHERE secteur = ? AND statut = 'Annulee' AND date LIKE '%" + date +"'");
			state.setInt(1, secteur.getId());

			ResultSet result = state.executeQuery();
			nbrannulee = result.getDouble(1);
			String str = nbrannulee + " collecte(s) annulée(s) dans le secteur " +secteur.getId() + " au cours " + datum + " " + date +".";
			System.out.println(str);
				
			result.close();
			state.close();	
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return nbrannulee;	
	}
	
	
	
	/**
	 * Affiche le nombre de collectes annulées par secteur pendant une période sous forme de graphique
	 * 
	 * @param periode
	 * @param date
	 * @see StatSecteur#collecteAnnuleeParSecteur(Secteur, boolean, String)
	 * @see Graphique
	 * @see Secteur#Secteur(int)
	 * @since 1.0
	 */
	
	public static void collecteAnnulee(boolean periode , String date) {
		
		List<Double> collecteannulee = new ArrayList<Double>();
		List<String> idsecteurs = new ArrayList<String>();
		List<String> data = new ArrayList<String>();
		
		String datum = "";
		
		if ( periode ) {
			datum = "de l'année";
		}
	
		else {
			datum = "du mois";
		}
		
		for (int i=1 ; i<=16 ; i++) {
			Secteur secteur = new Secteur(i);
			idsecteurs.add(Integer.toString(i));
			collecteannulee.add(collecteAnnuleeParSecteur(secteur, periode, date));		
		}
					
		data.add("collecteannulee");
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(500,10,500,500);
		Graphique g = new Graphique("Nombre de collectes annulées par secteur au cours "+datum +" "+ date, "Secteurs", "Poids de déchets (en kg)", collecteannulee, Color.white, data, idsecteurs, false);
		f.add(g);
		f.setVisible(true);	
	}
	
		
}