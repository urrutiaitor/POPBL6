
import java.io.IOException;
import java.util.Scanner;

import com.digi.xbee.api.exceptions.XBeeException;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Main implements SerialPortEventListener {

	SerialPort serialPort = null;
	long lastTime = 0;
	final static int maxLength = 64;
	String value = "";

	public static void main(String[] args) {
		Main main = new Main();
	}

	public Main() {
		Scanner keyboard = new Scanner(System.in);

		String[] portNames = SerialPortList.getPortNames();
		System.out.println("******************** PORT NAMES ********************");

		for (int i = 0; i < portNames.length; i++) {
			System.out.println("*** \tPORT " + (i + 1) + ": " + portNames[i] + " \t***");
		}
		System.out.println("****************************************************");

		System.out.println();
		System.out.print("Election: ");
		int num = keyboard.nextInt();
		keyboard.nextLine();
		num--;

		// In the constructor pass the name of the port with which we work
		serialPort = new SerialPort(portNames[num]);

		// Open port
		try {
			serialPort.openPort();
		} catch (SerialPortException e) {
			System.err.println("Error opening serial port");
			return;
		}

		try {
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_ODD);
		} catch (SerialPortException e) {
			System.err.println("Error setting serial parameters");
			return;
		}

		try {
			serialPort.addEventListener(this);
		} catch (SerialPortException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String ans;

		while ((ans = keyboard.nextLine().toUpperCase()) != "E") {

			System.out.print("> ");

			switch (ans) {
			case "P":
				System.out.println("\t\t\t\t\t\tProx request");
				sendProxRequest();
				break;
			case "T":
				System.out.println("\t\t\t\t\t\tTemp request");
				sendTempRequest();
				break;

			default:
				break;
			}

		}

		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			System.err.println("Error closing serial port");
			return;
		}

	}

	private void sendProxRequest() {
		String hexArray = "7E000F17010013A200403C4C2A8A7B024943AD";

		try {
			serialPort.writeBytes(hexStringToByteArray(hexArray));
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void sendTempRequest() {
		String hexArray = "7E000F17010013A200403C4C0F2BCB024953C7";

		try {
			serialPort.writeBytes(hexStringToByteArray(hexArray));
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR() && event.getEventValue() > 0) {
			try {
				String receivedData = serialPort.readHexString();
				System.out.println("Packet--");
				if (receivedData.startsWith("7E")) {
					value = receivedData;
				} else {
					value = value.concat(" " + receivedData);
				}
				
				System.out.println(value);
				
				String[] data = value.split(" ");
				if (data.length < 21) return;
				
				for (int i = 0; i < data.length; i++) {
					System.out.print(data[i] + " ");
				}
				System.out.println("-----> " + data[data.length - 4] + " -------> " + data.length);
				
				if (data[data.length - 4].endsWith("00")) {
					manipulateData(data);
				}
			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}
		}
		
	}

	private void manipulateData(String[] data) {
		System.out.println("Manipulating data + " + data);
	}

}
