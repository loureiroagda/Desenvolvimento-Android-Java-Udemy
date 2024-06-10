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
    private int menu;

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

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_opcoes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.salvar:
                //Execução da ação
                TarefaDAO tarefaDAO = new TarefaDAO(getActivity());
                String texto = binding.editText.getText().toString();

                if(!texto.isEmpty())
                {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setTxTarefa(texto);
                    tarefaDAO.salvar(tarefa);
                    NavHostFragment.findNavController(AdicionatTarefaFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }else {

                    Toast.makeText(getActivity(), "Escreva alguma coisa", Toast.LENGTH_LONG).show();
                }


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}