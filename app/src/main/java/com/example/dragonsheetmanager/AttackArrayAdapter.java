package com.example.dragonsheetmanager;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import model.Attaque;

public class AttackArrayAdapter extends ArrayAdapter<Attaque> {

    public AttackArrayAdapter(@NonNull Context context, int ressource, @NonNull List<Attaque> objects){
        super(context,ressource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View inflatedView = convertView;

        if(inflatedView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            inflatedView = layoutInflater.inflate(R.layout.list_item_attaque,parent,false);
        }

        final Attaque attaque = getItem(position);
        populateView(inflatedView,attaque);

        return inflatedView;
    }

    private void populateView(View inflatedView, Attaque attaque) {

        TextView tvAttackName = inflatedView.findViewById(R.id.tv_attaqueName);
        TextView tvBonusAttack = inflatedView.findViewById(R.id.tv_attaque_bonus);
        TextView tvAttackType = inflatedView.findViewById(R.id.tv_attaque_type);
        Button btnDamage = inflatedView.findViewById(R.id.btn_damage);

        tvAttackName.setText(attaque.getNomAttaque());
        tvBonusAttack.setText(String.valueOf(attaque.getBonusAuDegat()));
        tvAttackType.setText(attaque.getTypeDegat());
        btnDamage.setText("1d"+String.valueOf(attaque.getDeDegat()));


    }
}
