package com.example.projetogerenfacil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InterfaceNavegacaoActivity extends AppCompatActivity {
    private static final String TAG = "NavActivity";

    TextView tvFullName, tvEmail;
    Button btnOpcaoUsuarios, btnOpcaoClientes, btnOpcaoProdutos, btnOpcaoSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_navegacao);

        btnOpcaoUsuarios = findViewById(R.id.btnOpcaoUsuarios);
        btnOpcaoClientes = findViewById(R.id.btnOpcaoClientes);
        btnOpcaoProdutos = findViewById(R.id.btnOpcaoProdutos);
        btnOpcaoSair = findViewById(R.id.btnOpcaoSair);

        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);

        String nome = getIntent().getStringExtra("Nome");
        String email = getIntent().getStringExtra("Email");

        tvFullName.setText(nome);
        tvEmail.setText(email);

        btnOpcaoClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Botão 'CLIENTE' clicado");

                // Navegar para a tela de lista de clientes (ListaClienteActivity)
                Intent intent = new Intent(InterfaceNavegacaoActivity.this, ListaClienteActivity.class);
                startActivity(intent);
            }
        });

        btnOpcaoProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Botão 'Produtos' clicado");

                // Navegar para a tela de lista de produtos (ListaProdutoActivity)
                Intent intent = new Intent(InterfaceNavegacaoActivity.this, ListaProdutoActivity.class);
                startActivity(intent);
            }
        });

        btnOpcaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Botão 'SAIR' clicado");

                // Retorna para a página de Login
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
