package com.example.appscursojavaudemy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.appscursojavaudemy.databinding.ActivityMainBinding;
import com.example.appscursojavaudemy.ui.ClienteFragment;
import com.example.appscursojavaudemy.ui.ContatoFragment;
import com.example.appscursojavaudemy.ui.PrincipalFragment;
import com.example.appscursojavaudemy.ui.ServicoFragment;
import com.example.appscursojavaudemy.ui.SobreFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Definindo os Hooks
        drawerLayout = binding.drawerLayout;
        navigationView = binding.navView;
        toolbar = binding.toolbar;


        //Toolbar
        setSupportActionBar(toolbar);

        //Escondendo ou visualizando menu itens

        //Navigatio Drawer Menu Settings
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PrincipalFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_principal);
        }

    }



    @Override
    public void onBackPressed(){

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId())
        {
            case R.id.nav_principal:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PrincipalFragment()).commit();
                break;

            case R.id.nav_cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClienteFragment()).commit();
                break;

            case R.id.nav_servico:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ServicoFragment()).commit();
                break;

            case R.id.nav_contato:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContatoFragment()).commit();
                break;

            case R.id.nav_sobre:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SobreFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}