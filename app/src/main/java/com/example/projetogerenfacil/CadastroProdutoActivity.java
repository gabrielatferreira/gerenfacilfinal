package com.example.projetogerenfacil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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
                String enteredPrice = etPrice.getText().toString();
                String enteredStock = etStock.getText().toString();
                String enteredPromotions = etPromotions.getText().toString();
                String enteredDescription = etDescription.getText().toString();

                if (enteredName.isEmpty() || enteredCategory.isEmpty() || enteredPrice.isEmpty() || enteredStock.isEmpty() || enteredPromotions.isEmpty() || enteredDescription.isEmpty()) {
                    showToast("Preencha todos os campos.");
                } else {
                    // Se os dados forem validados, crie um arquivo com o cadastro do produto

                    // Crie um arquivo utilizando o nome do produto como nome do arquivo
                    SharedPreferences pref;
                    pref = getSharedPreferences("produto_" + enteredName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("nome", enteredName);
                    editor.putString("categoria", enteredCategory);
                    editor.putString("preco", enteredPrice);
                    editor.putString("estoque", enteredStock);
                    editor.putString("promocoes", enteredPromotions);
                    editor.putString("descricao", enteredDescription);
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
