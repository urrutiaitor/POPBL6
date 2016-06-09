package main;

import java.util.Scanner;

import cliente.ConnCliente;


public class Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Server IP: ");
		String serverIp = keyboard.nextLine();
		
		ConnCliente connCliente = new ConnCliente(serverIp);
		
	}

}
