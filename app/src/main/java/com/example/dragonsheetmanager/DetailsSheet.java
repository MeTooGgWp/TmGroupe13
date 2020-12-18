package com.example.dragonsheetmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.Fiche;

import static com.example.dragonsheetmanager.BasicSheet.AUTO_COMPLETE;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_SORTS;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_AGE;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_ALLIES;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_APPARENCE;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_BACKSTORY;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_CAPACITE;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_DEFAUT;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_EQUIPEMENT;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_HISTORIQUE;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_IDEAUX;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_LIENS;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_NOTE;
import static com.example.dragonsheetmanager.RequestCodes.REQUEST_CODE_FORM_USERINPUT_TRAIT;

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

    private void startFormString(String extra,int requestCode,Intent intent){
        //Ajout d'un extra permettant d'auto-compléter l'editbox avec les élément déjà présent
        intent.putExtra(AUTO_COMPLETE,extra);
        startActivityForResult(intent,requestCode);
    }


    private void startFormNumeric(float extra,int requestCode,Intent intent){
        //Ajout d'un extra permettant d'auto-compléter l'editbox avec les élément déjà présent
        intent.putExtra(AUTO_COMPLETE,extra);
        startActivityForResult(intent,requestCode);
    }

    public void modifyStringValue(View view) {
        //On "active" le formulaire
        Intent intent = new Intent(DetailsSheet.this, FormUserInputStringActivity.class);
        //Gestion des différents boutons :
        switch(view.getId()){
            case(R.id.btn_allies):
                startFormString(fiche.getBackgroundAndTrait().getAllieEtOrganisation(),REQUEST_CODE_FORM_USERINPUT_ALLIES,intent);
                break;

            case(R.id.btn_age):
                startFormNumeric(fiche.getBackgroundAndTrait().getAge(), REQUEST_CODE_FORM_USERINPUT_AGE,intent);
                break;

            case(R.id.btn_apparence):
                startFormString(fiche.getBackgroundAndTrait().getApparence(), REQUEST_CODE_FORM_USERINPUT_APPARENCE,intent);
                break;

            case(R.id.btn_Backstory):
                startFormString(fiche.getBackgroundAndTrait().getBackground(), REQUEST_CODE_FORM_USERINPUT_BACKSTORY,intent);
                break;

            case(R.id.btn_capacite_et_traits):
                startFormString(fiche.getCapaciteEtTrait(), REQUEST_CODE_FORM_USERINPUT_CAPACITE,intent);
                break;

            case(R.id.btn_defaut):
                startFormString(fiche.getBackgroundAndTrait().getDefauts(), REQUEST_CODE_FORM_USERINPUT_DEFAUT,intent);
                break;

            case(R.id.btn_equipement):
                startFormString(fiche.getEquipement(), REQUEST_CODE_FORM_USERINPUT_EQUIPEMENT,intent);
                break;

            case(R.id.btn_historique):
                startFormString(fiche.getBackgroundAndTrait().getHistorique(), REQUEST_CODE_FORM_USERINPUT_HISTORIQUE,intent);
                break;

            case(R.id.btn_ideaux):
                startFormString(fiche.getBackgroundAndTrait().getIdeaux(), REQUEST_CODE_FORM_USERINPUT_IDEAUX,intent);
                break;

            case(R.id.btn_liens):
                startFormString(fiche.getBackgroundAndTrait().getLiens(), REQUEST_CODE_FORM_USERINPUT_LIENS,intent);
                break;

            case(R.id.btn_note):
                startFormString(fiche.getNote(), REQUEST_CODE_FORM_USERINPUT_NOTE,intent);
                break;

            case(R.id.btn_traitsPerso):
                startFormString(fiche.getBackgroundAndTrait().getTraitDePersonnalite(), REQUEST_CODE_FORM_USERINPUT_TRAIT,intent);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            final float reponseNum = data.getFloatExtra(FormUserInputNumericActivity.NUM_VALUE, (float) 0);
            Log.i("Reponse", "Retrieve userInput from FormUserInputActivity" + reponseNum);
            final String reponseStr = data.getStringExtra(FormUserInputStringActivity.STR_VALUE);
            Log.i("Reponse", "Retrieve userInput from FormUserInputActivity" + reponseStr);

            switch (requestCode) {
                case REQUEST_CODE_FORM_USERINPUT_ALLIES:
                    fiche.getBackgroundAndTrait().setAllieEtOrganisation(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_AGE:
                    fiche.getBackgroundAndTrait().setAge((int) reponseNum);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_APPARENCE:
                    fiche.getBackgroundAndTrait().setApparence(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_BACKSTORY:
                    fiche.getBackgroundAndTrait().setBackground(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_CAPACITE:
                    fiche.setCapaciteEtTrait(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_DEFAUT:
                    fiche.getBackgroundAndTrait().setDefauts(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_EQUIPEMENT:
                    fiche.setEquipement(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_HISTORIQUE:
                    fiche.getBackgroundAndTrait().setHistorique(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_IDEAUX:
                    fiche.getBackgroundAndTrait().setIdeaux(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_LIENS:
                    fiche.getBackgroundAndTrait().setLiens(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_NOTE:
                    fiche.setNote(reponseStr);
                    break;

                case REQUEST_CODE_FORM_USERINPUT_TRAIT:
                    fiche.getBackgroundAndTrait().setTraitDePersonnalite(reponseStr);
                    break;

                case REQUEST_CODE_FORM_SORTS:

                    break;
            }


        } else {
            Toast.makeText(this, "Les modifications n'ont pas été enregistrées", Toast.LENGTH_LONG).show();
        }

    }
}