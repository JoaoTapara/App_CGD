package com.example.app_cgd.DTO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Usuario {

    public static String id, email, senha, senha_conf, nome, telefone, cpf, resposta, dum, dpp, idade, peso;


    public static int dia_dum, mes_dum, ano_dum;

    public Usuario() {
    }

    public Usuario(String id, String email, String senha, String senha_conf, String nome, String telefone, String cpf, String resposta, String dum, String dpp,String idade, String peso, int dia_dum, int mes_dum, int ano_dum){
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.senha_conf = senha_conf;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.resposta = resposta;
        this.dum = dum;
        this.idade = idade;
        this.peso = peso;
        this.dpp = dpp;
        this.dia_dum = dia_dum;
        this.mes_dum = mes_dum;
        this.ano_dum = ano_dum;
    }

    public int getDia_dum() {
        return dia_dum;
    }

    public void setDia_dum(int dia_dum) {
        this.dia_dum = dia_dum;
    }

    public int getMes_dum() {
        return mes_dum;
    }

    public void setMes_dum(int mes_dum) {
        this.mes_dum = mes_dum;
    }

    public int getAno_dum() {
        return ano_dum;
    }

    public void setAno_dum(int ano_dum) {
        this.ano_dum = ano_dum;
    }

    public  String getDpp() {
        return dpp;
    }

    public  void setDpp(String dpp) {
        this.dpp = dpp;
    }

    public String getDum() {
        return dum;
    }

    public void setDum(String dum) {
        this.dum = dum;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id_gestante) {
        this.id = id_gestante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha_conf() {
        return senha_conf;
    }

    public void setSenha_conf(String senha_conf) {
        this.senha_conf = senha_conf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public void salvar(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("usuarios").child(getId()).setValue(this);

    }
}
