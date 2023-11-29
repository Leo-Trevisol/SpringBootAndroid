package com.projeto.projetoandroidspring;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.projeto.projetoandroidspring.fragments.ModulosAdminFragment;
import com.projeto.projetoandroidspring.fragments.ModulosFragment;

public class ModulosAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulos_admin);
        final int mainColor = Global.getInstance().getDefaultColor();
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        getWindow().setStatusBarColor(mainColor);
        supportActionBar.setBackgroundDrawable(new ColorDrawable(mainColor));
        supportActionBar.setTitle("Módulos Admin");

        showModulos();
    }

    private void showModulos(){
        ModulosAdminFragment modulosFragment = new ModulosAdminFragment();

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
        Intent intent = new Intent(getApplicationContext(), ModulosActivity.class);
        finish(); // Fecha a Activity atual
        startActivity(intent); // Inicia a Activity novamente
    }
}