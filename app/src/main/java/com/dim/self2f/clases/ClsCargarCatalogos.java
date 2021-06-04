package com.dim.self2f.clases;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.dim.self2f.db.DataBaseHelper;
import com.dim.self2f.db.ItemCatBancos;
import com.dim.self2f.db.ItemCatEstadoCivil;
import com.dim.self2f.db.ItemCatFormaMigratoria;
import com.dim.self2f.db.ItemCatGradoEscolar;
import com.dim.self2f.db.ItemCatLocalidades;
import com.dim.self2f.db.ItemCatNacionalidad;
import com.dim.self2f.db.ItemCatNombreCarrera;
import com.dim.self2f.db.ItemCatProfesiones;
import com.dim.self2f.db.ItemCatSexo;
import com.dim.self2f.db.ItemCatTipoFinanciamiento;
import com.dim.self2f.Inicio;
import com.dim.self2f.db.*;

public class ClsCargarCatalogos {
	DataBaseHelper helper;
	List<ItemCatBancos> listCatbancos;
	ItemCatBancos itemCatBancos;
	List<ItemCatEstadoCivil> listCatEstadoCivil;
	ItemCatEstadoCivil itemCatEstadoCivil;
	List<ItemCatFormaMigratoria> listCatFormaMigratoria;
	ItemCatFormaMigratoria itemCatFormaMigratoria;
	List<ItemCatGradoEscolar> listCatGradoEscolar;
	ItemCatGradoEscolar itemCatGradoEscolar;
	List<ItemCatLocalidades> listCatLocalidades;
	ItemCatLocalidades itemCatLocalidades;
	List<ItemCatNacionalidad> listCatNacionalidad;
	ItemCatNacionalidad itemCatNacionalidad;
	List<ItemCatNombreCarrera> listCatNombreCarrera;
	ItemCatNombreCarrera itemCatNombreCarrera;
	List<ItemCatProfesiones> listCatProfesiones;
	ItemCatProfesiones itemCatProfesiones;
	List<ItemCatSexo> listCatSexo;
	ItemCatSexo itemCatSexo;
	List<ItemCatTipoFinanciamiento> listCatTipoFinanciamiento;
	ItemCatTipoFinanciamiento itemCatTipoFinanciamiento;
	List<ItemCatTipoProducto> listCatTipoProducto;
	ItemCatTipoProducto itemCatTipoProducto;
	List<ItemCatTipoVivienda> listCatTipoVivienda;
	ItemCatTipoVivienda itemCatTipoVivienda;
	List<ItemCatUniversidad> listCatUniversidad;
	ItemCatUniversidad itemCatUniversidad;
	@SuppressWarnings("unused")
	private final Inicio activity;
	private final ProgressDialog dialogo;
	public ClsCargarCatalogos(Inicio activity) {
		this.activity = activity;
		helper = new DataBaseHelper(activity);
		dialogo = new ProgressDialog(activity);
		cargarCatalogos();
	}
	
	public void cargarCatalogos() {
		cargaCatalogoBancos();
		Log.d("Inicio.- ", "cargandoCatalogos");
	}
	
	public void cargaCatalogoBancos() {
		listCatbancos = new ArrayList<ItemCatBancos>();
		listCatbancos = helper.getDataCatBancos();
		if (listCatbancos == null) {
			new catalogoBancosTask().execute();	
		}else {
			cargaCatalogoEstadoCivil();
		}
	}
	
