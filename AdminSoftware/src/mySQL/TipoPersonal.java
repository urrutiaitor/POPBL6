package mySQL;

public class TipoPersonal {

	int tipoPersonalId;
	String nombre;
	String descripcion;

	public TipoPersonal(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO TipoPersonal (nombre, descripcion) "
				+ "VALUES (\"" + nombre + "\", \"" + descripcion + "\");";

		System.out.println(sql);
		tipoPersonalId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return tipoPersonalId;
	}

	public int getTipoPersonalId() {
		return tipoPersonalId;
	}

	@Override
	public String toString() {
		return "TipoPersonal [tipoPersonalId=" + tipoPersonalId + ", nombre="
				+ nombre + ", descripcion=" + descripcion + "]";
	}
	
	
}
