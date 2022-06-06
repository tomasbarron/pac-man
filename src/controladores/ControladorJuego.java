package controladores;


import javax.swing.JOptionPane;
import juego.Juego;
import juego.Puntaje;
import juego.Tablero;
import personajes.Blinky;
import personajes.Fantasma;
import personajes.PacMan;
import ventana.VentanaJuego;

public class ControladorJuego extends Thread {
	private VentanaJuego vj;
	private Juego j; 
	private Puntaje puntajes;
	private int tiempoFinal=0;
	
	public ControladorJuego(VentanaJuego vj, Juego j){
		this.vj=vj;
		this.j=j;
		j.jugar();
		
	}
	
	public void run(){
		while((j.getPac().getVidas()>0)&&(j.getT().getCantItemsComidos()<j.getT().getCantItemsMax())&&(vj.getReloj()<300)){
			j.checkTurno();
			vj.repaint();
			turnoPacMan();
			turnoFantasma(j.getBli());
			turnoFantasma(j.getPin());
			turnoFantasma(j.getCly());
			j.getInk().calcularCamino(Tablero.getInstance(),PacMan.getInstance(),Blinky.getInstance());
			j.getInk().ejecutarAccion(Tablero.getInstance());
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			System.err.print("se interrumpio el sleep");
			}
		}
		tiempoFinal=vj.getReloj();
		if(j.getPac().getScore()>j.getPuntajes()[19].getPuntaje()){
			puntajes=new Puntaje();
			String cadena=JOptionPane.showInputDialog("Felicidades! Usted esta dentro de los 20 puntajes maximos! \n Ingrese su nombre");
			boolean notOk=true;
			if((cadena!=null)&&(cadena.length()>2)&&(!(cadena.contains(" "))))
				notOk=false;
			while((cadena!=null)&&(notOk)){
				cadena=JOptionPane.showInputDialog("Nombre no valido! \n Ingrese su nombre");
				if((cadena.length()>2)&&(!(cadena.contains(" "))))
					notOk=false;
			}
			if(cadena!=null){ 	//si es igual a null es porque clickearon cancelar por lo tanto no hay que guardar un puntaje.
				if(cadena.length()>20)
					cadena=cadena.substring(0, 20);
				puntajes.setNombre(cadena);
				puntajes.setPuntaje(j.getPac().getScore());
				puntajes.setTiempo(tiempoFinal);
				j.agregarPuntaje(puntajes);
			}
		}
		else{
			JOptionPane.showMessageDialog(
					null, //padre
					"Su puntaje final es:  "+j.getPac().getScore(), 
					"GAME OVER", // 
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void turnoPacMan(){
		j.getPac().ejecutarAccion(j.getT());
		j.getPac().comerItem(j.getT());
	}
	
	private void turnoFantasma(Fantasma f){
		f.calcularCamino(j.getT(),j.getPac());
		f.ejecutarAccion(j.getT());
	}
	

}
