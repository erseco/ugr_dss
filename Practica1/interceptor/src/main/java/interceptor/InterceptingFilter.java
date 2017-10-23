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

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "interceptingFilter", eager = true)
@SessionScoped
public class InterceptingFilter implements Serializable {
	
	private static final long serialVersionUID = -8496867889013699623L;
	
	private static final String STRING_ENGINE_OFF = "ENGINE OFF";
	private static final String STRING_ENGINE_ON = "ENGINE ON";
	private static final String STRING_ENGINE_SPEEDING = "ENGINE SPEEDING";
	private static final String STRING_TURN_OFF = "turn-off";
	private static final String STRING_TURN_ON = "turn-on";
	
	private static final String COLOR_RED = "#FF0000";
	private static final String COLOR_GREEN = "#00FF00";
	private static final String COLOR_BLUE = "#0000FF";

	private boolean engine_on = false;
    
	@ManagedProperty(value = "#{labelText}")
	private String labelText;

	@ManagedProperty(value = "#{labelColor}")
	private String labelColor;
    
	@ManagedProperty(value = "#{buttonOnOffText}")
	private String buttonOnOffText;
    
	@ManagedProperty(value = "#{buttonOnOffColor}")
	private String buttonOnOffColor;
    
	@ManagedProperty(value = "#{accelerateButtonDisabled}")
	private boolean accelerateButtonDisabled;

	private void on() {
		System.out.println("engine on...");
		this.engine_on = true;
		this.accelerateButtonDisabled = false;
		this.labelText = STRING_ENGINE_ON;
		this.labelColor = COLOR_GREEN;
		this.buttonOnOffText = STRING_TURN_OFF;
		this.buttonOnOffColor = COLOR_RED;
		
	}
	
	private void off() {
		System.out.println("engine off...");
		this.engine_on = false;
		this.accelerateButtonDisabled = true;
		this.labelText = STRING_ENGINE_OFF;
		this.labelColor = COLOR_RED;
		this.buttonOnOffText = STRING_TURN_ON;
		this.buttonOnOffColor = COLOR_GREEN;
		
	}

	private void accelerate() {
		System.out.println("engine speeding...");
		labelText = STRING_ENGINE_SPEEDING;
		labelColor = COLOR_BLUE;
	}
	
	public void toggleOnOff(ActionEvent e) {
		if (this.engine_on) {
			this.off();
		} else {
			this.on();
		}
	}
	
	public void toggleAccelerate(ActionEvent e) {		
		if (this.engine_on)
			this.accelerate();
	}
	
	public String getlabelText() {
		if (this.labelText == null) {
			this.labelText = STRING_ENGINE_OFF;
		}
		return this.labelText;
	}

	public void setlabelText(String labelText) {
		this.labelText = labelText;
	}

	public String getlabelColor() {
		if (this.labelColor == null) {
			this.labelColor = COLOR_RED;
		}
		return labelColor;
	}

	public void setlabelColor(String labelColor) {
		this.labelColor = labelColor;
	}

	public String getbuttonOnOffText() {
		if (this.buttonOnOffText == null) {
			this.buttonOnOffText = STRING_TURN_ON;
			this.accelerateButtonDisabled = true;
		}
		return buttonOnOffText;
	}

	public void setbuttonOnOffText(String buttonOnOffText) {
		this.buttonOnOffText = buttonOnOffText;
	}

	public String getbuttonOnOffColor() {
		if (this.buttonOnOffColor == null) {
			this.buttonOnOffColor = COLOR_GREEN;
			this.accelerateButtonDisabled = true;
		}
		return buttonOnOffColor;
	}

	public void setbuttonOnOffColor(String buttonOnOffColor) {
		this.buttonOnOffColor = buttonOnOffColor;
	}
	
	public boolean getAccelerateButtonDisabled() {	
		return this.accelerateButtonDisabled;
	}
	
	public void setAccelerateButtonDisabled(boolean disabled) {
		this.accelerateButtonDisabled = disabled;
	}
}
