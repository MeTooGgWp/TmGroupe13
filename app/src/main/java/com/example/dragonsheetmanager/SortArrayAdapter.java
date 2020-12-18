package com.example.dragonsheetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import model.Sort;

public class SortArrayAdapter extends ArrayAdapter<Sort> {

    public SortArrayAdapter(@NonNull Context context, int ressource, @NonNull List<Sort> objects){
        super(context,ressource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View inflatedView = convertView;

        if(inflatedView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            inflatedView = layoutInflater.inflate(R.layout.list_item_sort,parent,false);
        }

        final Sort sort = getItem(position);
        populateView(inflatedView,sort);

        return inflatedView;
    }

    private void populateView(View inflatedView, Sort sort) {
        TextView tvSortName = inflatedView.findViewById(R.id.tv_nomSort);
        TextView tvSortLevel = inflatedView.findViewById(R.id.tv_niveauSort);

        tvSortLevel.setText(String.valueOf(sort.getNiveauSort()));
        tvSortName.setText(sort.nomSort);
    }


}
