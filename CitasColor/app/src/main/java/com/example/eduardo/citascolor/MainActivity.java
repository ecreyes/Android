package com.example.eduardo.citascolor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    //obtener nombre de actividad
    public static final String TAG = MainActivity.class.getSimpleName();
    //declaracion de variables que estan en layout.
    private TextView citaTextView;
    private TextView autorTextView;
    private Button nuevaCitaBtn;
    private GeneradorCita citas = new GeneradorCita();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asignacion de variables con el layout,estas se usan en Java
        //para modificar el comportamiento en layout.
        citaTextView = findViewById(R.id.citaTextView);
        autorTextView = findViewById(R.id.autorTextView);
        nuevaCitaBtn = findViewById(R.id.nuevaCitaBtn);
    }

    //método que se ejecuta al hacer click en el boton.
    public void nuevaCita(View view){
        Log.d(TAG, "nuevaCita: se hizo click");
        Cita cita = citas.getCitaAleatoria();

        autorTextView.setText(cita.getAutor());
        citaTextView.setText(cita.getTexto());

    }
}
