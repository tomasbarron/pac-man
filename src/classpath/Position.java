package classpath;

import herramientas.Direccion;

public class Position {
	private int positionX;
	private int positionY;
	
	
	public int getX(){
		return positionX;
	}
	
	public void setX(int x){
		this.positionX=x;
	}
	
	public int getY() {
		return positionY;
	}
	
	public void setY(int y){
		this.positionY=y;
	}

	public boolean equals(Object o){
		if(o instanceof Position){
			Position p=(Position) o;
			return((this.getX()==p.getX())&&(this.getY()==p.getY()));
		}
		return false;
	}

	public static Position predecirPosicion(int x,int y,int pasosAdelante,Direccion d){
		Position pos=new Position();
		switch(d){
		case ARRIBA:
			pos.setX(x-pasosAdelante);
			pos.setY(y);
			break;
		case ABAJO:
			pos.setX(x+pasosAdelante);
			pos.setY(y);
			break;
		case DERECHA:
			pos.setX(x);
			pos.setY(y+pasosAdelante);
			break;
		case IZQUIERDA:
			pos.setX(x);
			pos.setY(y-pasosAdelante);
			break;
		}
		return pos;
	}
}
