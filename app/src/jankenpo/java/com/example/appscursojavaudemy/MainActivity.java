package com.example.appscursojavaudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.appscursojavaudemy.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void selectPedra(View v){
        this.opcaoSelecionada("pedra");
    }
    public void selectPapel(View v){
        this.opcaoSelecionada("papel");
    }
    public void selectTesoura(View v){
        this.opcaoSelecionada("tesoura");
    }
    public void opcaoSelecionada(String opcao){

        int x = new Random().nextInt(3);
        String [] opcoes = {"pedra","papel","tesoura"};
        String opcaoIA = opcoes[x];

        switch (opcaoIA)
        {
            case "pedra":
                binding.imgEscolhaIa.setImageResource(R.drawable.pedra);
                break;

            case "papel":
                binding.imgEscolhaIa.setImageResource(R.drawable.papel);
                break;

            case "tesoura":
                binding.imgEscolhaIa.setImageResource(R.drawable.tesoura);
                break;
        }
        //App ganha
        if(
                (opcaoIA == "tesoura" && opcao == "papel") ||
                        (opcaoIA == "papel" && opcao == "pedra") ||
                        (opcaoIA == "pedra" && opcao == "tesoura"))
        {
            binding.resultado.setText("Você perdeu!");
            //Usuário ganha
        }else if(
                (opcao == "tesoura" && opcaoIA == "papel") ||
                        (opcao == "papel" && opcaoIA == "pedra") ||
                        (opcao == "pedra" && opcaoIA == "tesoura"))
        {
            binding.resultado.setText("Você venceu!");
            //Empate
        }else
        {
            binding.resultado.setText("Empate!");
        }
    }
}