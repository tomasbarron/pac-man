package personajes;

import herramientas.RecorridoEsquina;
import juego.Tablero;
import classpath.PathFinder;

/**
 * Clase que representa al fantasma Blinky
 * @author  Barron Juan Tomas - Tomasetto Santiago
 */

public class Blinky extends Fantasma {
	
	private static Blinky instancia=new Blinky();
	/**
	 * Constructor de la clase settea la posicion inicial de Blinky y su nombre
	 */
	
	private Blinky() {
		super(11,14);
		this.setNombre("Blinky");
	}

	/**
	 * Calcula el camino que debe hacer Blinky segun el modo setteado
	 * @param t Tablero del juego
	 * @param pac Pac-Man
	 */
	
	public static Blinky getInstance(){
		return instancia;
	}
	
	@Override
	public void calcularCamino(Tablero t, PacMan pac) {
		switch(this.getModo()){
		case ASUSTADO:
			if(!this.tieneRecorrido()){
				this.setPath(this.irEsquina(this.getEsquinaAsustado()));
				if((this.getPath()==null)||(this.getPath().getLength()<1)){
					this.setRecorrido(new RecorridoEsquina(this.getEsquinaAsustado()));
				}
			}
			break;
		case PERSECUCION:
			this.setPath(PathFinder.findPath(this.getX(),this.getY(),pac.getX(),pac.getY()));
			break;
		case DISPERCION:
			if(!this.tieneRecorrido()){ //verifica que no se este ejecutando el recorrido de rotar por la esquina
				this.setPath(this.irEsquina( 2));
				if((this.getPath().getLength()<1)||(this.getPath()==null)) //verifica si ya llego a la esquina, si llego carga el recorrido para girar en torno a esa esquina.
					this.setRecorrido(new RecorridoEsquina(2));
			}
			break;
		}
	}

	@Override
	public void init() {
		instancia=new Blinky();
	}


}
