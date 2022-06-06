package ventana;


import java.awt.*;

import javax.swing.*;

import controladores.ControladorBotonInstrucciones;
import controladores.ControladorBotonJuego;
import controladores.ControladorBotonTOP;
import controladores.ControladorBotonConfig;

public class VentanaPrincipal extends JFrame  {
	

	private JPanel panel;
	private BotonRedondo boton1;
	private BotonRedondo boton2;
	private BotonRedondo boton3;
	private BotonRedondo boton4;
	
	public VentanaPrincipal(){
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Pac Man"); 
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    this.setResizable(true);
		panel= new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.darkGray);
		creaBotones();
		add(panel);	
		boton1.addActionListener(new ControladorBotonInstrucciones());
		boton2.addActionListener(new ControladorBotonJuego());
		boton3.addActionListener(new ControladorBotonTOP());
		boton4.addActionListener(new ControladorBotonConfig());
		this.setSize(730, 400);
	}
	
	private  void setBoton(BotonRedondo boton,int x, int y, int p ,int p1){ 
		boton.setBounds(x, y, p, p1); 
        boton.setFocusPainted(false); 
        boton.setForeground(Color.YELLOW); 
        boton.setFont(new Font("DialogInput", Font.BOLD, 15));
        panel.add(boton);  
		}

	private  void creaBotones( ){  
		boton1 = new BotonRedondo("Instrucciones", Color.BLACK, Color.GRAY); 
		boton2 = new BotonRedondo("Empezar Juego", Color.BLACK, Color.GRAY);
		boton3 = new BotonRedondo("TOP Scores", Color.BLACK, Color.GRAY);
		boton4 = new BotonRedondo("Config",Color.BLACK,Color.GRAY);
		setBoton(boton1,75,80,160,160); 
		setBoton(boton2,275,150,160,160);
		setBoton(boton3,475,80,160,160);
		setBoton(boton4,600,20,100,40);
		panel.add(boton4);
	
			}


	  

	
	
}

