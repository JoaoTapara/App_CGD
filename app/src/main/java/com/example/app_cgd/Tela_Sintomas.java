package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.app_cgd.DTO.Sintomas;
import com.example.app_cgd.databinding.ActivityTelaCataoBinding;
import com.example.app_cgd.databinding.ActivityTelaSintomasBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tela_Sintomas extends DrawerBase {

    ActivityTelaSintomasBinding binding;
    RecyclerView recyclerView;
    ArrayList<Sintomas> list;
    SintomasAdapter adapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("sintomas");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaSintomasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        IniciarComponentes();
        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
        String usuarioAtual = userId.getUid();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Sintomas>();
        adapter = new SintomasAdapter(this, list);
        recyclerView.setAdapter(adapter);
        databaseReference.child(usuarioAtual).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Sintomas s = dataSnapshot.getValue(Sintomas.class);
                    list.add(s);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void IniciarComponentes(){
        recyclerView = findViewById(R.id.recyclerView);
    }
}