package com.dim.self2f;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dim.self2f.clases.ClsCargarCatalogos;
import com.dim.self2f.clases.ClsConnectionDetector;

public class Inicio extends Activity {
    Button btnInicio;
    ClsCargarCatalogos cargaCatalogos;
    ClsConnectionDetector cd;
    Boolean haveInternet = false;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("");
        cargaCatalogos = new ClsCargarCatalogos(this);
        btnInicio = (Button)findViewById(R.id.btnInicio);
        cd = new ClsConnectionDetector(getApplicationContext());
        haveInternet = cd.isConnectingToInternet();
        if (!haveInternet) {
            Toast.makeText(getBaseContext(), "Sin conexión a internet...", 10000)
                    .show();
        }
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd = new ClsConnectionDetector(getApplicationContext());
                haveInternet = cd.isConnectingToInternet();
                if (!haveInternet) {
                    Toast.makeText(getBaseContext(), "Sin conexión a internet...", 10000)
                            .show();
                }else {
                    Intent intLogin = new Intent(Inicio.this, Login.class);
                    startActivity(intLogin);;
                    finish();
                }
            }
        });
    }
}
