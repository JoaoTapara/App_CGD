package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_cgd.DTO.Usuario;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Tela_Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText edt_email, edt_senha;

    private Button btn_Logar, btn_acesso;

    private Usuario u;

    private String[] mensagens = {"Email ou senha invalido"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        IniciarComponentes();

        btn_acesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tela_Login.this, Tela_Cadastro_login.class));
                finish();
            }
        });


        btn_Logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReceberDados();
                Logar(v);

            }
        });

    }

    private void Logar(View v) {

        mAuth.signInWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseUser user = mAuth.getCurrentUser();


                            startActivity(new Intent(Tela_Login.this,Tela_Principal.class));

                        } else {

                            Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();

                            edt_senha.setText("");

                        }
                    }
                });
    }


    private void ReceberDados() {

        u = new Usuario();
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());

    }

    private  void IniciarComponentes(){

        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);

        btn_Logar = findViewById(R.id.btn_logar);
        btn_acesso = findViewById(R.id.btn_agendar_consulta);

        mAuth = FirebaseAuth.getInstance();

    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
//        if(usuarioAtual != null){
//            startActivity(new Intent(getApplicationContext(), Tela_Principal.class));
//        }
//    }

}