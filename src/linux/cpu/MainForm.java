package linux.cpu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class MainForm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	String output = null;
    Process p;
    String s;
    Runner r_thread;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
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
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(114, 11, 1130, 275);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("CPU Graph");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 try {
			            CPU_Graph graph = new CPU_Graph();
			        } 
				 catch (InterruptedException ex) {
			            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
			        }
		        
			}
		});
		btnNewButton.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnNewButton.setBounds(114, 429, 265, 78);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Start Monitoring");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r_thread = new Runner();
		        r_thread.start();
			}
		});
		btnNewButton_1.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnNewButton_1.setBounds(555, 429, 238, 78);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Stop Monitoring");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r_thread.interrupt();
			}
		});
		btnNewButton_2.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnNewButton_2.setBounds(1019, 429, 257, 78);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainForm.class.getResource("/images/74166.jpg")));
		lblNewLabel.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel);
	}
	
	public class Runner extends Thread {

        public void run() {
            String[] command = {"bash", "-c", "ps -eo %cpu,pid,command --sort -%cpu"};
            while (!isInterrupted()) {
                try {
                    output = simple.captureOutput(command);
                    jTextArea1.setText("");
                    jTextArea1.setText(output);
                sleep(1000);
                }catch (InterruptedException ex) {
                    this.interrupt();
                    //Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
