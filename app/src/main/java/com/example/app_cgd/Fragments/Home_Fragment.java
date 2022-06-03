package com.example.app_cgd.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.google.firebase.firestore.DocumentReference;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@RequiresApi(api = Build.VERSION_CODES.O)
public class Home_Fragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String usuarioID;
    private String data_dum;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tv_dia, tv_mes, tv_ano;

    private Usuario u;
    private String mParam1;
    private String mParam2;



    int data =Integer.parseInt(Sistemas.data_dum);
    int [] dum_cal = new int[data];

    int dia1 = dum_cal[0];
    int dia2 = dum_cal[1];
    int mes1 = dum_cal[3];
    int mes2 = dum_cal[4];
    int ano1 = dum_cal[6];
    int ano2 = dum_cal[7];
    int ano3 = dum_cal[8];
    int ano4 = dum_cal[9];

    String conv_dia = String.valueOf(dia1 +  dia2);
    String conv_mes = String.valueOf(mes1 +  mes2);
    String conv_ano = String.valueOf(ano1 +  ano2 + ano3 + ano4);




//    LocalDateTime dataCadastro = LocalDateTime.of(ano, mes, dia, hora, minuto, segundos);
//    LocalDateTime hoje = LocalDateTime.now();
//    long dias = dataCadastro.until(hoje, ChronoUnit.DAYS);

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

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference reference = database.getReference().child("usuarios").child(usuarioID);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String dum = snapshot.child("dum").getValue(String.class);

                Sistemas.data_dum = dum;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tv_dia = getView().findViewById(R.id.tv_dia);
        tv_dia.setText(conv_dia);

        tv_mes = getView().findViewById(R.id.tv_mes);
        tv_mes.setText(conv_mes);

        tv_ano = getView().findViewById(R.id.tv_ano);
        tv_ano.setText(conv_ano);

        return inflater.inflate(R.layout.fragment_home_, container, false);
    }
}