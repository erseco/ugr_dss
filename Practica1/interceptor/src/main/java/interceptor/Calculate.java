/*
 * Development of Software Systems based on Components and Services
 * Master in Computer Engineering
 *
 * 2017 © Copyleft - All Wrongs Reserved
 *
 * Ernesto Serrano <erseco@correo.ugr.es>
 *
 */
package interceptor;

public class Calculate implements Filter {
	private final double INTERVAL = 14.27;
	
	public double execute(Object o) {
		double distance = (Double) o;
		double speed = distance * 3600 / INTERVAL;
		return speed;
	}
}
