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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "holaMundo", eager = true)
@RequestScoped
public class HolaMundo implements Serializable {

	private static final long serialVersionUID = -7172204411846250813L;
	private String mensaje;
	
	@ManagedProperty(value="#{mensaje}")
	private Mensaje mensajeBean;

	public HolaMundo(){
		System.out.println("¡Hola Mundo empezado!");
	}

	public String getMensaje() {		
		if (mensajeBean != null)	{	
			mensaje = mensajeBean.getMensaje();
		} else {
			System.out.println("MensajeBean está a null");
		}
		
		return mensaje;
	}

	public void setMensajeBean (Mensaje mensajeBean) {
		this.mensajeBean = mensajeBean;
	}
}