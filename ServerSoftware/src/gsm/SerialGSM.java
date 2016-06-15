package gsm;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import main.Proximidad;
import main.Temperatura;
import mysql.JDBC;

public class SerialGSM {

	SerialPort serialPortInput;
	SerialPort serialPortOutput;
	private SerialPort serialPort;
	
	InputGSM serialInput;
	OutputGSM serialOutput;
	
	String serverIp;

	public SerialGSM (String serverIp) {
		this.serverIp = serverIp;
		
		Scanner keyboard = new Scanner(System.in);
		
		String[] portNames = SerialPortList.getPortNames();
		System.out.println("************ PORT NAMES FOR GSM ***********");

		for (int i = 0; i < portNames.length; i++) {
			System.out.println("*** \t\tPORT " + (i + 1) + ": " + portNames[i] + " \t\t***");
		}
		System.out.println("*******************************************");

		System.out.println();
		System.out.print("Input line: ");
		int input = keyboard.nextInt();
		keyboard.nextLine();
		input--;
		
		System.out.println();
		System.out.print("Output line: ");
		int output = keyboard.nextInt();
		keyboard.nextLine();
		output--;

		serialPortInput = new SerialPort(portNames[input]);
		serialPortOutput = new SerialPort(portNames[output]);
		
		serialInput = new InputGSM(serialPortInput);
		serialOutput = new OutputGSM(serialPortOutput, this);
		
		System.out.println("***************** INPUT INIT *********************");
		serialInput.inicializar();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("***************** OUTPUT INIT *********************");
		serialOutput.inicializar();

	}

	public boolean closeConn () {
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			System.err.println("Error closing GSM serial port");
			return false;
		}
		
		return true;
	}

	public void call(String arg) {
		JDBC jdbc = new JDBC(serverIp);
		String[] data;
		String numero = null;
		
		try {
			serialInput.serial.writeString("at+chup\r\n");
		} catch (SerialPortException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		data = ((String) arg).split("\"");
		numero = data[1];
		System.out.println("NUM: " + numero);
		
		int habitacion = jdbc.comprobarEstancia(numero);
		
		Proximidad p = getProx(habitacion);
		Temperatura t = getTemp(habitacion);
		
		if ((p == null)||(t == null)) return;
		
		try {
			serialInput.serial.writeString("at+cmgs=\"" + numero + "\"\r\n");
			Thread.sleep(200);
			serialInput.serial.writeString(p.toString()+ "\"\r\n");
			Thread.sleep(200);
			serialInput.serial.writeString(t.toString()+ "\"\r\n");
			Thread.sleep(200);
			serialInput.serial.writeInt((char)26);
			
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Proximidad getProx(int habitacion) {
		JDBC jdbc = new JDBC(serverIp);
		
		ArrayList<Proximidad> proximidad = jdbc.getProximidad();
		ListIterator<Proximidad> proximidadIt = proximidad.listIterator();
		
		while (proximidadIt.hasNext()) {
			Proximidad p = proximidadIt.next();
			
			if (p.getHabitacion() != habitacion) {
				proximidadIt.remove();
			}
		}
		if (proximidad.size() == 0) return null;
		return proximidad.get(proximidad.size() - 1);
	}

	private Temperatura getTemp(int habitacion) {
		JDBC jdbc = new JDBC(serverIp);
		
		ArrayList<Temperatura> temperatura = jdbc.getTemperatura();
		ListIterator<Temperatura> temperaturaIt = temperatura.listIterator();
		
		while (temperaturaIt.hasNext()) {
			Temperatura t = temperaturaIt.next();
			
			if (t.getHabitacion() != habitacion) {
				temperaturaIt.remove();
			}
		}
		
		if (temperatura.size() == 0) return null;
		return temperatura.get(temperatura.size() - 1);
	}
}
