package com.example.appscursojavaudemy.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.appscursojavaudemy.listas.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    public TarefaDAO(Context context) {
        DBHelper dbase = new DBHelper(context);
        escrever = dbase.getWritableDatabase();
        ler = dbase.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();

        cv.put("nome", tarefa.getTxTarefa());

        try {
            escrever.insert(DBHelper.TABELA,null, cv);
            Log.i("Info db", "Tarefa salva com sucesso");
        }catch (Exception e){

            Log.i("Info db", "Erro ao salvar a tarefa");
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefaList = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA + " ;";
        Cursor c = ler.rawQuery(sql, null);

        while (c.moveToNext()){

            //Instanciando variaveis
            Tarefa tarefa = new Tarefa();
            Long id = c.getLong(c.getColumnIndex("id"));
            String texto = c.getString(c.getColumnIndex("nome"));

            //Preenchedo variaveis
            tarefa.setId(id);
            tarefa.setTxTarefa(texto);

            //Mandando para a lista
            tarefaList.add(tarefa);

        }
        return tarefaList;
    }
}
