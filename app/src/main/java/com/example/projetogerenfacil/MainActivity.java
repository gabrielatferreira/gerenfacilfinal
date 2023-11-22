package com.example.projetogerenfacil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // Adicione uma tag de log

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.btnLogin);
        etUser = findViewById(R.id.etCPF_CNPJ);
        etPass = findViewById(R.id.etPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Botão de login clicado");

                String enteredUsername = etUser.getText().toString();
                String enteredPassword = etPass.getText().toString();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    // Campos não preenchidos
                    Log.d(TAG, "Campos de entrada não preenchidos.");
                    showToast("msg_campos_invalidos");
                } else {
                    Log.d(TAG, "Iniciando validação dos dados.");

                    String nome, email, user, pass;

                    SharedPreferences pref;
                    pref = getSharedPreferences("admin" + enteredUsername, MODE_PRIVATE);

                    nome = pref.getString("chNome", "");
                    email = pref.getString("chEmail", "");
                    user = pref.getString("chCPF_CNPJ", "");
                    pass = pref.getString("chPass", "");

                    if (!nome.isEmpty()) { // se nome não está vazio é porque encontrou cadastro de admin
                        if (enteredUsername.equals(user) && enteredPassword.equals(pass)) {
                            // Login OK
                            Log.d(TAG, "Autenticação bem-sucedida como admin.");
                            //InterfaceNavegacaoActivity.
                            //InterfaceNavegacaoActivity.tvEmail.setText(user);

                            Intent Intent = new Intent(MainActivity.this, InterfaceNavegacaoActivity.class);
                            Intent.putExtra("Nome", nome);
                            Intent.putExtra("Email", email);
                            startActivity(Intent);
                        } else {
                            // Autenticação falhou
                            Log.d(TAG, "Falha na autenticação.");
                            showToast("msg_falha_autenticacao");
                        }
                    } else { // se não encontrou cadastro de admin, testa se há cadastro de usuário
                        pref = getSharedPreferences("user" + enteredUsername, MODE_PRIVATE);

                        nome = pref.getString("chNome", "");
                        email = pref.getString("chEmail", "");
                        user = pref.getString("chCPF_CNPJ", "");
                        pass = pref.getString("chPass", "");

                        if (enteredUsername.equals(user) && enteredPassword.equals(pass)) {
                            // Login OK
                            Log.d(TAG, "Autenticação bem-sucedida como usuário.");
                            //InterfaceNavegacaoActivity.
                            //InterfaceNavegacaoActivity.tvEmail.setText(user);

                            Intent Intent = new Intent(MainActivity.this, InterfaceNavegacaoActivity.class);
                            Intent.putExtra("Nome", nome);
                            Intent.putExtra("Email", email);
                            startActivity(Intent);
                        } else {
                            // Autenticação falhou
                            Log.d(TAG, "Falha na autenticação.");
                            showToast("msg_falha_autenticacao");
                        }
                    }
                }
            }
        });

        TextView forgotPasswordTextView = findViewById(R.id.tvForgotPassword);
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para lidar com o clique no texto "Esqueceu a senha?"
                // Implemente a navegação para a tela de redefinição de senha, se necessário

                //Intent forgot = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                //startActivity(forgot);

                Log.d(TAG, "Clique em 'Esqueceu a senha?'");
                showToast("msg_email_enviado");
            }
        });

        TextView registerTextView = findViewById(R.id.tvRegister);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para lidar com o clique no texto "Cadastre-se"
                // Implemente a navegação para a tela de cadastro, se necessário

                Log.d(TAG, "Clique em 'Cadastre-se'");
                Intent register = new Intent(MainActivity.this, EscolhaCadastroActivity.class);
                startActivity(register);
            }
        });
    }
}
