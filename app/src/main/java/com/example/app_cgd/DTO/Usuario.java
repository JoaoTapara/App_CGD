package com.example.app_cgd.DTO;

public class Usuario {

    String id_gestante, email, senha, nome, telefone_gestante, cpf, resposta;

    public String getId_gestante() {
        return id_gestante;
    }

    public void setId_gestante(String id_gestante) {
        this.id_gestante = id_gestante;
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
}
