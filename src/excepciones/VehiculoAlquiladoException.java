package excepciones;
/**
 * 
 * Excepción que se lanza cuando se intenta alquilar un vehículo que 
 * ya se encuentra alquilado.
 * 
 * @author Saul Crespo
 *
 */
public class VehiculoAlquiladoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3932138815266311906L;
	/**
	 * Constructor por defecto.
	 */
	public VehiculoAlquiladoException() {
		super("El vehiculo indicado ya está alquilado.");
	}
}
