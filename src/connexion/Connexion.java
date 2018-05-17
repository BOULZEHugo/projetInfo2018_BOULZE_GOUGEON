package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

/**
 * 
 * Cette classe instancie le singleton Connection.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @version 1.0
 *
 */


public class Connexion {

	private static Connection connection; 

	/**
	 * La méthode construit une connexion avec la BDD.
	 * 
	 * @param DB_URL - l'url de la BDD
	 * @since 1.0
	 * 
	 */
	
	public Connexion(String DB_URL) { 
		
		try {
		
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(DB_URL);
			System.out.println("Connexion établie avec succès !");	
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * La méthode crée une connexion avec la BDD si elle n'existe pas déjà.
	 * 
	 * @return Une connection avec la BDD
	 * @since 1.0
	 * 
	 */
	
	public static Connection getInstance(){
		
		if(connection == null){
			Scanner scan = new Scanner(System.in);
			System.out.println("Entrez le chemin de la base de données WasteBDD");
			String chemin = scan.next();
			String url = "jdbc:sqlite:" + chemin;
			scan.close();
			new Connexion(url);
		}
		
		return connection;
	}
	
	
	/**
	 * 
	 * Permet de déconnecter la connexion avec la BDD.
	 * 
	 * @since 1.0
	 * 
	 */
	
	public static void deconnexion() {
		
		if(connection != null) {
			
			try{
				connection.close();
				System.out.println("Déconnexion établie avec succès !");	
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}