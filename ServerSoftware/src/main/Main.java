package main;

import java.util.Scanner;

import cliente.ConnCliente;
import gsm.SerialGSM;
import xbee.SerialXBee;


public class Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Server IP: ");
		String serverIp = keyboard.nextLine();
		
		/*
		
		ConnCliente connCliente = new ConnCliente(serverIp);
		
		SerialXBee xbee = new SerialXBee(serverIp);
		
		*/
		
		SerialGSM sGSM = new SerialGSM(serverIp);
		
		keyboard.nextLine();
		
		sGSM.closeConn();
	}

}
