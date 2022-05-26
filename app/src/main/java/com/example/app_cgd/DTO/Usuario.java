package com.example.app_cgd.DTO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Usuario {

    public static String id, email, senha, senha_conf, nome, telefone, cpf, resposta, dum, dpp, idade, peso;

    public Usuario() {
    }

    public Usuario(String id, String email, String senha, String senha_conf, String nome, String telefone, String cpf, String resposta, String dum, String dpp,String idade, String peso){
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
    }

    public static String getDpp() {
        return dpp;
    }

    public static void setDpp(String dpp) {
        Usuario.dpp = dpp;
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
