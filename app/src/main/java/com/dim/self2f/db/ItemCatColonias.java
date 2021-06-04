package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatColonias {
	@DatabaseField
	private int idColonia;
	@DatabaseField
	private int idCodigoPostal;
	@DatabaseField
	private String colonia;
	@DatabaseField
	private String claveColonia;
	@DatabaseField
	private String claveCodigoPostal;
	@DatabaseField
	private boolean activo;
	
	public int getIdColonia() {
		return idColonia;
	}
	
	public void setIdColonia(int idColonia) {
		this.idColonia = idColonia;
	}
	
	public int getIdCodigoPostal() {
		return idCodigoPostal;
	}
	
	public void setIdCodigoPostal(int idCodigoPostal) {
		this.idCodigoPostal = idCodigoPostal;
	}
	
	public String getColonia() {
		return colonia;
	}
	
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	public String getClaveColonia() {
		return claveColonia;
	}
	
	public void setClaveColonia(String claveColonia) {
		this.claveColonia = claveColonia;
	}
	
	public String getClaveCodigoPostal() {
		return claveCodigoPostal;
	}
	
	public void setClaveCodigoPostal(String claveCodigoPostal) {
		this.claveCodigoPostal = claveCodigoPostal;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}	
}