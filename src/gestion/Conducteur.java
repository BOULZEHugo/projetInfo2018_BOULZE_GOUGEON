package gestion;


/**
 * Classe Conducteur héritant de Eboueur.
 * 
 * @author Hugo Boulze, Matthieu Gougeon 
 * @see Eboueur
 * @version 1.0
 * 
 */


public class Conducteur extends Eboueur {
	
	
	/**
	 * Date de l'obtention du permis de l'éboueur
	 */
	private String datePermis;


	
	/**
	 * Constructeur de Conducteur avec le nom,le prenom et la date de permis 
	 * 
	 * @param nom
	 * @param prenom
	 * @param datePermis
	 * @see Eboueur#Eboueur(String, String)
	 * @since 1.0
	 * 
	 */
	
	public Conducteur(String nom , String prenom, String datePermis) {
		super(nom,prenom);
		this.datePermis = datePermis;
	}
	

	
	/**
	 * Constructeur de Conducteur avec le nom et le prenom
	 * 
	 * @param nom
	 * @param prenom
	 * @see Eboueur#Eboueur(String, String)
	 * @since 1.0
	 * 
	 */
	
	public Conducteur(String nom , String prenom) {
		super(nom,prenom);
	}
	

	
	/**
	 * Constructeur de Conducteur avec tous les paramètres 
	 * 
	 * @param nom
	 * @param prenom
	 * @param disponibilite
	 * @param date
	 * @param equipe
	 * @param datePermis
	 * @see Eboueur#Eboueur(String, String, boolean, String, Equipe)
	 * @since 1.0
	 * 
	 */
	
	public Conducteur(String nom, String prenom, boolean disponibilite, String date, Equipe equipe, String datePermis) {
		super(nom, prenom, disponibilite, date, equipe);
		this.datePermis = datePermis;
	}

	
	
	// Getters et Setters de la classe
	
	public String getDatePermis() {
		return datePermis;
	}

	public void setDatePermis(String datePermis) {
		this.datePermis = datePermis;
	}
	
	
	
	/**
	 * Permet de connaître le salaire d'un conducteur
	 * 
	 * @param year - année actuelle
	 * @return double Le salaire
	 * @since 1.0
	 * 
	 */
	
	@Override
	public double salaire(int year){
		return (super.salaire(year)+167)*1.08;		// prime de risque de 8%
	}
	
	
	
	/**
	 * Permet d'afficher les caractéristiques d'une conducteur
	 * 
	 * @return str 
	 * @since 1.0
	 * 
	 */
	
	@Override
	public String toString(){
		
		String str = "";
		str += this.getPrenom() + " " + this.getNom() + " \n"
				+ "Catégorie : Conducteur" + " permis le " + this.datePermis + "\n"
				+ "Equipe : " + this.getEquipe().getIdentifiant() + "\n";
		
		if (this.isDisponibilite()){
			str += "DISPONIBLE";
		}
		
		else{
			str += "NON DISPONIBLE";
		}
				
		return str;	
	}
	
	
}