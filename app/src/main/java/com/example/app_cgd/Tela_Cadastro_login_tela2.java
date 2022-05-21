package com.example.app_cgd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Tela_Cadastro_login_tela2 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Usuario u;

    private EditText edt_nome, edt_telefone, edt_cpf;
    private Button btn_proximo2;

    private String[] mensagens = {"Preencha todos os campos","Cadastro feito com sucesso","Deu Bosta"};

    String usuarioID;

    public Tela_Cadastro_login_tela2() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_login_tela2);

        IniciarComponente();

        btn_proximo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecuperaDados(v);
            }
        });

    }

    private void RecuperaDados(View v) {

        String nome = edt_nome.getText().toString();
        String cpf = edt_cpf.getText().toString();
        String telefone = edt_telefone.getText().toString();
        DadosUsuarios.nome = nome;
        DadosUsuarios.cpf = cpf;
        DadosUsuarios.telefone = telefone;

        u = new Usuario();
        u.setNome(edt_nome.getText().toString());
        u.setCpf(edt_cpf.getText().toString());
        u.setTelefone(edt_telefone.getText().toString());

        if(nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() ){

            Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();

        } else{
            startActivity(new Intent(getApplicationContext(), Tela_Cadastro_login_tela3.class));
            finish();
        }
    }

    private void IniciarComponente(){

        edt_nome = findViewById(R.id.edt_nome);
        edt_cpf = findViewById(R.id.edt_cpf);
        edt_telefone = findViewById(R.id.edt_telefone);
        btn_proximo2 = findViewById(R.id.btn_proximo2);
        mAuth = FirebaseAuth.getInstance();
    }
}
