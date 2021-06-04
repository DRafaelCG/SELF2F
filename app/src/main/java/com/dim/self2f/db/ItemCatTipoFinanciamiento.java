package com.dim.self2f.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemCatTipoFinanciamiento {
	@DatabaseField
	private int idTipoFinanciamiento;
	@DatabaseField
	private String valor;
	@DatabaseField
	private String TipoFinanciamiento;
	
	public int getIdTipoFinanciamiento() {
		return idTipoFinanciamiento;
	}
	
	public void setIdTipoFinanciamiento(int idTipoFinanciamiento) {
		this.idTipoFinanciamiento = idTipoFinanciamiento;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getTipoFinanciamiento() {
		return TipoFinanciamiento;
	}
	
	public void setTipoFinanciamiento(String tipoFinanciamiento) {
		TipoFinanciamiento = tipoFinanciamiento;
	}
}
