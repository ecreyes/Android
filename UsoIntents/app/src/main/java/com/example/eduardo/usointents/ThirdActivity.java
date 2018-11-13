package com.example.eduardo.usointents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private EditText etPhone;
    private EditText etWeb;
    private ImageButton ibPhone;
    private ImageButton ibwWeb;
    private ImageButton ibCamera;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etPhone = findViewById(R.id.editTextPhone);
        etWeb = findViewById(R.id.editTextWeb);
        ibPhone = findViewById(R.id.imageButtonPhone);
        ibwWeb = findViewById(R.id.imageButtonWeb);

        ibPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhone.getText().toString();
                //si el campo no esta vacio
                if(!phoneNumber.equals("")){
                    //si la version es de marshmallow hacia adelante (6 en adelante)
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        //si ya esta dado el permiso.
                        if(revisarPermiso(Manifest.permission.CALL_PHONE)){
                            Intent intentCall = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNumber));
                            startActivity(intentCall);
                        }else{
                            //si no esta dado el permiso
                            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //si no se le ha preguntando.
                                //peticion de permiso en tiempo real, esta es asincrona por lo que debe manejarse
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            }else{
                                Toast.makeText(ThirdActivity.this, "Permiso denegado", Toast.LENGTH_SHORT).show();
                                //Intent para abrir la configuración.
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:"+getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }
                    }else{
                        versionAntigua(phoneNumber);
                    }
                }
            }

            //llamada si el celular posee android Lollipop hacia abajo
            private void versionAntigua(String phoneNumber){
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                if(revisarPermiso(Manifest.permission.CALL_PHONE)){
                    startActivity(intentCall);
                }else{
                    Toast.makeText(ThirdActivity.this,"No se puede realizar la llamada"
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        ibwWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etWeb.getText().toString();
                if(url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+url));
                    startActivity(intentWeb);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PHONE_CALL_CODE){
            /* por que requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
            envia el permiso en posición 0.
            */
            String permiso = permissions[0];
            int resultado = grantResults[0];
            //si el permiso es igual al que esta en manifest.
            if(permiso.equals(Manifest.permission.CALL_PHONE)){
                //comprobar si se acepta o no la peticion.
                if(resultado == PackageManager.PERMISSION_GRANTED){
                    //dio el permiso
                    String phoneNumber = etPhone.getText().toString();
                    Intent intentCall = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNumber));
                    startActivity(intentCall);
                }else{
                    Toast.makeText(ThirdActivity.this, "No dio el permiso", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /*
    Comprueba si el usuario ha aceptado el permiso.
    */
    private boolean revisarPermiso(String permiso){
        int resultado = this.checkCallingOrSelfPermission(permiso);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }
}
