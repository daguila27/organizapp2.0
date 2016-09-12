package com.example.daniel.beta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private int currentViewId = -1;
    private Stack contentViewsPila;
    private int selectedYear, selectedMonth, selectedDay;

    public void setCurrentViewById(int id){
        setContentView(id);
        currentViewId = id;
        contentViewsPila.push(id);
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

        contentViewsPila = new Stack(); //Para saber los contents views que se han ido cambiando

        setCurrentViewById(R.layout.activity_main); //Pone la actividaad principal en pantalla
        setearOnClickCalendario(R.id.calendarView); //Para almacenar la fecha que el usuario seleccione
    }

    public void cambiar(View view){
        setCurrentViewById(R.layout.second_activity);

        String date = String.valueOf(selectedDay) + "/" + String.valueOf(selectedMonth) + "/" + String.valueOf(selectedYear);

        TextView fecha = (TextView) findViewById(R.id.crearNotificacionFecha);
        fecha.append(date);
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

            //Mata a la actividad (creo)
            /*if(getCurrentViewById() == R.layout.activity_main){

            }
            else if(getCurrentViewById() == R.layout.second_activity){ //Se devuelve a la actividad principal
                setCurrentViewById(R.layout.activity_main);
            }*/

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
                Log.d("wea", dayOfMonth+" - "+(month+1)+" - "+ year);

                selectedYear = year;
                selectedMonth = month + 1; //Por algun motivo los meses empiezan en cero, sumandole 1 se arregla
                selectedDay = dayOfMonth;

            }
        });
        return cv;
    }


/*
    public String[] list_categoria(Cursor categorias){

    }
    */
}
