package personajes;

import classpath.Position;
import juego.Tablero;

/**
 * Clase que representa a Personaje, de esta clase extienden los personajes del juego
 * @author Barron Juan Tomas - Tomasetto Santiago
 */

public abstract class Personaje{
	
	private int xInicial;
	private int yInicial;
	private Position p=new Position();

	
	
	/**
	 * Constructor de la clase Personaje, settea el personaje en la posicion deseada
	 * @param x settea la posicion inicial en x del Personaje
	 * @param y settea la posicion inicial en y del Personaje
	 */
	
	public Personaje(int x,int y){
		this.setX(x);
		this.setY(y);
		this.xInicial=x;
		this.yInicial=y;

	}
	
	public void setX(int x){
		p.setX(x);
	}
	
	public int getX(){
		return p.getX();
	}
	
	public void setY(int y){
		p.setY(y);
	}
	
	public int getY(){
		return p.getY();
	}
	
	public Position getPosition(){
		return p;
	}
	
	public void setPosition(Position p){
		this.p=p;
	}
	
	/**
	 *Utiliza el metodo "equals" de position para ver si coincide con la posicion del item pasado por parametro y retorna true de ser asi
	 *@param i item al cual se le quiere comparar la posicion con la posicion del item actual
	 *@return retorna un booleano indicando si la posicion del item actual es igual a la de "i"
	 */
	
	public boolean coincidePosicion(Personaje i){
		return this.p.equals(i.getPosition());
	}
	
	
	/**
	 * Ejecuta la accion pertinente a cada personaje (Sobre escrita en cada personaje)
	 * @param t Tablero del Juego
	 */
	
	public abstract void ejecutarAccion(Tablero t);
	
	/**
	 * resetea a cada personaje a un estado inicial por si se quiere volver a jugar
	 */
	
	public abstract void init();

	/**
	 * reinicia la posicion del personaje de acuerdo a la posicion inicial que le corresponde
	 */
	
	public void restart(){
		this.setX(xInicial);
		this.setY(yInicial);
	}


}
