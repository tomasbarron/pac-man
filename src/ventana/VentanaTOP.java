package ventana;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import juego.Puntaje;

public class VentanaTOP extends JFrame {
	
	private JPanel panel;
	private JLabel lab;
	private int topX=5;
	private JTable tabla;
	private String[] titulos={"Puesto","Nombre","Puntos","Tiempo"};
	private String scores[][];
	private Puntaje[] puntajes;
	
	public VentanaTOP(int topX){
		this.setVisible(true);
		this.setTitle("TOP Scores");
		if(topX!=0)
			this.setTopX(topX);
		init();
		this.setSize(650, (100+(this.topX*30)));
	}
	
	public void init(){
		panel=new JPanel();
		lab=new JLabel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		lab.setBounds(280, 0, 150, 50);
		lab.setFont(new java.awt.Font("DialogInput", 0, 20));
		lab.setForeground(Color.YELLOW);
		lab.setText("TOP "+this.topX);
		panel.add(lab);
		cargarPuntajes();
		scores=new String[topX][4];
		for(int i=0;i<topX;i++){ 
			scores[i][0]=String.valueOf(i+1);
			scores[i][1]=puntajes[i].getNombre();
			scores[i][2]=String.valueOf(puntajes[i].getPuntaje());
			scores[i][3]=String.valueOf(puntajes[i].getTiempo());
		}
		tabla=new JTable(scores, titulos);
		tabla.getColumnModel().getColumn(0).setMaxWidth(60);
		tabla.getColumnModel().getColumn(1).setMaxWidth(300);
		tabla.getColumnModel().getColumn(2).setMaxWidth(125);
		tabla.getColumnModel().getColumn(3).setMaxWidth(125);
		tabla.setBackground(Color.BLACK);
		tabla.setRowHeight(30);
		tabla.setFont(new java.awt.Font("DialogInput", 0, 20));
		tabla.setForeground(Color.YELLOW);
		JScrollPane js=new JScrollPane(tabla);
		js.setBounds(20, 50, 610, 22+(30*this.topX));
		panel.add(js);
		this.add(panel);
	}

	
	private void cargarPuntajes() {
		puntajes=new Puntaje[20];
		ObjectInputStream lectura=null;
			try{
				lectura = new ObjectInputStream(new FileInputStream("puntajes"));
				puntajes=(Puntaje[])lectura.readObject();
			}catch(IOException e){
				for(int i=0;i<20;i++){
					puntajes[i]=new Puntaje();
				}
				persistirPuntajes();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

	//setters y getters
	
	private void persistirPuntajes() {
		ObjectOutputStream salida=null;
		try{
			salida= new ObjectOutputStream(new FileOutputStream("puntajes"));
			salida.writeObject(puntajes);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void setTopX(int x){
		this.topX=x;
	}
	
	public int getTopX(){
		return this.topX;
	}
	
}
