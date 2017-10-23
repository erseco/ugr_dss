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

public class CalculateDistance implements Filter {
	private final double RADIUS = 4.86;
	private double rpmPrevious = 0.0;
	
	public double execute(Object o) {
		double rpm = (Double) o;
		double distance = (rpm - rpmPrevious) * 2 * RADIUS * Math.PI;
		rpmPrevious = rpm;
		return distance;
	}
}
