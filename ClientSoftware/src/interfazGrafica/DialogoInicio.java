package interfazGrafica;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import conexiones.ConnServer;

public class DialogoInicio extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField usuario;
	JPasswordField contrasena;
	JButton boton;
	
	String serverIp;
	
	ConnServer connServer;
	
	public DialogoInicio (String serverIp) {
		this.setLocation(100, 100);
		this.setSize(300, 200);
		this.setContentPane(createPane());
		this.setVisible(true);
		this.setModal(false);
		
		this.serverIp = serverIp;
		
		connServer = new ConnServer(serverIp);
	}

	private Container createPane() {
		JPanel panel = new JPanel(new GridLayout(3, 1));
		
		usuario = new JTextField();
		
		usuario.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Usuario [DNI]")));
		panel.add(usuario);
		
		contrasena = new JPasswordField();
		
		contrasena.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Contrasena")));
		panel.add(contrasena);
		
		boton = new JButton("Entrar");
		boton.addActionListener(this);
		panel.add(boton);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Entrar") {
			ConnServer connS = new ConnServer(serverIp);
			String u = usuario.getText();
			String p = String.valueOf(contrasena.getText().hashCode());
			if (connServer.comprobarUsuario(u, p)) {
				this.dispose();
				Window window = new Window (connS, u, p);
			} else {
				JOptionPane.showMessageDialog(this, "El usuario o contrasena no son correctos", "Acceso fallido", JOptionPane.ERROR_MESSAGE);
				usuario.setText("");
				contrasena.setText("");
			}
		}
	}
	
	
}
