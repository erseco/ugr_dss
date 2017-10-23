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
import java.util.ArrayList;
import java.util.List;

public class FilterChain {
	private Target target;
	private List<Filter> filters;
	
	public FilterChain() {
		filters = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter filter) {
		filters.add(filter);
	}
	
	public void execute(double request) throws IOException, URISyntaxException {
		for (Filter filter: filters) {
			System.out.println("New speed (m/s) " + filter.execute(request));
		}
		target.execute(request);
	}
	
	public void setTarget(Target target) {
		this.target = target;
	}
}
