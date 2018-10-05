package cl.ecreyes.ejemplolistview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
  public static final String TAG = "MainActivity";
  public ArrayList<String> PAISES = new ArrayList<>();
  public ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    /* inicializando ArrayList
    PAISES.add("Chile");
    PAISES.add("Argentina");
    PAISES.add("España");
    PAISES.add("Estados unidos");
    */

    //Inicializando array con loadData
      loadData();

      //ListView, se asocia el list view del activity_main.xml
    ListView lv = findViewById(R.id.my_lv);
    //Adaptador
    //Al adaptador se le pasa esta actividad, el layout que se creó con los textos,y el arreglo de paises
    adapter = new ArrayAdapter<String>(this,R.layout.itemlist,PAISES);
    //seteando el adaptador al listview
    lv.setAdapter(adapter);
    //se setea el metodo onItemClick para que cuando se presione el listview se accione ese método.
    lv.setOnItemClickListener(this);


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
    Toast.makeText(this,"Se añadió: "+texto+" correctamente",Toast.LENGTH_SHORT).show();
    //se deja el input vacío.
    ed.setText("");
    //se guardan los datos
    saveData();
  }

  //guarda los datos en un xml almacenado en el celular, solo permite tipos primitivos el xml
  private void saveData(){
      //se puede editar el SharedPreferences en modo privado, es decir no comparte datos a otras apps
      SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
      //string que permite manipular cadenas sin crear nuevos objetos
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i<PAISES.size(); i++){
          sb.append(((i==0)?"":";")+PAISES.get(i));
      }
      //asigna una key al xml, en este caso paises y agrega el string.
      spe.putString("paises",sb.toString());
      //se realizan los cambios al xml.
      spe.commit();
  }

  //carga los datos almacenados en shared preferences
  private void loadData(){
      //se carga el sharedpreferences en lecutra y modo privado.
      SharedPreferences sp = getPreferences(MODE_PRIVATE);
      //obtiene el string con la clave 'paises', si no tiene valores carga el string default
      String paisesList = sp.getString("paises","Argentina;Chile;Bolivia;Colombia");
      //recorre el string anterior y agrega los paises.
      for(String pais:paisesList.split(";")){
          PAISES.add(pais);
      }
  }

    //metodo que emite una accion cuando se clickea un elemento del listView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      Uri uri = Uri.parse("http://en.wikipedia.org/wiki/" +
                Uri.encode(PAISES.get(position)));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
