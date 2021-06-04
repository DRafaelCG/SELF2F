package com.dim.self2f.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dim.self2f.clases.ClsCargarCatalogos;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper{
	private Context _mContext;
	DataBaseHelper helper;
	ClsCargarCatalogos catalogos;
	private static final String DB_NAME ="dbCatalogosF2F";
	private static final int DB_VERSION = 1;
	private Dao<ItemCatBancos, Integer> ItemBancos = null;
	private RuntimeExceptionDao<ItemCatBancos, Integer> simpleRuntimeDaoCatBancos = null;
	private Dao<ItemCatEstadoCivil, Integer> ItemEstadoCivil = null;
	private RuntimeExceptionDao<ItemCatEstadoCivil, Integer> simpleRuntimeDaoCatEstadoCivil = null;
	private Dao<ItemCatFormaMigratoria, Integer> ItemFormaMigratoria = null;
	private RuntimeExceptionDao<ItemCatFormaMigratoria, Integer> simpleRuntimeDaoCatFormaMigratoria = null;
	private Dao<ItemCatGradoEscolar, Integer> ItemGradoEscolar = null;
	private RuntimeExceptionDao<ItemCatGradoEscolar, Integer> simpleRuntimeDaoCatGradoEscolar = null;
	private Dao<ItemCatLocalidades, Integer> ItemLocalidades = null;
	private RuntimeExceptionDao<ItemCatLocalidades, Integer> simpleRuntimeDaoCatLocalidades = null;
	private Dao<ItemCatNacionalidad, Integer> ItemNacionalidad = null;
	private RuntimeExceptionDao<ItemCatNacionalidad, Integer> simpleRuntimeDaoCatNacionalidad = null;
	private Dao<ItemCatNombreCarrera, Integer> ItemNombreCarrera = null;
	private RuntimeExceptionDao<ItemCatNombreCarrera, Integer> simpleRuntimeDaoCatNombreCarrera = null;
	private Dao<ItemCatProfesiones, Integer> ItemProfesiones = null;
	private RuntimeExceptionDao<ItemCatProfesiones, Integer> simpleRuntimeDaoCatProfesiones = null;
	private Dao<ItemCatSexo, Integer> ItemSexo = null;
	private RuntimeExceptionDao<ItemCatSexo, Integer> simpleRuntimeDaoCatSexo = null;
	private Dao<ItemCatTipoFinanciamiento, Integer> ItemTipoFinanciamiento = null;
	private RuntimeExceptionDao<ItemCatTipoFinanciamiento, Integer> simpleRuntimeDaoCatTipoFinanciamiento = null;
	private Dao<ItemCatTipoProducto, Integer> ItemTipoProducto = null;
	private RuntimeExceptionDao<ItemCatTipoProducto, Integer> simpleRuntimeDaoCatTipoProducto = null;
	private Dao<ItemCatTipoVivienda, Integer> ItemTipoVivienda = null;
	private RuntimeExceptionDao<ItemCatTipoVivienda, Integer> simpleRuntimeDaoCatTipoVivienda = null;
	private Dao<ItemCatUniversidad, Integer> ItemUniversidad = null;
	private RuntimeExceptionDao<ItemCatUniversidad, Integer> simpleRuntimeDaoCatUniversidad = null;
	public DataBaseHelper(Context mContext) {
		super(mContext, DB_NAME, null, DB_VERSION);
		_mContext = mContext;
	}
	
	public Dao<ItemCatBancos, Integer> getDaoCatBancos() throws java.sql.SQLException{
		if (ItemBancos == null) {
			ItemBancos = getDao(ItemCatBancos.class);
		}
		return ItemBancos;
	}
	
	public Dao<ItemCatEstadoCivil, Integer> getDaoCatEstadoCivil() throws java.sql.SQLException{
		if (ItemEstadoCivil == null) {
			ItemEstadoCivil = getDao(ItemCatEstadoCivil.class);
		}
		return ItemEstadoCivil;
	}
	
	public Dao<ItemCatFormaMigratoria, Integer> getDaoCatFormaMigratoria() throws java.sql.SQLException{
		if (ItemFormaMigratoria == null) {
			ItemFormaMigratoria = getDao(ItemCatFormaMigratoria.class);
		}
		return ItemFormaMigratoria;
	}
	
	public Dao<ItemCatGradoEscolar, Integer> getDaoCatGradoEscolar() throws java.sql.SQLException{
		if (ItemGradoEscolar == null) {
			ItemGradoEscolar = getDao(ItemCatGradoEscolar.class);
		}
		return ItemGradoEscolar;
	}
	
	public Dao<ItemCatLocalidades, Integer> getDaoCatLocalidades() throws java.sql.SQLException{
		if (ItemLocalidades == null) {
			ItemLocalidades = getDao(ItemCatLocalidades.class);
		}
		return ItemLocalidades;
	}
	
	public Dao<ItemCatNacionalidad, Integer> getDaoCatNacionalidad() throws java.sql.SQLException{
		if (ItemNacionalidad == null) {
			ItemNacionalidad = getDao(ItemCatNacionalidad.class);
		}
		return ItemNacionalidad;
	}
	
	public Dao<ItemCatNombreCarrera, Integer> getDaoCatNombreCarrera() throws java.sql.SQLException{
		if (ItemNombreCarrera == null) {
			ItemNombreCarrera = getDao(ItemCatNombreCarrera.class);
		}
		return ItemNombreCarrera;
	}
	
	public Dao<ItemCatProfesiones, Integer> getDaoCatProfesiones() throws java.sql.SQLException{
		if (ItemProfesiones == null) {
			ItemProfesiones = getDao(ItemCatProfesiones.class);
		}
		return ItemProfesiones;
	}
	
	public Dao<ItemCatSexo, Integer> getDaoCatSexo() throws java.sql.SQLException{
		if (ItemSexo == null) {
			ItemSexo = getDao(ItemCatSexo.class);
		}
		return ItemSexo;
	}
	
	public Dao<ItemCatTipoFinanciamiento, Integer> getDaoCatTipoFinanciamiento() throws java.sql.SQLException{
		if (ItemTipoFinanciamiento == null) {
			ItemTipoFinanciamiento = getDao(ItemCatTipoFinanciamiento.class);
		}
		return ItemTipoFinanciamiento;
	}
	
	public Dao<ItemCatTipoProducto, Integer> getDaoCatTipoProducto() throws java.sql.SQLException{
		if (ItemTipoProducto == null) {
			ItemTipoProducto = getDao(ItemCatTipoProducto.class);
		}
		return ItemTipoProducto;
	}
	
	public Dao<ItemCatTipoVivienda, Integer> getDaoCatTipoVivienda() throws java.sql.SQLException{
		if (ItemTipoVivienda == null) {
			ItemTipoVivienda = getDao(ItemCatTipoVivienda.class);
		}
		return ItemTipoVivienda;
	}
	
	public Dao<ItemCatUniversidad, Integer> getDaoCatUniversidad() throws java.sql.SQLException{
		if (ItemUniversidad == null) {
			ItemUniversidad = getDao(ItemCatUniversidad.class);
		}
		return ItemUniversidad;
	}
	
	public RuntimeExceptionDao<ItemCatBancos, Integer> getSimpleDataDaoCatBancos(){
		if (simpleRuntimeDaoCatBancos == null) {
			simpleRuntimeDaoCatBancos = getRuntimeExceptionDao(ItemCatBancos.class);
		}
		return simpleRuntimeDaoCatBancos;
	}
	
	public RuntimeExceptionDao<ItemCatEstadoCivil, Integer> getSimpleDataDaoCatEstadoCivil(){
		if (simpleRuntimeDaoCatEstadoCivil == null) {
			simpleRuntimeDaoCatEstadoCivil = getRuntimeExceptionDao(ItemCatEstadoCivil.class);
		}
		return simpleRuntimeDaoCatEstadoCivil;
	}
	
	public RuntimeExceptionDao<ItemCatFormaMigratoria, Integer> getSimpleDataDaoCatFormaMigratoria(){
		if (simpleRuntimeDaoCatFormaMigratoria == null) {
			simpleRuntimeDaoCatFormaMigratoria = getRuntimeExceptionDao(ItemCatFormaMigratoria.class);
		}
		return simpleRuntimeDaoCatFormaMigratoria;
	}
	
	public RuntimeExceptionDao<ItemCatGradoEscolar, Integer> getSimpleDataDaoCatGradoEscolar(){
		if (simpleRuntimeDaoCatGradoEscolar == null) {
			simpleRuntimeDaoCatGradoEscolar = getRuntimeExceptionDao(ItemCatGradoEscolar.class);
		}
		return simpleRuntimeDaoCatGradoEscolar;
	}
	
	public RuntimeExceptionDao<ItemCatLocalidades, Integer> getSimpleDataDaoCatLocalidades(){
		if (simpleRuntimeDaoCatLocalidades == null) {
			simpleRuntimeDaoCatLocalidades = getRuntimeExceptionDao(ItemCatLocalidades.class);
		}
		return simpleRuntimeDaoCatLocalidades;
	}
	
	public RuntimeExceptionDao<ItemCatNacionalidad, Integer> getSimpleDataDaoCatNacionalidad(){
		if (simpleRuntimeDaoCatNacionalidad == null) {
			simpleRuntimeDaoCatNacionalidad = getRuntimeExceptionDao(ItemCatNacionalidad.class);
		}
		return simpleRuntimeDaoCatNacionalidad;
	}
	
	public RuntimeExceptionDao<ItemCatNombreCarrera, Integer> getSimpleDataDaoCatNombreCarrera(){
		if (simpleRuntimeDaoCatNombreCarrera == null) {
			simpleRuntimeDaoCatNombreCarrera = getRuntimeExceptionDao(ItemCatNombreCarrera.class);
		}
		return simpleRuntimeDaoCatNombreCarrera;
	}
	
	public RuntimeExceptionDao<ItemCatProfesiones, Integer> getSimpleDataDaoCatProfesiones(){
		if (simpleRuntimeDaoCatProfesiones == null) {
			simpleRuntimeDaoCatProfesiones = getRuntimeExceptionDao(ItemCatProfesiones.class);
		}
		return simpleRuntimeDaoCatProfesiones;
	}
	
	public RuntimeExceptionDao<ItemCatSexo, Integer> getSimpleDataDaoCatSexo(){
		if (simpleRuntimeDaoCatSexo == null) {
			simpleRuntimeDaoCatSexo = getRuntimeExceptionDao(ItemCatSexo.class);
		}
		return simpleRuntimeDaoCatSexo;
	}
	
	public RuntimeExceptionDao<ItemCatTipoFinanciamiento, Integer> getSimpleDataDaoCatTipoFinanciamiento(){
		if (simpleRuntimeDaoCatTipoFinanciamiento == null) {
			simpleRuntimeDaoCatTipoFinanciamiento = getRuntimeExceptionDao(ItemCatTipoFinanciamiento.class);
		}
		return simpleRuntimeDaoCatTipoFinanciamiento;
	}
	
	public RuntimeExceptionDao<ItemCatTipoProducto, Integer> getSimpleDataDaoCatTipoProducto(){
		if (simpleRuntimeDaoCatTipoProducto == null) {
			simpleRuntimeDaoCatTipoProducto = getRuntimeExceptionDao(ItemCatTipoProducto.class);
		}
		return simpleRuntimeDaoCatTipoProducto;
	}
	
	public RuntimeExceptionDao<ItemCatTipoVivienda, Integer> getSimpleDataDaoCatTipoVivienda(){
		if (simpleRuntimeDaoCatTipoVivienda == null) {
			simpleRuntimeDaoCatTipoVivienda = getRuntimeExceptionDao(ItemCatTipoVivienda.class);
		}
		return simpleRuntimeDaoCatTipoVivienda;
	}
	
	public RuntimeExceptionDao<ItemCatUniversidad, Integer> getSimpleDataDaoCatUniversidad(){
		if (simpleRuntimeDaoCatUniversidad == null) {
			simpleRuntimeDaoCatUniversidad = getRuntimeExceptionDao(ItemCatUniversidad.class);
		}
		return simpleRuntimeDaoCatUniversidad;
	}
	
	public List<ItemCatBancos> getDataCatBancos(){
		List<ItemCatBancos> listCatBancos = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatBancos, Integer> daoCatBancos = helper.getSimpleDataDaoCatBancos().queryBuilder();
			if (helper.getDaoCatBancos().countOf() > 0) {
				listCatBancos = daoCatBancos.query();
			}else {
				listCatBancos = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatBancos;
	}
	
	public List<ItemCatEstadoCivil> getDataCatEstadoCivil(){
		List<ItemCatEstadoCivil> listCatEstadoCivil = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatEstadoCivil, Integer> daoCatEstadoCivil = helper.getSimpleDataDaoCatEstadoCivil().queryBuilder();
			if (helper.getDaoCatEstadoCivil().countOf() > 0) {
				listCatEstadoCivil = daoCatEstadoCivil.query();
			}else {
				listCatEstadoCivil = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatEstadoCivil;
	}
	
	public List<ItemCatFormaMigratoria> getDataCatFormaMigratoria(){
		List<ItemCatFormaMigratoria> listCatFormaMigratoria = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatFormaMigratoria, Integer> daoCatFormaMigratoria = helper.getSimpleDataDaoCatFormaMigratoria().queryBuilder();
			if (helper.getDaoCatFormaMigratoria().countOf() > 0) {
				listCatFormaMigratoria = daoCatFormaMigratoria.query();
			}else {
				listCatFormaMigratoria = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatFormaMigratoria;
	}
	
	public List<ItemCatGradoEscolar> getDataCatGradoEscolar(){
		List<ItemCatGradoEscolar> listCatGradoEscolar = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatGradoEscolar, Integer> daoCatGradoEscolar = helper.getSimpleDataDaoCatGradoEscolar().queryBuilder();
			if (helper.getDaoCatGradoEscolar().countOf() > 0) {
				listCatGradoEscolar = daoCatGradoEscolar.query();
			}else {
				listCatGradoEscolar = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatGradoEscolar;
	}
	
	public List<ItemCatLocalidades> getDataCatLocalidades(){
		List<ItemCatLocalidades> listCatLocalidades = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatLocalidades, Integer> daoCatLocalidades = helper.getSimpleDataDaoCatLocalidades().queryBuilder();
			if (helper.getDaoCatLocalidades().countOf() > 0) {
				listCatLocalidades = daoCatLocalidades.query();
			}else {
				listCatLocalidades = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatLocalidades;
	}
	
	public List<ItemCatLocalidades> getDataTop10CatLocalidades(){
		List<ItemCatLocalidades> listTop10CatLocalidades = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatLocalidades, Integer> daoTop10CatLocalidades = helper.getSimpleDataDaoCatLocalidades().queryBuilder();
			daoTop10CatLocalidades.limit((long)10);
			listTop10CatLocalidades = daoTop10CatLocalidades.query();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTop10CatLocalidades;
	}
	
	public List<ItemCatNacionalidad> getDataCatNacionalidad(){
		List<ItemCatNacionalidad> listCatNacionalidad = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatNacionalidad, Integer> daoCatNacionalidad = helper.getSimpleDataDaoCatNacionalidad().queryBuilder();
			if (helper.getDaoCatNacionalidad().countOf() > 0) {
				listCatNacionalidad = daoCatNacionalidad.query();
			}else {
				listCatNacionalidad = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatNacionalidad;
	}
	
	public List<ItemCatNombreCarrera> getDataCatNombreCarrera(){
		List<ItemCatNombreCarrera> listCatNombreCarrera = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatNombreCarrera, Integer> daoCatNombreCarrera = helper.getSimpleDataDaoCatNombreCarrera().queryBuilder();
			if (helper.getDaoCatNombreCarrera().countOf() > 0) {
				listCatNombreCarrera = daoCatNombreCarrera.query();
			}else {
				listCatNombreCarrera = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatNombreCarrera;
	}
	
	public List<ItemCatProfesiones> getDataCatProfesiones(){
		List<ItemCatProfesiones> listCatProfesiones = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatProfesiones, Integer> daoCatProfesiones = helper.getSimpleDataDaoCatProfesiones().queryBuilder();
			if (helper.getDaoCatProfesiones().countOf() > 0) {
				listCatProfesiones = daoCatProfesiones.query();
			}else {
				listCatProfesiones = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatProfesiones;
	}
	
	public List<ItemCatSexo> getDataCatSexo(){
		List<ItemCatSexo> listCatSexo = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatSexo, Integer> daoCatSexo = helper.getSimpleDataDaoCatSexo().queryBuilder();
			if (helper.getDaoCatSexo().countOf() > 0) {
				listCatSexo = daoCatSexo.query();
			}else {
				listCatSexo = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatSexo;
	}
	
	public List<ItemCatTipoFinanciamiento> getDataCatTipoFinanciamiento(){
		List<ItemCatTipoFinanciamiento> listCatTipoFinanciamiento = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatTipoFinanciamiento, Integer> daoCatTipoFinanciamiento = helper.getSimpleDataDaoCatTipoFinanciamiento().queryBuilder();
			if (helper.getDaoCatTipoFinanciamiento().countOf() > 0) {
				listCatTipoFinanciamiento = daoCatTipoFinanciamiento.query();
			}else {
				listCatTipoFinanciamiento = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatTipoFinanciamiento;
	}
	
	public List<ItemCatTipoProducto> getDataCatTipoProducto(){
		List<ItemCatTipoProducto> listCatTipoProducto = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatTipoProducto, Integer> daoCatTipoProducto = helper.getSimpleDataDaoCatTipoProducto().queryBuilder();
			if (helper.getDaoCatTipoProducto().countOf() > 0) {
				listCatTipoProducto = daoCatTipoProducto.query();
			}else {
				listCatTipoProducto = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatTipoProducto;
	}
	
	public List<ItemCatTipoVivienda> getDataCatTipoVivienda(){
		List<ItemCatTipoVivienda> listCatTipoVivienda = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatTipoVivienda, Integer> daoCatTipoVivienda = helper.getSimpleDataDaoCatTipoVivienda().queryBuilder();
			if (helper.getDaoCatTipoVivienda().countOf() > 0) {
				listCatTipoVivienda = daoCatTipoVivienda.query();
			}else {
				listCatTipoVivienda = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatTipoVivienda;
	}
	
	public List<ItemCatUniversidad> getDataCatUniversidad(){
		List<ItemCatUniversidad> listCatUniversidad = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatUniversidad, Integer> daoCatUniversidad = helper.getSimpleDataDaoCatUniversidad().queryBuilder();
			if (helper.getDaoCatUniversidad().countOf() > 0) {
				listCatUniversidad = daoCatUniversidad.query();
			}else {
				listCatUniversidad = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatUniversidad;
	}
	/*********
	 * Agregar Metodo para eliminar todos los catalogos
	 */
	public void deleteCatalogos() {
		try {
			RuntimeExceptionDao<ItemCatBancos, Integer> daoCatBancos = getSimpleDataDaoCatBancos();
			List<ItemCatBancos> listCatBancos = daoCatBancos.queryForAll();
			daoCatBancos.delete(listCatBancos);
			RuntimeExceptionDao<ItemCatEstadoCivil, Integer> daoCatEstadoCivil = getSimpleDataDaoCatEstadoCivil();
			List<ItemCatEstadoCivil> listCatEstadoCivil = daoCatEstadoCivil.queryForAll();
			daoCatEstadoCivil.delete(listCatEstadoCivil);
			RuntimeExceptionDao<ItemCatFormaMigratoria, Integer> daoCatFormaMigratoria = getSimpleDataDaoCatFormaMigratoria();
			List<ItemCatFormaMigratoria> listCatFormaMigratoria = daoCatFormaMigratoria.queryForAll();
			daoCatFormaMigratoria.delete(listCatFormaMigratoria);
			RuntimeExceptionDao<ItemCatGradoEscolar, Integer> daoCatGradoEscolar = getSimpleDataDaoCatGradoEscolar();
			List<ItemCatGradoEscolar> listCatGradoEscolar = daoCatGradoEscolar.queryForAll();
			daoCatGradoEscolar.delete(listCatGradoEscolar);
			RuntimeExceptionDao<ItemCatLocalidades, Integer> daoCatLocalidades = getSimpleDataDaoCatLocalidades();
			List<ItemCatLocalidades> listCatLocalidades = daoCatLocalidades.queryForAll();
			daoCatLocalidades.delete(listCatLocalidades);
			RuntimeExceptionDao<ItemCatNacionalidad, Integer> daoCatNacionalidad = getSimpleDataDaoCatNacionalidad();
			List<ItemCatNacionalidad> listCatNacionalidad = daoCatNacionalidad.queryForAll();
			daoCatNacionalidad.delete(listCatNacionalidad);
			RuntimeExceptionDao<ItemCatNombreCarrera, Integer> daoCatNombreCarrera = getSimpleDataDaoCatNombreCarrera();
			List<ItemCatNombreCarrera> listCatNombreCarrera = daoCatNombreCarrera.queryForAll();
			daoCatNombreCarrera.delete(listCatNombreCarrera);
			RuntimeExceptionDao<ItemCatProfesiones, Integer> daoCatProfesiones = getSimpleDataDaoCatProfesiones();
			List<ItemCatProfesiones> listCatProfesiones = daoCatProfesiones.queryForAll();
			daoCatProfesiones.delete(listCatProfesiones);
			RuntimeExceptionDao<ItemCatSexo, Integer> daoCatSexo = getSimpleDataDaoCatSexo();
			List<ItemCatSexo> listCatSexo = daoCatSexo.queryForAll();
			daoCatSexo.delete(listCatSexo);
			RuntimeExceptionDao<ItemCatTipoFinanciamiento, Integer> daoCatTipoFinanciamiento = getSimpleDataDaoCatTipoFinanciamiento();
			List<ItemCatTipoFinanciamiento> listCatTipoFinanciamiento = daoCatTipoFinanciamiento.queryForAll();
			daoCatTipoFinanciamiento.delete(listCatTipoFinanciamiento);
			RuntimeExceptionDao<ItemCatTipoProducto, Integer> daoCatTipoProducto = getSimpleDataDaoCatTipoProducto();
			List<ItemCatTipoProducto> listCatTipoProducto = daoCatTipoProducto.queryForAll();
			daoCatTipoProducto.delete(listCatTipoProducto);
			RuntimeExceptionDao<ItemCatTipoVivienda, Integer> daoCatTipoVivienda = getSimpleDataDaoCatTipoVivienda();
			List<ItemCatTipoVivienda> listCatTipoVivienda = daoCatTipoVivienda.queryForAll();
			daoCatTipoVivienda.delete(listCatTipoVivienda);
			RuntimeExceptionDao<ItemCatUniversidad, Integer> daoCatUniversidad = getSimpleDataDaoCatUniversidad();
			List<ItemCatUniversidad> listCatUniversidad = daoCatUniversidad.queryForAll();
			daoCatUniversidad.delete(listCatUniversidad);
		} catch (Exception e) {
			Log.d("E eliminando catalogos", e.getMessage());
		}
	}
	
	//public int addDataCatBancos(ItemCatBancos itemCatBancos) {
	public void addDataCatBancos(ItemCatBancos itemCatBancos) {
		RuntimeExceptionDao<ItemCatBancos, Integer> daoCatBancos = getSimpleDataDaoCatBancos();
		daoCatBancos.create(itemCatBancos);
	}
	
	public void addDataCatEstadoCivil(ItemCatEstadoCivil itemCatEstadoCivil) {
		RuntimeExceptionDao<ItemCatEstadoCivil, Integer> daoCatEstadoCivil = getSimpleDataDaoCatEstadoCivil();
		daoCatEstadoCivil.create(itemCatEstadoCivil);
	}
	
	public void addDataCatFormaMigratoria(ItemCatFormaMigratoria itemCatFormaMigratoria) {
		RuntimeExceptionDao<ItemCatFormaMigratoria, Integer> daoCatFormaMigratoria = getSimpleDataDaoCatFormaMigratoria();
		daoCatFormaMigratoria.create(itemCatFormaMigratoria);
	}
	
	public void addDataCatGradoEscolar(ItemCatGradoEscolar itemCatGradoEscolar) {
		RuntimeExceptionDao<ItemCatGradoEscolar, Integer> daoCatGradoEscolar = getSimpleDataDaoCatGradoEscolar();
		daoCatGradoEscolar.create(itemCatGradoEscolar);
	}
	
	public void addDataCatLocalidades(ItemCatLocalidades itemCatLocalidades) {
		RuntimeExceptionDao<ItemCatLocalidades, Integer> daoCatLocalidades = getSimpleDataDaoCatLocalidades();
		daoCatLocalidades.create(itemCatLocalidades);
	}
	
	public void addDataCatNacionalidad(ItemCatNacionalidad itemCatNacionalidad) {
		RuntimeExceptionDao<ItemCatNacionalidad, Integer> daoCatNacionalidad = getSimpleDataDaoCatNacionalidad();
		daoCatNacionalidad.create(itemCatNacionalidad);
	}
	
	public void addDataCatNombreCarrera(ItemCatNombreCarrera itemCatNombreCarrera) {
		RuntimeExceptionDao<ItemCatNombreCarrera, Integer> daoCatNombreCarrera = getSimpleDataDaoCatNombreCarrera();
		daoCatNombreCarrera.create(itemCatNombreCarrera);
	}
	
	public void addDataCatProfesiones(ItemCatProfesiones itemCatProfesiones) {
		RuntimeExceptionDao<ItemCatProfesiones, Integer> daoCatProfesiones = getSimpleDataDaoCatProfesiones();
		daoCatProfesiones.create(itemCatProfesiones);
	}
	
	public void addDataCatSexo(ItemCatSexo itemCatSexo) {
		RuntimeExceptionDao<ItemCatSexo, Integer> daoCatSexo = getSimpleDataDaoCatSexo();
		daoCatSexo.create(itemCatSexo);
	}
	
	public void addDataCatTipoFinanciamiento(ItemCatTipoFinanciamiento itemCatTipoFinanciamiento) {
		RuntimeExceptionDao<ItemCatTipoFinanciamiento, Integer> daoCatTipoFinanciamiento = getSimpleDataDaoCatTipoFinanciamiento();
		daoCatTipoFinanciamiento.create(itemCatTipoFinanciamiento);
	}
	
	public void addDataCatTipoProducto(ItemCatTipoProducto itemCatTipoProducto) {
		RuntimeExceptionDao<ItemCatTipoProducto, Integer> daoCatTipoProducto = getSimpleDataDaoCatTipoProducto();
		daoCatTipoProducto.create(itemCatTipoProducto);
	}
	
	public void addDataCatTipoVivienda(ItemCatTipoVivienda itemCatTipoVivienda) {
		RuntimeExceptionDao<ItemCatTipoVivienda, Integer> daoCatTipoVivienda = getSimpleDataDaoCatTipoVivienda();
		daoCatTipoVivienda.create(itemCatTipoVivienda);
	}
	
	public void addDataCatUniversidad(ItemCatUniversidad itemCatUniversidad) {
		RuntimeExceptionDao<ItemCatUniversidad, Integer> daoCatUniversidad = getSimpleDataDaoCatUniversidad();
		daoCatUniversidad.create(itemCatUniversidad);
	}
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTableIfNotExists(connectionSource, ItemCatBancos.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatEstadoCivil.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatFormaMigratoria.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatGradoEscolar.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatLocalidades.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatNacionalidad.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatNombreCarrera.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatProfesiones.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatSexo.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatTipoFinanciamiento.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatTipoProducto.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatTipoVivienda.class);
			TableUtils.createTableIfNotExists(connectionSource, ItemCatUniversidad.class);
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			List<String> allSQL = new ArrayList<String>();
			switch (oldVersion) {
			case 1:
			}
			for (String sql : allSQL) {
				db.execSQL(sql);
			}
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}
	
	@Override
	public void close() {
		super.close();
	}

	public List<ItemCatLocalidades> getDatosWithCP(String cp){
		List<ItemCatLocalidades> listCatCPs = null;
		try {
			helper = new DataBaseHelper(_mContext);
			QueryBuilder<ItemCatLocalidades, Integer> daoCatWithCP = helper.getSimpleDataDaoCatLocalidades().queryBuilder();
			//daoCatWithCP.query().clear();
			daoCatWithCP.where().eq("cp", cp);
			daoCatWithCP.orderBy("idLocalidad", true);
			listCatCPs = daoCatWithCP.query();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCatCPs;
	}
	
	public String getIdLocWithCp(String cp, int idColonia, int idMunicipio, int idEstado) {
		String idLoc = "";
		List<ItemCatLocalidades> list = null;
		try {
			QueryBuilder<ItemCatLocalidades, Integer> dao = getSimpleDataDaoCatLocalidades().queryBuilder();
			dao.where().eq("cp", cp)
			.and().eq("idColonia", idColonia)
			.and().eq("idMunicipio", idMunicipio)
			.and().eq("idEstado", idEstado);
			list = dao.query();
			if (list != null) {
				idLoc = list.get(0).getIdLocalidad().toString();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idLoc;
	}
	
}
