package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatCodigoPostal {
	@DatabaseField 
	private int idCodigoPostal;
	@DatabaseField
	private int idMunicipio;
	@DatabaseField
	private String codigoPostal;
	@DatabaseField
	private String claveCodigoPostal;
	@DatabaseField
	private String claveMunicipio;
	@DatabaseField
	private boolean activo;
	
	public void setIdCodigoPostal(int idCodigoPostal) {
		this.idCodigoPostal = idCodigoPostal;
	}
	
	public int getIdCodigoPostal() {
		return idCodigoPostal;
	}
	
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	
	public int getIdMunicipio() {
		return idMunicipio;
	}
	
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public String getCodigoPostal() {
		return  codigoPostal;
	}
	
	public void setClaveCodigoPostal(String claveCodigoPostal) {
		this.claveCodigoPostal = claveCodigoPostal;
	}
	
	public String getClaveCodigoPostal() {
		return  claveCodigoPostal;
	}
	
	public void setClaveMunicipio(String claveMunicipio) {
		this.claveMunicipio = claveMunicipio;
	}
	
	public String getClaveMunicipio() {
		return  claveMunicipio;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean isActivo() {
		return activo;
	}
}