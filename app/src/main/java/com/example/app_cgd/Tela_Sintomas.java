package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.app_cgd.DTO.Sintomas;
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

    String userat;
    String id_s;
    DatabaseReference position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaSintomasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        IniciarComponentes();

        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
        String usuarioAtual = userId.getUid();

        userat = usuarioAtual;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Sintomas>();
        adapter = new SintomasAdapter(this, list);
        recyclerView.setAdapter(adapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        databaseReference.child(usuarioAtual).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Sintomas s = dataSnapshot.getValue(Sintomas.class);
                    list.add(s);

                    id_s = dataSnapshot.getKey();
//                    position = dataSnapshot.getRef(position);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Tela_Sintomas.this);
            builder.setTitle("Deletar");
            builder.setMessage("Deseja excluir este sintoma?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    startActivity(new Intent(getApplicationContext(), Tela_Sintomas.class));

                    FirebaseDatabase.getInstance().getReference().child("sintomas").child(userat).child(id_s).removeValue();



//                    int position = viewHolder.getAdapterPosition();
//                    adapter.notifyItemRemoved(id_s);
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());

                }
            });
            builder.show();

        }
    };

    private void IniciarComponentes(){
        recyclerView = findViewById(R.id.recyclerView);
    }
}