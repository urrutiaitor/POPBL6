package mySQL;

public class Hotel {

	int hotelId;
	String nombre;
	String descripcion;
	int estrellas;
	String direccion;
	String localidad;
	String pais;
	String web;
	
	public Hotel(String nombre, String descripcion, int estrellas, String direccion, String localidad, String pais,
			String web) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estrellas = estrellas;
		this.direccion = direccion;
		this.localidad = localidad;
		this.pais = pais;
		this.web = web;
	}
	
	public int submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO hotel (nombre, descripcion, estrellas, direccion, localidad, pais, web) "
				+ "VALUES (\"" + nombre + "\", \"" + descripcion + "\", \"" + estrellas + "\", \""
				+ direccion + "\", \""+ localidad + "\", \"" + pais + "\", \"" + web + "\");";

		System.out.println(sql);
		hotelId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return hotelId;
	}
	
}
