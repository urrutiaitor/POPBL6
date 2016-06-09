package interfazGrafica;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import main.Alojamiento;
import main.Proximidad;
import main.Temperatura;
import mySQL.JDBC;

public class Window extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MyAction accUsuario, accPersonal, accTipoPersonal;
	MyAction accHabitacion, accHospedaje, accTrabaja;
	MyAction accSensor, accTipoSensor;
	
	String serverIp;

	public Window (String ip) {
		this.serverIp = ip;
		crearAcciones();
		this.setJMenuBar(createMenuBar());
		this.setContentPane(createFrame());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void crearAcciones(){
		accUsuario = new MyAction ("Anadir usuario", new ImageIcon(""), "Anadir un usuario del hotel", KeyEvent.VK_U, serverIp);
		accPersonal = new MyAction ("Anadir personal", new ImageIcon(""), "Anadir un trabajador del hotel", KeyEvent.VK_P, serverIp);
		accTipoPersonal = new MyAction ("Anadir tipo personal", new ImageIcon(""), "Anadir un tipo de trabajador del hotel", KeyEvent.VK_W, serverIp);
		accHabitacion = new MyAction ("Anadir habitacion", new ImageIcon(""), "Anadir un habitacion en el hotel", KeyEvent.VK_H, serverIp);
		accHospedaje = new MyAction ("Anadir hospedaje", new ImageIcon(""), "Anadir una reserva de hospedaje", KeyEvent.VK_R, serverIp);
		accSensor = new MyAction ("Anadir sensor", new ImageIcon(""), "Anadir un sensor en una habitacion", KeyEvent.VK_S, serverIp);
		accTipoSensor = new MyAction ("Anadir tipo sensor", new ImageIcon(""), "Anadir un tipo de sensor", KeyEvent.VK_C, serverIp);
		accTrabaja = new MyAction ("Anadir trabaja", new ImageIcon(""), "Anadir una tarea para un trabajador", KeyEvent.VK_T, serverIp);
	}

	private Container createFrame() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		
		panel.add(sensorPanel());
		panel.add(alocationPanel());
		
		return panel;
	}

	private Component sensorPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(sensorProxPanel());
		panel.add(sensorTempPanel());
		return panel;
	}

	private Component sensorProxPanel() {
		String[] listaStrings = getProxHistorial();
		JList<String> lista = new JList<>(listaStrings);
		JScrollPane sp = new JScrollPane(lista);
		
		return sp;
	}

	private Component sensorTempPanel() {
		String[] listaStrings = getTempHistorial();
		JList<String> lista = new JList<>(listaStrings);
		JScrollPane sp = new JScrollPane(lista);
		
		return sp;
	}
	
	private String[] getProxHistorial() {
		JDBC jdbc = new JDBC(serverIp);
		ArrayList<Proximidad> proximidad = jdbc.getProximidad();
		String[] list = new String[proximidad.size()];
		
		for (int i = 0; i < proximidad.size(); i++) {
			list[i] = proximidad.get(i).toString();
		}
		
		return list;
	}

	private String[] getTempHistorial() {
		JDBC jdbc = new JDBC(serverIp);
		
		ArrayList<Temperatura> temperaturas = jdbc.getTemperatura();
		String[] list = new String[temperaturas.size()];
		
		for (int i = 0; i < temperaturas.size(); i++) {
			list[i] = temperaturas.get(i).toString();
			
		}
		
		return list;
	}

	private Component alocationPanel() {
		String[] listaStrings = getAlojHistorial();
		JList<String> lista = new JList<>(listaStrings);
		JScrollPane sp = new JScrollPane(lista);
		
		return sp;
	}

	private String[] getAlojHistorial() {
		JDBC jdbc = new JDBC(serverIp);
		
		ArrayList<Alojamiento> alojamientos = jdbc.getAlojamiento();
		String[] list = new String[alojamientos.size()];
		
		for (int i = 0; i < alojamientos.size(); i++) {
			list[i] = alojamientos.get(i).toString();
		}
		
		return list;
	}

	private JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();

		bar.add(createMenuAdd());;

		return bar;
	}

	private JMenu createMenuAdd() {
		JMenu menuAdd = new JMenu("Anadir");
		menuAdd.add(accUsuario);
		menuAdd.add(accPersonal);
		menuAdd.add(accTipoPersonal);
		menuAdd.add(accHabitacion);
		menuAdd.add(accHospedaje);
		menuAdd.add(accSensor);
		menuAdd.add(accTipoSensor);
		menuAdd.add(accTrabaja);
		return menuAdd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		
	}
	
	public void close () {
		this.dispose();
		System.out.println("Window closed");
	}
	
}
