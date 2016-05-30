package mySQL;

public class TipoSensor {

	int tipoSensorId;
	String nombre;
	String descripcion;
	
	public TipoSensor(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public int submit() {
		JDBC dbConnection = new JDBC();

		String sql = "INSERT INTO personal (nombre, descripcion) "
				+ "VALUES ('" + nombre + "', '" + descripcion + "');";

		System.out.println(sql);
		tipoSensorId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return tipoSensorId;
	}

	@Override
	public String toString() {
		return "TipoSensor [tipoSensorId=" + tipoSensorId + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
