package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ventana.VentanaInstrucciones;

public class ControladorBotonInstrucciones implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		VentanaInstrucciones vi=new VentanaInstrucciones();	
	}

}
