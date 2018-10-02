package cl.ecreyes.ejemplolistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = "MainActivity";
  public ArrayList<String> PAISES = new ArrayList<>();
  public ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //inicializando ArrayList
    PAISES.add("Chile");
    PAISES.add("Argentina");
    PAISES.add("España");
    PAISES.add("Estados unidos");

    //ListView, se asocia el list view del activity_main.xml
    ListView lv = findViewById(R.id.my_lv);
    //Adaptador
    //Al adaptador se le pasa esta actividad, el layout que se creó con los textos,y el arreglo de paises
    adapter = new ArrayAdapter<String>(this,R.layout.itemlist,PAISES);
    //seteando el adaptador al listview
    lv.setAdapter(adapter);
  }

  public void eventoBoton(View view){

    //Se asigna el id al edittext del activity_main.xml
    EditText ed = findViewById(R.id.ed);
    //se recupera el texto del edittext
    String texto = ed.getText().toString();
    //log del texto
    Log.d(TAG,texto);
    //añadimos el texto
    PAISES.add(texto);
    //refrescamos el adapter
    adapter.notifyDataSetChanged();
    //notificación.
    Toast.makeText(this,"Se añadió: "+texto+" correctamente",Toast.LENGTH_LONG).show();
    //se deja el input vacío.
    ed.setText("");
  }
}
