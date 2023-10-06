package excepciones;

/**
 * Excepción para manejar cuando el usuario intente acceder a un campo de un objeto
 * que no existe.
 * 
 * 
 * @author Saul Crespo
 *
 */
public class CampoNoExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7518581923019753609L;
	
	
	/**
	 * Constructor por defecto.
	 */
	public CampoNoExisteException() {
		super("Ha intentado acceder a un campo que no existe.");
	}
	
	/**
	 * Constructor más adecuado de esta Excepción, especificando el atributo al cual
	 * se ha intentado acceder, y el tipo de objeto que se intentaba actualizar.
	 * 
	 * @param atributo El atributo que se intentaba actualizar.
	 * @param objeto El objeto que contiene el atributo.
	 */
	public CampoNoExisteException(String atributo, String objeto) {
		super("El campo " + atributo + " no existe en el objeto " + objeto + ".");
	}
}
