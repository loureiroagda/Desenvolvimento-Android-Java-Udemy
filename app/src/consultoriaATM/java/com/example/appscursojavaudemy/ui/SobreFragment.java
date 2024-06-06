package com.example.appscursojavaudemy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appscursojavaudemy.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class SobreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Element versao = new Element();
        versao.setTitle("Versão 1.0");

        return new AboutPage(getActivity())
                .setImage(R.drawable.logo)
                .setDescription(getResources().getString(R.string.descricao))

                .addGroup("Entre em contato")
                .addEmail("atendimento@atmconsultria.com.br", "Envie um email")
                .addWebsite("https://portal.programajaponesonline.com.br/nr/", "Acesse nosso site")

                .addGroup("Redes sociais")
                .addInstagram("loureiroagda", "Instagram")
                .addYoutube("https://www.youtube.com/channel/UCnZffafwZbYVP5XNodQpshA", "YouTube")
                .addGitHub("loureiroagda", "GitHub")

                .addItem(versao)

                .create();
//        return fragment;
    }
}