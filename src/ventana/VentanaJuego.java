package ventana;

import herramientas.ModoFantasma;
import items.*;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.jws.Oneway;
import javax.swing.*;

import controladores.ControladorPacMan;
import juego.Tablero;
import personajes.*;

public class VentanaJuego extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tablero t;
	private PacMan pac;
	private Blinky bli;
	private Clyde cly;
	private Inky ink;
	private Pinky pin;
	private JPanel panel1;
	private JPanel panel2;
	private int reloj=0;
	private ControladorPacMan controlPacMan;
	private URL[] imagenesPacMan=new URL[8];
	private URL[][] imagenesFantasmas=new URL[4][9];
	private URL[] imagenesTablero=new URL[5];

	public VentanaJuego(){
		setLayout(null);
		panel1=new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				dibujar(g);
			}
		};
		panel2=new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				dibujarPanelInferior(g);
			}
		};
		cargarImagenesPacman();
		cargarImagenesFantasmas();
		cargarImagenesTablero();
		t=Tablero.getInstance();
		pac=PacMan.getInstance();
		bli= Blinky.getInstance();
		pin= Pinky.getInstance();
		ink= Inky.getInstance();
		cly= Clyde.getInstance();
		setVisible(true);
		setTitle("Pac-Man");
		panel1.setSize(560, 620);
		add(panel1);
		panel1.setBounds(0, 0, 560, 620);
		panel2.setSize(560, 30);
		add(panel2);
		panel2.setBounds(0, 620, 560, 30);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(560, 650);
		controlPacMan=new ControladorPacMan();
		addKeyListener(controlPacMan);
	}
	
	
	private void cargarImagenesTablero() {
		imagenesTablero[0]=getClass().getClassLoader().getResource("imagenes/wall.gif");
		imagenesTablero[1]=getClass().getClassLoader().getResource("imagenes/puerta.gif");
		imagenesTablero[2]=getClass().getClassLoader().getResource("imagenes/black.gif");
		imagenesTablero[3]=getClass().getClassLoader().getResource("imagenes/powerpellet.gif");
		imagenesTablero[4]=getClass().getClassLoader().getResource("imagenes/pacdot.gif");
	}


	private void cargarImagenesFantasmas() {
		for(int i=0;i<8;i++){
			imagenesFantasmas[0][i]=getClass().getClassLoader().getResource("imagenes/red"+(i+1)+".gif");
		}
		imagenesFantasmas[0][8]=getClass().getClassLoader().getResource("imagenes/azul.gif");
		for(int i=0;i<8;i++){
			imagenesFantasmas[1][i]=getClass().getClassLoader().getResource("imagenes/pink"+(i+1)+".gif");
		}
		imagenesFantasmas[1][8]=getClass().getClassLoader().getResource("imagenes/azul.gif");
		for(int i=0;i<8;i++){
			imagenesFantasmas[2][i]=getClass().getClassLoader().getResource("imagenes/skyblue"+(i+1)+".gif");
		}
		imagenesFantasmas[2][8]=getClass().getClassLoader().getResource("imagenes/azul.gif");
		for(int i=0;i<8;i++){
			imagenesFantasmas[3][i]=getClass().getClassLoader().getResource("imagenes/orange"+(i+1)+".gif");
		}
		imagenesFantasmas[3][8]=getClass().getClassLoader().getResource("imagenes/azul.gif");
	}


	private void cargarImagenesPacman() {
		for(int i=0;i<8;i++)
			imagenesPacMan[i]=getClass().getClassLoader().getResource("imagenes/pac"+(i+1)+".gif");
	}


	private void dibujar(Graphics g){
		dibujarTablero(g);
		dibujarPacman(g);
		dibujarFantasma(bli,0,g);
		dibujarFantasma(pin,1,g);
		dibujarFantasma(ink,2,g);
		dibujarFantasma(cly,3,g);
	}

	private void dibujarPanelInferior(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 560, 30);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("DialogInput", Font.BOLD, 20));
		g.drawString("Puntos: "+pac.getScore(), 50, 23);
		g.drawString("Vidas: "+pac.getVidas(), 220, 23);
		g.drawString("Tiempo: "+reloj/60+":"+reloj%60,360,23);
	}

	private void dibujarFantasma(Fantasma f,int i, Graphics g) {
		BufferedImage img;
		URL imgURL=null;
		if(f.getModo()!=ModoFantasma.ASUSTADO){
			switch(f.getDireccion()){
			case ARRIBA:
				if((f.getX()+f.getY())%2==0)
					imgURL=imagenesFantasmas[i][6];
				else
					imgURL=imagenesFantasmas[i][7];
				break;
			case ABAJO:
				if((f.getX()+f.getY())%2==0)
					imgURL=imagenesFantasmas[i][0];
				else
					imgURL=imagenesFantasmas[i][1];
				break;
			case DERECHA:
				if((f.getX()+f.getY())%2==0)
					imgURL=imagenesFantasmas[i][2];
				else
					imgURL=imagenesFantasmas[i][3];
				break;
			case IZQUIERDA:
				if((f.getX()+f.getY())%2==0)
					imgURL=imagenesFantasmas[i][4];
				else
					imgURL=imagenesFantasmas[i][5];
				break;
			}
		}
		else
			imgURL=imagenesFantasmas[i][8];
		if(imgURL!=null){
			try{
				img=ImageIO.read(imgURL);
				g.drawImage(img, (f.getY()*20), (f.getX()*20), null);
			}
			catch(IOException e){
				System.err.println("no se pudo cargar la imagen");
			}
		}
		else
			System.err.println("no se encontro la imagen en la ruta espesificada");
	}

	private void dibujarPacman(Graphics g) {
		BufferedImage img;
		URL imgURL=null;
		switch(pac.getDireccion()){
		case ARRIBA:
			if((pac.getX()+pac.getY())%2==0)
				imgURL=imagenesPacMan[6];
			else
				imgURL=imagenesPacMan[7];
			break;
		case ABAJO:
			if((pac.getX()+pac.getY())%2==0)
				imgURL=imagenesPacMan[0];
			else
				imgURL=imagenesPacMan[1];
			break;
		case DERECHA:
			if((pac.getX()+pac.getY())%2==0)
				imgURL=imagenesPacMan[2];
			else
				imgURL=imagenesPacMan[3];
			break;
		case IZQUIERDA:
			if((pac.getX()+pac.getY())%2==0)
				imgURL=imagenesPacMan[4];
			else
				imgURL=imagenesPacMan[5];
			break;
		}
		if(imgURL!=null){
			try{
				img=ImageIO.read(imgURL);
				g.drawImage(img, (pac.getY()*20), (pac.getX()*20), null);
			}
			catch(IOException e){
				System.err.println("no se pudo cargar la imagen");
			}
		}
		else
			System.err.println("no se encontro la imagen en la ruta espesificada");
	}

	private void dibujarTablero(Graphics g) {
		BufferedImage img;
		URL imgURL;
		for(int i=0;i<31;i++){
			for(int j=0;j<28;j++){
				if(t.getCelda(i, j) instanceof Muro){
					imgURL=imagenesTablero[0];
				}
				else{
					if(t.getCelda(i, j) instanceof Camino){
						if((i==12)&&((j==13)||(j==14))){
							imgURL=imagenesTablero[1];
						}
						else
							imgURL=imagenesTablero[2];
					}
					else
						if(t.getCelda(i, j) instanceof BolaDePoder){
							imgURL=imagenesTablero[3];
						}
						else
							imgURL=imagenesTablero[4];
				}
				if(imgURL!=null){
					try{
						img=ImageIO.read(imgURL);
						g.drawImage(img, 20*j,20*i , null);
					}
					catch(IOException e){
						System.err.println("no pudo cargar la imagen");
					}
				}
				else
					System.err.println("no se encontro la imagen en la ruta espesificada");
			}
		}
	}

	public int getReloj(){
		return reloj;
	}
	
	public void setReloj(int reloj){
		this.reloj=reloj;
	}
	
	


}
