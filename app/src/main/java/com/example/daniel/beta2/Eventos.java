package com.example.daniel.beta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.UUID;

public class Eventos{
	private String id;
	private int fecha;
	private String id_categoria;
	public Eventos(int fecha, String id_categoria){
		this.id = UUID.randomUUID().toString();
		this.fecha = fecha;
		this.id_categoria = id_categoria;
	}
	public String getId(){return id;}
	public int getFecha(){return fecha;}
	public String getId_categoria(){return id_categoria;}
}

