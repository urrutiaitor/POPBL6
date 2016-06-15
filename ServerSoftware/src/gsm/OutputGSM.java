package gsm;

import java.util.concurrent.Semaphore;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class OutputGSM implements SerialPortEventListener {

	SerialPort serial;
	SerialGSM serialGSM;
	Semaphore semaphore;
	
	public OutputGSM(SerialPort serial, SerialGSM serialGSM) {
		super();
		this.serial = serial;
		this.serialGSM = serialGSM;
		
		semaphore = new Semaphore(0);
		
		try {
			serial.openPort();
		} catch (SerialPortException e) {
			System.err.println("Error opening GSM output serial port");
			return;
		}
		
		try {
			serial.addEventListener(this);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		String receivedData = null;
		
		if (event.isRXCHAR() && event.getEventValue() > 0) {
			
			try {
				receivedData = serial.readString();
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Output SerialPortEvent: " + receivedData);
			
			if (receivedData.toUpperCase().contains("OK")) semaphore.release();
			
			if (receivedData.contains("+CLIP")){
				
				serialGSM.call(receivedData);
			}
			
		}
	}

	public void inicializar() {
		while (!tryCommand("ate0")) System.out.println("Intento fallido de comando at+cmgf=1");
		while (!tryCommand("at")) System.out.println("Intento fallido de comando at");
	}
	
public boolean tryCommand (String command) {
		
		try {
			serial.writeString(command + "\r\n");
			System.out.println("Output trying command: " + command);
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

}
