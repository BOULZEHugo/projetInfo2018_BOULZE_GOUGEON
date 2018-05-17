package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import connexion.Connexion;
import interfaceGraphique.Tableau;


/**
 * 
 * Classe Vehicule permettant de gérer les véhicules de l'entreprise.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see connexion.Connexion
 * @version 1.0
 *
 */


public class Vehicule {
	
	
	/**
	 * Numéro d'immatriculation unique du véhicule
	 */
	private String immatriculation;
	
	/**
	 * Type du véhicule (benne, basculeur)
	 */
	private String typeVehicule;
	
	
	
	/**
	 * Constructeur à partir de l'immatriculation du véhicule.
	 * 
	 * @param immatriculation
	 * @since 1.0
	 * 
	 */
	
	public Vehicule(String immatriculation) {
		super();
		this.immatriculation = immatriculation;
	}

	
	
	/**
	 * Constructeur à partir de l'immatriculation et le type du véhicule
	 * 
	 * @param immatriculation
	 * @since 1.0
	 * 
	 */

	public Vehicule(String immatriculation, String typeVehicule) {
		super();
		this.immatriculation = immatriculation;
		this.typeVehicule = typeVehicule;

	}
	

	
	// Getters et Setters de la classe
	
	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}


	
	/**
	 * Permet d'ajouter un véhicule dans la BDD
	 * 
	 * @since 1.0
	 * 
	 */
	
	public void ajouterVehicule() {
		
		try {
		
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("INSERT INTO vehicule(type,immatriculation) VALUES(?,?)");
			
			state.setString(1, this.typeVehicule);
			state.setString(2, this.immatriculation);
			state.executeUpdate();
			state.close();
			
			JOptionPane.showMessageDialog(null, "Ajout du véhicule effectué avec succès !", "Gestion des Véhicules", JOptionPane.INFORMATION_MESSAGE);
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}	   
	}
	
	
	
	/**
	 * Permet de supprimer un véhicule dans la BDD
	 * 
	 * @since 1.0
	 * 
	 */
	
	public void supprimerVehicule() {
		
		try {
		
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("DELETE FROM vehicule WHERE immatriculation = ?");
			state.setString(1, this.immatriculation);	
		
			int option = JOptionPane.showConfirmDialog(null, "Le véhicule que vous vous voulez supprimer est : \n " + this.toString()
					+ "\n\n\n Voulez vous continuer ? \n\n", "Gestion des vehicules - Supression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (option == 1 || option == 2) {
				state.close();
				JOptionPane.showMessageDialog(null, "Vous avez annulé la supression !", "Gestion des véhicules - Supression", JOptionPane.INFORMATION_MESSAGE);
			}

			state.executeUpdate();
			state.close();

			JOptionPane.showMessageDialog(null, "Supression du véhicule faite avec succès !", "Gestion des Véhicules - Supression", JOptionPane.INFORMATION_MESSAGE);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Permet de verifier si un véhicule est déjà utilisé pour une collecte dans la journée 
	 * 
	 * @param date - jour de collecte
	 * @since 1.0
	 * 
	 */
	
	public void vehiculeUtilise( String date) {
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT COUNT(*) FROM collecte WHERE collecte.vehicule = ? AND collecte.date = ? ");
			state.setString(1, this.immatriculation);	
			state.setString(2, date);	
			ResultSet result = state.executeQuery();
			
			if (result.getInt(1) != 0 ) {
				JOptionPane.showMessageDialog(null, "Véhicule déjà affecté ce " + date +" !", "Gestion des Véhicules", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			
			state.close();
			result.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	
	
	/**
	 * Permet de verifier si le vehicule existe
	 * 
	 * @since 1.0
	 * 
	 */
	 
	public void existenceVehicule(){
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT count(*) FROM vehicule WHERE immatriculation LIKE ?");
			
			state.setString(1, this.immatriculation);		
			ResultSet result = state.executeQuery();
					
			if ( result.getInt(1) != 0 ) {
				result.close();
				state.close();			
			}
			
			else { 
				result.close();
				state.close();
				JOptionPane.showMessageDialog(null, "Le véhicule n'existe pas !", " Gestion des Véhicules" , JOptionPane.ERROR_MESSAGE);	
				System.exit(0);
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Permet d'afficher les caractéristiques d'un véhicule 
	 * 
	 * @return str
	 * @since 1.0
	 * 
	 */
	
	
	@Override
	public String toString(){
		String str = "";
		str += "N° : " + this.immatriculation + "\n" ;
		return str;
	}
	
	
	
	/**
	 * 
	 * Permet d'obtenir la liste des vehicules de l'entreprise
	 * 
	 * @see Tableau#Tableau(String, String[], Object[][])
	 * since 1.0
	 * 
	 */
	
	 public static void listeVehicule() {
			
		try {
					
			String titre = "Liste des véhicules de l'entreprise ";
					
			Connection connection = Connexion.getInstance();
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM vehicule ");
			ResultSetMetaData resultMeta = result.getMetaData();
					
			int taille = resultMeta.getColumnCount();
			String[] titreColonne = new String [taille];
					
			for (int i = 1; i <= taille; i++) {
				titreColonne[i-1] = resultMeta.getColumnName(i); // i-1 car les tableaux démarrent à 0
			}

			Object [][] donnees= new Object[10][taille];
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