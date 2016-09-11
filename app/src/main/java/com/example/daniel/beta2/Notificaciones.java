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

import static com.example.daniel.beta2.NotificacionesTabla.NotificacionesEntry;

public class Notificaciones{
	private String id;
	private String mensaje;
	private String fecha;
	private String hora;
	private String sonido;
	
	public Notificaciones(String mensaje, String fecha,String hora, String sonido){
		this.id = UUID.randomUUID().toString();
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.hora = hora;
		this.sonido = sonido;	
	}
	public Notificaciones(Cursor cursor) {
		id = cursor.getString(cursor.getColumnIndex(NotificacionesEntry.ID));/*getcolummindex:retorna el indice de la columna*/
		mensaje = cursor.getString(cursor.getColumnIndex(NotificacionesEntry.MENSAJE));
		fecha = cursor.getString(cursor.getColumnIndex(NotificacionesEntry.FECHA));
		hora = cursor.getString(cursor.getColumnIndex(NotificacionesEntry.HORA));
		sonido = cursor.getString(cursor.getColumnIndex(NotificacionesEntry.SONIDO));
	}
	public ContentValues toContentValues() {
		ContentValues values = new ContentValues();
		values.put(NotificacionesEntry.ID, id);/*Añade el valor*/
		values.put(NotificacionesEntry.MENSAJE, mensaje);
		values.put(NotificacionesEntry.FECHA, fecha);
		values.put(NotificacionesEntry.HORA, hora);
		values.put(NotificacionesEntry.SONIDO, sonido);
		return values;
	}
	public String getId() { return id; }
	public String getMensaje(){
		return mensaje;
	}
	public String getFecha(){
		return fecha;
	} 
	public String getHora(){
		return hora;
	}
	public String getSonido(){
		return sonido;
	}
}

