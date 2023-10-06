package utiles;
/**Clase con diferentes métodos estáticos para generar de forma aleatoria códigos con un patrón determinado.
 * 
 * @author Saul Crespo
 *
 */
public class Generador {
	/**
	 * Método para generar un DNI válido de forma aleatoria.
	 * 
	 * @return String con un DNI válido aleatorio.
	 */
	public static String DNI() {
		String s = "";
		for (int i = 0; i < 8; i++) {
			s += Mates.randomInt(0, 9);
		}
		s += Validaciones.LETRAS_DNI.charAt(Integer.valueOf(s) % 23);
		return s;
	}
	/**
	 * Método para generar una matrícula aleatoria.
	 * 
	 * @return String con un DNI válido aleatorio.
	 */
	public static String Matricula() {
		String s = "";
		for (int i = 0; i < 4; i++) {
			s += Mates.randomInt(0, 9);
		}
		for (int i = 0; i < 3; i++) {
			s += (char) Mates.randomInt(65, 90);
		}
		return s;
	}
}