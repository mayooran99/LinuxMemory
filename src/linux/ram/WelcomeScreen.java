package linux.ram;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class WelcomeScreen extends JFrame {

	private JPanel contentPane;
	private final static int interval=1000;
	Timer t;
	int i;
	int count=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					WelcomeScreen frame = new WelcomeScreen();
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
	public WelcomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366,768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JProgressBar progressBar = new JProgressBar(0,20);
		progressBar.setBounds(496, 443, 450, 45);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);
		
		JLabel lblAutomatedComputerResource = new JLabel("AUTOMATED COMPUTER RESOURCE MANAGEMENT");
		lblAutomatedComputerResource.setForeground(Color.WHITE);
		lblAutomatedComputerResource.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		lblAutomatedComputerResource.setBounds(379, 305, 849, 183);
		contentPane.add(lblAutomatedComputerResource);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/74166.jpg"))));
		lblNewLabel.setBounds(0, 0, 1350, 742);
		contentPane.add(lblNewLabel);
		i=0;
		
		
		t=new Timer(interval, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(i==20){
					t.stop();
				}
				
				else{
					
					i=i+5;
					progressBar.setValue(i);
					System.out.println("i is" +i);
					count++;
					if(count==4)
					{
						try {
							Thread.sleep(1000);
							new HomePage().show();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
				}
			}
		
		});
		
		t.start();
		
		
	}
}
