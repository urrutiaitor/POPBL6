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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mySQL.Hospeda;
import mySQL.JDBC;
import mySQL.Personal;

public class PersonalDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JTextField nombreT;
	JTextField apellidoT;
	JTextField diaT;
	JTextField mesT;
	JTextField anoT;
	JTextField localidadT;
	JTextField paisT;
	JPasswordField contrasenaT;
	JTextField DNIT;
	JComboBox<String> tipoPersonalB;
	
	public PersonalDialog () {
		this.setLocation(100, 100);
		this.setSize(900, 600);
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
		JPanel panel = new JPanel(new GridLayout(8, 1));
		
		JDBC jdbc = new JDBC();
		ArrayList<String> nombres = jdbc.getNombre("tipoPersonal");
		String[] tipoPersonalS = new String[nombres.size()];
		for (int i = 0 ; i < nombres.size(); i++) {
			tipoPersonalS[i] = nombres.get(i);
		}
		tipoPersonalB = new JComboBox<>(tipoPersonalS);
		tipoPersonalB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Tipo de Personal")));
		panel.add(tipoPersonalB);
		
		nombreT = new JTextField();
		nombreT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Nombre")));
		panel.add(nombreT);
		
		apellidoT = new JTextField();
		apellidoT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Apellido")));
		panel.add(apellidoT);
		
		JPanel fechaP = new JPanel(new GridLayout(1, 3));
		fechaP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Fecha de nacimiento (AA MM DD)")));
		anoT = new JTextField();
		fechaP.add(anoT);
		mesT = new JTextField();
		fechaP.add(mesT);
		diaT = new JTextField();
		fechaP.add(diaT);
		panel.add(fechaP);
		
		localidadT = new JTextField();
		localidadT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Localidad")));
		panel.add(localidadT);
		
		paisT = new JTextField();
		paisT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Pais")));
		panel.add(paisT);
		
		contrasenaT = new JPasswordField();
		contrasenaT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Contrasena")));
		panel.add(contrasenaT);
		
		DNIT = new JTextField();
		DNIT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("DNI")));
		panel.add(DNIT);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Anadir") {
			JDBC jdbc = new JDBC();
			int idTipoPersonal = jdbc.getIdTabla("tipoPersonal", (String) tipoPersonalB.getSelectedItem());
			@SuppressWarnings("deprecation")
			Personal p = new Personal(nombreT.getText(), apellidoT.getText(),
					new Date(Integer.parseInt(anoT.getText()) - 1900, Integer.parseInt(mesT.getText()),
							Integer.parseInt(diaT.getText())),
					localidadT.getText(), paisT.getText(), contrasenaT.getText().hashCode(),
					DNIT.getText(), idTipoPersonal);
			int id = p.submit();
			JOptionPane.showMessageDialog(this, "Se ha asignado el id: " + id);
		}
	}
}