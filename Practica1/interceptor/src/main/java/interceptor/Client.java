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

import java.io.IOException;
import java.net.URISyntaxException;

public class Client {
	private FilterManager filterManager;
	
	public void setFilterManager(FilterManager filterManager) {
		this.filterManager = filterManager;
	}
	
	public void sendRequest(double request) throws IOException, URISyntaxException {
		filterManager.filterRequest(request);
	}
}
