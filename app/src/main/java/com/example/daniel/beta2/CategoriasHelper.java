package com.example.daniel.beta2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.daniel.beta2.CategoriasTabla.CategoriasEntry;

public class CategoriasDbHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Categorias.db";
	
	public CategoriasDbHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
    	public void onCreate(SQLiteDatabase db) {
        	db.execSQL("CREATE TABLE " + CategoriasEntry.TABLE_NAME + " ("
                + CategoriasEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CategoriasEntry.ID + " TEXT NOT NULL,"
                + CategoriasEntry.PRIORIDAD + " TEXT NOT NULL,"
                + CategoriasEntry.NOMBRE + " TEXT NOT NULL,"
                + "UNIQUE (" + CategoriasEntry.ID + "))");
		// Insertar datos ficticios para prueba inicial
        	mockData(db);
	}


	private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockCategoria(sqLiteDatabase, new Categorias( "3" , "Certamen"));
    }
	public long mockCategoria(SQLiteDatabase db, Categorias categoria) {
        return db.insert(CategoriasEntry.TABLE_NAME,null,categoria.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveCategoria(Categorias categoria) {
        SQLiteDatabase db = getWritableDatabase();

        return db.insert(/*Inserta una fila en la base de datos*/
                CategoriasEntry.TABLE_NAME,
                null,
                categoria.toContentValues());

    }

    public Cursor getAllCategorias() {/*GETREADABLEDATABASE: CREA Y/O ABRE UN BASE DE DATOS(PARA LECTURA), RETORNA VARIABLE DEL TIPO SQLITEDATABASE*/
        return getReadableDatabase().query(CategoriasEntry.TABLE_NAME,null,null,null,null,null,null);/*QUERY RETORNA CURSOR*/
    }

    public Cursor getCategoriaById(String categoriaId) {
        Cursor c = getReadableDatabase().query(
                CategoriasEntry.TABLE_NAME,
                null,
                CategoriasEntry.ID + " LIKE ?",
                new String[]{categoriaId},
                null,
                null,
                null);
        return c;
    }

    public int deleteCategoria(String categoriaId) {
        return getWritableDatabase().delete(/*GETWRITABLEDATABASE: ABRE BD PARA LECTURA Y ESCRITURA*/
                CategoriasEntry.TABLE_NAME,
                CategoriasEntry.ID + " LIKE ?",/*COMANDO WHERE, SI SE PASA NULL BORRA TODAS LA FILAS*/
                new String[]{categoriaId});
    }

    public int updateCategoria(Categorias categoria, String categoriaId) {
	/*int update (String table, 
                ContentValues values, 
                String whereClause, 
                String[] whereArgs)*/        
	return getWritableDatabase().update(
                CategoriasEntry.TABLE_NAME,
                categoria.toContentValues(),
                CategoriasEntry.ID + " LIKE ?",
                new String[]{categoriaId}
        );
    }
}
