package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.app_cgd.databinding.ActivityTelaCataoBinding;
import com.example.app_cgd.databinding.ActivityTelaPrincipalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tela_Catao extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String usuarioID;
    private ActivityTelaCataoBinding binding;

    private TextView tv_id_nome, tv_id_idade, tv_id_peso, tv_id_dum, tv_id_dpp, tv_id_telefone, tv_id_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaCataoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Iniciacomponetes();
        ChamadadosDaGestante();
    }

    private void ChamadadosDaGestante(){
        DatabaseReference reference = database.getReference().child("usuarios").child(usuarioID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                tv_id_dpp.setText(snapshot.child("dpp").getValue(String.class));
                tv_id_dum.setText(snapshot.child("dum").getValue(String.class));
                tv_id_nome.setText(snapshot.child("nome").getValue(String.class));
                tv_id_idade.setText(snapshot.child("idade").getValue(String.class));
                tv_id_peso.setText(snapshot.child("peso").getValue(String.class));
                tv_id_telefone.setText(snapshot.child("telefone").getValue(String.class));
                tv_id_email.setText(snapshot.child("email").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void Iniciacomponetes(){

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        tv_id_dpp = findViewById(R.id.tv_id_dpp);
        tv_id_dum = findViewById(R.id.tv_id_dum);
        tv_id_nome = findViewById(R.id.tv_id_nome);
        tv_id_idade = findViewById(R.id.tv_id_idade);
        tv_id_peso = findViewById(R.id.tv_id_peso);
        tv_id_telefone = findViewById(R.id.tv_id_telefone);
        tv_id_email = findViewById(R.id.tv_id_email);


    }

}