package entidades;

import java.text.DecimalFormat;

import enums.Combustibles;
import excepciones.MatriculaIncorrectaException;
import excepciones.PlazasNoValidasException;
import utiles.Generador;
import utiles.Validaciones;
/**
 * Clase que define los objetos de tipo Vehiculo. Hereda de la superclase
 * ParticipanteAlquiler, dado que estos objetos pueden ser el objeto de un
 * contrato de alquiler.
 * 
 * @see ParticipanteAlquiler
 * @see MatriculaIncorrectaException
 * @see PlazasIncorrectasException
 * 
 * @author Saul Crespo
 *
 */
public class Vehiculo extends ParticipanteAlquiler {
	/**
	 * String con la matrícula del vehículo.
	 */
	private String matricula;
	/**
	 * String con la marca del vehículo.
	 */
	private String marca;
	/**
	 * String con el modelo del vehículo.
	 */
	private String modelo;
	/**
	 * Byte con las plazas del vehículo.
	 */
	private byte plazas;
	/**
	 * @see Combustibles
	 */
	private Combustibles combustible;
	/**
	 * Float con el precio del vehículo.
	 */
	private float precio;
	/**
	 * Constructor por defecto del objeto vehículo. Genera una matrícula aleatoria, Strings
	 * vacías para la marca y el modelo, 4 plazas, combustible Gasolina y 0.00 Euros de precio
	 * de alquiler.
	 * 
	 * @throws MatriculaIncorrectaException En caso de que la matrícula introducida sea incorrecta.
	 * @throws PlazasNoValidasException En caso de que el número de plazas introducidas no sean válidas.
	 */
	public Vehiculo() throws MatriculaIncorrectaException, PlazasNoValidasException {
		this(Generador.Matricula(),"","",(byte) 4,Combustibles.GASOLINA,0.0f);
	}
	/**
	 * Constructor del vehículo, que recibe todos los datos del vehículo como parámetros.:
	 * 
	 * @param matricula La matrícula del coche.
	 * @param marca La marca del coche.
	 * @param modelo El modelo del coche.
	 * @param plazas Un numero entero con las plazas del coche.
	 * @param combustible El combustible que va a utilizar el coche.
	 * @param precio Un float con el precio.
	 * 
	 * @throws MatriculaIncorrectaException En caso de que la matrícula introducida sea incorrecta.
	 * @throws PlazasNoValidasException En caso de que el número de plazas introducidas no sean válidas.
	 */
	public Vehiculo(String matricula, String marca, String modelo, int plazas, Combustibles combustible, float precio) throws MatriculaIncorrectaException, PlazasNoValidasException {
		this(matricula,marca,modelo,(byte) plazas,combustible,precio);
	}
	/**
	 * Constructor del vehículo, que recibe todos los datos del vehículo como parámetros.:
	 * 
	 * @param matricula La matrícula del coche.
	 * @param marca La marca del coche.
	 * @param modelo El modelo del coche.
	 * @param plazas Un byte con las plazas del coche.
	 * @param combustible El combustible que va a utilizar el coche.
	 * @param precio Un float con el precio.
	 * 
	 * @throws MatriculaIncorrectaException En caso de que la matrícula introducida sea incorrecta.
	 * @throws PlazasNoValidasException En caso de que el número de plazas introducidas no sean válidas.
	 */
	public Vehiculo(String matricula, String marca, String modelo, byte plazas, Combustibles combustible, float precio) throws MatriculaIncorrectaException, PlazasNoValidasException {
		super();
		setMatricula(matricula);
		this.marca = marca;
		this.modelo = modelo;
		setPlazas(plazas);
		setCombustible(combustible);
		setPrecio(precio);
	}
	/**
	 * Constructor del vehículo, que recibe todos los datos del vehículo como parámetros.:
	 * 
	 * @param matricula La matrícula del coche.
	 * @param marca La marca del coche.
	 * @param modelo El modelo del coche.
	 * @param plazas Un byte con las plazas del coche.
	 * @param combustible El combustible que va a utilizar el coche.
	 * @param precio Un float con el precio.
	 * @param enAlquiler Boolean que especifica si el vehículo se encuentra en alquiler o no.
	 * 
	 * @throws MatriculaIncorrectaException En caso de que la matrícula introducida sea incorrecta.
	 * @throws PlazasNoValidasException En caso de que el número de plazas introducidas no sean válidas.
	 */
	public Vehiculo(String matricula, String marca, String modelo, byte plazas, Combustibles combustible, float precio, boolean enAlquiler) throws MatriculaIncorrectaException, PlazasNoValidasException {
		super(enAlquiler);
		setMatricula(matricula);
		this.marca = marca;
		this.modelo = modelo;
		setPlazas(plazas);
		setCombustible(combustible);
		setPrecio(precio);
	}
	/**
	 * Constructor del vehículo, que recibe todos los datos del vehículo como parámetros.:
	 * 
	 * @param matricula La matrícula del coche.
	 * @param marca La marca del coche.
	 * @param modelo El modelo del coche.
	 * @param plazas Un byte con las plazas del coche.
	 * @param combustible El combustible que va a utilizar el coche.
	 * @param precio Un float con el precio.
	 * @param enAlquiler String que especifica si el vehículo se encuentra en alquiler o no.
	 * 
	 * @throws MatriculaIncorrectaException En caso de que la matrícula introducida sea incorrecta.
	 * @throws PlazasNoValidasException En caso de que el número de plazas introducidas no sean válidas.
	 */
	public Vehiculo(String matricula, String marca, String modelo, byte plazas, Combustibles combustible, float precio, String enAlquiler) throws MatriculaIncorrectaException, PlazasNoValidasException {
		super(enAlquiler);
		setMatricula(matricula);
		this.marca = marca;
		this.modelo = modelo;
		setPlazas(plazas);
		setCombustible(combustible);
		setPrecio(precio);
	}
	/**
	 * Getter para la matrícula del vehículo.
	 * 
	 * @return La matrícula del vehículo.
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * Setter para la matrícula del vehículo.
	 * 
	 * @param matricula La matrícula que se quiere introducir.
	 * @throws MatriculaIncorrectaException En caso de que la matrícula introducida no sea
	 * una matrícula válida.
	 */
	public void setMatricula(String matricula) throws MatriculaIncorrectaException {
		if (Validaciones.matricula(matricula)) {
			this.matricula = matricula.toUpperCase();
		} else {
			throw new MatriculaIncorrectaException();
		}
	}
	/**
	 * Getter para la marca del vehículo.
	 * @return La marca del vehículo.
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * Setter para la marca del vehículo.
	 * 
	 * @param marca La marca del vehículo.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * Getter para el modelo del vehículo.
	 * @return El modelo del vehículo.
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * Setter para el modelo del vehículo.
	 * @param modelo El modelo del vehículo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * Getter para las plazas del vehículo.
	 * 
	 * @return El número de plazas
	 */
	public byte getPlazas() {
		return plazas;
	}
	/**
	 * Setter para el número de plazas del vehículo.
	 * 
	 * @param plazas Las plazas disponibles en el vehículo (Entre 1 y 9). 
	 * @throws PlazasNoValidasException En caso de que se introduzca un número menor
	 * que 1 o mayor que 9.
	 */
	public void setPlazas(byte plazas) throws PlazasNoValidasException {
		if (plazas > 0 && plazas <= 9) {
			this.plazas = plazas;
		} else {
			throw new PlazasNoValidasException();
		}
	}
	/**
	 * Getter para el combustible del vehículo.
	 * 
	 * @return El combustible del vehículo.
	 * 
	 * @see Combustibles
	 */
	public Combustibles getCombustible() {
		return combustible;
	}
	
	/**
	 * Setter para el combustible del vehículo.
	 * 
	 * @param combustible El combustible del vehículo a introducir.
	 */
	public void setCombustible(Combustibles combustible) {
		this.combustible = combustible;
	}
	
	/**
	 * Getter para el precio del vehículo.
	 * 
	 * @return Devuelve el precio del vehículo en formato Float.
	 */
	public float getPrecio() {
		return precio;
	}
	
	/**
	 * Getter para recibir el precio del vehículo con dos decimales.
	 * 
	 * @return El precio formateado en una String.
	 */
	public String printPrecio() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(precio);
	}
	
	/**
	 * Método Setter para el precio.
	 * 
	 * @param precio El precio que se desea introducir en formato float.
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "Vehiculo con matricula: " + matricula +
				"\n\t-MODELO: " + marca + " " + modelo +
				"\n\t-PLAZAS: " + plazas +
				"\n\t-COMBUSTIBLE: " + combustible +
				"\n\t-PRECIO: " + printPrecio() + "\t-Alquilado: " + printAlquiler();
	}
	
}
