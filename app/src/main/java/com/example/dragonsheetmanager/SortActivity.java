package com.example.dragonsheetmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import model.Fiche;
import model.Sort;

public class SortActivity extends AppCompatActivity {

    private Fiche fiche;

    private ListView lvSort;
    private SortArrayAdapter sortArrayAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_list);
        Intent i = getIntent();
        fiche = (Fiche)i.getSerializableExtra("ficheObject");
        onLoad();
        initList();
    }

    public void onLoad(){
        //Chargement des sorts dans la ListView
        lvSort = findViewById(R.id.lv_sort);

    }

    public void initList(){
        List<Sort> sorts = fiche.getSorts();
        sortArrayAdapter = new SortArrayAdapter(this,
                R.id.lv_sort,
                sorts);
        lvSort.setAdapter(sortArrayAdapter);
        lvSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Sort sort = sorts.get(position);
                Intent i = new Intent(SortActivity.this,SortDescriptionDisplayerActivity.class);
                i.putExtra("Description",sort.getDescriptionSort());
                startActivity(i);
            }
        });

    }


}
