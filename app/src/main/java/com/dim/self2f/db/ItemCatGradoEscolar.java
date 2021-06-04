package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatGradoEscolar {
	@DatabaseField
	private int idGrado;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String Grado;
	
	public int getIdGrado() {
		return idGrado;
	}
	
	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getGrado() {
		return Grado;
	}
	
	public void setGrado(String grado) {
		Grado = grado;
	}
}
