package xbee;

import java.sql.Date;
import java.util.Scanner;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import mysql.Medicion;

public class SerialXBee implements SerialPortEventListener {

	SerialPort serialPort = null;
	long lastTime = 0;
	final static int maxLength = 64;
	String value = "";
	String serverIp;

	public SerialXBee(String serverIp) {
		Scanner keyboard = new Scanner(System.in);
		
		this.serverIp = serverIp;

		String[] portNames = SerialPortList.getPortNames();
		System.out.println("******************** PORT NAMES FOR XBEE********************");

		for (int i = 0; i < portNames.length; i++) {
			System.out.println("*** \tPORT " + (i + 1) + ": " + portNames[i] + " \t***");
		}
		System.out.println("******************************************************");

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

	}

	private void sendTempRequest() {
		System.out.println("\t\t\t\t\tRequest of temperature");
		String hexArray = "7E000F17010013A200403C4C0FFFFE004953C2";

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

				if (receivedData.startsWith("7E")) {
					value = receivedData;
				} else {
					value = value.concat(" " + receivedData);
				}

				manipulateData(value);

			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}
		}

	}

	private void manipulateData(String value) {

		if (value.startsWith("7E 00 12 92 00 13 A2 00 40 3C 4C 2A 9A 14 01 01 00 01 00 00")) {
			if (value.split(" ").length == 22)
				readProx(value);
		}

		if (value.startsWith("7E 00 15 97 01 00 13 A2 00 40 3C 4C 0F 8C A4 49 53")) {
			if (value.split(" ").length == 25)
				readTemp(value);
		}
	}

	private void readProx(String value) {
		if (value.endsWith("01 14")) {
			System.out.println("Movimiento detectado");

			Date fecha = new Date(System.currentTimeMillis());
			double valor = 1;
			String unidad = "MOVIMIENTO";
			int sensorId = 2; // proximidad

			Medicion m = new Medicion(fecha, valor, unidad, sensorId);
			m.submit(serverIp);

			sendTempRequest();

		}
		if (value.endsWith("00 15")) {
			System.out.println("Paron de movimiento");

			Date fecha = new Date(System.currentTimeMillis());
			double valor = 1;
			String unidad = "QUIETO";
			int sensorId = 2; // proximidad

			Medicion m = new Medicion(fecha, valor, unidad, sensorId);
			m.submit(serverIp);
		}
	}

	private void readTemp(String value) {
		String[] data = value.split(" ");
		String temperatureHex = data[22] + data[23];
		System.out.println(temperatureHex);
		int tempInt = Integer.decode("0x" + temperatureHex);
		double temperature = tempInt * 100 / 1024;

		Date fecha = new Date(System.currentTimeMillis());
		double valor = temperature;
		String unidad = "ºC";
		int sensorId = 1; // temperatura

		Medicion m = new Medicion(fecha, valor, unidad, sensorId);
		m.submit(serverIp);

		System.out.println("Temperature: " + temperature);

	}
	
	public boolean closeConn () {
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			System.err.println("Error closing serial port");
			return false;
		}
		
		return true;
	}

}