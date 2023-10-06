package enums;
/**
 * Enum con los mensajes que se mostrarán al usuario en los diferentes menús de la aplicación.
 * 
 * @author saulc
 *
 */
public enum Mensajes {
	PRINCIPAL("Que desea hacer?" +
			"\n1) Gestion vehiculos \t2) Realizar Reserva \t3) Devolver vehiculo \t4) Salir "),
	GESTION( "Se encuentra en el espacio de Gestion de Vehiculos. Que desea hacer?" +
			"\n1) Añadir nuevo vehiculo \t2) Listar vehiculos \t3) Buscar un vehiculo" +
			"\n4) Actualizar un vehiculo \t5) Eliminar un vehiculo \t6) Volver al menu principal");
	
	private String mensaje;
	
	/**
	 * Constructor con el mensaje perteneciente a cada tipo.
	 * 
	 * @param mensaje El mensaje perteneciente a cada tipo.
	 */
	Mensajes(String mensaje) {
		this.mensaje = mensaje;
	}

	/**Devuelve una String con el mensaje solicitado.
	 * 
	 * @return String con el mensaje solicitado de los guardados en esta lista.
	 */
	public String getMensaje() {
		return mensaje;
	}	
}
