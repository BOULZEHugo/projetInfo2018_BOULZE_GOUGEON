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
 * La classe abstraite Eboueur
 * 
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see connexion.Connexion
 * @version 1.0
 *
 */


public abstract class Eboueur {
	
	
	/**
	 * Nom de famille de l'éboueur
	 */
	private String nom;
	
	/**
	 * Prénom de l'éboueur
	 */
	private String prenom;
	
	/**
	 * Dipsoniblité de l'éboueur - 1 si dipsonible et 0 sinon
	 */
	private boolean disponibilite;
	
	/**
	 * Date d'arrivée de l'éboueur dans l'entreprise
	 */
	private String date;
	
	/**
	 * Numéro d'équipe affectée
	 */
	private Equipe equipe;

	
	
	/**
	 * Constructeur avec le nom et le prenom de l'éboueur.
	 * 
	 * @param nom
	 * @param prenom
	 * @since 1.0
	 * 
	 */
	
	public Eboueur(String nom , String prenom) {
		super();
		this.nom    = nom;
		this.prenom = prenom;
	}

	
	
	/**
	 * Constructeur d'éboueur avec tous les paramètres.
	 * 
	 * @param nom
	 * @param prenom
	 * @param disponibilite - 0 Si Eboueur dispo, 1 sinon.
	 * @param date - date d'arrivee
	 * @param equipe
	 * @since 1.0
	 * 
	 */
	
	public Eboueur(String nom, String prenom, boolean disponibilite, String date, Equipe equipe) {
		super();
		this.nom           = nom;
		this.prenom        = prenom;
		this.disponibilite = disponibilite;
		this.date          = date;
		this.equipe        = equipe;
	}

	
	
	// Getters et Setters de la classe
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	

	/**
	 * Permet d'ajouter un éboueur à la BDD.
	 * 
	 * @param datePermis - si non égal à 0, on considère que l'éboueur est un conducteur.
	 * @since 1.0
	 * 
	 */
	
	public void ajouterEboueur(String datePermis){
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("INSERT INTO eboueur(nom,prenom,equipe,disponibilite,date_arrivee,date_permis)"
					+ "VALUES(?,?,?,?,?,?)");
			
			state.setString(1, this.nom);	
			state.setString(2, this.prenom);	
			state.setInt(3, this.equipe.getIdentifiant());	
			state.setBoolean(4, this.disponibilite);	
			state.setString(5, this.date);
			state.setString(6, datePermis);	
			state.executeUpdate();		
			state.close();
			}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	

	/**
	 * Permet de supprimer un éboueur a la BDD.
	 * 
	 * @since 1.0
	 * 
	 */
	
	public void supprimerEboueur(){

		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("DELETE FROM eboueur WHERE eboueur.nom = ? AND eboueur.prenom = ?");
			
			state.setString(1, this.nom);	
			state.setString(2, this.prenom);	
			
			int option = JOptionPane.showConfirmDialog(null, "L'éboueur que vous voulez supprimer est : \n " + this.nom + " " + this.prenom
					+ "\n\n\n Voulez vous continuer ? \n\n", "Gestion des véhicules - Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (option == 1 || option == 2) {
				state.close();
				JOptionPane.showMessageDialog(null, "Vous avez annulé la suppression !", "Gestion des éboueurs - Suppression", JOptionPane.INFORMATION_MESSAGE);
				
			}

			else { 
				
				state.executeUpdate();
				state.close();
			}
			
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	

	/**
	 * Permet de verifier si un éboueur est déjà attribué à une équipe.
	 * 
	 * @since 1.0
	 * @throws prb de connexion !
	 */
	
	public void eboueurOccupe() {
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT equipe FROM eboueur WHERE nom LIKE ? AND prenom LIKE ?");
			state.setString(1, this.getNom());	
			state.setString(2, this.getPrenom());	
			ResultSet result = state.executeQuery();
			
			if ( result.getInt(1) == 0) {
				result.close();
				state.close();		
			}
			
			else { 
				result.close();
				state.close();
				JOptionPane.showMessageDialog(null, "Eboueur affecté à une équipe !", " Gestion des Eboueurs" , JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}	
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Permet de verifier si l'éboueur existe.
	 * 
	 * @since 1.0
	 * 
	 */
	 
	public void existenceEboueur(){
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT count(*) FROM eboueur WHERE nom LIKE ? AND prenom LIKE ?");
			
			state.setString(1, this.nom);	
			state.setString(2, this.prenom);	
			ResultSet result = state.executeQuery();
					
			if ( result.getInt(1) != 0 ) {
				result.close();
				state.close();			
			}
			
			else { 
				result.close();
				state.close();
				JOptionPane.showMessageDialog(null, "L'éboueur n'existe pas !", " Gestion des Eboueurs" , JOptionPane.ERROR_MESSAGE);
				System.exit(0);
				
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	/**
	 * Donne le salaire d'un éboueur en fonction de son ancienneté.
	 * 
	 * @param year - année actuelle
	 * @return double - le salaire
	 * @since 1.0
	 * 
	 */
	
	public double salaire(int year) {
		
		double salaire = 0;
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT SUBSTR(date_arrivee,7) FROM eboueur WHERE nom = ? AND prenom = ?");
			state.setString(1, this.getNom());	
			state.setString(2, this.getPrenom());	
			ResultSet result = state.executeQuery();
				
			String date = result.getString(1);
			int annee = Integer.parseInt(date); 
				
			result.close();
			state.close();
			
			salaire = 1527.64+20.9*(year-annee);	
			
			System.out.println("Le salaire de " + this.nom +" "+ this.prenom +" est de " + salaire +"€ /mois.");
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return salaire;		
	}


	
	/**
	 * 
	 * Permet d'obtenir la liste des éboueurs de l'entreprise.
	 * 
	 * @see Tableau#Tableau(String, String[], Object[][])
	 * since 1.0
	 * 
	 */
	
	 public static void listeEboueur() {
			
		try {
		
			String titre = "Liste des éboueurs de l'entreprise";
					
			Connection connection = Connexion.getInstance();
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery("SELECT nom,prenom,equipe,disponibilite,date_arrivee,date_permis FROM eboueur");
			ResultSetMetaData resultMeta = result.getMetaData();
					
			int taille = resultMeta.getColumnCount();
			String[] titreColonne = new String [taille];
					
			for (int i = 1; i <= taille; i++) {
				titreColonne[i-1] = resultMeta.getColumnName(i); 	// i-1 car les tableaux démarrent à 0
			}
			Object [][] donnees= new Object[50][taille];
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