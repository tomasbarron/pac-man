package ventana;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class VentanaInstrucciones extends JFrame {
	
	private String imgFileName ="imagenes/Instrucciones.jpg";
	private Image imagen;
	private JPanel panel;
	
	
	public VentanaInstrucciones(){
		URL imgUrl= getClass().getClassLoader().getResource(imgFileName);
		if(imgUrl==null){
			System.err.println("no se encuentra el archivo :"+imgFileName);
		}else{
			try {
				imagen=ImageIO.read(imgUrl);
			}catch(IOException ex){
				System.err.println("no se pudo cargar la imagen");

			}
		}
		panel=new JPanel();
		this.setTitle("Instrucciones del juego");
		this.setVisible(true);
		this.add(panel);
		this.setSize(474, 471);

	}
	
	
	public void dibujar(){
		panel.getGraphics().drawImage(imagen, 0, 0, null);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		dibujar();
	}
	

}
