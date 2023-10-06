package alquileres;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import entidades.Cliente;
import entidades.Reserva;
import entidades.Vehiculo;
import enums.Combustibles;
import excepciones.*;
import utiles.Ficheros;
/**
 * Clase cuya función es gestionar todas las listas de objetos de nuestra aplicación.
 * 
 * La instancia de esta clase será manipulada desde la clase Principal y su clase auxiliar, DataCollector.
 * 
 * @author saulc
 *
 */
public class GestionAlquileres extends Ficheros {
	/**
	 * Nombre de la empresa de alquileres.
	 */
	private String nombre;
	/**
	 * Lista de vehículos de los que dispone la empresa.
	 */
	private ArrayList<Vehiculo> vehiculos;
	/**
	 * Lista con las matrículas de los vehículos para comprobaciones rápidas acerca de la existencia de un
	 * vehículo con una matrícula dada.
	 */
	private HashSet<String> matriculas;
	
	/** Lista de las reservas realizadas con esta empresa.
	 */
	private ArrayList<Reserva> reservas;
	
	/**
	 * Lista de los clientes que han contratado los servicios de la empresa, cuya yave
	 */
	private HashMap<String, Cliente> clientes;
	
	/**
	 * String con la lista de errores a la hora de cargar datos desde archivo. Informa del archivo y el registro
	 * en el que se encuentra el error, y solo se inicializa con el constructor GestionAlquileres(String,String,String,String)
	 */
	private String errorLog;
	
	/**
	 * Constructor por defecto.
	 * Asigna el nombre "Empresa Alquiler" al gestor de alquileres.
	 */
	public GestionAlquileres() {
		this("Empresa Alquiler");
	}
	
	/**Constructor que recibe el nombre de la empresa como parámetro.
	 * Inicializa todas las listas como listas vacías.
	 * 
	 * @param nombre String con el nombre de la empresa.
	 * 
	 * */
	public GestionAlquileres(String nombre) {
		this.nombre = nombre;
		vehiculos = new ArrayList<Vehiculo>();
		reservas = new ArrayList<Reserva>();
		clientes = new HashMap<String, Cliente>();
		matriculas = new HashSet<String>();
	}
	
