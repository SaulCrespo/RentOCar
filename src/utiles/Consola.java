package utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Clase que gestiona las peticiones de datos al usuario y la introducción de los datos pedidos. 
 * Hace las veces de interfaz de usuario en la consola, y sustituye a la clase Scanner.
 * 
 * @author Saul Crespo
 *
 */
public class Consola {
	/**
	 * El bufferedReader que utiliza esta clase para leer los datos introducidos por consola.
	 */
	private BufferedReader bfr;
	/**
	 * Constructor del objeto Consola, que inicializa el BufferedReader para leer los datos
	 * introducidos por el usuario. 
	 */
	public Consola() {
		bfr = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Cierra el stream de datos.
	 */
	public void close() {
		try {
			bfr.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/** Método para introducir texto por defecto. Se piden los datos con un mensaje,
	 * y a continuación el usuario los introduce.
	 * 
	 * @param mensaje String con el mensaje de los datos que se van a pedir al usuario.
	 * @return String con el texto introducido por el usuario (una sola línea).
	 */
	public String in(String mensaje) {
		System.out.print(mensaje +": ");
		try {
			return bfr.readLine();
		} catch (IOException e) {
			return "";
		}
	}
	
	/**Similar al método in(), con la diferencia de que los datos se introducen en una
	 * nueva línea.
	 * 
	 * @param mensaje String con el mensaje de los datos que se van a pedir al usuario.
	 * @return String con el texto introducido por el usuario (una sola línea).
	 */
	public String inbr(String mensaje) {
		System.out.println(mensaje);
		try {
			return bfr.readLine();
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * Método para realizar una pausa en la aplicación, antes de continuar con las siguientes acciones
	 * del usuario.
	 * 
	 */
	public void pause() {
		System.out.print("Presione Enter para continuar...");
		try {
			bfr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
