package com.dim.self2f.clases;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

public class ClsCalcularRFCWs {
	String rfcCalculado = "";
	public ClsCalcularRFCWs(){
	}
	
	public String calcularRFC(String pPNombre, String pSNombre, String pApPat, String pApMat, String pFechaNac){
		new calculaRFCTask().execute(pFechaNac, pApPat, pApMat, pPNombre, pSNombre);
		return rfcCalculado;
	}
	
	private class calculaRFCTask extends AsyncTask<String, Void, Void>{
		
		@Override
		protected Void doInBackground(String... params) {
			calculaRFC cRFC = new calculaRFC();
			cRFC.pFechaNac = params[0];
			cRFC.pApPat = params[1];
			cRFC.pApMat = params[2];
			cRFC.pPNombre = params[3];
			cRFC.pSNombre = params[4];
			cRFC.connection();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void v) {
		}
	}
	
	public class calculaRFC {
		private static final String SOAP_ACTION = "http://tempuri.org/GenerarRFC";
	    private static final String METHOD_NAME = "GenerarRFC";
	    private static final String NAMESPACE = "http://tempuri.org/";
	    private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";
	    String pFechaNac;
	    String pApPat;
	    String pApMat;
	    String pPNombre;
	    String pSNombre;
		public calculaRFC() {
		}
		
		public void connection() {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    	request.addProperty("pFechaNacimiento", pFechaNac.toString());
	    	request.addProperty("pPrimerApellido", pApPat.toString());
	    	request.addProperty("pSegundoApellido", pApMat.toString());
	    	request.addProperty("pPrimerNombre", pPNombre.toString());	
	    	request.addProperty("pPrimerNombre", pSNombre.toString());
	    	HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	try {
				httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				final SoapPrimitive sResult = (SoapPrimitive)envelope.getResponse();
				if (sResult != null) {
					rfcCalculado = sResult.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
