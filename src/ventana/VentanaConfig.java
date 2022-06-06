package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;

import controladores.ControladorCheckBox;


public class VentanaConfig extends JFrame{
	
	private JPanel panel;
	private JLabel label;
	private JComboBox<Integer> combo;
	
	public VentanaConfig(){
		setLayout(new BorderLayout());
		panel=new JPanel();
		panel.setLayout(null);
		combo=new JComboBox<Integer>();
		combo.setBounds(225, 62, 100, 25);
		panel.setBackground(Color.BLACK);
		panel.add(combo);
		combo.addItem(5);
		combo.addItem(10);
		combo.addItem(15);
		combo.addItem(20);
		combo.addItemListener(ControladorCheckBox.getInstance());
		label=new JLabel("Mostrar TOP's:");
		label.setFont(new Font("DialogInput", Font.BOLD, 20));
		label.setForeground(Color.YELLOW);
		label.setBackground(Color.BLACK);
		label.setBounds(30, 62, 200, 30);
		panel.add(label);
		add(panel);
		setVisible(true);
		setTitle("Configuracion");
		setResizable(false);
		setSize(400, 180);
	}
	
	public int getItemActual(){
		return (Integer) combo.getSelectedItem();
	}
	
	
	
	

}
