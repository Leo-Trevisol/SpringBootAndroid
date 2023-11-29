package com.projeto.projetoandroidspring.fragments;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.projeto.projetoandroidspring.ModulosActivity;
import com.projeto.projetoandroidspring.ModulosAdminActivity;
import com.projeto.projetoandroidspring.R;
import com.projeto.projetoandroidspring.utils.Modulo;
import com.projeto.projetoandroidspring.utils.ModulosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ModulosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.AppTheme);


        View view = inflater.inflate(R.layout.fragment_modulos, container, false);

        chamarModulos(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void chamarModulos(View view) {
        final GridView gridView = view.findViewById(R.id.gridView_modulos);
        List<Modulo> lstModulos = new ArrayList<Modulo>();

        lstModulos.add( new Modulo(R.drawable.admin, "RECURSOS ADM", v -> openActivity()));

        gridView.setAdapter(new ModulosAdapter(lstModulos, getActivity()));
    }

    public void openActivity(){
        Intent i = new Intent(getActivity(), ModulosAdminActivity.class);
        startActivity(i);
    }

}