package com.example.daniel.beta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.UUID;

public class Categorias{
	private String id;
	private int prioridad;
	private String nombre;
	
	public Categorias(int prioridad, String nombre){
		this.id = UUID.randomUUID().toString();
		this.prioridad = prioridad;
		this.nombre = nombre;	
	}
	public String getId(){return id;}
	public int getPrioridad(){
		return prioridad;
	}
	public String getNombre(){
		return nombre;
	} 
}

