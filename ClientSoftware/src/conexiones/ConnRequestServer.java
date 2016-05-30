package conexiones;

public class ConnRequestServer {

	String usuario;
	int contrasena;
	
	
	
	public ConnRequestServer(String usuario, int contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	/*
	 * 
	 * Tiene que acceder al servidor, conectandose a traves de ICE y preguntar por el usuario y contrasena
	 * Si el usuario y contrasena es correcta, devuelve la conexion, si no FALSE
	 * 
	 */

	public ConnServer acceder() {
		// TODO Auto-generated method stub
		
		if(true) return new ConnServer(usuario);
		
		return null;
	}
}
