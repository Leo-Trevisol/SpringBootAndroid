package com.projeto.projetoandroidspring.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.projeto.projetoandroidspring.LoginActivity;
import com.projeto.projetoandroidspring.ModulosAdminActivity;
import com.projeto.projetoandroidspring.R;
import com.projeto.projetoandroidspring.classesfonte.request.UsuarioRequest;
import com.projeto.projetoandroidspring.classesfonte.response.LstUsuarioResponse;
import com.projeto.projetoandroidspring.classesfonte.response.UsuarioResponse;
import com.projeto.projetoandroidspring.classesfonte.vo.LoginVO;
import com.projeto.projetoandroidspring.utils.CustomAsyncTask;
import com.projeto.projetoandroidspring.utils.Modulo;
import com.projeto.projetoandroidspring.utils.ModulosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ModulosAdminFragment extends Fragment {

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

        lstModulos.add( new Modulo(R.drawable.buscauser, "CONSULTAR USUÁRIOS", v -> consultarUsuarios()));
        lstModulos.add( new Modulo(R.drawable.newuser, "INSERIR USUÁRIO", v -> openActivity()));

        gridView.setAdapter(new ModulosAdapter(lstModulos, getActivity()));
    }

    public void openActivity(){
        Intent i = new Intent(getActivity(), ModulosAdminActivity.class);
        startActivity(i);
    }

    private void consultarUsuarios() {

        Gson gson = new Gson();

        CustomAsyncTask task = new CustomAsyncTask(getActivity(), "",2000, "Aguarde...") {
            @Override
            public void customOnPostExecute() {

                if (this.getConteudoRetorno() != null) {
                    LstUsuarioResponse response = gson.fromJson(this.getConteudoRetorno(), LstUsuarioResponse.class);

                    if(response.getErro() != null){
                        Toast.makeText(getActivity(), response.getErro(), Toast.LENGTH_SHORT).show();
                    }else{
                    }

                } else {
                    Toast.makeText(getActivity(), "Falha ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                }
            }
        };

        task.execute("http://192.168.3.104:8080/api/v1/usuarios/findAll");


    }


}