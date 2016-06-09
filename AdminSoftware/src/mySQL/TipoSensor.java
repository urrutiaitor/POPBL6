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
	
	public int submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO tiposensor (nombre, descripcion) "
				+ "VALUES (\"" + nombre + "\", \"" + descripcion + "\");";

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
