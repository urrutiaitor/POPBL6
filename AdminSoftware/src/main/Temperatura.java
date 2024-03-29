package main;

import java.sql.Date;

public class Temperatura {

	double valor;
	Date tiempo;
	String situacion;
	int habitacion;
	
	public Temperatura(double valor, Date tiempo, String situacion, int habitacion) {
		super();
		this.valor = valor;
		this.tiempo = tiempo;
		this.situacion = situacion;
		this.habitacion = habitacion;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
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

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Temperatura [valor=" + valor + ", tiempo=" + tiempo + ", situacion=" + situacion + "]";
	}
	
}
