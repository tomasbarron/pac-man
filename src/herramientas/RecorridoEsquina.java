package herramientas;

/**
 * Esta clase tiene cargado todas las posibles formas de recorrer una esquina
 * @author Barron Juan Tomas-Tomasetto Santiago
 *
 */

public class RecorridoEsquina {
	
	private Direccion[] esquina1={Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO,
								  Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,
								  Direccion.ARRIBA,Direccion.ARRIBA,Direccion.ARRIBA,Direccion.ARRIBA,
								  Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA};
	private Direccion[] esquina2={Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO,
								  Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,
								  Direccion.DERECHA,Direccion.ARRIBA,Direccion.ARRIBA,Direccion.ARRIBA,
								  Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA};
	private Direccion[] esquina3={Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,
								  Direccion.ARRIBA,Direccion.ARRIBA,Direccion.ARRIBA,
								  Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,
								  Direccion.ARRIBA,Direccion.ARRIBA,Direccion.ARRIBA,
								  Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,
								  Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO,
								  Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,
								  Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO};
	private Direccion[] esquina4={Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,Direccion.IZQUIERDA,
								  Direccion.ARRIBA,Direccion.ARRIBA,Direccion.ARRIBA,
								  Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,
								  Direccion.ARRIBA,Direccion.ARRIBA,Direccion.ARRIBA,
								  Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,
								  Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO,
								  Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,Direccion.DERECHA,
								  Direccion.ABAJO,Direccion.ABAJO,Direccion.ABAJO};

	private int contador;
	private int esquina;
	
	/**
	 * Constructor para la clase RecorridoEsquina
	 * @param esquina parametro que indica de que esquina se quiere el recorrido, solo se modifica en el constructor
	 */
	public RecorridoEsquina(int esquina){
		this.contador=0;
		this.esquina=esquina;
	}
	
	/**
	 * Metodo que obtiene la siguiente direccion del arreglo de la esquina que se quiere recorrer
	 * @return Retorna la siguiente direccion en el arreglo
	 */
	public Direccion getSiguiente(){
		Direccion d=Direccion.ARRIBA;
		switch(esquina){
		case 1:
			if(contador>esquina1.length) //reinicia el contador en caso de haer terminado
				contador=0;
			d=esquina1[contador];
			break;
		case 2:
			if(contador>esquina2.length)
				contador=0;
			d=esquina2[contador];
			break;
		case 3:
			if(contador>esquina3.length)
				contador=0;
			d=esquina3[contador];
			break;
		case 4:
			if(contador>esquina4.length)
				contador=0;
			d=esquina4[contador];
			break;
		}
		contador++;
		return d;
	}
}
