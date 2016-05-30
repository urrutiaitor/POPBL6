package main;

import java.sql.Date;

public class Proximidad {

	Date tiempo;
	String situacion;
	
	public Proximidad(Date tiempo, String situacion) {
		super();
		this.tiempo = tiempo;
		this.situacion = situacion;
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
	
	@Override
	public String toString() {
		return "Proximidad [situacion=" + situacion + "]";
	}
	
}
