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
import com.blueradix.android.playingwithchips.databinding.RemoveChipsFragmentBinding;
import com.blueradix.android.playingwithchips.dragons.Dragon;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class RemoveChipsFragment extends Fragment {

    private RemoveChipsFragmentBinding binding;
    private ChipsViewModel chipsViewModel;
    private List<Dragon> listOfDragonsToRemove;

    public static RemoveChipsFragment newInstance() {
        return new RemoveChipsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RemoveChipsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipsViewModel = new ViewModelProvider(this).get(ChipsViewModel.class);

        listOfDragonsToRemove = chipsViewModel.getListOfDragonsToRemove();
        populateChipsToRemoveInList();

        binding.restoreRemovedChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clean all the chips up in the group
                binding.removeChipGroup.removeAllViews();
                //Restore all dragons to remove list in the viewModel
                chipsViewModel.restoreRemovedDragons();
                //get the restored list
                listOfDragonsToRemove = chipsViewModel.getListOfDragonsToRemove();
                //Add the restored list to the ChipGroup
                populateChipsToRemoveInList();
            }
        });

        binding.showRemainingChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer();

                for(Dragon dragon : listOfDragonsToRemove){
                    sb.append(dragon.getName()).append(" - ");
                }

                Snackbar.make(view, sb, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void populateChipsToRemoveInList() {
        for (final Dragon dragon: listOfDragonsToRemove){
            final Chip chip = new Chip(getContext());
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCloseIconVisible(true);

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //remove from the UI
                    binding.removeChipGroup.removeView(chip);
                    //remove from the list (data source)
                    listOfDragonsToRemove.remove(dragon);
                }
            });
            binding.removeChipGroup.addView(chip);
        }
    }
}