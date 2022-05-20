package com.example.app_cgd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.app_cgd.DTO.Usuario;
import com.example.app_cgd.Fragments.Home_Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Tela_Login extends AppCompatActivity {


    EditText edt_email, edt_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_senha = (EditText)findViewById(R.id.edt_senha);

    }



    public void clique_cadastro(View v){
        Intent tela = new Intent(this, Tela_Cadastro_login.class);
        startActivity(tela);
    }

}