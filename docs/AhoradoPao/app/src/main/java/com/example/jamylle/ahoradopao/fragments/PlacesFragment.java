package com.example.jamylle.ahoradopao.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jamylle.ahoradopao.R;
import com.example.jamylle.ahoradopao.adapters.PadariaAdapter;

import java.util.ArrayList;

public class PlacesFragment extends Fragment {

    Toolbar toolbar;
    ArrayList<Padaria> padarias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_places, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_main);

        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_main_title);
        toolbarTitle.setText(R.string.menu_item_places);
        toolbarTitle.setTextSize(21);

        if(!toolbar.getMenu().hasVisibleItems()) {

            toolbar.inflateMenu(R.menu.menu_toolbar_main);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.action_filter:

                            LayoutInflater inflater = getActivity().getLayoutInflater();
                            View dialogView = inflater.inflate(R.layout.dialog_filter_places, null);

                            ArrayList<String> spinnerOptions = new ArrayList<>();
                            spinnerOptions.add("Relevância");
                            spinnerOptions.add("Proximidade");
                            spinnerOptions.add("Maior preço");
                            spinnerOptions.add("Menor Preço");

                            ArrayAdapter<String> dataAdapter =
                                    new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerOptions);

                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_filter);
                            spinner.setAdapter(dataAdapter);

                            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                            dialog.setView(dialogView);
                            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { }
                            });
                            dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { }
                            });
                            dialog.show();

                            break;

                        default:
                            break;
                    }

                    return false;
                }
            });
        }

        padarias = initPlaces();

        PadariaAdapter adapter = new PadariaAdapter(getActivity(), padarias);

        ListView listView = (ListView) view.findViewById(R.id.listView_places);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();

                bundle.putString("nome",      padarias.get(position).nome);
                bundle.putString("avaliacao", padarias.get(position).avaliacao);
                bundle.putString("distancia", padarias.get(position).distancia);
                bundle.putString("tempo",     padarias.get(position).tempo);
                bundle.putString("status",    padarias.get(position).status);

                PlaceDetailsFragment detailsFragment = new PlaceDetailsFragment();
                detailsFragment.setArguments(bundle);

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, detailsFragment)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {

        toolbar.getMenu().removeItem(R.id.action_filter);

        super.onDestroy();
    }

    public ArrayList<Padaria> initPlaces() {

        ArrayList<Padaria> padarias = new ArrayList<>();

        padarias.add(new Padaria("Padaria Moura", "4.3", "1.2", "20", "Aberto"));
        padarias.add(new Padaria("Padaria da Maria", "4.5", "0.5", "12", "Aberto"));
        padarias.add(new Padaria("Panificação Irmãos Cunha", "3.5", "0.2", "5", "Fechado"));
        padarias.add(new Padaria("Padaria Top", "5.0", "3.0", "30", "Aberto"));
        padarias.add(new Padaria("Padaria 100%", "4.7", "2.0", "25", "Fechado"));

        return padarias;
    }

    public class Padaria {

        public String nome;
        public String avaliacao;
        public String distancia;
        public String tempo;
        public String status;

        public Padaria(String n, String a, String d, String t, String s) {

            this.nome = n;
            this.avaliacao = a;
            this.distancia = d;
            this.tempo = t;
            this.status = s;
        }
    }
}
