package com.example.appscursojavaudemy.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appscursojavaudemy.R;
import com.example.appscursojavaudemy.databinding.FragmentPrincipalBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class PrincipalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_principal, container, false);

        FloatingActionButton fab = fragment.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmail();
            }
        });

        return fragment;
    }

    private void enviarEmail() {

        String celular = "tel:2124621560";
        String imagem = "https://br.pinterest.com/pin/615093261581164919/";
        String endereco = "https://www.google.com/maps/place/%E7%A8%8B%E4%B9%85%E4%BF%9D%E5%85%AD%E5%9C%B0%E8%94%B5/" +
                "@35.6560254,139.4098481,18.25z/data=!4m6!3m5!1s0x6018e386e9661a6f:0xd0ca48cf6c3fdc6b!8m2!3d35.6562455!4d139.41053!16s%2F" +
                "g%2F11f9v7z196?authuser=0&entry=ttu";

//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(celular));
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"loureiroagda@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem Automático");

        //Mime Types
        intent.setType("message/rfc822");//Email
//        intent.setType("text/plain");//app de texto
//        intent.setType("image/*");//app de imagem
//        intent.setType("application/pdf");//app de pdf

        startActivity(Intent.createChooser(intent, "Compartilhar"));
    }
}