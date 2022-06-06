package classpath;

public class AStarHeuristic  {

	public float getCost(Map map,  int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		return result;
	}

}
