package alquileres;

import entidades.Cliente;
import entidades.Vehiculo;
import enums.Combustibles;
import java.time.format.DateTimeParseException;
import excepciones.ClienteAlquilandoException;
import excepciones.DNIIncorrectoException;
import excepciones.MatriculaIncorrectaException;
import excepciones.PlazasNoValidasException;
import excepciones.VehiculoAlquiladoException;
import excepciones.VehiculoNoExisteException;

import utiles.Consola;
import utiles.Ficheros;

/** Esta es una clase auxiliar, que complementa a la clase Principal.
 * 
 * Contiene una serie de métodos estáticos cuya función es recolectar los datos recibiendo el input del
 * usuario, en los casos en los que el usuario deba introducir muchos datos diferentes.
 * 
 * @author Saul Crespo
 * 
 * */
public class DataCollector extends Ficheros{
	
	/** Método que recoge los datos de un vehículo, para guardarlos en un objeto Vehículo que pueda ser
	 * gestionado por el gestor de alquileres.
	 * 
	 * @param c El objeto consola que va a pedir y recibir los datos del usuario.
	 * 
	 * @return El objeto Vehiculo con los datos recolectados.
	 * 
	 * */
	public static Vehiculo vehiculo(Consola c) {
		byte steps = 0;
		String[] data = new String[6];
		do {
			try {
				if (steps == 0) {
					data[0] = c.in("Introduzca matricula"); 
					steps++;
				}
				if (steps == 1) {
					data[1] = c.in("Introduzca marca");
					steps++;
				}
				if (steps == 2) {
					data[2] = c.in("Introduzca modelo");
					steps++;
				}
				if (steps == 3) {
					data[3] = c.in("Introduzca plazas");
					steps++;
				}
				if (steps == 4) {
					data[4] = c.in("Introduzca combustible");
					steps++;
				}
				if (steps == 5) {
					data[5] = c.in("Introduzca precio");
					steps++;
				}
				
				return new Vehiculo(data[0],data[1],data[2],
									Integer.parseInt(data[3]),
									Combustibles.valueOf(data[4].toUpperCase()),
									Float.parseFloat(data[5]));
			} catch (MatriculaIncorrectaException e) {
				System.out.println(e.getMessage());
				steps = 0;
			} catch (NumberFormatException e) {
				System.out.println("Introduzca un formato numerico valido");
				steps = (e.getMessage().toLowerCase().contains("integer"))? (byte) 3:5;
			} catch (PlazasNoValidasException e) {
				System.out.println(e.getMessage());
				steps = 3;
			} catch (IllegalArgumentException e) {
				System.out.println("El combustible introducido no es correcto.");
				steps = 4;
			} catch (Exception e) {
				System.out.println(e);
			}
		} while (c.in("Desea repetir(r) o salir(s)").toLowerCase().charAt(0) != ('s'));
		return null;
	}
	
	/** Método que recoge los datos de un vehiculo existente en el gestor, para actualizarlos. Se pide
	 *  la matricula del vehiculo, el nombre del atributo que se desea modificar, y el nuevo valor que 
	 *  tendrá el atributo.
	 *  
	 *  @param c El objeto consola que va a pedir y recibir los datos del usuario.
	 *  
	 *  @return Un array de Strings con los tres datos:
	 *  <ul>
	 *  	<li>[0] Matrícula del vehículo.</li>
	 *  	<li>[1] Nombre del campo que se va a actualizar.</li>
	 *  	<li>[2] Nuevo valor del campo actualizado.</li>
	 *  </ul>
	 *  
	 * */
	public static String[] datosVehiculo(Consola c) {
		String[] temp = new String[3];
		temp[0] = c.in("Introduzca matrícula del vehiculo a actualizar");
		temp[1] = c.in("Introduzca el nombre del campo a actualizar");
		temp[2] = c.in("Introduzca el valor que desea");
		return temp;
	}
	
