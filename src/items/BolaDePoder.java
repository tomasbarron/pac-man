package items;

/**
 * Clase que representa a una Bola De Poder
 * @author  Barron Juan Tomas - Tomasetto Santiago
 */

public class BolaDePoder extends Item{
	
	private static final int PUNTAJE=50;
	
	/**
	 * Crea una Bola de poder en la posicion dada por los parametros
	 * @param x Posicion en el eje x de la bola de poder
	 * @param y Posicion en el eje y de la bola de poder
	 */
	
	public BolaDePoder(int x,int y) {
		this.setX(x);
		this.setY(y);
	}
	
	
	
	public static int getPuntaje(){
		return PUNTAJE;
	}

}
