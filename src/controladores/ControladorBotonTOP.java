package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.VentanaConfig;
import ventana.VentanaTOP;

public class ControladorBotonTOP implements ActionListener {
	private VentanaTOP vt;
	
	@Override
	public void actionPerformed(ActionEvent e) {
			vt=new VentanaTOP(ControladorCheckBox.getInstance().getItem());	
	}

}