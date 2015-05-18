package com.example.araceli.clinica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Datos extends SQLiteOpenHelper {

    public Datos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table clinica (clave integer primary key unique, nombre text, direccion text, telefono text, fecha text, temperatura text, peso text, motivo text, observaciones text) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists clinica");
        db.execSQL("create table clinica (clave integer primary key unique, nombre text, direccion text, telefono text, fecha text, temperatura text, peso text, motivo text, observaciones text) ");
    }
}
