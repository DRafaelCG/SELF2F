package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatBancos {
	@DatabaseField
	private int idBanco;
	@DatabaseField
	private int valor;
	@DatabaseField
	private String descripcion;
	
	public int getIdBanco() {
		return idBanco;
	}
	
	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}