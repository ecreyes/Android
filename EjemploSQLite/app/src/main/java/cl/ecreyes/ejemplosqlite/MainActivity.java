package cl.ecreyes.ejemplosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se crea la base de datos
        HolaUsuariosSQLiteHelper usdbh =
                new HolaUsuariosSQLiteHelper(this, "DBUsuarios", null, 2);
        //se utiliza la base de datos en modo lectura.
        SQLiteDatabase db = usdbh.getReadableDatabase();
        // si se logra hacer conección a la bd
        if(db!=null){
            StringBuilder sb = new StringBuilder();
            //se recuperan los registros
            Cursor c = db.rawQuery("SELECT * FROM Usuarios",null);
            //muevo al primero
            if(c.moveToFirst()){
                String nombre = c.getString(1);
                sb.append(nombre);
                sb.append("\n");
                //me muevo a los siguientes
                while(c.moveToNext()){
                    nombre = c.getString(1);
                    sb.append(nombre);
                    sb.append("\n");
                }
                //se asignan al textView
                TextView tv = findViewById(R.id.tv_contenido);
                tv.setText(sb.toString());
            }
            c.close();
        }
        db.close();

    }
}
