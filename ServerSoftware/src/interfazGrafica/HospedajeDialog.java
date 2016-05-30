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

import mySQL.Habitacion;
import mySQL.Hospeda;
import mySQL.JDBC;

public class HospedajeDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JTextField anoInicioT;
	JTextField mesInicioT;
	JTextField diaInicioT;
	JTextField anoFinalT;
	JTextField mesFinalT;
	JTextField diaFinalT;
	JComboBox<String> usuarioB;
	JComboBox<String> habitacionB;
	
	public HospedajeDialog () {
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
		JPanel panel = new JPanel(new GridLayout(4, 1));
		
		JDBC jdbc = new JDBC();
		ArrayList<String> nombresUsuario = jdbc.getNombre("usuario");
		String[] usuarioS = new String[nombresUsuario.size()];
		for (int i = 0 ; i < nombresUsuario.size(); i++) {
			usuarioS[i] = nombresUsuario.get(i);
		}
		usuarioB = new JComboBox<String>(usuarioS);
		usuarioB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Usuario")));
		panel.add(usuarioB);
		
		ArrayList<String> nombresHabitacion = jdbc.getNombre("habitacion");
		String[] habitacionS = new String[nombresHabitacion.size()];
		for (int i = 0 ; i < nombresHabitacion.size(); i++) {
			usuarioS[i] = nombresHabitacion.get(i);
		}
		habitacionB = new JComboBox<String>(habitacionS);
		habitacionB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Habitacion")));
		panel.add(habitacionB);
		
		JPanel fechaInicioP = new JPanel(new GridLayout(1, 3));
		fechaInicioP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Fecha de entrada (AA MM DD)")));
		
		anoInicioT = new JTextField();
		fechaInicioP.add(anoInicioT);
		mesInicioT = new JTextField();
		fechaInicioP.add(mesInicioT);
		diaInicioT = new JTextField();
		fechaInicioP.add(diaInicioT);
		panel.add(fechaInicioP);
		
		JPanel fechaFinalP = new JPanel(new GridLayout(1, 3));
		fechaFinalP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Fecha de salida (AA MM DD)")));
		anoFinalT = new JTextField();
		fechaFinalP.add(anoFinalT);
		mesFinalT = new JTextField();
		fechaFinalP.add(mesFinalT);
		diaFinalT = new JTextField();
		fechaFinalP.add(diaFinalT);
		panel.add(fechaFinalP);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Anadir") {
			JDBC jdbc = new JDBC();
			int idUsuario = jdbc.getIdTabla("usuario", (String) usuarioB.getSelectedItem());
			int idHabitacion = jdbc.getIdTabla("habitacion", (String) habitacionB.getSelectedItem());
			@SuppressWarnings("deprecation")
			Hospeda h = new Hospeda(idUsuario, idHabitacion,
					new Date(Integer.parseInt(anoInicioT.getText()) - 1900, Integer.parseInt(mesInicioT.getText()),
							Integer.parseInt(diaInicioT.getText())),
					new Date(Integer.parseInt(anoFinalT.getText()) - 1900, Integer.parseInt(mesFinalT.getText()),
							Integer.parseInt(diaFinalT.getText())));
			if (h.submit()) JOptionPane.showMessageDialog(this, "Se ha anadido correctamente");
		}
	}
}