package cliente;

import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;

import main.Proximidad;
import main.Temperatura;
import mysql.JDBC;

public class ConnCliente {

	Date inicio;
	Date fin;
	int habitacion;
	String serverIp;
	
	public ConnCliente (String serverIp) {
		this.serverIp = serverIp;
	}
	
	
	private int getHabitacion(String usuario, String contrasena) {
		JDBC jdbc = new JDBC(serverIp);
		
		
		int habitacion = jdbc.comprobarEstancia(usuario, contrasena, inicio, fin);
		
		return habitacion;
		
	}
	
	private ArrayList<Proximidad> getProxHistorial(int habitacion) {
		JDBC jdbc = new JDBC(serverIp);
		
		ArrayList<Proximidad> proximidad = jdbc.getProximidad();
		ListIterator<Proximidad> proximidadIt = proximidad.listIterator();
		
		while (proximidadIt.hasNext()) {
			Proximidad p = proximidadIt.next();
			
			if (p.getHabitacion() != habitacion) {
				proximidadIt.remove();
			} else {
				if (p.getTiempo().after(fin)) {
					proximidadIt.remove();
				} else {
					if (p.getTiempo().before(inicio)) proximidadIt.remove();
				}
			}
		}
		
		return proximidad;
	}

	private ArrayList<Temperatura> getTempHistorial(int habitacion) {
		JDBC jdbc = new JDBC(serverIp);
		
		ArrayList<Temperatura> temperatura = jdbc.getTemperatura();
		ListIterator<Temperatura> temperaturaIt = temperatura.listIterator();
		
		while (temperaturaIt.hasNext()) {
			Temperatura t = temperaturaIt.next();
			
			if (t.getHabitacion() != habitacion) {
				temperaturaIt.remove();
			} else {
				if (t.getTiempo().after(fin)) {
					temperaturaIt.remove();
				} else {
					if (t.getTiempo().before(inicio)) temperaturaIt.remove();
				}
			}
		}
		
		return temperatura;
	}
}
