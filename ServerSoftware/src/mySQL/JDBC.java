package mySQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import main.Proximidad;
import main.Temperatura;

import java.sql.Statement;

public class JDBC {
	final String db = "hotelDB";
	final String dbmsurl = "jdbc:mysql://localhost:3306/" + db;

	String userName;
	String password;
	Statement stmt = null;
	ResultSet result = null;
	Connection conn = null;

	public JDBC(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	public JDBC() {
		userName = "root";
		password = "";
	}

	public ResultSet getStatements() {

		try {
			stmt = conn.createStatement();

		} catch (SQLException e) {
			System.out.println("Error en la statement:");
			System.out.println(e.getMessage());
		}
		return result;

	}

	public Connection getConnection() throws SQLException {

		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection(dbmsurl, connectionProps);
		return conn;
	}

	public int ejecutarUpdate(String sql) {
		int id = 0;
		try {
			
			conn = getConnection();
			getStatements();
			id = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			System.out.println(id);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return id;

	}
	
	public ArrayList<String> getNombre(String tabla) {
		String sql = "SELECT nombre FROM "+ tabla +";";
		ArrayList<String> nombres = new ArrayList<String>();

		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				nombres.add(result.getString(1));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nombres;
	}

	public int getIdTabla(String tabla, String nombre) {
		String sql = "SELECT nombre FROM "+ tabla +" WHERE nombre = " + nombre + ";";
		int num = 0;

		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			num = result.getInt(1);
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	public ArrayList<Proximidad> getProximidad() {
		
		ArrayList<Proximidad> list = new ArrayList<Proximidad>();
		
		String sql = "SELECT medicion.fecha, medicion.unidad " +
				"FROM medicion, sensor, tipoSensor " +
				"WHERE medicion.sensorId = sensor.sensorId " +
				"AND sensor.tipoSensorId = tipoSensor.tipoSensorId " +
				"AND tipoSensor.nombre = \"proximidad\";";

		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				Date date = result.getDate("fecha");
				String unidad = result.getString("unidad");
				list.add(new Proximidad(date, unidad));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
public ArrayList<Temperatura> getTemperatura() {
		
		ArrayList<Temperatura> list = new ArrayList<Temperatura>();
		
		String sql = "SELECT medicion.fecha, medicion.valor, medicion.unidad " +
				"FROM medicion, sensor, tipoSensor " +
				"WHERE medicion.sensorId = sensor.sensorId " +
				"AND sensor.tipoSensorId = tipoSensor.tipoSensorId " +
				"AND tipoSensor.nombre = \"temperatura\";";

		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				double valor = result.getDouble("valor");
				Date date = result.getDate("fecha");
				String unidad = result.getString("unidad");
				list.add(new Temperatura(valor, date, unidad));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
