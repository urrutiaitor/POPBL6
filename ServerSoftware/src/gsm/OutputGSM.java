package gsm;

import java.util.Observable;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class OutputGSM extends Observable implements SerialPortEventListener {

	SerialPort serial;
	
	public OutputGSM(SerialPort serial) {
		super();
		this.serial = serial;
		
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
		if (event.isRXCHAR() && event.getEventValue() > 0) {
			try {
				String receivedData = serial.readString();

				System.out.println("Output: " + receivedData);
				
				this.notifyObservers(receivedData);

			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}
		}
	}

}
