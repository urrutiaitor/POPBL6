package gsm;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class InputGSM implements SerialPortEventListener {

	SerialPort serial;
	Semaphore semaphore;

	public InputGSM(SerialPort serial) {
		super();
		this.serial = serial;
		
		semaphore = new Semaphore(0);

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
		while (!tryCommand("at+chup")) System.out.println("Intento fallido de comando at+chup");
		while (!tryCommand("at+clip=1")) System.out.println("Intento fallido de comando at+clip=1");
		while (!tryCommand("at+cmgf=1")) System.out.println("Intento fallido de comando at+cmgf=1");
	
	}
	
	public boolean tryCommand (String command) {
		
		try {
			serial.writeString(command);
			try {
				semaphore.tryAcquire(1000, TimeUnit.MILLISECONDS);
				System.out.println("Command done: " + command);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} catch (SerialPortException e) {
			System.err.println("Error write command: " + command);
			return false;
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR() && event.getEventValue() > 0) {
			try {
				if (serial.readString().toUpperCase().equals("OK")) semaphore.release();

			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}
		}
	}

}
