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
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.HashMap;
import java.util.Map;


public class Tela_Cadastro_login_tela3 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Usuario u;

    private EditText edt_dum, edt_idade, edt_peso;
    private Button btn_cadastrar;

    private String[] mensagens = {"Preencha todos os campos","Cadastro feito com sucesso","Deu Bosta"};

    String usuarioID;

    public Tela_Cadastro_login_tela3() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_login_tela3);

        IniciarComponente();

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecuperaDados(v);
            }
        });

    }

    private void RecuperaDados(View v) {


        String dum = edt_dum.getText().toString();
        String idade = edt_idade.getText().toString();
        String peso = edt_peso.getText().toString();
        DadosUsuarios.dum = dum;
        DadosUsuarios.idade = idade;
        DadosUsuarios.peso = peso;

        u = new Usuario();
        u.setDum(edt_dum.getText().toString());
        u.setIdade(edt_idade.getText().toString());
        u.setPeso(edt_peso.getText().toString());


        if(u.getDum().isEmpty() || u.getIdade().isEmpty() || u.getPeso().isEmpty()){
            Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();

        } else{

            CadastrarUsuario(v);

        }
    }

    private  void CadastrarUsuario(View v){

        String email = DadosUsuarios.email;
        String senha = DadosUsuarios.senha;

        mAuth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            u.setId(mAuth.getUid());;
                            u.salvar();
                            Logar();

                            Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();

                        }else {

                            String erro;

                            try{
                                throw  task.getException();

                            }catch (FirebaseAuthWeakPasswordException e){
                                erro = "Senha com no mínimo 6 caracteres";
                            }
                            catch (FirebaseAuthUserCollisionException e){
                                erro = "Usuario ja existente";
                            }
                            catch (FirebaseAuthInvalidCredentialsException e){
                                erro = "Digite um email valido";
                            }
                            catch (Exception e){
                                erro = "Erro ao cadastrar o usuario";
                            }

                            Snackbar snackbar = Snackbar.make(v,erro, Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();
                        }
                    }
                });
    }

    private void IniciarComponente(){

        edt_dum = findViewById(R.id.edt_dum);
        edt_idade = findViewById(R.id.edt_idade);
        edt_peso = findViewById(R.id.edt_peso);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        mAuth = FirebaseAuth.getInstance();
    }

    private void Logar(){
        startActivity(new Intent(getApplicationContext(), Tela_Login.class));
    }
}
