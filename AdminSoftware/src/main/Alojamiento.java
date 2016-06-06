package main;

import java.util.Date;

public class Alojamiento {

	String usuario;
	String habitacion;
	Date inicio;
	Date fin;
	
	public Alojamiento(String usuario, String habitacion, Date inicio, Date fin) {
		super();
		this.usuario = usuario;
		this.habitacion = habitacion;
		this.inicio = inicio;
		this.fin = fin;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "Alojamiento [usuario=" + usuario + ", habitacion=" + habitacion + ", inicio=" + inicio + ", fin=" + fin
				+ "]";
	}
	
}
