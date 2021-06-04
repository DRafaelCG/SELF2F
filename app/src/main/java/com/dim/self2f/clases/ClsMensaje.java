package com.dim.self2f.clases;

public class ClsMensaje {
	static private ClsMensaje instancia;
	private String mensaje;
	public ClsMensaje() {
		setMensaje("");
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	static public ClsMensaje getInstancia() {
		if (instancia == null) {
			instancia = new ClsMensaje();
		}
		return instancia;
	}
}
