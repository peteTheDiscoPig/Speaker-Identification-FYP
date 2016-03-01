package com.peteroconnor.fyp.SpeakerAuthentication.GUI;

import java.awt.Font;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Mel.MelFilterBank;

/**
 * A demonstration application showing how to create a vertical combined chart.
 *
 */
public class CombinedXYPlotDemo1 extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public CombinedXYPlotDemo1(final String title) {

        super(title);
//        final JFreeChart chart = createCombinedChart();
//        final ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
//        panel.setPreferredSize(new java.awt.Dimension(500, 270));
//        setContentPane(panel);

    }

    /**
     * Creates a combined chart.
     *
     * @return the combined chart.
     */
//    private JFreeChart createCombinedChart() {
//
//        // create subplot 1...
//        
//        
//        final XYTextAnnotation annotation = new XYTextAnnotation("Hello!", 50.0, 10000.0);
//        annotation.setFont(new Font("SansSerif", Font.PLAIN, 9));
//        annotation.setRotationAngle(Math.PI / 4.0);
//        
//        
//        // create subplot 2...
//        MelFilterBank mfb = new MelFilterBank(2048);
//        final XYDataset data2 =  createMelFilterData(mfb.getFFTBinNumberForMel());//createDataset2();
//        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
//        final NumberAxis rangeAxis2 = new NumberAxis("Range 2");
//        rangeAxis2.setAutoRangeIncludesZero(false);
//        
//        final XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
//        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
//
//        // parent plot...
//        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
//        plot.setGap(10.0);
//        
//        // add the subplots...
//        
//        plot.add(subplot2, 1);
//        plot.setOrientation(PlotOrientation.VERTICAL);
//
//        // return a new chart containing the overlaid plot...
//        return new JFreeChart("CombinedDomainXYPlot Demo",
//                              JFreeChart.DEFAULT_TITLE_FONT, plot, true);
//
//    }

    /**
     * Creates a sample dataset.
     *
     * @return Series 1.
     */
   
    private XYDataset createDataset2() {

        // create dataset 2...
        final XYSeries series2 = new XYSeries("Series 3");

        series2.add(10.0, 16853.2);
        series2.add(20.0, 19642.3);
        series2.add(30.0, 18253.5);
        series2.add(40.0, 15352.3);
        series2.add(50.0, 13532.0);
        series2.add(100.0, 12635.3);
        series2.add(110.0, 13998.2);
        series2.add(120.0, 11943.2);
        series2.add(130.0, 16943.9);
        series2.add(140.0, 17843.2);
        series2.add(150.0, 16495.3);
        series2.add(160.0, 17943.6);
        series2.add(170.0, 18500.7);
        series2.add(180.0, 19595.9);

        return new XYSeriesCollection(series2);

    }
    
    private XYDataset createMelFilterData(int[] melFilterPoints){
    	int first = 0, middle = 1, last = 2;
    	final XYSeries melSeries = new XYSeries("Mel");
    	
    	for(int i = 0; i < melFilterPoints.length - 2; i++){
    		melSeries.add(melFilterPoints[first], 0);
    		melSeries.add(melFilterPoints[middle],10);
    		melSeries.add(melFilterPoints[last],0);
    		
    		first++;
    		middle++;
    		last++;
    	}
    	
    	return new XYSeriesCollection(melSeries);
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final CombinedXYPlotDemo1 demo = new CombinedXYPlotDemo1("CombinedDomainXYPlot Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}