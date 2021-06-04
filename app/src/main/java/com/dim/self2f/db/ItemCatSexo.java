package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatSexo {
	@DatabaseField
	private int idSexo;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String sexo;
	
	public int getIdSexo() {
		return idSexo;
	}
	
	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
