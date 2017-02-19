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



public class minimum extends ApplicationFrame 
{
   public minimum( String applicationTitle, String chartTitle )
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


private XYDataset createDataset( )
   {
	ThreadMXBean bean=(ThreadMXBean) ManagementFactory.getThreadMXBean();
	
      final XYSeries UserTime = new XYSeries( "UserTime" );                    
      final XYSeries WallTime = new XYSeries( "WallTime" );           
      int[] temp=new int[51];
      for(int i=1;i<=500;i+=10){
    	  int[] tab=RandomData.generate1d(i,0,1000);
    	  
    	  long startTime=System.nanoTime();
    	  //long userStartTime=bean.getCurrentThreadCpuTime();
    	  RandomData.Min(tab);
    	  long endTime=System.nanoTime();
    	  //long userEndTime=bean.getCurrentThreadCpuTime();
    	  //UserTime.add( i , userEndTime-userStartTime );  
    	  WallTime.add( i , endTime-startTime );
    	  }
            
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      //dataset.addSeries( UserTime);          
      dataset.addSeries( WallTime );          
   
      return dataset;
   }
   
  
   public static void main( String[ ] args ) 
   {
      minimum chart = new minimum("minimum", "compare");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
      
   
}
}