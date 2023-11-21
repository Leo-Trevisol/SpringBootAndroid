package com.projeto.projetoandroidspring;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.projeto.projetoandroidspring.classesfonte.LoginRequest;
import com.projeto.projetoandroidspring.classesfonte.LoginResponse;
import com.projeto.projetoandroidspring.utils.CustomAsyncTask;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextSenha;

    private Button btLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario = findViewById(R.id.textusuario);

        editTextSenha = findViewById(R.id.textsenha);

        btLogar = findViewById(R.id.btentrar);

        btLogar.setOnClickListener(v -> {
            validarUsuarioSenha(editTextUsuario.getText().toString(), editTextSenha.getText().toString());
        });
    }

    //METODO VALIDACAO DE LOGIN E SENHA ===

    private void validarUsuarioSenha(String txtUsuario, String textsenha) {

        LoginRequest req = new LoginRequest(txtUsuario, textsenha);

        Gson gson = new Gson();

        String json = gson.toJson(req);

        CustomAsyncTask task = new CustomAsyncTask(this, json) {
            @Override
            public void customOnPostExecute() {

                if (this.getConteudoRetorno() != null) {
                    LoginResponse response = gson.fromJson(this.getConteudoRetorno(), LoginResponse.class);

                    if(response.getErro() != null){
                        Toast.makeText(LoginActivity.this, response.getErro(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, response.getToken(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao fazer login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        task.execute("http://192.168.3.104:8080/loginUsuario");


    }
}