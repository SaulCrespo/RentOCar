package excepciones;
/**
 * Excepción que se lanza cuando se detecta un DNI no válido.
 * 
 * @author Saul Crespo
 *
 */
public class DNIIncorrectoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6021208324112560387L;
	/**
	 * Constructor por defecto.
	 */
	public DNIIncorrectoException() {
		super("El DNI introducido no es valido");
	}
}
