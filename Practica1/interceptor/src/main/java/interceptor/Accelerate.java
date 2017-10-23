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

public class Accelerate implements Filter {

	public double execute(Object o) {
		System.out.println("Accelerating... ");
		return 0;
	}

}
