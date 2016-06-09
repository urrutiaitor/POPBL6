package cliente;

import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;

import Ice.Current;
import main.Proximidad;
import main.Temperatura;
import mysql.JDBC;
import utils.StringVectorHolder;

public class ConsultorI extends utils._GetHistorialDisp {

	JDBC jdbc;
	Date inicio;
	Date fin;
	
	public ConsultorI(String serverIp) {
		jdbc = new JDBC(serverIp);
	}

	@Override
	public void getProx(String usuario, String contrasena, StringVectorHolder vector, Current __current) {

		int habitacionId = jdbc.comprobarEstancia(usuario, contrasena, inicio, fin);
		
		ArrayList<Proximidad> proximidad = jdbc.getProximidad();
		ListIterator<Proximidad> proximidadIt = proximidad.listIterator();

		while (proximidadIt.hasNext()) {
			Proximidad p = proximidadIt.next();

			if (p.getHabitacion() != habitacionId) {
				proximidadIt.remove();
			} else {
				if (p.getTiempo().after(fin)) {
					proximidadIt.remove();
				} else {
					if (p.getTiempo().before(inicio))
						proximidadIt.remove();
				}
			}
		}
		
		String[] v = vector.value;
		
		for (int i = 0; i < proximidad.size(); i++) {
			
			v[i] = proximidad.get(i).getTiempo().getTime() + "%" + proximidad.get(i).getSituacion() + "%" + proximidad.get(i).getHabitacion();
			
		}
		
	}

	@Override
	public void getTemp(String usuario, String contrasena, StringVectorHolder vector, Current __current) {

		int habitacionId = jdbc.comprobarEstancia(usuario, contrasena, inicio, fin);
		
		ArrayList<Temperatura> temperatura = jdbc.getTemperatura();
		ListIterator<Temperatura> temperaturaIt = temperatura.listIterator();

		while (temperaturaIt.hasNext()) {
			Temperatura t = temperaturaIt.next();

			if (t.getHabitacion() != habitacionId) {
				temperaturaIt.remove();
			} else {
				if (t.getTiempo().after(fin)) {
					temperaturaIt.remove();
				} else {
					if (t.getTiempo().before(inicio))
						temperaturaIt.remove();
				}
			}
		}
		
		String[] v = vector.value;
		
		for (int i = 0; i < temperatura.size(); i++) {
			
			v[i] = temperatura.get(i).getTiempo().getTime() + "%" + temperatura.get(i).getSituacion() + "%" + temperatura.get(i).getHabitacion();
			
		}
	}

	@Override
	public boolean comprobarUsuario(String usuario, String contrasena, Current __current) {
		if (jdbc.comprobarEstancia(usuario, contrasena) > -1) return true;
		return false;
		
	}

}