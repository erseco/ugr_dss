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

public class FilterManager {
	private FilterChain filterChain;
	
	public FilterManager(Target target) {
		filterChain = new FilterChain();
		filterChain.setTarget(target);
	}
	
	public void setFilter(Filter filter) {
		filterChain.addFilter(filter);
	}
	
	public void filterRequest(double request) throws IOException, URISyntaxException {
		filterChain.execute(request);
	}
}
