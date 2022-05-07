package com.example.app_cgd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_cgd.DTO.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public void clique_cadastro_login (View v){

        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();
        String nome = edt_nome.getText().toString();
        String cpf = edt_cpf.getText().toString();
        String telefone_gestante = edt_telefone.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        //Faz a requisição para o servidor
        Call<Usuario> call = api.cadastrar(nome, email, senha, cpf, telefone_gestante);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Toast.makeText(getApplicationContext(), "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show();
                //finish();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}