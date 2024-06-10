package com.example.appscursojavaudemy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appscursojavaudemy.R;
import com.example.appscursojavaudemy.listas.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private List<Tarefa> tarefaList;


    public TarefaAdapter(List<Tarefa> list) {
        this.tarefaList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_tarefa_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tarefa tarefa = tarefaList.get(position);
        holder.tarefa.setText(tarefa.getTxTarefa());

    }

    @Override
    public int getItemCount() {

        return this.tarefaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tarefa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.txt_tarefa);
        }
    }
}
