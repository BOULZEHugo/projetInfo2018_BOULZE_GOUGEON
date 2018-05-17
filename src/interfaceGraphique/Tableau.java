package interfaceGraphique;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * 
 * Cette classe permet de créer des tableaux
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see JFrame
 * @version 1.0
 *
 */

public class Tableau extends JFrame {
	
	
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * Constructeur de la classe Tableau
	 * 
	 * @param titre - titre du tableau
	 * @param entetes - nom des colonnes du tableau
	 * @param donnees - valeurs à mettre dans le tableau
	 * @since 1.0
	 * 
	 */
	
	public Tableau(String titre, String[] entetes, Object[][] donnees) {
        super();
        setTitle(titre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable tableau = new JTable(donnees, entetes);
        
        //permet de centrer les éléments du tableau
        DefaultTableCellRenderer cell = new DefaultTableCellRenderer(); 
        cell.setHorizontalAlignment(JLabel.CENTER);							// centre les données pour une cellule
        
        for (int i=0 ; i < tableau.getColumnCount() ; i++) {				// centre chaque cellule du tableau
        	tableau.getColumnModel().getColumn(i).setCellRenderer(cell);
        } 
 
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
        pack();
    }
 
    
}