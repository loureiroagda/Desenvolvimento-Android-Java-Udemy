package com.example.appscursojavaudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.appscursojavaudemy.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EditText nota;
    AnotacaoPreferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nota = findViewById(R.id.nota);

        preferencias = new AnotacaoPreferencias(getApplicationContext());
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String texto = nota.getText().toString();

                if(texto.equals(""))
                {
                    Snackbar.make(v, "Preencha a anotação", Snackbar.LENGTH_LONG).show();
                }else
                {
                    preferencias.salvarNota(texto);
                    Snackbar.make(v, "Nota salvo com sucesso", Snackbar.LENGTH_LONG).show();
                }

            }
        });


        //Recuperar a nota
        String notaTXT = preferencias.recuperaNota();
        if(!notaTXT.equals(""))
        {
            nota.setText(notaTXT);
        }
    }
}