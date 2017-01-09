package com.example.jamylle.ahoradopao.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamylle.ahoradopao.R;

public class OffersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_offers, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_main);

        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_main_title);
        toolbarTitle.setText(R.string.menu_item_offers);
        toolbarTitle.setTextSize(21);

        return view;
    }
}
