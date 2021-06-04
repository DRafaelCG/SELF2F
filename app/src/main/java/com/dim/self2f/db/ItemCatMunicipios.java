package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatMunicipios {
	@DatabaseField
	private int idMunicipio;
	@DatabaseField
	private int idEstado;
	@DatabaseField
	private String municipio;
	@DatabaseField
	private String claveMunicipio;
	@DatabaseField
	private String claveEstado;
	@DatabaseField
	private boolean activo;
	
	public int getIdMunicipio() {
		return idMunicipio;
	}
	
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	
	public int getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getClaveMunicipio() {
		return claveMunicipio;
	}
	
	public void setClaveMunicipio(String claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
	}
	
	public String getClaveEstado() {
		return claveEstado;
	}
	
	public void setClaveEstado(String claveEstado) {
		this.claveEstado = claveEstado;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}