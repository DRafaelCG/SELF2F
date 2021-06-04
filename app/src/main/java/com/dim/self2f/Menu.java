package com.dim.self2f;


import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dim.self2f.clases.ClsCargarCatalogosMenu;
import com.dim.self2f.clases.ClsMensaje;
import com.dim.self2f.clases.ClsSolicitud;
import com.dim.self2f.clases.ClsSolicitudesFinalizadas;
import com.dim.self2f.clases.ClsSolicitudesPendientes;
import com.dim.self2f.clases.ClsUsuarioF2F;
import com.dim.self2f.clases.ClsValidaCampos;
import com.dim.self2f.db.DataBaseHelper;
import com.dim.self2f.pulltorefresh.PullToRefreshListView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Menu extends  ListActivity {
    ClsUsuarioF2F usuario = ClsUsuarioF2F.getInstancia();
    ClsValidaCampos valida = new ClsValidaCampos();
    ClsMensaje mensaje = ClsMensaje.getInstancia();
    ClsSolicitud solicitud = ClsSolicitud.getInstancia();
    TextView tvTituloItemMenu;
    Boolean fromLogin = false;
    Dialog customDialog;
    TextView tVTitulo;
    TextView tVContenido;
    Dialog customDialogAcepCancel;
    TextView tVTituloAcepCancel;
    TextView tVContenidoAcepCancel;
    /***TramitesPendientes*******************/
    private SimpleAdapter adapterTramitesPendientes;
    private ArrayList<HashMap<String, String>> eventosTramitesPendientes;
    String[] fromTPendientes = new String[] {"idFolio", "ContenidoFolio",
            "ContenidoFolioInteligente", "ContenidoNombreCompleto"};
    int[] toTPendientes = new int[] {R.id.idFolioPend, R.id.tVContenidoFolioPend,
            R.id.tVContenidoFolioInteligentePend, R.id.tVContenidoNombreCompletoPend};
    private PullToRefreshListView listTPendientes;
    private ClsSolicitudesPendientes[] listSolicitudesPendientes;
    int idActualizaTPend = 0;
    /**********************/
    /***TramitesPendientes*******************/
    private SimpleAdapter adapterTramitesFinalizados;
    private ArrayList<HashMap<String, String>> eventosTramitesFinalizados;
    String[] fromTFinalizados = new String[] {"idFolio", "ContenidoFolio",
            "ContenidoFolioInteligente", "ContenidoNombreCompleto"};
    int[] toTFinalizados = new int[] {R.id.idFolio, R.id.tVContenidoFolio,
            R.id.tVContenidoFolioInteligente, R.id.tVContenidoNombreCompleto};
    private PullToRefreshListView listTFinalizados;
    private ClsSolicitudesFinalizadas[] listSolicitudesFinalizadas;
    int idActualizaTFin = 0;
    /**********************/
    /************CambiaPwd*****************************************/
    EditText eTPwdActual;
    EditText eTNvoPwd;
    EditText eTRepitNvoPwd;
    Button btnCambiarPwd;
    String cadenaValida = "";
    /*****************************************************/
    /***ActualizarCatalogos*****************************/
    //Button btnActualizaCatalogos;
    DataBaseHelper helper;
    ClsCargarCatalogosMenu cargaCatalogosMenu;
    /*********************************/
    /****CerrarSesion*****************/
    //Button btnCerrarSesion;
    /*********************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("");
        tvTituloItemMenu = (TextView)findViewById(R.id.tVTituloItemMenu);
        tvTituloItemMenu.setText("");
        customDialog = new Dialog(Menu.this, R.style.Theme_Dialog_Translucent);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setCancelable(false);
        customDialog.setContentView(R.layout.dialogo_aceptar);
        tVTitulo = (TextView)customDialog.findViewById(R.id.tVTituloDialogo);
        tVTitulo.setGravity(Gravity.CENTER);
        tVContenido = (TextView)customDialog.findViewById(R.id.tVContenido);
        tVContenido.setGravity(Gravity.CENTER);
        eTPwdActual = (EditText)findViewById(R.id.eTContraseniaAnterior);
        eTNvoPwd = (EditText)findViewById(R.id.eTContraseniaNueva);
        eTRepitNvoPwd = (EditText)findViewById(R.id.eTConfirmarContrasenia);
        btnCambiarPwd = (Button)findViewById(R.id.btnCambiarPwd);
        //btnActualizaCatalogos = (Button)findViewById(R.id.bttnActualizarCatalogos);
        //btnCerrarSesion = (Button)findViewById(R.id.bttnCerrarSesion);
        helper = new DataBaseHelper(getApplicationContext());

        Bundle b = getIntent().getExtras();
        if (b != null) {
            fromLogin = b.getBoolean("logueado");
            if (fromLogin) {
                if (!usuario.getpNombre().toString().matches("")) {
                    tVTitulo.setText("Bienvenido");
                    String nombreString = usuario.getpNombre().toString() + " " + usuario.getApPaterno().toString();
                    tVContenido.setText(nombreString.toString());
                    ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customDialog.dismiss();
                            customDialog.cancel();
                        }
                    });
                    customDialog.show();
                }
            }
        }
        /***TramitesPendientes*******************/
        listTPendientes = (PullToRefreshListView)findViewById(R.id.listPendientes);
        //listTPendientes = (PullToRefreshListView)findViewById(android.R.id.list);
        listTPendientes.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                idActualizaTPend = 1;
                listTPendientes.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new cargaSolicitudesPendientesTask().execute();
                        listTPendientes.onRefreshComplete();
                    }
                }, 2000);

            }
        });
        /***TramitesPendientes*******************/
        listTFinalizados = (PullToRefreshListView)findViewById(android.R.id.list);
        listTFinalizados.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                idActualizaTFin = 1;
                listTFinalizados.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new cargaSolicitudesFinalizadasTask().execute();
                        listTFinalizados.onRefreshComplete();
                    }
                }, 2000);

            }
        });
        btnCambiarPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valida.validaNuevaContrasenia(eTPwdActual.getText().toString(),
                        eTNvoPwd.getText().toString(), eTRepitNvoPwd.getText().toString())) {
                    new CambiaContraseñaTask().execute(eTNvoPwd.toString());
                }else {
                    tVTitulo.setText("Datos incorrectos");
                    tVContenido.setText(mensaje.getMensaje().toString());
                    ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customDialog.dismiss();
                            customDialog.cancel();
                        }
                    });
                    customDialog.show();
                }
            }
        });
		/*btnActualizaCatalogos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				customDialogAcepCancel = new Dialog(Menu.this, R.style.Theme_Dialog_Translucent);
				customDialogAcepCancel.requestWindowFeature(Window.FEATURE_NO_TITLE);
				customDialogAcepCancel.setCancelable(false);
				customDialogAcepCancel.setContentView(R.layout.dialogo_aceptar_cancelar);
				tVTituloAcepCancel = (TextView)customDialogAcepCancel.findViewById(R.id.tVTituloDialogo);
				tVTituloAcepCancel.setGravity(Gravity.CENTER);
				tVContenidoAcepCancel = (TextView)customDialogAcepCancel.findViewById(R.id.tVContenido);
				tVContenidoAcepCancel.setGravity(Gravity.CENTER);
				tVTituloAcepCancel.setText("Va a actualizar los catalogos");
				tVContenidoAcepCancel.setText("¿Desea continuar?");
				((Button)customDialogAcepCancel.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						customDialogAcepCancel.dismiss();
						customDialogAcepCancel.cancel();
						actualizaCatalogos();
					}
				});
				((Button)customDialogAcepCancel.findViewById(R.id.btnCancelarDialog)).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						customDialogAcepCancel.dismiss();
						customDialogAcepCancel.cancel();
					}
				});
				customDialogAcepCancel.show();
			}
		});
		btnCerrarSesion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				customDialogAcepCancel = new Dialog(Menu.this, R.style.Theme_Dialog_Translucent);
				customDialogAcepCancel.requestWindowFeature(Window.FEATURE_NO_TITLE);
				customDialogAcepCancel.setCancelable(false);
				customDialogAcepCancel.setContentView(R.layout.dialogo_aceptar_cancelar);
				tVTituloAcepCancel = (TextView)customDialogAcepCancel.findViewById(R.id.tVTituloDialogo);
				tVTituloAcepCancel.setGravity(Gravity.CENTER);
				tVContenidoAcepCancel = (TextView)customDialogAcepCancel.findViewById(R.id.tVContenido);
				tVContenidoAcepCancel.setGravity(Gravity.CENTER);
				tVTituloAcepCancel.setText("Va a cerrar su sesión");
				tVContenidoAcepCancel.setText("¿Desea continuar?");
				((Button)customDialogAcepCancel.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						customDialogAcepCancel.dismiss();
						customDialogAcepCancel.cancel();
						usuario = new ClsUsuarioF2F();
						valida = new ClsValidaCampos();
						mensaje = new ClsMensaje();
						Intent intInicio = new Intent(Menu.this, Inicio.class);
						startActivity(intInicio);;
						finish();
					}
				});
				((Button)customDialogAcepCancel.findViewById(R.id.btnCancelarDialog)).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						customDialogAcepCancel.dismiss();
						customDialogAcepCancel.cancel();
					}
				});
				customDialogAcepCancel.show();
			}
		});*/
        listTPendientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                llenaSolicitudPendiente(position);
            }

        });
    }

    protected void actualizaCatalogos() {
        helper.deleteCatalogos();
        cargaCatalogosMenu = new ClsCargarCatalogosMenu(Menu.this);
        cargaCatalogosMenu.cargarCatalogos();
    }

    public void cambiaVista(View v) {
        switch (v.getId()) {
            case R.id.bttnInicio:
                tvTituloItemMenu.setText("Inicio");
                findViewById(R.id.iInicio).setVisibility(View.VISIBLE);
                findViewById(R.id.iSolicitudesPendientes).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesFinalizadas).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCambiarContrasenia).setVisibility(View.INVISIBLE);
                findViewById(R.id.iActualizarCatalogos).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCerrarSesion).setVisibility(View.INVISIBLE);
                eTPwdActual.setText("");
                eTNvoPwd.setText("");
                eTPwdActual.setText("");
                break;
            case R.id.bttnNvaSolicitud:
                tvTituloItemMenu.setText("Nueva solicitud");
                findViewById(R.id.iInicio).setVisibility(View.VISIBLE);
                findViewById(R.id.iSolicitudesPendientes).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesFinalizadas).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCambiarContrasenia).setVisibility(View.INVISIBLE);
                findViewById(R.id.iActualizarCatalogos).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCerrarSesion).setVisibility(View.INVISIBLE);
                Intent intSolicitud = new Intent(Menu.this, Solicitud.class);
                startActivity(intSolicitud);
                finish();
                break;
            case R.id.bttnSolicitudesPendientes:
                tvTituloItemMenu.setText("Solicitudes pendientes");
                findViewById(R.id.iSolicitudesPendientes).setVisibility(View.VISIBLE);
                findViewById(R.id.iInicio).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesFinalizadas).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCambiarContrasenia).setVisibility(View.INVISIBLE);
                findViewById(R.id.iActualizarCatalogos).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCerrarSesion).setVisibility(View.INVISIBLE);
                eTPwdActual.setText("");
                eTNvoPwd.setText("");
                eTPwdActual.setText("");
                if (listSolicitudesPendientes == null) {
                    new cargaSolicitudesPendientesTask().execute();
                }else {
                    eventosTramitesPendientes = new ArrayList<HashMap<String,String>>();
                    for (ClsSolicitudesPendientes met:listSolicitudesPendientes) {
                        HashMap<String, String> datosEvento = new HashMap<String, String>();
                        datosEvento.put("idFolio", String.valueOf(met.getIDFolio()));
                        datosEvento.put("ContenidoFolio", String.valueOf(met.getIDFolio()));
                        //datosEvento.put("ContenidoFCaptura", met.getFechaCaptura());
                        //datosEvento.put("ContenidoFolioAgencia", met.getFolioAgencia());
                        datosEvento.put("ContenidoFolioInteligente", met.getFolioInteligente());
                        datosEvento.put("ContenidoNombreCompleto", met.getNombreCompleto());
                        eventosTramitesPendientes.add(datosEvento);
                    }
                    adapterTramitesPendientes = new SimpleAdapter(Menu.this, eventosTramitesPendientes, R.layout.rows_solicitudespendientes
                            , fromTPendientes, toTPendientes);
                }
                break;
            case R.id.bttnSolicitudesFinalizadas:
                tvTituloItemMenu.setText("Solicitudes finalizadas");
                findViewById(R.id.iSolicitudesFinalizadas).setVisibility(View.VISIBLE);
                findViewById(R.id.iInicio).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesPendientes).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCambiarContrasenia).setVisibility(View.INVISIBLE);
                findViewById(R.id.iActualizarCatalogos).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCerrarSesion).setVisibility(View.INVISIBLE);
                eTPwdActual.setText("");
                eTNvoPwd.setText("");
                eTPwdActual.setText("");
                if (listSolicitudesFinalizadas == null) {
                    new cargaSolicitudesFinalizadasTask().execute();
                }else {
                    eventosTramitesFinalizados = new ArrayList<HashMap<String,String>>();
                    for (ClsSolicitudesFinalizadas met:listSolicitudesFinalizadas) {
                        HashMap<String, String> datosEvento = new HashMap<String, String>();
                        datosEvento.put("idFolio", String.valueOf(met.getIDFolio()));
                        datosEvento.put("ContenidoFolio", String.valueOf(met.getIDFolio()));
                        //datosEvento.put("ContenidoFCaptura", met.getFechaCaptura());
                        //datosEvento.put("ContenidoFolioAgencia", met.getFolioAgencia());
                        datosEvento.put("ContenidoFolioInteligente", met.getFolioInteligente());
                        datosEvento.put("ContenidoNombreCompleto", met.getNombreCompleto());
                        eventosTramitesFinalizados.add(datosEvento);
                    }
                    adapterTramitesFinalizados = new SimpleAdapter(Menu.this, eventosTramitesFinalizados, R.layout.rows_solicitudesfinalizadas
                            , fromTFinalizados, toTFinalizados);
                }
                break;
            case R.id.bttnCambiarContrasenia:
                tvTituloItemMenu.setText("Cambiar contraseña");
                findViewById(R.id.iCambiarContrasenia).setVisibility(View.VISIBLE);
                findViewById(R.id.iInicio).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesPendientes).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesFinalizadas).setVisibility(View.INVISIBLE);
                findViewById(R.id.iActualizarCatalogos).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCerrarSesion).setVisibility(View.INVISIBLE);
                eTPwdActual.setText("");
                eTNvoPwd.setText("");
                eTPwdActual.setText("");
                break;
            case R.id.bttnActualizarCatalogos:
                tvTituloItemMenu.setText("Actualizar catalogos");
                findViewById(R.id.iActualizarCatalogos).setVisibility(View.VISIBLE);
                findViewById(R.id.iInicio).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesPendientes).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesFinalizadas).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCambiarContrasenia).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCerrarSesion).setVisibility(View.INVISIBLE);
                eTPwdActual.setText("");
                eTNvoPwd.setText("");
                eTPwdActual.setText("");
                muestraDialogoActualizaCatalogosOCerrarSesion(1);
                break;
            case R.id.bttnCerrarSesion:
                tvTituloItemMenu.setText("Cerrar sesión");
                findViewById(R.id.iCerrarSesion).setVisibility(View.VISIBLE);
                findViewById(R.id.iInicio).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesPendientes).setVisibility(View.INVISIBLE);
                findViewById(R.id.iSolicitudesFinalizadas).setVisibility(View.INVISIBLE);
                findViewById(R.id.iCambiarContrasenia).setVisibility(View.INVISIBLE);
                findViewById(R.id.iActualizarCatalogos).setVisibility(View.INVISIBLE);
                eTPwdActual.setText("");
                eTNvoPwd.setText("");
                eTPwdActual.setText("");
                muestraDialogoActualizaCatalogosOCerrarSesion(2);
                break;
        }
    }

    protected void muestraDialogoActualizaCatalogosOCerrarSesion(final int idOpcion) {
        customDialogAcepCancel = new Dialog(Menu.this, R.style.Theme_Dialog_Translucent);
        customDialogAcepCancel.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialogAcepCancel.setCancelable(false);
        customDialogAcepCancel.setContentView(R.layout.dialogo_aceptar_cancelar);
        tVTituloAcepCancel = (TextView)customDialogAcepCancel.findViewById(R.id.tVTituloDialogo);
        tVTituloAcepCancel.setGravity(Gravity.CENTER);
        tVContenidoAcepCancel = (TextView)customDialogAcepCancel.findViewById(R.id.tVContenido);
        tVContenidoAcepCancel.setGravity(Gravity.CENTER);
        if (idOpcion == 1) {
            tVTituloAcepCancel.setText("Va a actualizar los catalogos");
        }else {
            tVTituloAcepCancel.setText("Va a cerrar su sesión");
        }
        tVContenidoAcepCancel.setText("¿Desea continuar?");
        ((Button)customDialogAcepCancel.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogAcepCancel.dismiss();
                customDialogAcepCancel.cancel();
                if (idOpcion == 1) {
                    actualizaCatalogos();
                }else {
                    usuario = new ClsUsuarioF2F();
                    valida = new ClsValidaCampos();
                    mensaje = new ClsMensaje();
                    Intent intInicio = new Intent(Menu.this, Inicio.class);
                    startActivity(intInicio);;
                    finish();
                }
            }
        });
        ((Button)customDialogAcepCancel.findViewById(R.id.btnCancelarDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogAcepCancel.dismiss();
                customDialogAcepCancel.cancel();
            }
        });
        customDialogAcepCancel.show();
    }

    /*****TramitesPendientes*******************************************/
    private class cargaSolicitudesPendientesTask extends AsyncTask<Void, Void, SimpleAdapter> {
        private final ProgressDialog dialogo = new ProgressDialog(Menu.this, ProgressDialog.THEME_TRADITIONAL);
        @Override
        protected void onPreExecute(){
            if (idActualizaTPend == 0) {
                dialogo.setMessage("Cargando solicitudes pendientes");
            }else {
                dialogo.setMessage("Actualizando solicitudes pendientes");
            }
            dialogo.setCancelable(false);
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.getWindow().setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
            dialogo.show();
        }

        protected SimpleAdapter doInBackground(Void... params){
            listSolicitudesPendientes lSPend = new listSolicitudesPendientes();
            lSPend.connection();
            return adapterTramitesPendientes;
        }

        protected void onPostExecute(SimpleAdapter adapterTramitesPendientes){
            if (adapterTramitesPendientes != null) {
                listTPendientes.setAdapter(adapterTramitesPendientes);
                //setListAdapter(adapterTramitesPendientes);
            }
            dialogo.dismiss();
        }
    }

    public class listSolicitudesPendientes{
        /*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarTramitesPendientes";
        private static final String METHOD_NAME = "ConsultarTramitesPendientes";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
        public listSolicitudesPendientes(){
        }
        public SimpleAdapter connection(){
            /*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("pIdUsuario", usuario.getIdUsuario());
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapObject sResult = (SoapObject)envelope.getResponse();
                listSolicitudesPendientes = new ClsSolicitudesPendientes[sResult.getPropertyCount()];
                if (sResult != null) {
                    for (int i = 0; i < sResult.getPropertyCount(); i++) {
                        SoapObject im = (SoapObject)sResult.getProperty(i);
                        ClsSolicitudesPendientes solPendientes = new ClsSolicitudesPendientes();
                        solPendientes.setIDFolio(Integer.parseInt(im.getProperty("IDFolio").toString()));
                        //solPendientes.setFechaCaptura(im.getProperty("FechaCaptura").toString());
                        solPendientes.setFolioAgencia(im.getProperty("FolioAgencia").toString());
                        solPendientes.setFolioInteligente(im.getProperty("FolioInteligente").toString());
                        //solPendientes.setNombreCompleto(im.getProperty("NombreCompleto").toString());
                        solPendientes.setApellidoPaterno(im.getProperty("ApellidoPaterno").toString());
                        solPendientes.setApellidoMaterno(im.getProperty("ApellidoMaterno").toString());
                        solPendientes.setPrimerNombre(im.getProperty("PrimerNombre").toString());
                        solPendientes.setSegundoNombre(im.getProperty("SegundoNombre").toString());
                        solPendientes.setCEstadoCivil(im.getProperty("CEstadoCivil").toString());
                        solPendientes.setCSexo(im.getProperty("CSexo").toString());
                        solPendientes.setFechaNacimiento(im.getProperty("FechaNacimiento").toString());
                        solPendientes.setCNacionalidad(im.getProperty("CNacionalidad").toString());
                        solPendientes.setCFormaMigratoria(im.getProperty("CFormaMigratoria").toString());
                        solPendientes.setRFC(im.getProperty("RFC").toString());
                        solPendientes.setCalleDomicilio(im.getProperty("CalleDomicilio").toString());
                        solPendientes.setNumeroExteriorDomicilio(im.getProperty("NumeroExteriorDomicilio").toString());
                        solPendientes.setNumeroInteriorDomicilio(im.getProperty("NumeroInteriorDomicilio").toString());
                        solPendientes.setLocalidad(im.getProperty("Localidad").toString());
                        solPendientes.setCTipovivienda(im.getProperty("CTipovivienda").toString());
                        solPendientes.setClaveLadaDomicilio(im.getProperty("ClaveLadaDomicilio").toString());
                        solPendientes.setTelefonoDomicilio(im.getProperty("TelefonoDomicilio").toString());
                        solPendientes.setCTipoProducto(im.getProperty("CTipoProducto").toString());
                        solPendientes.setAntiguedadDomicilio(im.getProperty("AntiguedadDomicilio").toString());
                        solPendientes.setCUniversidad(im.getProperty("CUniversidad").toString());
                        solPendientes.setCampus(im.getProperty("Campus").toString());
                        solPendientes.setCCarrera(im.getProperty("CCarrera").toString());
                        solPendientes.setCFinanciamiento(im.getProperty("CFinanciamiento").toString());
                        solPendientes.setPorcentajeBeca(im.getProperty("PorcentajeBeca").toString());
                        solPendientes.setCGradoEscolar(im.getProperty("CGradoEscolar").toString());
                        solPendientes.setIngresoNetoFijo(im.getProperty("IngresoNetoFijo").toString());
                        solPendientes.setIngresoNetoVariable(im.getProperty("IngresoNetoVariable").toString());
                        solPendientes.setProfesionistasIndependientes(im.getProperty("ProfesionistasIndependientes").toString());
                        solPendientes.setVentasAnuales(im.getProperty("VentasAnuales").toString());
                        solPendientes.setClaveLadaEmpleo(im.getProperty("ClaveLadaEmpleo").toString());
                        solPendientes.setTelefonoEmpleo(im.getProperty("TelefonoEmpleo").toString());
                        solPendientes.setExtension(im.getProperty("Extension").toString());
                        solPendientes.setCOcupacion(im.getProperty("COcupacion").toString());
                        solPendientes.setAntiguedadEmpleo(im.getProperty("AntiguedadEmpleo").toString());
                        solPendientes.setCelular(im.getProperty("Celular").toString());
                        solPendientes.setCorreoElectronico(im.getProperty("CorreoElectronico").toString());
                        //solPendientes.setSucursalPaperless(im.getProperty("SucursalPaperless").toString());
                        solPendientes.setNombreEmpresa(im.getProperty("NombreEmpresa").toString());
                        solPendientes.setCalleEmpresa(im.getProperty("CalleEmpresa").toString());
                        solPendientes.setNumeroExteriorEmpresa(im.getProperty("NumeroExteriorEmpresa").toString());
                        solPendientes.setNumeroInteriorEmpresa(im.getProperty("NumeroInteriorEmpresa").toString());
                        solPendientes.setLocalidadEmpresa(im.getProperty("LocalidadEmpresa").toString());
                        solPendientes.setEstatusCapturaBasica(im.getProperty("EstatusCapturaBasica").toString());
                        solPendientes.setEstatusAutenticacion(im.getProperty("EstatusAutenticacion").toString());
                        solPendientes.setEstatusEvaluacion(im.getProperty("EstatusEvaluacion").toString());
                        solPendientes.setEstatusCapturaReferencias(im.getProperty("EstatusCapturaReferencias").toString());
                        listSolicitudesPendientes[i] = solPendientes;
                    }
                    eventosTramitesPendientes = new ArrayList<HashMap<String,String>>();
                    for (ClsSolicitudesPendientes met:listSolicitudesPendientes) {
                        HashMap<String, String> datosEvento = new HashMap<String, String>();
                        datosEvento.put("idFolio", String.valueOf(met.getIDFolio()));
                        datosEvento.put("ContenidoFolio", String.valueOf(met.getIDFolio()));
                        //datosEvento.put("ContenidoFCaptura", met.getFechaCaptura());
                        //datosEvento.put("ContenidoFolioAgencia", met.getFolioAgencia());
                        datosEvento.put("ContenidoFolioInteligente", met.getFolioInteligente());
                        String nombreCompleto = met.getApellidoPaterno() + " " + met.getApellidoMaterno() + " " + met.getPrimerNombre();
                        nombreCompleto = nombreCompleto.replace("anyType{}", "");
                        datosEvento.put("ContenidoNombreCompleto", nombreCompleto.toString());
                        eventosTramitesPendientes.add(datosEvento);
                    }
                    adapterTramitesPendientes = new SimpleAdapter(Menu.this, eventosTramitesPendientes, R.layout.rows_solicitudespendientes
                            , fromTPendientes, toTPendientes);
                }
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }*/
            Random rnd;
            Date dt;
            long    ms;
            rnd = new Random();
            ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            dt = new Date(ms);
            listSolicitudesPendientes = new ClsSolicitudesPendientes[10];
            int edoCivil = 0;
            int sex = 0;
            int nac = 0;
            int formaM = 0;
            int numTel = 0;
            int numTelEmp = 0;
            int numCel = 0;
            for (int i = 0; i < 10; i++) {
                edoCivil = (int)(Math.random()*2);
                sex = (int)(Math.random()*1);
                nac = (int)(Math.random()*1);
                formaM = (int)(Math.random()*2);
                numTel = (int)(Math.random()*9);
                numTelEmp = (int)(Math.random()*9);
                numCel = (int)(Math.random()*9);
                ClsSolicitudesPendientes solPendientes = new ClsSolicitudesPendientes();
                solPendientes.setIDFolio(i+1);
                //solPendientes.setFechaCaptura(im.getProperty("FechaCaptura").toString());
                solPendientes.setFolioAgencia("Folio agencia: " + (i+1));
                solPendientes.setFolioInteligente("Folio inteligente: " + (i+1));
                //solPendientes.setNombreCompleto(im.getProperty("NombreCompleto").toString());
                solPendientes.setApellidoPaterno("Paterno: " + (i+1));
                solPendientes.setApellidoMaterno("Materno: " + (i+1));
                solPendientes.setPrimerNombre("Nombre: " + (i+1));
                solPendientes.setSegundoNombre("");
                solPendientes.setCEstadoCivil("Edo. civil: " + edoCivil);
                solPendientes.setCSexo("Sexp: " + sex);
                solPendientes.setFechaNacimiento(dt.toString());
                solPendientes.setCNacionalidad("Nacionalidad: " + nac );
                solPendientes.setCFormaMigratoria("Forma migratoria: " + formaM);
                solPendientes.setRFC("FRC" + (i+1));
                solPendientes.setCalleDomicilio("Calle: " +  (i+1));
                solPendientes.setNumeroExteriorDomicilio("Ext: " + (i+1));
                solPendientes.setNumeroInteriorDomicilio("Int: " + (i+1));
                solPendientes.setLocalidad("Localidad: " + (i+1));
                solPendientes.setCTipovivienda("Tipo vivienda: " + (i+1));
                solPendientes.setClaveLadaDomicilio(String.valueOf(i+1));
                solPendientes.setTelefonoDomicilio(String.valueOf(numTel));
                solPendientes.setCTipoProducto("Tipo producto: " + (i+1));
                solPendientes.setAntiguedadDomicilio("Antiguedad Dom.:" + (i+1));
                solPendientes.setCUniversidad("Universidad: "+ (i+1));
                solPendientes.setCampus("Campus: " + (i+1));
                solPendientes.setCCarrera("Carrera: " + (i+1));
                solPendientes.setCFinanciamiento("Financiamiento: " + (i+1));
                solPendientes.setPorcentajeBeca("Porcentaje: " + (i+1));
                solPendientes.setCGradoEscolar("Grado escolar: " + (i+1));
                solPendientes.setIngresoNetoFijo("Ingreso fijo: " + (i+1));
                solPendientes.setIngresoNetoVariable("Ingreso variable: " + (i+1));
                solPendientes.setProfesionistasIndependientes("Profesionista: " + (i+1));
                solPendientes.setVentasAnuales("Ventas anuales: " + (i+1));
                solPendientes.setClaveLadaEmpleo(String.valueOf(i+1));
                solPendientes.setTelefonoEmpleo(String.valueOf(numTelEmp));
                solPendientes.setExtension(String.valueOf(i+1));
                solPendientes.setCOcupacion("Ocupación: " + (i+1));
                solPendientes.setAntiguedadEmpleo(String.valueOf(i+1));
                solPendientes.setCelular(String.valueOf(numCel));
                solPendientes.setCorreoElectronico("correo@" + (i+1)+ ".com");
                //solPendientes.setSucursalPaperless(im.getProperty("SucursalPaperless").toString());
                solPendientes.setNombreEmpresa("Empresa: " + (i+1));
                solPendientes.setCalleEmpresa("Calle empresa: "+ (i+1));
                solPendientes.setNumeroExteriorEmpresa("Ext emp: "+ (i+1));
                solPendientes.setNumeroInteriorEmpresa("Int emp: "+ (i+1));
                solPendientes.setLocalidadEmpresa("Localidad emp: "+ (i+1));
                solPendientes.setEstatusCapturaBasica("Estatus captura: "+ (i+1));
                solPendientes.setEstatusAutenticacion("Estatus autenticacion: "+ (i+1));
                solPendientes.setEstatusEvaluacion("Estatus evaluacion: "+ (i+1));
                solPendientes.setEstatusCapturaReferencias("Estatus referencias: "+ (i+1));
                listSolicitudesPendientes[i] = solPendientes;
            }
            eventosTramitesPendientes = new ArrayList<HashMap<String,String>>();
            for (ClsSolicitudesPendientes met:listSolicitudesPendientes) {
                HashMap<String, String> datosEvento = new HashMap<String, String>();
                datosEvento.put("idFolio", String.valueOf(met.getIDFolio()));
                datosEvento.put("ContenidoFolio", String.valueOf(met.getIDFolio()));
                //datosEvento.put("ContenidoFCaptura", met.getFechaCaptura());
                //datosEvento.put("ContenidoFolioAgencia", met.getFolioAgencia());
                datosEvento.put("ContenidoFolioInteligente", met.getFolioInteligente());
                String nombreCompleto = met.getApellidoPaterno() + " " + met.getApellidoMaterno() + " " + met.getPrimerNombre();
                nombreCompleto = nombreCompleto.replace("anyType{}", "");
                datosEvento.put("ContenidoNombreCompleto", nombreCompleto.toString());
                eventosTramitesPendientes.add(datosEvento);
            }
            adapterTramitesPendientes = new SimpleAdapter(Menu.this, eventosTramitesPendientes, R.layout.rows_solicitudespendientes
                    , fromTPendientes, toTPendientes);

            return adapterTramitesPendientes;
        }
    }

    /*****TramitesPendientes*******************************************/
    /*****TramitesFinalizados*******************************************/

    private class cargaSolicitudesFinalizadasTask extends AsyncTask<Void, Void, SimpleAdapter>{
        private final ProgressDialog dialogo = new ProgressDialog(Menu.this, ProgressDialog.THEME_TRADITIONAL);
        @Override
        protected void onPreExecute(){
            if (idActualizaTFin == 0) {
                dialogo.setMessage("Cargando solicitudes finalizadas");
            }else {
                dialogo.setMessage("Actualizando solicitudes finalizadas");
            }
            dialogo.setCancelable(false);
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.getWindow().setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
            dialogo.show();
        }

        protected SimpleAdapter doInBackground(Void... params){
            listSolicitudesFinalizadas lSFin = new listSolicitudesFinalizadas();
            lSFin.connection();
            return adapterTramitesFinalizados;
        }

        protected void onPostExecute(SimpleAdapter adapterTramitesFinalizados){
            if (adapterTramitesFinalizados != null) {
                listTFinalizados.setAdapter(adapterTramitesFinalizados);
                //setListAdapter(adaptador);
            }
            dialogo.dismiss();
        }
    }

    public class listSolicitudesFinalizadas{
        /*private static final String SOAP_ACTION = "http://tempuri.org/ConsultarTramitesFinalizados";
        private static final String METHOD_NAME = "ConsultarTramitesFinalizados";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
        public listSolicitudesFinalizadas(){
        }

        public SimpleAdapter connection(){
            /*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("pIdUsuario", usuario.getIdUsuario());
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapObject sResult = (SoapObject)envelope.getResponse();
                listSolicitudesFinalizadas = new ClsSolicitudesFinalizadas[sResult.getPropertyCount()];
                if (sResult != null) {
                    for (int i = 0; i < sResult.getPropertyCount(); i++) {
                        SoapObject im = (SoapObject)sResult.getProperty(i);
                        ClsSolicitudesFinalizadas solFinalizadas = new ClsSolicitudesFinalizadas();
                        solFinalizadas.setIDFolio(Integer.parseInt(im.getProperty("IDFolio").toString()));
                        //solFinalizadas.setFechaCaptura(im.getProperty("FechaCaptura").toString());
                        solFinalizadas.setFolioAgencia(im.getProperty("FolioAgencia").toString());
                        solFinalizadas.setFolioInteligente(im.getProperty("FolioInteligente").toString());
                        //solFinalizadas.setNombreCompleto(im.getProperty("NombreCompleto").toString());
                        solFinalizadas.setApellidoPaterno(im.getProperty("ApellidoPaterno").toString());
                        solFinalizadas.setApellidoMaterno(im.getProperty("ApellidoMaterno").toString());
                        solFinalizadas.setPrimerNombre(im.getProperty("PrimerNombre").toString());
                        solFinalizadas.setSegundoNombre(im.getProperty("SegundoNombre").toString());
                        solFinalizadas.setCEstadoCivil(im.getProperty("CEstadoCivil").toString());
                        solFinalizadas.setCSexo(im.getProperty("CSexo").toString());
                        solFinalizadas.setFechaNacimiento(im.getProperty("FechaNacimiento").toString());
                        solFinalizadas.setCNacionalidad(im.getProperty("CNacionalidad").toString());
                        solFinalizadas.setCFormaMigratoria(im.getProperty("CFormaMigratoria").toString());
                        solFinalizadas.setRFC(im.getProperty("RFC").toString());
                        solFinalizadas.setCalleDomicilio(im.getProperty("CalleDomicilio").toString());
                        solFinalizadas.setNumeroExteriorDomicilio(im.getProperty("NumeroExteriorDomicilio").toString());
                        solFinalizadas.setNumeroInteriorDomicilio(im.getProperty("NumeroInteriorDomicilio").toString());
                        solFinalizadas.setLocalidad(im.getProperty("Localidad").toString());
                        solFinalizadas.setCTipovivienda(im.getProperty("CTipovivienda").toString());
                        solFinalizadas.setClaveLadaDomicilio(im.getProperty("ClaveLadaDomicilio").toString());
                        solFinalizadas.setTelefonoDomicilio(im.getProperty("TelefonoDomicilio").toString());
                        solFinalizadas.setCTipoProducto(im.getProperty("CTipoProducto").toString());
                        solFinalizadas.setAntiguedadDomicilio(im.getProperty("AntiguedadDomicilio").toString());
                        solFinalizadas.setCUniversidad(im.getProperty("CUniversidad").toString());
                        solFinalizadas.setCampus(im.getProperty("Campus").toString());
                        solFinalizadas.setCCarrera(im.getProperty("CCarrera").toString());
                        solFinalizadas.setCFinanciamiento(im.getProperty("CFinanciamiento").toString());
                        solFinalizadas.setPorcentajeBeca(im.getProperty("PorcentajeBeca").toString());
                        solFinalizadas.setCGradoEscolar(im.getProperty("CGradoEscolar").toString());
                        solFinalizadas.setIngresoNetoFijo(im.getProperty("IngresoNetoFijo").toString());
                        solFinalizadas.setIngresoNetoVariable(im.getProperty("IngresoNetoVariable").toString());
                        solFinalizadas.setProfesionistasIndependientes(im.getProperty("ProfesionistasIndependientes").toString());
                        solFinalizadas.setVentasAnuales(im.getProperty("VentasAnuales").toString());
                        solFinalizadas.setClaveLadaEmpleo(im.getProperty("ClaveLadaEmpleo").toString());
                        solFinalizadas.setTelefonoEmpleo(im.getProperty("TelefonoEmpleo").toString());
                        solFinalizadas.setExtension(im.getProperty("Extension").toString());
                        solFinalizadas.setCOcupacion(im.getProperty("COcupacion").toString());
                        solFinalizadas.setAntiguedadEmpleo(im.getProperty("AntiguedadEmpleo").toString());
                        solFinalizadas.setCelular(im.getProperty("Celular").toString());
                        solFinalizadas.setCorreoElectronico(im.getProperty("CorreoElectronico").toString());
                        //solFinalizadas.setSucursalPaperless(im.getProperty("SucursalPaperless").toString());
                        solFinalizadas.setNombreEmpresa(im.getProperty("NombreEmpresa").toString());
                        solFinalizadas.setCalleEmpresa(im.getProperty("CalleEmpresa").toString());
                        solFinalizadas.setNumeroExteriorEmpresa(im.getProperty("NumeroExteriorEmpresa").toString());
                        solFinalizadas.setNumeroInteriorEmpresa(im.getProperty("NumeroInteriorEmpresa").toString());
                        solFinalizadas.setLocalidadEmpresa(im.getProperty("LocalidadEmpresa").toString());
                        solFinalizadas.setEstatusCapturaBasica(im.getProperty("EstatusCapturaBasica").toString());
                        solFinalizadas.setEstatusAutenticacion(im.getProperty("EstatusAutenticacion").toString());
                        solFinalizadas.setEstatusEvaluacion(im.getProperty("EstatusEvaluacion").toString());
                        solFinalizadas.setEstatusCapturaReferencias(im.getProperty("EstatusCapturaReferencias").toString());
                        listSolicitudesFinalizadas[i] = solFinalizadas;
                    }
                    eventosTramitesFinalizados = new ArrayList<HashMap<String,String>>();
                    for (ClsSolicitudesFinalizadas met:listSolicitudesFinalizadas) {
                        HashMap<String, String> datosEvento = new HashMap<String, String>();
                        datosEvento.put("idFolio", String.valueOf(met.getIDFolio()));
                        datosEvento.put("ContenidoFolio", String.valueOf(met.getIDFolio()));
                        //datosEvento.put("ContenidoFCaptura", met.getFechaCaptura());
                        //datosEvento.put("ContenidoFolioAgencia", met.getFolioAgencia());
                        datosEvento.put("ContenidoFolioInteligente", met.getFolioInteligente());
                        String nombreCompleto = met.getApellidoPaterno() + " " + met.getApellidoMaterno() + " " + met.getPrimerNombre();
                        nombreCompleto = nombreCompleto.replace("anyType{}", "");
                        datosEvento.put("ContenidoNombreCompleto", nombreCompleto.toString());
                        eventosTramitesFinalizados.add(datosEvento);
                    }
                    adapterTramitesFinalizados = new SimpleAdapter(Menu.this, eventosTramitesFinalizados, R.layout.rows_solicitudesfinalizadas
                            , fromTFinalizados, toTFinalizados);
                }
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }*/
            Random rnd;
            Date dt;
            long    ms;
            rnd = new Random();
            ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            dt = new Date(ms);
            int edoCivil = 0;
            int sex = 0;
            int nac = 0;
            int formaM = 0;
            int numTel = 0;
            int numTelEmp = 0;
            int numCel = 0;
            listSolicitudesFinalizadas = new ClsSolicitudesFinalizadas[10];
            for (int i = 0; i < 10; i++) {
                edoCivil = (int)(Math.random()*2);
                sex = (int)(Math.random()*1);
                nac = (int)(Math.random()*1);
                formaM = (int)(Math.random()*2);
                numTel = (int)(Math.random()*9);
                numTelEmp = (int)(Math.random()*9);
                numCel = (int)(Math.random()*9);
                ClsSolicitudesFinalizadas solFinalizadas = new ClsSolicitudesFinalizadas();
                solFinalizadas.setIDFolio(i+1);
                //solFinalizadas.setFechaCaptura(im.getProperty("FechaCaptura").toString());
                solFinalizadas.setFolioAgencia("FolioAgencia: " + (i+1) );
                solFinalizadas.setFolioInteligente("Folio Inteligente: " + (i+1));
                //solFinalizadas.setNombreCompleto(im.getProperty("NombreCompleto").toString());
                solFinalizadas.setApellidoPaterno("Paterno: " +(i+1));
                solFinalizadas.setApellidoMaterno("Materno: " + (i+1));
                solFinalizadas.setPrimerNombre("Nombre: " + (i+1));
                solFinalizadas.setSegundoNombre("");
                solFinalizadas.setCEstadoCivil("Edo. CIvil: " + edoCivil);
                solFinalizadas.setCSexo("Sexo: " + sex);
                solFinalizadas.setFechaNacimiento(dt.toString());
                solFinalizadas.setCNacionalidad("Nacionalidad: " + nac);
                solFinalizadas.setCFormaMigratoria("Forma migratoria: " + formaM);
                solFinalizadas.setRFC("RFC" + (i+1));
                solFinalizadas.setCalleDomicilio("Calle dom: "+ (i+1));
                solFinalizadas.setNumeroExteriorDomicilio("Ext. dom: "+ (i+1));
                solFinalizadas.setNumeroInteriorDomicilio("Int. dom: "+ (i+1));
                solFinalizadas.setLocalidad("Localidad: "+ (i+1));
                solFinalizadas.setCTipovivienda("Tipo vivienda: "+ (i+1));
                solFinalizadas.setClaveLadaDomicilio(String.valueOf(i+1));
                solFinalizadas.setTelefonoDomicilio(String.valueOf(numTel));
                solFinalizadas.setCTipoProducto("Producto: " + (i+1));
                solFinalizadas.setAntiguedadDomicilio("Antiguedad dom: "+ (i+1));
                solFinalizadas.setCUniversidad("Universidad: " + (i+1));
                solFinalizadas.setCampus("Campus: " + (i+1));
                solFinalizadas.setCCarrera("Carrera: " + (i+1));
                solFinalizadas.setCFinanciamiento("Financiamiento: " + (i+1));
                solFinalizadas.setPorcentajeBeca("Porcentaje beca: " + (i+1));
                solFinalizadas.setCGradoEscolar( String.valueOf(i+1));
                solFinalizadas.setIngresoNetoFijo("Ingreso neto: " + (i+1));
                solFinalizadas.setIngresoNetoVariable("Ingreso variable: " + (i+1));
                solFinalizadas.setProfesionistasIndependientes("Prof. independiente: " + (i+1));
                solFinalizadas.setVentasAnuales("Ventas anuales: " + (i+1));
                solFinalizadas.setClaveLadaEmpleo(String.valueOf( (i+1)));
                solFinalizadas.setTelefonoEmpleo(String.valueOf(numTelEmp));
                solFinalizadas.setExtension("Ext: " + (i+1));
                solFinalizadas.setCOcupacion("Ocupación: " + (i+1));
                solFinalizadas.setAntiguedadEmpleo(String.valueOf( (i+1)));
                solFinalizadas.setCelular(String.valueOf(numCel));
                solFinalizadas.setCorreoElectronico("correo@" + (i+1) + ".com");
                //solFinalizadas.setSucursalPaperless(im.getProperty("SucursalPaperless").toString());
                solFinalizadas.setNombreEmpresa("Empresa: " + (i+1));
                solFinalizadas.setCalleEmpresa("Calle emp: " + (i+1));
                solFinalizadas.setNumeroExteriorEmpresa("Ext. emp: " + (i+1));
                solFinalizadas.setNumeroInteriorEmpresa("Int. emp: " + (i+1));
                solFinalizadas.setLocalidadEmpresa("Localidad emp: " + (i+1));
                solFinalizadas.setEstatusCapturaBasica("Est. captura: " + (i+1));
                solFinalizadas.setEstatusAutenticacion("Est. autenticación: " + (i+1));
                solFinalizadas.setEstatusEvaluacion("Est. evaluación: " + (i+1));
                solFinalizadas.setEstatusCapturaReferencias("Est. referencias: " + (i+1));
                listSolicitudesFinalizadas[i] = solFinalizadas;
            }
            eventosTramitesFinalizados = new ArrayList<HashMap<String,String>>();
            for (ClsSolicitudesFinalizadas met:listSolicitudesFinalizadas) {
                HashMap<String, String> datosEvento = new HashMap<String, String>();
                datosEvento.put("idFolio", String.valueOf(met.getIDFolio()));
                datosEvento.put("ContenidoFolio", String.valueOf(met.getIDFolio()));
                //datosEvento.put("ContenidoFCaptura", met.getFechaCaptura());
                //datosEvento.put("ContenidoFolioAgencia", met.getFolioAgencia());
                datosEvento.put("ContenidoFolioInteligente", met.getFolioInteligente());
                String nombreCompleto = met.getApellidoPaterno() + " " + met.getApellidoMaterno() + " " + met.getPrimerNombre();
                nombreCompleto = nombreCompleto.replace("anyType{}", "");
                datosEvento.put("ContenidoNombreCompleto", nombreCompleto.toString());
                eventosTramitesFinalizados.add(datosEvento);
            }
            adapterTramitesFinalizados = new SimpleAdapter(Menu.this, eventosTramitesFinalizados, R.layout.rows_solicitudesfinalizadas
                    , fromTFinalizados, toTFinalizados);

            return adapterTramitesFinalizados;
        }
    }
    /*****TramitesFinalizados*******************************************/
    /****CambiarPwd******************************************************/
    private class CambiaContraseñaTask extends AsyncTask<String, Boolean, Boolean>{
        private final ProgressDialog dialogo = new ProgressDialog(Menu.this, ProgressDialog.THEME_TRADITIONAL);
        @Override
        protected void onPreExecute(){
            dialogo.setMessage("Actualizando contraseña");
            dialogo.setCancelable(false);
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.getWindow().setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
            dialogo.show();
        }

        protected Boolean doInBackground(String... params){
            boolean cambioPwd = false;
            cambiarContrasenia cC = new cambiarContrasenia();
            cC.nvoPwd = params[0];
            cambioPwd = cC.connection();
            return cambioPwd;
        }

        protected void onPostExecute(Boolean cambioPwd){
            dialogo.dismiss();
            muestraMensajeCambioContrasenia(cambioPwd);
        }
    }

    public class cambiarContrasenia{
        private static final String SOAP_ACTION = "http://tempuri.org/CambiarContraseña";
        private static final String METHOD_NAME = "CambiarContraseña";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";
        boolean cambioPwd = false;
        String nvoPwd;
        public cambiarContrasenia(){
        }

        public Boolean connection(){
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("pUsername", usuario.getNombreDeUsuario().toString());
            request.addProperty("pPasswordActual", usuario.getPassword().toString());
            request.addProperty("pPasswordNuevo", nvoPwd.toString());
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapObject sResult = (SoapObject)envelope.getResponse();
                listSolicitudesFinalizadas = new ClsSolicitudesFinalizadas[sResult.getPropertyCount()];
                if (sResult != null) {
                    cambioPwd = Boolean.parseBoolean(sResult.toString());
                }
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
            return cambioPwd;
        }
    }

    protected void muestraMensajeCambioContrasenia(Boolean cambioPwd) {
        if (cambioPwd) {
            tVTitulo.setText("Cambio de Contraseña");
            tVContenido.setText("La contraseña cambio exitosamente");
            ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                    customDialog.cancel();
                }
            });
            customDialog.show();
        }else {
            tVTitulo.setText("Cambio de Contraseña");
            tVContenido.setText("La contraseña no pudo ser cambiada, intente nuevamente");
            ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                    customDialog.cancel();
                }
            });
            customDialog.show();
        }
    }
    /**********************************************************/

    protected void llenaSolicitudPendiente(int position) {
        if (eventosTramitesPendientes.get(position).get("idFolio") != null) {
            for (ClsSolicitudesPendientes met:listSolicitudesPendientes) {
                if (met.getIDFolio() == (Integer.parseInt(eventosTramitesPendientes.get(position).get("idFolio").toString()))) {
                    solicitud.setIDFolio(String.valueOf(met.getIDFolio()).replace("anyType{}", ""));
                    solicitud.setFechaCaptura(met.getFechaCaptura().replace("anyType{}", ""));
                    solicitud.setFolioAgencia(met.getFolioAgencia().replace("anyType{}", ""));
                    solicitud.setFolioInteligente(met.getFolioInteligente().replace("anyType{}", ""));
                    solicitud.setpApellidoPaterno(met.getApellidoPaterno().replace("anyType{}", ""));
                    solicitud.setpApellidoMaterno(met.getApellidoMaterno().replace("anyType{}", ""));
                    solicitud.setpPrimerNombre(met.getPrimerNombre().replace("anyType{}", ""));
                    solicitud.setpSegundoNombre(met.getSegundoNombre().replace("anyType{}", ""));
                    solicitud.setpCEstadoCivil(met.getCEstadoCivil().replace("anyType{}", ""));
                    solicitud.setpCSexo(met.getCSexo().replace("anyType{}", ""));
                    solicitud.setFechaNacimiento(met.getFechaNacimiento().replace("anyType{}", ""));
                    solicitud.setpAñoNacimiento(met.getFechaNacimiento().substring(6, 10).replace("anyType{}", ""));
                    solicitud.setpMesNacimiento(met.getFechaNacimiento().substring(3, 5).replace("anyType{}", ""));
                    solicitud.setpDiaNacimiento(met.getFechaNacimiento().substring(0, 2).replace("anyType{}", ""));
                    solicitud.setpCNacionalidad(met.getCNacionalidad().replace("anyType{}", ""));
                    solicitud.setpCFormaMigratoria(met.getCFormaMigratoria().replace("anyType{}", ""));
                    solicitud.setpRFC(met.getRFC().replace("anyType{}", ""));
                    solicitud.setpCalleDomicilio(met.getCalleDomicilio().replace("anyType{}", ""));
                    solicitud.setpNumeroExteriorDomicilio(met.getNumeroExteriorDomicilio().replace("anyType{}", ""));
                    solicitud.setpNumeroInteriorDomicilio(met.getNumeroInteriorDomicilio().replace("anyType{}", ""));
                    solicitud.setpClaveLocalidadDomicilio(met.getLocalidad().replace("anyType{}", ""));
                    solicitud.setTipoVivienda(met.getCTipovivienda().replace("anyType{}", ""));
                    solicitud.setpClaveLadaDomicilio(met.getClaveLadaDomicilio().replace("anyType{}", ""));
                    solicitud.setpTelefonoDomicilio(met.getTelefonoDomicilio().replace("anyType{}", ""));
                    solicitud.setpCTipoProducto(met.getCTipoProducto().replace("anyType{}", ""));
                    solicitud.setAñosAntiguedad(met.getAntiguedadDomicilio().replace("anyType{}", ""));
                    solicitud.setUniversidad(met.getCUniversidad().replace("anyType{}", ""));
                    solicitud.setCampus(met.getCampus().replace("anyType{}", ""));
                    solicitud.setCarrera(met.getCCarrera().replace("anyType{}", ""));
                    solicitud.setTipoFinanciamiento(met.getCFinanciamiento().replace("anyType{}", ""));
                    solicitud.setBeca(met.getPorcentajeBeca().replace("anyType{}", ""));
                    solicitud.setGradoEscolar(met.getCGradoEscolar().replace("anyType{}", ""));
                    solicitud.setIngresoNetoFijo(met.getIngresoNetoFijo().replace("anyType{}", ""));
                    solicitud.setIngresoNetoVariable(met.getIngresoNetoVariable().replace("anyType{}", ""));
                    solicitud.setProfesionistasIndependientes(met.getProfesionistasIndependientes().replace("anyType{}", ""));
                    solicitud.setVentasAnuales(met.getVentasAnuales().replace("anyType{}", ""));
                    solicitud.setClaveLocalidadEmpleo(met.getClaveLadaEmpleo().replace("anyType{}", ""));
                    solicitud.setTelEmpleo(met.getTelefonoEmpleo().replace("anyType{}", ""));
                    solicitud.setExtension(met.getExtension().replace("anyType{}", ""));
                    solicitud.setOcupacion(met.getCOcupacion().replace("anyType{}", ""));
                    solicitud.setAñosAntiguedadEmpleo(met.getAntiguedadEmpleo().replace("anyType{}", ""));
                    solicitud.setTelCelular(met.getCelular().replace("anyType{}", ""));
                    solicitud.setCorreoElectronico(met.getCorreoElectronico().replace("anyType{}", ""));
                    solicitud.setNombreEmpresa(met.getNombreEmpresa().replace("anyType{}", ""));
                    solicitud.setCalleEmpresa(met.getCalleEmpresa().replace("anyType{}", ""));
                    solicitud.setNumExteriorEmpresa(met.getNumeroExteriorEmpresa().replace("anyType{}", ""));
                    solicitud.setNumInteriorEmpresa(met.getNumeroInteriorEmpresa().replace("anyType{}", ""));
                    solicitud.setClaveLocalidadEmpleo(met.getLocalidadEmpresa().replace("anyType{}", ""));
                    /**Agregar referencias***********************************/
                    solicitud.setEstatusCapturaBasica(met.getEstatusCapturaBasica().replace("anyType{}", ""));
                    solicitud.setEstatusCapturaAutenticacion(met.getEstatusAutenticacion().replace("anyType{}", ""));
                    solicitud.setEstatusCapturaEvaluacion(met.getEstatusEvaluacion().replace("anyType{}", ""));
                    solicitud.setEstatusCapturaCierreVenta(met.getEstatusCapturaReferencias().replace("anyType{}", ""));
                    cambiaSolicitudPendiente();
                    break;
                }
            }
        }
    }

    protected void cambiaSolicitudPendiente() {
        Intent intSolicitud = new Intent(Menu.this, Solicitud.class);
        intSolicitud.putExtra("fromPendientes", true);
        startActivity(intSolicitud);
        finish();
    }
}
