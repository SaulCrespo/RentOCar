package utiles;
/**
 * Esta clase contiene métodos útiles para realizar
 * operaciones matemáticas.
 * 
 * */
public class Mates {
	/**
	 * Returns a random int between the two parameters (min, max).
	 * 
	 * */
	public static int randomInt(Number min, Number max) {
		return (int) ((Math.random() * ( max.doubleValue() + 1 - min.doubleValue())) + min.doubleValue());
	}
	/**
	 * Returns a random float between the two parameters (min, max).
	 * 
	 * */
	public static float randomFloat(Number min, Number max) {
		return (float) ((Math.random() * ( max.doubleValue() + 1 - min.doubleValue())) + min.doubleValue());
	}
	/**
	 * Returns a random double between the two parameters (min, max).
	 * 
	 * */
	public static double randomDouble(Number min, Number max) {
		return ((Math.random() * ( max.doubleValue() + 1 - min.doubleValue())) + min.doubleValue());
	}
	
	public static long randomLong(Number min, Number max) {
		return (long) ((Math.random() * ( max.longValue() + 1 - min.longValue())) + min.longValue());
	}

	/**Returns the factorial of a number.*/
	public static int factorial(int number) {
		int num = number;
		while (number > 1) {
			number--;
			num *= number;
		}
		return num;
	}
	/**
	 * Returns true if number is prime, false if not.
	 * */
	public static boolean isPrime(int number) {
		for (int i = number - 1; i >1; i--) {
			if (number%i== 0) {
				return false;
			}
		}
		return true;
	}
}