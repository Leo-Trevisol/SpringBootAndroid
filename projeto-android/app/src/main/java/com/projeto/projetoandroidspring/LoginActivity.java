package com.projeto.projetoandroidspring;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.projeto.projetoandroidspring.classesfonte.request.LoginUsuarioRequest;
import com.projeto.projetoandroidspring.classesfonte.request.UsuarioRequest;
import com.projeto.projetoandroidspring.classesfonte.response.LoginUsuarioResponse;
import com.projeto.projetoandroidspring.classesfonte.response.UsuarioResponse;
import com.projeto.projetoandroidspring.classesfonte.vo.LoginVO;
import com.projeto.projetoandroidspring.utils.CustomAsyncTask;
import com.projeto.projetoandroidspring.utils.DateUtils;
import com.projeto.projetoandroidspring.utils.Utils;
import com.projeto.projetoandroidspring.view.CustomEditText;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextSenha;

    private Button btLogar;

    private ImageView btAddUser;

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

        btAddUser = findViewById(R.id.btAdduser);

        btAddUser.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_ProjetoAndroidSpring));
            builder.setTitle("Cadastrar Usuario");
            View viewInflated = LayoutInflater.from(this).inflate(R.layout.fragment_adicionar_novo_usuario, null, false);
            builder.setView(viewInflated);
            final AlertDialog dialog = builder.create();
            dialog.show();

            CustomEditText usuario = viewInflated.findViewById(R.id.txtInfoUsuario);

            CustomEditText senha = viewInflated.findViewById(R.id.txtInfoSenha);

            CustomEditText senhaConfirm = viewInflated.findViewById(R.id.txtInfoSenhaConfirm);

            CustomEditText email = viewInflated.findViewById(R.id.txtInfoEmail);

            CustomEditText nascimento = viewInflated.findViewById(R.id.txtInfoNascimento);

            Button btCadastrar = viewInflated.findViewById(R.id.btCadastra);

            btCadastrar.setOnClickListener(v1 -> {
                if (Utils.isEmpty(usuario.getText().trim()) || Utils.isEmpty(senha.getText().trim())
                        || Utils.isEmpty(senhaConfirm.getText().trim()) || Utils.isEmpty(email.getText().trim())
                        || Utils.isEmpty(nascimento.getText().trim())) {
                    Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                } else if (!senha.getText().trim().equals(senhaConfirm.getText().trim())) {
                    Toast.makeText(this, "As senhas devem ser iguais", Toast.LENGTH_SHORT).show();
                } else {
                    if (nascimento.getText().length() > 0 || nascimento.getText().length() > 0) {
                        nascimento.setText(nascimento.getText() + " 00:00:00");
                    }

                    LoginVO loginVO = new LoginVO(usuario.getText().trim(), senha.getText().trim(),
                            email.getText().trim(), nascimento.getText().trim());

                    cadastrarUsuario(loginVO, dialog);


                }
            });


        });

    }

    //METODO VALIDACAO DE LOGIN E SENHA ===

    private void validarUsuarioSenha(String txtUsuario, String textsenha) {

        LoginUsuarioRequest req = new LoginUsuarioRequest(txtUsuario, textsenha);

        Gson gson = new Gson();

        String json = gson.toJson(req);

        CustomAsyncTask task = new CustomAsyncTask(this, json) {
            @Override
            public void customOnPostExecute() {

                if (this.getConteudoRetorno() != null) {
                    LoginUsuarioResponse response = gson.fromJson(this.getConteudoRetorno(), LoginUsuarioResponse.class);

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

    //METODO CADASTRO NOVO USUARIO ===

    private void cadastrarUsuario(LoginVO loginVO, AlertDialog dialog) {

        UsuarioRequest req = new UsuarioRequest(loginVO);

        Gson gson = new Gson();

        String json = gson.toJson(req);

        CustomAsyncTask task = new CustomAsyncTask(this, json) {
            @Override
            public void customOnPostExecute() {

                if (this.getConteudoRetorno() != null) {
                    UsuarioResponse response = gson.fromJson(this.getConteudoRetorno(), UsuarioResponse.class);

                    if(response.getErro() != null){
                        Toast.makeText(LoginActivity.this, response.getErro(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, response.getToken(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Falha ao cadastrar usuario", Toast.LENGTH_SHORT).show();
                }
            }
        };

        task.execute("http://192.168.3.104:8080/inserirUsuario");


    }


}