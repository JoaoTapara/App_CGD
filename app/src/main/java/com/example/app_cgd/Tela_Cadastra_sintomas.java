package com.example.app_cgd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.app_cgd.DTO.Sintomas;
import com.example.app_cgd.databinding.ActivityTelaCadastraSintomasBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tela_Cadastra_sintomas extends DrawerBase {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("sintomas");
    private Button btn_cadas_sintomas;
    private String humor, sintomas, outros;
    private Sintomas s;

    private ActivityTelaCadastraSintomasBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaCadastraSintomasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        IniciaComponentes();

        Spinner_1();
        Spinner_2();
        Spinner_3();


        btn_cadas_sintomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cadastrarSintomas();
            }
        });


    }



    private void Spinner_1(){

        String[] type = new String[]{"muito bem", "meio bem", "meio ruim", "muito ruim" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type
        );

        AutoCompleteTextView selecionado = findViewById(R.id.spinner_1);
        selecionado.setAdapter(adapter);

        selecionado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                humor = selecionado.getText().toString();

                Toast.makeText(Tela_Cadastra_sintomas.this,selecionado.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void Spinner_2(){

        String[] type = new String[]{"falta de ar", "dor na lombar", "incontinência", "insônia", "estresse" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type
        );

        AutoCompleteTextView selecionado = findViewById(R.id.spinner_2);
        selecionado.setAdapter(adapter);

        selecionado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                sintomas = selecionado.getText().toString();

                Toast.makeText(Tela_Cadastra_sintomas.this,selecionado.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void Spinner_3(){

        String[] type = new String[]{"nem um desses", "excesso de esforço", "doença ou ferimento", "fadiga mental" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type
        );

        AutoCompleteTextView selecionado = findViewById(R.id.spinner_3);
        selecionado.setAdapter(adapter);

        selecionado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                outros = selecionado.getText().toString();

                Toast.makeText(Tela_Cadastra_sintomas.this,selecionado.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void IniciaComponentes(){

        btn_cadas_sintomas = findViewById(R.id.btn_cadas_sintomas);

    }

    private void cadastrarSintomas(){
        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
        String usuarioAtual = userId.getUid();
        s = new Sintomas();
        s.setHumor(humor);
        s.setSintomas(sintomas);
        s.setOutros(outros);

        databaseReference.child(usuarioAtual).push().setValue(s);
        Toast.makeText(this, "Sintomas Cadastrados", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Tela_Cadastra_sintomas.this, Tela_Principal.class));
        finish();


    }

}