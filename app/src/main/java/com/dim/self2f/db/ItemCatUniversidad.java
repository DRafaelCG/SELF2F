package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatUniversidad {
	@DatabaseField
	private int idUniversidad;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String universidad;
	
	public int getIdUniversidad() {
		return idUniversidad;
	}
	
	public void setIdUniversidad(int idUniversidad) {
		this.idUniversidad = idUniversidad;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getUniversidad() {
		return universidad;
	}
	
	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
}
