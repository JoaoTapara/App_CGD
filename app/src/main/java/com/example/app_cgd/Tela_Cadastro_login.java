package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private EditText edt_email, edt_senha, edt_senha_conf, edt_nome, edt_telefone, edt_cpf;
    private Button btn_cadastrar;

    private String[] mensagens = {"Preencha todos os campos","Cadastro feito com sucesso","Deu Bosta"};

    String usuarioID;

    public Tela_Cadastro_login() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_login);

        IniciarComponente();

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecuperaDados(v);


            }
        });

    }

    private void RecuperaDados(View v) {


        String nome = edt_nome.getText().toString();
        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();
        String senha_conf = edt_senha_conf.getText().toString();
        String cpf = edt_cpf.getText().toString();
        String telefone = edt_telefone.getText().toString();

        u = new Usuario();
        u.setNome(edt_nome.getText().toString());
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());
        u.setSenha_conf(edt_senha_conf.getText().toString());
        u.setCpf(edt_cpf.getText().toString());
        u.setTelefone_gestante(edt_telefone.getText().toString());

        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senha_conf.isEmpty() || cpf.isEmpty() || telefone.isEmpty() ){

            Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();

        } else{

            CadastrarUsuario(v);

        }
    }

    private  void CadastrarUsuario(View v){

        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            u.setId(mAuth.getUid());
                            u.setNome(edt_nome.getText().toString());
                            u.salvar();

                            //SalvarDadosUsuario();

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

    private void SalvarDadosUsuario(){

        String nome = u.getNome().toString();
        String cpf = u.getCpf().toString();
        String telefone = u.getTelefone_gestante().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Map<String,Object> usuario = new HashMap<>();
        usuario.put("nome",nome);
        usuario.put("cpf",cpf);
        usuario.put("telefone",telefone);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();




    }

    private void IniciarComponente(){

        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
        edt_senha_conf = findViewById(R.id.edt_senha_conf);
        edt_nome = findViewById(R.id.edt_nome);
        edt_cpf = findViewById(R.id.edt_cpf);
        edt_telefone = findViewById(R.id.edt_telefone);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);

        mAuth = FirebaseAuth.getInstance();

    }








}
