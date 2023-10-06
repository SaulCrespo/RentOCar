package excepciones;

/**
 * Excepción que se lanza cuando se intenta acceder a un vehículo
 * que no se encuentra en la base de datos.
 * 
 * @author Saul Crespo
 *
 */
public class VehiculoNoExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 296168972898323018L;
	/**
	 * Constructor por defecto.
	 */
	public VehiculoNoExisteException() {
		super("El vehiculo seleccionado no existe.");
	}
}
