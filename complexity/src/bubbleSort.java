import java.awt.Color;


import java.awt.Shape;
import java.awt.BasicStroke; 
import java.lang.management.ManagementFactory;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import com.sun.management.ThreadMXBean;

import array.RandomData;



public class bubbleSort extends ApplicationFrame 
{
   public bubbleSort( String applicationTitle, String chartTitle )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createScatterPlot(
         chartTitle ,
         "array size" ,
         "time" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , false , false);
         
      Shape cross = ShapeUtilities.createDiagonalCross(3, 1);
      
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYItemRenderer renderer = plot.getRenderer( );
      renderer.setSeriesPaint( 2 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 0 , Color.YELLOW );
      renderer.setSeriesShape(0, cross);

      plot.setDomainCrosshairVisible(true);
      plot.setRangeCrosshairVisible(true);
       
      setContentPane( chartPanel ); 
   }
    public static void swap(int[] data, int i, int j){
	    int tmp= data[i];
	    data[i]= data[j];
	    data[j]= tmp;
	    }
    
   public static void sort(int[] data){
	    if(data.length < 2){return;}
	    boolean hadToSwap= false;
	    do{
	    hadToSwap=false;
	    for(int i= 0; i != data.length-1; ++i){
	    if(data[i] > data[i+1]){
	    swap(data, i, i+1);
	    hadToSwap= true;
	    } }
	    }while(hadToSwap);
	    }


private XYDataset createDataset( )
   {
                
      final XYSeries WallTime = new XYSeries( "WallTime" );           
      
      for(int i=1;i<=5000;i+=10){
    	  int[] tab=RandomData.generate1d(i,0,1000);
    	  
    	  long startTime=System.nanoTime();
    	  
    	  sort(tab);
    	  long endTime=System.nanoTime();
    	  
    	  WallTime.add( i , endTime-startTime );
    	 
    	  }
            
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
              
      dataset.addSeries( WallTime );          
   
      return dataset;
   }
   
  
   public static void main( String[ ] args ) 
   {
      bubbleSort chart = new bubbleSort("bubble sort", "bubble");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
      
   
}
}