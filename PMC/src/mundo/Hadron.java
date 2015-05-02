package mundo;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.googlecode.charts4j.*;


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
	public void insertQuark(Quark q)
	{
		quarks.remove(0);
		quarks.add(q);
		
	}
	public JFreeChart getChart()
	{
		return createChart(createDataset());
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
	    chart.setBackgroundPaint(java.awt.Color.white);
	
	//    final StandardLegend legend = (StandardLegend) chart.getLegend();
	//      legend.setDisplaySeriesShapes(true);
	    
	    // get a reference to the plot for further customisation...
	    final XYPlot plot = chart.getXYPlot();
	    plot.setBackgroundPaint(java.awt.Color.lightGray);
	//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
	    plot.setDomainGridlinePaint(java.awt.Color.white);
	    plot.setRangeGridlinePaint(java.awt.Color.white);
	    
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
	public ImageIcon toMeter()
	{
		Quark last = getLastQuark();
		double  level = (last.getMovimiento() + last.getSonido() + last.getTemperatura() )/3;
		
		GoogleOMeter chart = GCharts.newGoogleOMeter(level, ":)", "Crowd Level", Color.newColor("D41111"), Color.newColor("b9d432"));
        chart.setTitle("Crowd Meter", Color.newColor("b9d432"), 14);
        chart.setSize(600, 250);
        final LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("6c6c6c"), 100);
        chart.setBackgroundFill(fill);
        chart.setAreaFill(Fills.newSolidFill(Color.newColor(Color.GRAY, 70)));
        String url = chart.toURLString();
        
        ImageIcon icon = new ImageIcon();
        try {
			icon =  new ImageIcon(ImageIO.read(new URL(url)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return icon;
	}
	public ImageIcon toChart()
	{
		 // EXAMPLE CODE START

        // Defining lines
        final int NUM_POINTS = quarks.size();

        final double[] temp = new double[NUM_POINTS];
        final double[] movi = new double[NUM_POINTS];
        final double[] soni = new double[NUM_POINTS];
        
        for (int i = 0; i<quarks.size(); i++)
        {
            temp[i] = quarks.get(i).getTemperatura();
            movi[i] = quarks.get(i).getMovimiento();
            soni[i] = quarks.get(i).getSonido();
        }
        
        Line line1 = Plots.newLine(Data.newData(temp), Color.newColor("CA3D05"), "Temperatura");
        line1.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line1.addShapeMarkers(Shape.DIAMOND, Color.newColor("CA3D05"), 12);
        line1.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);
        
        Line line2 = Plots.newLine(Data.newData(movi), Color.SKYBLUE, "Movimiento");
        line2.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line2.addShapeMarkers(Shape.DIAMOND, Color.SKYBLUE, 12);
        line2.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);
        
        Line line3 = Plots.newLine(Data.newData(soni), Color.GREENYELLOW, "Sonido");
        line2.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line2.addShapeMarkers(Shape.DIAMOND, Color.GREENYELLOW, 12);
        line2.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);


        // Defining chart.
        LineChart chart = GCharts.newLineChart(line1, line2, line3);
        chart.setSize(600, 450);
        chart.setTitle("Crowd Control", Color.WHITE, 14);
        chart.addHorizontalRangeMarker(40, 60, Color.newColor(Color.RED, 30));
        chart.addVerticalRangeMarker(70, 90, Color.newColor(Color.GREEN, 30));
        chart.setGrid(25, 25, 3, 2);

        // Defining axis info and styles
        AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.WHITE, 12, AxisTextAlignment.CENTER);
        AxisLabels xAxis = AxisLabelsFactory.newAxisLabels("Nov", "Dec", "Jan", "Feb", "Mar");
        xAxis.setAxisStyle(axisStyle);
        AxisLabels xAxis2 = AxisLabelsFactory.newAxisLabels("2007", "2007", "2008", "2008", "2008");
        xAxis2.setAxisStyle(axisStyle);
        AxisLabels yAxis = AxisLabelsFactory.newAxisLabels("", "25", "50", "75", "100");
        AxisLabels xAxis3 = AxisLabelsFactory.newAxisLabels("Month", 50.0);
        xAxis3.setAxisStyle(AxisStyle.newAxisStyle(Color.WHITE, 14, AxisTextAlignment.CENTER));
        yAxis.setAxisStyle(axisStyle);
        AxisLabels yAxis2 = AxisLabelsFactory.newAxisLabels("Hits", 50.0);
        yAxis2.setAxisStyle(AxisStyle.newAxisStyle(Color.WHITE, 14, AxisTextAlignment.CENTER));
        yAxis2.setAxisStyle(axisStyle);

        // Adding axis info to chart.
        chart.addXAxisLabels(xAxis);
        chart.addXAxisLabels(xAxis2);
        chart.addXAxisLabels(xAxis3);
        chart.addYAxisLabels(yAxis);
        chart.addYAxisLabels(yAxis2);

        // Defining background and chart fills.
        chart.setBackgroundFill(Fills.newSolidFill(Color.newColor("1F1D1D")));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("363433"), 100);
        fill.addColorAndOffset(Color.newColor("2E2B2A"), 0);
        chart.setAreaFill(fill);
        String url = chart.toURLString();
        ImageIcon icon = new ImageIcon();
        try {
			icon =  new ImageIcon(ImageIO.read(new URL(url)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return icon;
        // EXAMPLE CODE END. Use this url string in your web or
        // Internet application.
//        Logger.global.info(url);
//        String expectedString = "http://chart.apis.google.com/chart?chco=CA3D05,87CEEB&chd=e:AAB4DhEzFxGnHrJRLhOZRmUpXCYZYpYAXCWfXCZIczhmmtrKuE,..-H8e7M6O5Y4U2u0exmuZrWo9nmnWn.o9pgo9m3jMeZZSU1R7&chdl=My+Website.com|Competition.com&chf=bg,s,1F1D1D|c,lg,0,363433,1.0,2E2B2A,0.0&chg=25.0,25.0,3,2&chls=3,1,0|3,1,0&chm=r,FF00004C,0,0.40,0.60|R,0080004C,0,0.70,0.90|d,CA3D05,0,-1,12,0|d,FFFFFF,0,-1,8,0|d,87CEEB,1,-1,12,0|d,FFFFFF,1,-1,8,0&chs=600x450&cht=lc&chts=FFFFFF,14&chtt=Web+Traffic%7C%28in+billions+of+hits%29&chxl=0:||25|50|75|100|1:|Hits|2:|Nov|Dec|Jan|Feb|Mar|3:|2007|2007|2008|2008|2008|4:|Month&chxp=1,50.0|4,50.0&chxr=1,0.0,100.0|4,0.0,100.0&chxs=0,FFFFFF,12,0|1,FFFFFF,12,0|2,FFFFFF,12,0|3,FFFFFF,12,0|4,FFFFFF,14,0&chxt=y,y,x,x,x";
//        assertEquals("Junit error", normalize(expectedString), normalize(url));
	}

	public Quark getLastQuark() 
	{
		return quarks.get(quarks.size()-1);
	}
	
}
