package com.dim.self2f;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dim.self2f.clases.ClsCalcularRFCWs;
import com.dim.self2f.clases.ClsMensaje;
import com.dim.self2f.clases.ClsSolicitud;
import com.dim.self2f.clases.ClsUsuarioF2F;
import com.dim.self2f.clases.ClsValidaCampos;
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
import com.dim.self2f.db.ItemCatTipoProducto;
import com.dim.self2f.db.ItemCatTipoVivienda;
import com.dim.self2f.db.ItemCatUniversidad;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Solicitud extends Activity{
    ClsValidaCampos validaCampos = new ClsValidaCampos();
    ClsSolicitud solicitud = ClsSolicitud.getInstancia();
    ClsMensaje mensaje = ClsMensaje.getInstancia();
    ClsUsuarioF2F usuariof2f = ClsUsuarioF2F.getInstancia();
    //ClsCalcularRFC calculaRFC;
    ClsCalcularRFCWs calculaRFC;
    List<ItemCatEstadoCivil> listEstadoCivil;
    ArrayAdapter<String> adapterEstadoCivil;
    ArrayList<String> estadoCivil;
    List<ItemCatSexo> listSexo;
    ArrayAdapter<String> adapterSexo;
    ArrayList<String> sexo;
    List<ItemCatNacionalidad> listNacionalidades;
    ArrayAdapter<String> adapterNacionalidades;
    ArrayList<String> nacionalidades;
    List<ItemCatFormaMigratoria> listFormaMigratoria;
    ArrayAdapter<String> adapterFormaMigratoria;
    ArrayList<String> formasMigratorias;
    List<ItemCatTipoProducto> listProductos;
    ArrayAdapter<String> adapterProductos;
    ArrayList<String> productos;
    List<ItemCatTipoVivienda> listViviendas;
    ArrayAdapter<String> adapterViviendas;
    ArrayList<String> viviendas;
    EditText eTTipoVivienda;
    List<ItemCatProfesiones> listOcupacion;
    ArrayAdapter<String> adapterOcupacion;
    ArrayList<String> ocupaciones;
    List<ItemCatTipoFinanciamiento> listTipoFinanciamiento;
    ArrayAdapter<String> adapterTipoFinanciamiento;
    ArrayList<String> tipoFinanciamiento;
    EditText etTipoFinanciamiento;
    List<ItemCatUniversidad> listUniversidad;
    ArrayAdapter<String> adapterUniversidad;
    ArrayList<String> universidades;
    EditText etUniversidad;
    List<ItemCatNombreCarrera> listCarreras;
    ArrayAdapter<String> adapterCarreras;
    ArrayList<String> carreras;
    EditText etCarrera;
    List<ItemCatGradoEscolar> listGradoEscolar;
    ArrayAdapter<String> adapterGradoEscolar;
    ArrayList<String> gradoEscolar;
    EditText etGradoEscolar;
    TextView tVTipoBeca;
    EditText eTTipoBeca;
    TextView tVTipoBecaObl;
    TextView tVGradoEscolar;
    TextView tVFormaMigratoria;
    TextView tVFormaMigratoriaObl;
    TextView tvRfc;
    RelativeLayout.LayoutParams params;
    RelativeLayout.LayoutParams paramsGradoEscolar;
    RadioGroup rGPreg1;
    RadioGroup rGPreg2;
    RadioGroup rGPreg3;
    RelativeLayout rLayoutOpcionesPreg1;
    TextView tVPreg2;
    RelativeLayout.LayoutParams params2;
    EditText eTOcupacion;
    RelativeLayout rLayoutOcupacion;
    RelativeLayout.LayoutParams params3;
    TextView tVLadaEmpleo;
    private int year;
    private int month;
    private int day;
    DataBaseHelper helper;
    List<ItemCatLocalidades> listWithCP;
    ArrayAdapter<String> adapterEstados;
    ArrayList<String> estados;
    ArrayAdapter<String> adapterDeloMunicipio;
    ArrayList<String> delOMunicipios;
    ArrayAdapter<String> adapterColonias;
    ArrayList<String> colonias;
    EditText eTApPaterno;
    EditText eTApMaterno;
    EditText eTPNombre;
    EditText eTSNombre;
    EditText etEstadoCivil;
    EditText etSexo;
    EditText eTFechaNacimiento;
    EditText etNacionalidad;
    EditText eTFormaMigratoria;
    EditText eTRFC;
    EditText eTCalle;
    EditText eTNumExterior;
    EditText eTNumInterior;
    EditText eTCodigoPostal;
    EditText eTColonia;
    EditText eTDeloMunicipio;
    EditText eTEstado;
    EditText eTLadaDom;
    EditText eTTelDom;
    EditText eTTipoProducto;
    List<ItemCatBancos> listBancos;
    ArrayAdapter<String> adapterBancos;
    ArrayList<String> bancos;
    EditText etBancos;
    ArrayList<String> sbErrorCapBasica;
    Dialog customDialog;
    TextView tVTitulo;
    TextView tVContenido;
    EditText eTultimos4Digitos;
    RadioButton rbOpcionSIPreg1;
    RadioButton rbOpcionNOPreg1;
    EditText eTañosAntiguedad;
    EditText eTIngresoNetoFijo;
    EditText eTIngresoNetoVariable;
    EditText eTVentasAnuales;
    EditText eTProfesionistasIndep;
    EditText eTCampus;
    EditText eTLadaEmpleo;
    EditText eTTelEmpleo;
    EditText eTExtension;
    EditText eTAñosAntiguedadEmpleo;
    EditText eTCorreoElectronico;
    EditText eTTelCelular;
    EditText eTCodigoPostalEmpresa;
    EditText eTColoniaEmpresa;
    EditText eTDeloMunicipioEmpresa;
    EditText eTEstadoEmpresa;
    List<ItemCatLocalidades> listWithCPEmpresa;
    ArrayAdapter<String> adapterEstadosEmpresa;
    ArrayList<String> estadosEmpresa;
    ArrayAdapter<String> adapterDeloMunicipioEmpresa;
    ArrayList<String> delOMunicipiosEmpresa;
    ArrayAdapter<String> adapterColoniasEmpresa;
    ArrayList<String> coloniasEmpresa;
    EditText eTNombreEmpresa;
    TextView tVTipodeOcupacionNegocioP;
    EditText eTCalleEmpresa;
    EditText eTNumExtEmpresa;
    EditText eTNumIntEmpresa;
    EditText eTApPaternoRef1;
    EditText eTApMaternoRef1;
    EditText eTPrimerNombreRef1;
    EditText eTSegundoNombreRef1;
    EditText eTLadaDomRef1;
    EditText eTTelDomRef1;
    EditText eTLadaEmpleoRef1;
    EditText eTTelEmpleoRef1;
    EditText eTExtensionRef1;
    EditText eTCelularRef1;
    EditText eTApPaternoRef2;
    EditText eTApMaternoRef2;
    EditText eTPrimerNombreRef2;
    EditText eTSegundoNombreRef2;
    EditText eTLadaDomRef2;
    EditText eTTelDomRef2;
    EditText eTLadaEmpleoRef2;
    EditText eTTelEmpleoRef2;
    EditText eTExtensionRef2;
    EditText eTCelularRef2;
    TextView tVContFolioAgencia;
    TextView tVContFolioInteligente;
    TextView tVContNombreCompleto;
    TextView tVContEstatus;
    TextView tVContDictamen;
    TextView tVContSituacion;
    TextView tVContFechaDictamen;
    Button bttnContinuarDictamen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("");
        Bundle b = getIntent().getExtras();
        if (b != null) {
            if (b.getBoolean("fromPendientes")) {
                if (solicitud.getEstatusCapturaBasica().toString().matches("Activa")) {
                    findViewById(R.id.iCapturaBasica).setVisibility(View.VISIBLE);
                    findViewById(R.id.iAutenticacion).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iCapturaEvaluacion).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iCierreVenta).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iDictamen).setVisibility(View.INVISIBLE);
                }else if (solicitud.getEstatusCapturaAutenticacion().toString().matches("Activa")){
                    findViewById(R.id.iCapturaBasica).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iAutenticacion).setVisibility(View.VISIBLE);
                    findViewById(R.id.iCapturaEvaluacion).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iCierreVenta).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iDictamen).setVisibility(View.INVISIBLE);
                }else if (solicitud.getEstatusCapturaEvaluacion().toString().matches("Activa")) {
                    findViewById(R.id.iCapturaBasica).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iAutenticacion).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iCapturaEvaluacion).setVisibility(View.VISIBLE);
                    findViewById(R.id.iCierreVenta).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iDictamen).setVisibility(View.INVISIBLE);
                }else if (solicitud.getEstatusCapturaCierreVenta().toString().matches("Activa")) {
                    findViewById(R.id.iCapturaBasica).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iAutenticacion).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iCapturaEvaluacion).setVisibility(View.INVISIBLE);
                    findViewById(R.id.iCierreVenta).setVisibility(View.VISIBLE);
                    findViewById(R.id.iDictamen).setVisibility(View.INVISIBLE);
                }
            }
        }else {
            findViewById(R.id.iCapturaBasica).setVisibility(View.VISIBLE);
            findViewById(R.id.iAutenticacion).setVisibility(View.INVISIBLE);
            findViewById(R.id.iCapturaEvaluacion).setVisibility(View.INVISIBLE);
            findViewById(R.id.iCierreVenta).setVisibility(View.INVISIBLE);
            findViewById(R.id.iDictamen).setVisibility(View.INVISIBLE);
        }
        params = (RelativeLayout.LayoutParams)findViewById(R.id.tVRFC).getLayoutParams();
        paramsGradoEscolar = (RelativeLayout.LayoutParams)findViewById(R.id.tVGradoEscolar).getLayoutParams();
        etEstadoCivil = (EditText)findViewById(R.id.eTestadoCivil);
        etSexo = (EditText)findViewById(R.id.eTsexo);
        etNacionalidad = (EditText)findViewById(R.id.eTNacionalidad);
        eTTipoProducto = (EditText)findViewById(R.id.eTTipoProducto);
        eTTipoVivienda = (EditText)findViewById(R.id.eTTipoVivienda);
        tVFormaMigratoria = (TextView)findViewById(R.id.tVFormaMigratoria);
        eTFormaMigratoria = (EditText)findViewById(R.id.eTFormaMigratoria);
        tVFormaMigratoriaObl = (TextView)findViewById(R.id.tVFormaMigratoriaObl);
        etTipoFinanciamiento = (EditText)findViewById(R.id.eTTipoFinanciamiento);
        tVTipoBeca = (TextView)findViewById(R.id.tVBeca);
        eTTipoBeca = (EditText)findViewById(R.id.eTBeca);
        tVTipoBecaObl = (TextView)findViewById(R.id.tVBecaObl);
        tVGradoEscolar = (TextView)findViewById(R.id.tVGradoEscolar);
        tVTipoBeca.setVisibility(View.INVISIBLE);
        eTTipoBeca.setVisibility(View.INVISIBLE);
        tVTipoBecaObl.setVisibility(View.INVISIBLE);
        tvRfc = (TextView)findViewById(R.id.tVRFC);
        tVFormaMigratoria.setVisibility(View.INVISIBLE);
        eTFormaMigratoria.setVisibility(View.INVISIBLE);
        tVFormaMigratoriaObl.setVisibility(View.INVISIBLE);
        etUniversidad = (EditText)findViewById(R.id.eTUniversidad);
        etCarrera = (EditText)findViewById(R.id.eTCarrera);
        etGradoEscolar = (EditText)findViewById(R.id.eTGradoEscolar);
        rLayoutOpcionesPreg1 = (RelativeLayout)findViewById(R.id.rLayoutOpcionesPreg1);
        tVPreg2 = (TextView)findViewById(R.id.tVPreg2);
        params2 = (RelativeLayout.LayoutParams)findViewById(R.id.tVPreg2).getLayoutParams();
        params.setMargins(0, -35, 0, 0);
        tvRfc.setLayoutParams(params);
        paramsGradoEscolar.setMargins(0, -35, 0, 0);
        tVGradoEscolar.setLayoutParams(paramsGradoEscolar);
        params2.setMargins(0, -60, 0, 0);
        tVPreg2.setLayoutParams(params2);
        rGPreg1 = (RadioGroup)findViewById(R.id.rGPreg1);
        rGPreg2 = (RadioGroup)findViewById(R.id.rGPreg2);
        rGPreg3 = (RadioGroup)findViewById(R.id.rGPreg3);
        eTApPaterno = (EditText)findViewById(R.id.eTApPaterno);
        eTApMaterno = (EditText)findViewById(R.id.eTApMaterno);
        eTPNombre = (EditText)findViewById(R.id.eTpNombre);
        eTSNombre = (EditText)findViewById(R.id.eTsNombre);
        eTFechaNacimiento = (EditText)findViewById(R.id.eTfechaNac);
        eTRFC = (EditText)findViewById(R.id.eTRFC);
        eTCalle = (EditText)findViewById(R.id.eTCalle);
        eTNumExterior = (EditText)findViewById(R.id.eTNumExterior);
        eTNumInterior = (EditText)findViewById(R.id.eTNumInterior);
        eTLadaDom = (EditText)findViewById(R.id.eTLadaDom);
        eTTelDom = (EditText)findViewById(R.id.eTTelDom);
        etBancos = (EditText)findViewById(R.id.eTtipoTarjetaBancaria);
        helper = new DataBaseHelper(getApplicationContext());
        customDialog = new Dialog(Solicitud.this, R.style.Theme_Dialog_Translucent);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setCancelable(false);;
        customDialog.setContentView(R.layout.dialogo_aceptar);
        tVTitulo = (TextView)customDialog.findViewById(R.id.tVTituloDialogo);
        tVTitulo.setGravity(Gravity.CENTER);
        tVContenido = (TextView)customDialog.findViewById(R.id.tVContenido);
        tVContenido.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL);
        llenaCombos();
        calculaRFC = new ClsCalcularRFCWs();
        eTultimos4Digitos = (EditText)findViewById(R.id.eTultimos4Digitos);
        rbOpcionSIPreg1 = (RadioButton)findViewById(R.id.rOpcionSIPreg1);
        rbOpcionNOPreg1 = (RadioButton)findViewById(R.id.rOpcionNOPreg1);
        rbOpcionNOPreg1.setChecked(true);
        solicitud.setpExisteTarjeta(false);
        eTañosAntiguedad = (EditText)findViewById(R.id.eTAñosAntiguedad);
        eTIngresoNetoFijo = (EditText)findViewById(R.id.eTIngresoNetoFijo);
        eTIngresoNetoVariable = (EditText)findViewById(R.id.eTIngresoNetoVariable);
        eTVentasAnuales = (EditText)findViewById(R.id.eTVentasAnuales);
        eTProfesionistasIndep = (EditText)findViewById(R.id.eTProfesionistasIndep);
        eTCampus = (EditText)findViewById(R.id.eTCampus);
        eTLadaEmpleo = (EditText)findViewById(R.id.eTLadaEmpleo);
        eTTelEmpleo = (EditText)findViewById(R.id.eTTelEmpleo);
        eTExtension = (EditText)findViewById(R.id.eTExtension);
        eTAñosAntiguedadEmpleo = (EditText)findViewById(R.id.eTAñosAntiguedadEmpleo);
        eTCorreoElectronico = (EditText)findViewById(R.id.eTCorreoElectronico);
        eTTelCelular = (EditText)findViewById(R.id.eTTelCelular);
        eTCodigoPostalEmpresa = (EditText)findViewById(R.id.eTCodigoPostalEmpresa);
        eTColoniaEmpresa = (EditText)findViewById(R.id.eTColoniaEmpresa);
        eTDeloMunicipioEmpresa = (EditText)findViewById(R.id.eTMunicipioEmpresa);
        eTEstadoEmpresa = (EditText)findViewById(R.id.eTEstadoEmpresa);
        eTNombreEmpresa = (EditText)findViewById(R.id.eTNombreEmpresa);
        tVTipodeOcupacionNegocioP = (TextView)findViewById(R.id.tVTipodeOcupacionNegocioP);
        eTCalleEmpresa = (EditText)findViewById(R.id.eTCalleEmpresa);
        eTNumExtEmpresa = (EditText)findViewById(R.id.eTNumExtEmpresa);
        eTNumIntEmpresa = (EditText)findViewById(R.id.eTNumIntEmpresa);
        eTApPaternoRef1 = (EditText)findViewById(R.id.eTApPaternoRef1);
        eTApMaternoRef1 = (EditText)findViewById(R.id.eTApMaternoRef1);
        eTPrimerNombreRef1 = (EditText)findViewById(R.id.eTPrimerNombreRef1);
        eTSegundoNombreRef1 = (EditText)findViewById(R.id.eTSegundoNombreRef1);
        eTLadaDomRef1 = (EditText)findViewById(R.id.eTLadaDomRef1);
        eTTelDomRef1 = (EditText)findViewById(R.id.eTTelDomRef1);
        eTLadaEmpleoRef1 = (EditText)findViewById(R.id.eTLadaEmpleoRef1);
        eTTelEmpleoRef1 = (EditText)findViewById(R.id.eTTelEmpleoRef1);
        eTExtensionRef1 = (EditText)findViewById(R.id.eTExtensionRef1);
        eTCelularRef1 = (EditText)findViewById(R.id.eTCelularRef1);
        eTApPaternoRef2 = (EditText)findViewById(R.id.eTApPaternoRef2);
        eTApMaternoRef2 = (EditText)findViewById(R.id.eTApMaternoRef2);
        eTPrimerNombreRef2 = (EditText)findViewById(R.id.eTPrimerNombreRef2);
        eTSegundoNombreRef2 = (EditText)findViewById(R.id.eTSegundoNombreRef2);
        eTLadaDomRef2 = (EditText)findViewById(R.id.eTLadaDomRef2);
        eTTelDomRef2 = (EditText)findViewById(R.id.eTTelDomRef2);
        eTLadaEmpleoRef2 = (EditText)findViewById(R.id.eTLadaEmpleoRef2);
        eTTelEmpleoRef2 = (EditText)findViewById(R.id.eTTelEmpleoRef2);
        eTExtensionRef2 = (EditText)findViewById(R.id.eTExtensionRef2);
        eTCelularRef2 = (EditText)findViewById(R.id.eTCelularRef2);
        rLayoutOcupacion = (RelativeLayout)findViewById(R.id.rLayoutOcupacion);
        tVLadaEmpleo = (TextView)findViewById(R.id.tVLadaEmpleo);
        params3 = (RelativeLayout.LayoutParams)findViewById(R.id.tVLadaEmpleo).getLayoutParams();
        eTOcupacion = (EditText)findViewById(R.id.eTOcupacion);
        rLayoutOcupacion.setVisibility(View.INVISIBLE);
        params3.setMargins(0, -300, 0, 0);
        tVLadaEmpleo.setLayoutParams(params3);
        setFechaActual();
        eTCodigoPostal = (EditText)findViewById(R.id.eTCodigoPostal);
        eTEstado = (EditText)findViewById(R.id.eTEstado);
        eTDeloMunicipio = (EditText)findViewById(R.id.eTDelegacion);
        eTColonia = (EditText)findViewById(R.id.eTColonia);
        tVContFolioAgencia = (TextView)findViewById(R.id.tVContFolioAgencia);
        tVContFolioInteligente = (TextView)findViewById(R.id.tVContFolioInteligente);
        tVContNombreCompleto = (TextView)findViewById(R.id.tVContNombreCompleto);
        tVContEstatus = (TextView)findViewById(R.id.tVContEstatus);
        tVContDictamen = (TextView)findViewById(R.id.tVContDictamen);
        tVContSituacion = (TextView)findViewById(R.id.tVContSituacion);
        tVContFechaDictamen = (TextView)findViewById(R.id.tVContFechaDictamen);
        bttnContinuarDictamen = (Button)findViewById(R.id.bttnContinuarDictamen);
        etNacionalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneNacionlidad();
            }
        });
        eTTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneProducto();
            }
        });
        etBancos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneBanco();
            }
        });

        eTFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionaFechaNacimiento();
            }
        });

        eTOcupacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionaOcupacion();
            }
        });
        rGPreg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rOpcionSIPreg1:
                        rLayoutOpcionesPreg1.setVisibility(View.VISIBLE);
                        params2.setMargins(0, 20, 0, 0);
                        tVPreg2.setLayoutParams(params2);
                        solicitud.setpExisteTarjeta(true);
                        break;
                    case R.id.rOpcionNOPreg1:
                        rLayoutOpcionesPreg1.setVisibility(View.INVISIBLE);
                        params2.setMargins(0, -60, 0, 0);
                        tVPreg2.setLayoutParams(params2);
                        solicitud.setpExisteTarjeta(false);
                        break;
                }
            }
        });
        rGPreg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rOpcionSIPreg2:
                        solicitud.setpExisteCreditoHipotecario(true);
                        break;
                    case R.id.rOpcionNOPreg2:
                        solicitud.setpExisteCreditoHipotecario(false);
                        break;
                }
            }
        });
        rGPreg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rOpcionSIPreg3:
                        solicitud.setpExisteCreditoAutomotriz(true);
                        break;
                    case R.id.rOpcionNOPreg3:
                        solicitud.setpExisteCreditoAutomotriz(false);
                        break;
                }
            }
        });
        eTCodigoPostal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (eTCodigoPostal.getText().toString().length() == 5) {
                    consultaCP(eTCodigoPostal.getText().toString(), 1);
                }
            }
        });
        eTCodigoPostalEmpresa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (eTCodigoPostalEmpresa.getText().toString().length() == 5) {
                    consultaCP(eTCodigoPostalEmpresa.getText().toString(), 2);
                }
            }
        });
        eTColonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colonias != null) {
                    if (colonias.size() > 0) {
                        seleccioneColonia(1);
                    }
                }else {
                    eTLadaDom.requestFocus();
                }
            }
        });
        eTColoniaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coloniasEmpresa != null) {
                    if (coloniasEmpresa.size() > 0) {
                        seleccioneColonia(2);
                    }
                }else {
                    eTApPaternoRef1.requestFocus();
                }
            }
        });
        eTDeloMunicipio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delOMunicipios != null) {
                    if (delOMunicipios.size() > 0) {
                        seleccioneDeloMunicipio(1);
                    }
                }
                else {
                    eTLadaDom.requestFocus();
                }
            }
        });
        eTDeloMunicipioEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delOMunicipiosEmpresa != null) {
                    if (delOMunicipiosEmpresa.size() > 0) {
                        seleccioneDeloMunicipio(2);
                    }
                }else {
                    eTApPaternoRef1.requestFocus();
                }
            }
        });
        eTEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estados != null) {
                    if (estados.size() > 0) {
                        seleccioneEstado(1);
                    }
                }
                else {
                    eTLadaDom.requestFocus();
                }
            }
        });
        eTEstadoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estadosEmpresa != null) {
                    if (estadosEmpresa.size() > 0) {
                        seleccioneEstado(2);
                    }
                }else {
                    eTApPaternoRef1.requestFocus();
                }
            }
        });
        etEstadoCivil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneEstadoCivil();
            }
        });
        etSexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneSexo();
            }
        });
        eTFormaMigratoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneTipoFormaMigratoria();
            }
        });
        eTTipoVivienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneTipoVivienda();

            }
        });
        etTipoFinanciamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccioneTipoFinanciamiento();
            }
        });
        etUniversidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rLayoutOcupacion.getVisibility() == View.VISIBLE) {
                    seleccioneUniversidad();
                }
            }
        });
        etCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rLayoutOcupacion.getVisibility() == View.VISIBLE) {
                    seleccioneCarrera();
                }
            }
        });
        etGradoEscolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rLayoutOcupacion.getVisibility() == View.VISIBLE) {
                    seleccioneGradoEscolar();
                }
            }
        });
        eTApPaterno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTApPaterno.getText().toString().matches("")) {
                    eTApPaterno.setText(eTApPaterno.getText().toString().toUpperCase());
                    if (!eTPNombre.getText().toString().matches("") && !eTFechaNacimiento.getText().toString().matches("")) {
                        calculaRFCPerdiendoFoco();
                    }
                }
            }
        });
        eTApMaterno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTApMaterno.getText().toString().matches("")) {
                    eTApMaterno.setText(eTApMaterno.getText().toString().toUpperCase());
                    if (!eTApPaterno.getText().toString().matches("") && !eTPNombre.getText().toString().matches("")
                            && !eTFechaNacimiento.getText().toString().matches("")) {
                        calculaRFCPerdiendoFoco();
                    }
                }else {
                    if (!eTApPaterno.getText().toString().matches("") && !eTPNombre.getText().toString().matches("")
                            && !eTFechaNacimiento.getText().toString().matches("")) {
                        calculaRFCPerdiendoFoco();
                    }
                }
            }
        });
        eTPNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTPNombre.getText().toString().matches("")) {
                    eTPNombre.setText(eTPNombre.getText().toString().toUpperCase());
                    if (!eTApPaterno.getText().toString().matches("") && !eTFechaNacimiento.getText().toString().matches("")) {
                        calculaRFCPerdiendoFoco();
                    }
                }

            }
        });
        eTSNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTSNombre.getText().toString().matches("")) {
                    eTSNombre.setText(eTSNombre.getText().toString().toUpperCase());
                    if (!eTApPaterno.getText().toString().matches("") && !eTPNombre.getText().toString().matches("")
                            && !eTFechaNacimiento.getText().toString().matches("")) {
                        calculaRFCPerdiendoFoco();
                    }
                }else {
                    if (!eTApPaterno.getText().toString().matches("") && !eTPNombre.getText().toString().matches("")
                            && !eTFechaNacimiento.getText().toString().matches("")) {
                        calculaRFCPerdiendoFoco();
                    }
                }
            }
        });
        eTCalle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTCalle.getText().toString().matches("")) {
                    eTCalle.setText(eTCalle.getText().toString().toUpperCase());
                }
            }
        });
        eTNumExterior.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTNumExterior.getText().toString().matches("")) {
                    eTNumExterior.setText(eTNumExterior.getText().toString().toUpperCase());
                }
            }
        });
        eTNumInterior.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTNumInterior.getText().toString().matches("")) {
                    eTNumInterior.setText(eTNumInterior.getText().toString().toUpperCase());
                }
            }
        });
        eTRFC.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!eTApPaterno.getText().toString().matches("") && !eTPNombre.getText().toString().matches("")
                        && !eTFechaNacimiento.getText().toString().matches("")) {
                    calculaRFCPerdiendoFoco();
                }
            }
        });

    }

    public void llenaCombos() {
        listNacionalidades = new ArrayList<ItemCatNacionalidad>();
        listNacionalidades = helper.getDataCatNacionalidad();
        nacionalidades = new ArrayList<String>();
        if (listNacionalidades != null) {
            for (int i = 0; i < listNacionalidades.size(); i++) {
                nacionalidades.add(listNacionalidades.get(i).getNacionalidad().toString().toUpperCase());
            }
        }
        adapterNacionalidades = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, nacionalidades);
        for (int i = 0; i < nacionalidades.size(); i++) {
            if (nacionalidades.get(i).toString().matches("MEXICO")) {
                etNacionalidad.setText(nacionalidades.get(i).toString().toUpperCase());
                break;
            }
        }
        listFormaMigratoria = new ArrayList<ItemCatFormaMigratoria>();
        listFormaMigratoria = helper.getDataCatFormaMigratoria();
        formasMigratorias = new ArrayList<String>();
        if (listFormaMigratoria != null) {
            for (int i = 0; i < listFormaMigratoria.size(); i++) {
                formasMigratorias.add(listFormaMigratoria.get(i).getFormaMigratoria().toString().toUpperCase());
            }
        }
        adapterFormaMigratoria = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, formasMigratorias);
        listProductos = new ArrayList<ItemCatTipoProducto>();
        listProductos = helper.getDataCatTipoProducto();
        productos = new ArrayList<String>();
        if (listProductos != null) {
            for (int i = 0; i < listProductos.size(); i++) {
                productos.add(listProductos.get(i).getProducto().toString().toUpperCase());
            }
        }
        adapterProductos = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, productos);
        listEstadoCivil = new ArrayList<ItemCatEstadoCivil>();
        listEstadoCivil = helper.getDataCatEstadoCivil();
        estadoCivil = new ArrayList<String>();
        if (listEstadoCivil != null) {
            for (int i = 0; i < listEstadoCivil.size(); i++) {
                estadoCivil.add(listEstadoCivil.get(i).getDescripcion().toString().toUpperCase());
            }
        }
        adapterEstadoCivil = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, estadoCivil);
        listSexo = new ArrayList<ItemCatSexo>();
        listSexo = helper.getDataCatSexo();
        sexo = new ArrayList<String>();
        if (listSexo != null) {
            for (int i = 0; i < listSexo.size(); i++) {
                sexo.add(listSexo.get(i).getSexo().toString().toUpperCase());
            }
        }
        adapterSexo = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, sexo);
        listViviendas = new ArrayList<ItemCatTipoVivienda>();
        listViviendas = helper.getDataCatTipoVivienda();
        viviendas = new ArrayList<String>();
        if (listViviendas != null) {
            for (int i = 0; i < listViviendas.size(); i++) {
                viviendas.add(listViviendas.get(i).getTipoVivienda().toString().toUpperCase());
            }
        }
        adapterViviendas = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, viviendas);
        listOcupacion = new ArrayList<ItemCatProfesiones>();
        listOcupacion = helper.getDataCatProfesiones();
        ocupaciones = new ArrayList<String>();
        if (listOcupacion != null) {
            for (int i = 0; i < listOcupacion.size(); i++) {
                ocupaciones.add(listOcupacion.get(i).getProfesion().toString().toUpperCase());
            }
        }
        adapterOcupacion = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, ocupaciones);
        listTipoFinanciamiento = new ArrayList<ItemCatTipoFinanciamiento>();
        listTipoFinanciamiento = helper.getDataCatTipoFinanciamiento();
        tipoFinanciamiento = new ArrayList<String>();
        if (listTipoFinanciamiento != null) {
            for (int i = 0; i < listTipoFinanciamiento.size(); i++) {
                tipoFinanciamiento.add(listTipoFinanciamiento.get(i).getTipoFinanciamiento().toString().toUpperCase());
            }
        }
        adapterTipoFinanciamiento = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, tipoFinanciamiento);
        listUniversidad = new ArrayList<ItemCatUniversidad>();
        listUniversidad = helper.getDataCatUniversidad();
        universidades = new ArrayList<String>();
        if (listUniversidad != null) {
            for (int i = 0; i < listUniversidad.size(); i++) {
                universidades.add(listUniversidad.get(i).getUniversidad().toString().toUpperCase());
            }
        }
        adapterUniversidad = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, universidades);
        listCarreras = new ArrayList<ItemCatNombreCarrera>();
        listCarreras = helper.getDataCatNombreCarrera();
        carreras = new ArrayList<String>();
        if (listCarreras != null) {
            for (int i = 0; i < listCarreras.size(); i++) {
                carreras.add(listCarreras.get(i).getNombreCarrera().toString().toUpperCase());
            }
        }
        adapterCarreras = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, carreras);
        listGradoEscolar = new ArrayList<ItemCatGradoEscolar>();
        listGradoEscolar = helper.getDataCatGradoEscolar();
        gradoEscolar = new ArrayList<String>();
        if (listGradoEscolar != null) {
            for (int i = 0; i < listGradoEscolar.size(); i++) {
                gradoEscolar.add(listGradoEscolar.get(i).getGrado().toString().toUpperCase());
            }
        }
        adapterGradoEscolar = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, gradoEscolar);
        listBancos = new ArrayList<ItemCatBancos>();
        listBancos = helper.getDataCatBancos();
        bancos = new ArrayList<String>();
        if (listBancos != null) {
            for (int i = 0; i < listBancos.size(); i++) {
                bancos.add(listBancos.get(i).getDescripcion().toString());
            }
        }
        adapterBancos = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, bancos);
    }

    protected void consultaCP(String cp, int DomOEmp) {
        listWithCP = helper.getDatosWithCP(cp);
        if (listWithCP != null) {
            if (listWithCP.size() > 0) {
                if (DomOEmp == 1) {
                    colonias = new ArrayList<String>();
                    delOMunicipios = new ArrayList<String>();
                    estados = new ArrayList<String>();
                    for (int i = 0; i < listWithCP.size(); i++) {
                        colonias.add(listWithCP.get(i).getColonia().toString());
                        delOMunicipios.add(listWithCP.get(i).getMunicipio().toString());
                        estados.add(listWithCP.get(i).getEstado().toString());
                    }
                    Iterator<String> itrEstados = estados.iterator();
                    String edo = "";
                    edo = itrEstados.next();
                    while (itrEstados.hasNext()) {
                        if (edo.equals(itrEstados.next())) {
                            itrEstados.remove();
                        }else {
                            edo = itrEstados.next();
                        }
                    }
                    Iterator<String> itrMunicipios = delOMunicipios.iterator();
                    String mpio = "";
                    mpio = itrMunicipios.next();
                    while (itrMunicipios.hasNext()) {
                        if (mpio.equals(itrMunicipios.next())) {
                            itrMunicipios.remove();
                        }else {
                            mpio = itrMunicipios.next();
                        }
                    }
                    adapterColonias = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, colonias);
                    adapterDeloMunicipio = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, delOMunicipios);
                    adapterEstados = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, estados);
                    eTColonia.setText(colonias.get(0).toString());
                    eTDeloMunicipio.setText(delOMunicipios.get(0).toString());
                    eTEstado.setText(estados.get(0).toString());
                }else {
                    coloniasEmpresa = new ArrayList<String>();
                    delOMunicipiosEmpresa = new ArrayList<String>();
                    estadosEmpresa = new ArrayList<String>();
                    for (int i = 0; i < listWithCP.size(); i++) {
                        coloniasEmpresa.add(listWithCP.get(i).getColonia().toString());
                        delOMunicipiosEmpresa.add(listWithCP.get(i).getMunicipio().toString());
                        estadosEmpresa.add(listWithCP.get(i).getEstado().toString());
                    }
                    Iterator<String> itrEstadosEmpresa = estadosEmpresa.iterator();
                    String edoEmpresa = "";
                    edoEmpresa = itrEstadosEmpresa.next();
                    while (itrEstadosEmpresa.hasNext()) {
                        if (edoEmpresa.equals(itrEstadosEmpresa.next())) {
                            itrEstadosEmpresa.remove();
                        }else {
                            edoEmpresa = itrEstadosEmpresa.next();
                        }
                    }
                    Iterator<String> itrMunicipiosEmpresa = delOMunicipiosEmpresa.iterator();
                    String mpioEmpresa = "";
                    mpioEmpresa = itrMunicipiosEmpresa.next();
                    while (itrMunicipiosEmpresa.hasNext()) {
                        if (mpioEmpresa.equals(itrMunicipiosEmpresa.next())) {
                            itrMunicipiosEmpresa.remove();
                        }else {
                            mpioEmpresa = itrMunicipiosEmpresa.next();
                        }
                    }
                    adapterColoniasEmpresa = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, coloniasEmpresa);
                    adapterDeloMunicipioEmpresa = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, delOMunicipiosEmpresa);
                    adapterEstadosEmpresa = new ArrayAdapter<String>(Solicitud.this, android.R.layout.simple_selectable_list_item, estadosEmpresa);
                    eTColoniaEmpresa.setText(coloniasEmpresa.get(0).toString());
                    eTDeloMunicipioEmpresa.setText(delOMunicipiosEmpresa.get(0).toString());
                    eTEstadoEmpresa.setText(estadosEmpresa.get(0).toString());
                }
            }else {
                eTColonia.setText("SIN DATOS");
                eTDeloMunicipio.setText("SIN DATOS");
                eTEstado.setText("SIN DATOS");
            }
        }else {
            eTColonia.setText("SIN DATOS");
            eTDeloMunicipio.setText("SIN DATOS");
            eTEstado.setText("SIN DATOS");
        }
    }

    protected void seleccioneNacionlidad() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("NACIONALIDAD:")
                .setCancelable(false)
                .setAdapter(adapterNacionalidades, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etNacionalidad.setText(nacionalidades.get(which).toString());
                        if (!etNacionalidad.getText().toString().matches("MEXICO")) {
                            tVFormaMigratoria.setVisibility(View.VISIBLE);
                            eTFormaMigratoria.setVisibility(View.VISIBLE);
                            tVFormaMigratoriaObl.setVisibility(View.VISIBLE);
                            params.setMargins(0, 25, 0, 0);
                            tvRfc.setLayoutParams(params);
                        }else {
                            tVFormaMigratoria.setVisibility(View.INVISIBLE);
                            eTFormaMigratoria.setVisibility(View.INVISIBLE);
                            tVFormaMigratoriaObl.setVisibility(View.INVISIBLE);
                            params.setMargins(0, -35, 0, 0);
                            tvRfc.setLayoutParams(params);
                        }
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneProducto() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("PRODUCTO:")
                .setCancelable(false)
                .setAdapter(adapterProductos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eTTipoProducto.setText(productos.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneBanco() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("INSTITUCIÓN:")
                .setCancelable(false)
                .setAdapter(adapterBancos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etBancos.setText(bancos.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneColonia(int DomOEmpresa) {
        if (DomOEmpresa == 1) {
            new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                    .setTitle("COLONIA:")
                    .setCancelable(false)
                    .setAdapter(adapterColonias, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eTColonia.setText(colonias.get(which).toString());
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                    .setTitle("COLONIA:")
                    .setCancelable(false)
                    .setAdapter(adapterColoniasEmpresa, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eTColoniaEmpresa.setText(coloniasEmpresa.get(which).toString());
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }

    }

    protected void seleccioneDeloMunicipio(int DomOEmpleo) {
        if (DomOEmpleo == 1) {
            new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                    .setTitle("DELEGACIÓN O MUNICIPIO:")
                    .setCancelable(false)
                    .setAdapter(adapterDeloMunicipio, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eTDeloMunicipio.setText(delOMunicipios.get(which).toString());
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                    .setTitle("DELEGACIÓN O MUNICIPIO:")
                    .setCancelable(false)
                    .setAdapter(adapterDeloMunicipioEmpresa, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eTDeloMunicipioEmpresa.setText(delOMunicipiosEmpresa.get(which).toString());
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
    }

    protected void seleccioneEstado(int DomOEmpleo) {
        if (DomOEmpleo == 1) {
            new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                    .setTitle("ESTADO:")
                    .setCancelable(false)
                    .setAdapter(adapterEstados, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eTEstado.setText(estados.get(which).toString());
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                    .setTitle("ESTADO:")
                    .setCancelable(false)
                    .setAdapter(adapterEstadosEmpresa, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eTEstadoEmpresa.setText(estadosEmpresa.get(which).toString());
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }

    }

    protected void seleccioneEstadoCivil() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("ESTADO CIVIL:")
                .setCancelable(false)
                .setAdapter(adapterEstadoCivil, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etEstadoCivil.setText(estadoCivil.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneSexo() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("SEXO:")
                .setCancelable(false)
                .setAdapter(adapterSexo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etSexo.setText(sexo.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneTipoFormaMigratoria() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("FORMA MIGRATORIA:")
                .setCancelable(false)
                .setAdapter(adapterFormaMigratoria, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eTFormaMigratoria.setText(formasMigratorias.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneTipoVivienda() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("TIPO DE VIVIENDA:")
                .setCancelable(false)
                .setAdapter(adapterViviendas, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eTTipoVivienda.setText(viviendas.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneTipoFinanciamiento() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("TIPO DE FINANCIAMIENTO:")
                .setCancelable(false)
                .setAdapter(adapterTipoFinanciamiento, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etTipoFinanciamiento.setText(tipoFinanciamiento.get(which).toString());
                        if (etTipoFinanciamiento.getText().toString().toUpperCase().matches("BECA")) {
                            tVTipoBeca.setVisibility(View.VISIBLE);
                            eTTipoBeca.setVisibility(View.VISIBLE);
                            tVTipoBecaObl.setVisibility(View.VISIBLE);
                            paramsGradoEscolar.setMargins(0, 25, 0, 0);
                            tVGradoEscolar.setLayoutParams(paramsGradoEscolar);
                        }else {
                            tVTipoBeca.setVisibility(View.INVISIBLE);
                            eTTipoBeca.setVisibility(View.INVISIBLE);
                            tVTipoBecaObl.setVisibility(View.INVISIBLE);
                            paramsGradoEscolar.setMargins(0, -35, 0, 0);
                            tVGradoEscolar.setLayoutParams(paramsGradoEscolar);
                        }
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccionaFechaNacimiento() {
        if (eTFechaNacimiento.getText().toString().matches("")) {
            DatePickerDialog dpd = new DatePickerDialog(Solicitud.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int selectedYear, int selectedMonthOfYear,
                                      int selectedDayOfMonth) {
                    if (selectedMonthOfYear < 10) {
                        if (selectedDayOfMonth < 10) {
                            eTFechaNacimiento.setText(new StringBuilder()
                                    .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append("0").append(selectedDayOfMonth).append(" "));
                        }else {
                            eTFechaNacimiento.setText(new StringBuilder()
                                    .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                        }
                    }else {
                        if (selectedDayOfMonth < 10) {
                            eTFechaNacimiento.setText(new StringBuilder()
                                    .append(selectedYear).append("-").append(selectedMonthOfYear + 1).append("-").append("0").append(selectedDayOfMonth).append(" "));
                        }else {
                            eTFechaNacimiento.setText(new StringBuilder()
                                    .append(selectedYear).append("-").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                        }
                    }
                    calculaRFCPerdiendoFoco();
                }
            }, year, month, day);
            dpd.setTitle("Seleccione \n fecha de nacimiento");
            dpd.show();
            dpd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    //validaEdad(eTFechaNacimiento.getText().toString(), 0);
                    calculaRFCPerdiendoFoco();
                }
            });
        }else {
            if (Integer.parseInt(eTFechaNacimiento.getText().toString().substring(8, 10)) < 10) {
                DatePickerDialog dpd = new DatePickerDialog(Solicitud.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonthOfYear,
                                          int selectedDayOfMonth) {
                        if (selectedMonthOfYear < 10) {
                            if (selectedDayOfMonth < 10) {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append("0").append(selectedDayOfMonth).append(" "));
                            }else {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                            }
                        }else {
                            if (selectedDayOfMonth < 10) {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                            }else {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                            }
                        }
                        calculaRFCPerdiendoFoco();
                    }
                }, Integer.parseInt(eTFechaNacimiento.getText().toString().substring(0, 4)),
                        Integer.parseInt(eTFechaNacimiento.getText().toString().substring(5, 7)) - 1,
                        Integer.parseInt(eTFechaNacimiento.getText().toString().substring(8, 10)));
                dpd.setTitle("Seleccione \n fecha de nacimiento");
                dpd.show();
                dpd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //validaEdad(eTFechaNacimiento.getText().toString(), 0);
                        calculaRFCPerdiendoFoco();
                    }
                });
            }else {
                DatePickerDialog dpd = new DatePickerDialog(Solicitud.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonthOfYear,
                                          int selectedDayOfMonth) {
                        if (selectedMonthOfYear < 10) {
                            if (selectedDayOfMonth < 10) {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append("0").append(selectedDayOfMonth).append(" "));
                            }else {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                            }
                        }else {
                            if (selectedDayOfMonth < 10) {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append("0").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                            }else {
                                eTFechaNacimiento.setText(new StringBuilder()
                                        .append(selectedYear).append("-").append(selectedMonthOfYear + 1).append("-").append(selectedDayOfMonth).append(" "));
                            }
                        }
                        calculaRFCPerdiendoFoco();
                    }
                }, Integer.parseInt(eTFechaNacimiento.getText().toString().substring(0, 4)),
                        Integer.parseInt(eTFechaNacimiento.getText().toString().substring(5, 7)) - 1,
                        Integer.parseInt(eTFechaNacimiento.getText().toString().substring(8, 10)));
                dpd.setTitle("Seleccione \n fecha de nacimiento");
                dpd.show();
                dpd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //validaEdad(eTFechaNacimiento.getText().toString(), 0);
                        calculaRFCPerdiendoFoco();
                    }
                });
            }
        }
    }

    public void calculaRFCPerdiendoFoco() {
        if (!eTSNombre.getText().toString().matches("")) {
            if (eTApMaterno.getText().toString().matches("")) {
                if (!eTPNombre.getText().toString().matches("") && !eTApPaterno.getText().toString().matches("")
                        && !eTFechaNacimiento.getText().toString().matches("")) {
                    eTRFC.setText(calculaRFC.calcularRFC(eTPNombre.getText().toString(), eTSNombre.getText().toString(),
                            eTApPaterno.getText().toString(), eTApMaterno.getText().toString(), eTFechaNacimiento.getText().toString()));
                }else {
                    eTRFC.setText("");
                }
            }else {
                if (!eTPNombre.getText().toString().matches("") && !eTApPaterno.getText().toString().matches("")
                        && !eTApMaterno.getText().toString().matches("") && !eTFechaNacimiento.getText().toString().matches("")) {
                    eTRFC.setText(calculaRFC.calcularRFC(eTPNombre.getText().toString(), eTSNombre.getText().toString(),
                            eTApPaterno.getText().toString(), eTApMaterno.getText().toString(), eTFechaNacimiento.getText().toString()));
                }else {
                    eTRFC.setText("");
                }
            }
        }else {
            if (eTApMaterno.getText().toString().matches("")) {
                if (!eTPNombre.getText().toString().matches("") && !eTApPaterno.getText().toString().matches("")
                        && !eTFechaNacimiento.getText().toString().matches("")) {
                    eTRFC.setText(calculaRFC.calcularRFC(eTPNombre.getText().toString(), eTSNombre.getText().toString(),
                            eTApPaterno.getText().toString(), eTApMaterno.getText().toString(), eTFechaNacimiento.getText().toString()));
                }else {
                    eTRFC.setText("");
                }
            }else {
                if (!eTPNombre.getText().toString().matches("") && !eTApPaterno.getText().toString().matches("")
                        && !eTApMaterno.getText().toString().matches("") && !eTFechaNacimiento.getText().toString().matches("")) {
                    eTRFC.setText(calculaRFC.calcularRFC(eTPNombre.getText().toString(), eTSNombre.getText().toString(),
                            eTApPaterno.getText().toString(), eTApMaterno.getText().toString(), eTFechaNacimiento.getText().toString()));
                }else {
                    eTRFC.setText("");
                }
            }
        }
    }

    protected void seleccioneGradoEscolar() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("GRADO ESCOLAR:")
                .setCancelable(false)
                .setAdapter(adapterGradoEscolar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etGradoEscolar.setText(gradoEscolar.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneUniversidad() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("UNIVERSIDAD:")
                .setCancelable(false)
                .setAdapter(adapterUniversidad, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etUniversidad.setText(universidades.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    protected void seleccioneCarrera() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("CARRERA:")
                .setCancelable(false)
                .setAdapter(adapterCarreras, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etCarrera.setText(carreras.get(which).toString());
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    public void setFechaActual(){
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR) - 18;
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    protected void seleccionaOcupacion() {
        new AlertDialog.Builder(Solicitud.this, AlertDialog.THEME_TRADITIONAL)
                .setTitle("OCUPACION:")
                .setCancelable(false)
                .setAdapter(adapterOcupacion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eTOcupacion.setText(ocupaciones.get(which).toString());
                        if (eTOcupacion.getText().toString().matches("UNIVERSITARIO")) {
                            rLayoutOcupacion.setVisibility(View.VISIBLE);
                            params3.setMargins(0, 20, 0, 0);
                            tVLadaEmpleo.setLayoutParams(params3);
                        }else {
                            rLayoutOcupacion.setVisibility(View.INVISIBLE);
                            params3.setMargins(0, -300, 0, 0);
                            tVLadaEmpleo.setLayoutParams(params3);
                        }
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    public void cambiaVista(View v) {
        switch (v.getId()) {
            case R.id.btnContinuarCapturaBasica:
                if (validaCamposCapturaBasica()) {
                    new enviaCapturaBasicaTask().execute();
                }else {
                    muestraErroresCapBasica();
                }
                break;
            case R.id.btnContinuarAutenticacion:
                validaCapturaAutenticacion();
                break;
            case R.id.btnContinuarCapEvaluacion:
                validaCapturaEvaluacion();
                break;
            case R.id.btnTerminarCierreVenta:
                validaCapturaCierreVenta();
                break;
        }
    }

    public Boolean validaCamposCapturaBasica() {
        boolean valido = false;
        sbErrorCapBasica = new ArrayList<String>();
        if (!validaCampos.validaCampoVacioYReguexNombresApellidos(eTApPaterno.getText().toString(), "Apellido Paterno")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCampoVacioYReguexNombresApellidos(eTApMaterno.getText().toString(), "Apellido Materno")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCampoVacioYReguexNombresApellidos(eTPNombre.getText().toString(), "Primer Nombre")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCampoVacioYReguexNombresApellidos(eTSNombre.getText().toString(), "Segundo Nombre")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(etEstadoCivil.getText().toString(), "Estado Civil")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(etSexo.getText().toString(), "Sexo")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaEdad(eTFechaNacimiento.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(etNacionalidad.getText().toString(), "Nacionalidad")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!etNacionalidad.getText().toString().equals("MEXICO")) {
            if (!validaCampos.validaSinItemSpinner(eTFormaMigratoria.getText().toString(), "Forma Migratoria")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
        }
        if (!validaCampos.validaRFC(eTRFC.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCalle(eTCalle.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaNumExterior(eTNumExterior.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaNumInterior(eTNumInterior.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCodigoPostal(eTCodigoPostal.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(eTColonia.getText().toString(), "Colonia")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(eTDeloMunicipio.getText().toString(), "Delegación o Municipio")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(eTEstado.getText().toString(), "Estado")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaLadaDom(eTLadaDom.getText().toString())){
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaTelDom(eTTelDom.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(eTTipoProducto.getText().toString(), "Producto")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!(sbErrorCapBasica.size() > 0)) {
            for (int i = 0; i < estadoCivil.size(); i++) {
                if (estadoCivil.get(i).toString().equals(etEstadoCivil.getText().toString())) {
                    for (int j = 0; j < listEstadoCivil.size(); j++) {
                        if (listEstadoCivil.get(j).getDescripcion().toString().equals(estadoCivil.get(i).toString())) {
                            solicitud.setpCEstadoCivil(listEstadoCivil.get(j).getValor().toString());
                            break;
                        }
                    }
                    break;
                }
            }
            for (int i = 0; i < sexo.size(); i++) {
                if (sexo.get(i).toString().equals(etSexo.getText().toString())) {
                    for (int j = 0; j < listSexo.size(); j++) {
                        if (listSexo.get(j).getSexo().toString().equals(sexo.get(i).toString())) {
                            solicitud.setpCSexo(listSexo.get(j).getValor().toString());
                            break;
                        }
                    }
                    break;
                }
            }
            for (int i = 0; i < nacionalidades.size(); i++) {
                if (nacionalidades.get(i).toString().equals(etNacionalidad.getText().toString())){
                    for (int j = 0; j < listNacionalidades.size(); j++) {
                        if (listNacionalidades.get(j).getNacionalidad().toString().equals(nacionalidades.get(i).toString())) {
                            solicitud.setpCNacionalidad(listNacionalidades.get(j).getValor().toString());
                            break;
                        }
                    }
                    break;
                }
            }
            if (!etNacionalidad.getText().toString().equals("MEXICO")) {
                for (int i = 0; i < formasMigratorias.size(); i++) {
                    if (formasMigratorias.get(i).toString().equals(eTFormaMigratoria.getText().toString())) {
                        for (int j = 0; j < listFormaMigratoria.size(); j++) {
                            if (listFormaMigratoria.get(j).getFormaMigratoria().toString().equals(formasMigratorias.get(i).toString())) {
                                solicitud.setpCFormaMigratoria(listFormaMigratoria.get(j).getValor().toString());
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            int idColonia = 0;
            int idMunicipio = 0;
            int idEstado = 0;
            for (int i = 0; i < colonias.size(); i++) {
                if (colonias.get(i).toString().equals(eTColonia.getText().toString())) {
                    for (int j = 0; j < listWithCP.size(); j++) {
                        if (listWithCP.get(j).getColonia().toString().equals(colonias.get(i).toString())) {
                            idColonia = listWithCP.get(j).getIdColonia();
                            break;
                        }
                    }
                    break;
                }
            }
            for (int i = 0; i < delOMunicipios.size(); i++) {
                if (delOMunicipios.get(i).toString().equals(eTDeloMunicipio.getText().toString())) {
                    for (int j = 0; j < listWithCP.size(); j++) {
                        if (listWithCP.get(j).getMunicipio().toString().equals(delOMunicipios.get(i).toString())) {
                            idMunicipio = listWithCP.get(j).getIdMunicipio();
                            break;
                        }
                    }
                    break;
                }
            }
            for (int i = 0; i < estados.size(); i++) {
                if (estados.get(i).toString().equals(eTEstado.getText().toString())) {
                    for (int j = 0; j < listWithCP.size(); j++) {
                        if (listWithCP.get(j).getEstado().toString().equals(estados.get(i).toString())) {
                            idEstado = listWithCP.get(j).getIdEstado();
                            break;
                        }
                    }
                    break;
                }
            }
            String idLoc = helper.getIdLocWithCp(eTCodigoPostal.getText().toString(), idColonia, idMunicipio, idEstado);
            solicitud.setpClaveLocalidadDomicilio(idLoc);
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).toString().equals(eTTipoProducto.getText().toString())) {
                    for (int j = 0; j < listProductos.size(); j++) {
                        if (listProductos.get(j).getProducto().toString().equals(productos.get(i).toString())) {
                            solicitud.setpCTipoProducto(listProductos.get(j).getValor().toString());
                            break;
                        }
                    }
                    break;
                }
            }
            valido = true;
        }
        return valido;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intRegMenu = new Intent(Solicitud.this, Menu.class);
        startActivity(intRegMenu);
        finish();
    }

    private class enviaCapturaBasicaTask extends AsyncTask<Void, Void, Boolean> {
        private final ProgressDialog dialogo = new ProgressDialog(Solicitud.this);
        @Override
        protected void onPreExecute() {
            dialogo.setMessage("Enviando Captura Basica...");
            dialogo.setCancelable(false);
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean capBasicaEnviada = false;
            InsertaCapBasica icb = new InsertaCapBasica();
            capBasicaEnviada = icb.connection();
            return capBasicaEnviada;
        }

        @Override
        protected void onPostExecute(Boolean capBasicaEnviada) {
            if (capBasicaEnviada) {
                ocultaCapBasica();
            }else {
                muestraErroresCapBasica();
            }
            dialogo.dismiss();
        }
    }

    public class InsertaCapBasica {
        private static final String SOAP_ACTION = "http://tempuri.org/InsertarCapturaBasica";
        private static final String METHOD_NAME = "InsertarCapturaBasica";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";
        boolean enviaCapBasica = false;
        public InsertaCapBasica() {

        }

        public Boolean connection() {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("pIDAgencia", solicitud.getPIDAgencia());
            request.addProperty("pIDUsuario", usuariof2f.getIdUsuario());
            request.addProperty("pApellidoPaterno", solicitud.getpApellidoPaterno().toString());
            request.addProperty("pApellidoMaterno", solicitud.getpApellidoMaterno().toString());
            request.addProperty("pPrimerNombre", solicitud.getpPrimerNombre().toString());
            request.addProperty("pSegundoNombre", solicitud.getpSegundoNombre().toString());
            request.addProperty("pCEstadoCivil", solicitud.getpCEstadoCivil().toString());
            request.addProperty("pCSexo", solicitud.getpCSexo().toString());
            request.addProperty("pAñoNacimiento", solicitud.getpAñoNacimiento().toString());
            request.addProperty("pMesNacimiento", solicitud.getpMesNacimiento().toString());
            request.addProperty("pDiaNacimiento", solicitud.getpDiaNacimiento().toString());
            request.addProperty("pCNacionalidad", solicitud.getpCNacionalidad().toString());
            request.addProperty("pCFormaMigratoria", solicitud.getpCFormaMigratoria().toString());
            request.addProperty("pRFC", solicitud.getpRFC().toString());
            request.addProperty("pCalleDomicilio", solicitud.getpCalleDomicilio().toString());
            request.addProperty("pNumeroExteriorDomicilio", solicitud.getpNumeroExteriorDomicilio().toString());
            request.addProperty("pNumeroInteriorDomicilio", solicitud.getpNumeroInteriorDomicilio().toString());
            request.addProperty("pClaveLocalidadDomicilio", solicitud.getpClaveLocalidadDomicilio().toString());
            request.addProperty("pClaveLadaDomicilio", solicitud.getpClaveLadaDomicilio().toString());
            request.addProperty("pTelefonoDomicilio", solicitud.getpTelefonoDomicilio().toString());
            request.addProperty("pCTipoProducto", solicitud.getpCTipoProducto().toString());
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapObject sResult = (SoapObject)envelope.getResponse();
                if (sResult != null) {
                    if (sResult.getProperty("Resultado").toString().equals("OPERACION_CORRECTA")) {
                        enviaCapBasica = true;
                        solicitud.setCapturaBasicaCompleta(true);
                        solicitud.setFolioInteligente(sResult.getProperty("FolioInteligente").toString());
                        solicitud.setIDFolio(sResult.getProperty("IDFolio").toString());
                    }else {
                        SoapObject sResultDetalles = (SoapObject)sResult.getProperty("Detalles");
                        if (sResultDetalles != null) {
                            if (!(sResultDetalles.getPropertyCount() == 0)) {
                                sbErrorCapBasica = new ArrayList<String>();
                                for (int i = 0; i < sResultDetalles.getPropertyCount(); i++) {
                                    sbErrorCapBasica.add(sResultDetalles.getProperty(i).toString());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return enviaCapBasica;
        }
    }

    protected void ocultaCapBasica(){
        findViewById(R.id.iCapturaBasica).setVisibility(View.INVISIBLE);
        findViewById(R.id.iAutenticacion).setVisibility(View.VISIBLE);
        findViewById(R.id.iCapturaEvaluacion).setVisibility(View.INVISIBLE);
        findViewById(R.id.iCierreVenta).setVisibility(View.INVISIBLE);
        rLayoutOpcionesPreg1.setVisibility(View.INVISIBLE);
    }

    protected void muestraErroresCapBasica() {
        String mensajeError = "";
        for (int i = 0; i < sbErrorCapBasica.size(); i++) {
            mensajeError = mensajeError + sbErrorCapBasica.get(i).toString() + "\n";
        }
        if (!mensajeError.toString().matches("")) {
            tVTitulo.setText("Datos incompletos");
            tVContenido.setText(mensajeError.toString());
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

    protected void muestraErroresCapAutenticacion() {
        if (sbErrorCapBasica.size() > 0) {
            String mensajeError = "";
            for (int i = 0; i < sbErrorCapBasica.size(); i++) {
                mensajeError = mensajeError + sbErrorCapBasica.get(i).toString() + "\n";
            }
            if (!mensajeError.toString().matches("")) {
                tVTitulo.setText("Datos incompletos");
                tVContenido.setText(mensajeError.toString());
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

    public void validaCapturaAutenticacion() {
        sbErrorCapBasica = new ArrayList<String>();
        if (rbOpcionSIPreg1.isChecked()) {
            if (!validaCampos.validaSinItemSpinner(etBancos.getText().toString(), "Institución Bancaria")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaUltimosCuatroDigitos(eTultimos4Digitos.getText().toString())) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!(sbErrorCapBasica.size() > 0)) {
                for (int i = 0; i < bancos.size(); i++) {
                    if (bancos.get(i).toString().matches(etBancos.getText().toString())) {
                        for (int j = 0; j < listBancos.size(); j++) {
                            if (listBancos.get(j).getDescripcion().toString().matches(bancos.get(i).toString())) {
                                solicitud.setpIdBanco(listBancos.get(j).getValor());
                                solicitud.setpUltimosCuatroDigitos(eTultimos4Digitos.getText().toString());
                                break;
                            }
                        }
                        break;
                    }
                }
                new consultaIntentosAURestantesTask().execute();
            }else {
                muestraErroresCapAutenticacion();
            }
        }else if (rbOpcionNOPreg1.isChecked()) {
            new consultaIntentosAURestantesTask().execute();
        }
    }

    public void validaCapturaEvaluacion() {
        sbErrorCapBasica = new ArrayList<String>();
        if (!validaCampos.validaSinItemSpinner(eTTipoVivienda.getText().toString(), "Tipo Vivienda")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaAntiguedadDomEmp(eTañosAntiguedad.getText().toString(), "Antigüedad Domicilio")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaIngresosFijoVariableVentasProf(eTIngresoNetoFijo.getText().toString(), "Ingreso Neto Fijo")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaIngresosFijoVariableVentasProf(eTIngresoNetoVariable.getText().toString(), "Ingreso Neto Variable")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaIngresosFijoVariableVentasProf(eTVentasAnuales.getText().toString(), "Ventas Anuales")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaIngresosFijoVariableVentasProf(eTProfesionistasIndep.getText().toString(), "Profesionistas Independientes")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaSinItemSpinner(eTOcupacion.getText().toString(), "Ocupación")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (rLayoutOcupacion.getVisibility() == View.VISIBLE) {
            if (eTOcupacion.getText().toString().matches("UNIVERSITARIO")) {
                if (!validaCampos.validaSinItemSpinnerOcupacion(etUniversidad.getText().toString(), "Universidad")) {
                    sbErrorCapBasica.add(mensaje.getMensaje().toString());
                    mensaje.setMensaje("");
                }
                if (!validaCampos.validaCampus(eTCampus.getText().toString())) {
                    sbErrorCapBasica.add(mensaje.getMensaje().toString());
                    mensaje.setMensaje("");
                }
                if (!validaCampos.validaSinItemSpinnerOcupacion(etCarrera.getText().toString(), "Carrera")) {
                    sbErrorCapBasica.add(mensaje.getMensaje().toString());
                    mensaje.setMensaje("");
                }
                if (!validaCampos.validaSinItemSpinnerOcupacion(etTipoFinanciamiento.getText().toString(), "Tipo Financiamiento")) {
                    sbErrorCapBasica.add(mensaje.getMensaje().toString());
                    mensaje.setMensaje("");
                }
                if (etTipoFinanciamiento.getText().toString().matches("BECA")) {
                    if (!validaCampos.validaPorcentajeBeca(eTTipoBeca.getText().toString())) {
                        sbErrorCapBasica.add(mensaje.getMensaje().toString());
                        mensaje.setMensaje("");
                    }
                }
                if (!validaCampos.validaSinItemSpinnerOcupacion(etGradoEscolar.getText().toString(), "Grado Escolar")) {
                    sbErrorCapBasica.add(mensaje.getMensaje().toString());
                    mensaje.setMensaje("");
                }
            }
        }
        if (eTOcupacion.getText().toString().matches("ASALARIADO")) {
            if (!validaCampos.validaLadaEmpleo(eTLadaEmpleo.getText().toString())) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaTelEmpleo(eTTelEmpleo.getText().toString())) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
        }
        if (!validaCampos.validaExtensionEmp(eTExtension.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaAntiguedadDomEmp(eTAñosAntiguedadEmpleo.getText().toString(), "Años Antigüedad Empleo")) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCorreoElectronico(eTCorreoElectronico.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaNumeroCelular(eTTelCelular.getText().toString())) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!(sbErrorCapBasica.size() > 0)) {
            for (int i = 0; i < viviendas.size(); i++) {
                if (viviendas.get(i).toString().matches(eTTipoVivienda.getText().toString())) {
                    for (int j = 0; j < listViviendas.size(); j++) {
                        if (listViviendas.get(j).getTipoVivienda().toString().matches(viviendas.get(i).toString())) {
                            solicitud.setTipoVivienda(listViviendas.get(j).getValor().toString());
                            break;
                        }
                    }
                    break;
                }
            }
            for (int i = 0; i < ocupaciones.size(); i++) {
                if (ocupaciones.get(i).toString().matches(eTOcupacion.getText().toString())) {
                    for (int j = 0; j < listOcupacion.size(); j++) {
                        if (listOcupacion.get(j).getProfesion().toString().matches(ocupaciones.get(i).toString())) {
                            solicitud.setOcupacion(listOcupacion.get(j).getProfesion().toString());
                            solicitud.setValorOcupacion(listOcupacion.get(j).getValor().toString());
                            break;
                        }
                    }
                    break;
                }
            }
            if (rLayoutOcupacion.getVisibility() == View.VISIBLE) {
                if (eTOcupacion.getText().toString().matches("UNIVERSITARIO")) {
                    for (int i = 0; i < universidades.size(); i++) {
                        if (universidades.get(i).toString().matches(etUniversidad.getText().toString())) {
                            for (int j = 0; j < listUniversidad.size(); j++) {
                                if (listUniversidad.get(j).getUniversidad().toString().matches(universidades.get(i).toString())) {
                                    solicitud.setUniversidad(listUniversidad.get(j).getValor().toString());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    for (int i = 0; i < carreras.size(); i++) {
                        if (carreras.get(i).toString().matches(etCarrera.getText().toString())) {
                            for (int j = 0; j < listCarreras.size(); j++) {
                                if (listCarreras.get(j).getNombreCarrera().toString().matches(carreras.get(i).toString())) {
                                    solicitud.setCarrera(listCarreras.get(j).getValor().toString());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    for (int i = 0; i < tipoFinanciamiento.size(); i++) {
                        if (tipoFinanciamiento.get(i).toString().matches(etTipoFinanciamiento.getText().toString())) {
                            for (int j = 0; j < listTipoFinanciamiento.size(); j++) {
                                if (listTipoFinanciamiento.get(j).getTipoFinanciamiento().toString().matches(tipoFinanciamiento.get(i).toString())) {
                                    solicitud.setTipoFinanciamiento(listTipoFinanciamiento.get(j).getValor().toString());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    for (int i = 0; i < gradoEscolar.size(); i++) {
                        if (gradoEscolar.get(i).toString().matches(etGradoEscolar.getText().toString())) {
                            for (int j = 0; j < listGradoEscolar.size(); j++) {
                                if (listGradoEscolar.get(j).getGrado().toString().matches(gradoEscolar.get(i).toString())) {
                                    solicitud.setGradoEscolar(listGradoEscolar.get(j).getValor().toString());
                                    break;
                                }
                            }
                            break;
                        }
                    }

                }
            }
            new enviaCapturaEvaluacionTask().execute();
        }else {
            muestraErroresCapturaEvaluacion();
        }

    }

    private class consultaIntentosAURestantesTask extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean intentosConsultados = false;
            ConsultaIntentosRestantes cir = new ConsultaIntentosRestantes();
            intentosConsultados = cir.connection();
            return intentosConsultados;
        }

        @Override
        protected void onPostExecute(Boolean intentosConsultados) {
            if (intentosConsultados) {
                enviaCapAutenticacion();
            }else {
                muestraErrorCapAutenticacionIntentos();
            }
        }
    }

    public class ConsultaIntentosRestantes {
        private static final String SOAP_ACTION = "http://tempuri.org/ConsultarIntentosAURestantes";
        private static final String METHOD_NAME = "ConsultarIntentosAURestantes";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";
        boolean intentosConsultados = false;
        public ConsultaIntentosRestantes() {
        }

        public Boolean connection() {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("pIdFolio", Integer.parseInt(solicitud.getIDFolio().toString()));
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapPrimitive sResult = (SoapPrimitive)envelope.getResponse();
                if (sResult != null) {
                    solicitud.setpNumeroIntento(Integer.parseInt(sResult.toString()));
                    if (solicitud.getpNumeroIntento() > 0) {
                        solicitud.setHayIntentosRestantes(true);
                    }
                    intentosConsultados = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return intentosConsultados;
        }
    }

    protected void enviaCapAutenticacion() {
        if (solicitud.isHayIntentosRestantes()) {
            new enviaCapturaAutenticacionTask().execute();
        }
    }

    private class enviaCapturaAutenticacionTask extends AsyncTask<Void, Void, Boolean>{
        private final ProgressDialog dialogo = new ProgressDialog(Solicitud.this);
        @Override
        protected void onPreExecute() {
            dialogo.setMessage("Enviando Captura Autenticación...");
            dialogo.setCancelable(false);
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean capAutenticacionEnviada = false;
            InsertaCapAutenticacion ica = new InsertaCapAutenticacion();
            capAutenticacionEnviada = ica.connection();
            return capAutenticacionEnviada;
        }

        @Override
        protected void onPostExecute(Boolean capAutenticacionEnviada) {
            if (capAutenticacionEnviada) {
                ocultaCapAutenticacion();
            }else {
                muestraErroresCapAutenticacion();
            }
            dialogo.dismiss();
        }
    }

    public class InsertaCapAutenticacion {
        private static final String SOAP_ACTION = "http://tempuri.org/InsertarCapturaAutenticacion";
        private static final String METHOD_NAME = "InsertarCapturaAutenticacion";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";
        boolean enviaCapAutenticacion = false;
        public InsertaCapAutenticacion() {
        }

        public Boolean connection() {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            //request.addProperty("pIdUsuario", solicitud.getPIDUsuario());
            request.addProperty("pIdUsuario", usuariof2f.getIdUsuario());
            request.addProperty("pIdFolio", Integer.parseInt(solicitud.getIDFolio().toString()));
            request.addProperty("pExisteTarjeta", solicitud.ispExisteTarjeta());
            request.addProperty("pIdBanco", solicitud.getpIdBanco());
            request.addProperty("pUltimosCuatroDigitosTarjeta", solicitud.getpUltimosCuatroDigitos());
            request.addProperty("pExisteCreditoHipotecario", solicitud.ispExisteCreditoHipotecario());
            request.addProperty("pExisteCreditoAutomotriz", solicitud.ispExisteCreditoAutomotriz());
            request.addProperty("pNumeroIntento", solicitud.getpNumeroIntento());
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapObject sResult = (SoapObject)envelope.getResponse();
                if (sResult != null) {
                    if (Boolean.parseBoolean(sResult.getProperty(0).toString())) {
                        enviaCapAutenticacion = true;
                        solicitud.setCapturaAutenticacionValida(Boolean.parseBoolean(sResult.getProperty(0).toString()));
                        solicitud.setMensajeCapAutenticacion(sResult.getProperty(1).toString());
                    }else {
                        sbErrorCapBasica = new ArrayList<String>();
                        solicitud.setCapturaAutenticacionValida(false);
                        sbErrorCapBasica.add(sResult.getProperty(1).toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return enviaCapAutenticacion;
        }
    }

    protected void muestraErrorCapAutenticacionIntentos() {
        String mensageErrorAutenticacion = "Usted cuenta con " + String.valueOf(solicitud.getpNumeroIntento()) + " intentos.";
        if (!mensageErrorAutenticacion.toString().matches("")) {
            tVTitulo.setText("Datos incompletos");
            tVContenido.setText(mensageErrorAutenticacion.toString());
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

    protected void muestraErroresCapturaEvaluacion() {
        if (sbErrorCapBasica.size() > 0) {
            String mensajeError = "";
            for (int i = 0; i < sbErrorCapBasica.size(); i++) {
                mensajeError = mensajeError + sbErrorCapBasica.get(i).toString() + "\n";
            }
            if (!mensajeError.toString().matches("")) {
                tVTitulo.setText("Datos incompletos");
                tVContenido.setText(mensajeError.toString());
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

    private class enviaCapturaEvaluacionTask extends AsyncTask<Void, Void, Boolean>{
        private final ProgressDialog dialogo = new ProgressDialog(Solicitud.this);
        @Override
        protected void onPreExecute() {
            dialogo.setMessage("Enviando Captura Evaluación...");
            dialogo.setCancelable(false);
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean capEvaluacionEnviada = false;
            InsertaCapEvaluacion ice = new InsertaCapEvaluacion();
            capEvaluacionEnviada = ice.connection();
            return capEvaluacionEnviada;
        }

        @Override
        protected void onPostExecute(Boolean capEvaluacionEnviada) {
            if (capEvaluacionEnviada) {
                ocultaCapEvaluacion();
            }else {
                muestraErroresCapturaEvaluacion();
            }
            dialogo.dismiss();
        }
    }

    public class InsertaCapEvaluacion {
        private static final String SOAP_ACTION = "http://tempuri.org/InsertarCapturaEvaluacion";
        private static final String METHOD_NAME = "InsertarCapturaEvaluacion";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";
        boolean enviaCapEvaluacion = false;
        public InsertaCapEvaluacion() {}
        public Boolean connection() {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            //request.addProperty("pIDUsuario", solicitud.getPIDUsuario());
            request.addProperty("pIDUsuario", usuariof2f.getIdUsuario());
            request.addProperty("pIDFolio", Integer.parseInt(solicitud.getIDFolio()));
            request.addProperty("pCTipoVivienda", solicitud.getTipoVivienda());
            request.addProperty("pAntiguedadDomicilio", Integer.parseInt(solicitud.getAñosAntiguedad()));
            request.addProperty("pIngresoNetoFijo", solicitud.getIngresoNetoFijo());
            request.addProperty("pIngresoNetoVariable", solicitud.getIngresoNetoVariable());
            request.addProperty("pVentasAnuales", solicitud.getVentasAnuales());
            request.addProperty("pProfesionistasIndependientes", solicitud.getProfesionistasIndependientes());
            request.addProperty("pCOcupacion", solicitud.getValorOcupacion());
            request.addProperty("pCUniversidad", solicitud.getUniversidad());
            request.addProperty("pCampus", solicitud.getCampus());
            request.addProperty("pCNombreCarrera", solicitud.getCarrera());
            request.addProperty("pCTipoFinanciamiento", solicitud.getTipoFinanciamiento());
            request.addProperty("pPorcentajeBeca", solicitud.getBeca());
            request.addProperty("pCGradoEscolar", solicitud.getGradoEscolar());
            request.addProperty("pClaveLadaEmpleo", solicitud.getLadaEmpleo());
            request.addProperty("pTelefonoEmpleo", solicitud.getTelEmpleo());
            request.addProperty("pExtension", solicitud.getExtension());
            request.addProperty("pAntiguedadEmpleo", solicitud.getAñosAntiguedadEmpleo());
            request.addProperty("pCorreoElectronico", solicitud.getCorreoElectronico());
            request.addProperty("pCelular", solicitud.getTelCelular());
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapObject sResult = (SoapObject)envelope.getResponse();
                if (sResult != null) {
                    if (Boolean.parseBoolean(sResult.getProperty(0).toString())) {
                        enviaCapEvaluacion = true;
                        solicitud.isCapturaEvaluacionCompleta();
                        solicitud.setMensajeCapEvaluacion(sResult.getProperty(1).toString());
                    }else {
                        sbErrorCapBasica = new ArrayList<String>();
                        sbErrorCapBasica.add(sResult.getProperty("Parametro").toString());
                        sbErrorCapBasica.add(sResult.getProperty("Descripcion").toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return enviaCapEvaluacion;
        }
    }

    protected void ocultaCapAutenticacion() {
        solicitud.setCapturaAutenticacionCompleta(true);
        findViewById(R.id.iAutenticacion).setVisibility(View.INVISIBLE);
        findViewById(R.id.iCapturaEvaluacion).setVisibility(View.VISIBLE);
        findViewById(R.id.iCapturaBasica).setVisibility(View.INVISIBLE);
        findViewById(R.id.iCierreVenta).setVisibility(View.INVISIBLE);
        tVTitulo.setText("Captura autenticación correcta");
        tVContenido.setText(solicitud.getMensajeCapAutenticacion().toString());
        ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
                customDialog.cancel();
            }
        });
        customDialog.show();
    }

    protected void ocultaCapEvaluacion() {
        solicitud.setCapturaEvaluacionCompleta(true);
        findViewById(R.id.iCapturaEvaluacion).setVisibility(View.INVISIBLE);
        findViewById(R.id.iCierreVenta).setVisibility(View.VISIBLE);
        findViewById(R.id.iCapturaBasica).setVisibility(View.INVISIBLE);
        findViewById(R.id.iAutenticacion).setVisibility(View.INVISIBLE);
        tVTipodeOcupacionNegocioP.setText(solicitud.getOcupacion().toString());
        tVTitulo.setText("Captura evaluación correcta");
        tVContenido.setText(solicitud.getMensajeCapEvaluacion().toString());
        ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
                customDialog.cancel();
            }
        });
    }

    public void validaCapturaCierreVenta() {
        sbErrorCapBasica = new ArrayList<String>();
        if (tVTipodeOcupacionNegocioP.getText().toString().matches("ASALARIADO")) {
            if (!validaCampos.validaNombreEmpresa(eTNombreEmpresa.getText().toString(), "Nombre Empresa")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaNombreEmpresa(eTCalleEmpresa.getText().toString(), "Calle de la Empresa")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaNombreEmpresa(eTNumExtEmpresa.getText().toString(), "Número Exterior")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaNombreEmpresa(eTNumIntEmpresa.getText().toString(), "Número Interior")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaNombreEmpresa(eTCodigoPostalEmpresa.getText().toString(), "Código Postal")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaSinItemSpinner(eTColoniaEmpresa.getText().toString(), "Colonia")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaSinItemSpinner(eTDeloMunicipioEmpresa.getText().toString(), "Delegación")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
            if (!validaCampos.validaSinItemSpinner(eTEstadoEmpresa.getText().toString(), "Estado")) {
                sbErrorCapBasica.add(mensaje.getMensaje().toString());
                mensaje.setMensaje("");
            }
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTApPaternoRef1.getText().toString(), "Apellido Paterno", 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTApMaternoRef1.getText().toString(), "Apellido Materno", 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTPrimerNombreRef1.getText().toString(), "Primer Nombre", 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTSegundoNombreRef1.getText().toString(), "Segundo Nombre", 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaLadaRefs(eTLadaDomRef1.getText().toString(), 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaTelRefs(eTTelDomRef1.getText().toString(), 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaLadaEmpRefs(eTLadaEmpleoRef1.getText().toString(), 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaTelEmpRefs(eTTelEmpleoRef1.getText().toString(), 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (validaCampos.validaExtensionRefs(eTExtensionRef1.getText().toString(), 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaNumeroCelularRefs(eTCelularRef1.getText().toString(), 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTApPaternoRef2.getText().toString(), "Apellido Paterno", 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTApMaternoRef2.getText().toString(), "Apellido Materno", 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTPrimerNombreRef2.getText().toString(), "Primer Nombre", 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaCamposVaciosReguexReferencias(eTSegundoNombreRef2.getText().toString(), "Segundo Nombre", 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaLadaRefs(eTLadaDomRef2.getText().toString(), 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaTelRefs(eTTelDomRef2.getText().toString(), 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaLadaEmpRefs(eTLadaEmpleoRef2.getText().toString(), 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaTelEmpRefs(eTTelEmpleoRef2.getText().toString(), 1)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (validaCampos.validaExtensionRefs(eTExtensionRef2.getText().toString(), 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!validaCampos.validaNumeroCelularRefs(eTCelularRef2.getText().toString(), 2)) {
            sbErrorCapBasica.add(mensaje.getMensaje().toString());
            mensaje.setMensaje("");
        }
        if (!(sbErrorCapBasica.size() > 0)) {
            if (tVTipodeOcupacionNegocioP.getText().toString().matches("ASALARIADO")) {
                int idColoniaEmp = 0;
                int idDeloMunEmp = 0;
                int idEstadoEmp = 0;
                for (int i = 0; i < coloniasEmpresa.size(); i++) {
                    if (coloniasEmpresa.get(i).toString().matches(eTColoniaEmpresa.getText().toString())) {
                        for (int j = 0; j < listWithCPEmpresa.size(); j++) {
                            if (listWithCPEmpresa.get(j).getColonia().toString().matches(coloniasEmpresa.get(i).toString())) {
                                idColoniaEmp = listWithCPEmpresa.get(j).getIdColonia();
                                break;
                            }
                        }
                        break;
                    }
                }
                for (int i = 0; i < delOMunicipiosEmpresa.size(); i++) {
                    if (delOMunicipiosEmpresa.get(i).toString().matches(eTDeloMunicipioEmpresa.getText().toString())) {
                        for (int j = 0; j < listWithCPEmpresa.size(); j++) {
                            if (listWithCPEmpresa.get(j).getMunicipio().toString().matches(eTDeloMunicipioEmpresa.getText().toString())) {
                                idDeloMunEmp = listWithCPEmpresa.get(j).getIdMunicipio();
                                break;
                            }
                        }
                        break;
                    }
                }
                for (int i = 0; i < estadosEmpresa.size(); i++) {
                    if (estadosEmpresa.get(i).toString().matches(eTEstadoEmpresa.getText().toString())) {
                        for (int j = 0; j < listWithCPEmpresa.size(); j++) {
                            if (listWithCPEmpresa.get(j).getEstado().toString().matches(estadosEmpresa.get(i).toString())) {
                                idEstadoEmp = listWithCPEmpresa.get(j).getIdEstado();
                                break;
                            }
                        }
                        break;
                    }
                }
                String idLocEmp = helper.getIdLocWithCp(eTCodigoPostalEmpresa.getText().toString(), idColoniaEmp, idDeloMunEmp, idEstadoEmp);
                solicitud.setClaveLocalidadEmpleo(idLocEmp);
                new enviaCapturaCierreVentaTask().execute();
            }
        }else {
            muestraErroresCapturaCierreVenta();
        }

    }

    protected void muestraErroresCapturaCierreVenta() {
        if (sbErrorCapBasica.size() > 0) {
            String mensajeError = "";
            for (int i = 0; i < sbErrorCapBasica.size(); i++) {
                mensajeError = mensajeError + sbErrorCapBasica.get(i).toString() + "\n";
            }
            if (!mensajeError.toString().matches("")) {
                tVTitulo.setText("Datos incompletos");
                tVContenido.setText(mensajeError.toString());
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

    private class enviaCapturaCierreVentaTask extends AsyncTask<Void, Void, Boolean>{
        private final ProgressDialog dialogo = new ProgressDialog(Solicitud.this);
        @Override
        protected void onPreExecute() {
            dialogo.setMessage("Enviando Captura Cierre Venta...");
            dialogo.setCancelable(false);
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean capCierreEnviada = false;
            InsertaCapCierreVenta ice = new InsertaCapCierreVenta();
            capCierreEnviada = ice.connection();
            return capCierreEnviada;
        }

        @Override
        protected void onPostExecute(Boolean capCierreEnviada) {
            if (capCierreEnviada) {
                muestraResumenSolicitudConcluida();
            }else {
                muestraErroresCapturaCierreVenta();
            }
            dialogo.dismiss();
        }
    }

    public class InsertaCapCierreVenta {
        private static final String SOAP_ACTION = "http://tempuri.org/InsertarCapturaReferencias";
        private static final String METHOD_NAME = "InsertarCapturaReferencias";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";
        boolean enviaCapCierreVenta = false;
        public InsertaCapCierreVenta() {

        }

        public Boolean connection() {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            //request.addProperty("pIDUsuario", solicitud.getPIDUsuario());
            request.addProperty("pIDUsuario", usuariof2f.getIdUsuario());
            request.addProperty("pIDFolio", Integer.parseInt(solicitud.getIDFolio()));
            request.addProperty("pNombreEmpresa", solicitud.getTipoVivienda());
            request.addProperty("pCalleEmpresa", Integer.parseInt(solicitud.getAñosAntiguedad()));
            request.addProperty("pNoExteriorEmpresa", solicitud.getIngresoNetoFijo());
            request.addProperty("pNoInteriorEmpresa", solicitud.getIngresoNetoVariable());
            request.addProperty("pClaveLocalidadEmpresa", solicitud.getVentasAnuales());
            request.addProperty("pRef1ApellidoPaterno", solicitud.getProfesionistasIndependientes());
            request.addProperty("pRef1ApellidoMaterno", solicitud.getValorOcupacion());
            request.addProperty("pRef1PrimerNombre", solicitud.getUniversidad());
            request.addProperty("pRef1SegundoNombre", solicitud.getCampus());
            request.addProperty("pRef1LadaDomicilio", solicitud.getCarrera());
            request.addProperty("pRef1TelefonoDomicilio", solicitud.getTipoFinanciamiento());
            request.addProperty("pRef1LadaEmpleo", solicitud.getBeca());
            request.addProperty("pRef1TelefonoEmpleo", solicitud.getGradoEscolar());
            request.addProperty("pRef1Extension", solicitud.getLadaEmpleo());
            request.addProperty("pRef1Celular", solicitud.getTelEmpleo());
            request.addProperty("pRef2ApellidoPaterno", solicitud.getProfesionistasIndependientes());
            request.addProperty("pRef2ApellidoMaterno", solicitud.getValorOcupacion());
            request.addProperty("pRef2PrimerNombre", solicitud.getUniversidad());
            request.addProperty("pRef2SegundoNombre", solicitud.getCampus());
            request.addProperty("pRef2LadaDomicilio", solicitud.getCarrera());
            request.addProperty("pRef2TelefonoDomicilio", solicitud.getTipoFinanciamiento());
            request.addProperty("pRef2LadaEmpleo", solicitud.getBeca());
            request.addProperty("pRef2TelefonoEmpleo", solicitud.getGradoEscolar());
            request.addProperty("pRef2Extension", solicitud.getLadaEmpleo());
            request.addProperty("pRef2Celular", solicitud.getTelEmpleo());
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            try {
                httpTransport.debug = true;
                httpTransport.call(SOAP_ACTION, envelope);
                final SoapObject sResult = (SoapObject)envelope.getResponse();
                if (sResult != null) {
                    if (Boolean.parseBoolean(sResult.getProperty(0).toString())) {
                        enviaCapCierreVenta = true;
                        solicitud.isCapturaCierreCompleta();
                        solicitud.setMensajeCapturaCierre(sResult.getProperty(1).toString());
                    }else {
                        sbErrorCapBasica = new ArrayList<String>();
                        sbErrorCapBasica.add(sResult.getProperty("Parametro").toString());
                        sbErrorCapBasica.add(sResult.getProperty("Descripcion").toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return enviaCapCierreVenta;
        }
    }

    protected void muestraResumenSolicitudConcluida() {
        findViewById(R.id.iCapturaBasica).setVisibility(View.INVISIBLE);
        findViewById(R.id.iAutenticacion).setVisibility(View.INVISIBLE);
        findViewById(R.id.iCapturaEvaluacion).setVisibility(View.INVISIBLE);
        findViewById(R.id.iCierreVenta).setVisibility(View.INVISIBLE);
        findViewById(R.id.iDictamen).setVisibility(View.VISIBLE);
        /****Poner estatus de dictamen**************************
         *
         * **/
        tVContFolioAgencia.setText("");
        tVContFolioInteligente.setText("");
        tVContNombreCompleto.setText("");
        tVContEstatus.setText("");
        tVContDictamen.setText("");
        tVContSituacion.setText("");
        tVContFechaDictamen.setText("");
        bttnContinuarDictamen.setText("");
    }
}