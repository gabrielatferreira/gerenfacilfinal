package com.example.projetogerenfacil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    EditText etFullName, etCPF_CNPJ, etEmail, etPassword, etConfirmPassword, etPosition, etAccessKey;
    Button btnRegister;
    TextView tvLogin;

    public static boolean validarSenha(String senha) {
        // Verificar o comprimento mínimo e máximo
        if (senha.length() < 6 || senha.length() > 30) {
            return false;
        }

        // Verificar se há pelo menos uma letra maiúscula, uma minúscula e um numeral
        boolean possuiMaiuscula = false;
        boolean possuiMinuscula = false;
        boolean possuiNumero = false;

        for (char c : senha.toCharArray()) {
            if (Character.isUpperCase(c)) {
                possuiMaiuscula = true;
            } else if (Character.isLowerCase(c)) {
                possuiMinuscula = true;
            } else if (Character.isDigit(c)) {
                possuiNumero = true;
            }
        }

        return possuiMaiuscula && possuiMinuscula && possuiNumero;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario);

        etFullName = findViewById(R.id.etFullName);
        etCPF_CNPJ = findViewById(R.id.etCPF_CNPJ);
        etPosition = findViewById(R.id.etPosition);
        etAccessKey = findViewById(R.id.etAccessKey);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredName = etFullName.getText().toString();
                String enteredCPF_CNPJ = etCPF_CNPJ.getText().toString();
                String enteredCargo = etPosition.getText().toString();
                String enteredKey = etAccessKey.getText().toString();
                String enteredEmail = etEmail.getText().toString();
                String enteredPass1 = etPassword.getText().toString();
                String enteredPass2 = etConfirmPassword.getText().toString();

                if(enteredName.isEmpty() || enteredCPF_CNPJ.isEmpty() || enteredCargo.isEmpty() || enteredKey.isEmpty() || enteredEmail.isEmpty() || enteredPass1.isEmpty() || enteredPass2.isEmpty()) {
                    showToast("msg_campos_invalidos");
                } else if (enteredName.length() > 30) {
                    showToast("msg_limite_excedido");
                } else if (enteredCPF_CNPJ.length() != 11 && enteredCPF_CNPJ.length() != 14) {
                    showToast("msg_campos_invalidos");
                } else if (!enteredEmail.contains("@") || enteredEmail.length() > 50) {
                    showToast("msg_email_invalidos");
                } else if (!validarSenha(enteredPass1)) {
                    showToast("msg_senha_invalida");
                } else if (!enteredPass1.equals(enteredPass2)) {
                    showToast("msg_senhas_diferentes");
                } else {
                    // se os dados forem validados, cria arquivo com cadastro do usuário

                    // Cria arquivo utilizando o CPF/CNPJ como nome do arquivo
                    SharedPreferences pref;
                    pref = getSharedPreferences("user" + enteredCPF_CNPJ, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("chNome", enteredName);
                    editor.putString("chCPF_CNPJ", enteredCPF_CNPJ);
                    editor.putString("chCargo", enteredCargo);
                    editor.putString("chAcesso", enteredKey);
                    editor.putString("chEmail", enteredEmail);
                    editor.putString("chPass", enteredPass1);
                    editor.commit();

                    showToast("msg_salvo");

                    // Redirecione para a tela de login (MainActivity) quando o cadastro for concluído
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirecione para a tela de login (MainActivity) quando "Faça login" for clicado
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
