package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mySQL.TipoPersonal;

public class TipoSensorDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JTextField nombreT;
	JTextField descripcionT;
	
	public TipoSensorDialog () {
		this.setLocation(100, 100);
		this.setSize(280, 280);
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
		JPanel panel = new JPanel(new GridLayout(2, 1));
		
		nombreT = new JTextField();
		nombreT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Nombre")));
		panel.add(nombreT);
		
		descripcionT = new JTextField();
		descripcionT.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Descripcion")));
		panel.add(descripcionT);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Anadir") {
			TipoPersonal t = new TipoPersonal(nombreT.getText(), descripcionT.getText());
			int id = t.submit();
			JOptionPane.showMessageDialog(this, "Se ha asignado el id: " + id);
		}
	}
	
}
