package com.example.daniel.beta2;

import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.w3c.dom.Text;

import static com.example.daniel.beta2.CategoriasTabla.CategoriasEntry;
import static com.example.daniel.beta2.NotificacionesTabla.NotificacionesEntry;
import static com.example.daniel.beta2.EventosTabla.EventosEntry;



public class MainActivity extends AppCompatActivity {

    private int currentViewId = -1;
    private Stack contentViewsPila;
    private int selectedYear, selectedMonth, selectedDay;

    private String eventoFecha;

    private List<String> listaEventos;
    private ListView lv;

    private SQLiteDatabase base_categorias;
    private SQLiteDatabase base_notificaciones;
    private SQLiteDatabase base_eventos;

    Context estado;//informacion respecto al estado del entorno de la aplicacion...
    CategoriasHelper categorias = new CategoriasHelper(estado);
    NotificacionesHelper notificaciones = new NotificacionesHelper(estado);
    EventosHelper eventos = new EventosHelper(estado);

    public void setCurrentViewById(int id){
        setContentView(id);
        currentViewId = id;
        contentViewsPila.push(id);

        crearActivity();
    }

    public int getCurrentViewById(){
        return currentViewId;
    }

    public int popContentView(){
        contentViewsPila.pop();
        if(contentViewsPila.empty()){
            return -1;
        }
        int anterior = (int) contentViewsPila.pop();
        setCurrentViewById(anterior);
        return anterior;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState); //Por defecto

        //categorias.onCreate(base_categorias);//SE INICIAN LAS BASES DE DATOS
        //notificaciones.onCreate(base_notificaciones);
        //eventos.onCreate(base_eventos);

        contentViewsPila = new Stack(); //Para saber los contents views que se han ido cambiando
        //crear_categorias_config();

        setCurrentViewById(R.layout.activity_main); //Pone la actividaad principal en pantalla
        setearOnClickCalendario(R.id.calendarView); //Para almacenar la fecha que el usuario seleccione

        listaEventos = new ArrayList<String>();


        //Harcodeado por el momento
        //Hacer algo para que se lea desde la base de datos
        listaEventos.add("Certamen");
        listaEventos.add("Aniversario");
        listaEventos.add("Cumpleaños");
        listaEventos.add("Reunion");

    }


    public void atras(){
        int actual = popContentView();
        if(actual == -1){
            onBackPressed();
        }
        else if(actual == R.layout.activity_main){
            setearOnClickCalendario(R.id.calendarView);
        }
    }

    public void atrasView(View view){
        atras();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if(Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0){
            Log.d("CDA", "onKeyDown Called");

            atras();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //Cada vez que se hace click en una fecha del calendario, esta fecha se almacena en las variables selectedYear, selectedMonth, selectedDay
    public CalendarView setearOnClickCalendario(int idCalendario){
        CalendarView cv = (CalendarView)findViewById(idCalendario);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,int dayOfMonth){

                selectedYear = year;
                selectedMonth = month + 1; //Por algun motivo los meses empiezan en cero, sumandole 1 se arregla
                selectedDay = dayOfMonth;

            }
        });
        return cv;
    }

    public void crearSecondActivity(){
        Spinner dropdown = (Spinner)findViewById(R.id.crearSpinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaEventos);
        dropdown.setAdapter(adapter);
    }
    public void crearActivity(){
        if(getCurrentViewById() == R.layout.second_activity){
            crearSecondActivity();
        }
        if(getCurrentViewById() == R.layout.categorias_config){
            listaAgregarCategoria();
        }
    }

    //cambia de main_activity a second_activity
    public void cambiar(View view){
        setCurrentViewById(R.layout.second_activity);

        TimePicker reloj = (TimePicker) findViewById(R.id.reloj);

        int hora = reloj.getCurrentHour();
        int minutos = reloj.getCurrentMinute();

        //Fecha para sql
        eventoFecha = "" + selectedYear + "-" + selectedMonth + "-" + selectedDay + " " + hora + ":" + minutos + ":00";

        //Fecha para usuario
        String fechaActual =  "" + selectedDay + "/" + selectedMonth + "/" + selectedYear + " " + hora + ":" + minutos;

        TextView fecha = (TextView) findViewById(R.id.crearNotificacionFecha);
        fecha.append(fechaActual);
    }

    public void listaAgregarCategoria(){

        lv = (ListView) findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.categorias_list_item, listaEventos);
        lv.setAdapter(adapter);
    }

    public void botonAgregarCategoria(View view){
        setCurrentViewById(R.layout.categorias_config);
        crearActivity();
    }

    public void agregarCategoriaALista(View view){
        EditText mEdit = (EditText) findViewById(R.id.EditTextAgregarCategoria);
        String paisAAgregar = mEdit.getText().toString();
        listaEventos.add(paisAAgregar);

        lv = (ListView) findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.categorias_list_item, listaEventos);
        lv.setAdapter(adapter);
    }

    /*
    public String[][] getcategorias(Cursor categoria , boolean tipe){
        categoria.moveToFirst();//SE MUEVE EL CURSOR A LA PRIMERA FILA YA QUE ESTE COMIENZA EN LA FILA -1
        String[][] matrix = new String[categoria.getColumnCount()-1][categoria.getCount()];
        //getCount() retorna el numero de filas que existen
        //getColumnCount() retorna el numero de columnas, se le resta una porque no queremos contar la columna con el id de la categoria
        int prioridad_columna = categoria.getColumnIndex(CategoriasEntry.PRIORIDAD);
        int nombre_columna = categoria.getColumnIndex(CategoriasEntry.NOMBRE);
        categoria.moveToFirst();//PORSIACASO...
        while(categoria.moveToNext()){
            if(tipe) {//true = string[columna][fila]
                matrix[prioridad_columna][categoria.getPosition()] = categoria.getString(prioridad_columna);
                //getPosicion() retorna la fila en la que se encuentra el cursor
                matrix[nombre_columna][categoria.getPosition()] = categoria.getString(nombre_columna);
            }
            else{//false = string[fila][columna]
                matrix[categoria.getPosition()][prioridad_columna] = categoria.getString(prioridad_columna);
                //getPosicion() retorna la fila en la que se encuentra el cursor
                matrix[categoria.getPosition()][nombre_columna] = categoria.getString(nombre_columna);
            }
        }
        return matrix;
    }
*/
/*
    public void crear_categorias_config(){
        Cursor pointer = categorias.getAllCategorias();
        String[][] matrix = getcategorias(categorias.getAllCategorias(),false);
        String[] categorias_array = matrix[1];
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.categorias_list_item, categorias_array);
        ListView lv =(ListView)findViewById(R.id.categorias_config);
        lv.setAdapter(adaptador);
    }
    */
}