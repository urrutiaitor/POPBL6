package conexiones;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ConnNodo implements Runnable {

	ServerSocket serverSocket;
	ArrayList<ConnNodoWorker> workers;
	ConnNodoWorker worker;
	
	public ConnNodo (int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Error abriendo socket de servidor");
		}
		
		workers = new ArrayList<ConnNodoWorker>();
	}
	
	
	@Override
	public void run() {
		while (true) {
			try {
				worker = new ConnNodoWorker(serverSocket.accept());
			} catch (IOException e) {
				System.err.println("Error aceptando y creando worker");
				return;
			};
			
			workers.add(worker);
			worker.run();
		}
	}

}
