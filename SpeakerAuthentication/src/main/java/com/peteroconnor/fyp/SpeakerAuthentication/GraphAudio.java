package com.peteroconnor.fyp.SpeakerAuthentication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

//reference http://www.java2s.com/Code/Java/Chart/JFreeChartMultipleDatasetDemo1.htm
public class GraphAudio extends ApplicationFrame{

    private XYPlot plot;

    private int datasetIndex = 0;
    
    private double[] dataToGraph;	
	 public GraphAudio(final String title, double[] dataToGraph) {

	        super(title);
	        this.dataToGraph = dataToGraph;
	        final TimeSeriesCollection dataset1 = createRandomDataset("Series 1");
	        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "Wav", "Time", "Value", dataset1, true, true, false
	        );
	        chart.setBackgroundPaint(Color.white);
	        
	        this.plot = chart.getXYPlot();
	        this.plot.setBackgroundPaint(Color.lightGray);
	        this.plot.setDomainGridlinePaint(Color.white);
	        this.plot.setRangeGridlinePaint(Color.white);
	        this.plot.getRenderer().setSeriesStroke(0, new BasicStroke(0.5f));
//	        this.plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 4, 4, 4, 4));
	        final ValueAxis axis = this.plot.getDomainAxis();
	        axis.setAutoRange(true);

	        final NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
	        rangeAxis2.setAutoRangeIncludesZero(false);
	        
	        final JPanel content = new JPanel(new BorderLayout());

	        final ChartPanel chartPanel = new ChartPanel(chart);
	        content.add(chartPanel);
	        
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        setContentPane(content);

	    }
	
	private TimeSeriesCollection createRandomDataset(final String name) {
    	
        final TimeSeries series = new TimeSeries(name);
        RegularTimePeriod t = new Day();
        for (int i = 0; i < dataToGraph.length; i++) {
            series.add(t, dataToGraph[i]);
            t = t.next();
        }
        return new TimeSeriesCollection(series);
    }
	
	
	public void showGraph(){
		final GraphAudio demo = new GraphAudio("Wav Graph", dataToGraph);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
	}
	
	
}
