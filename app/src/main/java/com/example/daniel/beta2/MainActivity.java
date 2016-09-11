package com.example.daniel.beta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    int selectedYear, selectedMonth, selectedDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cada vez que se hace click en una fecha del calendario, esta fecha se almacena en las variables selectedYear, selectedMonth, selectedDay
        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,int dayOfMonth){
                Log.d("wea", dayOfMonth+" - "+(month+1)+" - "+ year);

                selectedYear = year;
                selectedMonth = month + 1; //Por algun motivo los meses empiezan en cero, sumandole 1 se arregla
                selectedDay = dayOfMonth;

            }
        });
    }
    public void cambiar(View view){

        setContentView(R.layout.second_activity);

        String date = String.valueOf(selectedDay) + "/" + String.valueOf(selectedMonth) + "/" + String.valueOf(selectedYear);

        TextView fecha = (TextView) findViewById(R.id.crearNotificacionFecha);
        fecha.append(date);

    }
    public void
}
