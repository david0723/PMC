package Graficas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

import mundo.Hadron;
import mundo.Looper;
import mundo.Master;
import mundo.Quark;

public class InterfazPMC {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	private Master master;
	private JLabel lblgraficaHistorial;
	private JPanel panelHistorial;
	private Hadron hadron;
	private Looper loop;
	private JPanel panelTiempoReal;
	private JLabel lblgraficaTR;
	private JLabel lblCrowdMeter;
	private JPanel panelMeter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPMC window = new InterfazPMC();
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
	public InterfazPMC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inithadron();
		loop = new Looper(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 656, 641);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 644, 607);
		frame.getContentPane().add(tabbedPane);
		
		panelTiempoReal = new JPanel();
		tabbedPane.addTab("Tiempo Real", null, panelTiempoReal, null);
		panelTiempoReal.setLayout(null);
		
		lblgraficaTR = new JLabel("");
		lblgraficaTR.setBounds(6, 6, 967, 508);
		panelTiempoReal.add(lblgraficaTR);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				new Thread(loop).start();
			}
		});
		btnStart.setBounds(281, 526, 117, 29);
		panelTiempoReal.add(btnStart);
		
		panelHistorial = new JPanel();
		tabbedPane.addTab("Historial", null, panelHistorial, null);
		panelHistorial.setLayout(null);
		
		textField = new JTextField();
		textField.setText("12/02/2015");
		textField.setBounds(134, 494, 134, 28);
		panelHistorial.add(textField);
		textField.setColumns(10);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha inicial");
		lblFechaDeInicio.setBounds(33, 500, 134, 16);
		panelHistorial.add(lblFechaDeInicio);
		
		JLabel lblFechaFinal = new JLabel("Fecha final");
		lblFechaFinal.setBounds(280, 500, 121, 16);
		panelHistorial.add(lblFechaFinal);
		
		textField_1 = new JTextField();
		textField_1.setText("12/02/2015");
		textField_1.setBounds(360, 494, 134, 28);
		panelHistorial.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
				Hadron h = new Hadron();
				try 
				{
					h = master.darHistorial(format.parse(textField.getText()), format.parse(textField_1.getText()));
				} 
				catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblgraficaHistorial.setIcon(h.toChart());
				panelHistorial.updateUI();
				
				
			}
		});
		btnVer.setBounds(212, 528, 117, 29);
		panelHistorial.add(btnVer);
		
		lblgraficaHistorial = new JLabel("aqui va la grafica");
		lblgraficaHistorial.setBounds(6, 6, 967, 474);
		panelHistorial.add(lblgraficaHistorial);
		
		panelMeter = new JPanel();
		tabbedPane.addTab("Crowd Meter", null, panelMeter, null);
		panelMeter.setLayout(new BorderLayout(0, 0));
		
		lblCrowdMeter = new JLabel("Crowd Meter");
		panelMeter.add(lblCrowdMeter, BorderLayout.CENTER);
	}
	
	public void showData()
	{
//		panel.removeAll();
		Quark q = new Quark();
		q.random(hadron.getLastQuark());
		hadron.insertQuark(q);
		
		ImageIcon chart = hadron.toChart();
		lblgraficaTR.setIcon(chart);
		
		ImageIcon meter = hadron.toMeter();
		lblCrowdMeter.setIcon(meter);
//		lblNewLabel =new JLabel(chart);
//		panel.add(lblNewLabel, BorderLayout.CENTER);
//		panel.updateUI();
		
//		ChartPanel cp = new ChartPanel(hadron.getChart());
//		panel.add(cp);
		panelMeter.updateUI();
		panelTiempoReal.updateUI();
	}
	

	public void inithadron()
	{
		Quark xquark = new Quark(new Date(),20,30,40);
		this.hadron = new Hadron();
		for (int i = 0; i < 25; i ++)
		{
			Quark quark = new Quark();
			quark.random(xquark);
			xquark = quark;
			hadron.addQuark(quark);
		}
	}
}
