package controladores;

import herramientas.ModoFantasma;

import java.util.Timer;
import java.util.TimerTask;

import personajes.Blinky;
import personajes.Clyde;
import personajes.Inky;
import personajes.Pinky;

import ventana.VentanaJuego;

public class MiTarea extends TimerTask {
	private VentanaJuego vj;
	private int tiempo=0;
	private Timer timer;
	
	public MiTarea(VentanaJuego vj){
		this.vj=vj;
	}
	
	@Override
	public void run() {
		tiempo++;
		vj.setReloj(tiempo);
		if(tiempo==7)
			setModoPersecusion();
		if(tiempo==27)
			setModoDispersion();
		if(tiempo==34)
			setModoPersecusion();
		if(tiempo==54)
			setModoDispersion();
		if(tiempo==59)
			setModoPersecusion();
		if(tiempo==119)
			setModoDispersion();
		if(tiempo==124)
			setModoPersecusion();
		if(tiempo>301){
			timer.cancel();
		}
		
	}
	
	public int getTiempo(){
		return tiempo;
	}

	public void setModoDispersion(){
		Blinky.getInstance().setModo(ModoFantasma.DISPERCION);
		Clyde.getInstance().setModo(ModoFantasma.DISPERCION);
		Pinky.getInstance().setModo(ModoFantasma.DISPERCION);
		Inky.getInstance().setModo(ModoFantasma.DISPERCION);
	}
	public void setModoPersecusion(){
		Blinky.getInstance().setModo(ModoFantasma.PERSECUCION);
		Clyde.getInstance().setModo(ModoFantasma.PERSECUCION);
		Pinky.getInstance().setModo(ModoFantasma.PERSECUCION);
		Inky.getInstance().setModo(ModoFantasma.PERSECUCION);
	}
}
