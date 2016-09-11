package com.example.daniel.beta2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.daniel.beta2.NotificacionesTabla.NotificacionesEntry;


public class NotificacionesHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Notificaciones.db";
	
	public NotificacionesHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
    	public void onCreate(SQLiteDatabase db) {
        	db.execSQL("CREATE TABLE " + NotificacionesEntry.TABLE_NAME + " ("
                + NotificacionesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NotificacionesEntry.ID + " TEXT NOT NULL,"
                + NotificacionesEntry.MENSAJE + " TEXT NOT NULL,"
                + NotificacionesEntry.FECHA + " TEXT NOT NULL,"
                    + NotificacionesEntry.HORA + " TEXT NOT NULL,"
                    + NotificacionesEntry.SONIDO + " TEXT NOT NULL,"
                + "UNIQUE (" + NotificacionesEntry.ID + "))");
	// Insertar datos ficticios para prueba inicial
        mockData(db);
	}
	private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockNotificacion(sqLiteDatabase, new Notificaciones("mensaje", "fecha","hora","sonido"));
    }
	public long mockNotificacion(SQLiteDatabase db, Notificaciones notificacion) {
        return db.insert(
                NotificacionesEntry.TABLE_NAME,
                null,
                notificacion.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveNotificacion(Notificaciones notificacion) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                NotificacionesEntry.TABLE_NAME,
                null,
                notificacion.toContentValues());

    }

    public Cursor getAllNotificaciones() {
        return getReadableDatabase()
                .query(NotificacionesEntry.TABLE_NAME,null,null,null,null,null,null);
    }

    public Cursor getNotificacionById(String notificacionId) {
        Cursor c = getReadableDatabase().query(
                NotificacionesEntry.TABLE_NAME,
                null,
                NotificacionesEntry.ID + " LIKE ?",
                new String[]{notificacionId},
                null,
                null,
                null);
        return c;
    }

    public int deleteNotificacion(String notificacionId) {
        return getWritableDatabase().delete(
                NotificacionesEntry.TABLE_NAME,
                NotificacionesEntry.ID + " LIKE ?",
                new String[]{notificacionId});
    }

    public int updateNotificacion(Notificaciones notificacion, String notificacionId) {
        return getWritableDatabase().update(
                NotificacionesEntry.TABLE_NAME,
                notificacion.toContentValues(),
                NotificacionesEntry.ID + " LIKE ?",
                new String[]{notificacionId}
        );
    }
}
