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



public class quickSort extends ApplicationFrame 
{
   public quickSort( String applicationTitle, String chartTitle )
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
    
   public static int partition(int[] data, int begin, int end, int pivotIdx){
	    swap(data, pivotIdx, --end);
	    pivotIdx= end;
	    int pivot= data[pivotIdx];
	    //invariant is that everything before begin is known to be < pivot
	    // and everything after end is known to be >= pivot
	    while(begin != end){
	    if(data[begin] >= pivot){
	    swap(data, begin, --end);
	    }else{
	    ++begin;
	    }
	    }
	    swap(data, pivotIdx, begin);
	    return begin;
	    }
   public static  int[] sort(int[] data){
	    sort(data, 0, data.length);
	    return data;
	    }
	   
	    public static void sort(int[] data, int begin, int end){
	    if((end-begin) < 2){ return; }
	    int m= partition(data, begin, end, (end+begin)/2);
	    sort(data, begin, m);
	    sort(data, m+1, end); // +1 for convergence }
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
	   quickSort chart = new quickSort("quick sort", "quick");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
      
   
}
}