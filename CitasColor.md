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
Se puede filtrar por `D/MainActivity`

## Generar número aleatorio de [0,n)
```java
public int getNumeroAleatorio(int max){
    Random random = new Random();
    random.setSeed(System.currentTimeMillis());
    return random.nextInt(max);
}
```

## Asignación de valores a la variables del layout.
Recordar que primero se relacionan los elementos con la UI y una vez que estan relacionados, se pueden modificar sus valores.
```java
//autor y cita son String.
autorTextView.setText(autor);
citaTextView.setText(cita);
```

# Ciclos de vida de las actividades
Los ciclos de vida son parte de las actividades y se pueden sobreescribir.
Para sobreescribir cualquier método en android studio se presiona Ctrl+O

## Guardar el estado de una actividad.
El método a sobre-escribir es el `onSaveInstanceState`, el que tiene solo un parámetro.
```java
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
}
```
Un `Bundle` es simplemente un clave:valor como un diccionario, este utiliza los métodos get para obtener el valor al ingresar una clave y el método put para añadir un clave:valor al Bundle, el put se elige dependiendo del tipo de dato que se quiere guardar.
Ejemplo:
```java
private TextView citaTextView;
private TextView autorTextView;

//constantes
private static final String COLOR = "color";
private static final String AUTOR = "autor";
private static final String CITA = "cita";


@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(AUTOR,autorTextView.getText().toString());
    outState.putString(CITA,citaTextView.getText().toString());
    outState.putInt(COLOR,citaTextView.getCurrentTextColor());
}
```
notar que antes para asignar valor a estas variables se uso el setText, por eso ahora al llamarlas se utiliza el getText y se convierte a string.
Entonce le decimos al bundle, guardame este string con (clave,valor).
Con esto ya tenemos guardado el estado que queriamos de la actividad.

## Obteniendo el estado de la actividad.
Antes habiamos guardado todo el estado, ahora como obtener ese estado?.
Existen dos formas, utilizar el bundle que viene por defecto en onCreate o modificar un método onRestoreInstanceState, a continuacion se veran las dos formas.

### Usando el bundle que viene onCreate.
Solamente hay que preguntar si no esta vacio, de ser asi se recuperan los datos y se asignan a las variables correspondientes:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //asignacion de variables con el layout,estas se usan en Java
    //para modificar el comportamiento en layout.
    citaTextView = findViewById(R.id.citaTextView);
    autorTextView = findViewById(R.id.autorTextView);
    nuevaCitaBtn = findViewById(R.id.nuevaCitaBtn);

    if(savedInstanceState != null){
        citaTextView.setText(savedInstanceState.getString(CITA));
        citaTextView.setTextColor(savedInstanceState.getInt(COLOR));
        autorTextView.setText(savedInstanceState.getString(AUTOR));
        autorTextView.setTextColor(savedInstanceState.getInt(COLOR));
    }
}
```
### Usando OnRestoreInstaceState
Buscar el método `onRestoreInstanceState` y sobre escribirlo.
```java
@Override
protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    citaTextView.setText(savedInstanceState.getString(CITA));
    citaTextView.setTextColor(savedInstanceState.getInt(COLOR));
    autorTextView.setText(savedInstanceState.getString(AUTOR));
    autorTextView.setTextColor(savedInstanceState.getInt(COLOR));
}
```
La única diferencia es que no posee el if para comprobar si es distinto de null.

# El ciclo de vida de las Actividades.
Las Actividades en Android funcionan como una estructura de datos de tipo Pila.
Cada vez que una actividad es creada se pone al tope de la pila y se pone en estado de ejecución..
Pueden haber dos casos:
* a) La Actividad cubre toda la pantalla, la actividad anterior esta en estado Detenida
* b) La Actividad es transparente o deja ver cierta parte de la actividad anterior, la Actividad anterior esta en estado de Pausa.

Los Estados de las actividades son:
* onCreate(): Se incializan las variables,vistas etc, se resive el bundle de las actividades anteriores.
* onStart(): se llama justo antes de que la actividad se este ejecutando y el usuario aun no puede interactuar.
* onResume(): se llama cuando la aplicación es visible para el usuario y este puede interactuar, se inicializan animaciones, música etc.
* Ejecucion: se pone al tope de la pila.
* onPause(): se pone la actividad en pausa, la actividad esta en el backgraund, se guardan los estados y se detienen las animaciones, hay otra actividad en el tope de la pila.
* onStop(): Se detiene la Actividad ya que esta no esvisible.
* onDestroy(): se destruye la actividad.
* Terminada

extra:
* onRestart(): Es llamado cuando una actividad detenida es iniciada denuevo.