package excepciones;
/**
 * Excepción que se lanza cuando se detecta una matrícula que no es válida.
 * 
 * @author Saul Crespo
 *
 */
public class MatriculaIncorrectaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4412523349677243172L;
	/**
	 * Constructor por defecto.
	 */
	public MatriculaIncorrectaException() {
		super("La matricula introducida es incorrecta.");
	}
}
