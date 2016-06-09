package mysql;

import java.sql.Date;

public class Medicion {

	int medicionId;
	Date fecha;
	double valor;
	String unidad;
	int sensorId;
	
	public Medicion(Date fecha, double valor, String unidad, int sensorId) {
		super();
		this.fecha = fecha;
		this.valor = valor;
		this.unidad = unidad;
		this.sensorId = sensorId;
	}
	
	public int submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO medicion (fecha, valor, unidad, sensorId) "
				+ "VALUES (NOW(), '" + valor + "', '" + unidad + "', '" + sensorId + "');";

		System.out.println(sql);
		medicionId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return medicionId;
	}


	@Override
	public String toString() {
		return "Medicion [medicionId=" + medicionId + ", fecha=" + fecha + ", valor=" + valor + ", unidad=" + unidad
				+ ", sensorId=" + sensorId + "]";
	}
	
}