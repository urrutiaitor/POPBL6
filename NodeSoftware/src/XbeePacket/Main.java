package XbeePacket;

import java.util.Scanner;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.connection.IConnectionInterface;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortList;

public class Main {

	static String PORT = "COM7";
	static int BAUD_RATE = 9600;
	
	public static void main(String[] args) {
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
		
		
	}

}
