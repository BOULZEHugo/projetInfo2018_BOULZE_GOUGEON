package gestion;

/**
 * Classe Ramasseur héritant de Eboueur.
 * 
 * @author Hugo Boulze, Matthieu Gougeon 
 * @see Eboueur
 * @version 1.0
 * 
 */


public class Ramasseur extends Eboueur {

	
	
	/**
	 * Constructeur de Ramasseur avec le nom et le prénom. 
	 * 
	 * @param nom
	 * @param prenom
	 * @see Eboueur#Eboueur(String, String)
	 * @since 1.0
	 * 
	 */
	
	public Ramasseur(String nom, String prenom) {
		super(nom, prenom);
	}
	

	
	/**
	 * Constructeur de Ramasseur avec tous les paramètres. 
	 * 
	 * @param nom
	 * @param prenom
	 * @param disponibilite
	 * @param date
	 * @param equipe
	 * @see Eboueur#Eboueur(String, String, boolean, String, Equipe)
	 * @since 1.0
	 * 
	 */
	
	public Ramasseur(String nom, String prenom, boolean disponibilite, String date, Equipe equipe) {
		super(nom, prenom, disponibilite, date, equipe);
	}



	/**
	 * 
	 * Le salaire de l'éboueur est calculé à partir de son ancienneté
	 * 
	 * @param year - année actuelle
	 * @return double 
	 * @since 1.0
	 * 
	 */
	
	@Override
	public double salaire(int year){
		return super.salaire(year)*1.12;		// prime de risque de 12%
	}
	


	/**
	 * Permet d'afficher les caractéristiques d'une ramasseur
	 * 
	 * @return str 
	 * @since 1.0
	 * 
	 */
	
	@Override
	public String toString(){
		
		String str = "";
		
		str += this.getPrenom() + " " + this.getNom() + " \n"
				+ "Catégorie : Ramasseur" + "\n"
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