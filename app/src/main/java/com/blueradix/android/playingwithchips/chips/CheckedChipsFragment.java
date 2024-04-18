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
import com.blueradix.android.playingwithchips.databinding.CheckedChipsFragmentBinding;
import com.blueradix.android.playingwithchips.dragons.Dragon;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

public class CheckedChipsFragment extends Fragment {

    private CheckedChipsFragmentBinding binding;

    private ChipsViewModel chipsViewModel;

//    private List<Dragon> listOfDragonsToCheck;

    public static CheckedChipsFragment newInstance() {
        return new CheckedChipsFragment();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = CheckedChipsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipsViewModel = new ViewModelProvider(this).get(ChipsViewModel.class);

        for (final Dragon dragon : chipsViewModel.getListOfDragons()){
            final Chip chip = new Chip(getContext());
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCheckable(true);
            chip.setCheckedIconVisible(true);


            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean  isChecked) {
                    if(isChecked){
                        //add the selected dragon into a List in the view model
                        chipsViewModel.getListOfCheckedDragons().add(dragon);
                        if(binding.showMessageCheckedChipsSwitch.isChecked()){
                            Snackbar.make(compoundButton.getRootView(), dragon.getName() + " : you clicked me!", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            });
            if(chipsViewModel.getListOfCheckedDragons().contains(dragon)){
                chip.setChecked(true);
            }
            binding.chechedChipsGroup.addView(chip);
        }

        //restore the checked dragons using the list of checked dragons stored in the View Model
        //this will cater for any screen rotation.
//        for (final Dragon dragon : chipsViewModel.getListOfCheckedDragons()){
//
//        }

//        binding.showCheckedChipsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                StringBuilder sb = new StringBuilder();
//                List<Integer> checkedChipIds = binding.chechedChipsGroup.getCheckedChipIds();
//                for(Integer id : checkedChipIds){
//                    Chip chip = binding.chechedChipsGroup.findViewById(id);
//                    if(chip.isChecked()){
//                        sb.append(chip.getText()).append("-").append(chip.getTag()).append(" / ");
//                    }
//                }
//                Snackbar.make(view.getRootView(), "selected dragons: " + sb, Snackbar.LENGTH_LONG).show();
//            }
//        });

        binding.showCheckedChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                for (final Dragon dragon : chipsViewModel.getListOfCheckedDragons()){
                    sb.append(dragon.getName()).append("-").append(dragon.getId()).append(" / ");
                }
                Snackbar.make(view.getRootView(), "selected dragons: " + sb, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}