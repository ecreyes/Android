package com.example.eduardo.usointents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
                if(phoneNumber!=null){
                    versionAntigua(phoneNumber);
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
    }

    /*
    Comprueba si tenemos el permiso pasado como parametro, esto no le va a preguntar al usuario,
    solo va a revisar si esta en el manifest.
     */
    private boolean revisarPermiso(String permiso){
        int resultado = this.checkCallingOrSelfPermission(permiso);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }
}
