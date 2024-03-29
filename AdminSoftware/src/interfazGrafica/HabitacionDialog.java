package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mySQL.Habitacion;
import mySQL.JDBC;

public class HabitacionDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JTextField nombreT;
	JTextField descripcionT;
	JTextField tamanoT;
	JTextField pisoT;
	JComboBox<String> hotelB;
	
	public HabitacionDialog () {
		this.setLocation(100, 100);
		this.setSize(800, 400);
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
		JPanel panel = new JPanel(new GridLayout(5, 1));
		
		JDBC jdbc = new JDBC();
		ArrayList<String> nombres = jdbc.getNombre("hotel");
		String[] hotelS = new String[nombres.size()];
		for (int i = 0 ; i < nombres.size(); i++) {
			hotelS[i] = nombres.get(i);
		}
		hotelB = new JComboBox<>(hotelS);
		hotelB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Hotel")));
		panel.add(hotelB);
		
		nombreT = new JTextField();
		nombreT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Nombre")));
		panel.add(nombreT);
		
		descripcionT = new JTextField();
		descripcionT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Descripcion")));
		panel.add(descripcionT);
		
		tamanoT = new JTextField();
		tamanoT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Tamano")));
		panel.add(tamanoT);
		
		pisoT = new JTextField();
		pisoT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Piso")));
		panel.add(pisoT);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Anadir") {
			JDBC jdbc = new JDBC();
			int id = jdbc.getIdTabla("hotel", (String) hotelB.getSelectedItem());
			Habitacion h = new Habitacion(nombreT.getText(), descripcionT.getText(),
					Integer.parseInt(tamanoT.getText()), Integer.parseInt(pisoT.getText()), id);
			id = h.submit();
			JOptionPane.showMessageDialog(this, "Se ha asignado la Id: " + id);
		}
		
	}
}