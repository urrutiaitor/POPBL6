package cliente;

import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;

import Ice.Object;
import main.Proximidad;
import main.Temperatura;
import mysql.JDBC;

public class ConnCliente {

	Date inicio;
	Date fin;
	int habitacion;
	String serverIp;

	Ice.Communicator ic = null;
	Ice.ObjectAdapter adapter;
	Ice.Identity id;

	public ConnCliente(String serverIp) {
		this.serverIp = serverIp;

		ic = Ice.Util.initialize();
		adapter = ic.createObjectAdapterWithEndpoints("ConsultorAdapter", "default -p 10000");

		Ice.Object object = (Object) new ConsultorI(serverIp);
		id = ic.stringToIdentity("TheConsultor");
		adapter.add(object, id);
		adapter.activate();
	}

	public void close() {
		ic.destroy();
	}
}
