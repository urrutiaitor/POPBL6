package mySQL;

import java.sql.Date;

public class Sensor {

	int sensorId;
	Date fecha;
	int habitacionId;
	int tipoSensorId;
	
	public Sensor(Date fecha, int habitacionId, int tipoSensorId) {
		super();
		this.fecha = fecha;
		this.habitacionId = habitacionId;
		this.tipoSensorId = tipoSensorId;
	}
	
	public int submit() {
		JDBC dbConnection = new JDBC();

		String sql = "INSERT INTO sensor (fecha, habitacionId, tipoSensorId) "
				+ "VALUES ('" + fecha.toString() + "', '" + habitacionId + "', '" + tipoSensorId + "');";

		System.out.println(sql);
		sensorId = dbConnection.ejecutarUpdate(sql);
		
		System.out.println(toString());

		return sensorId;
	}

	@Override
	public String toString() {
		return "Sensor [sensorId=" + sensorId + ", fecha=" + fecha + ", habitacionId=" + habitacionId
				+ ", tipoSensorId=" + tipoSensorId + "]";
	}

}