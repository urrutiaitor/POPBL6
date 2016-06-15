package cliente;

import java.util.ArrayList;
import java.util.ListIterator;

import Ice.Current;
import main.Alojamiento;
import main.Proximidad;
import main.Temperatura;
import mysql.JDBC;
import utils.StringVectorHolder;

public class ConsultorI extends utils._GetHistorialDisp {

	JDBC jdbc;
	
	public ConsultorI(String serverIp) {
		jdbc = new JDBC(serverIp);
	}

	@Override
	public String[] getProx(String usuario, String contrasena, Current __current) {

		Alojamiento a = jdbc.comprobarEstancia(usuario, contrasena, null, null);
		
		ArrayList<Proximidad> proximidad = jdbc.getProximidad();
		ListIterator<Proximidad> proximidadIt = proximidad.listIterator();

		while (proximidadIt.hasNext()) {
			Proximidad p = proximidadIt.next();
			
			System.out.println("PROXIMIDAD: " + p.toString());
			System.out.println("ALOJAMIENTO: " + a.toString() + "\n");

			if (p.getHabitacion() != Integer.valueOf(a.getHabitacion())) {
				proximidadIt.remove();
			} else {
				if (p.getTiempo().after(a.getFin())) {
					proximidadIt.remove();
				} else {
					if (p.getTiempo().before(a.getInicio()))
						proximidadIt.remove();
				}
			}
		}
		
		
		String[] stringVector = new String[proximidad.size()];
		
		for (int i = 0; i < proximidad.size(); i++) {
			long time = proximidad.get(i).getTiempo().getTime();
			String situacion = proximidad.get(i).getSituacion();
			int habitacion = proximidad.get(i).getHabitacion();
			stringVector[i] = time + "%" + situacion + "%" + habitacion;
			
		}
		
		return stringVector;
	}

	@Override
	public String[] getTemp(String usuario, String contrasena, Current __current) {

		Alojamiento a = jdbc.comprobarEstancia(usuario, contrasena, null, null);
		
		ArrayList<Temperatura> temperatura = jdbc.getTemperatura();
		ListIterator<Temperatura> temperaturaIt = temperatura.listIterator();

		while (temperaturaIt.hasNext()) {
			Temperatura t = temperaturaIt.next();

			if (t.getHabitacion() != Integer.valueOf(a.getHabitacion())) {
				temperaturaIt.remove();
			} else {
				if (t.getTiempo().after(a.getFin())) {
					temperaturaIt.remove();
				} else {
					if (t.getTiempo().before(a.getInicio()))
						temperaturaIt.remove();
				}
			}
		}
		
		String[] stringVector = new String[temperatura.size()];
		
		for (int i = 0; i < temperatura.size(); i++) {
			double valor = temperatura.get(i).getValor();
			long time = temperatura.get(i).getTiempo().getTime();
			String situacion = temperatura.get(i).getSituacion();
			int habitacion = temperatura.get(i).getHabitacion();
			stringVector[i] = valor + "%" + time + "%" + situacion + "%" + habitacion;
		}
		
		return stringVector;
	}

	@Override
	public boolean comprobarUsuario(String usuario, String contrasena, Current __current) {
		int r = jdbc.comprobarEstancia(usuario, contrasena);
		if (r > -1) return true;
		return false;
		
	}

}
