package alquileres;

import entidades.Vehiculo;
import enums.Mensajes;
import excepciones.*;
import utiles.Consola;
/**
 * Esta es la clase principal de la aplicación, que contiene el método main. <br>
 * 
 * Contiene los métodos para los diferentes menús de la aplicación, y gestiona la experiencia de usuario.
 * 
 * @author Saul Crespo
 */
public class Principal {
	/**Variable estática donde se guarda el gestor de alquileres de la aplicación.
	 * 
	 */
	public static GestionAlquileres gs;
	/**Variable estática que guarda el objeto consola, que se encarga de pedir y recibir el input del usuario.
	 * 
	 * */
	public static Consola c;
	/**
	 * Método main de la aplicación, desde el cual
	 * se inicia el programa.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		c = new Consola();
		gs = inicializarTienda();
		do {} while(menuPrincipal());
		System.out.println("Hasta pronto!");
		c.close();
	}
	
	/** Método para cargar todos los datos de una tienda de alquileres, ya sea desde los archivos de la base de datos,
	 *  como desde una tienda vacía.
	 * 
	 * @return GestionAlquileres, dependiendo de la elección del usuario con todos los datos cargados en los archivos
	 * o vacío y listo para operar con él.
	 */
	public static GestionAlquileres inicializarTienda() {
		if (c.in("Desea cargar base de datos predefinida? s/n").toLowerCase().charAt(0) == 's') {
			gs = new GestionAlquileres(c.in("Introduzca el nombre de la tienda"),
					"src/database/vehiculos.csv",
					"src/database/clientes.csv",
					"src/database/reservas.csv");
			System.out.println(gs.getErrorLog());
			c.pause();
			System.out.println(gs);
		} else {
			gs = new GestionAlquileres(c.in("Introduzca el nombre de la tienda"));
		}
		return gs;
	}
	
	
	/**
	 * Método que contiene el menú principal de la aplicación.
	 * 
	 * El usuario introduce el número de la opción que desea ejecutar:
	 * 
	 * <ul>
	 * 		<li>1) Menú de gestión de vehículos.</li>
	 * 		<li>2) Realizar una reserva.</li>
	 * 		<li>3) Devolver un coche alquilado.</li>
	 * 		<li>4) Salir.</li>
	 * 		<li>Default) No realizar ninguna acción.</li>
	 * </ul>
	 * 
	 * 	@return True para repetir el menú, false para salir de la aplicación.
	 * */
	public static boolean menuPrincipal() {
		boolean flag = true;
		switch(c.inbr(Mensajes.PRINCIPAL.getMensaje()).toLowerCase().charAt(0)) {
			case '1' -> gestionVehiculos();
			case '2' -> DataCollector.reserva(gs, c);
			case '3' -> {
				try {
					System.out.println("El importe total de su alquiler es " +
							gs.devolverVehiculo(c.in("Introduzca la matricula del vehiculo"),
												c.in("Introduzca el DNI del cliente")) + " Euros.");
				} catch (VehiculoNoExisteException | VehiculoNoAlquiladoException |
						 ClienteNoAlquilandoException | ReservaNoExisteException e) {
					System.out.println(e.getMessage());		
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
			case '4' -> flag = false;
			default -> System.out.println("Entrada incorrecta. Pruebe de nuevo.");	
		}
		if (flag) {
			c.pause();
		}
		return flag;
	}
	
	
	/**
	 * Método que contiene el menú de gestión de vehículos de la aplicación.
	 * 
	 * El usuario introduce el número de la opción que desea ejecutar:
	 * 
	 * <ul>
	 * 		<li>1) Añadir Vehículo.</li>
	 * 		<li>2) Listar los vehículos.</li>
	 * 		<li>3) Buscar los vehículos.</li>
	 * 		<li>4) Actualizar un vehículo.</li>
	 * 		<li>5) Eliminar un vehículo.</li>
	 * 		<li>6) Salir.</li>
	 * 		<li>Default) No realizar ninguna acción.</li>
	 * </ul>
	 * */
	public static void gestionVehiculos() {
		boolean flag = true;
		Vehiculo buffered = null;
		do {
			System.out.println();
			
			switch(c.inbr(Mensajes.GESTION.getMensaje()).charAt(0)) {
				case '1'-> {
					try {
						buffered = DataCollector.vehiculo(c);
						gs.addVehiculo(buffered);
						
					} catch (NullPointerException e) {
						System.out.println("Operacion abortada.");
					}
				}
				case '2'-> System.out.println(gs.listarVehiculos());
				case '3'-> {
					buffered = bufferVehiculo();
				}
				case '4'-> {
					do {	
						try {
							String[] temp;
							if (buffered != null) {
								temp = DataCollector.datosVehiculo(buffered.getMatricula(), c);
							} else {
								temp = DataCollector.datosVehiculo(c);
							}
							buffered = gs.actualizarVehiculo(temp[0], temp[1], temp[2]);
							System.out.println("Vehiculo actualizado.");
						} catch (VehiculoNoExisteException | CampoNoModificableException | CampoNoExisteException | PlazasNoValidasException e ) {
							System.out.println(e.getMessage());
						}catch (NumberFormatException e) {
							System.out.println("Por favor, introduzca un número en el formato correcto.");
						} catch (Exception e) {
							System.out.println(e);;
						}
					} while (c.in("Desea actualizar otro vehiculo? s/n").charAt(0) == 's');
				}
				case '5'-> {
					try {
						if (buffered != null &&
								c.in("Desea eliminar el vehiculo guardado? (matricula: " +
								buffered.getMatricula() + ") s/n" ).toLowerCase().charAt(0) == 's') {
							System.out.println(gs.eliminarVehiculo(buffered.getMatricula()));
							buffered = null;
						} else {
							System.out.println(gs.eliminarVehiculo(c.in("Introduzca matricula del vehiculo que desea eliminar")));
						}	
					} catch (VehiculoNoExisteException e) {
						System.out.println(e.getMessage());
					}	
				}
				case '6'-> flag = false;
				default -> System.out.println("Entrada incorrecta. Pruebe de nuevo.");
			}
			if (flag) {
				c.pause();
			}
		} while (flag);
		System.out.println("Volviendo al menú principal...");
	}
	
	
	/**Método para guardar el último vehículo que se ha buscado, en caso de querer utilizarlo de nuevo para alguna acción
	 * sin necesidad de realizar una nueva búsqueda.
	 * */
	public static Vehiculo bufferVehiculo() {
		try {
			Vehiculo v = gs.findVehiculo(c.in("Introduzca la matricula del vehiculo"));
			System.out.println(v);
			return v;
		} catch (VehiculoNoExisteException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}
}