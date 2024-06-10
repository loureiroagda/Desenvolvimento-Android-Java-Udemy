package com.example.appscursojavaudemy.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSAO = 1;
    public static String NOME_DB = "dbTarefa";
    public static String TABELA = "tarefas";

    public SQLiteDatabase.CursorFactory factory;


    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS "  + TABELA
                 +" (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL ); ";

        try {
            db.execSQL(sql);
            Log.i("Info db", "Tabela criada com sucesso");
        }catch (Exception e){

            Log.i("Info db", "Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA
                + "; ";

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("Info db", "App atualizado com sucesso");
        }catch (Exception e){

            Log.i("Info db", "Erro ao atualizar o app" + e.getMessage());
        }

    }
}
