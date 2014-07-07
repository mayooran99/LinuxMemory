package linux.ram;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
public class MemoryHome {

	private JPanel contentPane;
	static TimeSeries ts = new TimeSeries("data", Millisecond.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MemoryHome frame = new MemoryHome();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemoryHome() throws InterruptedException{
		
		   gen myGen = new gen();
	        new Thread(myGen).start();

	        TimeSeriesCollection dataset = new TimeSeriesCollection(ts);
	        JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "PhysicalMemoryPercentage",
	            "Time",
	            "Value",
	            dataset,
	            true,
	            true,
	            false
	        );
	        final XYPlot plot = chart.getXYPlot();
	        ValueAxis axis = plot.getDomainAxis();
	        axis.setAutoRange(true);
	        axis.setFixedAutoRange(60000.0);

	        JFrame frame = new JFrame("PhysicalMemoryPercentage");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        ChartPanel label = new ChartPanel(chart);
	        frame.getContentPane().add(label);
	      
	    
	        //Suppose I add combo boxes and buttons here later
	        frame.pack();
	        frame.setVisible(true);
		
		
	}
	
	static class gen implements Runnable {
    	Process p;
    	String s;
    	Double val;

        public void run() {
            while(true) {
            	try{
			    	 p = Runtime.getRuntime().exec("sh /home/mayooran/Desktop/nm");
			    	 
			         BufferedReader br = new BufferedReader(
			             new InputStreamReader(p.getInputStream()));
			         while ((s = br.readLine()) != null)
			         {
			        	 val=Double.parseDouble(s);
			         }
			         p.waitFor();
			         System.out.println ("exit: " + p.exitValue());
			         p.destroy();
			    	 
                ts.addOrUpdate(new Millisecond(), val);
                    Thread.sleep(2000);
            	}
                 catch (Exception ex) {
                    System.out.println(ex);
                    break;
                }
            }
        }
    }

}
