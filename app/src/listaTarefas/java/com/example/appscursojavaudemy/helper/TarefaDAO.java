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

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getTxTarefa());

        try {
            String[] item = {tarefa.getId().toString()};
            escrever.update(DBHelper.TABELA,cv, "id=?", item);
            Log.i("Info db", "Tarefa salva com sucesso");
        }catch (Exception e){

            Log.i("Info db", "Erro ao salvar a tarefa");
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        String[] item = {tarefa.getId().toString()};
        try {
            escrever.delete(DBHelper.TABELA,"id=?", item);
            Log.i("Info db", "Tarefa salva com sucesso");
        }catch (Exception e){

            Log.i("Info db", "Erro ao salvar a tarefa");
            return false;
        }
        return true;
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

    @Override
    public String selectString(Long id) {
        String texto = null;
        String sql = "SELECT nome FROM " + DBHelper.TABELA + " WHERE id = ?;";
        Cursor c = null;

        try {
            // Usa a instrução rawQuery com um parâmetro
            c = ler.rawQuery(sql, new String[]{String.valueOf(id)});

            // Verifica se há resultados
            if (c != null && c.moveToFirst()) {
                texto = c.getString(c.getColumnIndexOrThrow("nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close(); // Fecha o cursor para liberar recursos
            }
        }

        return texto;
    }

}
