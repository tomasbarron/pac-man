package items;

/**
 * Clase que representa a un Pac-Punto
 * @author Barron Juan Tomas - Tomasetto Santiago
 */

public class PacPunto extends Item {

	private static final int PUNTOS=10;
	
	/**
	 * Constructor de la clase Pac-Punto, settea la posicion del Pac-Punto con los parametros pasados
	 * @param x setea la posicion en x del Pac-Punto
	 * @param y setea la posicion en y del Pac-Punto
	 */
	
	public PacPunto(int x,int y) {
		this.setX(x);
		this.setY(y);
	}

	public static int getPuntaje() {
		return PUNTOS;
	}

}
