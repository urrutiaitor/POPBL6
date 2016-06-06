package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mySQL.JDBC;
import mySQL.Personal;
import mySQL.Sensor;

public class SensorDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JTextField diaT;
	JTextField mesT;
	JTextField anoT;
	JComboBox<String> habitacionB;
	JComboBox<String> tipoSensorB;
	
	public SensorDialog () {
		this.setLocation(100, 100);
		this.setSize(800, 300);
		this.setContentPane(createPane());
		this.setVisible(true);
		this.setModal(false);
		
	}
	
	private Container createPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createTextField(), BorderLayout.CENTER);
		JButton boton;
		panel.add(boton = new JButton("Anadir"), BorderLayout.SOUTH);
		boton.addActionListener(this);
		return panel;
	}

	private Container createTextField () {
		JPanel panel = new JPanel(new GridLayout(3, 1));
		
		JDBC jdbc = new JDBC();
		
		ArrayList<String> nombresTipoSensor = jdbc.getNombre("tipoSensor");
		String[] sensorS = new String[nombresTipoSensor.size()];
		for (int i = 0 ; i < nombresTipoSensor.size(); i++) {
			sensorS[i] = nombresTipoSensor.get(i);
		}
		tipoSensorB = new JComboBox<String>(sensorS);
		tipoSensorB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Tipo sensor")));
		panel.add(tipoSensorB);
		
		ArrayList<String> nombresHabitacion = jdbc.getNombre("habitacion");
		String[] habitacionS = new String[nombresHabitacion.size()];
		for (int i = 0 ; i < nombresHabitacion.size(); i++) {
			habitacionS[i] = nombresHabitacion.get(i);
		}
		habitacionB = new JComboBox<String>(habitacionS);
		habitacionB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Habitacion")));
		panel.add(habitacionB);
		
		JPanel fechaP = new JPanel(new GridLayout(1, 3));
		fechaP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Fecha de instalacion (AAAA MM DD)")));
		anoT = new JTextField();
		fechaP.add(anoT);
		mesT = new JTextField();
		fechaP.add(mesT);
		diaT = new JTextField();
		fechaP.add(diaT);
		panel.add(fechaP);
	
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Anadir") {
			JDBC jdbc = new JDBC();
			int idHabitacion = jdbc.getIdTabla("habitacion", (String) habitacionB.getSelectedItem());
			int idTipoSensor = jdbc.getIdTabla("tipoSensor", (String) tipoSensorB.getSelectedItem());
			@SuppressWarnings("deprecation")
			Sensor s = new Sensor(new Date(Integer.parseInt(anoT.getText()) - 1900, Integer.parseInt(mesT.getText()),
							Integer.parseInt(diaT.getText())),
					idHabitacion, idTipoSensor);
			int id = s.submit();
			JOptionPane.showMessageDialog(this, "Se ha asignado el id: " + id);
		}
	}
}
