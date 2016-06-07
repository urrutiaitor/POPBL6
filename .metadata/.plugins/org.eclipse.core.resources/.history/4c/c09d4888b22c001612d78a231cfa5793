package gsm;

import jssc.SerialPort;
import jssc.SerialPortException;

public class InputGSM {

	SerialPort serial;

	public InputGSM(SerialPort serial) {
		super();
		this.serial = serial;

		try {
			serial.openPort();
		} catch (SerialPortException e) {
			System.err.println("Error opening GSM input serial port");
			return;
		}
	}

	public boolean write(String command) {
		while (tryCommand(command)) System.out.println("Intento fallido de comando " + command);
		return true;
	}

	public void inicializar() {

		while (!tryCommand("at")) System.out.println("Intento fallido de comando at");
		while (!tryCommand("ata")) System.out.println("Intento fallido de comando ata");
		while (!tryCommand("at+clip=1")) System.out.println("Intento fallido de comando at+clip=1");
		while (!tryCommand("at+cmgf=1")) System.out.println("Intento fallido de comando at+cmgf=1");
	
	}
	
	public boolean tryCommand (String command) {
		
		try {
			serial.writeString(command);
			if (serial.readString().toUpperCase().equals("OK")) return true;
		} catch (SerialPortException e) {
			System.err.println("Error write command: " + command);
			return false;
		}
		
		return false;
	}

}
