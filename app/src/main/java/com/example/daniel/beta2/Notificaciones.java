package com.example.daniel.beta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.UUID;

public class Notificaciones{
	private String id;
	private String mensaje;
	private int fecha;
	private int hora;
	private String sonido;
	
	public Notificaciones(String mensaje, int fecha,int hora, String sonido){
		this.id = UUID.randomUUID().toString();
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.hora = hora;
		this.sonido = sonido;	
	}
	public String getId(){return id;}
	public int getMensaje(){
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

