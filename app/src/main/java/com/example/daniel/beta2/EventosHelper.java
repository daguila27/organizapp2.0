package com.example.daniel.beta2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.daniel.beta2.EventosTabla.EventosEntry;

public class EventosHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Eventos.db";
	
	public EventosHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
    	public void onCreate(SQLiteDatabase db) {
        	db.execSQL("CREATE TABLE " + EventosEntry.TABLE_NAME + " ("
                + EventosEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EventosEntry.ID + " TEXT NOT NULL,"
                + EventosEntry.FECHA + " INTEGER NOT NULL,"
                + EventosEntry.ID_CATEGORIA + " TEXT NOT NULL,"
                + "UNIQUE (" + EventosEntry.ID + "))");
	// Insertar datos ficticios para prueba inicial
        mockData(db);
	}
	private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockLawyer(sqLiteDatabase, new Eventos("5", "Certamen"));
    }
	public long mockLawyer(SQLiteDatabase db, Eventos evento) {
        return db.insert(
                EventosEntry.TABLE_NAME,
                null,
                evento.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveEvento(Eventos evento) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                EventosEntry.TABLE_NAME,
                null,
                evento.toContentValues());

    }

    public Cursor getAllEventos() {
        return getReadableDatabase()
                .query(EventosEntry.TABLE_NAME,
                        null,
                        null,null,null,null,null);
    }

    public Cursor getEventoById(String eventoId) {
        Cursor c = getReadableDatabase().query(
                EventosEntry.TABLE_NAME,
                null,
                EventosEntry.ID + " LIKE ?",
                new String[]{eventoId},
                null,
                null,
                null);
        return c;
    }

    public int deleteEvento(String eventoId) {
        return getWritableDatabase().delete(
                EventosEntry.TABLE_NAME,
                EventosEntry.ID + " LIKE ?",
                new String[]{eventoId});
    }

    public int updateEvento(Eventos evento, String eventoId) {
        return getWritableDatabase().update(
                EventosEntry.TABLE_NAME,
                evento.toContentValues(),
                EventosEntry.ID + " LIKE ?",
                new String[]{eventoId}
        );
    }
}
