package juego;

/**
 * Clase juego, se encarga del funcionamiento principal del juego, es la clase que maneja al resto de las clases.
 * @author Barron Juan Tomas-Tomasetto Santiago
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import herramientas.*;
import personajes.*;

public class Juego{
		
	private Tablero t;
	private PacMan pac;
	private Blinky bli;
	private Clyde cly;
	private Inky ink;
	private Pinky pin;
	private Puntaje[] puntajes;

	
	/**
	 * Constructor de la clase juego, instancia todos los personajes e inicializa variables.
	 */
	
	public Juego(){
		t=Tablero.getInstance();
		pac=PacMan.getInstance();
		bli=Blinky.getInstance();
		cly=Clyde.getInstance();
		ink=Inky.getInstance();
		pin=Pinky.getInstance();
	}
	
	//Metodos
	
	
	/**
	 * El metodo checkTurno chequea si los fantasmas comen a Pac-Man y, en caso de que este se encuentre en modo Super Pac-Man, si Pac-Man se come un fantasma. 
	 * Ademas en caso de que alguno de esos casos suceda reinicia las posiciones de los personajes dentro del mapa
	 */
	
	public void checkTurno(){
		if(pac.getModo()==ModoPacMan.NORMAL ){
			if(pac.coincidePosicion(bli) || pac.coincidePosicion(cly) || pac.coincidePosicion(ink) || pac.coincidePosicion(pin)){
				System.out.println("");
				pac.morir();
			    bli.restart();
			    ink.restart();
			    pin.restart();
			    cly.restart();
			}
		}
		else if(pac.getModo()==ModoPacMan.SUPERPACMAN){
			if(pac.coincidePosicion(bli)){
				 bli.restart();
				 pac.comerFantasma();
			}
			if(pac.coincidePosicion(cly)){
				 cly.restart();
				 pac.comerFantasma();
			}
			if(pac.coincidePosicion(ink)){
				 ink.restart();
				 pac.comerFantasma();
		
			}
			if(pac.coincidePosicion(bli)){
				 ink.restart();
				 pac.comerFantasma();
			}
		}
		
		if((pac.getModo()==ModoPacMan.NORMAL)&&(bli.getModo()==ModoFantasma.ASUSTADO)){ //en caso de que pacman vuelva a su modo normal y los fantasmas esten todavia en su modo asustados losdevuelve a persecucion
			bli.setModo(ModoFantasma.PERSECUCION);
			pin.setModo(ModoFantasma.PERSECUCION);
			ink.setModo(ModoFantasma.PERSECUCION);
			cly.setModo(ModoFantasma.PERSECUCION);
			}
	}
	

	/**
	 * Metodo que se encarga de correr el juego hasta que Pac-Man haya perdido todas sus vidas o haya comido una Bola de poder(condicion para la entrega del modulo 1).
	 * Ejecuta los turnos de cada personaje.
	 */
	
	public void jugar(){
		this.inicializarPuntajes();
	}
		
		public void agregarPuntaje(Puntaje p){
			if(pac.getScore()>puntajes[19].getPuntaje()){
				int i=19;
				while((pac.getScore()>puntajes[i].getPuntaje())&&(i!=0)){
					i--;
				}
				for(int j=19;j>i;j--){
					puntajes[j].setPuntaje(puntajes[j-1].getPuntaje());
					puntajes[j].setNombre(puntajes[j-1].getNombre());
					puntajes[j].setTiempo(puntajes[j-1].getTiempo());
				}
				puntajes[i].setPuntaje(p.getPuntaje());
				puntajes[i].setNombre(p.getNombre());
				puntajes[i].setTiempo(p.getTiempo());
			}
			persistirPuntajes();
		}
		
		public void inicializarPuntajes(){
			puntajes=new Puntaje[20];
			ObjectInputStream lectura=null;
				try{
					lectura = new ObjectInputStream(new FileInputStream("puntajes"));
					puntajes=(Puntaje[])lectura.readObject();
				}catch(IOException e){
					for(int i=0;i<20;i++){
						puntajes[i]=new Puntaje();
					}
					persistirPuntajes();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
		
		
		public void persistirPuntajes(){
			ObjectOutputStream salida=null;
			try{
				salida= new ObjectOutputStream(new FileOutputStream("puntajes"));
				salida.writeObject(puntajes);
			}catch(IOException e){
				e.printStackTrace();
			}
		}

	
	//getters y setters

	public Tablero getT() {
		return t;
	}

	public void setT(Tablero t) {
		this.t = t;
	}

	public PacMan getPac() {
		return pac;
	}

	public void setPac(PacMan pac) {
		this.pac = pac;
	}

	public Blinky getBli() {
		return bli;
	}

	public void setBli(Blinky bli) {
		this.bli = bli;
	}

	public Clyde getCly() {
		return cly;
	}

	public void setCly(Clyde cly) {
		this.cly = cly;
	}

	public Inky getInk() {
		return ink;
	}

	public void setInk(Inky ink) {
		this.ink = ink;
	}

	public Pinky getPin() {
		return pin;
	}

	public void setPin(Pinky pin) {
		this.pin = pin;
	}

	public Puntaje[] getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(Puntaje[] puntajes) {
		this.puntajes = puntajes;
	}


}
