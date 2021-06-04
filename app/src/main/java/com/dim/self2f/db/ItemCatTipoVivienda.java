package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatTipoVivienda {
	@DatabaseField
	private int idTipoVivienda;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String tipoVivienda;
	
	public int getIdTipoVivienda() {
		return idTipoVivienda;
	}
	
	public void setIdTipoVivienda(int idTipoVivienda) {
		this.idTipoVivienda = idTipoVivienda;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getTipoVivienda() {
		return tipoVivienda;
	}
	
	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}
}
