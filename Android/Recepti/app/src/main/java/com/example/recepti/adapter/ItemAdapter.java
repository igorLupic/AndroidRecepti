package com.example.recepti.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.recepti.R;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] recepti;
    Resources resources;



    public ItemAdapter(Context cx, String[] ar){
        mInflater = (LayoutInflater) cx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resources = cx.getResources();
        recepti = ar;
    }

    @Override
    public int getCount() {
        return recepti.length;
    }

    @Override
    public Object getItem(int position) {
        return recepti[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.recept_list_item, null);
        TextView nazivTextView = v.findViewById(R.id.naziv_view);
        TextView tezinaTextView = v.findViewById(R.id.tezina_view);

        String ar = recepti[position];

        nazivTextView.setText(ar);
        tezinaTextView.setText(ar);
        return v;
    }

}
