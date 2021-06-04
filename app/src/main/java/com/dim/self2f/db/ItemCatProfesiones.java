package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatProfesiones {
	@DatabaseField
	private int idProfesion;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String profesion;
	
	public int getIdProfesion() {
		return idProfesion;
	}
	
	public void setIdProfesion(int idProfesion) {
		this.idProfesion = idProfesion;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getProfesion() {
		return profesion;
	}
	
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
}
