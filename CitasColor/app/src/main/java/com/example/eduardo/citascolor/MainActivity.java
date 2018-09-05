package com.example.eduardo.citascolor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView citaTextView;
    private TextView autorTextView;
    private Button nuevaCitaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citaTextView = findViewById(R.id.citaTextView);
        autorTextView = findViewById(R.id.autorTextView);
        nuevaCitaBtn = findViewById(R.id.nuevaCitaBtn);
    }

    public void nuevaCita(View view){
        Log.d(TAG, "nuevaCita: se hizo click");
    }
}
