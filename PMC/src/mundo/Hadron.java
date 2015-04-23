package mundo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Hadron 
{
	private ArrayList<Quark> quarks;
	
	public Hadron()
	{
		quarks = new ArrayList<Quark>();
	}
	
	public void addQuark(Quark quark)
	{
		quarks.add(quark);
	}

	public Quark createAverageQuark()
	{
		long temp = 0;
		long soni = 0;
		long movi = 0;
		
		for (int i = 0; i<quarks.size(); i++)
        {
			temp += quarks.get(i).getTemperatura();
            movi += quarks.get(i).getMovimiento();
            soni += quarks.get(i).getSonido();
        }
		
		return new Quark(new Date(), temp, movi, soni);
	}
	
	public XYDataset createDataset() 
	{
		final XYSeriesCollection dataset = new XYSeriesCollection();
		try
		{
			if (!quarks.isEmpty())
		    {
		        final XYSeries series1 = new XYSeries("Temperatura");
		        final XYSeries series2 = new XYSeries("Movimiento");
		        final XYSeries series3 = new XYSeries("Sonido");
		        
		        for (int i = 0; i<quarks.size(); i++)
		        {
		            series1.add(i, quarks.get(i).getTemperatura());
		            series2.add(i, quarks.get(i).getMovimiento());
		            series3.add(i, quarks.get(i).getSonido());
		        }
		
		        dataset.addSeries(series1);
		        dataset.addSeries(series2);
		        dataset.addSeries(series3);        
		        
		    }
		    else
		    {
		    	throw new Exception("El arreglo de quarks esta vacio, no se puede crear un dataset");
		    }
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return dataset;
        
    }

	public JFreeChart createChart(final XYDataset dataset) 
	{
	    
	    // create the chart...
	    final JFreeChart chart = ChartFactory.createXYLineChart(
	        "Crowd Control",      // chart title
	        "X",                      // x axis label
	        "Y",                      // y axis label
	        dataset,                  // data
	        PlotOrientation.VERTICAL,
	        false,                     // include legend
	        true,                     // tooltips
	        false                     // urls
	    );
	
	    // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
	    chart.setBackgroundPaint(Color.white);
	
	//    final StandardLegend legend = (StandardLegend) chart.getLegend();
	//      legend.setDisplaySeriesShapes(true);
	    
	    // get a reference to the plot for further customisation...
	    final XYPlot plot = chart.getXYPlot();
	    plot.setBackgroundPaint(Color.lightGray);
	//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    
	    final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	    renderer.setSeriesShapesVisible(0, false);
	    renderer.setSeriesShapesVisible(1, false);
	    renderer.setSeriesShapesVisible(2, false);
	    plot.setRenderer(renderer);
	
	    // change the auto tick unit selection to integer units only...
	    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    // OPTIONAL CUSTOMISATION COMPLETED.
	            
	    return chart;
	    
	}
	
}
