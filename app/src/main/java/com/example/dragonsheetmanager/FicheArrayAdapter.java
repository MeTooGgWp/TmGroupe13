package com.example.dragonsheetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.List;

import model.Fiche;

public class FicheArrayAdapter extends ArrayAdapter<Fiche> {

    public FicheArrayAdapter(@NonNull Context context, int ressource, @NonNull List<Fiche> objects){
        super(context,ressource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        View inflatedView = convertView;

        if(inflatedView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            inflatedView = layoutInflater.inflate(R.layout.list_item_fiche,parent,false);
        }

        final Fiche fiche = getItem(position);
        populateView(inflatedView,fiche);

        return inflatedView;
    }

    private void populateView(View inflatedView, Fiche fiche) {
        TextView tvCharacterName = inflatedView.findViewById(R.id.tv_characterNameList);
        TextView tvCharacterClass =inflatedView.findViewById(R.id.tv_classeList);
        TextView tvCharacterLevel = inflatedView.findViewById(R.id.tv_characterLevelList);

        tvCharacterName.setText(fiche.getBasicInfo().getNomPersonnage());
        tvCharacterClass.setText(fiche.getBasicInfo().getClasse());
        tvCharacterLevel.setText(String.valueOf(fiche.getBasicInfo().getNiveau()));

    }

}
