package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

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

       FicheRepository ficheRepository = new FicheRepository();
       ficheRepository.query().observe(this, new Observer<List<Fiche>>() {
            @Override
            public void onChanged(List<Fiche> fiches) {
                Log.i("Fiche", fiches.toString());
            }
        });




    }

}