package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Random;

import model.Fiche;
import repository.FicheRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startFicheA(View view) {
        //Fiche temporaire créée pour les test
        Fiche f = new Fiche("louis"); //Changer le constructeur de la classe fiche
        //*************************************

        //PLutot que de passer par sérialisation, peut être passer par Gson dans le futur

        //Création d'un Intent pour passer la fiche dans une activité
        Intent i = new Intent(this, BasicSheet.class);
        //Transfert de la fiche dans l'intent
        i.putExtra("ficheObject",f);


       startActivity(i);
    }
}