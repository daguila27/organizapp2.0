package com.example.daniel.beta2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/*esquema para base de datos categoias*/
public class NotificacionesTabla{
	public static abstract class NotificacionesEntry implements BaseColumns {
		public static final String TABLE_NAME ="Notificaciones";
		/*private String id;
			private String mensaje;
			private int fecha;
			private int hora;
			private String sonido;
			*/
		public static final String ID = "id";
		public static final String MENSAJE = "mensaje";
		public static final String FECHA = "fecha";
		public static final String HORA = "hora";
		public static final String SONIDO = "sonido";
    }
}
