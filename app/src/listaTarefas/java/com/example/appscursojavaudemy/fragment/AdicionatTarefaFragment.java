package com.example.appscursojavaudemy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.appscursojavaudemy.R;
import com.example.appscursojavaudemy.databinding.FragmentAddTarefaBinding;
import com.example.appscursojavaudemy.helper.TarefaDAO;
import com.example.appscursojavaudemy.listas.Tarefa;

public class AdicionatTarefaFragment extends Fragment {

    private FragmentAddTarefaBinding binding;
    public Long idEdit;

    public String texto = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddTarefaBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        texto = recuperarDado();
        if (texto != null)
        {
            binding.editText.setText(texto);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_opcoes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public String recuperarDado(){
        Bundle bundle = getArguments();
        if (bundle != null)
        {
            TarefaDAO tarefaDAO = new TarefaDAO(getActivity());
            String txt = tarefaDAO.selectString(bundle.getLong("testeBundle"));
            idEdit = bundle.getLong("testeBundle");
            return txt;

        }else
        {
            binding.editText.setText("");
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.salvar:
                Tarefa tarefa = new Tarefa();
                TarefaDAO tarefaDAO = new TarefaDAO(getActivity());
                //Edição
                if (texto != null){

                    texto = binding.editText.getText().toString();
                    tarefa.setTxTarefa(texto);
                    tarefa.setId(idEdit);

                    if(tarefaDAO.atualizar(tarefa)){
                        Toast.makeText(getActivity(), "Tarefa atualizada com sucesso", Toast.LENGTH_LONG).show();
                    }else{Toast.makeText(getActivity(), "Erro ao atualizar tarefa", Toast.LENGTH_LONG).show();}

                    NavHostFragment.findNavController(AdicionatTarefaFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);

                }else{//salvar

                    texto = binding.editText.getText().toString();
                    if(!texto.isEmpty())
                    {
                        tarefa.setTxTarefa(texto);

                        if(tarefaDAO.salvar(tarefa)){
                            Toast.makeText(getActivity(), "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                        }else{Toast.makeText(getActivity(), "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();}

                        NavHostFragment.findNavController(AdicionatTarefaFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FirstFragment);
                    }else {
                        Toast.makeText(getActivity(), "Escreva alguma coisa", Toast.LENGTH_LONG).show();
                    }
                }
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}