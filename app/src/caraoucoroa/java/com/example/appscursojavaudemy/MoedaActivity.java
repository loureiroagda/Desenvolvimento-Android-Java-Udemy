package com.example.appscursojavaudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MoedaActivity extends AppCompatActivity {

    ImageView voltar, moeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moeda);

        Bundle dados = getIntent().getExtras();
        int x = dados.getInt("numero");

        voltar = findViewById(R.id.voltar);
        moeda = findViewById(R.id.moeda);

        if(x==0)
            moeda.setImageResource(R.drawable.moeda_cara);
        else moeda.setImageResource(R.drawable.moeda_coroa);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}