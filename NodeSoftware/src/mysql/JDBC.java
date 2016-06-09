package mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import main.Alojamiento;
import main.Proximidad;
import main.Temperatura;

import java.sql.Statement;

public class JDBC {
	final String db = "hoteldb";
	String ip = "127.0.0.1";
	String dbmsurl = "jdbc:mysql://" + ip + ":3306/" + db;

	String userName;
	String password;
	Statement stmt = null;
	ResultSet result = null;
	Connection conn = null;

	public JDBC (String username, String password) {
		this.userName = username;
		this.password = password;
	}
	
	public JDBC (String ip) {
		this.ip = ip;
		userName = "hotelroot";
		password = "hotelroot";
		dbmsurl = "jdbc:mysql://" + ip + ":3306/" + db;
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
			id = stmt.executeUpdate(sql);

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
		String sql = "SELECT nombre FROM " + tabla + ";";
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
		String sql = "SELECT " + tabla + "Id FROM " + tabla + " WHERE nombre = \"" + nombre + "\";";
		int num = 0;

		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			result.next();
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

		String sql = "SELECT medicion.fecha, medicion.unidad, sensor.habitacionId " + "FROM medicion, sensor, tipoSensor "
				+ "WHERE medicion.sensorId = sensor.sensorId " + "AND sensor.tipoSensorId = tipoSensor.tipoSensorId "
				+ "AND tipoSensor.nombre = \"Sensor presencia\";";

		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				Date date = result.getDate("fecha");
				String unidad = result.getString("unidad");
				int habitacionId = result.getInt("habitacionId");
				list.add(new Proximidad(date, unidad, habitacionId));
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

		String sql = "SELECT medicion.fecha, medicion.valor, medicion.unidad, sensor.habitacionId " + "FROM medicion, sensor, tipoSensor "
				+ "WHERE medicion.sensorId = sensor.sensorId " + "AND sensor.tipoSensorId = tipoSensor.tipoSensorId "
				+ "AND tipoSensor.nombre = \"Sensor temperatura\";";

		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				double valor = result.getDouble("valor");
				Date date = result.getDate("fecha");
				String unidad = result.getString("unidad");
				int habitacionId = result.getInt("habitacionId");
				list.add(new Temperatura(valor, date, unidad, habitacionId));
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Alojamiento> getAlojamiento() {

		ArrayList<Alojamiento> list = new ArrayList<Alojamiento>();

		String sql = "SELECT usuario.nombre, habitacion.nombre, hospeda.inicio, " + 
				"hospeda.final FROM usuario, habitacion, hospeda " + 
				"WHERE usuario.usuarioId = hospeda.usuarioId " + 
				"AND habitacion.habitacionId = hospeda.habitacionId;";
		
		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				String nombreUsuario = result.getString(1);
				String nombreHabitacion = result.getString(2);
				Date inicio = result.getDate(3);
				Date fin = result.getDate(4);

				list.add(new Alojamiento(nombreUsuario, nombreHabitacion, inicio, fin));
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private int comprobarUsuario(String usuario, String contrasena) {
		String sql = "SELECT usuarioId, DNI, contrasena "
				+ "FROM usuario"
				+ "WHERE DNI = \"" + usuario + "\""
				+ "AND contrasena = \"" + contrasena + "\";";
		
		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			if (result.next()) {
				int usuarioId = result.getInt(1);
				
				conn.close();
				
				return usuarioId;
			} else {
				conn.close();
				return -1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int comprobarEstancia(String usuario, String contrasena, Date inicio, Date fin) {
		int habitacionId = -1;
		
		int usuarioId = comprobarUsuario(usuario, contrasena);
		
		if (usuarioId == -1) return -1;
		
		String sql = "SELECT usuarioId, habitacionId, inicio, fin "
				+ "FROM hospeda"
				+ "WHERE usuarioId = \"" + usuarioId + "\";";
		
		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				habitacionId = result.getInt(2);
				inicio = result.getDate(3);
				fin = result.getDate(4);
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return habitacionId;
		
	}

	public int comprobarEstancia(String usuario, String contrasena) {
		int habitacionId = -1;
		
		int usuarioId = comprobarUsuario(usuario, contrasena);
		
		if (usuarioId == -1) return -1;
		
		String sql = "SELECT usuarioId, habitacionId "
				+ "FROM hospeda"
				+ "WHERE usuarioId = \"" + usuarioId + "\";";
		
		try {
			conn = getConnection();
			getStatements();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				habitacionId = result.getInt(2);
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return habitacionId;
	}
	
}