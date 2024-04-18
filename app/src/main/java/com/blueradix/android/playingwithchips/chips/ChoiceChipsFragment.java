package com.blueradix.android.playingwithchips.chips;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueradix.android.playingwithchips.R;

public class ChoiceChipsFragment extends Fragment {


    public static ChoiceChipsFragment newInstance() {
        return new ChoiceChipsFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.choice_chips_fragment, container, false);
    }
}