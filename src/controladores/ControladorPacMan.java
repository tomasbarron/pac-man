package controladores;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import herramientas.Direccion;
import personajes.PacMan;

public class ControladorPacMan implements KeyListener{
	
	private PacMan pac;
	
	public ControladorPacMan(){
		pac=PacMan.getInstance();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int tecla= e.getKeyCode();
		switch (tecla) {
		case 38:
			pac.setDireccion(Direccion.ARRIBA);
			break;
		case 40:
			pac.setDireccion(Direccion.ABAJO);
			break;
		case 37:
			pac.setDireccion(Direccion.IZQUIERDA);
			break;
		case 39:
			pac.setDireccion(Direccion.DERECHA);
			break;
		default:
			break;
		}
	}
	
	public Direccion getDireccionPacMan(){
		return pac.getDireccion();
	}
	
	public PacMan getPacMan(){
		return this.pac;
	}
	
	public void setPacMan(PacMan pac){
		this.pac=pac;
	}
					
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
