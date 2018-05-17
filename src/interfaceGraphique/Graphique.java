package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * Cette classe permet de construire des histogrammes.
 * 
 * @author Hugo Boulze, Matthieu Gougeon
 * @see JPanel
 * @version 1.0
 * 
 * 
 */

public class Graphique extends JPanel {

	
	private static final long serialVersionUID = 1L;

	private String titre;
	private String ordonnee;
	private String abscisse;
	private List<Double> valeurs;
	private List<String> series;
	private List<String> categories;
	private boolean legende;
	private Color couleurFond;
	private Color[] couleursBarres = {Color.cyan.darker(), Color.green.darker()};

	
	
	/**
	 * Constrcuteur de la classe
	 * 
	 * @param titre
	 * @param abscisse
	 * @param ordonnee
	 * @param valeurs
	 * @param fond
	 * @param listeSeries
	 * @param listeCategory
	 * @param legende
	 * @see Graphique#initialiser()
	 * @since 1.0
	 * 
	 */
	
	public Graphique(String titre, String abscisse, String ordonnee, List<Double> valeurs, Color fond, List<String> listeSeries, List<String> listeCategory, boolean legende) {
		super(new GridLayout(1,0));
		this.titre=titre;
		this.ordonnee=ordonnee;
		this.abscisse=abscisse;
		this.valeurs=valeurs;
		this.series=listeSeries;
		this.categories=listeCategory;
		this.legende=legende;
		this.couleurFond=fond;
		initialiser();
	}


	
	/**
	 * Initialise le graphique
	 * 
	 */
	
	private void initialiser(){
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int k = 0;
		
		for ( int j=0; j<categories.size(); j++){
			for (int i=0; i<series.size(); i++){
				dataset.addValue(valeurs.get(k), series.get(i), categories.get(j));
				k++;
			}
		}
		JFreeChart chart = ChartFactory.createBarChart(titre,abscisse,ordonnee,   				
				dataset,    				// data
				PlotOrientation.VERTICAL, 	// orientation
				legende,                    // include legend
				true,                     	// tooltips
				false                     	// URL
		);

		// definition de la couleur de fond
		chart.setBackgroundPaint(couleurFond);
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		// valeur comprise entre 0 et 1 transparence de la zone graphique
		plot.setBackgroundAlpha(0.9f);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// pour la couleur des barres pour chaque serie
		for (int s=0; s<series.size(); s++){
			GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, couleursBarres[s],
					0.0f, 0.0f, new Color(0, 40, 70));
			renderer.setSeriesPaint(s, gp0);
		}		

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setFillZoomRectangle(true);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setPreferredSize(new Dimension(500, 270));

		add(chartPanel);
	}

	
}