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
 * Cette classe permet d'instancier des objets de type collecte.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @version 1.0
 * @see connexion.Connexion 
 * 
 */


public class Collecte {
	
	
	/**
	 * Type de la collecte
	 */
	private TypeCollecte type;
	
	/**
	 * Statut de la collecte
	 */
	private StatutCollecte statut;
	
	/**
	 * Véhicule utilisé lors de la collecte
	 */
	private Vehicule vehicule;
	
	/**
	 * Secteur à collecter
	 */
	private Secteur secteur;
	
	/**
	 * Décharge
	 */
	private Decharge decharge;
	
	/**
	 * Date de la collecte
	 */
	private String date;
	
	/**
	 * Heure de début de la collecte
	 */
	private String heureDebut;
	
	/**
	 * Temps de collecte
	 */
	private int temps;
	
	/**
	 * Poids de déchets collectés
	 */
	private double poids;
	
	/**
	 * Volume de déchets collectés
	 */
	private double volume;
	
	/**
	 * Remarque concernant la collecte
	 */
	private String remarque;
	
	
	
	/**
	 * 
	 * Constructeur utilisé lors de la planification d'une collecte.
	 * 
	 * @param type
	 * @param vehicule
	 * @param secteur
	 * @param date
	 * @param heureDebut
	 * @since 1.0
	 * 
	 */
	
	public Collecte(TypeCollecte type, Vehicule vehicule, Secteur secteur,
			String date, String heureDebut) {
		super();
		this.type       = type;
		this.vehicule   = vehicule;
		this.secteur    = secteur;
		this.decharge   = null;
		this.date       = date;
		this.heureDebut = heureDebut;
		this.temps      = 0;
		this.poids      = 0;
		this.volume     = 0;
		this.remarque   = " RAS ";
	}


	
	/**
	 * 
	 * Constructeur utilisé lors d'un compte rendu de collecte.
	 * 
	 * @param statut
	 * @param secteur
	 * @param decharge
	 * @param date
	 * @param temps
	 * @param poids
	 * @param volume
	 * @param remarque
	 * @since 1.0
	 * 
	 */
	
	public Collecte(StatutCollecte statut, Secteur secteur, Decharge decharge, String date, int temps, double poids,
			double volume, String remarque) {
		super();
		this.statut   = statut;
		this.secteur  = secteur;
		this.decharge = decharge;
		this.date     = date;
		this.temps    = temps;
		this.poids    = poids;
		this.volume   = volume;
		this.remarque = remarque;
	}

	
	
	/**
	 * Constructeur utilisé lors de l'annulation d'une collecte
	 * 
	 * @param date
	 * @param secteur
	 * @param remarque
	 * @since 1.0
	 * 
	 */
	
	public Collecte( String date , Secteur secteur , String remarque) {
		super();
		this.date     = date;
		this.secteur  = secteur;
		this.remarque = remarque;
	}


	
	// Getters et Setters de la classe

	public TypeCollecte getType() {
		return type;
	}
	public void setType(TypeCollecte type) {
		this.type = type;
	}
	public StatutCollecte getStatut() {
		return statut;
	}
	public void setStatut(StatutCollecte statut) {
		this.statut = statut;
	}
	public Vehicule getVehicule() {
		return vehicule;
	}
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	public Decharge getDecharge() {
		return decharge;
	}
	public void setDecharge(Decharge decharge) {
		this.decharge = decharge;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}
	public int getTemps() {
		return temps;
	}
	public void setTemps(int temps) {
		this.temps = temps;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}


	
	/**
	 * Permet de planifier une collecte en l'inscrivant dans la BDD.
	 * 
	 * @see StatutCollecte
	 * @since 1.0
	 * 
	 */
	
