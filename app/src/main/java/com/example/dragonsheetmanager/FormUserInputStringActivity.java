package com.example.dragonsheetmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.dragonsheetmanager.BasicSheet.AUTO_COMPLETE;

public class FormUserInputStringActivity extends AppCompatActivity {

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
        Intent i = getIntent();
        String auto_complete = i.getStringExtra(AUTO_COMPLETE);
        etUserInput.setText(auto_complete);
    }


    public void submit(View view) {
        final String userInputText = etUserInput.getText().toString();
            //Création de la réponse :
            Intent intent = new Intent();
            intent.putExtra(STR_VALUE, userInputText);
            intent.putExtra(NUM_VALUE,0);
            //Envoi de l'intention:
            setResult(RESULT_OK, intent);
            finish();

    }



}
