package conexiones;

import mySQL.JDBC;

public class ConnCliente {

	private void getProxList (String usuario) {
		JDBC jdbc = new JDBC();
		
		jdbc.getProx();
		
	}
}
