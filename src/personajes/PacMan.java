package personajes;

import juego.*;
import herramientas.ModoPacMan;
import items.BolaDePoder;
import items.PacPunto;
import herramientas.Direccion;
import herramientas.ModoFantasma;

/**
 * Clase que representa a Pac-Man
 *@author Barron Juan Tomas - Tomasetto Santiago
 */

public class PacMan extends Personaje {
	
	private int score;  //puntaje que va obteniendo Pac-Man
	private Direccion direccion; //direccion en la que se mueve Pac-Man
	private ModoPacMan modo; //modo en el que se encuentra Pac-Man
	private int vidas; 
	private int cantTurnos; //contador de turnos, utilizado para devolver a Pac-Man a su estado normal
	private int cantFantasmasComidos;
	private static PacMan instancia=new PacMan();

	/**
	 * Constructor de la clase Pac-Man, settea la posicion inicial de Pac-Man, cantidad de vidas, cantidad de turnos, cantidad de fantasmas comidas, direccion y puntaje
	 */
	
	private PacMan() {
		super(23,14);
		this.vidas=3;
		this.modo=ModoPacMan.NORMAL;
		this.cantTurnos=21;
		this.cantFantasmasComidos=0;
		this.direccion=Direccion.IZQUIERDA;
		this.score=0;
	}
	
	public static PacMan getInstance(){
		return instancia;
	}
	/**
	 * Se mueve en el tablero de acuerdo a la direccion que se le indica por el teclado
	 * @param t Tablero del juego
	 */
	
	@Override
	public void ejecutarAccion(Tablero t){
		switch(direccion){
		case ARRIBA:
			if(t.canMove(this.getX()-1, this.getY())){ 
				this.mover(this.getX()-1,this.getY());
			}
			break;
		case ABAJO:
			if(!((this.getX()==11)&&((this.getY()==13)||(this.getY()==14)))){
				if(t.canMove(this.getX()+1, this.getY())){
					this.mover(this.getX()+1,this.getY());
				}
			}
			break;
		case IZQUIERDA:
			if((this.getX()==14)&&(this.getY()==0)){ //CONDICION PARA EL TUNEL 
				this.mover(14,27);
			}
			else{
				if(t.canMove(this.getX(), this.getY()-1)){
					this.mover(this.getX(),this.getY()-1);
				}
			}
			break;
		case DERECHA:
			if((this.getX()==14)&&(this.getY()==27)){ //CONDICION PARA EL TUNEL
				this.mover(14,0);
			}
			else{
				if(t.canMove(this.getX(), this.getY()+1)){

					this.mover(this.getX(),this.getY()+1);
				}
			}
			break;
		default:
			break;
		}
		this.cantTurnos++;
		if((this.cantTurnos>20)&&(this.modo==ModoPacMan.SUPERPACMAN)){
			this.modo=ModoPacMan.NORMAL;
		}
	}
	
	/**
	 * Mueve a Pac-Man a la posicion indicada por los parametros
	 * @param x posicion en x a la que se desea mover a Pac-Man
	 * @param y posicion en y a la que se desea mover a Pac-Man
	 */
	
	private void mover(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Aumenta la cantidad de fantasmas comidas e incrementa el puntaje de acuerdo a la cantidad de fantasmas que hayan sido comidos
	 */
	
	public void comerFantasma(){
		this.cantFantasmasComidos++;
		this.score=this.score+(200*(int)Math.pow(2, this.cantFantasmasComidos));
	}
	
	public Direccion getDireccion(){
		return this.direccion;
	}
	
	public int getVidas(){
		return this.vidas;
	}
	
	public void setVidas(int v){
		this.vidas=v;
	}

	public void setDireccion(Direccion d){
		this.direccion=d;
	}

	public ModoPacMan getModo() {
		return modo;
	}
	

	public void setModo(ModoPacMan modo) {
		this.modo = modo;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Resta en 1 la cantidad de vidas de pacman y reincia su posicion
	 */
	public void morir(){
		this.vidas--;
		this.setDireccion(Direccion.IZQUIERDA);
		this.restart();
	}

	/**
	 * Incrementa el puntaje de acuerdo a lo que vale comer un Pac-Punto
	 * @param t Tablero del juego
	 */
	
	public void comerPacPunto(Tablero t) {
		this.score=this.score+PacPunto.getPuntaje();
	}
	
	/**
	 * Incrementa el puntaje de acuerdo a lo que vale comer una Bola de poder, settea a Pac-Man en modo SUPERPACMAN, settea la cantidad de turnos del modo SUPERPACMAN y la cantidad de fantasmas comidos en 0
	 * @param t Tablero del juego
	 */
	
	public void comerBolaDePoder(Tablero t) {
		this.score=this.score+BolaDePoder.getPuntaje();
		this.setModo(ModoPacMan.SUPERPACMAN);
		this.cantTurnos=0;
		this.cantFantasmasComidos=0;
	}
	/**
	 * Metodo que se encarga de comer, en case de haberlo, los Pac-Puntos o las Bolas de poder y dejar la celda vacia.
	 * Tambien se encarga de setear el modo de los fantasmas en caso de comer una Bola de poder.
	 */
	
	public void comerItem(Tablero t){
		if(t.hayPacPunto(getX(),getY())){
			comerPacPunto(t);
			t.liberarCelda(getX(), getY());
			t.setCantItemsComidos(t.getCantItemsComidos()+1);
		}
		if(t.hayBolaDePoder(getX(),getY())){
			comerBolaDePoder(t);
			t.liberarCelda(getX(),getY());
			Blinky.getInstance().setModo(ModoFantasma.ASUSTADO);
			Inky.getInstance().setModo(ModoFantasma.ASUSTADO);
			Pinky.getInstance().setModo(ModoFantasma.ASUSTADO);
			Clyde.getInstance().setModo(ModoFantasma.ASUSTADO);
			t.setCantItemsComidos(t.getCantItemsComidos()+1);
		}
		t.setMasDe30Puntos(t.getCantItemsComidos()>=30); //verfica si Pac-Man comio mas de 30 Pac-Puntos
		t.setMasDeUnTercio(t.getCantItemsComidos()>=(int)(0.3*t.getCantItemsMax())); //verifica si Pac-Man comio mas de un tercio del puntaje maximo del tablero
	}
	


	@Override
	public void init() {
		instancia=new PacMan();
	}


}
