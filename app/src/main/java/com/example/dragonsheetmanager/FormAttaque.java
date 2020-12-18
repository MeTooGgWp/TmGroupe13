package com.example.dragonsheetmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import model.Attaque;

import static com.example.dragonsheetmanager.BasicSheet.AUTO_COMPLETE;

public class FormAttaque extends AppCompatActivity {

    public static final String ATT_VALUE ="ATT_VALUE";

    private EditText etAttaqueName,etDamageThrow,etDamageType,etDamageBonus,etDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attaque_form);
        loadViews();
    }

    private void loadViews() {
        etAttaqueName = (EditText) findViewById(R.id.et_attackName);
        etDamageThrow = (EditText) findViewById(R.id.et_bonusJet);
        etDamageThrow.setInputType(InputType.TYPE_CLASS_NUMBER);
        etDamageType = (EditText) findViewById(R.id.et_typeDegat);
        etDamageBonus = (EditText) findViewById(R.id.et_bonusDegat);
        etDamageBonus.setInputType(InputType.TYPE_CLASS_NUMBER);
        etDice = (EditText) findViewById(R.id.et_diceType);
        etDice.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public void submitAttaque(View view) {
        Attaque newAttaque = new Attaque(
                etAttaqueName.getText().toString(),
                Integer.parseInt(etDamageBonus.getText().toString()),
                etDamageType.getText().toString(),
                Integer.parseInt(etDamageThrow.getText().toString()),
                Integer.parseInt(etDice.getText().toString())
        );

        //Envoi de la nouvelle attaque :
        Intent intent = new Intent();
        intent.putExtra(ATT_VALUE,newAttaque);
        //Envoi de l'intention:
        setResult(RESULT_OK, intent);
        finish();
    }
}
