package com.example.jamylle.ahoradopao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jamylle.ahoradopao.R;
import com.example.jamylle.ahoradopao.fragments.PlacesFragment;

import java.util.ArrayList;

public class PadariaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PlacesFragment.Padaria> padarias;

    public PadariaAdapter(Context c, ArrayList<PlacesFragment.Padaria> p) {

        this.context = c;
        this.padarias = p;
    }

    @Override
    public int getCount() {

        return padarias.size();
    }

    @Override
    public Object getItem(int position) {

        return padarias.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = LayoutInflater.from(context).inflate(R.layout.row_listview_places, null);

        PlacesFragment.Padaria padaria = padarias.get(position);

        TextView nome      = (TextView) row.findViewById(R.id.textView_bakery_name);
        TextView avaliacao = (TextView) row.findViewById(R.id.textView_ratingBar);
        TextView info      = (TextView) row.findViewById(R.id.textView_bakery_info);
        TextView status    = (TextView) row.findViewById(R.id.textView_bakery_status);

        nome.setText(padaria.nome);
        avaliacao.setText(padaria.avaliacao);
        info.setText(String.format("~ %s min   •   %s KM", padaria.tempo, padaria.distancia));
        status.setText(padaria.status);

        if (padaria.status.equals("Aberto")) {

            status.setTextColor(android.graphics.Color.parseColor("#008000"));
        }
        else {

            status.setTextColor(android.graphics.Color.parseColor("#ff1900"));
        }

        return row;
    }
}
