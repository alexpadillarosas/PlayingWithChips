package com.blueradix.android.playingwithchips.chips;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueradix.android.playingwithchips.dragons.ChipsViewModel;
import com.blueradix.android.playingwithchips.databinding.HorizontallyScrollableChipsFragmentBinding;
import com.blueradix.android.playingwithchips.dragons.Dragon;
import com.google.android.material.chip.Chip;


public class HorizontallyScrollableChipsFragment extends Fragment {

    private HorizontallyScrollableChipsFragmentBinding binding;
    private ChipsViewModel chipsViewModel;
    public static HorizontallyScrollableChipsFragment newInstance() {
        return new HorizontallyScrollableChipsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = HorizontallyScrollableChipsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipsViewModel = new ViewModelProvider(this).get(ChipsViewModel.class);

        for(final Dragon dragon : chipsViewModel.getListOfDragons()){
            final Chip chip = new Chip(getContext());
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCheckable(true);
            binding.horizontallyScrollableChipGroup.addView(chip);
        }
    }
}