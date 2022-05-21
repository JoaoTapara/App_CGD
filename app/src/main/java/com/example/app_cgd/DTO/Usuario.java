package com.example.app_cgd.DTO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Usuario {

    String id, email, senha, senha_conf, nome, telefone_gestante, cpf, resposta;

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

    public String getTelefone_gestante() {
        return telefone_gestante;
    }

    public void setTelefone_gestante(String telefone_gestante) {
        this.telefone_gestante = telefone_gestante;
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
