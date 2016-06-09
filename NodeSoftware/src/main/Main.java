package main;

import java.util.Scanner;

import xbee.SerialXBee;


public class Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Server IP: ");
		String serverIp = keyboard.nextLine();
		
		SerialXBee xbee = new SerialXBee(serverIp);
		
		keyboard.nextLine();
		
		xbee.closeConn();
	}

}