	/**Constructor que carga en el gestor de alquileres los datos de vehículos, clientes y reservas
	 * guardados en archivos de texto.
	 * 
	 * 
	 * @param nombre String con el nombre de la empresa.
	 * @param vehiculos La ruta del archivo desde el que cargaremos los datos de los vehículos.
	 * @param clientes La ruta del archivo desde el que cargaremos los datos de los clientes. 
	 * @param reservas La ruta del archivo desde el que cargaremos los datos de las reservas.
	 */
	public GestionAlquileres(String nombre, String vehiculos, String clientes, String reservas) {
		this.nombre = nombre;
		errorLog = "";
		this.matriculas = new HashSet<String>();
		ArrayList<Vehiculo> tempVehiculos = new ArrayList<Vehiculo>();
		String[] lines = lee(vehiculos).split("\n");
		for(int i = 0; i< lines.length; i++) {
			try {
				String[] fields = lines[i].split("\t");
				matriculas.add(fields[0]);
					tempVehiculos.add(new Vehiculo(fields[0],fields[1],fields[2],
						Byte.parseByte(fields[3]),
						Combustibles.valueOf(fields[4].toUpperCase()),
						Float.parseFloat(fields[5]), fields[6]));
			} catch (MatriculaIncorrectaException | PlazasNoValidasException e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\n" +e.getMessage();
			} catch (NumberFormatException e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\nError en el formato numérico";
			} catch (Exception e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\n" +e;
			}
		}
		this.vehiculos = tempVehiculos;
		HashMap<String, Cliente> tempClientes = new HashMap<String, Cliente>();
		lines = lee(clientes).split("\n");
		for(int i = 0; i< lines.length; i++) {
			try {
				String[] fields = lines[i].split("\t");
				tempClientes.put(fields[0], new Cliente(fields[0],fields[1],fields[2], fields[3]));
			} catch (DNIIncorrectoException e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\n" +e.getMessage();
			} catch (Exception e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\n" +e;
			}
		}
		this.clientes = tempClientes;
		ArrayList<Reserva> tempReservas = new ArrayList<Reserva>();
		lines = lee(reservas).split("\n");
		for(int i = 0; i< lines.length; i++) {
			try {
				String[] fields = lines[i].split("\t");
				String[] units = fields[2].split("/");
				LocalDateTime ldt = LocalDateTime.of(
					Integer.parseInt((units[4].endsWith("\r")? units[4].substring(0, 4):units[4])),
					Integer.parseInt(units[3]),
					Integer.parseInt(units[2]),
					Integer.parseInt(units[0]),
					Integer.parseInt(units[1]));
				tempReservas.add(new Reserva(
						this.vehiculos.get(getIndexOfVehiculo(fields[0])),
						this.clientes.get(fields[1]), ldt));
			} catch (VehiculoNoExisteException e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\n" +e.getMessage();
			} catch (DateTimeException | NumberFormatException e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\nFecha en formato incorrecto";
			} catch (NullPointerException e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] +
						"\nEl Cliente de esta reserva no se encuentra alquilando actualmente";
			} catch (Exception e) {
				errorLog += "\nError al cargar el registro " + i + "del archivo " + clientes + ": " + lines[i] + "\n" +e;
			}
		}
		Collections.sort(tempReservas);
		if (errorLog.isEmpty()) {
			errorLog = "Todos los datos se han cargado correctamente.";
		}
		this.reservas =  tempReservas;
	}
	
	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	
	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public HashMap<String, Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(HashMap<String, Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
	
	/**
	 * Método que añade un vehículo a la lista de vehículos.
	 * 
	 * @param vehiculo El vehículo que queremos añadir.
	 * @return True si el vehículo se ha podido añadir correctamente,
	 * 	false si ya existe un vehículo con la matrícula indicada en la base de datos.
	 */
	public boolean addVehiculo(Vehiculo vehiculo) {
		String matricula = vehiculo.getMatricula();
		if (matriculas.contains(matricula)) {
			return false;
		}
		matriculas.add(matricula);
		vehiculos.add(vehiculo);
		return true;
	}
	
	/**Método que devuelve una lista con los datos de todos los vehículos que hay en la empresa.
	 * 
	 * @return String con la lista de los vehículos con todos sus datos.
	 */
	public String listarVehiculos() {
		String temp = nombre.toUpperCase() + "-> Coches disponibles:";
		for (Vehiculo v:vehiculos) {
			temp += "\n" + v;
		}
		return temp;
	}
	
	/**Método que sirve para encontrar un vehículo entre los vehículos disponibles introduciendo
	 * la matrícula del mismo.
	 * 
	 * @param matricula La matrícula del vehículo que deseamos encontrar.
	 * @return El objeto Vehículo con la matrícula especificada.
	 * @throws VehiculoNoExisteException En caso de que no haya ningún vehículo con la matrícula
	 * especificada, lanza esta excepción.
	 */
	public Vehiculo findVehiculo(String matricula) throws VehiculoNoExisteException  {
		return vehiculos.get(getIndexOfVehiculo(matricula));
	}
	/**Método que sirve para eliminar un vehículo, conociendo la matrícula del mismo.
	 * 
	 * @param matricula La matrícula del vehículo que deseamos eliminar.
	 * @return El objeto Vehiculo que hemos eliminado
	 * @throws VehiculoNoExisteException En caso de que no haya ningún vehículo con la matrícula
	 * especificada, lanza esta excepción.
	 */
	public String eliminarVehiculo(String matricula) throws VehiculoNoExisteException {
		return "Vehiculo con matricula " + vehiculos.remove(getIndexOfVehiculo(matricula)).getMatricula() + " eliminado.";
	}
	
	/** Método que devuelve el índice de un vehículo dentro de la lista de vehículos conociendo
	 * la matrícula.
	 * 
	 * @param matricula La matrícula del vehículo del que deseamos conocer el índice.
	 * @return Un int con el índice del vehículo.
	 * @throws VehiculoNoExisteException  En caso de que no haya ningún vehículo con la matrícula
	 * especificada, lanza esta excepción.
	 */
	public int getIndexOfVehiculo(String matricula) throws VehiculoNoExisteException  {
		if (matriculas.contains(matricula)) {
			for (int i = 0; i < vehiculos.size(); i++) {
				if (vehiculos.get(i).getMatricula().equals(matricula)) {
					return i;
				}
			}
		}
		throw new VehiculoNoExisteException();
	}
	/** Método que sirve para actualizar los datos de un vehículo que ya se encuentra almacenado
	 * en el gestor de alquileres. Sólo permite actualizar los campos "precio" y "alquilado" de dicho
	 * vehículo.
	 * 
	 * @param matricula La matrícula del vehículo que deseamos actualizar.
	 * @param dato El nombre del atributo que deseamos actualizar.
	 * @param input El nuevo valor que queremos dar al atributo
	 * @throws NumberFormatException En caso de que no se introduzca un valor válido como nuevo precio.
	 * @throws CampoNoModificableException En caso de que no esté permitido modificar este campo.
	 * @throws CampoNoExisteException En caso de que el campo que se pase como parámetro no exista.
	 * @throws VehiculoNoExisteException En caso de que no exista ningún vehículo con esta matrícula.
	 * @throws PlazasNoValidasException En caso de que el número de plazas no sea válido.
	 */
	public Vehiculo actualizarVehiculo(String matricula, String dato, String input) throws NumberFormatException,
																					   CampoNoModificableException,
																					   CampoNoExisteException,
																					   VehiculoNoExisteException,
																					   PlazasNoValidasException {
		Vehiculo v = findVehiculo(matricula);
		switch(dato.toLowerCase()) {
			case "precio"-> v.setPrecio(Float.parseFloat(input));
			case "alquilado"-> v.setEnAlquiler(input);
			case "combustible" -> v.setCombustible(Combustibles.valueOf(input.toUpperCase()));
			case "plazas" -> v.setPlazas(Byte.parseByte(input));
			case "matricula"-> throw new CampoNoModificableException(dato,"Vehiculo");
			case "marca" -> throw new CampoNoModificableException(dato,"Vehiculo");
			case "modelo" -> throw new CampoNoModificableException(dato,"Vehiculo");
			default -> throw new CampoNoExisteException(dato,"Vehiculo");
		}
		return v;
	}
	
	/**Método para realizar la reserva de un vehículo y guardarla en la lista de reservas.
	 * 
	 * @param matricula Una String con la matrícula del vehículo que queremos reservar.
	 * @param DNI Una String con el DNI del cliente que desea realizar la matricula.
	 * @param fecha String. El formato para meter la fecha tiene que ser: hh/mm/dd/MM/YYYY.
	 * @throws VehiculoNoExisteException En caso de que no exista ningún vehículo con esta matrícula.
	 * @throws VehiculoAlquiladoException En caso de que el vehículo seleccionado ya se encuentre alquilado.
	 * @throws ClienteAlquilandoException En caso de que el cliente seleccionado ya esté alquilando un coche.
	 * @throws DateTimeParseException En caso de que haya habido algun error al introducir la fecha.
	 * @throws DateTimeParseException En caso de que haya habido algun error con las fechas.
	 * @throws NumberFormatException En caso de que haya habido un error introduciendo los números de las fechas.
	 * @throws DateTimeException En caso de que haya algun problema con las fechas.
	 */
	public void reservarVehiculo(String matricula, String DNI, String fecha) throws VehiculoNoExisteException,
																					VehiculoAlquiladoException,
																					ClienteAlquilandoException,
																					DateTimeParseException,
																					DateTimeException,
																					NumberFormatException {
		Vehiculo v = findVehiculo(matricula);
		Cliente c = clientes.get(DNI);
		String[] temp = fecha.split("/");
		LocalDateTime f = LocalDateTime.of(
				Integer.parseInt(temp[4]),Integer.parseInt(temp[3]),Integer.parseInt(temp[2]),
				Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
		
		if (v.isEnAlquiler()) {
			throw new VehiculoAlquiladoException();
		}
		
		if(clientes.get(DNI).isEnAlquiler()) {
			throw new ClienteAlquilandoException();
		}
		reservas.add(new Reserva(v,c,f));
		v.setEnAlquiler(true);
		c.setEnAlquiler(true);
		Collections.sort(reservas);
	}
	
	/** Método para finalizar la reserva de un vehículo y devolverlo a la empresa.
	 * 
	 * @param matricula String con la matrícula del vehículo a devolver.
	 * @param DNI String con el DNI del cliente que quiere devolver el vehículo.
	 * @return String con el importe total del alquiler del vehñiculo.
	 * @throws VehiculoNoExisteException En caso de que no exista ningún vehículo con esta matrícula.
	 * @throws VehiculoNoAlquiladoException En caso de que el vehículo seleccionado no esté alquilado.
	 * @throws ClienteNoAlquilandoException En caso de que el cliente seleccionado no esté alquilando ningún vehículo.
	 * @throws ReservaNoExisteException En caso de que no exista ninguna reserva con los datos introducidos.
	 */
	public String devolverVehiculo(String matricula, String DNI) throws VehiculoNoExisteException,
																		VehiculoNoAlquiladoException,
																		ClienteNoAlquilandoException,
																		ReservaNoExisteException {
		Vehiculo v = findVehiculo(matricula);
		Cliente c = clientes.get(DNI);
		float importe = -1;
		
		if (!v.isEnAlquiler()) {
			throw new VehiculoNoAlquiladoException();
		}
		
		if(!clientes.get(DNI).isEnAlquiler()) {
			throw new ClienteNoAlquilandoException();
		}
		
		for (int i = 0; i < reservas.size(); i++) {
			if(reservas.get(i).getVehiculo().equals(v) && reservas.get(i).getCliente().equals(c)) {
				importe =  reservas.remove(i).getImporte();
				break;
			}
		}
		if (importe == -1) {
			throw new ReservaNoExisteException();
		}
		v.setEnAlquiler(false);
		c.setEnAlquiler(false);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(importe);
	}
	/**Comprueba si el cliente con un DNI dado está registrado en el gestor de alquileres.
	 * 
	 * @param DNI String con el DNI del cliente.
	 * @return True si ya hay un cliente con este DNI, false si no.
	 */
	public boolean hasCliente(String DNI) {
		return clientes.containsKey(DNI);
	}
	
	/**Añade un cliente a la lista de clientes de la empresa.
	 * 
	 * @param cliente El objeto de la clase Cliente que se desea añadir a la lista de clientes.
	 */
	public void addCliente(Cliente cliente) {
		clientes.put(cliente.getDNI(), cliente);
	}
	
	public String listarClientes() {
		String temp = nombre.toUpperCase() + "-> Clientes:";
		for (String c:clientes.keySet()) {
			temp += "\n" + clientes.get(c);
		}
		return temp;
	}
	
	public String listarReservas() {
		String temp = nombre.toUpperCase() + "-> Reservas:";
		for (Reserva r:reservas) {
			temp += "\n" + r;
		}
		return temp;
	}
	
	@Override
	public String toString() {
		return listarVehiculos() + "\n" + listarClientes() + "\n" + listarReservas();	
	}

}
