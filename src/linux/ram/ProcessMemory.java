package linux.ram;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ProcessMemory extends JFrame {

	Process p;
	String s;
	ArrayList list,list2;
	String sArray[],sArray2[];
	private JPanel contentPane;
	final JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					ProcessMemory frame = new ProcessMemory();
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
	public ProcessMemory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth()); 
		int ySize = ((int) tk.getScreenSize().getHeight()); 
		setSize(1366,768);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		comboBox.setBounds(457, 330, 265, 48);
		contentPane.add(comboBox);
		
		list=new ArrayList();
		sArray=new String[1000];
		JButton btnNewButton = new JButton("List IDs");
		btnNewButton.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultComboBoxModel model;
				 try{
			    	 p = Runtime.getRuntime().exec("ps -A -o pid");
			         BufferedReader br = new BufferedReader(
			             new InputStreamReader(p.getInputStream()));
			         while ((s = br.readLine()) != null)
			        	 list.add(s);
			         p.waitFor();
			         System.out.println ("exit: " + p.exitValue());
			         p.destroy();
			    	 }
				 catch(Exception e)
				 {
					 System.out.println("Exception "+e);
				 }
				 
				 for(int i=0;i<list.size();i++)
				 {
					 sArray[i]=list.get(i).toString();
				 }
				 model=new DefaultComboBoxModel(sArray);
				 comboBox.setModel(model);
			}
		});
		btnNewButton.setBounds(734, 330, 265, 48);
		contentPane.add(btnNewButton);
		
		final JTextArea textArea = new JTextArea();
		JScrollPane pane=new JScrollPane(textArea);
		textArea.setBounds(10, 88, 420, 215);
		pane.setBounds(114,11,1151,296);
		contentPane.add(pane);
		
		JButton btnMemoryinfo = new JButton("MemoryInfo");
		btnMemoryinfo.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnMemoryinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try{
					 String s1=comboBox.getSelectedItem().toString();
			    	 p = Runtime.getRuntime().exec("pmap "+s1);
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
					 System.out.println("Exception "+e);
				 }
			}
		});
		btnMemoryinfo.setBounds(587, 519, 265, 78);
		contentPane.add(btnMemoryinfo);
		
		JButton btnRunningapps = new JButton("RunningApps");
		btnRunningapps.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnRunningapps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
			    	 p = Runtime.getRuntime().exec("xlsclients");
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
					 System.out.println("Exception "+e);
				 }
			}
		});
		btnRunningapps.setBounds(1008, 431, 265, 78);
		contentPane.add(btnRunningapps);
		
		JButton btnRunningtime = new JButton("RunningTime");
		btnRunningtime.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnRunningtime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					textArea.setText("");
					String s2=comboBox.getSelectedItem().toString();
					 System.out.println("Process ID clicked was "+s2);
			    	 p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "ps -eo pid,comm,cmd,start,etime | grep -i "+s2});
			         BufferedReader br = new BufferedReader(
			             new InputStreamReader(p.getInputStream()));
			         System.out.println("Came after process exceution1");
			         while ((s = br.readLine()) != null)
			         {
			        	 System.out.println("Came after process exceution2");
			        	 textArea.append(s+"\n");
			         }
		
			         p.waitFor();
			         System.out.println ("exit: " + p.exitValue());
			         p.destroy();
			    	 }
				 catch(Exception e)
				 {
					 System.out.println("Exception "+e);
				 }
			}
		});
		btnRunningtime.setBounds(166, 429, 265, 78);
		contentPane.add(btnRunningtime);
		
		JButton btnIdletime = new JButton("IdleTime");
		btnIdletime.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnIdletime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					textArea.setText("");
					String s2=comboBox.getSelectedItem().toString();
			    	 p = Runtime.getRuntime().exec("bash /home/mayooran/Desktop/mn "+s2);
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
					 System.out.println("Exception "+e);
				 }
			}
		});
		btnIdletime.setBounds(166, 606, 265, 78);
		contentPane.add(btnIdletime);
		
		JLabel label = new JLabel("RAM");
		label.setFont(new Font("Poor Richard", Font.PLAIN, 48));
		label.setBounds(287, 0, 140, 77);
		contentPane.add(label);
		
		JButton btnProcesstree = new JButton("ProcessTree");
		btnProcesstree.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnProcesstree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					textArea.setText("");
					String s2=comboBox.getSelectedItem().toString();
			    	 p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "ps -aux | awk ' /^mayooran/ { system(\"pstree \" 2686) }' "});
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
					 System.out.println("Exception "+e);
				 }
			}
		});
		btnProcesstree.setBounds(1008, 606, 265, 78);
		contentPane.add(btnProcesstree);
		
		 JLabel lblNewLabel = new JLabel("New label");
		 lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/74166.jpg"))));
		 lblNewLabel.setBounds(0, 0, 1374, 744);
		 contentPane.add(lblNewLabel);
	}
}
