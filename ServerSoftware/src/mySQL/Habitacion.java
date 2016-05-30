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
	
	public int submit() {
		JDBC dbConnection = new JDBC();

		String sql = "INSERT INTO personal (nombre, descripcion, tamano, piso, hoteId) "
				+ "VALUES ('" + nombre + "', '" + descripcion + "', '" + tamano + "', '"
				+ piso + "', '"+ hotelId + "');";

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
