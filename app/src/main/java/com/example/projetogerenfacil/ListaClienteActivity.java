package com.example.projetogerenfacil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaClienteActivity extends AppCompatActivity {
    private List<Cliente> listaDeClientes;
    private RecyclerView recyclerView;
    private ClienteAdapter clienteAdapter;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_clientes);

        listaDeClientes = new ArrayList<>();
        listaDeClientes = getClientesFromStorage();

        recyclerView = findViewById(R.id.recyclerUsuarios);
        clienteAdapter = new ClienteAdapter(listaDeClientes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clienteAdapter);

        btnAdicionar = findViewById(R.id.fabAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaClienteActivity.this, CadastroClienteActivity.class);
                startActivityForResult(intent, 1);
                Log.d("ListaClienteActivity", "Botão de adicionar clicado");
            }
        });
    }

    @NonNull
    private List<Cliente> getClientesFromStorage() {
        List<Cliente> clientes = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("cliente_77788899900", MODE_PRIVATE);

        // Verifique se o arquivo XML contém os dados do cliente
        if (preferences.contains("nome")) {
            String nome = preferences.getString("nome", "");
            String cpf = preferences.getString("cpf_cnpj", "");
            String endereco = preferences.getString("endereco", "");
            String complemento = preferences.getString("complemento", "");

            // Crie o cliente e adicione à lista
            Cliente cliente = new Cliente(nome, cpf, endereco, complemento);
            clientes.add(cliente);
            Log.d("ListaClienteActivity", "Cliente recuperado: " + nome);
        }

        return clientes;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nome = data.getStringExtra("nome");
            String cpf = data.getStringExtra("cpf_cnpj");
            String endereco = data.getStringExtra("endereco");
            String complemento = data.getStringExtra("complemento");

            Cliente novoCliente = new Cliente(nome, cpf, endereco, complemento);
            listaDeClientes.add(novoCliente);
            clienteAdapter.notifyDataSetChanged();
            Log.d("ListaClienteActivity", "Novo cliente adicionado: " + nome);

            salvarClienteNoArmazenamento(novoCliente);
        }
    }

    private void salvarClienteNoArmazenamento(Cliente cliente) {
        SharedPreferences preferences = getSharedPreferences("cliente_77788899900", MODE_PRIVATE);

        // Salve os detalhes do cliente no SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome", cliente.getNome());
        editor.putString("cpf_cnpj", cliente.getCpf());
        editor.putString("endereco", cliente.getEndereco());
        editor.putString("complemento", cliente.getComplemento());
        editor.apply();

        // Adicione logs para verificar o processo
        Log.d("SalvarCliente", "Cliente salvo com sucesso:");
        Log.d("SalvarCliente", "Nome: " + cliente.getNome());
        Log.d("SalvarCliente", "CPF/CNPJ: " + cliente.getCpf());
        Log.d("SalvarCliente", "Endereço: " + cliente.getEndereco());
        Log.d("SalvarCliente", "Complemento: " + cliente.getComplemento());
    }
}

