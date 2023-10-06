package excepciones;
/**Excepción para manejar cuando se intenta devolver un
 * vehículo con unos datos de reserva incorrectos.
 * 
 * @author Saul Crespo
 *
 */
public class ReservaNoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048161820029700331L;

	/**
	 * Constructor por defecto.
	 */
	public ReservaNoExisteException() {
		super("No existe ninguna reserva con los datos introducidos");
	}
}
