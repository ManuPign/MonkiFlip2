package com.example.inbox3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    private SqliteHelper sqliteHelper;
    private SQLiteDatabase BASE1;
    Context ctx;

    private final static String NAME_DB = "BASE1.sqlite";
    private final static int VERSION_DB = 1;

    private final String sqlCreatePublicaciones = "CREATE TABLE Publicaciones(" +
            "id INTEGER NOT NULL," +
            "remitente TEXT NOT NULL," +
            "asunto TEXT NOT NULL," +
            "contenido TEXT NOT NULL," +
            "enlaceImagen TEXT NOT NULL," +
            "categoria TEXT NOT NULL," +
            "PRIMARY KEY(id AUTOINCREMENT)" +
            ")";

    private final String sqlCreateUsuarios = "CREATE TABLE Usuarios(" +
            "id INTEGER NOT NULL," +
            "usuario TEXT NOT NULL," +
            "contrasenia TEXT NOT NULL," +
            "activo INTEGER NOT NULL," +
            "PRIMARY KEY(id AUTOINCREMENT)" +
            ")";





    public SqliteHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreatePublicaciones);
        db.execSQL(sqlCreateUsuarios);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Publicaciones");
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(sqlCreatePublicaciones);
        db.execSQL(sqlCreateUsuarios);

    }




}
