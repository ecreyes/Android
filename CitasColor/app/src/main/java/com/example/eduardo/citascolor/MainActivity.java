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

    //constantes
    private static final String COLOR = "color";
    private static final String AUTOR = "autor";
    private static final String CITA = "cita";

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

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "Se llamó el restore instace state");

        citaTextView.setText(savedInstanceState.getString(CITA));
        citaTextView.setTextColor(savedInstanceState.getInt(COLOR));
        autorTextView.setText(savedInstanceState.getString(AUTOR));
        autorTextView.setTextColor(savedInstanceState.getInt(COLOR));
    }

    //guardar el estado de una actividad.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AUTOR,autorTextView.getText().toString());
        outState.putString(CITA,citaTextView.getText().toString());
        outState.putInt(COLOR,citaTextView.getCurrentTextColor());
    }

    //método que se ejecuta al hacer click en el boton.
    public void nuevaCita(View view){
        Log.d(TAG, "nuevaCita: se hizo click");
        Cita cita = citas.getCitaAleatoria();

        autorTextView.setText(cita.getAutor());
        autorTextView.setTextColor(cita.getColor());
        citaTextView.setText(cita.getTexto());
        citaTextView.setTextColor(cita.getColor());

    }
}
