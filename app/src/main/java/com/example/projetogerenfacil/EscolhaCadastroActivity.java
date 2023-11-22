package com.example.projetogerenfacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EscolhaCadastroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_cadastro);



        Button botaoCadastroAdmin = findViewById(R.id.btnCadastroAdmin);
        botaoCadastroAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent voltarMain = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(voltarMain);

                Intent register = new Intent(getApplicationContext(), CadastroAdministradorActivity.class);
                startActivity(register);
            }
        });

        Button botaoCadastroUsuario = findViewById(R.id.btnCadastroUsuario);
        botaoCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent voltarMain = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(voltarMain);

                Intent register = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
                startActivity(register);
            }
        });
    }
}
