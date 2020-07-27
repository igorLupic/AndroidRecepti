package com.example.recepti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.recepti.R;
import com.example.recepti.model.Recept;

import java.util.List;

public class ReceptListAdapter extends ArrayAdapter<Recept> {

    public ReceptListAdapter(Context context, List<Recept> recepti) {
        super(context, 0, recepti);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Recept recept = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.recept_list_item, viewGroup, false);
        }

        TextView naziv_view = view.findViewById(R.id.naziv_view);
        naziv_view.setText(recept.getNaziv());

        TextView tezina_view = view.findViewById(R.id.tezina_view);
        tezina_view.setText(recept.getTezina());

        return view;
    }


}
