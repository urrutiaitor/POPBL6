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

import mySQL.Hospeda;
import mySQL.JDBC;
import mySQL.Trabaja;


public class TrabajaDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	JTextField diaInicioT;
	JTextField mesInicioT;
	JTextField anoInicioT;
	JTextField diaFinalT;
	JTextField mesFinalT;
	JTextField anoFinalT;
	JComboBox<String> hotelB;
	JComboBox<String> personaB;
	
	public TrabajaDialog () {
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
		ArrayList<String> nombresHotel = jdbc.getNombre("hotel");
		String[] hotelS = new String[nombresHotel.size()];
		for (int i = 0 ; i < nombresHotel.size(); i++) {
			hotelS[i] = nombresHotel.get(i);
		}
		hotelB = new JComboBox<String>(hotelS);
		hotelB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Hotel")));
		panel.add(hotelB);
		
		ArrayList<String> nombresPersona = jdbc.getNombre("habitacion");
		String[] personaS = new String[nombresPersona.size()];
		for (int i = 0 ; i < nombresPersona.size(); i++) {
			personaS[i] = nombresPersona.get(i);
		}
		personaB = new JComboBox<String>(personaS);
		personaB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Habitacion")));
		panel.add(personaB);
		
		JPanel fechaInicioP = new JPanel(new GridLayout(1, 3));
		fechaInicioP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Fecha de inicio (AA MM DD)")));
		
		anoInicioT = new JTextField();
		fechaInicioP.add(anoInicioT);
		mesInicioT = new JTextField();
		fechaInicioP.add(mesInicioT);
		diaInicioT = new JTextField();
		fechaInicioP.add(diaInicioT);
		panel.add(fechaInicioP);
		
		JPanel fechaFinalP = new JPanel(new GridLayout(1, 3));
		fechaFinalP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Fecha de final (AA MM DD)")));
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
			int idHotel = jdbc.getIdTabla("hotel", (String) hotelB.getSelectedItem());
			int idPersona = jdbc.getIdTabla("persona", (String) personaB.getSelectedItem());
			@SuppressWarnings("deprecation")
			Trabaja t = new Trabaja(idHotel, idPersona,
					new Date(Integer.parseInt(anoInicioT.getText()) - 1900, Integer.parseInt(mesInicioT.getText()),
							Integer.parseInt(diaInicioT.getText())),
					new Date(Integer.parseInt(anoFinalT.getText()) - 1900, Integer.parseInt(mesFinalT.getText()),
							Integer.parseInt(diaFinalT.getText())));
			if (t.submit()) JOptionPane.showMessageDialog(this, "Se ha anadido correctamente");
		}
	}
}
