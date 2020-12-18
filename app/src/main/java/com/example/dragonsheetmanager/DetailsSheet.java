package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import model.Fiche;

public class DetailsSheet extends AppCompatActivity {

    private Fiche fiche;

    private Button btnAllies;
    private Button btnAge;
    private Button btnApparence;
    private Button btnBackstory;
    private Button btnCapaciteEtTrait;
    private Button btnDefaut;
    private Button btnEquipement;
    private Button btnHistorique;
    private Button btnIdeaux;
    private Button btnLiens;
    private Button btnNote;
    private Button btnTraitPerso;
    private Button btnSorts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_sheet);
        //Récupération de l'intent:
        Intent i = getIntent();
        //Dans un cadre hors-test passer par values.string !!
        fiche = (Fiche)i.getSerializableExtra("ficheObject");

        initView();

    }

    private void initView() {
        btnAllies = (Button) findViewById(R.id.btn_allies);
        btnAge = (Button) findViewById(R.id.btn_age);
        btnApparence = (Button) findViewById(R.id.btn_apparence);
        btnBackstory = (Button) findViewById(R.id.btn_Backstory);
        btnCapaciteEtTrait = (Button) findViewById(R.id.btn_capacite_et_traits);
        btnDefaut = (Button) findViewById(R.id.btn_defaut);
        btnEquipement = (Button) findViewById(R.id.btn_equipement);
        btnHistorique = (Button) findViewById(R.id.btn_historique);
        btnIdeaux = (Button) findViewById(R.id.btn_ideaux);
        btnLiens = (Button) findViewById(R.id.btn_liens);
        btnNote = (Button) findViewById(R.id.btn_note);
        btnTraitPerso = (Button) findViewById(R.id.btn_traitsPerso);
        btnSorts = (Button) findViewById(R.id.btn_sorts);
    }
}