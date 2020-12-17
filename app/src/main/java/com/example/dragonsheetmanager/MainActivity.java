package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

    private String user = "Louis"; //Temporairement fixée (sera établie lors de la liaison avec un compte utilisateur)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
        initViews();
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

        loadFromdDB(user);



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