	/** Método que recoge los datos de un vehiculo existente en el gestor, para actualizarlos. Se pide
	 *  la matricula del vehiculo, el nombre del atributo que se desea modificar, y el nuevo valor que 
	 *  tendrá el atributo.
	 *  
	 *  @param matricula La matricula del vehiculo que deseamos actualizar.
	 *  @param c El objeto consola que va a pedir y recibir los datos del usuario.
	 *  
	 *  @return Un array de Strings con los tres datos:
	 *  <ul>
	 *  	<li>[0] Matrícula del vehículo.</li>
	 *  	<li>[1] Nombre del campo que se va a actualizar.</li>
	 *  	<li>[2] Nuevo valor del campo actualizado.</li>
	 *  </ul>
	 *  
	 * */
	public static String[] datosVehiculo(String matricula, Consola c) {
		String[] temp = new String[3];
		temp[0] = matricula;
		temp[1] = c.in("Introduzca el nombre del campo a actualizar");
		temp[2] = c.in("Introduzca el valor que desea");
		return temp;
	}

	/**
	 * Método que recoge los datos de un nuevo cliente para guardarlo en la base de datos.
	 * 
	 * @param c El objeto consola que va a pedir y recibir los datos del usuario.
	 * 
	 * @return El objeto Cliente con los datos recolectados.
	 * 
	 * @throws DNIIncorrectoException Una excepción cuando el usuario no haya introducido un DNI válido
	 * 
	 * */
	public static Cliente cliente(Consola c) throws DNIIncorrectoException {
		return new Cliente(c.in("Introduzca el DNI del cliente"),
						   c.in("Introduzca el nombre del cliente"),
						   c.in("Introduzca los apellidos del cliente"));
	}
	/**
	 * Método que recoge los datos de un nuevo cliente para guardarlo en la base de datos.
	 * 
	 * @param DNI Una String con el DNI del cliente que deseamos guardar.
	 * @param c El objeto consola que va a pedir y recibir los datos del usuario.
	 * 
	 * @return El objeto Cliente con los datos recolectados.
	 * 
	 * @throws DNIIncorrectoException Una excepción cuando el usuario no haya introducido un DNI válido
	 * 
	 * */
	public static Cliente cliente(String DNI, Consola c) throws DNIIncorrectoException {
		return new Cliente(DNI,c.in("Introduzca nombre del cliente"), c.in("Introduzca apellidos del cliente"));
	}
	
	
	/** Método que recoge los datos necesarios para realizar una reserva, y que realiza la misma en el 
	 *  gestor de alquileres recibido como parámetro.
	 *  
	 *  @param gs Gestor de alquileres donde se realizará la reserva.
	 *  @param c El objeto consola que va a pedir y recibir los datos del usuario.
	 * 
	 * */
	public static void reserva(GestionAlquileres gs, Consola c) {
		boolean flag = false;
		byte steps = 0;
		String matricula,cliente;
		matricula = cliente = "";
		do {
			try {
				if (steps == 0) {
					matricula = c.in("Introduzca la matricula del vehiculo a reservar");
					steps++;
				}
				if (steps == 1) {
					cliente = c.in("DNI del cliente");
					if (!gs.hasCliente(cliente)) {
						if(c.in("El cliente no existe. Desea inscribir un cliente con este DNI? s/n").toLowerCase().charAt(0) == 's') {
							gs.addCliente(DataCollector.cliente(cliente,c));
						} else {
							break;
						}
					}
					steps++;
				}
				if (steps == 2) {
					gs.reservarVehiculo(matricula, cliente,c.in("Introduzca la fecha en formato hh/mm/dd/MM/yyyy"));
					flag = true;
				}
			} catch (VehiculoNoExisteException | VehiculoAlquiladoException e) {
				System.out.println(e.getMessage());
				steps = 1;
			} catch (ClienteAlquilandoException | DNIIncorrectoException e) {
				System.out.println(e.getMessage());
				steps = 0;
			} catch (DateTimeParseException | NumberFormatException e) {
				System.out.println("Error al introducir la fecha.");
				steps = 2;
			} catch (Exception e) {
				System.out.println(e);
				steps = 0;
			} finally {
				if (!flag) {
					flag = (c.in("Desea repetir? s/n").toLowerCase().charAt(0) == 'n');
				}
			}
		} while (!flag);
		System.out.println("Volviendo al menu principal...");
	}
}