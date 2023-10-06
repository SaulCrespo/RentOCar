package excepciones;
/**
 * 
 * Excepción generada cuando un cliente que no se encuentra alquilando
 * un coche intenta devolverlo.
 * 
 * @author Saul Crespo
 *
 */
public class ClienteNoAlquilandoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8139252571180512603L;
	/**
	 * Constructor por defecto.
	 */
	public ClienteNoAlquilandoException() {
		super("El cliente seleccionado no tiene ningun coche alquilado.");
	}
}
