package pe.evaluationroom.candidateerrorexam.notify.technicalsupport.bean;

import java.io.Serializable;

public class Detalle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
