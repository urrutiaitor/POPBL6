package interfazGrafica;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFileChooser;

public class MyAction extends AbstractAction {
	String text;
	public MyAction (String text, Icon imagen, String description, Integer nemonic){
		super(text,imagen);
		this.text = text;
		this.putValue( Action.SHORT_DESCRIPTION ,description);
		this.putValue(Action.MNEMONIC_KEY, nemonic);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case "Anadir usuario":
			UsuarioDialog usuarioD = new UsuarioDialog();
			break;
		case "Anadir personal":
			PersonalDialog personalD = new PersonalDialog();
			break;
		case "Anadir tipo personal":
			TipoPersonalDialog tipoPersonalD = new TipoPersonalDialog();
			break;
		case "Anadir habitacion":
			HabitacionDialog habitacionD = new HabitacionDialog();
			break;
		case "Anadir hospedaje":
			HospedajeDialog hospedajeD = new HospedajeDialog();
			break;
		case "Anadir sensor":
			SensorDialog sensorD = new SensorDialog();
			break;
		case "Anadir tipo sensor":
			TipoSensorDialog tipoSensorD = new TipoSensorDialog();
			break;
		case "Anadir trabaja":
			TrabajaDialog trabajaD = new TrabajaDialog();
			break;
		case "Exit":
			System.exit(-1);
			break;
		}
		
		
	}
}
