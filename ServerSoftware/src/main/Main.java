package main;

import java.util.Scanner;

import cliente.ConnCliente;
import gsm.SerialGSM;


public class Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Server IP: ");
		String serverIp = keyboard.nextLine();
		
		ConnCliente connCliente = new ConnCliente(serverIp);
		
		SerialGSM sGSM = new SerialGSM(serverIp);
		
	}

}
