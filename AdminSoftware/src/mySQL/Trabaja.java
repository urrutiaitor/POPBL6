package mySQL;

import java.sql.Date;

public class Trabaja {

	int hotelId;
	int personalId;
	Date inicio;
	Date fin;
	
	public Trabaja(int hotelId, int personalId, Date inicio, Date fin) {
		super();
		this.hotelId = hotelId;
		this.personalId = personalId;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public boolean submit(String serverIp) {
		JDBC dbConnection = new JDBC(serverIp);

		String sql = "INSERT INTO trabaja (hotelId, personalId, inicio, final) "
				+ "VALUES (\"" + hotelId + "\", \"" + personalId + "\", \""
				+ inicio.toString() + "\", \"" + fin.toString() + "\");";

		System.out.println(sql);
		int num = dbConnection.ejecutarUpdate(sql);
		
		if (num > 0) {
			System.out.println(toString());
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Trabaja [hotelId=" + hotelId + ", personalId=" + personalId + ", inicio=" + inicio + ", fin=" + fin
				+ "]";
	}
	
}
