package excepciones;
/**
 * 
 * Excepción generada cuando un cliente que ya se encuentra alquilando
 * un coche intenta alquilar otro.
 * 
 * @author Saul Crespo
 *
 */
public class ClienteAlquilandoException extends Exception {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8167396851559033926L;
	/**
	 * Constructor por defecto.
	 */
	public ClienteAlquilandoException() {
		super("El cliente ya esta alquilando un coche actualmente.");
	}

}
