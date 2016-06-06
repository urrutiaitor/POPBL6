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

import conexiones.ConnRequestServer;
import conexiones.ConnServer;
import main.Proximidad;
import main.Temperatura;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	String usuario;
	ArrayList<Temperatura> temperaturas;
	ArrayList<Proximidad> proximidades;
	
	DefaultListModel<String> listModelProx;
	DefaultListModel<String> listModelTemp;
	
	ConnServer conn;
	
	
	public Window(ConnServer connS) {
		this.setContentPane(createFrame());
		this.setLocation(300, 200);
		this.setSize(640, 480);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		conn = connS;
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
		
		ArrayList<Proximidad> proxList = ConnServer.getProximidad();
		
		for (int i = 0; i < proxList.size(); i++) {
			listModelProx.addElement(proxList.get(i).toString());
		}
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		return scrollPane;
	}

	private Component panelTemperaturas() {
		listModelTemp = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModelTemp);
		
		ArrayList<Temperatura> tempList = ConnServer.getTemperatura();
		
		for (int i = 0; i < tempList.size(); i++) {
			listModelTemp.addElement(tempList.get(i).toString());
		}
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		return scrollPane;
	}

	
}
