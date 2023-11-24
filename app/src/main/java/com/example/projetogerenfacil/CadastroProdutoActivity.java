package com.example.projetogerenfacil;

import static com.example.projetogerenfacil.StorageUtils.getProdutosFromStorage;
import static java.util.Objects.isNull;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;

public class CadastroProdutoActivity extends AppCompatActivity {
    private static final String TAG = "CadastroProdutoActivity";

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    EditText etProductName, etCategory, etPrice, etStock, etPromotions, etDescription;
    Button btnProductRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produto);

        etProductName = findViewById(R.id.etFullName);
        etCategory = findViewById(R.id.etCPF_CNPJ);
        etPrice = findViewById(R.id.etAddress);
        etStock = findViewById(R.id.etComplement);
        etPromotions = findViewById(R.id.etProduct);
        etDescription = findViewById(R.id.etCategory);
        btnProductRegister = findViewById(R.id.btnRegister);

        btnProductRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Cadastrar produto: botão de registro clicado");

                String enteredName = etProductName.getText().toString();
                String enteredCategory = etCategory.getText().toString();
                Double enteredPrice = Double.valueOf(etPrice.getText().toString());
                Integer enteredStock = Integer.valueOf(etStock.getText().toString());
                String enteredPromotions = etPromotions.getText().toString();
                String enteredDescription = etDescription.getText().toString();

                if (enteredName.isEmpty() || enteredCategory.isEmpty() || enteredPrice.isNaN() || isNull(enteredStock) || enteredPromotions.isEmpty() || enteredDescription.isEmpty()) {
                    showToast("Preencha todos os campos.");
                } else {
                    // Se os dados forem validados, crie um arquivo com o cadastro do produto

                    List<Produto> produtos = getProdutosFromStorage(getApplicationContext());
                    Produto novoProduto = new Produto(enteredName, enteredCategory, enteredPrice, enteredStock, enteredPromotions, enteredDescription);
                    produtos.add(novoProduto);
                    // Crie um arquivo utilizando o nome do produto como nome do arquivo
                    SharedPreferences pref = getSharedPreferences("produto_", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    Gson gson = new Gson();
                    String jsonStringProdutos = gson.toJson(produtos);

                    editor.putString("lista_produtos", jsonStringProdutos);
                    editor.apply();

                    Log.d(TAG, "Produto cadastrado com sucesso: " + enteredName);

                    showToast("Cadastro de produto salvo com sucesso.");

                    // Redirecionar para a tela de navegação após o cadastro bem-sucedido
                    Intent intent = new Intent(CadastroProdutoActivity.this, InterfaceNavegacaoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
