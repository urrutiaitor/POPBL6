package gsm;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class OutputGSM implements SerialPortEventListener {

	SerialPort serial;
	SerialGSM serialGSM;
	
	public OutputGSM(SerialPort serial, SerialGSM serialGSM) {
		super();
		this.serial = serial;
		this.serialGSM = serialGSM;
		
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
		String receivedData;
		
		if (event.isRXCHAR() && event.getEventValue() > 0) {
			try {
				
				do {
				
				receivedData = serial.readString();
				System.out.println("Data: " + receivedData);
				if (receivedData == null) return;
				} while (!receivedData.contains("+CLIP"));
				serialGSM.call(receivedData);

			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}
		}
	}

}
