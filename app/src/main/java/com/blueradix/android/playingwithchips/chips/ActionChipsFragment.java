package com.blueradix.android.playingwithchips.chips;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueradix.android.playingwithchips.R;
import com.blueradix.android.playingwithchips.databinding.ActionChipsFragmentBinding;
import com.blueradix.android.playingwithchips.dragons.Sign;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ActionChipsFragment extends Fragment {

    private ActionChipsFragmentBinding binding;
    private List<Sign> listOfSigns;
    public static ActionChipsFragment newInstance() {
        return new ActionChipsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ActionChipsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create an OnClickListener
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageSnackbar(v);
            }
        };


        binding.rockChip.setOnClickListener(onClickListener);
        binding.paperChip.setOnClickListener(onClickListener);
        binding.scissorChip.setOnClickListener(onClickListener);

        //FOR Programmed actions
        listOfSigns = populateSigns();
        for (final Sign sign : listOfSigns){
            Chip chip = new Chip(getContext());
            //The tag property is for us to store things, you can store an object or a key value pair.
            //we are going to store the whole sign object
            chip.setTag(sign);
            chip.setText(sign.getName());
            chip.setChipIconResource(sign.getImageResourceId());

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sign sign = (Sign) chip.getTag();
                    Snackbar.make(v, "id: " + sign.getId() + " : " + sign.getName(), Snackbar.LENGTH_LONG).show();

                    //here you can open a new Activity if you want.

                }
            });
            binding.programmedActionChipGroup.addView(chip);
        }
    }

    private void showMessageSnackbar(View v) {
        Snackbar.make(v, ((Chip)v).getText().toString() , Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    private List<Sign> populateSigns(){
        List<Sign> signs = new ArrayList<>();
        signs.add(new Sign(1L, "Sun", R.drawable.ic_baseline_wb_sunny_24));
        signs.add(new Sign(2L, "Eye", R.drawable.ic_baseline_visibility_24));
        signs.add(new Sign(3L, "Verified", R.drawable.ic_baseline_verified_24));

        return signs;
    }
}