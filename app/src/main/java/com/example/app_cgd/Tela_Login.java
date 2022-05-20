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

    public void clique_logar(View v){

        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();

        //monto a requisição
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        //faz chamada no servidor

        Call<Usuario> call = api.logar(email, senha);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                //pega a resposta do servidor
                Usuario retorno = response.body();

                //verifica se encontrou
                if(retorno.getId_gestante().equals("0")){

                    Toast.makeText(getApplicationContext(), "Email ou senha Invalido", Toast.LENGTH_SHORT).show();
                    edt_senha.setText("");

                    //finish();

                }else {

                    String id_user = retorno.getId_gestante();
                    Tela_Principal.id_user = id_user;


                            Toast.makeText(Tela_Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();



                    Intent tela = new Intent(getApplicationContext(), Tela_Principal.class);
                    startActivity(tela);

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        Intent tela = new Intent(getApplicationContext(), Tela_Cadastro_Cartao.class);
//        startActivity(tela);

    }


    public void clique_cadastro(View v){
        Intent tela = new Intent(this, Tela_Cadastro_login.class);
        startActivity(tela);
    }

}