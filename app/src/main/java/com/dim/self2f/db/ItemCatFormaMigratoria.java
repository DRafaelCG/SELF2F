package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatFormaMigratoria {
	@DatabaseField
	private int idForma;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String formaMigratoria;
	
	public int getIdForma() {
		return idForma;
	}
	
	public void setIdForma(int idForma) {
		this.idForma = idForma;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getFormaMigratoria() {
		return formaMigratoria;
	}
	
	public void setFormaMigratoria(String formaMigratoria) {
		this.formaMigratoria = formaMigratoria;
	}
}
