package mySQL;

import java.sql.Date;

public class Personal {

	int personalId;
	String nombre;
	String apellido;
	Date fechaNacimiento;
	String localidad;
	String pais;
	int contrasena;
	String DNI;
	int tipoPersonalId;
	
	
	public Personal(String nombre, String apellido, Date fechaNacimiento, String localidad, String pais, int contrasena, String DNI, int tipoPersonalId) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.localidad = localidad;
		this.pais = pais;
		this.contrasena = contrasena;
		this.DNI = DNI;
		this.tipoPersonalId = tipoPersonalId;
	}
	
	public int submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO personal (nombre, apellido, fechaNacimiento, localidad, pais, contrasena, DNI, tipoPersonalId) "
				+ "VALUES (\"" + nombre + "\", \"" + apellido + "\", \"" + fechaNacimiento.toString() + "\", \""
				+ localidad + "\", \""+ pais + "\", \"" + contrasena + "\", \"" + DNI + "\", \""
				+ tipoPersonalId + "\");";

		System.out.println(sql);
		personalId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return personalId;
	}

	@Override
	public String toString() {
		return "Personal [personalId=" + personalId + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", localidad=" + localidad + ", pais=" + pais
				+ ", contrasena=" + contrasena + ", DNI=" + DNI + ", tipoPersonalId=" + tipoPersonalId + "]";
	}
	
	
	
}
