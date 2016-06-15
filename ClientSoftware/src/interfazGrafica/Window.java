package interfazGrafica;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import conexiones.ConnServer;
import main.Proximidad;
import main.Temperatura;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<Temperatura> temperaturas;
	ArrayList<Proximidad> proximidades;
	
	DefaultListModel<String> listModelProx;
	DefaultListModel<String> listModelTemp;
	
	ConnServer conn;
	String usuario;
	String contrasena;
	
	
	public Window(ConnServer connS, String usuario, String contrasena) {
		this.conn = connS;
		this.usuario = usuario;
		this.contrasena = contrasena;
		
		this.setContentPane(createFrame());
		this.setLocation(200, 100);
		this.setSize(640*2, 480*2);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private Container createFrame() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		
		panel.add(panelProximidades());
		panel.add(panelTemperaturas());
		
		return panel;
	}

	private Component panelProximidades() {
		listModelProx = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModelProx);
		
		ArrayList<Proximidad> proxList = conn.getProximidad(usuario, contrasena);
		
		for (int i = 0; i < proxList.size(); i++) {
			listModelProx.addElement(proxList.get(i).toString());
		}
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		return scrollPane;
	}

	private Component panelTemperaturas() {
		listModelTemp = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModelTemp);
		
		ArrayList<Temperatura> tempList = conn.getTemperatura(usuario, contrasena);
		
		for (int i = 0; i < tempList.size(); i++) {
			listModelTemp.addElement(tempList.get(i).toString());
		}
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		return scrollPane;
	}

	
}
