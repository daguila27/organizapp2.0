package com.example.daniel.beta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.UUID;
import android.database.Cursor;
import android.content.ContentValues;

import static com.example.daniel.beta2.CategoriasTabla.CategoriasEntry;

public class Categorias{
	private String id;
	private String prioridad;
	private String nombre;
	
	public Categorias(String prioridad, String nombre){
		this.id = UUID.randomUUID().toString();
		this.prioridad = prioridad;
		this.nombre = nombre;	
	}
	public Categorias(Cursor cursor) {
		id = cursor.getString(cursor.getColumnIndex(CategoriasEntry.ID));/*getcolummindex:retorna el indice de la columna*/
		prioridad = cursor.getString(cursor.getColumnIndex(CategoriasEntry.PRIORIDAD));
		nombre = cursor.getString(cursor.getColumnIndex(CategoriasEntry.NOMBRE));
	}
 	public ContentValues toContentValues() {
		ContentValues values = new ContentValues();
		values.put(CategoriasEntry.ID, id);/*Añade el valor*/
		values.put(CategoriasEntry.PRIORIDAD, prioridad);
		values.put(CategoriasEntry.NOMBRE, nombre);
		return values;
    	}
	
	public String getId(){return id;}
	public String getPrioridad(){return prioridad;}
	public String getNombre(){return nombre;} 
}

