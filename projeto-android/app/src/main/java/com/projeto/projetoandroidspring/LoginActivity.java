package com.projeto.projetoandroidspring;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.projeto.projetoandroidspring.utils.GenericMask;
import com.projeto.projetoandroidspring.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextSenha, nascimento;

    private Button btLogar;

    private ImageView btAddUser;
    private static DatePickerDialog picker;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        calendar = Calendar.getInstance();

        editTextUsuario = findViewById(R.id.textusuario);

        editTextSenha = findViewById(R.id.textsenha);

        btLogar = findViewById(R.id.btentrar);

        btLogar.setOnClickListener(v -> {
            if(Utils.isEmpty(editTextUsuario.getText().toString().trim()) ||
                    Utils.isEmpty(editTextSenha.getText().toString().trim()))
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
            else
            validarUsuarioSenha(editTextUsuario.getText().toString(), editTextSenha.getText().toString());
        });

        btAddUser = findViewById(R.id.btAdduser);

        btAddUser.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_ProjetoAndroidSpring));
            builder.setTitle("Cadastrar Usuário");
            View viewInflated = LayoutInflater.from(this).inflate(R.layout.fragment_adicionar_novo_usuario, null, false);
            builder.setView(viewInflated);
            final AlertDialog dialog = builder.create();
            dialog.show();

            EditText usuario = viewInflated.findViewById(R.id.txtInfoUsuario);

            EditText senha = viewInflated.findViewById(R.id.txtInfoSenha);

            EditText senhaConfirm = viewInflated.findViewById(R.id.txtInfoSenhaConfirm);

            EditText email = viewInflated.findViewById(R.id.txtInfoEmail);

            ImageView calendario = viewInflated.findViewById(R.id.calendario);

            calendario.setColorFilter(R.color.colorPrimary);

            nascimento = viewInflated.findViewById(R.id.txtInfoNascimento);
            nascimento.addTextChangedListener(GenericMask.insert(nascimento, GenericMask.DATE_MASK));

            calendario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog();
                }
            });
            Button btCadastrar = viewInflated.findViewById(R.id.btCadastra);

            btCadastrar.setOnClickListener(v1 -> {
                if (Utils.isEmpty(usuario.getText().toString().trim()) || Utils.isEmpty(senha.getText().toString().trim())
                        || Utils.isEmpty(senhaConfirm.getText().toString().trim()) || Utils.isEmpty(email.getText().toString().trim())
                        || Utils.isEmpty(nascimento.getText().toString().trim())) {
                    Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                } else if (!senha.getText().toString().trim().equals(senhaConfirm.getText().toString().trim())) {
                    Toast.makeText(this, "As senhas devem ser iguais", Toast.LENGTH_SHORT).show();
                } else if (!Utils.isValidEmail(email.getText().toString())) {
                    Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
                }else if(!DateUtils.isValidDate(nascimento.getText().toString().trim())){
                    Toast.makeText(this, "Data inválida", Toast.LENGTH_SHORT).show();
                }else {
                    if (nascimento.getText().length() > 0 || nascimento.getText().length() > 0) {
                        nascimento.setText(nascimento.getText().toString());
                    }
                    LoginVO loginVO = new LoginVO(usuario.getText().toString().trim(), senha.getText().toString().trim(),
                            email.getText().toString().trim(), nascimento.getText().toString().trim());

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

        CustomAsyncTask task = new CustomAsyncTask(this, json, 2000, "Aguarde...") {
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

        CustomAsyncTask task = new CustomAsyncTask(this, json,2000, "Aguarde...") {
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
                    Toast.makeText(LoginActivity.this, "Falha ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                }
            }
        };

        task.execute("http://192.168.3.104:8080/inserirUsuario");


    }

    private void showDatePickerDialog() {
        // Criar uma instância de DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Atualizar a data no objeto Calendar
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Atualizar o texto do EditText com a data selecionada
                        updateEditTextData();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        // Exibir o DatePickerDialog
        datePickerDialog.show();
    }

    private void updateEditTextData() {
        // Atualizar o texto do EditText com a data formatada
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        nascimento.setText(dateFormat.format(calendar.getTime()));
    }
}

