package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import api.ApiClient;
import api.service.UserService;
import model.Login;
import model.User;
import model.UserLogin;
import repository.LoginRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText etPseudo;
    private EditText etPassword;
    private Button btnLogin;
    private LoginRepository loginRepository = new LoginRepository(LoginActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("User", MODE_APPEND);
        String pseudo = sh.getString("pseudo", "");
        if(!pseudo.equals("")){
            // on va sur le main menu
            Intent i = new Intent(this, MainMenu.class);
            startActivity(i);
        }
    }

    private void initView() {
        etPseudo = findViewById(R.id.et_pseudo);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

    }

    public void login(View view) {
        final String pseudoValue = etPseudo.getText().toString();
        final String passwordValue = etPassword.getText().toString();
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("User", MODE_APPEND);
        String pseudo = sh.getString("pseudo", "");
        if(!pseudo.equals("")){
            // on va sur le main menu
            Intent i = new Intent(this, MainMenu.class);
            startActivity(i);
            return;
        }
        if (pseudoValue.isEmpty() || passwordValue.isEmpty()){
            Log.e("login","MDP ou Pseudo vide");
            return;
        }

        final String passwordValueHash = hashMD5(passwordValue);

        Login login = new Login(pseudoValue, passwordValueHash);


        loginRepository.login(login);

        pseudo = sh.getString("pseudo", "");
        if(pseudo.equals("")){
            return;
        }

        // on va sur le main menu
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);

    }

    public String hashMD5(String s) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;

    }
}