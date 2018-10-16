package cl.ecreyes.ejemplosqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HolaUsuariosSQLiteHelper extends SQLiteOpenHelper {
    String tableUsuarios = "CREATE TABLE Usuarios (Id integer,Nombre text)";

    public HolaUsuariosSQLiteHelper(Context context, String name,
                                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //se ejecutará cuando se incie la aplicación por primera vez.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUsuarios);
        for(int i =1 ; i<=5 ;i++){
            int id = i;
            String nombre = "Usuario "+i;
            db.execSQL("INSERT INTO Usuarios (Id, Nombre) " + "VALUES ("
                    + id + ", '" + nombre + "')");
        }
    }

    //se ejecutará en caso de que se cambie la versión.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(tableUsuarios);
        for(int i =1 ; i<=5 ;i++){
            int id = i;
            String nombre = "Usuario "+i;
            db.execSQL("INSERT INTO Usuarios (Id, Nombre) " + "VALUES ("
                    + id + ", '" + nombre + "')");
        }
    }
}
