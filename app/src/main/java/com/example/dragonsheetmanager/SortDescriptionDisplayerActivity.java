package com.example.dragonsheetmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SortDescriptionDisplayerActivity extends AppCompatActivity {

    TextView tvDescription;
    String description ="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_description_activity);
        Intent i = getIntent();
        tvDescription = findViewById(R.id.tv_sortDesc);
        description = i.getStringExtra("Description");
        if(description == null)
            description = "Erreur lors du chargement de la description\nVeillez excuser le désagrément!";
        tvDescription.setText(description);
    }
}
