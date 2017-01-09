package com.example.jamylle.ahoradopao.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jamylle.ahoradopao.R;

public class PlaceDetailsFragment extends Fragment {

    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.fragment_place_details, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_main);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new PlacesFragment())
                        .commit();
            }
        });

        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_main_title);
        toolbarTitle.setText(bundle.getString("nome"));
        toolbarTitle.setTextSize(18);

        TextView nome          = (TextView) view.findViewById(R.id.textView_bakery_name);
        TextView avaliacao     = (TextView) view.findViewById(R.id.textView_ratingBar);
        TextView info          = (TextView) view.findViewById(R.id.textView_bakery_info);
        final TextView details = (TextView) view.findViewById(R.id.textView_bakery_details);

        nome.setText(bundle.getString("nome"));
        avaliacao.setText(bundle.getString("avaliacao"));
        info.setText(String.format("~ %s min   •   %s KM", bundle.getString("tempo"), bundle.getString("distancia")));

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        RatingBar ratingBarMain = (RatingBar) view.findViewById(R.id.ratingBar_main);

        ratingBar.setRating(Float.parseFloat(bundle.getString("avaliacao")));
        ratingBarMain.setRating(Float.parseFloat(bundle.getString("avaliacao")));

        final RelativeLayout ratingContainer = (RelativeLayout) view.findViewById(R.id.ratingContainer);

        Button buttonRating = (Button) view.findViewById(R.id.button_rating);
        Button buttonDetails = (Button) view.findViewById(R.id.button_details);

        buttonRating.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                details.setVisibility(View.GONE);
                ratingContainer.setVisibility(View.VISIBLE);
            }
        });

        buttonDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ratingContainer.setVisibility(View.GONE);
                details.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {

        toolbar.setNavigationIcon(null);

        super.onDestroy();
    }
}
