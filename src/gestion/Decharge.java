package gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import connexion.Connexion;
import interfaceGraphique.Tableau;


/**
 * Classe Decharge qui permet d'accéder aux caractéristiques des décharges partenaires.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @version 1.0
 * 
 */


public class Decharge {
	
	
	/**
	 * Nom de la décharge
	 */
	private String nom;
	
	/**
	 * Adresse de la décharge
	 */
	private String adresse;
	
	
	
	// Constructeur de la classe
	
	public Decharge(String nom, String adresse) {
		super();
		this.nom     = nom;
		this.adresse = adresse;
	}

	
	
	// Getters et Setters de la classe
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
	/**
	 * Permet d'afficher les caractéristiques d'une décharge.
	 * 
	 * @return str 
	 * @since 1.0
	 * 
	 */
	
	@Override
	public String toString(){
		
		String str = "";
		str += "Information déchetterie : \n \n" + this.adresse + "\n" + this.adresse;
		
		return str;
	}
	
	
	
	/**
	 * 
	 * Permet d'obtenir la liste des décharges gérées par l'entreprise.
	 * 
	 * @see Tableau#Tableau(String, String[], Object[][])
	 * since 1.0
	 * 
	 */
	
	 public static void listeDecharge() {
				
		try {
			
			String titre = "Liste des décharges gérées par l'entreprise ";
					
			Connection connection = Connexion.getInstance();
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM decharge ");
			ResultSetMetaData resultMeta = result.getMetaData();
					
			int taille = resultMeta.getColumnCount();
			String[] titreColonne = new String [taille];
					
			for (int i = 1; i <= taille; i++) {
				titreColonne[i-1] = resultMeta.getColumnName(i); 	// i-1 car les tableaux démarrent à 0
			}

			Object [][] donnees= new Object[4][taille];
			int compteur = 0;
					
			while (result.next()) { 
						
				for (int i = 1; i <= taille ; i++) { 
						donnees[compteur][i-1] = result.getObject(i);
				}
						
				compteur +=1;	
			}
				
			result.close();
			state.close();
					
			new Tableau(titre,titreColonne,donnees).setVisible(true);
		}
				
		catch (SQLException e) {
			e.printStackTrace(); 
		}
	}			
				
	 
}