package com.blueradix.android.playingwithchips.chips;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.blueradix.android.playingwithchips.dragons.ChipsViewModel;
import com.blueradix.android.playingwithchips.databinding.FilterChipsFragmentBinding;
import com.blueradix.android.playingwithchips.dragons.Dragon;
import com.google.android.material.chip.Chip;

public class FilterChipsFragment extends Fragment {

    private FilterChipsFragmentBinding binding;

    private ChipsViewModel chipsViewModel;
    public static FilterChipsFragment newInstance() {
        return new FilterChipsFragment();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FilterChipsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipsViewModel = new ViewModelProvider(this).get(ChipsViewModel.class);

        //Restores the list of Dragons Type from the view model
        for( final String type : chipsViewModel.getListOfDragonsType()){
            final Chip chip = new Chip(getContext());
            chip.setText(type);
            chip.setCheckable(true);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        addChipsToSelectedDragonsPerType(type);
                    }else{
                        removeChipsFromSelectedDragonsPerType(type);
                    }
                }
            });
            binding.dragonsTypeChipGroup.addView(chip);
        }

        //Restores the selected dragons per type ChipGroup from the list in the viewModel
        for (final Dragon dragon : chipsViewModel.getListOfFilteredDragonsPerType()){
             final Chip chip = new Chip(getContext());
            chip.setText(dragon.getName());
            //here we are adding the whole dragon object as a tag!!!
            chip.setTag(dragon);
            binding.selectedDragonsPerTypeChipGroup.addView(chip);
        }
    }

    private void removeChipsFromSelectedDragonsPerType(String type) {
        for (int i = binding.selectedDragonsPerTypeChipGroup.getChildCount() - 1 ; i >= 0 ; i--){
            View view = binding.selectedDragonsPerTypeChipGroup.getChildAt(i);
            Dragon dragon = (Dragon) view.getTag(); //remember we stored the whole Dragon object in the TAG property.
            if (dragon.getType().equals(type)){
                binding.selectedDragonsPerTypeChipGroup.removeView(view);
                chipsViewModel.getListOfFilteredDragonsPerType().remove(dragon);
            }
        }
    }

    private void addChipsToSelectedDragonsPerType(String type) {

        for(final Dragon dragon : chipsViewModel.getListOfDragons()){
            if(dragon.getType().equals(type)){
                final Chip chip = new Chip(getContext());
                chip.setText(dragon.getName());
                //here we are adding the whole dragon object as a tag!!!
                chip.setTag(dragon);

                binding.selectedDragonsPerTypeChipGroup.addView(chip);
                chipsViewModel.getListOfFilteredDragonsPerType().add(dragon);
            }
        }
    }
}