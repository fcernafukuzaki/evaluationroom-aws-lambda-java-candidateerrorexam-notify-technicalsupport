package pe.evaluationroom.candidateerrorexam.notify.technicalsupport.bean;

import java.io.Serializable;

public class SoporteTecnicoNotificacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String correoElectronico;
	private String observacion;
	private Detalle detalle;
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}
	
	@Override
	public String toString() {
		return "SoporteTecnicoNotificacion [correoElectronico=" + correoElectronico + ", observacion=" + observacion
				+ ", detalle=" + detalle + "]";
	}
	
}
