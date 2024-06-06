package com.example.appscursojavaudemy.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.appscursojavaudemy.R;


public class NumerosFragment extends Fragment implements View.OnClickListener{

    private ImageButton btnUm, btnDois, btnTres, btnQuatro, btnCinco, btnSeis;
    private MediaPlayer mediaPlayer;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_numeros, container, false);

        btnUm = fragment.findViewById(R.id.um);
        btnDois = fragment.findViewById(R.id.dois);
        btnTres = fragment.findViewById(R.id.tres);
        btnQuatro = fragment.findViewById(R.id.quatro);
        btnCinco = fragment.findViewById(R.id.cinco);
        btnSeis = fragment.findViewById(R.id.seis);

        btnUm.setOnClickListener(this);
        btnDois.setOnClickListener(this);
        btnTres.setOnClickListener(this);
        btnQuatro.setOnClickListener(this);
        btnCinco.setOnClickListener(this);
        btnSeis.setOnClickListener(this);


        return fragment;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.um:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                tocarSom();
                break;
            case R.id.dois:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.two);
                tocarSom();
                break;
            case R.id.tres:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.three);
                tocarSom();
                break;
            case R.id.quatro:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.four);
                tocarSom();
                break;
            case R.id.cinco:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.five);
                tocarSom();
                break;
            case R.id.seis:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.six);
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