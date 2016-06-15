package conexiones;

import java.sql.Date;
import java.util.ArrayList;

import main.Proximidad;
import main.Temperatura;

public class ConnServer {

	Ice.Communicator ic = null;
	utils.StringVectorHolder vectorHolder = new utils.StringVectorHolder();
	static utils.GetHistorialPrx theConsultor;
	
	public ConnServer(String serverIp) {
		
		try {
			ic = Ice.Util.initialize();
			Ice.ObjectPrx base = ic.stringToProxy("TheConsultor:default -h " + serverIp + " -p 10000");
			theConsultor = utils.GetHistorialPrxHelper.checkedCast(base);
			if (theConsultor == null)
				throw new Error("Invalid proxy");
			
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
		
		System.out.println("ICE Client activated");
	}

	public ArrayList<Proximidad> getProximidad(String usuario, String contrasena) {
		String[] vector = theConsultor.getProx(usuario, contrasena);
		ArrayList<Proximidad> proxList = new ArrayList<Proximidad>();
		
		for (int i = 0; i < vector.length; i++) {
			String data[] = vector[i].split("%");
			
			Date date = new Date(Long.parseLong(data[0]));
			String situacion = data[1];
			int habitacion = Integer.parseInt(data[2]);
			
			Proximidad p = new Proximidad(date, situacion, habitacion);
			proxList.add(p);
		}
		
		return proxList;
		
	}

	public ArrayList<Temperatura> getTemperatura(String usuario, String contrasena) {
		String[] vector = theConsultor.getTemp(usuario, contrasena);
		ArrayList<Temperatura> proxList = new ArrayList<Temperatura>();
		
		for (int i = 0; i < vector.length; i++) {
			String data[] = vector[i].split("%");
			
			double valor = Double.parseDouble(data[0]);
			Date date = new Date(Long.parseLong(data[1]));
			String situacion = data[2];
			int habitacion = Integer.parseInt(data[3]);
			
			Temperatura t = new Temperatura(valor, date, situacion, habitacion);
			proxList.add(t);
		}
		
		return proxList;
	}
	
	public boolean comprobarUsuario (String usuario, String contrasena) {
		if (theConsultor.comprobarUsuario(usuario, contrasena)) return true;
		return false;
		
	}

	public void close () {
		ic.destroy();
	}
	
}
