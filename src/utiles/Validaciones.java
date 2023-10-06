package utiles;
/**
 * Clase que contiene métodos estáticos para validar Strings que tengan que tener
 * un patrón particular, tales como DNI, matrícula, etc...
 * 
 * @author saulc
 *
 */
public class Validaciones {
	/**
	 * El patrón de letras para el DNI.
	 */
	public static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	/**Método para comprobar si un DNI es válido o no.
	 * 
	 * @param DNI String con el DNI que se desea validar.
	 * @return True si es un DNI válido, false si no lo es.
	 */
	public static boolean DNI(String DNI) {
		return (DNI.length() == 9 && digitos(DNI,8) && LETRAS_DNI.charAt(Integer.valueOf(DNI.substring(0,8)) % 23) == DNI.charAt(8));
	}
	
	/** Método para comprobar si una matrícula es válida o no.
	 * 
	 * @param matricula String con la matrícula que se desea validar.
	 * @return True si es válida, false si no lo es.
	 */
	public static boolean matricula(String matricula) {
		return (matricula.length() == 7 && digitos(matricula,4) && letras(matricula.substring(4),3));
	}
	
	/**Método para comprobar si todos los carácteres dentro de una String o substring son dígitos.
	 * 	
	 * @param s La String que se desea analizar.
	 * @param n El número de carácteres que se desea comprobar.
	 * @return True si todos los carácteres analizados son dígitos, false si hay por lo menos uno que no lo es.
	 */
	public static boolean digitos(String s, int n) {
		for (int i = 0; i < n; i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**Método para comprobar si todos los carácteres dentro de una String o substring son letras.
	 * 	
	 * @param s La String que se desea analizar.
	 * @param n El número de carácteres que se desea comprobar.
	 * @return True si todos los carácteres analizados son letras, false si hay por lo menos uno que no lo sea.
	 */
	public static boolean letras(String s, int n) {
		for (int i = 0; i < n; i++) {
			if (!Character.isLetter(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	

}