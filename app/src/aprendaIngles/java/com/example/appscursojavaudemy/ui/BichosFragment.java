package com.example.appscursojavaudemy.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.appscursojavaudemy.R;


public class BichosFragment extends Fragment implements View.OnClickListener{

    private ImageButton btncao, btngato, btnleao, btnmacaco, btnovelha, btnvaca;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_bichos, container, false);

        btncao = fragment.findViewById(R.id.cao);
        btngato = fragment.findViewById(R.id.gato);
        btnleao = fragment.findViewById(R.id.leao);
        btnmacaco = fragment.findViewById(R.id.macaco);
        btnovelha = fragment.findViewById(R.id.ovelha);
        btnvaca = fragment.findViewById(R.id.vaca);

        btncao.setOnClickListener(this);
        btngato.setOnClickListener(this);
        btnleao.setOnClickListener(this);
        btnmacaco.setOnClickListener(this);
        btnovelha.setOnClickListener(this);
        btnvaca.setOnClickListener(this);

        return fragment;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.cao:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dog);
                tocarSom();
                break;

            case R.id.gato:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cat);
                tocarSom();
                break;

            case R.id.leao:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lion);
                tocarSom();
                break;

            case R.id.macaco:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.monkey);
                tocarSom();
                break;

            case R.id.ovelha:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sheep);
                tocarSom();
                break;

            case R.id.vaca:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cow);
                tocarSom();
                break;
        }

    }

    public void tocarSom(){

        if(mediaPlayer!=null)
        {
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}