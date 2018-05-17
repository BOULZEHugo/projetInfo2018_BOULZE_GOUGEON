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
 * Classe Equipe permettant de gérer les équipes.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see connexion.Connexion
 * @version 1.0
 *
 */


public class Equipe {
	
	
	/**
	 * Numéro de l'équipe
	 */
	private int identifiant;
	
	/**
	 * identifiant du conducteur
	 */
	private Conducteur conducteur;
	
	/**
	 * identifiant du rammaseur 1
	 */
	private Ramasseur ramasseur1;
	
	/**
	 * identifiant du rammasseur 2
	 */
	private Ramasseur ramasseur2;
	
	/**
	 * Numéro du secteur 1
	 */
	private Secteur secteur1;
	
	/**
	 * Numéro du secteur 2
	 */
	private Secteur secteur2;
	
	/**
	 * Dipsoniblité de l'équipe - 1 si dipsonible et 0 sinon
	 */
	private boolean disponibilite;


	
	/**
	 * 
	 * Constructeur d'équipe à partir du numéro d'équipe.
	 * 
	 * @param id
	 * @since 1.0
	 * 
	 */
	
	public Equipe(int id) {
		super();
		this.identifiant = id;
	}
	
	
	
	/**
	 * Constructeur d'équipe à partir de tous les paramètres.
	 * 
	 * @param conducteur
	 * @param ramasseur1
	 * @param ramasseur2
	 * @param secteur1
	 * @param secteur2
	 * @param disponibilite
	 * @since 1.0
	 * 
	 */
	
	public Equipe(Conducteur conducteur, Ramasseur ramasseur1, Ramasseur ramasseur2, Secteur secteur1, Secteur secteur2, boolean disponibilite) {
		super();
		this.conducteur = conducteur;
		this.ramasseur1 = ramasseur1;
		this.ramasseur2 = ramasseur2;
		this.secteur1 = secteur1;
		this.secteur2 = secteur2;
		this.disponibilite = disponibilite;
	}

	
	
