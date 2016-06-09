package interfazGrafica;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFileChooser;

public class MyAction extends AbstractAction {
	String text;
	String serverIp;
	
	public MyAction (String text, Icon imagen, String description, Integer nemonic, String serverIp){
		super(text,imagen);
		this.text = text;
		this.serverIp = serverIp;
		this.putValue( Action.SHORT_DESCRIPTION ,description);
		this.putValue(Action.MNEMONIC_KEY, nemonic);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case "Anadir usuario":
			UsuarioDialog usuarioD = new UsuarioDialog(serverIp);
			break;
		case "Anadir personal":
			PersonalDialog personalD = new PersonalDialog(serverIp);
			break;
		case "Anadir tipo personal":
			TipoPersonalDialog tipoPersonalD = new TipoPersonalDialog(serverIp);
			break;
		case "Anadir habitacion":
			HabitacionDialog habitacionD = new HabitacionDialog(serverIp);
			break;
		case "Anadir hospedaje":
			HospedajeDialog hospedajeD = new HospedajeDialog(serverIp);
			break;
		case "Anadir sensor":
			SensorDialog sensorD = new SensorDialog(serverIp);
			break;
		case "Anadir tipo sensor":
			TipoSensorDialog tipoSensorD = new TipoSensorDialog(serverIp);
			break;
		case "Anadir trabaja":
			TrabajaDialog trabajaD = new TrabajaDialog(serverIp);
			break;
		case "Exit":
			System.exit(-1);
			break;
		}
		
		
	}
}
