package com.example.projetogerenfacil;
import android.util.Log;

public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private final String complemento;

    public Cliente(String nome, String cpf, String endereco, String complemento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.complemento = complemento;
        Log.d("Cliente", "Cliente construído com nome: " + nome);
    }

    public String getNome() {
        Log.d("Cliente", "getNome() chamado");
        return nome;
    }

    public String getCpf() {
        Log.d("Cliente", "getCpf() chamado");
        return cpf;
    }

    public String getEndereco() {
        Log.d("Cliente", "getEndereco() chamado");
        return endereco;
    }

    public String getComplemento() {
        Log.d("Cliente", "getComplemento() chamado");
        return complemento;
    }
}
