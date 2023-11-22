package com.example.projetogerenfacil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroAdministradorActivity extends AppCompatActivity {

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    EditText etAdminFullName, etAdminCPF_CNPJ, etAdminEmail, etAdminPassword, etAdminConfirmPassword;
    Button btnAdminRegister;
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
        setContentView(R.layout.cadastro_administrador);

        etAdminFullName = findViewById(R.id.etAdminFullName);
        etAdminCPF_CNPJ = findViewById(R.id.etAdminCPF_CNPJ);
        etAdminEmail = findViewById(R.id.etAdminEmail);
        etAdminPassword = findViewById(R.id.etAdminPassword);
        etAdminConfirmPassword = findViewById(R.id.etAdminConfirmPassword);
        btnAdminRegister = findViewById(R.id.btnAdminRegister);
        tvLogin = findViewById(R.id.tvLogin);


        btnAdminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredName = etAdminFullName.getText().toString();
                String enteredCPF_CNPJ = etAdminCPF_CNPJ.getText().toString();
                String enteredEmail = etAdminEmail.getText().toString();
                String enteredPass1 = etAdminPassword.getText().toString();
                String enteredPass2 = etAdminConfirmPassword.getText().toString();

                if(enteredName.isEmpty() || enteredCPF_CNPJ.isEmpty() || enteredEmail.isEmpty() || enteredPass1.isEmpty() || enteredPass2.isEmpty()) {
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
                    pref = getSharedPreferences("admin" + enteredCPF_CNPJ, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("chNome", enteredName);
                    editor.putString("chCPF_CNPJ", enteredCPF_CNPJ);
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
