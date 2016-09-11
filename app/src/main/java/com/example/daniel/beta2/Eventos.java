package com.example.daniel.beta2;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.UUID;

import static com.example.daniel.beta2.EventosTabla.EventosEntry;

public class Eventos{
	private String id;
	private String nombre;
	private String fecha;
	private String id_categoria;
	
	public Eventos(String fecha,String nombre, String id_categoria){
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.fecha = fecha;
		this.id_categoria = id_categoria;
	}
	public Eventos(Cursor cursor) {
		id = cursor.getString(cursor.getColumnIndex(EventosEntry.ID));/*getcolummindex:retorna el indice de la columna*/
		nombre = cursor.getString(cursor.getColumnIndex(EventosEntry.NOMBRE));
		fecha = cursor.getString(cursor.getColumnIndex(EventosEntry.FECHA));
		id_categoria = cursor.getString(cursor.getColumnIndex(EventosEntry.ID_CATEGORIA));
	}
	
	public ContentValues toContentValues() {
		ContentValues values = new ContentValues();
		values.put(EventosEntry.ID, id);/*Añade el valor*/
		values.put(EventosEntry.NOMBRE, nombre);
		values.put(EventosEntry.FECHA, fecha);
		values.put(EventosEntry.ID_CATEGORIA, id_categoria);
		return values;
    	}
	public String getId(){return id;}
	public String getNombre(){return nombre;}
	public String getFecha(){return fecha;}
	public String getId_categoria(){return id_categoria;}
}