	public void planifierCollecte() {
		
		try {
			
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("INSERT INTO "
					+ "collecte(nature,statut,vehicule,secteur,decharge,date,temps,poids,volume,heure_debut,remarque) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			
			if ( this.type == TypeCollecte.EmballageEtPapier) {
				state.setString(1, "EmballageEtPapier");
			}
			
			else {
				state.setString(1, "Verre");
			}
		
			state.setString(2, "En cours");
			state.setString(3, this.vehicule.getImmatriculation());	
			state.setInt(4, this.secteur.getId());
			state.setInt(5, 0);
			state.setString(6, this.date);
			state.setInt(7, 0);
			state.setDouble(8, 0);
			state.setDouble(9, 0);
			state.setString(10, this.heureDebut);
			state.setString(11, "");
		
			int option = JOptionPane.showConfirmDialog(null, "La collecte que vous avez planifiée est : \n " + this.toString()
					+ "\n\n\n Voulez vous continuer ? \n\n", "Gestion des collectes - Planification", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (option == 1 || option == 2) {
				state.close();
				connection.close();
				JOptionPane.showMessageDialog(null, "Vous avez annulé la planification !", "Gestion des collectes - Planification", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			
			else {
				state.executeUpdate();
				state.close();
			}
			JOptionPane.showMessageDialog(null, "Collecte planifiée avec succès !", "Gestion des collectes - Planification", JOptionPane.INFORMATION_MESSAGE);
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Permet d'annuler une collecte dans la BDD.
	 * @since 1.0
	 * 
	 */
	
	public void annulerCollecte() { // on aura fait au prélabale appel au constructeur collecte qui aura actualisé les attributs

		try {
		
			Connection connection = Connexion.getInstance();
			PreparedStatement state = connection.prepareStatement("UPDATE collecte SET statut = ?, remarque = ?  WHERE collecte.date = ? AND collecte.secteur = ?");
				
			state.setString(1, "Annulee");	
			state.setString(2, this.remarque);
			state.setString(3, this.date);
			state.setInt(4, this.secteur.getId());
			state.executeUpdate();
			state.close();
		
			JOptionPane.showMessageDialog(null, "Collecte annulée avec succès !", "Gestion des collectes", JOptionPane.INFORMATION_MESSAGE);
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	/**
	 * Permet de faire le compte rendu de la collecte dans la BDD.
	 * @see StatutCollecte
	 * @since 1.0
	 * 
	 */
	
	public void compteRendu() {
		
		try {
			
			Connection connection1 = Connexion.getInstance();
			
			String date = "'"+this.date +"'";	
			PreparedStatement state0 = connection1.prepareStatement("UPDATE collecte"
					+ " SET statut = ? , decharge = ?, poids = ? , volume = ?, temps = ?, Remarque = ? "
					+ " WHERE collecte.date ="+date +" AND collecte.secteur = " +this.secteur.getId());
			
			if ( this.statut == StatutCollecte.Effectuee) {
				state0.setString(1, "Effectuee");
			}
			
			else {
				state0.setString(1, "Inachevee");
			}
			

			String decharge = "'"+ this.decharge.getNom() + "'";
			Statement state1 = connection1.createStatement();
			ResultSet result1 = state1.executeQuery("SELECT id FROM decharge WHERE nom = " + decharge);
		
			state0.setInt(2, result1.getInt(1));
			
			state1.close();
			result1.close();
			
			state0.setDouble(3, this.poids);
			state0.setDouble(4, this.volume);
			state0.setDouble(5, this.temps);
			state0.setString(6, this.remarque);
			
			state0.executeUpdate();
			state0.close();
			
			JOptionPane.showMessageDialog(null, "Collecte mise à jour avec succès !", "Gestion des collectes", JOptionPane.INFORMATION_MESSAGE);
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
		
	/**
	 * Permet d'afficher les caractéristiques d'une collecte.
	 * @return str 
	 * @since 1.0
	 * 
	 */
	
	@Override
	public String toString(){
		
		String str;
		
		str = " \n \n Secteur : " + this.secteur.getId() +"\n"		
				+ "Type de collecte : " + this.type + " \n"
				+ "Jour de collecte : " + this.date + "\n"
				+ "Véhicule : " + this.vehicule.getImmatriculation() +"\n"
				+ "Date : " +this.date +"\n"
				+ "Heure de début : " + this.heureDebut +"\n"
				+ "Temps de collecte : " + this.temps + " h" +"\n"
				+ "Poids des déchets ramassés : " + this.poids +" kg"+"\n"
				+ "Volume des déchets ramassés : " + this.volume + " m³";
		
		return str;
	}
	
	
	
	/**
	 * 
	 * Permet d'obtenir la liste des collectes de l'entreprises sur une période donnée.
	 * 
	 * @param periode - True si annuel, False si mensuel
	 * @param date
	 * @see Tableau#Tableau(String, String[], Object[][])
	 * since 1.0
	 * 
	 */
	
	 public static void listeCollecte(boolean periode,String date) {
			
		try {
					
			String datum ="";
			int nombre = 0;
					
			if ( periode ) {
				datum  = "l'année";
				nombre = 366;
			}
				
			else {	
				datum  = "le mois";
				nombre = 31;
			}
			
			String titre = "Ensemble des collectes de l'entreprise sur " + datum + "  " + date ;
					
			Connection connection = Connexion.getInstance();
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM collecte  WHERE date LIKE '%" + date +"'"  );
			ResultSetMetaData resultMeta = result.getMetaData();
					
			int taille = resultMeta.getColumnCount();
			String[] titreColonne = new String [taille];
					
			for (int i = 1; i <= taille; i++) {
				titreColonne[i-1] = resultMeta.getColumnName(i); // i-1 car les tableaux démarrent à 0
			}

			Object [][] donnees= new Object[nombre][taille];	
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