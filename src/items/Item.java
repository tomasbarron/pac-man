package items;

import classpath.Position;

/**
*Clase que representa a los items del juego
*@author Barron Juan Tomas - Tomasetto Santiago
*/

public class Item {
	
	private Position p=new Position();
	
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
	
}
