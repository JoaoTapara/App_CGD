package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_cgd.DTO.DadosUsuarios;
import com.example.app_cgd.DTO.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

import org.w3c.dom.Document;

import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Map;


public class Tela_Cadastro_login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Usuario u;

    private EditText edt_email, edt_senha, edt_confirmar_senha;
    private Button btn_proximo;

    private String[] mensagens = {"Preencha todos os campos","Cadastro feito com sucesso","Deu Bosta","Senhas não são compatíveis"};

    String usuarioID;

    public Tela_Cadastro_login() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_login);

        IniciarComponente();

        btn_proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecuperaDados(v);
            }
        });

    }

    private void RecuperaDados(View v) {

        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();
        String senha_conf = edt_confirmar_senha.getText().toString();
        DadosUsuarios.email = email;
        DadosUsuarios.senha = senha;
        DadosUsuarios.senha_conf = senha_conf;

        u = new Usuario();
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());
        u.setSenha_conf(edt_confirmar_senha.getText().toString());

        if(email.isEmpty() || senha.isEmpty() || senha_conf.isEmpty()){

            Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();

        }else if(!senha.equals(senha_conf)){
            Snackbar snackbar = Snackbar.make(v, mensagens[3], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();

        }else{
            startActivity(new Intent(getApplicationContext(), Tela_Cadastro_login_tela2.class));
            finish();
        }
    }

    private void IniciarComponente(){

        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
        edt_confirmar_senha = findViewById(R.id.edt_confirma_senha);
        btn_proximo = findViewById(R.id.btn_proximo);
        mAuth = FirebaseAuth.getInstance();
    }
}
