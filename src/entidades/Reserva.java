package entidades;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/**
 * Clase que define un objeto reserva de un vehículo, con todos los datos de los contratos
 * de alquiler. Cada reserva contiene un objeto Vehiculo, un objeto Cliente, y un objeto
 * LocalDateTime con el inicio de la reserva. Esta clase implementa la interfaz Comparable
 * para poder ordenar la lista de reservas.
 * 
 * @see Vehiculo
 * @see Cliente
 * @see LocalDate
 * @see Comparable
 * 
 * @author Saul Crespo
 *
 */
public class Reserva implements Comparable<Reserva> {
	/**
	 * El vehículo que va a ser alquilado.
	 */
	private Vehiculo vehiculo;
	/**
	 * El cliente que va a alquilar el vehículo.
	 */
	private Cliente cliente;
	/**
	 * La fecha de inicio de la reserva.
	 */
	private LocalDateTime fecha;
	/**
	 * Constructor por defecto de una reserva, que inicializa el vehículo y el cliente
	 * como null, y asigna la fecha y hora de la instanciación de este objeto.
	 */
	public Reserva() {
		this(null, null, LocalDateTime.now());
	}
	/**
	 * Constructor de una reserva con todos los parámetros.
	 * 
	 * @param vehiculo El vehículo que va a ser alquilado.
	 * @param cliente El cliente que va a alquilar el vehículo.
	 * @param fecha La fecha de inicio del alquiler.
	 */
	public Reserva(Vehiculo vehiculo, Cliente cliente, LocalDateTime fecha) {
		this.vehiculo = vehiculo;
		this.cliente = cliente;
		this.fecha = fecha;
	}
	
	/**
	 * Getter para el vehículo de la reserva.
	 * 
	 * @return El objeto vehículo que se está alquilando.
	 */
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	/**
	 * Setter para el vehículo de la reserva.
	 * 
	 * @param vehiculo El objeto vehículo que se va a alquilar.
	 */
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	/**
	 * Getter para el cliente de la reserva.
	 * 
	 * @return El cliente que está alquilando el coche.
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Setter para el cliente de la reserva.
	 * 
	 * @param cliente El cliente que está alquilando el coche.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Getter para la fecha de inicio de la reserva.
	 * 
	 * @return El objeto LocalDateTime de inicio de la reserva.
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	/**
	 * Setter para la fecha de inicio de la reserva.
	 * 
	 * @param fecha El objeto LocalDateTime con el inicio de la reserva.
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/**
	 * Método para calcular el importe total de un alquiler.
	 * 
	 * @return Float con el importe total (precio/día * días alquilado)
	 */
	public float getImporte() {
		return vehiculo.getPrecio() * ChronoUnit.DAYS.between(fecha, LocalDateTime.now());
	}
	
	@Override
	public String toString() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		return "Reserva con fecha: " + df.format(fecha) +
				"\n\t-VEHICULO: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + " con matrícula: " + vehiculo.getMatricula() +
				"\n\t-CLIENTE: " + cliente.getNombre() + " " + cliente.getApellidos() + " con DNI: " + cliente.getDNI();
	}

	@Override
	public int compareTo(Reserva o) {
		if (fecha.isBefore(o.getFecha())) {
			return -1;
		} else if (fecha.isAfter(o.getFecha())){
			return 1;
		} else {
			return 0;
		}
	}
}
