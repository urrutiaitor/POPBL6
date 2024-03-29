package main;

import java.sql.Date;

public class Proximidad {

	Date tiempo;
	String situacion;
	int habitacion;
	
	public Proximidad(Date tiempo, String situacion, int habitacion) {
		super();
		this.tiempo = tiempo;
		this.situacion = situacion;
		this.habitacion = habitacion;
	}
	
	public Date getTiempo() {
		return tiempo;
	}
	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}
	public String getSituacion() {
		return situacion;
	}
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public int getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(int habitacion) {
		this.habitacion = habitacion;
	}

	@Override
	public String toString() {
		return "Proximidad [tiempo=" + tiempo + ", situacion=" + situacion + "]";
	}
	
}
