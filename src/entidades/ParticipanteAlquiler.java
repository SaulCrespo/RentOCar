package entidades;
/**
 * Super clase que define a una entidad que puede participar en un contrato de alquiler.
 * Sus clases hijas son Vehiculo y Cliente.
 * 
 * @author Saul Crespo
 *
 */
public class ParticipanteAlquiler {
	/**
	 * Booleano que indica si la entidad representada se encuentra actualmente dentro de
	 * un contrato de alquiler.
	 */
	private boolean enAlquiler;
	/**
	 * Constructor por defecto, que da como valor automático falso al atributo enAlquiler.
	 */
	public ParticipanteAlquiler() {
		enAlquiler = false;
	}
	/**
	 * Constructor especificando si el objeto se encuentra en alquiler.
	 * 
	 * @param enAlquiler Booleano que indica si el objeto se encuentra en alquiler en el
	 * momento de instanciarlo.
	 */
	public ParticipanteAlquiler(boolean enAlquiler) {
		this.enAlquiler = enAlquiler;
	}
	/**
	 * Constructor con una string (idealmente introducida por el usuario) que especifica si
	 * el objeto se encuentra en alquiler.
	 * 
	 * @param alquila String que indica si el objeto se encuentra en alquiler en el
	 * momento de instanciarlo. Tendrá los siguientes valores:
	 * <ul>
	 * 		<li>True: Si el usuario introduce una String que comienze con los carácteres 's' o 'S'</li>
	 * 		<li>False: Si el usuario introduce cualquier otro tipo de String</li>
	 * </ul>
	 */
	public ParticipanteAlquiler(String alquila) {
		setEnAlquiler(alquila);
	}
	/**
	 * Getter para el parámetro enAlquiler
	 * 
	 * @return True si se encuentra en alquiler, false si no.
	 */
	public boolean isEnAlquiler() {
		return enAlquiler;
	}
	/**
	 * Método Setter que recibiendo un booleano establece si el objeto se encuentra en alquiler o no.
	 * 
	 * @param enAlquiler Booleano.
	 * <ul>
	 * 		<li>True: Se encuentra en un alquiler.</li>
	 * 		<li>False: No se encuentra en un alquiler.</li>
	 * </ul>
	 */
	public void setEnAlquiler(boolean enAlquiler) {
		this.enAlquiler = enAlquiler;
	}
	/**
	 * Método Setter que recibiendo una string establece si el objeto se encuentra en alquiler o no.
	 * 
	 * @param enAlquiler String (idealmente introducida por el usuario).
	 * <ul>
	 * 		<li>True: Si el usuario introduce una String que comienze con los carácteres 's' o 'S'</li>
	 * 		<li>False: Si el usuario introduce cualquier otro tipo de String</li>
	 * </ul>
	 */
	public void setEnAlquiler(String enAlquiler) {
		this.enAlquiler = (enAlquiler.toLowerCase().charAt(0) == 's');
	}
	/**
	 * Método getter que devuelve una String indicando si el objeto se encuentra en alquiler o no.
	 * 
	 * @return Una String con un mensaje sobre si el objeto se encuentra en alquiler.
	 * <ul>
	 * 		<li>Si: El objeto se encuentra en alquiler.</li>
	 * 		<li>No: El objeto no se encuentra en alquiler.</li>
	 * </ul>
	 */
	public String printAlquiler() {
		return (enAlquiler)? "Si":"No";
	}
}