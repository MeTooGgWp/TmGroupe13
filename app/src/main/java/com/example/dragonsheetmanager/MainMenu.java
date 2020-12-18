package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private Button btnfiches;
    private Button btnRejoindreGroup;
    private Button btnCreerGroupe;
    private Button btnGroupes;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initView();
    }

    private void initView() {
        btnfiches = findViewById(R.id.btn_fiches);
        btnRejoindreGroup = findViewById(R.id.btn_rejoindre_groupe);
        btnCreerGroupe = findViewById(R.id.btn_cree_groupe);
        btnGroupes = findViewById(R.id.btn_mes_groupe);
        btnLogout = findViewById(R.id.btn_deconnexion);
    }

    public void logout(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.remove("pseudo");
        myEdit.remove("token");

        myEdit.apply();

        this.finish();
    }

    public void getFiches(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}