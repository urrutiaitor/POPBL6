package conexiones;

import java.util.ArrayList;

import main.Proximidad;
import main.Temperatura;

public class ConnServer {

	String usuario;
	
	public ConnServer(String usuario) {
		this.usuario = usuario;
	}

	public static ArrayList<Proximidad> getProximidad() {
		// TODO Auto-generated method stub
		return new ArrayList<Proximidad>();
	}

	public static ArrayList<Temperatura> getTemperatura() {
		// TODO Auto-generated method stub
		return new ArrayList<Temperatura>();
	}

}
