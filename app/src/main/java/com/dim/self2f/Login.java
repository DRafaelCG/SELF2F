package com.dim.self2f;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.self2f.clases.ClsConnectionDetector;
import com.dim.self2f.clases.ClsMensaje;
import com.dim.self2f.clases.ClsSolicitud;
import com.dim.self2f.clases.ClsUsuarioF2F;
import com.dim.self2f.clases.ClsValidaCampos;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Login extends Activity {
    ClsUsuarioF2F usuarioF2F = ClsUsuarioF2F.getInstancia();
    ClsValidaCampos validaUsuario;
    ClsMensaje mensaje = ClsMensaje.getInstancia();
    ClsSolicitud solicitud = ClsSolicitud.getInstancia();
    EditText eTUsuario;
    EditText eTContrasenia;
    Button btnLogin;
    ClsConnectionDetector cd;
    Boolean haveInternet = false;
    Dialog customDialog;
    TextView tVTitulo;
    TextView tVContenido;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setTitle("");
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            setContentView(R.layout.activity_login);
            eTUsuario = (EditText)findViewById(R.id.eTusuario);
            eTContrasenia = (EditText)findViewById(R.id.eTpassword);
            btnLogin = (Button)findViewById(R.id.btnLogin);
            cd = new ClsConnectionDetector(getApplicationContext());
            haveInternet = cd.isConnectingToInternet();
            customDialog = new Dialog(Login.this, R.style.Theme_Dialog_Translucent);
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            customDialog.setCancelable(false);;
            customDialog.setContentView(R.layout.dialogo_aceptar);
            tVTitulo = (TextView)customDialog.findViewById(R.id.tVTituloDialogo);
            tVTitulo.setGravity(Gravity.CENTER);
            tVContenido = (TextView)customDialog.findViewById(R.id.tVContenido);
            tVContenido.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL);
            if (!haveInternet) {
                Toast.makeText(getBaseContext(), "Sin conexión a internet...", 10000)
                        .show();
            }
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cd = new ClsConnectionDetector(getApplicationContext());
                    haveInternet = cd.isConnectingToInternet();
                    if (!haveInternet) {
                        Toast.makeText(getBaseContext(), "Sin conexión a internet...", 10000)
                                .show();
                    }else {
                        validaUsuario = new ClsValidaCampos();
                        if (validaUsuario.validaUsuarioContrasenia(eTUsuario.getText().toString(),
                                eTContrasenia.getText().toString())) {
                            usuarioF2F.setPassword(eTContrasenia.getText().toString());
                            new validaCredencialesTask().execute(eTUsuario.getText().toString(), eTContrasenia.getText().toString());
                        }else {
                            tVTitulo.setText("Datos incompletos");
                            tVContenido.setText(mensaje.getMensaje().toString());
                            ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    customDialog.dismiss();
                                    customDialog.cancel();
                                    mensaje.setMensaje("");
                                }
                            });
                            customDialog.show();
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class validaCredencialesTask extends AsyncTask<String, Void, ClsUsuarioF2F> {
        private final ProgressDialog dialogo = new ProgressDialog(Login.this, ProgressDialog.THEME_TRADITIONAL);
        @Override
        protected void onPreExecute(){
            dialogo.setMessage("Validando...");
            dialogo.setIcon(R.mipmap.ic_launcher);
            dialogo.show();
            dialogo.setCanceledOnTouchOutside(false);
        }

        @Override
        protected ClsUsuarioF2F doInBackground(String... args){
            AuthenticateUser mAuth = new AuthenticateUser();
            mAuth.mUser = args[0];
            mAuth.mPwd =  args[1];
            mAuth.connection(mAuth.mUser, mAuth.mPwd);
            return usuarioF2F;
        }

        @Override
        protected void onPostExecute(ClsUsuarioF2F usuario){
            if (dialogo.isShowing()) {
                irMenu();
                dialogo.dismiss();
            }
        }
    }

    public void irMenu() {
        if (usuarioF2F.getpNombre() == null || usuarioF2F.getIdUsuario() == 0) {
            tVTitulo.setText("Datos incorrectos");
            tVContenido.setText("Usuario o contraseña incorrectos");
            ((Button)customDialog.findViewById(R.id.btnAceptarDialog)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                    customDialog.cancel();
                }
            });
            customDialog.show();
        }else {
            if (usuarioF2F.getClass() != null) {
                solicitud.setPIDAgencia(usuarioF2F.getIdAgencia());
                //solicitud.setPIDUsuario(usuarioF2F.getIdUsuario());
                Intent intMenu = new Intent(Login.this, Menu.class);
                intMenu.putExtra("logueado", true);
                startActivity(intMenu);
                finish();
            }
        }
    }

    public class AuthenticateUser{
        /*private static final String SOAP_ACTION = "http://tempuri.org/Login";
        private static final String METHOD_NAME = "Login";
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL="http://desarrollo.f2f.tarjetas.sistemassel.mx/wsselMovil/f2fservice.asmx";*/
        public String mUser;
        public String mPwd;
        public AuthenticateUser(){
        }
        public void connection(String mUser, String mPwrd){
            /*try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("pUsuario", mUser);
                request.addProperty("pPassword", mPwrd);
                HttpTransportSE httpTransport = new HttpTransportSE(URL);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                try {
                    httpTransport.debug = true;
                    httpTransport.call(SOAP_ACTION, envelope);
                    final SoapObject sResult = (SoapObject)envelope.getResponse();
                    if (sResult != null) {
                        usuarioF2F.setIdUsuario(Integer.parseInt(sResult.getProperty(0).toString()));
                        usuarioF2F.setIdBroker(Integer.parseInt(sResult.getProperty(1).toString()));
                        usuarioF2F.setNombreDeUsuario(sResult.getProperty(2).toString());
                        usuarioF2F.setpNombre(sResult.getProperty(3).toString());
                        usuarioF2F.setsNombre(sResult.getProperty(4).toString());
                        usuarioF2F.setApPaterno(sResult.getProperty(5).toString());
                        usuarioF2F.setApMaterno(sResult.getProperty(6).toString());
                        //usuarioF2F.setEmail(String.valueOf(sResult.getProperty(7).toString()));
                        usuarioF2F.setIdAgencia(Integer.parseInt(sResult.getProperty(7).toString()));
                        usuarioF2F.setIdPerfil(Integer.parseInt(sResult.getProperty(8).toString()));
                    }else {
                        usuarioF2F.setpNombre(null);
                        usuarioF2F.setIdUsuario(0);
                    }
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }catch (XmlPullParserException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Log.d("Login-validando usuario", e.toString());
            }*/
            usuarioF2F.setIdUsuario(1);
            usuarioF2F.setIdBroker(1);
            usuarioF2F.setNombreDeUsuario("rcastrejon");
            usuarioF2F.setpNombre("Rafael");
            usuarioF2F.setsNombre("");
            usuarioF2F.setApPaterno("Castrejón");
            usuarioF2F.setApMaterno("G.");
            //usuarioF2F.setEmail(String.valueOf(sResult.getProperty(7).toString()));
            usuarioF2F.setIdAgencia(1);
            usuarioF2F.setIdPerfil(1);
        }
    }

}