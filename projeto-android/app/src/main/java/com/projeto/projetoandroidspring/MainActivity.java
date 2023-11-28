package com.projeto.projetoandroidspring;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.projeto.projetoandroidspring.fragments.ModulosFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int mainColor = Global.getInstance().getDefaultColor();
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        getWindow().setStatusBarColor(mainColor);
        supportActionBar.setBackgroundDrawable(new ColorDrawable(mainColor));
        supportActionBar.setTitle("Módulos");

        showModulos();

    }

    private void showModulos(){
        ModulosFragment modulosFragment = new ModulosFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container, modulosFragment);

        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        finish(); // Fecha a Activity atual
        startActivity(intent); // Inicia a Activity novamente
    }
}