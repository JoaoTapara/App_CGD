package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app_cgd.DTO.Sistemas;
import com.example.app_cgd.DTO.Usuario;
import com.example.app_cgd.databinding.ActivityTelaPrincipalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Tela_Principal extends DrawerBase {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String usuarioID;

    private TextView tv_semana, tv_dpp;

    public static String id_user;
    private ActivityTelaPrincipalBinding binding;

    private NavHostFragment navHostFragment;
    private NavController navController;



    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Iniciacomponetes();
        semana();



        DatabaseReference reference = database.getReference().child("usuarios").child(usuarioID);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                tv_dpp.setText(snapshot.child("dpp").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void semana() {

        DatabaseReference reference = database.getReference().child("usuarios").child(usuarioID);
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int ano = snapshot.child("ano_dum").getValue(Integer.class);
                int mes = snapshot.child("mes_dum").getValue(Integer.class);
                int dia = snapshot.child("dia_dum").getValue(Integer.class);

                LocalDateTime dataCadastro = LocalDateTime.of(ano, mes, dia, 0, 0, 0);
                LocalDateTime hoje = LocalDateTime.now();
                long dias = dataCadastro.until(hoje, ChronoUnit.DAYS);

                String semana_q = String.valueOf(dias / 7);

                tv_semana.setText(semana_q);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void Iniciacomponetes(){

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        tv_dpp = findViewById(R.id.tv_dpp);
        tv_semana = findViewById(R.id.tv_semana);



    }


}