/*  
 *  Desarrollo de Sistemas de Software basados en Componentes y Servicios
 *  Master en Ingeniería Informática
 * 
 *  2017 © Copyleft - All Wrongs Reserved
 *
 *  Ernesto Serrano <erseco@correo.ugr.es>
 *  
 */
package prueba;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mensaje", eager = true)
@RequestScoped
public class Mensaje implements Serializable {

	private static final long serialVersionUID = -812411546146037135L;
	private String mensaje = "¡Hola Mundo!";

	public String getMensaje(){
		return mensaje;
	}

	public void setMensaje (String mensaje){
		this.mensaje = mensaje;
	}
}