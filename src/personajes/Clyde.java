package personajes;

import herramientas.RecorridoEsquina;
import classpath.Path;
import classpath.PathFinder;
import juego.Tablero;

/**
 * Clase que representa al fantasma Clyde
 * @author  Barron Juan Tomas - Tomasetto Santiago
 */


public class Clyde extends Fantasma {
	
	private static Clyde instancia=new Clyde();
	/**
	 * Constructor de la clase, settea la posicion inicial de Clyde y settea el nombre
	 * @author  Barron Juan Tomas - Tomasetto Santiago
	 */
	
	private Clyde() {
		super(14, 16);
		this.setNombre("Clyde");
	}
	
	public static Clyde getInstance(){
		return instancia;
	}

	/**
	 *Sobreescribe el metodo ejecutar de la clase Fantasma, se encarga de mover a Clyde en el tablero de acuerdo al path que tenga cargado (empieza a moverse cuando ya se consumierdon mas de un tercio de los Pac-Puntos)
	 * @param t Tablero del juego
	 */
	
	@Override
	public void ejecutarAccion(Tablero t){
		if(t.esMasDeUnTercio()){ //si se cumple la condicion, ejecuta su accion.
			super.ejecutarAccion(t);
		}
	}
	/**
	 *Calcula el camino que debe realizar Clyde de acuerdo a el modo en el que esta setteado
	 * @param t Tablero del juego
	 * @param pac Pac-Man
	 */

	@Override
	public void calcularCamino(Tablero t, PacMan pac) {
		if(t.esMasDeUnTercio()){
			switch(this.getModo()){
			case ASUSTADO:
				if(!this.tieneRecorrido()){ //verifica que no se este ejecutando el recorrido alrededor de la esquina seleccionada
					this.setPath(this.irEsquina(this.getEsquinaAsustado()));
					if((this.getPath()==null)||(this.getPath().getLength()<1)){ //verifica que no haya llegado a la esquina deseada
						this.setRecorrido(new RecorridoEsquina(this.getEsquinaAsustado()));
					}
				}
				break;
			case PERSECUCION:
				Path path=PathFinder.findPath(this.getX(), this.getY(), pac.getX(), pac.getY());
				if((path!=null)&&(path.getLength()>8)){
					this.setPath(path);
				}
				else
					this.setPath(this.irEsquina(3));
				break;
			case DISPERCION:
				if((!this.tieneRecorrido())&&(t.esMasDeUnTercio())){
					this.setPath(this.irEsquina(3));
					if((this.getPath().getLength()<1)||(this.getPath()==null))
						this.setRecorrido(new RecorridoEsquina(3));
				}
				break;
			}
		}
	}

	@Override
	public void init() {
		instancia=new Clyde();
	}


}
