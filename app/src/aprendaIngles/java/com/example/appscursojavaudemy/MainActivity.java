package com.example.appscursojavaudemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.appscursojavaudemy.databinding.ActivityMainBinding;
import com.example.appscursojavaudemy.ui.BichosFragment;
import com.example.appscursojavaudemy.ui.NumerosFragment;
import com.example.appscursojavaudemy.ui.VogaisFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setElevation(0);

        //Configurando um adapter para as abas
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Bichos", BichosFragment.class)
                        .add("Numeros", NumerosFragment.class)
                        .add("Vogais", VogaisFragment.class)
                        .create()
        );

        viewPager = binding.viewpager;
        viewPager.setAdapter(adapter);

        binding.viewPagerTab.setViewPager(viewPager);

    }
}