package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Fiche;
import repository.FicheRepository;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM_FICHE = 1;
    private List<Fiche> fiches = new ArrayList<>();
    private ListView lvFiches;
    private FicheArrayAdapter arrayAdapter;

    private String user ;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUser();
        loadViews();
        initViews();

        loadFromdDB(user);
    }

    private void initUser() {
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("User", MODE_APPEND);
        user = sh.getString("pseudo", "hui");
        token = sh.getString("token","");
        Toast.makeText(this,user,Toast.LENGTH_LONG).show();

    }

    public void loadViews(){
        lvFiches = findViewById(R.id.lv_fiches);
    }

    public void initViews(){
        arrayAdapter = new FicheArrayAdapter(this,
                R.id.lv_fiches,
                fiches);
        lvFiches.setAdapter(arrayAdapter);
        lvFiches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Fiche fiche = fiches.get(position);
                startFicheA(view,fiche);
            }
        });





    }


    public void loadFromdDB(String user){
        //Chargement des fiches à partir de la bd
        FicheRepository ficheRepository = new FicheRepository();
        ficheRepository.query(user).observe(this, new Observer<List<Fiche>>() {
            @Override
            public void onChanged(List<Fiche> fichesBd) {
                fiches.clear();
                fiches.addAll(fichesBd);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }


    public void startFicheA(View view,Fiche f) {
        //Fiche temporaire créée pour les test
       // Fiche f = new Fiche("louis"); //Changer le constructeur de la classe fiche
        //*************************************

       //Création d'un Intent pour passer la fiche dans une activité
        Intent i = new Intent(this, BasicSheet.class);
        //Transfert de la fiche dans l'intent
        i.putExtra("ficheObject",f);
        startActivity(i);
    }

    public void refresh(View view) {
        loadFromdDB(user);
    }
}