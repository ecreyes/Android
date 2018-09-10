package com.example.eduardo.citascolor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import java.util.Random;

public class MainActivity extends Activity {
    //obtener nombre de actividad
    public static final String TAG = MainActivity.class.getSimpleName();
    //declaracion de variables que estan en layout.
    private TextView citaTextView;
    private TextView autorTextView;
    private Button nuevaCitaBtn;
    //doble lista enlazada
    private ArrayList<ArrayList<String>> contenido = new ArrayList<>();
    ArrayList<String> campos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asignacion de variables con el layout,estas se usan en Java
        //para modificar el comportamiento en layout.
        citaTextView = findViewById(R.id.citaTextView);
        autorTextView = findViewById(R.id.autorTextView);
        nuevaCitaBtn = findViewById(R.id.nuevaCitaBtn);

        //agregar
        campos.add("autor0");
        campos.add("cita0");
        contenido.add(campos);

        campos = new ArrayList<>();
        campos.add("autor1");
        campos.add("cita1");
        contenido.add(campos);

        campos = new ArrayList<>();
        campos.add("autor2");
        campos.add("cita2");
        contenido.add(campos);
    }

    //método que se ejecuta al hacer click en el boton.
    public void nuevaCita(View view){
        Log.d(TAG, "nuevaCita: se hizo click");

        int index = getNumeroAleatorio(contenido.size());
        String autor = contenido.get(index).get(0);
        String cita = contenido.get(index).get(1);

        autorTextView.setText(autor);
        citaTextView.setText(cita);

    }

    public int getNumeroAleatorio(int max){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return random.nextInt(max);
    }
}
