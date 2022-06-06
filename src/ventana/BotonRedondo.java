package ventana;
	
	import java.awt.*; 
	import javax.swing.JButton; 
	import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.*; 

	public class BotonRedondo extends JButton{ 
	/**
		 * 
		 */

	private static final long serialVersionUID = 1L;
	private Color colorFondo, colorPresionado; 
	private Shape figura;
	//Constructor forma de elipse 
	
	public BotonRedondo(String rotulo, Color fon, Color pre) { 
	        super(rotulo); 
	        colorFondo = fon; 
	        colorPresionado = pre; 
	        setContentAreaFilled(false); 
	    }

	 @ Override 
	    protected void paintComponent(Graphics g) { 
	        if (getModel().isArmed()) { 
	            g.setColor(colorPresionado); 
	        } else { 
	            g.setColor(colorFondo);   
	        } 
	            g.fillOval(0, 0, getSize().width , getSize().height ); 
	        super.paintComponent(g); 
	    } 
	//Sobreescritura del borde 
	@ Override 
	    protected void paintBorder(Graphics g) { 
	        g.setColor(Color.black); 
	            g.drawOval(0, 0, getSize().width , getSize().height ); 
	    } 
	 

	    @ Override 
	    public boolean contains(int x, int y) { 
	            figura = new Ellipse2D.Float(0, 0, getWidth(), getHeight()); 
	        return (figura.contains(x, y)); 
	    } 
	} 


