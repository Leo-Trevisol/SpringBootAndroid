package com.projeto.projetoandroidspring.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projeto.projetoandroidspring.R;
import com.projeto.projetoandroidspring.interfaces.ToolBarListener;

public class AdicionarNovoUsuarioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adicionar_novo_usuario, container, false);
    }
}