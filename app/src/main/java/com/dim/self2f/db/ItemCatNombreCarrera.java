package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatNombreCarrera {
	@DatabaseField
	private int idNombreCarrera;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String nombreCarrera;
	
	public int getIdNombreCarrera() {
		return idNombreCarrera;
	}
	
	public void setIdNombreCarrera(int idNombreCarrera) {
		this.idNombreCarrera = idNombreCarrera;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getNombreCarrera() {
		return nombreCarrera;
	}
	
	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
}
