package juego;

/**
 * Clase con todos los elementos que contiene el tablero exceptuando a los personajes.
 * @author Barron Juan Tomas-Tomasetto Santiago
 */

import classpath.Map;
import items.*;

public class Tablero {
	
	private static int columnas=31;    						   //numero de columnas
	private static int filas=28;							   //numero de filas
	private Item tableroJuego[][]= new Item[columnas][filas];  //tablero de items
	private Map m=new Map();                                   //referencia muros y caminos
	private boolean masDe30Puntos;
	private boolean masDeUnTercio;
	private int cantItemsMax;								// maxima cantidad de puntos (referencia para Clyde)
	private int cantItemsComidos=0;
	private static Tablero instancia=new Tablero();
	
	/**
	 * Constructor de la clase tablero, inicializa las celdas del mismo con Muros, Pac-Puntos, Bolas de poder o Caminos segun corresponda.
	 * Tambien inicializa variables como masDe30Puntos, masDeUnTercio y canPuntosMax.
	 */
	private Tablero(){
		this.masDe30Puntos=false;
		this.masDeUnTercio=false;
		for(int i=0;i<columnas;i++){
			for(int j=0;j<filas;j++){
				if(m.getCollidable(i,j)==0){
					if((i<9)||(i>19)){
						this.tableroJuego[i][j]=new PacPunto(i,j);
						setCantItemsMax(getCantItemsMax()+1);
					}
					else
						this.tableroJuego[i][j]=new Camino(i,j);
				}
				else
					this.tableroJuego[i][j]=new Muro(i,j);
			}	
		}
		for(int i=9;i<20;i++){
			this.tableroJuego[i][6]=new PacPunto(i,6);
			this.tableroJuego[i][21]=new PacPunto(i,21);
			setCantItemsMax(getCantItemsMax()+2);
		}
		this.tableroJuego[3][1] = new BolaDePoder(3,1);		
		this.tableroJuego[3][26] = new BolaDePoder(3,26);	
		this.tableroJuego[23][1] = new BolaDePoder(23,1);	
		this.tableroJuego[23][26] = new BolaDePoder(23,26);
		setCantItemsMax(getCantItemsMax()+4);
	}
	
	//getters y setters
	
	public static Tablero getInstance(){
		return instancia;
	}
	
	public Map getM() {
		return m;
	}
	
	public Item[][] getTableroJuego() {
		return tableroJuego;
	}
	
	public void setM(Map m) {
		this.m = m;
	}

	public void setTableroJuego(Item[][] tableroJuego) {
		this.tableroJuego = tableroJuego;
	}

	public boolean hayPacPunto(int x,int y){
		return (this.tableroJuego[x][y] instanceof PacPunto);
	}
	
	public boolean hayBolaDePoder(int x,int y){
		return (this.tableroJuego[x][y] instanceof BolaDePoder);
	}
	
	public Item getCelda(int posX,int posY) {
		return tableroJuego[posX][posY];
	}

	public void setCelda(Item item, int posX,int posY) {
		this.tableroJuego[posX][posY]=item;
	}
	
	public boolean esMasDe30Puntos() {
		return masDe30Puntos;
	}

	public void setMasDe30Puntos(boolean masDe30Puntos) {
		this.masDe30Puntos=masDe30Puntos;
	}

	public boolean esMasDeUnTercio() {
		return masDeUnTercio;
	}

	public void setMasDeUnTercio(boolean masDeUnTercio) {
		this.masDeUnTercio=masDeUnTercio;
	}

	public int getCantItemsMax() {
		return cantItemsMax;
	}
	
	public void setCantItemsMax(int cantPuntosMax) {
		this.cantItemsMax=cantPuntosMax;
	}
	
	public void setCantItemsComidos(int cantItemsComidos){
		this.cantItemsComidos=cantItemsComidos;
	}
	
	public int getCantItemsComidos(){
		return cantItemsComidos;
	}
	
	/**
	 * Deja Camino en la posicion indicada
	 * @param x cordenada en x
	 * @param y cordenada en y
	 */
	
	public void liberarCelda(int x, int y){	
		this.tableroJuego[x][y]= new Camino(x,y);
	}
	
	/**
	 * Verifica si la posicion indicada es valida para que un personaje pueda moverse
	 * @param x cordenada en x
	 * @param y cordenada en y
	 * @return retorna true si la posicion es valida para un personaje y false en caso contrario
	 */
		
	public boolean canMove(int x, int y) {		
		if((x < 0)||(y < 0)||(x >= columnas)||(y >=filas)||(this.getCelda(x, y) instanceof Muro))	//Fuera del tablero
			return false;
		else
			return true;
	}

	public void reset() {
		instancia=new Tablero();
	}
	
}