	private class catalogoBancosTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			dialogo.setMessage("Cargando catalogos...");
			dialogo.setCancelable(false);
			dialogo.setCanceledOnTouchOutside(false);
			dialogo.show();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean lleno = false;
			LlenaCatalogoBancos cBancos = new LlenaCatalogoBancos();
			lleno = cBancos.connection();
			return lleno;
		}
		
		protected void onPostExecute(Boolean isCatBancosLleno) {
			if (isCatBancosLleno) {
				cargaCatalogoEstadoCivil();
			}
		}
	}
	
	public class LlenaCatalogoBancos{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoBancos";
	    private static final String METHOD_NAME = "ConsultarCatalogoBancos";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean catBancosLleno = false;
		public LlenaCatalogoBancos() {
		}
		public Boolean connection() {
			/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatBancos = new ItemCatBancos();
						itemCatBancos.setIdBanco(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatBancos.setValor(Integer.parseInt(sResult2.getProperty(1).toString()));
						itemCatBancos.setDescripcion(sResult2.getProperty(2).toString());
						helper.addDataCatBancos(itemCatBancos);
					}
					catBancosLleno = true;
				}else {
					catBancosLleno = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for(int i = 0; i < 5; i++) {
				itemCatBancos = new ItemCatBancos();
				itemCatBancos.setIdBanco(i+1);
				itemCatBancos.setValor(i+1);
				itemCatBancos.setDescripcion("Banco: " + (i+1));
				helper.addDataCatBancos(itemCatBancos);
			}
			catBancosLleno = true;
	    	return catBancosLleno;
		}
	}
	
	public void cargaCatalogoEstadoCivil() {
		listCatEstadoCivil = new ArrayList<ItemCatEstadoCivil>();
		listCatEstadoCivil = helper.getDataCatEstadoCivil();
		if (listCatEstadoCivil == null) {
			new catalogoEstadoCivilTask().execute();	
		}else {
			cargaCatFormaMigratoria();
		}
	}
	
	private class catalogoEstadoCivilTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean llenoCatEstadoCivl = false;
			LlenaCatalogoEstadoCivil cEstadoCivil = new LlenaCatalogoEstadoCivil();
			llenoCatEstadoCivl = cEstadoCivil.connection();
			return llenoCatEstadoCivl;
		}
		
		protected void onPostExecute(Boolean isCatEstadoCivilLleno) {
			if (isCatEstadoCivilLleno) {
				cargaCatFormaMigratoria();
			}
		}
	}
	
	public class LlenaCatalogoEstadoCivil{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoEstadoCivil";
	    private static final String METHOD_NAME = "ConsultarCatalogoEstadoCivil";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatEstadoCivl = false;
		public LlenaCatalogoEstadoCivil() {
		}
		public Boolean connection() {
			/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatEstadoCivil = new ItemCatEstadoCivil();
						itemCatEstadoCivil.setIdEstadoCivil(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatEstadoCivil.setValor(sResult2.getProperty(1).toString());
						itemCatEstadoCivil.setDescripcion(sResult2.getProperty(2).toString());
						helper.addDataCatEstadoCivil(itemCatEstadoCivil);
					}
					llenoCatEstadoCivl = true;
				}else {
					llenoCatEstadoCivl = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatEstadoCivil = new ItemCatEstadoCivil();
				itemCatEstadoCivil.setIdEstadoCivil(i+1);
				itemCatEstadoCivil.setValor("Estado Civil: " + (i+1));
				itemCatEstadoCivil.setDescripcion("Descripcion: " + (i+1));
				helper.addDataCatEstadoCivil(itemCatEstadoCivil);
			}
			llenoCatEstadoCivl = true;
	    	return llenoCatEstadoCivl;
		}
	}
	
	public void cargaCatFormaMigratoria() {
		listCatFormaMigratoria = new ArrayList<ItemCatFormaMigratoria>();
		listCatFormaMigratoria = helper.getDataCatFormaMigratoria();
		if (listCatFormaMigratoria == null) {
			new catalogoFormaMigratoriaTask().execute();	
		}else {
			cargaCatGradoEscolar();
		}
	}
	
	private class catalogoFormaMigratoriaTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatalogoFormaMigratoria = false;
			LlenaCatalogoFormaMigratoria cFormaMigratoria = new LlenaCatalogoFormaMigratoria();
			LlenaCatalogoFormaMigratoria = cFormaMigratoria.connection();
			return LlenaCatalogoFormaMigratoria;
		}
		
		protected void onPostExecute(Boolean isCatFormaMigratoriaLleno) {
			if (isCatFormaMigratoriaLleno) {
				cargaCatGradoEscolar();
			}
		}
	}
	
	public class LlenaCatalogoFormaMigratoria{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoFormaMigratoria";
	    private static final String METHOD_NAME = "ConsultarCatalogoFormaMigratoria";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatFormaMigratoria = false;
		public LlenaCatalogoFormaMigratoria() {
		}
		public Boolean connection() {
		/*	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatFormaMigratoria = new ItemCatFormaMigratoria();
						itemCatFormaMigratoria.setIdForma(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatFormaMigratoria.setValor(sResult2.getProperty(1).toString());
						itemCatFormaMigratoria.setFormaMigratoria(sResult2.getProperty(2).toString());
						helper.addDataCatFormaMigratoria(itemCatFormaMigratoria);
					}
					llenoCatFormaMigratoria = true;
				}else {
					llenoCatFormaMigratoria = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 2; i++) {
				itemCatFormaMigratoria = new ItemCatFormaMigratoria();
				itemCatFormaMigratoria.setIdForma(i+1);
				itemCatFormaMigratoria.setValor("Valor: " + (i+1));
				itemCatFormaMigratoria.setFormaMigratoria("Forma migratoria: " + (i+1));
				helper.addDataCatFormaMigratoria(itemCatFormaMigratoria);
			}
			llenoCatFormaMigratoria = true;
	    	return llenoCatFormaMigratoria;
		}
	}
	
	public void cargaCatGradoEscolar() {
		listCatGradoEscolar = new ArrayList<ItemCatGradoEscolar>();
		listCatGradoEscolar = helper.getDataCatGradoEscolar();
		if (listCatGradoEscolar == null) {
			new catalogoGradoEscolarTask().execute();	
		}else {
			cargaCatLocalidades();
		}
	}
	
	private class catalogoGradoEscolarTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatGradoEscolar = false;
			LlenaCatGradoEscolar cGradoEscolar = new LlenaCatGradoEscolar();
			LlenaCatGradoEscolar = cGradoEscolar.connection();
			return LlenaCatGradoEscolar;
		}
		
		protected void onPostExecute(Boolean isCatFormaMigratoriaLleno) {
			if (isCatFormaMigratoriaLleno) {
				cargaCatLocalidades();
			}
		}
	}
	
	public class LlenaCatGradoEscolar{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoGradoEscolar";
	    private static final String METHOD_NAME = "ConsultarCatalogoGradoEscolar";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatGradoEscolar = false;
		public LlenaCatGradoEscolar() {
		}
		public Boolean connection() {
		/*	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatGradoEscolar = new ItemCatGradoEscolar();
						itemCatGradoEscolar.setIdGrado(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatGradoEscolar.setValor(sResult2.getProperty(1).toString());
						itemCatGradoEscolar.setGrado(sResult2.getProperty(2).toString());
						helper.addDataCatGradoEscolar(itemCatGradoEscolar);
					}
					llenoCatGradoEscolar = true;
				}else {
					llenoCatGradoEscolar = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatGradoEscolar = new ItemCatGradoEscolar();
				itemCatGradoEscolar.setIdGrado(i+1);
				itemCatGradoEscolar.setValor("Valor: " + (i+1));
				itemCatGradoEscolar.setGrado("Grado: " + (i+1));
				helper.addDataCatGradoEscolar(itemCatGradoEscolar);
			}
			llenoCatGradoEscolar = true;
	    	return llenoCatGradoEscolar;
		}
	}
	
	private void cargaCatLocalidades() {
		listCatLocalidades = new ArrayList<ItemCatLocalidades>();
		listCatLocalidades = helper.getDataTop10CatLocalidades();
		if (listCatLocalidades == null) {
			new catalogoLocalidadesTask().execute();	
		}else {
			cargaCatNacionalidad();
		}
	}
	
	private class catalogoLocalidadesTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatLocalidades = false;
			LlenaCatLocalidades cLocalidades = new LlenaCatLocalidades();
			LlenaCatLocalidades = cLocalidades.connection();
			return LlenaCatLocalidades;
		}
		
		protected void onPostExecute(Boolean isCatLocalidadesLleno) {
			if (isCatLocalidadesLleno) {
				cargaCatNacionalidad();
			}
		}
	}
	
	public class LlenaCatLocalidades{
		/*private static final String urlJson = "http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx/ConsultarCatalogoLocalidades";
		private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoLocalidades";
	    private static final String METHOD_NAME = "ConsultarCatalogoLocalidades";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatLocalidades = false;
	    StringBuilder contenido;
		public LlenaCatLocalidades() {
		}
		
		public Boolean connection() {
			/*try {
				HttpClient httpCliente = new DefaultHttpClient();
				HttpPost post = new HttpPost("http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx/ConsultarCatalogoLocalidades");
				post.setHeader("content-type", "application/json");
				HttpResponse resp = httpCliente.execute(post);
				StatusLine statusLine = resp.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					HttpEntity entityResp = resp.getEntity();
					InputStream content = entityResp.getContent();
					try {
						JsonFactory jsonFactory = new MappingJsonFactory();
						JsonParser jp = jsonFactory.createJsonParser(content);
						JsonToken token;
						token = jp.nextToken();
						if (token != JsonToken.START_OBJECT) {
						}
						while (token != JsonToken.END_OBJECT) {
							token = jp.nextToken();
							if (token == JsonToken.START_ARRAY) {
								while (jp.nextToken() != JsonToken.END_ARRAY) {
									itemCatLocalidades = new ItemCatLocalidades();
									JsonNode node = jp.readValueAsTree();
									itemCatLocalidades.setIdLocalidad(node.get("IDLocalidad").getTextValue());
									itemCatLocalidades.setCp(node.get("CP").getTextValue());
									itemCatLocalidades.setIdColonia(node.get("IDColonia").getIntValue());
									itemCatLocalidades.setColonia(node.get("Colonia").getTextValue());
									itemCatLocalidades.setIdMunicipio(node.get("IDMunicipio").getIntValue());
									itemCatLocalidades.setMunicipio(node.get("Municipio").getTextValue());
									itemCatLocalidades.setIdEstado(node.get("IDEstado").getIntValue());
									itemCatLocalidades.setEstado(node.get("Estado").getTextValue());
									helper.addDataCatLocalidades(itemCatLocalidades);
								}
								llenoCatLocalidades = true;	
							}else {
								jp.skipChildren();
							}
						}
					} catch (Exception e) {
						llenoCatLocalidades = false;
						e.printStackTrace();
					}
				}else {
					llenoCatLocalidades = false;
				}
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}*/
			for(int i = 0; i < 20; i++){
				itemCatLocalidades = new ItemCatLocalidades();
				itemCatLocalidades.setIdLocalidad(String.valueOf(i+1));
				itemCatLocalidades.setCp("Cp: " + (i+1));
				itemCatLocalidades.setIdColonia((i+1));
				itemCatLocalidades.setColonia("Colonia: " + (i+1));
				itemCatLocalidades.setIdMunicipio(i+1);
				itemCatLocalidades.setMunicipio("Municipio: " + (i+1));
				itemCatLocalidades.setIdEstado((i+1));
				itemCatLocalidades.setEstado("Estado: " + (i+1));
				helper.addDataCatLocalidades(itemCatLocalidades);
			}
			llenoCatLocalidades = true;
	    	return llenoCatLocalidades;
		}
	}
	
	public void cargaCatNacionalidad() {
		listCatNacionalidad = new ArrayList<ItemCatNacionalidad>();
		listCatNacionalidad = helper.getDataCatNacionalidad();
		if (listCatNacionalidad == null) {
			new catalogoNacionalidadTask().execute();	
		}else {
			cargaCatNombreCarrera();
		}
	}
	
	private class catalogoNacionalidadTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatNacionalidad = false;
			LlenaCatNacionalidad cNacionalidad = new LlenaCatNacionalidad();
			LlenaCatNacionalidad = cNacionalidad.connection();
			return LlenaCatNacionalidad;
		}
		
		protected void onPostExecute(Boolean isCatNacionalidadLleno) {
			if (isCatNacionalidadLleno) {
				cargaCatNombreCarrera();
			}
		}
	}
	
	public class LlenaCatNacionalidad{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoNacionalidad";
	    private static final String METHOD_NAME = "ConsultarCatalogoNacionalidad";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatNacionalidad = false;
		public LlenaCatNacionalidad() {
		}
		public Boolean connection() {
			/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatNacionalidad = new ItemCatNacionalidad();
						itemCatNacionalidad.setIdNacionalidad(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatNacionalidad.setValor(sResult2.getProperty(1).toString());
						itemCatNacionalidad.setNacionalidad(sResult2.getProperty(2).toString());
						helper.addDataCatNacionalidad(itemCatNacionalidad);
					}
					llenoCatNacionalidad = true;
				}else {
					llenoCatNacionalidad = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 2; i++) {
				itemCatNacionalidad = new ItemCatNacionalidad();
				itemCatNacionalidad.setIdNacionalidad(i+1);
				itemCatNacionalidad.setValor(String.valueOf(i+1));
				itemCatNacionalidad.setNacionalidad("Nacionalidad: " + (i+1));
				helper.addDataCatNacionalidad(itemCatNacionalidad);
			}
			llenoCatNacionalidad = true;
	    	return llenoCatNacionalidad;
		}
	}
	
	public void cargaCatNombreCarrera() {
		listCatNombreCarrera = new ArrayList<ItemCatNombreCarrera>();
		listCatNombreCarrera = helper.getDataCatNombreCarrera();
		if (listCatNombreCarrera == null) {
			new catalogoNombreCarreraTask().execute();	
		}else {
			cargaCatProfesiones();
		}
	}
	
	private class catalogoNombreCarreraTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatNombreCarrera = false;
			LlenaCatNombreCarrera cNombreCarrera = new LlenaCatNombreCarrera();
			LlenaCatNombreCarrera = cNombreCarrera.connection();
			return LlenaCatNombreCarrera;
		}
		
		protected void onPostExecute(Boolean isCatNombreCarreraLleno) {
			if (isCatNombreCarreraLleno) {
				cargaCatProfesiones();
			}
		}
	}
	
	public class LlenaCatNombreCarrera{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoNombreCarrera";
	    private static final String METHOD_NAME = "ConsultarCatalogoNombreCarrera";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatNombreCarrera = false;
		public LlenaCatNombreCarrera() {
		}
		public Boolean connection() {
			/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatNombreCarrera = new ItemCatNombreCarrera();
						itemCatNombreCarrera.setIdNombreCarrera(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatNombreCarrera.setValor(sResult2.getProperty(1).toString());
						itemCatNombreCarrera.setNombreCarrera(sResult2.getProperty(2).toString());
						helper.addDataCatNombreCarrera(itemCatNombreCarrera);
					}
					llenoCatNombreCarrera = true;
				}else {
					llenoCatNombreCarrera = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatNombreCarrera = new ItemCatNombreCarrera();
				itemCatNombreCarrera.setIdNombreCarrera(i+1);
				itemCatNombreCarrera.setValor("Valor: " + (i+1));
				itemCatNombreCarrera.setNombreCarrera("Carrera: " + (i+1));
				helper.addDataCatNombreCarrera(itemCatNombreCarrera);
			}
			llenoCatNombreCarrera = true;
	    	return llenoCatNombreCarrera;
		}
	}
	
	public void cargaCatProfesiones() {
		listCatProfesiones = new ArrayList<ItemCatProfesiones>();
		listCatProfesiones = helper.getDataCatProfesiones();
		if (listCatProfesiones == null) {
			new catalogoProfesionesTask().execute();	
		}else {
			cargaCatSexo();
		}
	}
	
	private class catalogoProfesionesTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatProfesiones = false;
			LlenaCatProfesiones cProfesiones = new LlenaCatProfesiones();
			LlenaCatProfesiones = cProfesiones.connection();
			return LlenaCatProfesiones;
		}
		
		protected void onPostExecute(Boolean isCatProfesionesLleno) {
			if (isCatProfesionesLleno) {
				cargaCatSexo();
			}
		}
	}
	
	public class LlenaCatProfesiones{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoProfesiones";
	    private static final String METHOD_NAME = "ConsultarCatalogoProfesiones";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatProfesiones = false;
		public LlenaCatProfesiones() {
		}
		public Boolean connection() {
			/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatProfesiones = new ItemCatProfesiones();
						itemCatProfesiones.setIdProfesion(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatProfesiones.setValor(sResult2.getProperty(1).toString());
						itemCatProfesiones.setProfesion(sResult2.getProperty(2).toString());
						helper.addDataCatProfesiones(itemCatProfesiones);
					}
					llenoCatProfesiones = true;
				}else {
					llenoCatProfesiones = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatProfesiones = new ItemCatProfesiones();
				itemCatProfesiones.setIdProfesion(i+1);
				itemCatProfesiones.setValor("Valor: " + (i+1));
				itemCatProfesiones.setProfesion("Profesión: " + (i+1));
				helper.addDataCatProfesiones(itemCatProfesiones);
			}
			llenoCatProfesiones = true;
	    	return llenoCatProfesiones;
		}
	}
	
	public void cargaCatSexo() {
		listCatSexo = new ArrayList<ItemCatSexo>();
		listCatSexo = helper.getDataCatSexo();
		if (listCatSexo == null) {
			new catalogoSexoTask().execute();	
		}else {
			cargaCatTipoFinanciamiento();
		}
		
	}
	
	private class catalogoSexoTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatSexo = false;
			LlenaCatSexo cSexo = new LlenaCatSexo();
			LlenaCatSexo = cSexo.connection();
			return LlenaCatSexo;
		}
		
		protected void onPostExecute(Boolean isCatSexoLleno) {
			if (isCatSexoLleno) {
				cargaCatTipoFinanciamiento();
			}
		}
	}
	
	public class LlenaCatSexo{
	/*	private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoSexo";
	    private static final String METHOD_NAME = "ConsultarCatalogoSexo";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatSexo = false;
		public LlenaCatSexo() {
		}
		public Boolean connection() {
			/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatSexo = new ItemCatSexo();
						itemCatSexo.setIdSexo(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatSexo.setValor(sResult2.getProperty(1).toString());
						itemCatSexo.setSexo(sResult2.getProperty(2).toString());
						helper.addDataCatSexo(itemCatSexo);
					}
					llenoCatSexo = true;
				}else {
					llenoCatSexo = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 1; i++) {
				itemCatSexo = new ItemCatSexo();
				itemCatSexo.setIdSexo(i+1);
				itemCatSexo.setValor("Valor: " + (i+1));
				itemCatSexo.setSexo("Sexo: " + (i+1));
				helper.addDataCatSexo(itemCatSexo);
			}
			llenoCatSexo = true;
	    	return llenoCatSexo;
		}
	}
	
	public void cargaCatTipoFinanciamiento() {
		listCatTipoFinanciamiento = new ArrayList<ItemCatTipoFinanciamiento>();
		listCatTipoFinanciamiento = helper.getDataCatTipoFinanciamiento();
		if (listCatTipoFinanciamiento == null) {
			new catalogoTipoFinanciamientoTask().execute();	
		}else {
			cargaCatTipoProducto();
		}
	}
	
	private class catalogoTipoFinanciamientoTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatTipoFinanciamiento = false;
			LlenaCatTipoFinanciamiento cTipoFinanciamiento = new LlenaCatTipoFinanciamiento();
			LlenaCatTipoFinanciamiento = cTipoFinanciamiento.connection();
			return LlenaCatTipoFinanciamiento;
		}
		
		protected void onPostExecute(Boolean isCatTipoFinanciamientoLleno) {
			if (isCatTipoFinanciamientoLleno) {
				cargaCatTipoProducto();
			}
		}
	}
	
	public class LlenaCatTipoFinanciamiento{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoTipoFinanciamiento";
	    private static final String METHOD_NAME = "ConsultarCatalogoTipoFinanciamiento";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatTipoFinanciamiento = false;
		public LlenaCatTipoFinanciamiento() {
		}
		public Boolean connection() {
		/*	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatTipoFinanciamiento = new ItemCatTipoFinanciamiento();
						itemCatTipoFinanciamiento.setIdTipoFinanciamiento(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatTipoFinanciamiento.setValor(sResult2.getProperty(1).toString());
						itemCatTipoFinanciamiento.setTipoFinanciamiento(sResult2.getProperty(2).toString());
						helper.addDataCatTipoFinanciamiento(itemCatTipoFinanciamiento);
					}
					llenoCatTipoFinanciamiento = true;
				}else {
					llenoCatTipoFinanciamiento = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatTipoFinanciamiento = new ItemCatTipoFinanciamiento();
				itemCatTipoFinanciamiento.setIdTipoFinanciamiento(i+1);
				itemCatTipoFinanciamiento.setValor("Valor: " + (i+1));
				itemCatTipoFinanciamiento.setTipoFinanciamiento("Financiamiento: " + (i+1));
				helper.addDataCatTipoFinanciamiento(itemCatTipoFinanciamiento);
			}
			llenoCatTipoFinanciamiento = true;
	    	return llenoCatTipoFinanciamiento;
		}
	}
	
	public void cargaCatTipoProducto() {
		listCatTipoProducto = new ArrayList<ItemCatTipoProducto>();
		listCatTipoProducto = helper.getDataCatTipoProducto();
		if (listCatTipoProducto == null) {
			new catalogoTipoProductoTask().execute();	
		}else {
			cargaCatTipoVivienda();
		}		
	}
	
	private class catalogoTipoProductoTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatTipoProducto = false;
			LlenaCatTipoProducto cTipoProducto = new LlenaCatTipoProducto();
			LlenaCatTipoProducto = cTipoProducto.connection();
			return LlenaCatTipoProducto;
		}
		
		protected void onPostExecute(Boolean isCatTipoProductoLleno) {
			if (isCatTipoProductoLleno) {
				cargaCatTipoVivienda();
			}
		}
	}
	
	public class LlenaCatTipoProducto{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoTipoProducto";
	    private static final String METHOD_NAME = "ConsultarCatalogoTipoProducto";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatTipoProducto = false;
		public LlenaCatTipoProducto() {
		}
		public Boolean connection() {
			/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatTipoProducto = new ItemCatTipoProducto();
						
						itemCatTipoProducto.setIdProducto(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatTipoProducto.setValor(sResult2.getProperty(1).toString());
						itemCatTipoProducto.setProducto(sResult2.getProperty(2).toString());
						helper.addDataCatTipoProducto(itemCatTipoProducto);
					}
					llenoCatTipoProducto = true;
				}else {
					llenoCatTipoProducto = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatTipoProducto = new ItemCatTipoProducto();
				itemCatTipoProducto.setIdProducto(i+1);
				itemCatTipoProducto.setValor("Valor: " + (i+1));
				itemCatTipoProducto.setProducto("Producto: " + (i+1));
				helper.addDataCatTipoProducto(itemCatTipoProducto);
			}
			llenoCatTipoProducto = true;
	    	return llenoCatTipoProducto;
		}
	}
	
	public void cargaCatTipoVivienda(){
		listCatTipoVivienda = new ArrayList<ItemCatTipoVivienda>();
		listCatTipoVivienda = helper.getDataCatTipoVivienda();
		if (listCatTipoVivienda == null) {
			new catalogoTipoViviendaTask().execute();
		}else {
			cargaCatUniversidad();
		}		
	}
	
	private class catalogoTipoViviendaTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatTipoVivienda = false;
			LlenaCatTipoVivienda cTipoVivienda = new LlenaCatTipoVivienda();
			LlenaCatTipoVivienda = cTipoVivienda.connection();
			return LlenaCatTipoVivienda;
		}
		
		protected void onPostExecute(Boolean isCatTipoViviendaLleno) {
			if (isCatTipoViviendaLleno) {
				cargaCatUniversidad();
			}
		}
	}
	
	public class LlenaCatTipoVivienda{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoTipoVivienda";
	    private static final String METHOD_NAME = "ConsultarCatalogoTipoVivienda";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatTipoVivienda = false;
		public LlenaCatTipoVivienda() {
		}
		public Boolean connection() {
		/*	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatTipoVivienda = new ItemCatTipoVivienda();
						itemCatTipoVivienda.setIdTipoVivienda(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatTipoVivienda.setValor(sResult2.getProperty(1).toString());
						itemCatTipoVivienda.setTipoVivienda(sResult2.getProperty(2).toString());
						helper.addDataCatTipoVivienda(itemCatTipoVivienda);
					}
					llenoCatTipoVivienda = true;
				}else {
					llenoCatTipoVivienda = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatTipoVivienda = new ItemCatTipoVivienda();
				itemCatTipoVivienda.setIdTipoVivienda(i+1);
				itemCatTipoVivienda.setValor("Valor: " + (i+1));
				itemCatTipoVivienda.setTipoVivienda("Tipo: " + (i+1));
				helper.addDataCatTipoVivienda(itemCatTipoVivienda);
			}
			llenoCatTipoVivienda = true;
	    	return llenoCatTipoVivienda;
		}
	}
	
	public void cargaCatUniversidad() {
		listCatUniversidad = new ArrayList<ItemCatUniversidad>();
		listCatUniversidad = helper.getDataCatUniversidad();
		if (listCatUniversidad == null) {
			new catalogoUniversidadTask().execute();	
		}		
	}
	
	private class catalogoUniversidadTask extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			if (!dialogo.isShowing()) {
				dialogo.setMessage("Cargando catalogos...");
				dialogo.setCancelable(false);
				dialogo.setCanceledOnTouchOutside(false);
				dialogo.show();
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean LlenaCatUniversidad = false;
			LlenaCatUniversidad cUniversidad = new LlenaCatUniversidad();
			LlenaCatUniversidad = cUniversidad.connection();
			return LlenaCatUniversidad;
		}
		
		protected void onPostExecute(Boolean isCatUniversidadLleno) {
			if (isCatUniversidadLleno) {
				if (dialogo.isShowing()) {
					Log.d("Fin.-", "Termino carga catalogos");
					dialogo.dismiss();
				}
			}
		}
	}
	
	public class LlenaCatUniversidad{
		/*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarCatalogoUniversidad";
	    private static final String METHOD_NAME = "ConsultarCatalogoUniversidad";
	    private static final String NAMESPACE = "http://desarrollo.tarjetas.sistemassel.mx/wsSelMovil/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
	    boolean llenoCatUniversidad = false;
		public LlenaCatUniversidad() {
		}
		public Boolean connection() {
		/*	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
	    		httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapObject sResult = (SoapObject)envelope.getResponse();
				if (sResult != null) {
					for (int i = 0; i < sResult.getPropertyCount(); i++) {
						SoapObject sResult2 = (SoapObject)sResult.getProperty(i);
						itemCatUniversidad = new ItemCatUniversidad();
						itemCatUniversidad.setIdUniversidad(Integer.parseInt(sResult2.getProperty(0).toString()));
						itemCatUniversidad.setValor(sResult2.getProperty(1).toString());
						itemCatUniversidad.setUniversidad(sResult2.getProperty(2).toString());
						helper.addDataCatUniversidad(itemCatUniversidad);
					}
					llenoCatUniversidad = true;
				}else {
					llenoCatUniversidad = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			for (int i = 0; i < 5; i++) {
				itemCatUniversidad = new ItemCatUniversidad();
				itemCatUniversidad.setIdUniversidad(i+1);
				itemCatUniversidad.setValor("Valor: " + (i+1));
				itemCatUniversidad.setUniversidad("Universidad: " + (i+1));
				helper.addDataCatUniversidad(itemCatUniversidad);
			}
			llenoCatUniversidad = true;
	    	return llenoCatUniversidad;
		}
	}
}