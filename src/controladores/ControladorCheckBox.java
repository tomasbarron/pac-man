package controladores;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ventana.VentanaConfig;

public class ControladorCheckBox implements ItemListener{
	private int item;
	private static ControladorCheckBox instancia=new ControladorCheckBox();
	
	private ControladorCheckBox(){
		item=0;
	}
	
	public static ControladorCheckBox getInstance(){
		return instancia;
	}
	
	@Override
	public void itemStateChanged(ItemEvent it) {
		item= (int) it.getItem();
		
	}
	
	public int getItem(){
		return item;
	}

}
