# CitasColor
Se recomienda utilizar la vista en Android para trabajar en el proyecto.

## Constraint Layout y Relative Layout
Lo primero que hay que verificar es que el layout sea del tipo Constraint Layout en el Activity_main en la sección de `Component tree`

## Relacionar los elementos de UI con el MainActivity.java

Supongamos que tenemos un textView en la UI, para relacionarlo hay que asignarle un ID.
luego en el archivo java, hay que declarar la variable y asignarla dentro del onCreate:
```java
public class MainActivity extends Activity {
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
}
```

## OnClickListener mediante XML
Se agrega el metodo onClick al xml del boton y se le va a asignar una funcion que hay que declarla en java.

```xml
<Button
    android:id="@+id/nuevaCitaBtn"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginBottom="16dp"
    android:layout_marginEnd="8dp"
    android:layout_marginLeft="8dp"
    android:layout_marginRight="8dp"
    android:layout_marginStart="8dp"
    android:text="Nueva Cita"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintHorizontal_bias="0.501"
    app:layout_constraintStart_toStartOf="parent"
    android:onClick="nuevaCita"
    />
```
Luego en la clase de java se agrega el atributo:
```java
public static final String TAG = MainActivity.class.getSimpleName();
```
y se crea la funcion que va responder cuando se haga click.
```java
public void nuevaCita(View view){
    Log.d(TAG, "nuevaCita: se hizo click");
}
```
La función recibe una vista, y el log es para imprimir, en este caso va a imprimir en la actividad que se apretó el boton y el texto q va a enviar.

En LogCat se puede ver el mensaje del log.