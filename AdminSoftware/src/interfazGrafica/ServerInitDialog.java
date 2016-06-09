package interfazGrafica;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerInitDialog extends JDialog implements ActionListener {

	JTextField ip;
	JButton button;
	
	public ServerInitDialog () {
		this.setContentPane(createFrame());
		this.setSize(200, 100);
		this.setVisible(true);
	}

	private Container createFrame() {
		JPanel panel = new JPanel();
		
		panel.add(ip = new JTextField("Server IP"));
		panel.add(button = new JButton("Aceptar"));
		button.addActionListener(this);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Aceptar") {
			this.dispose();
			Window window = new Window(ip.getText());
		}
	}
	
	
	
}
