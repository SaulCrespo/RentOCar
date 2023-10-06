package excepciones;
/**
 * Excepción que se lanza cuando se intenta devolver un coche que no se
 * encuentra alquilado.
 * 
 * @author Saul Crespo
 *
 */
public class VehiculoNoAlquiladoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9081824087664630223L;
	/**
	 * Constructor por defecto.
	 */
	public VehiculoNoAlquiladoException() {
		super("El vehiculo seleccionado no se encuentra alquilado.");
	}
}
