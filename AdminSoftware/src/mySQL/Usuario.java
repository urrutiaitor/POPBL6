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
	String numero;
	
	public Usuario(String nombre, String apellido, Date fechaNacimiento, String localidad, String pais, int contrasena, String DNI, String numero) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.localidad = localidad;
		this.pais = pais;
		this.contrasena = contrasena;
		this.DNI = DNI;
		this.numero = numero;
	}
	
	public int submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO usuario (nombre, apellido, fechaNacimiento, localidad, pais, contrasena, DNI, numero) "
				+ "VALUES (\"" + nombre + "\", \"" + apellido + "\", \"" + fechaNacimiento.toString() + "\", \""
				+ localidad + "\", \""+ pais + "\", \"" + contrasena + "\", \"" + DNI + "\", \"" + numero + "\");";

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
