package com.example.jamylle.ahoradopao.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jamylle.ahoradopao.R;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_main);

        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_main_title);
        toolbarTitle.setText(R.string.menu_item_orders);
        toolbarTitle.setTextSize(21);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_make_order, null);

                ArrayList<String> spinnerPlacesOptions = new ArrayList<>();
                spinnerPlacesOptions.add("Selecione...");
                spinnerPlacesOptions.add("Padaria Moura");
                spinnerPlacesOptions.add("Padaria da Maria");
                spinnerPlacesOptions.add("Panificação Irmãos Cunha");
                spinnerPlacesOptions.add("Padaria Top");
                spinnerPlacesOptions.add("Padaria 100%");

                ArrayList<String> spinnerPaesOptions = new ArrayList<>();
                spinnerPaesOptions.add("Selecione...");
                spinnerPaesOptions.add("Mistos");
                spinnerPaesOptions.add("Doces");
                spinnerPaesOptions.add("Salgados");
                spinnerPaesOptions.add("de Ló");

                ArrayAdapter<String> dataAdapterPlaces =
                        new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerPlacesOptions);

                ArrayAdapter<String> dataAdapterPaes =
                        new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerPaesOptions);

                dataAdapterPlaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapterPaes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner spinnerPlaces = (Spinner) dialogView.findViewById(R.id.spinner_places);
                spinnerPlaces.setAdapter(dataAdapterPlaces);
                spinnerPlaces.setSelection(0);

                Spinner spinnerPaes = (Spinner) dialogView.findViewById(R.id.spinner_paes);
                spinnerPaes.setAdapter(dataAdapterPaes);
                spinnerPaes.setSelection(0);

                NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.numberPicker);
                numberPicker.setMaxValue(10);
                numberPicker.setMinValue(0);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setView(dialogView);
                dialog.setPositiveButton("Pedir", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Snackbar.make(getView(), "Pedido realizado!", Snackbar.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Snackbar.make(getView(), "Pedido cancelado!", Snackbar.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }
}
