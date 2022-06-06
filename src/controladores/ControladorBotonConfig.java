package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ventana.VentanaConfig;

public class ControladorBotonConfig implements ActionListener {
	private int item;
	

	public int getItem(){
		return this.item;
	}
	
	public void setItem(int item){
		this.item=item;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			VentanaConfig vc=new VentanaConfig();
	}

}