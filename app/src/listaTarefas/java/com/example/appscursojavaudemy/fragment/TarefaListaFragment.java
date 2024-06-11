package com.example.appscursojavaudemy.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appscursojavaudemy.R;
import com.example.appscursojavaudemy.adapter.TarefaAdapter;
import com.example.appscursojavaudemy.databinding.FragmentListaTarefaBinding;
import com.example.appscursojavaudemy.helper.DBHelper;
import com.example.appscursojavaudemy.helper.RecyclerItemClickListener;
import com.example.appscursojavaudemy.helper.TarefaDAO;
import com.example.appscursojavaudemy.listas.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaListaFragment extends Fragment{

    private FragmentListaTarefaBinding binding;
    private TarefaAdapter adapter;
    private List<Tarefa> list = new ArrayList<>();
    private Tarefa tarefaSelect;

    private DBHelper db;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListaTarefaBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(), binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Recuperando a posição da tarefa para edição e transformando em string
                        tarefaSelect = list.get(position);
                        Long id = tarefaSelect.getId();

                        TarefaListaFragment fragment = new TarefaListaFragment();
                        Bundle bundle = new Bundle();

                        //Enviando o bundle com a consulta
                        bundle.putLong("testeBundle", id);
                        fragment.setArguments(bundle);
                        NavHostFragment.findNavController(TarefaListaFragment.this)
                                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        tarefaSelect = list.get(position);

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());
                        //Configurando titulo do alerta
                        dialogo.setTitle("Confirmar exclusão");
                        dialogo.setMessage("Deseja excluir a tarefa: [ " + tarefaSelect.getTxTarefa() + " ]?");

                        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TarefaDAO tarefaDAO = new TarefaDAO(getActivity());
                                if(tarefaDAO.deletar(tarefaSelect)){
                                    Toast.makeText(getActivity(), "Tarefa deletada com sucesso", Toast.LENGTH_SHORT).show();
                                    onStart();
                                }else {}

                            }
                        });

                        dialogo.setNegativeButton("Não", null);
                        dialogo.create();
                        dialogo.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(TarefaListaFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);


            }
        });
    }

    public void carregarTarefa(){

        //Lista de Tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getActivity());
        list = tarefaDAO.listar();


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