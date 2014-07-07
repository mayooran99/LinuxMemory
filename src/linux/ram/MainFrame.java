package linux.ram;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	Process p;
	String s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth()); 
		int ySize = ((int) tk.getScreenSize().getHeight()); 
		setSize(1366,768);
		
		//setBounds(100, 100, 669, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		show();
		JScrollPane pane = new JScrollPane();
		pane.setBounds(114,11,1151,296);
		contentPane.add(pane);
		
		final JTextArea textArea = new JTextArea();
		pane.setViewportView(textArea);
		
		
		JButton btnRamInfo = new JButton("System Memory");
		btnRamInfo.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnRamInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				repaint();
				
				 try{
					 
			    	 p = Runtime.getRuntime().exec("cat /proc/meminfo");
			         BufferedReader br = new BufferedReader(
			             new InputStreamReader(p.getInputStream()));
			         while ((s = br.readLine()) != null)
			         {
			        	 textArea.append(s+"\n");
			         }
			         p.waitFor();
			         p.destroy();
			 
			    	 }
				 catch(Exception e)
		    	 {
		    		 
		    	 }
			}
		});
		btnRamInfo.setBounds(971, 600, 265, 78);
		contentPane.add(btnRamInfo);
		 
		 JButton btnNewButton = new JButton("Advanced");
		 btnNewButton.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		 try{
			    	 p = Runtime.getRuntime().exec("sudo dmidecode –t 17");
			         BufferedReader br = new BufferedReader(
			             new InputStreamReader(p.getInputStream()));
			         while ((s = br.readLine()) != null)
			         {
			             textArea.append(s+"\n");
			             System.out.println("Advanced value is "+s);
			         }
			         p.waitFor();
			         p.destroy();
			    	 }
				 catch(Exception e)
		    	 {
		    		 
		    	 }
		 	}
		 });
		 btnNewButton.setBounds(166, 429, 265, 78);
		 contentPane.add(btnNewButton);
		 
		 final JComboBox comboBox = new JComboBox();
		 comboBox.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		 comboBox.setBounds(457, 330, 265, 48);
		 contentPane.add(comboBox);
		 
		 comboBox.addItem("All Processes");
		 comboBox.addItem("Except Root Processes");
		 comboBox.addItem("Processes by logged in user");
		 
		 JButton btnListProcesses = new JButton("List Processes");
		 btnListProcesses.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		 btnListProcesses.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		textArea.setText("");
		 		 String o=comboBox.getSelectedItem().toString();
				 if(o.equalsIgnoreCase("All Processes"))
				 {
					 try{
				    	 p = Runtime.getRuntime().exec("ps aux");
				         BufferedReader br = new BufferedReader(
				             new InputStreamReader(p.getInputStream()));
				         while ((s = br.readLine()) != null)
				        	 textArea.append(s+"\n");
				         p.waitFor();
				         System.out.println ("exit: " + p.exitValue());
				         p.destroy();
				    	 }
					 catch(Exception e)
					 {
			   		 
					 }
				 }
				 else if(o.equalsIgnoreCase("Except Root Processes"))
				 {
					 textArea.setText("");
					 try{
				    	 p = Runtime.getRuntime().exec("ps -U root -u root -N");
				         BufferedReader br = new BufferedReader(
				             new InputStreamReader(p.getInputStream()));
				         while ((s = br.readLine()) != null)
				        	 textArea.append(s+"\n");
				         p.waitFor();
				         System.out.println ("exit: " + p.exitValue());
				         p.destroy();
				    	 }
					 catch(Exception e)
					 {
			   		 
					 }
				 }
				 else if(o.equalsIgnoreCase("Processes by logged in user"))
				 {
					 textArea.setText("");
					 try{
				    	 p = Runtime.getRuntime().exec("ps -u mayooran");
				         BufferedReader br = new BufferedReader(
				             new InputStreamReader(p.getInputStream()));
				         while ((s = br.readLine()) != null)
				        	 textArea.append(s+"\n");
				         p.waitFor();
				         System.out.println ("exit: " + p.exitValue());
				         p.destroy();
				    	 }
					 catch(Exception e)
					 {
			   		 
					 }
				 }
		 	}
		 });
		 btnListProcesses.setBounds(734, 331, 265, 48);
		 contentPane.add(btnListProcesses);
		 
		 JButton btnViewProcessdetails = new JButton("Process Details");
		 btnViewProcessdetails.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		 btnViewProcessdetails.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		new ProcessMemory().setVisible(true);
		 	}
		 });
		 btnViewProcessdetails.setBounds(166, 598, 265, 78);
		 contentPane.add(btnViewProcessdetails);
		 
		 JButton btnGraph = new JButton("Graph");
		 btnGraph.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		 btnGraph.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		try {
					new MemoryHome();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	}
		 });
		 btnGraph.setBounds(971, 435, 265, 78);
		 contentPane.add(btnGraph);
		 
		 JLabel lblNewLabel = new JLabel("New label");
		 lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/74166.jpg"))));
		 lblNewLabel.setBounds(0, 0, 1374, 744);
		 contentPane.add(lblNewLabel);
		
	}
	
	public void loadData(JTextArea t)
	{
		
		 try{
	    	 p = Runtime.getRuntime().exec("free -ms 5");
	         BufferedReader br = new BufferedReader(
	             new InputStreamReader(p.getInputStream()));
	         while ((s = br.readLine()) != null)
	        	 t.setText(s);
	         p.waitFor();
	         System.out.println ("exit: " + p.exitValue());
	         p.destroy();
	    	 }
		 catch(Exception e)
   	 {
   		 
   	 }
	}
}
