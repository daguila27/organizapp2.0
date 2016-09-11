package com.example.daniel.beta2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/*esquema para base de datos categoias*/
public class CategoriasTabla{
	public static abstract class CategoriasEntry implements BaseColumns {
		public static final String TABLE_NAME ="Categorias";

		public static final String ID = "id";
		public static final String PRIORIDAD = "prioridad";
		public static final String NOMBRE = "nombre";
    }
}

