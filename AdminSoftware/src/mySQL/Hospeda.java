package mySQL;

import java.sql.Date;

public class Hospeda {

	int usuarioId;
	int habitacionId;
	Date inicio;
	Date fin;
	
	public Hospeda(int usuarioId, int habitacionId, Date inicio, Date fin) {
		super();
		this.usuarioId = usuarioId;
		this.habitacionId = habitacionId;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public boolean submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO hospeda (usuarioId, habitacionId, inicio, final) "
				+ "VALUES (\"" + usuarioId + "\", \"" + habitacionId + "\", \""
				+ inicio.toString() + "\", \"" + fin.toString() + "\");";

		System.out.println(sql);
		if (dbConnection.ejecutarUpdate(sql) > 0) {
			System.out.println(toString());
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Hospeda [usuarioId=" + usuarioId + ", habitacionId=" + habitacionId + ", inicio=" + inicio + ", fin="
				+ fin + "]";
	}
	
}
