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
import mundo.Refresher;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class Interfaz 
{

	private Thread ref;
	
	private JFrame frame;
	private JPanel panel;
	private Hadron hadron; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} 
				catch (Exception e) {e.printStackTrace();}
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
	private void initialize() 
	{
		ref = new Thread(new Refresher(this));
		inithadron();
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
				ref.start();
//				try {
//					showDataForever();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				show();
			}
		});
		btnTest.setBounds(172, 317, 251, 36);
		frame.getContentPane().add(btnTest);
	}
	
	public void showDataForever() throws InterruptedException
	{
System.out.println("show data");
		for (int i = 0; i !=100; i++)
		{
			System.out.println("Loop: "+i);
			
			panel.removeAll();
			hadron.insertQuark(new Quark());
			ChartPanel cp = new ChartPanel(hadron.getChart());
			panel.add(cp);
			panel.updateUI();
			Thread.sleep(1000);
		}
		
	}
	
	public void showData()
	{
		panel.removeAll();
		hadron.insertQuark(new Quark());
		ChartPanel cp = new ChartPanel(hadron.getChart());
		panel.add(cp);
		panel.updateUI();
	}
	

	public void inithadron()
	{
		this.hadron = new Hadron();
		for (int i = 0; i < 25; i ++)
		{
			Quark quark = new Quark();
			hadron.addQuark(quark);
		}
	}

}
