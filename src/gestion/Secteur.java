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
 * Classe Secteur permettant de gérer les secteurs de collectes.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @version 1.0
 *
 */


public class Secteur {
	
	
	/**
	 * Numéro du secteur
	 */
	private int id;
	
	/**
	 * Nom du secteur
	 */
	private String nom;
	
	/**
	 * Nombre d'habitants vivant dans le secteur
	 */
	private int nombreHab;
	
	/**
	 * Jour de collecte des emballages et papier
	 */
	private String jourPapier;
	
	/**
	 * Jour de collecte du verre
	 */
	private String jourVerre;
	
	/**
	 * Numéro de l'équipe affectée à ce secteur
	 */
	private Equipe equipe;
	
	/**
	 * Nombre d'entreprises présents dans le secteur
	 */
	private int nbrEntreprises;
	
	/**
	 * Distance du trajet à parcourir lors de la collecte
	 */
	private double distanceTrajet;
	
	
	
	/**
	* Constructeur de secteur à partir de l'id.
	* 
	* @param id
	* @since 1.0
	*/
	
	public Secteur(int id) {
		this.id = id;
	}
	
	
	
	/**
	 * Constructeur de secteur à partir de l'ensemble des paramètres.
	 * 
	 * @param id
	 * @param nom
	 * @param nombreHab
	 * @param jourPapier
	 * @param jourVerre
	 * @param equipe
	 * @param nbrEntreprises
	 * @param distanceTrajet
	 * @since 1.0
	 */
	
	public Secteur(int id, String nom, int nombreHab, String jourPapier, String jourVerre, Equipe equipe, int nbrEntreprises, double distanceTrajet) {
		super();
		this.id = id;
		this.nom = nom;
		this.nombreHab = nombreHab;
		this.jourPapier = jourPapier;
		this.jourVerre = jourVerre;
		this.equipe = equipe;
		this.nbrEntreprises = nbrEntreprises;
		this.distanceTrajet = distanceTrajet;
	}


	
	// Getters et Setters de la classe
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNombreHab() {
		return nombreHab;
	}
	public void setNombreHab(int nombreHab) {
		this.nombreHab = nombreHab;
	}
	public String getJourPapier() {
		return jourPapier;
	}
	public void setJourPapier(String jourPapier) {
		this.jourPapier = jourPapier;
	}
	public String getJourVerre() {
		return jourVerre;
	}
	public void setJourVerre(String jourVerre) {
		this.jourVerre = jourVerre;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	
	
	
	/**
	 * Le setter de this.equipe est en réalité la fonction qui permet de changer l'équipe attribuée au secteur
	 * 
	 * @param equipe
	 * @since 1.0
	 */
	
	public void changerEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	public int getNbrEntreprises() {
		return nbrEntreprises;
	}
	public void setNbrEntreprises(int nbrEntreprises) {
		this.nbrEntreprises = nbrEntreprises;
	}
	public double getDistanceTrajet() {
		return distanceTrajet;
	}
	public void setDistanceTrajet(double distanceTrajet) {
		this.distanceTrajet = distanceTrajet;
	}
	
	
	
	/**
	 * Permet d'afficher les secteurs qui doivent être collectés au jour donné par l'utilisateur en fonction du type de déchets
	 * 
	 * @param jour - jour de collecte
	 * @since 1.0
	 * 
	 */
	
	public static void jourSecteur(String jour) {
		
		try {
			
			String str = "";
			String jour1 = "'" + jour + "'";  
			Connection connection = Connexion.getInstance();
			Statement Verre = connection.createStatement();
			Statement Papier = connection.createStatement();
			ResultSet resultVerre = Verre.executeQuery("SELECT * FROM secteur WHERE secteur.jour_verre = " + jour1 );
			ResultSet resultPapier = Papier.executeQuery("SELECT * FROM secteur WHERE secteur.jour_papier = " + jour1 );
			ResultSetMetaData resultMetaVerre = resultVerre.getMetaData();
			
			str += "\nListe des secteurs qui doivent être collectés le " + jour + " :\n";
			str += "\n\nVerre\n\n";
			
			for (int i = 1; i <= 3; i++) {
				str += "   " + resultMetaVerre.getColumnName(i).toUpperCase() + "    *    ";
			}
			str += "\n\n";
			
			while (resultVerre.next()) { 
				
				for (int i = 1; i <= 3; i++) {
					str += "   |   " + resultVerre.getObject(i).toString() + "   |   ";
				}
				
				str += "\n";
			}
			
			resultVerre.close();
			Verre.close();
			
			str += "\n\nEmballages et Papiers\n\n";
			ResultSetMetaData resultMetaPapier = resultPapier.getMetaData();
			
			for (int i = 1; i <= 3; i++) {
				str += "   " + resultMetaPapier.getColumnName(i).toUpperCase() + "    *    ";
			}
			str += "\n\n";
			
			while (resultPapier.next()) { 
				
				for (int i = 1; i <=3; i++) {
					str += "   |   " + resultPapier.getObject(i).toString() + "    |   ";
				}

				str += "\n";
			}
			str += " \n";
			
			resultPapier.close();
			Papier.close();
			
			JOptionPane.showMessageDialog(null, str, "Gestion des collectes - Planification", JOptionPane.INFORMATION_MESSAGE);
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}	  
	}
		
		

	/**
	 * Permet de savoir si un secteur est atrribué à une équipe.
	 * 
	 * @since 1.0
	 * 
	 */
	
	public void secteurOccupe() {
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT equipe FROM secteur WHERE secteur.id =?");
			state.setInt(1, this.getId());	
			ResultSet result = state.executeQuery();
			
			
			if ( result.getInt(1) == 0) {
				state.close();
			}
			
			else {
				state.close();
				
				JOptionPane.showMessageDialog(null, "Secteur déjà affecté !", " Gestion des Secteurs" , JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Permet d'afficher les caractéristiques d'un secteur
	 * 
	 * @return str
	 * @since 1.0
	 * 
	 */
		
	@Override
	public String toString(){
		
		String str = "";
		
		str += "Secteur " + this.id + "\n \n"
				+ this.nom + " \n \n"
				+ "Equipe de collecte :" + this.equipe + "\n"
				+ "Nombre d'habitants : " + this.nombreHab + "\n"
				+ "Jour de collecte des Emballages et Papier : " + this.jourPapier + "\n"
				+ "Jour de collecte du Verre : " + this.jourVerre + "\n"
				+ "Nombre d'entreprises : " + this.nbrEntreprises +"\n"
				+ "Distance du trajet : " + this.distanceTrajet + " km";
		
		return str;		
	}
	
	

	/**
	 * 
	 * Permet d'obtenir la liste des secteurs de collecte de l'entreprise
	 * 
	 * @see Tableau#Tableau(String, String[], Object[][])
	 * since 1.0
	 * 
	 */
	
	 public static void listeSecteur() {
			
		try {
				
			String titre = "Liste des secteurs de l'entreprise";
					
			Connection connection = Connexion.getInstance();
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM secteur");
			ResultSetMetaData resultMeta = result.getMetaData();
					
			int taille = resultMeta.getColumnCount();
			String[] titreColonne = new String [taille];
					
			for (int i = 1; i <= taille; i++) {
				titreColonne[i-1] = resultMeta.getColumnName(i); 	// i-1 car les tableaux démarrent à 0
			}

			Object [][] donnees= new Object[16][taille];
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