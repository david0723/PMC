package Graficas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

import mundo.Hadron;
import mundo.Quark;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class Interfaz {

	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel= new JPanel();
		panel.setBounds(82, 90, 438, 202);
		frame.getContentPane().add(panel);
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				showDataForever();
			}
		});
		btnTest.setBounds(172, 317, 251, 36);
		frame.getContentPane().add(btnTest);
	}
	
	public void showDataForever()
	{

		panel.removeAll();
		ChartPanel cp = new ChartPanel(createData());
		panel.add(cp);
		panel.updateUI();
	}
	
	public JFreeChart createData()
	{
		Hadron hadron = new Hadron();
		for (int i = 0; i < 10; i ++)
		{
			Quark quark = new Quark(new Date(), (long) (Math.random()*10), (long) (Math.random()*10), (long) (Math.random()*10));
			hadron.addQuark(quark);
		}
		XYDataset data = hadron.createDataset();
		return hadron.createChart(data);
	}

}
