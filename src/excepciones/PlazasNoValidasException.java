package excepciones;
/**
 * Excepción que se lanza cuando se intenta introducir un número de plazas no válido
 * para un vehículo.
 * 
 * @author Saul Crespo
 *
 */
public class PlazasNoValidasException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8184225001427303730L;
	/**
	 * Constructor por defecto.
	 */
	public PlazasNoValidasException() {
		super("Las plazas que ha introducido no son plazas válidas para un vehículo.");
	}
}
