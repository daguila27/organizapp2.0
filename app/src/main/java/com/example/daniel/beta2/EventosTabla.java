package com.example.daniel.beta2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/*esquema para base de datos categoias*/
public class EventosTabla{
	public static abstract class EventosEntry implements BaseColumns {
		public static final String TABLE_NAME ="Eventos";
		/*private String id;
		private int fecha;
		private String id_categoria;*/
		public static final String ID = "id";
		public static final int FECHA = "fecha";
		public static final String ID_CATEGORIA = "id_categoria";
    }
}
