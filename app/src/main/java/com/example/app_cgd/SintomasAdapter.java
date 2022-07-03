package com.example.app_cgd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_cgd.DTO.Sintomas;

import java.util.ArrayList;

public class SintomasAdapter extends RecyclerView.Adapter<SintomasAdapter.MyViewHolder> {

    private ArrayList<Sintomas> lSintomas;
    private Context context;

    public ArrayList<Sintomas> getlSintomas() {
        return lSintomas;
    }

    public SintomasAdapter(Context context, ArrayList<Sintomas> lSintomas){
        this.lSintomas = lSintomas;
        this.context = context;
    }


    //metodo para chamar a lista em uma tela
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sintomas_lista, parent, false);
        return new MyViewHolder(v);
    }


    //onde vou pegar e jogar na tela ,onde se manipula a lista
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Sintomas s = lSintomas.get(position);
        holder.tv_humor.setText(s.getHumor());
        holder.tv_sintomas.setText(s.getSintomas());
        holder.tv_outros.setText(s.getOutros());


    }


    // methodo onde se add o tamanho da lista
    @Override
    public int getItemCount() {
        return lSintomas.size(); // desse modo fica mais facil por estar pegando o tamanho q vc ja criou
    }


    public void notifyItemRemoved(String id_s) {
        return;
    }


    //como se fosse o iniciar componetes
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_humor, tv_sintomas, tv_outros;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_humor = itemView.findViewById(R.id.id_humor);
            tv_sintomas = itemView.findViewById(R.id.id_sintomas);
            tv_outros = itemView.findViewById(R.id.id_outros);
        }
    }
}
