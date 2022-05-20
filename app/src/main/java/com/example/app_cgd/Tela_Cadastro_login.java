package com.example.app_cgd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_cgd.DTO.Usuario;


public class Tela_Cadastro_login extends AppCompatActivity {

    EditText edt_email, edt_senha, edt_nome, edt_telefone, edt_cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_login);

        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_senha = (EditText)findViewById(R.id.edt_senha);
        edt_nome = (EditText)findViewById(R.id.edt_nome);
        edt_cpf = (EditText)findViewById(R.id.edt_cpf);
        edt_telefone = (EditText)findViewById(R.id.edt_telefone);

    }








}
