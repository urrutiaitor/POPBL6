package interfazGrafica;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Tab1 tab1;
	Tab2 tab2;
	MyAction accUsuario, accPersonal, accTipoPersonal;
	MyAction accHabitacion, accHospedaje, accTrabaja;
	MyAction accSensor, accTipoSensor;

	public Window () {
		crearAcciones();
		this.setJMenuBar(createMenuBar());
		this.setContentPane(createFrame());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void crearAcciones(){
		accUsuario = new MyAction ("Anadir usuario", new ImageIcon(""), "Anadir un usuario del hotel", KeyEvent.VK_U);
		accPersonal = new MyAction ("Anadir personal", new ImageIcon(""), "Anadir un trabajador del hotel", KeyEvent.VK_P);
		accTipoPersonal = new MyAction ("Anadir tipo personal", new ImageIcon(""), "Anadir un tipo de trabajador del hotel", KeyEvent.VK_W);
		accHabitacion = new MyAction ("Anadir habitacion", new ImageIcon(""), "Anadir un habitacion en el hotel", KeyEvent.VK_H);
		accHospedaje = new MyAction ("Anadir hospedaje", new ImageIcon(""), "Anadir una reserva de hospedaje", KeyEvent.VK_R);
		accSensor = new MyAction ("Anadir sensor", new ImageIcon(""), "Anadir un sensor en una habitacion", KeyEvent.VK_S);
		accTipoSensor = new MyAction ("Anadir tipo sensor", new ImageIcon(""), "Anadir un tipo de sensor", KeyEvent.VK_C);
		accTrabaja = new MyAction ("Anadir trabaja", new ImageIcon(""), "Anadir una tarea para un trabajador", KeyEvent.VK_T);
	}

	private Container createFrame() {
		JPanel panel = new JPanel();
		
		return panel;
	}

	private JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();

		bar.add(createMenuFile());
		bar.add(createMenuAdd());
		bar.add(Box.createHorizontalGlue());
		bar.add(createMenuConfiguration());

		return bar;
	}

	private JMenu createMenuFile() {
		JMenu menuFile = new JMenu("File");
		
		return menuFile;
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

	private JMenu createMenuConfiguration() {
		JMenu menuConfiguration = new JMenu("Configuracion");
		
		return menuConfiguration;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void close () {
		this.dispose();
		System.out.println("Window closed");
	}
	
}
