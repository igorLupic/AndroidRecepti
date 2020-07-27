package com.example.recepti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.recepti.R;
import com.example.recepti.model.Komentar;

import java.util.List;

public class KomentarListAdapter extends ArrayAdapter<Komentar> {

    public KomentarListAdapter(Context context, List<Komentar> komentari) {
        super(context, 0, komentari);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Komentar komentar = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.komentar_list_item, viewGroup, false);
        }

        TextView komentator_view = view.findViewById(R.id.komentator_view);
        komentator_view.setText(komentar.getKomentator());

        TextView tekst_view = view.findViewById(R.id.tekst_view);
        tekst_view.setText(komentar.getTekst());

        return view;
    }


}
