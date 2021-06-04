package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatLocalidades {
	@DatabaseField
	private String idLocalidad;
	@DatabaseField
	private String cp;
	@DatabaseField
	private int idColonia;
	@DatabaseField
	private String colonia;
	@DatabaseField
	private int idMunicipio;
	@DatabaseField
	private String municipio;
	@DatabaseField
	private int idEstado;
	@DatabaseField
	public String estado;
	
	public String getIdLocalidad() {
		return idLocalidad;
	}
	
	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
	public String getCp() {
		return cp;
	}
	
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public int getIdColonia() {
		return idColonia;
	}
	
	public void setIdColonia(int idColonia) {
		this.idColonia = idColonia;
	}
	
	public String getColonia() {
		return colonia;
	}
	
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	public int getIdMunicipio() {
		return idMunicipio;
	}
	
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public int getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
