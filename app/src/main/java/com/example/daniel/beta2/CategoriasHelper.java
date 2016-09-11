package com.example.daniel.beta2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.lawyersapp.daniel.CategoriasTabla.CategoriasTabla;

public class CategoriasDbHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Categorias.db";
	
	public CategoriasDbHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
    	public void onCreate(SQLiteDatabase db) {
        	db.execSQL("CREATE TABLE " + CategoriasTabla.TABLE_NAME + " ("
                + CategoriasTabla._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CategoriasTabla.ID + " TEXT NOT NULL,"
                + CategoriasTabla.PRIORIDAD + " TEXT NOT NULL,"
                + CategoriasTabla.NOMBRE + " TEXT NOT NULL,"
                + "UNIQUE (" + CategoriasTabla.ID + "))");
	// Insertar datos ficticios para prueba inicial
        mockData(db);
	}
	private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockLawyer(sqLiteDatabase, new Lawyer("5", "Certamen"));
    }
	public long mockLawyer(SQLiteDatabase db, Lawyer lawyer) {
        return db.insert(
                CategoriaTabla.TABLE_NAME,
                null,
                lawyer.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveLawyer(Lawyer lawyer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                CategoriaTabla.TABLE_NAME,
                null,
                lawyer.toContentValues());

    }

    public Cursor getAllLawyers() {
        return getReadableDatabase()
                .query(
                        CategoriaTabla.TABLE_NAME,
                        null,
                        null);
    }

    public Cursor getLawyerById(String lawyerId) {
        Cursor c = getReadableDatabase().query(
                CategoriaTabla.TABLE_NAME,
                null,
                CategoriaTabla.ID + " LIKE ?",
                new String[]{lawyerId},
                null,
                null,
                null);
        return c;
    }

    public int deleteLawyer(String lawyerId) {
        return getWritableDatabase().delete(
                LawyerEntry.TABLE_NAME,
                LawyerEntry.ID + " LIKE ?",
                new String[]{lawyerId});
    }

    public int updateLawyer(Lawyer lawyer, String lawyerId) {
        return getWritableDatabase().update(
                LawyerEntry.TABLE_NAME,
                lawyer.toContentValues(),
                LawyerEntry.ID + " LIKE ?",
                new String[]{lawyerId}
        );
    }
}
