package personajes;

import herramientas.RecorridoEsquina;
import classpath.PathFinder;
import classpath.Position;
import juego.Tablero;

/**
*Clase que representa al fantasma Inky
*@author Barron Juan Tomas - Tomasetto Santiago
*/

public class Inky extends Fantasma {

	private static Inky instancia=new Inky();
	/**
	 *Contructor del fantasma Inky, settea la posicion inicial del mismo y su nombre
	 */
	
	private Inky() {
		super(14,11);
		this.setNombre("Inky");
	}

	/**
	 * Avanza de acuerdo al camino que tenga cargado (este fantasma comienza a moverse cuando Pac-Man comio mas de 30 Pac-Puntos)
	 * @param t Tablero del juego 
	 */
	
	public static Inky getInstance(){
		return instancia;
	}
	
	@Override
	public void ejecutarAccion(Tablero t){
		if(t.esMasDe30Puntos()){ //si se cumple la condicion, ejecuta su accion.
			super.ejecutarAccion(t);
		}
	}
	
	/**
	 *Calcula el camino que debe realizar de acuerdo a la estrategia pertinente para este fantasma y al modo fantasma que este settea
	 *@param t Tablero del juego
	 *@param pac Pac-Man
	 *@param bli Blinky
	 */
	
	public void calcularCamino(Tablero t, PacMan pac,Blinky bli) {
		if(t.esMasDe30Puntos()){
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
				int diferencialX=0,diferencialY=0;
				double pendiente;
				Position pos=Position.predecirPosicion(pac.getX(), pac.getY(), 2, pac.getDireccion());
				if((pos.getX()!=0)||(pos.getY()!=0)){
					diferencialX=(pos.getX()-bli.getX())*2;
					diferencialY=(pos.getY()-bli.getY())*2;
				}
				if(diferencialX!=0){
					pendiente=diferencialY/diferencialX;
					while(!(t.canMove(bli.getX()+diferencialX, bli.getY()+diferencialY))&&(diferencialX!=0)){ //achica de a uno en x hasta que encuentra una posicion valida, si no la encuentra su cacillero objetivo es el de Pac-Man
						if(diferencialX>0)
							diferencialX--;	
						if(diferencialX<0)
							diferencialX++;
						if(diferencialX!=0)
							diferencialY=(int)Math.round(diferencialX*pendiente);
					}
					if(diferencialX!=0){
						this.setPath(PathFinder.findPath(this.getX(), this.getY(), bli.getX()+diferencialX, bli.getY()+diferencialY));
					}
				}
				if(this.getPath()==null){
					this.setPath(PathFinder.findPath(this.getX(),this.getY(),pac.getX(),pac.getY()));
				}
				break;
			case DISPERCION:
				if(!this.tieneRecorrido()){
					this.setPath(this.irEsquina(4));
					if((this.getPath().getLength()<1)||(this.getPath()==null))
						this.setRecorrido(new RecorridoEsquina(4));
				}
				break;
			}
		}
	}

	/**
	 *Sobreescribe el comportamiento del metodo calcularCamino de la clase padre Fantasma por ser abstracto (implementa la misma estrategia que Blinky)
	 */
	
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
			if(!this.tieneRecorrido()){
				this.setPath(this.irEsquina(4));
				if((this.getPath().getLength()<1)||(this.getPath()==null))
					this.setRecorrido(new RecorridoEsquina(4));
			}
			break;
		}
	}


	@Override
	public void init() {
		instancia=new Inky();
	}

}
