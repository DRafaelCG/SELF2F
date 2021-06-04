package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatNacionalidad {
	@DatabaseField
	private int idNacionalidad;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String Nacionalidad;
	
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getNacionalidad() {
		return Nacionalidad;
	}
	
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
}
