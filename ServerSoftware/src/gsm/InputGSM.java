package gsm;

import java.util.concurrent.Semaphore;
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
		
		try {
			serial.addEventListener(this);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean write(String command) {
		while (tryCommand(command)) System.out.println("Intento fallido de comando " + command);
		return true;
	}

	public void inicializar() {

		while (!tryCommand("ate0")) System.out.println("Intento fallido de comando ate0");
		while (!tryCommand("at")) System.out.println("Intento fallido de comando at");
		while (!tryCommand("at+clip=1")) System.out.println("Intento fallido de comando at+clip=1");
		while (!tryCommand("at+cmgf=1")) System.out.println("Intento fallido de comando at+cmgf=1");
		
	}
	
	public boolean tryCommand (String command) {
		
		try {
			serial.writeString(command + "\r\n");
			System.out.println("Input trying command: " + command);
			semaphore.acquire();
			return true;
		} catch (SerialPortException e) {
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR() && event.getEventValue() > 0) {
			try {
				String text;
				if ((text = serial.readString()).toUpperCase().contains("OK")){
					semaphore.release();
				}
				System.out.println("Input SerialPortEvent: " + text);

			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}
		}
	}

}
