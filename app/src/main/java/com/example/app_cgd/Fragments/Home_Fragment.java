package com.example.app_cgd.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_cgd.DTO.Sistemas;
import com.example.app_cgd.DTO.Usuario;
import com.example.app_cgd.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@RequiresApi(api = Build.VERSION_CODES.O)
public class Home_Fragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String usuarioID;
    private int dia_dum, mes_dum, ano_dum;
    private String semanas_ex;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tv_semana, tv_dpp;

    private Usuario u;
    private Sistemas s;
    private String mParam1;
    private String mParam2;

    public Home_Fragment() {

    }

    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        semana();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = database.getReference().child("usuarios").child(usuarioID);

        tv_semana = getView().findViewById(R.id.tv_semana);
        tv_dpp = getView().findViewById(R.id.tv_dpp);

        String dia_dum = String.valueOf(reference.child("dia_dum"));

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String semana_final = Sistemas.semanas_ex;

                String dpp_final = Usuario.dpp;

                tv_semana.setText(semana_final);
                tv_dpp.setText(dpp_final);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_, container, false);
    }


    public void semana() {

        u = new Usuario();
        int ano = u.getAno_dum();
        int mes = u.getMes_dum();
        int dia = u.getDia_dum();

        LocalDateTime dataCadastro = LocalDateTime.of(ano, mes, dia, 0, 0, 0);
        LocalDateTime hoje = LocalDateTime.now();
        long dias = dataCadastro.until(hoje, ChronoUnit.DAYS);

        String semana_q = String.valueOf(dias / 7);

        Sistemas.semanas_ex = semana_q;

    }
}