package excepciones;
/**
 * 
 * Excepción lanzada cuando se intenta modificar un atributo cuyo contenido no está
 * permitido modificar.
 * 
 * @author Saul Crespo
 *
 */
public class CampoNoModificableException extends Exception {

	private static final long serialVersionUID = -8311110394303047237L;

	/**
	 * Constructor por defecto.
	 */
	public CampoNoModificableException() {
		super("Ha intentado acceder a un campo que no se puede modificar.");
	}
	
	/**
	 * Constructor más adecuado de esta Excepción, especificando el atributo al cual
	 * se ha intentado acceder, y el tipo de objeto que se intentaba actualizar.
	 * 
	 * @param atributo El atributo que se intentaba actualizar.
	 * @param objeto El objeto que contiene el atributo.
	 */
	public CampoNoModificableException(String campo, String objeto) {
		super("El campo " + campo + " no es modificable en el objeto " + objeto + ".");
	}

}
