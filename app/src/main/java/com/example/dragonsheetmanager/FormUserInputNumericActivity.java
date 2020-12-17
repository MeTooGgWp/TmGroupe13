package com.example.dragonsheetmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormUserInputNumericActivity extends AppCompatActivity {

    public static final String NUM_VALUE = "NUM_VALUE";
    public static final String STR_VALUE = "STR_VALUE";
    //Butterknife
    private EditText etUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input);
        loadViews();
    }

    private void loadViews() {
        etUserInput = findViewById(R.id.et_userInputForSheet);
        etUserInput.setInputType(InputType.TYPE_CLASS_NUMBER);
    }


    public void submit(View view) {
        final String userInputText = etUserInput.getText().toString();
        try {
            //Conversion en nombre :
            float f = Float.parseFloat(userInputText);
            //Création de la réponse :
            Intent intent = new Intent();
            intent.putExtra(NUM_VALUE, f);
            intent.putExtra(STR_VALUE,"");
            //Envoi de l'intention:
            setResult(RESULT_OK, intent);
            finish();
        } catch (Exception e) {
            Log.i("error","Mauvaise conversion");
            String error = "Veillez entrez une valeur strictement numérique";
            Toast.makeText(this,error,Toast.LENGTH_LONG).show();
        }
    }
}
