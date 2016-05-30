package conexiones;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnNodoWorker implements Runnable {

	Socket clientSocket;
	
	OutputStream os;
	InputStream is;
	
	public ConnNodoWorker (Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			os = clientSocket.getOutputStream();
			is = clientSocket.getInputStream();
		} catch (IOException e) {
			System.err.println("Error creando output/input stream");
			return;
		}
	}
	
	
	@Override
	public void run() {
		while (!clientSocket.isClosed()) {
			is.
		}
	}

}