	// Getters et Setters de la classe

	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public Conducteur getConducteur() {
		return conducteur;
	}
	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}
	public Ramasseur getRamasseur1() {
		return ramasseur1;
	}
	public void setRamasseur1(Ramasseur ramasseur1) {
		this.ramasseur1 = ramasseur1;
	}
	public Ramasseur getRamasseur2() {
		return ramasseur2;
	}
	public void setRamasseur2(Ramasseur ramasseur2) {
		this.ramasseur2 = ramasseur2;
	}
	public Secteur getSecteur1() {
		return secteur1;
	}
	public void setSecteur1(Secteur secteur1) {
		this.secteur1 = secteur1;
	}
	public Secteur getSecteur2() {
		return secteur2;
	}
	public void setSecteur2(Secteur secteur2) {
		this.secteur2 = secteur2;
	}
	public boolean isDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	
	
	/**
	 * Permet d'ajouter une équipe dans la BDD.
	 * 
	 * @param equipe
	 * @see Equipe#actualiserEquipe(int, int, int)
	 * @since 1.0
	 * 
	 */
	
	public static void ajouterEquipe(Equipe equipe) {
		
		try {
			
			Connection connection = Connexion.getInstance();
			
			PreparedStatement statConducteur = connection.prepareStatement("SELECT id FROM eboueur WHERE nom = ? AND prenom = ? AND date_permis = ?");
			statConducteur.setString(1,  equipe.conducteur.getNom() )	;
			statConducteur.setString(2, equipe.conducteur.getPrenom());
			statConducteur.setString(3,  equipe.conducteur.getDatePermis());
			ResultSet result0 = statConducteur.executeQuery();	
			int idC = result0.getInt(1);
		
			PreparedStatement statRamasseur1 = connection.prepareStatement("SELECT id FROM eboueur WHERE nom = ? AND prenom = ?");
			statRamasseur1.setString(1,  equipe.ramasseur1.getNom() )	;
			statRamasseur1.setString(2, equipe.ramasseur1.getPrenom());
			ResultSet result1 = statRamasseur1.executeQuery();	
			int idR1 = result1.getInt(1);
			
			PreparedStatement statRamasseur2 = connection.prepareStatement("SELECT id FROM eboueur WHERE nom = ? AND prenom = ?");
			statRamasseur2.setString(1,  equipe.ramasseur2.getNom() )	;
			statRamasseur2.setString(2, equipe.ramasseur2.getPrenom());
			ResultSet result2 = statRamasseur2.executeQuery();	
			int idR2 = result2.getInt(1);
			
			PreparedStatement state = connection.prepareStatement("INSERT INTO equipe(conducteur,ramasseur1,ramasseur2, secteur1, secteur2,disponibilite)"
					+ " VALUES(?,?,?,?,?,?)");
		
			state.setInt(1, idC );
			state.setInt(2, idR1);
			state.setInt(3, idR2);
			state.setInt(4, equipe.secteur1.getId());
			state.setInt(5, equipe.secteur2.getId());
			state.setInt(6, 1); 
			
			state.executeUpdate();
			result2.close();
			result1.close();
			result0.close();
			state.close();
			statConducteur.close();
			statRamasseur1.close();
			statRamasseur2.close();

			equipe.actualiserEquipe(idC, idR1, idR2);
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 
	 * Permet d'actuliser l'attribut équipe de chacun des éboueurs de la nouvelle équipe créée.
	 * 
	 * @param idC  - id du conducteur
	 * @param idR1 - id du ramasseur 1
	 * @param idR2 - id du ramasseur 2
	 * @see Equipe#ajouterEquipe(Equipe)
	 * @since 1.0
	 * 
	 */
	
	public void actualiserEquipe(int idC, int idR1, int idR2) {
		
		try {
			
			Connection connection = Connexion.getInstance();

			PreparedStatement stateId = connection.prepareStatement("SELECT id FROM equipe WHERE conducteur = ? AND ramasseur1 = ? AND ramasseur2 = ? ");
			stateId.setInt(1, idC);
			stateId.setInt(2, idR1);
			stateId.setInt(3, idR2);
			ResultSet resultId = stateId.executeQuery();
			System.out.println("coucou" + stateId.toString());
			System.out.println("coucou" + stateId.toString());
			
			if (resultId.next()) {
				int idEquipe = resultId.getInt(1);
				System.out.println("coucou" + idEquipe);
				PreparedStatement stateC = connection.prepareStatement("UPDATE eboueur SET equipe = ?  WHERE nom = ? AND prenom = ? ");
				PreparedStatement stateR1 = connection.prepareStatement("UPDATE eboueur SET equipe = ?  WHERE nom = ? AND prenom = ? ");
				PreparedStatement stateR2 = connection.prepareStatement("UPDATE eboueur SET equipe = ?  WHERE nom = ? AND prenom = ? ");
				
				stateC.setInt(1, idEquipe);
				stateC.setString(2, this.conducteur.getNom());
				stateC.setString(3, this.conducteur.getPrenom());
				stateR1.setInt(1, idEquipe);
				stateR1.setString(2, this.ramasseur1.getNom());
				stateR1.setString(3, this.ramasseur1.getPrenom());
				stateR2.setInt(1, idEquipe);
				stateR2.setString(2, this.ramasseur2.getNom());
				stateR2.setString(3, this.ramasseur2.getPrenom());
				
				stateC.executeUpdate();
				stateR1.executeUpdate();
				stateR2.executeUpdate();
				
				resultId.close();
				stateC.close();
				stateR1.close();
				stateR2.close();
				
				JOptionPane.showMessageDialog(null, "Equipe n° "+ idEquipe +" : \n\n " + this.toString() +" \n\n ajoutée avec succès !", "Gestion des Equipes", JOptionPane.INFORMATION_MESSAGE);
			}
			
			else {
				System.out.println("attention je nai pas dequipe valide");
			}
			connection.close();
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Permet de supprimer une équipe dans la BDD.
	 * 
	 * @since 1.0
	 * 
	 */

	public void supprimerEquipe(){
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("DELETE FROM equipe WHERE equipe.id = ?"); // les équipes sont identifiés par un identif par le gestionnaire
			state.setInt(1, this.identifiant);
			
			int option = JOptionPane.showConfirmDialog(null, "L'équipe que vous vous voulez supprimer est la numéro : \n " + this.identifiant 
					+ "\n\n\n Voulez vous continuer ? \n\n", "Gestion des Equipes - Supression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (option == 1 || option == 2) {
				state.close();
				connection.close();
				JOptionPane.showMessageDialog(null, "Vous avez annulé la supression !", "Gestion des Equipes - Supression", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}

			
			// si l'équipe est supprimée, les id des équipes dans secteur doivent passer à la valeur 0
			
			PreparedStatement secteur1= connection.prepareStatement("UPDATE secteur SET equipe = ?  WHERE equipe = ?");
			PreparedStatement secteur2 = connection.prepareStatement("UPDATE secteur SET equipe = ?  WHERE equipe = ?");
			
			secteur1.setInt(1, 0);
			secteur1.setInt(2, this.getIdentifiant());
			secteur1.executeUpdate();
			secteur2.setInt(1, 0);
			secteur2.setInt(2, this.getIdentifiant());
			secteur2.executeUpdate();
			secteur1.close();
			secteur2.close();
			

			// de même, les id_equipe des eboueurs passent à 0
			
			PreparedStatement eboueur= connection.prepareStatement("UPDATE eboueur SET equipe = ?  WHERE equipe = ?");
			eboueur.setInt(1, 0);
			eboueur.setInt(2, this.getIdentifiant());
			eboueur.executeUpdate();
			eboueur.close();
			
			state.executeUpdate();
			state.close();
			
			JOptionPane.showMessageDialog(null, "Equipe supprimée avec succès !", "Gestion des Equipes", JOptionPane.INFORMATION_MESSAGE);
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
		
	
	
	/**
	 * Permet d'affecter un secteur à une équipe.
	 * 
	 * @param secteur
	 * @since 1.0
	 * 
	 */
	
	public void affectationSecteur(Secteur secteur) {
		
		try {
			
			Connection connection = Connexion.getInstance();
			
			PreparedStatement statSecteur1= connection.prepareStatement("SELECT secteur1 FROM equipe WHERE id = ?");
			statSecteur1.setInt(1, this.getIdentifiant());
			ResultSet result1 = statSecteur1.executeQuery();	
			
			int idS1 = result1.getInt(1);
			
			if(idS1==0){
				PreparedStatement stateS1 = connection.prepareStatement("UPDATE equipe SET secteur1 = ?  WHERE id = ?");
				stateS1.setInt(1, secteur.getId());
				stateS1.setInt(2, this.getIdentifiant());
				stateS1.executeUpdate();
				stateS1.close();
				
				PreparedStatement stateSect1 = connection.prepareStatement("UPDATE secteur SET equipe = ?  WHERE id = ?");
				stateSect1.setInt(1, this.getIdentifiant());
				stateSect1.setInt(2, secteur.getId());
				stateSect1.executeUpdate();
				stateSect1.close();
			}
		
			else {
				PreparedStatement statSecteur2= connection.prepareStatement("SELECT secteur2 FROM equipe WHERE id = ?");
				statSecteur2.setInt(1, this.getIdentifiant());
				ResultSet result2 = statSecteur2.executeQuery();	
				int idS2 = result2.getInt(1);
		
				if(idS2==0){
					PreparedStatement stateS2 = connection.prepareStatement("UPDATE equipe SET secteur2 = ?  WHERE id = ?");
					stateS2.setInt(1, secteur.getId());
					stateS2.setInt(2, this.getIdentifiant());
					stateS2.executeUpdate();
					stateS2.close();
					
					PreparedStatement stateSect2 = connection.prepareStatement("UPDATE secteur SET equipe = ?  WHERE id = ?");
					stateSect2.setInt(1, this.getIdentifiant());
					stateSect2.setInt(2, secteur.getId());
					stateSect2.executeUpdate();
					stateSect2.close();
				}
			}
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Permet de savoir si une équipe possède déjà 2 secteurs de collecte.
	 * La fonction ne fait rien si l'équipe ne possède qu'un ou aucun secteur de collecte.
	 * Dans le cas où elle en possède déjà 2, la fonction ferme la méthode en cours en affichant un message d'erreur.
	 * 
	 * @since 1.0
	 * 
	 */
	
	public void equipeOccupee() { 
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("SELECT secteur1,secteur2 FROM equipe WHERE equipe.id = ?");
			state.setInt(1, this.getIdentifiant());	
			ResultSet result = state.executeQuery();
			
			if ( result.getInt(1) == 0 || result.getInt(2) == 0) {
				result.close();
				state.close();
			}
			
			else {
				result.close();
				state.close();
				JOptionPane.showMessageDialog(null, "L'équipe possède déjà 2 secteurs de collecte!", " Gestion des Equipes" , JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Permet d'afficher les caractéristiques d'une équipe.
	 * 
	 * @return str
	 * @since 1.0
	 * 
	 */
	
	@Override
	public String toString(){
		
		String str ="";
		
		str += "L'équipe crééé est la suivante : \n" 
				+ "Le conducteur est " + conducteur.getPrenom() + " " +conducteur.getNom() + "\n"
				+ "Les ramasseurs sont " + ramasseur1.getPrenom()+ " " +ramasseur1.getNom() + " et " + ramasseur2.getPrenom()+ " " +ramasseur2.getNom() +"\n"
				+ "Les secteurs collectés sont " + this.secteur1.getId() + " et " + this.secteur2.getId();
		
		return str;
	}
	
	

	/**
	 * 
	 * Permet d'obtenir la liste des équipes de l'entreprise.
	 * 
	 * @see Tableau#Tableau(String, String[], Object[][])
	 * @since 1.0
	 * 
	 */
	
	 public static void listeEquipe() {
			
		try {
				
			String titre = "Liste des équipes de l'entreprise";
					
			Connection connection = Connexion.getInstance();
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM equipe");
			ResultSetMetaData resultMeta = result.getMetaData();
					
			int taille = resultMeta.getColumnCount();
			String[] titreColonne = new String [taille];
					
			for (int i = 1; i <= taille; i++) {
				titreColonne[i-1] = resultMeta.getColumnName(i); 	// i-1 car les tableaux démarrent à 0
			}

			Object [][] donnees= new Object[40][taille];
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