package personajes;

import herramientas.RecorridoEsquina;
import classpath.PathFinder;
import classpath.Position;
import juego.Tablero;
/**
 * Clase que representa a Pinky
 * @author Barron Juan Tomas - Tomasetto Santiago
 */
public class Pinky extends Fantasma {
	private static Pinky instancia=new Pinky();
	/**
	 * Constructor de la clase Pinky, settea la posicion inicial del mismo y su nombre
	 */
	private Pinky() {
		super(14, 13);
		this.setNombre("Pinky");
	}

	/**
	 * Calcula el camino que debe realizar el Pinky de acuerdo a la estrategia que debe desarrollar
	 * @param t Tablero del juego
	 * @param pac Pac-Man
	 */
	public static Pinky getInstance(){
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
			Position pos= Position.predecirPosicion(pac.getX(), pac.getY(), 4, pac.getDireccion());
			if(t.canMove(pos.getX(), pos.getY())){
				this.setPath(PathFinder.findPath(this.getX(), this.getY(), pos.getX(), pos.getY()));
			}
			else{
				this.setPath(PathFinder.findPath(this.getX(), this.getY(), pac.getX(), pac.getY()));
			}
			break;
		case DISPERCION:
			if(!this.tieneRecorrido()){
				this.setPath(this.irEsquina(1));
				if((this.getPath().getLength()<1)||(this.getPath()==null))
					this.setRecorrido(new RecorridoEsquina(1));
			}
			break;
		}
	}

	@Override
	public void init() {
		instancia=new Pinky();
	}


}
