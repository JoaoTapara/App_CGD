package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.app_cgd.databinding.ActivityTelaCataoBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tela_Catao extends DrawerBase {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String usuarioID;
    private ActivityTelaCataoBinding binding;

    private TextView tv_id_nome, tv_id_idade, tv_id_peso, tv_id_dum, tv_id_dpp, tv_id_telefone, tv_id_email, tv_id_cpf,
            tv_id_sangue_c, tv_id_altura_au, tv_id_semana_au, tv_id_hepat_b, tv_id_hepat_c, tv_id_toxop, tv_id_hiv,
            tv_id_fcf, tv_id_nome_m, tv_id_telefone_m, tv_id_crm, tv_id_pa, tv_id_h_b, tv_id_i, tv_id_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaCataoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Iniciacomponetes();
        ChamadadosDaGestante();
        ChamadadosDoCartao();
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
                tv_id_cpf.setText(snapshot.child("cpf").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ChamadadosDoCartao(){

        DatabaseReference reference = database.getReference().child("dados").child(usuarioID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                tv_id_sangue_c.setText(snapshot.child("Sangue").getValue(String.class));
                tv_id_altura_au.setText(snapshot.child("altura_au").getValue(String.class));
                tv_id_semana_au.setText(snapshot.child("semana_au").getValue(String.class));
                tv_id_hepat_b.setText(snapshot.child("e_HepatiteB").getValue(String.class));
                tv_id_hepat_c.setText(snapshot.child("e_HepatitteC").getValue(String.class));
                tv_id_toxop.setText(snapshot.child("e_Toxoplasmose").getValue(String.class));
                tv_id_hiv.setText(snapshot.child("e_hiv").getValue(String.class));
                tv_id_fcf.setText(snapshot.child("fcf").getValue(String.class));
                tv_id_nome_m.setText(snapshot.child("nome").getValue(String.class));
                tv_id_telefone_m.setText(snapshot.child("telefone").getValue(String.class));
                tv_id_crm.setText(snapshot.child("crm").getValue(String.class));
                tv_id_pa.setText(snapshot.child("pa").getValue(String.class));
                tv_id_h_b.setText(snapshot.child("v_HepatiteB").getValue(String.class));
                tv_id_i.setText(snapshot.child("v_Influenza").getValue(String.class));
                tv_id_t.setText(snapshot.child("v_Triplicebacteriana").getValue(String.class));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });

    }

    private void Iniciacomponetes(){

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //chamando dados do child "ususario"
        tv_id_dpp = findViewById(R.id.tv_id_dpp);
        tv_id_dum = findViewById(R.id.tv_id_dum);
        tv_id_nome = findViewById(R.id.tv_id_nome);
        tv_id_idade = findViewById(R.id.tv_id_idade);
        tv_id_peso = findViewById(R.id.tv_id_peso);
        tv_id_telefone = findViewById(R.id.tv_id_telefone);
        tv_id_email = findViewById(R.id.tv_id_email);
        tv_id_cpf = findViewById(R.id.tv_id_cpf);

        //chamand0o dados do child "dados"
        tv_id_sangue_c = findViewById(R.id.tv_id_sangue_c);
        tv_id_altura_au = findViewById(R.id.tv_id_altura_au);
        tv_id_semana_au = findViewById(R.id.tv_id_semana_au);
        tv_id_hepat_b = findViewById(R.id.tv_id_hepat_b);
        tv_id_hepat_c = findViewById(R.id.tv_id_hepat_c);
        tv_id_toxop = findViewById(R.id.tv_id_toxop);
        tv_id_hiv = findViewById(R.id.tv_id_hiv);
        tv_id_fcf = findViewById(R.id.tv_id_fcf);
        tv_id_nome_m = findViewById(R.id.tv_id_nome_m);
        tv_id_telefone_m = findViewById(R.id.tv_id_telefone_m);
        tv_id_crm = findViewById(R.id.tv_id_crm);
        tv_id_pa = findViewById(R.id.tv_id_pa);
        tv_id_h_b = findViewById(R.id.tv_id_h_b);
        tv_id_i = findViewById(R.id.tv_id_i);
        tv_id_t = findViewById(R.id.tv_id_t);



    }

}