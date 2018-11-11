package com.example.eduardo.usointents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv = findViewById(R.id.textViewSecondActivity);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String cuerpo = bundle.getString("body");
            tv.setText(cuerpo);
        }else{
            tv.setText("No se pudo recibir el cuerpo");
        }

        btn = findViewById(R.id.buttonSecondActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
