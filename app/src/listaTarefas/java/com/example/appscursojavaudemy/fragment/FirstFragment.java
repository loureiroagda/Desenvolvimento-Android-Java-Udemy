package com.example.appscursojavaudemy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appscursojavaudemy.R;
import com.example.appscursojavaudemy.adapter.TarefaAdapter;
import com.example.appscursojavaudemy.databinding.FragmentFirstBinding;
import com.example.appscursojavaudemy.helper.RecyclerItemClickListener;
import com.example.appscursojavaudemy.listas.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private TarefaAdapter adapter;
    private List<Tarefa> list = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(), binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Item Click", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Item Long Click", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
                )
        );

    }

    public void carregarTarefa(){

        //Lista de Tarefas
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTxTarefa("Tarefa 1");
        list.add(tarefa1);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTxTarefa("Tarefa 2");
        list.add(tarefa2);

        Tarefa tarefa3 = new Tarefa();
        tarefa3.setTxTarefa("Tarefa 3");
        list.add(tarefa3);


        //Configurar adapter
        adapter = new TarefaAdapter(list);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        carregarTarefa();
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}