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



public class mergeSort extends ApplicationFrame 
{
   public mergeSort( String applicationTitle, String chartTitle )
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
  
    
     public static void mergeSorted(int data[], int begin, int middle, int end){
    	 int[] tmp= new int[middle-begin];
    	 System.arraycopy(data, begin, tmp, 0, tmp.length);
    	 int i=0, j=middle, dest=begin;
    	 while((i< tmp.length) && (j<end)){
    	 data[dest++]= (tmp[i] < data[j]) ? tmp[i++] : data[j++] ;
    	 }
    	 while(i < tmp.length){
    	 data[dest++]= tmp[i++];
    	 }
    	 }
     
     public static void sort(int[] data){
    	  sort(data, 0, data.length);
    	  }
    	  public static void sort(int[] data, int begin, int end){
    	  if((end-begin) < 2){return;}
    	  int middle= (end+begin)/2;
    	  sort(data, begin, middle);
    	  sort(data, middle, end);
    	  mergeSorted(data, begin, middle, end);
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
	   mergeSort chart = new mergeSort("merge sort", "merge");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
      
   
}
}