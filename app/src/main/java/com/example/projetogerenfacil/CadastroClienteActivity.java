package com.example.projetogerenfacil;

import static com.example.projetogerenfacil.StorageUtils.getClientesFromStorage;
import static com.example.projetogerenfacil.StorageUtils.getProdutosFromStorage;

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

public class CadastroClienteActivity extends AppCompatActivity {
    private static final String TAG = "CadastroClienteActivity";

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    EditText etClienteFullName, etClienteCPF_CNPJ, etClienteEndereco, etClienteComplemento;
    Button btnClienteRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_cliente);

        etClienteFullName = findViewById(R.id.etFullName);
        etClienteCPF_CNPJ = findViewById(R.id.etCPF_CNPJ);
        etClienteEndereco = findViewById(R.id.etAddress);
        etClienteComplemento = findViewById(R.id.etComplement);
        btnClienteRegister = findViewById(R.id.btnRegister);

        btnClienteRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Cadastrar cliente: botão de registro clicado");

                String enteredName = etClienteFullName.getText().toString();
                String enteredCPF_CNPJ = etClienteCPF_CNPJ.getText().toString();
                String enteredEndereco = etClienteEndereco.getText().toString();
                String enteredComplemento = etClienteComplemento.getText().toString();

                if (enteredName.isEmpty() || enteredCPF_CNPJ.isEmpty() || enteredEndereco.isEmpty() || enteredComplemento.isEmpty()) {
                    showToast("Preencha todos os campos.");
                } else if (enteredName.length() > 50) {
                    showToast("Nome excede o limite de caracteres.");
                } else if (enteredCPF_CNPJ.length() != 11 && enteredCPF_CNPJ.length() != 14) {
                    showToast("CPF/CNPJ inválido.");
                } else if (enteredEndereco.length() > 50) {
                    showToast("Endereço excede o limite de caracteres.");
                } else if (enteredComplemento.length() > 50) {
                    showToast("Complemento excede o limite de caracteres.");
                } else {
                    // Se os dados forem validados, crie um arquivo com o cadastro do cliente
                    List<Cliente> clientes = getClientesFromStorage(getApplicationContext());
                    Cliente cliente = new Cliente(enteredName, enteredCPF_CNPJ, enteredEndereco, enteredComplemento);
                    clientes.add(cliente);

                    SharedPreferences pref = getSharedPreferences("cliente_", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    Gson gson = new Gson();
                    String jsonStringClientes = gson.toJson(clientes);

                    editor.putString("lista_clientes", jsonStringClientes);
                    editor.apply();

                    Log.d(TAG, "Cliente cadastrado com sucesso: " + enteredName);

                    showToast("Cadastro salvo com sucesso.");

                    // Redirecionar para a tela de navegação após o cadastro bem-sucedido
                    Intent intent = new Intent(CadastroClienteActivity.this, InterfaceNavegacaoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
