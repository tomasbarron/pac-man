package juego;

import java.io.Serializable;

public class Puntaje implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int tiempo;
	private int puntaje;
	
	public Puntaje(){
		this.nombre="AAA";
		this.tiempo=0;
		this.puntaje=0;
	}
	
	public Puntaje(String nombre,int tiempo,int puntaje){
		this.nombre=nombre;
		this.tiempo=tiempo;
		this.puntaje=puntaje;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setTiempo(int tiempo){
		this.tiempo=tiempo;
	}
	
	public int getTiempo(){
		return this.tiempo;
	}
	
	public void setPuntaje(int puntaje){
		this.puntaje=puntaje;
	}
	
	public int getPuntaje(){
		return puntaje; 
	}
	
	

}
