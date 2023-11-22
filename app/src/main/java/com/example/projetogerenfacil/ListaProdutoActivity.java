package com.example.projetogerenfacil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutoActivity extends AppCompatActivity {
    private List<Produto> listaDeProdutos;
    private RecyclerView recyclerView;
    private ProdutoAdapter produtoAdapter;
    Button btnAdicionarProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_produto);

        listaDeProdutos = new ArrayList<>();
        listaDeProdutos.addAll(getProdutosFromStorage());

        recyclerView = findViewById(R.id.recyclerProdutos);
        produtoAdapter = new ProdutoAdapter(listaDeProdutos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(produtoAdapter);

        btnAdicionarProduto = findViewById(R.id.fabAdicionarProduto);

        btnAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaProdutoActivity.this, CadastroProdutoActivity.class);
                startActivityForResult(intent, 1);
                Log.d("ListaProdutoActivity", "Botão de adicionar clicado");
            }
        });
    }

    @NonNull
    private List<Produto> getProdutosFromStorage() {
        List<Produto> produtos = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("produto_", MODE_PRIVATE);

        if (preferences.contains("nome")) {
            String nome = preferences.getString("nome", "");
            String categoria = preferences.getString("categoria", "");
            double preco = Double.parseDouble(preferences.getString("preco", "0.0"));
            int estoque = Integer.parseInt(preferences.getString("estoque", "0"));
            String promocoes = preferences.getString("promocoes", "");
            String descricao = preferences.getString("descricao", "");

            Produto produto = new Produto(nome, categoria, preco, estoque, promocoes, descricao);
            produtos.add(produto);
            Log.d("ListaProdutoActivity", "Produto recuperado: " + nome);
        }

        return produtos;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nome = data.getStringExtra("nome");
            String categoria = data.getStringExtra("categoria");
            double preco = Double.parseDouble(data.getStringExtra("preco"));
            int estoque = Integer.parseInt(data.getStringExtra("estoque"));
            String promocoes = data.getStringExtra("promocoes");
            String descricao = data.getStringExtra("descricao");

            Produto novoProduto = new Produto(nome, categoria, preco, estoque, promocoes, descricao);
            listaDeProdutos.add(novoProduto);
            produtoAdapter.notifyDataSetChanged();
            Log.d("ListaProdutoActivity", "Novo produto adicionado: " + nome);

            salvarProdutoNoArmazenamento(novoProduto);
        }
    }

    private void salvarProdutoNoArmazenamento(Produto produto) {
        SharedPreferences preferences = getSharedPreferences("produto_77788899900", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome", produto.getNome());
        editor.putString("categoria", produto.getCategoria());
        editor.putString("preco", String.valueOf(produto.getPreco()));
        editor.putString("estoque", String.valueOf(produto.getEstoque()));
        editor.putString("promocoes", produto.getPromocoes());
        editor.putString("descricao", produto.getDescricao());
        editor.apply();

        Log.d("SalvarProduto", "Produto salvo com sucesso:");
        Log.d("SalvarProduto", "Nome: " + produto.getNome());
        Log.d("SalvarProduto", "Categoria: " + produto.getCategoria());
        Log.d("SalvarProduto", "Preço: " + produto.getPreco());
        Log.d("SalvarProduto", "Estoque: " + produto.getEstoque());
        Log.d("SalvarProduto", "Promoções: " + produto.getPromocoes());
        Log.d("SalvarProduto", "Descrição: " + produto.getDescricao());
    }
}
