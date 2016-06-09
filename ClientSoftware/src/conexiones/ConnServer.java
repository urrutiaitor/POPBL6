package conexiones;

import java.sql.Date;
import java.util.ArrayList;

import main.Proximidad;
import main.Temperatura;
import utils.StringVectorHolder;

public class ConnServer {

	Ice.Communicator ic = null;
	utils.StringVectorHolder vectorHolder = new utils.StringVectorHolder();
	static utils.GetHistorialPrx theConsultor;
	
	public ConnServer() {
		
		try {
			ic = Ice.Util.initialize();
			Ice.ObjectPrx base = ic.stringToProxy("TheVectorManipulator:default -p 10000");
			theConsultor = utils.GetHistorialPrxHelper.checkedCast(base);
			if (theConsultor == null)
				throw new Error("Invalid proxy");
			
			ic.destroy();
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}

	public static ArrayList<Proximidad> getProximidad(String usuario, String contrasena) {
		StringVectorHolder vector = new StringVectorHolder();
		theConsultor.getProx(usuario, contrasena, vector);
		ArrayList<Proximidad> proxList = new ArrayList<Proximidad>();
		
		for (int i = 0; i < vector.value.length; i++) {
			String data[] = vector.value[i].split("%");
			
			Date date = new Date(Long.parseLong(data[0]));
			String situacion = data[1];
			int habitacion = Integer.parseInt(data[2]);
			
			Proximidad p = new Proximidad(date, situacion, habitacion);
			proxList.add(p);
		}
		
		return proxList;
		
	}

	public static ArrayList<Temperatura> getTemperatura(String usuario, String contrasena) {
		StringVectorHolder vector = new StringVectorHolder();
		theConsultor.getTemp(usuario, contrasena, vector);
		ArrayList<Temperatura> proxList = new ArrayList<Temperatura>();
		
		for (int i = 0; i < vector.value.length; i++) {
			String data[] = vector.value[i].split("%");
			
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
