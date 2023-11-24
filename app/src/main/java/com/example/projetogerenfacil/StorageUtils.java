package com.example.projetogerenfacil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StorageUtils {

    private StorageUtils() {}
    @NonNull
    public static List<Produto> getProdutosFromStorage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("produto_", context.MODE_PRIVATE);

        List<Produto> produtos = new ArrayList<>();

        if (sharedPreferences.contains("lista_produtos")) {
            String produtosJsonString = sharedPreferences.getString("lista_produtos", "");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Produto>>(){}.getType();
            produtos = gson.fromJson(produtosJsonString, type);

            Log.d("ListaProdutoActivity", "Produtos recuperados, quantidade: " + produtos.size());
        }

        return produtos;
    }

    @NonNull
    public static List<Cliente> getClientesFromStorage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cliente_", context.MODE_PRIVATE);

        List<Cliente> clientes = new ArrayList<>();

        if (sharedPreferences.contains("lista_clientes")) {
            String clientesJsonString = sharedPreferences.getString("lista_clientes", "");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Cliente>>(){}.getType();
            clientes = gson.fromJson(clientesJsonString, type);

            Log.d("ListaProdutoActivity", "Clientes recuperados, quantidade: " + clientes.size());
        }

        return clientes;
    }
}
