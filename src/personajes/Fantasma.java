package personajes;

import juego.Tablero;
import herramientas.Direccion;
import herramientas.ModoFantasma;
import herramientas.RecorridoEsquina;
import classpath.*;

/**
*Clase que representa a los Fantasmas, de esta extienden todos los fantasmas del juego
* @author Barron Juan Tomas - Tomasetto Santiago
*/
public abstract class Fantasma extends Personaje {

	private ModoFantasma modo; //variable para el modo en el que se encuentra el fantasma
	private Path p; //path que va a usar en modo persecucion
	private RecorridoEsquina recorrido; //recorrido que va a usar para circular alrededor de una esquina
	private String nombre;
	private Direccion d;
	private int esquinaAsustado; //essquina random a la que va a ir cuando este asustado
	
	/**
	 *Constructor de la Clase, crea un fantasma en la posicion indicada por los parametros x e y, settea el modoFantasma en persecucion, settea la esquina a la que debe ir en modo asustado y settea el path en null
	 * 
	 */
	
	public Fantasma(int x,int y){
		super(x,y);
		this.modo=ModoFantasma.DISPERCION;
		this.p=null;
		this.esquinaAsustado=0;
		recorrido=null;
		this.d=Direccion.ARRIBA;
	}
	

	/**
	 * Avanza en el path setteado para el fantasma o de haber llegado a una esquina en modo Asustado o Dispercion sigue el recorrido de acuerdo a la esquina correspondiente
	 * @param t Tablero del juego
	 */
	@Override
	public void ejecutarAccion(Tablero t){
		if((p!=null)&&(p.getLength()>0)){ //avanza en el path segun su estrategia y actualiza direccion
			if(this.getX()==p.getX(1)){  //significa que se mueve en el eje y
				if(this.getY()>p.getY(1))
					this.d=Direccion.IZQUIERDA;
				else
					this.d=Direccion.DERECHA;				
			}
			else{
				if(this.getX()>p.getX(1))
					this.d=Direccion.ARRIBA;
				else
					this.d=Direccion.ABAJO;
			}
		}			
		else{
			if(this.tieneRecorrido()){
				d=recorrido.getSiguiente();
			}
		}
		switch(d){
		case ARRIBA:
			this.setX(this.getX()-1);
			break;
		case ABAJO:
			this.setX(this.getX()+1);
			break;
		case DERECHA:
			this.setY(this.getY()+1);
			break;
		case IZQUIERDA:
			this.setY(this.getY()-1);
			break;
		}
	}

	/**
	 * @return retorna el modoFantasma 
	 */
	
	public ModoFantasma getModo() {
		return modo;
	}
	
	/**
	 * 
	 * @return retorna la direccion de fantasma
	 */
	public Direccion getDireccion(){
		return d;
	}
	
	/**
	 *Settea el modo del Fantasma , este puede ser "Asustado", "Persecucion" o "Dispersion"
	 *@param modo Modo del fantasma
	 */

	public void setModo(ModoFantasma modo) {
		if(modo==ModoFantasma.ASUSTADO){
			this.esquinaAsustado=(int) ((Math.random()*4)+1);
		}
		else{
			this.esquinaAsustado=0; //estas instrucciones son para resetear las variables una vez que sale de modo asustado
			this.recorrido=null;
			}
		this.modo=modo;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	/**
	 *Imprime el recorrido que tiene cargado el fantasma
	 */
	
	public void imprimirRecorrido(){
		if((p==null)||(p.getLength()==0)){
			if(this.recorrido==null)
				System.out.println(this.nombre+" no tiene recorrido");
			else
				System.out.println("el fantasma esta girando alrededor de una esquina");
		}
		else{
			System.out.print("El recorrido de "+this.nombre+" sera: ");
			for(int i=0;i<p.getLength();i++){
				System.out.print("("+p.getStep(i).getX()+","+p.getStep(i).getY()+")");
					}
			System.out.println();
		}
	}
	
	public void setPath(Path p){
		this.p=p;
	}
	
	public Path getPath(){
		return p;
	}
	
	/**
	 *Crea el path que debe realizar el fantasma para ir a la esquina que se le indica con el parametro enviado
	 *@param esquina Numero de esquina a la que se quiere ir
	 */
	
	public Path irEsquina(int esquina){
		Path path=new Path();
		switch (esquina){
			case 1: 
				path =PathFinder.findPath(this.getX(), this.getY(), 1, 1); 
				break;
			case 2: 
				path =PathFinder.findPath(this.getX(), this.getY(), 1, 26); 
				break;
			case 3: 
				path =PathFinder.findPath(this.getX(), this.getY(), 29, 1); 
				break;
			case 4: 
				path =PathFinder.findPath(this.getX(), this.getY(), 29, 26);
				break;
		}
		return path;
	}
	
	
	
	/**
	 *Calcula el camino que debe realizar el fantasma, este metodo es abstracto porque cada fantasma implementa una estrategia distinta para generar el camino que le corresponde
	 *@param t Tablero del juego
	 *@param pac Pac-Man
	 */
	
	public abstract void calcularCamino(Tablero t,PacMan pac);
	
	/**
	 * Devuelve true si el recorrido es distinto de null
	 * @return devuelve un booleano que depende de si recorrido tiene o no tiene algo cargado
	 */
	
	public boolean tieneRecorrido(){
		return(recorrido!=null);
	}
	
	public void setRecorrido(RecorridoEsquina r){
		this.recorrido=r;
	}
	
	/**
	 * Retorna la esquina a la que ira el fantasma en caso de estar en modo Asustado
	 * @return Numero de la esquina
	 */
	
	public int getEsquinaAsustado() {
		return esquinaAsustado;
	}
	
	

	public void setEsquinaAsustado(int esquinaAsustado) {
		this.esquinaAsustado=esquinaAsustado;
	}
	


	@Override
	public void restart() {
		this.setModo(ModoFantasma.PERSECUCION);
		super.restart();
	}

	
	
}
