package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;

import personajes.PacMan;

import juego.Juego;
import ventana.VentanaJuego;


public class ControladorBotonJuego implements ActionListener,WindowListener {
	private Juego juego;
	private VentanaJuego vj;
	private ControladorJuego controlJuego;
	private MiTarea reloj;
	@Override
	public void actionPerformed(ActionEvent e) {
		juego=new Juego();
		vj=new VentanaJuego();
		vj.addWindowListener(this);
		Timer timer=new Timer();
		reloj=new MiTarea(vj);
		timer.scheduleAtFixedRate(reloj, 0, 1000);
		controlJuego=new ControladorJuego(vj,juego);
		controlJuego.start();

	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {	
		PacMan.getInstance().setVidas(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}