package com.example.projetogerenfacil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        listaDeClientes = StorageUtils.getClientesFromStorage(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerUsuarios);
        clienteAdapter = new ClienteAdapter(listaDeClientes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clienteAdapter);

        btnAdicionar = findViewById(R.id.fabAdicionar);

        btnAdicionar.setOnClickListener(view -> {
            Intent intent = new Intent(ListaClienteActivity.this, CadastroClienteActivity.class);
            startActivityForResult(intent, 1);
            Log.d("ListaClienteActivity", "Botão de adicionar clicado");
        });
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

