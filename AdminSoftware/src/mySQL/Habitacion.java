package mySQL;

public class Habitacion {

	int habitacionId;
	String nombre;
	String descripcion;
	int tamano;
	int piso;
	int hotelId;
	
	public Habitacion(String nombre, String descripcion, int tamano, int piso, int hotelId) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tamano = tamano;
		this.piso = piso;
		this.hotelId = hotelId;
	}
	
	public int submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO habitacion (nombre, descripcion, tamano, piso, hotelId) "
				+ "VALUES (\"" + nombre + "\", \"" + descripcion + "\", \"" + tamano + "\", \""
				+ piso + "\", \""+ hotelId + "\");";

		System.out.println(sql);
		habitacionId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return habitacionId;
	}

	@Override
	public String toString() {
		return "Habitacion [habitacionId=" + habitacionId + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", tamano=" + tamano + ", piso=" + piso + ", hotelId=" + hotelId + "]";
	}
	
}
