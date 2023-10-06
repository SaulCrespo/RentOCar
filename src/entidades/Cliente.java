package entidades;

import excepciones.DNIIncorrectoException;
import utiles.Generador;
import utiles.Validaciones;
/**Esta clase define los objetos de tipo cliente, que pueden alquilar los coches.
 * 
 * Hereda de la clase ParticipanteAlquiler, dado que es una de las dos partes en este
 * tipo de contrato.
 * 
 * @see ParticipanteAlquiler
 * @see DNIIncorrectoException
 * 
 * @author Saul Crespo

 */
public class Cliente extends ParticipanteAlquiler {
	private String DNI;
	private String nombre;
	private String apellidos;
	/**
	 * Constructor por defecto, que inicializa nombre y apellidos como Strings vacías
	 * y asigna un DNI aleatorio.
	 * 
	 * @throws DNIIncorrectoException
	 */
	public Cliente()  throws DNIIncorrectoException {
		this(Generador.DNI(),"","");
	}
	
	/**
	 * Constructor con todos los parámetros.
	 * 
	 * @param DNI String con el DNI del cliente.
	 * @param nombre String con el nombre del cliente.
	 * @param apellidos String con los apellidos del cliente.
	 * @throws DNIIncorrectoException En caso de que se introduzca como parámetro un DNI
	 * no válido
	 */
	public Cliente(String DNI, String nombre, String apellidos) throws DNIIncorrectoException {
		super();
		setDNI(DNI);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	/**
	 * Constructor con todos los parámetros.
	 * 
	 * @param DNI String con el DNI del cliente.
	 * @param nombre String con el nombre del cliente.
	 * @param apellidos String con los apellidos del cliente.
	 * @param enAlquiler Booleano que indica si se encuentra en alquiler.
	 * 
	 * @throws DNIIncorrectoException En caso de que se introduzca como parámetro un DNI
	 * no válido
	 */
	public Cliente(String DNI, String nombre, String apellidos, boolean enAlquiler) throws DNIIncorrectoException {
		super(enAlquiler);
		setDNI(DNI);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	/**
	 * Constructor con todos los parámetros.
	 * 
	 * @param DNI String con el DNI del cliente.
	 * @param nombre String con el nombre del cliente.
	 * @param apellidos String con los apellidos del cliente.
	 * @param enAlquiler String que indica si se encuentra en alquiler.
	 * 
	 * @throws DNIIncorrectoException En caso de que se introduzca como parámetro un DNI
	 * no válido
	 */
	public Cliente(String DNI, String nombre, String apellidos, String enAlquiler) throws DNIIncorrectoException {
		this(DNI, nombre, apellidos, enAlquiler.toLowerCase().startsWith("s"));
	}
	/**
	 * Getter para el DNI del cliente.
	 * 
	 * @return String con el DNI del cliente
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * Setter para el DNI del cliente. Controla que el DNI introducido sea válido.
	 * 
	 * @param DNI String con el DNI del cliente.
	 * @throws DNIIncorrectoException En caso de que el DNI introducido no sea válido.
	 */
	public void setDNI(String DNI) throws DNIIncorrectoException {
		if (Validaciones.DNI(DNI)) {
			this.DNI = DNI;
		} else {
			throw new DNIIncorrectoException();
		}
	}
	/**
	 * Getter para el nombre del cliente.
	 * 
	 * @return String con el nombre del cliente.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter para el nombre del cliente.
	 * 
	 * @param nombre String con el nombre del cliente.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter para los apellidos del cliente.
	 * 
	 * @return String con los apellidos del cliente.
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Setter para los apellidos del cliente.
	 * 
	 * @param nombre String con los apellidos del cliente.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	@Override
	public String toString() {
		return "Cliente con DNI " + DNI + ":\n\t-Nombre completo: " + nombre + " " + apellidos;
	}	
}