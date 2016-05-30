package mySQL;

import java.sql.Date;

public class Usuario {

	int usuarioId;
	String nombre;
	String apellido;
	Date fechaNacimiento;
	String localidad;
	String pais;
	int contrasena;
	String DNI;
	
	public Usuario(String nombre, String apellido, Date fechaNacimiento, String localidad, String pais, int contrasena, String DNI) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.localidad = localidad;
		this.pais = pais;
		this.contrasena = contrasena;
		this.DNI = DNI;
	}
	
	public int submit() {
		JDBC dbConnection = new JDBC();

		String sql = "INSERT INTO usuario (nombre, apellido, fechaNacimiento, localidad, pais, contrasena, DNI) "
				+ "VALUES ('" + nombre + "', '" + apellido + "', '" + fechaNacimiento.toString() + "', '"
				+ localidad + "', '"+ pais + "', '" + contrasena + "', '" + DNI + "');";

		System.out.println(sql);
		usuarioId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return usuarioId;
	}

	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", localidad=" + localidad + ", pais=" + pais
				+ ", contrasena=" + contrasena + ", DNI=" + DNI + "]";
	}
	
}
