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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Target {
	private String url = "http://localhost:8080/interceptor/home.xhtml";

	public void execute(double rpm) throws IOException, URISyntaxException {
		System.out.println("Executing interface UI");

		if (Desktop.isDesktopSupported()) {
			// Windows & Mac
			Desktop.getDesktop().browse(new URI(url));
		} else {
			// Ubuntu
			Runtime.getRuntime().exec("/usr/bin/firefox -new-window " + url);
		}
	}
}
