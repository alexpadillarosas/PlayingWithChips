package com.blueradix.android.playingwithchips.chips;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueradix.android.playingwithchips.R;
import com.blueradix.android.playingwithchips.databinding.OptionsFragmentBinding;


public class OptionsFragment extends Fragment {

    private OptionsFragmentBinding binding;
    public static OptionsFragment newInstance() {

        return new OptionsFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = OptionsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        binding.entryChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_optionsFragment_to_entryChipsFragment);
            }
        });

        binding.choiceChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_optionsFragment_to_choiceChipsFragment);
            }
        });

        binding.horizontallyScrollableChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_optionsFragment_to_horizontallyScrollableChipsFragment);
            }
        });

        binding.actionChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_optionsFragment_to_actionChipsFragment);
            }
        });

        binding.removableChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_optionsFragment_to_removeChipsFragment);
            }
        });

        binding.checkedChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_optionsFragment_to_checkedChipsFragment);
            }
        });

        binding.filterChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_optionsFragment_to_filterChipsFragment);
            }
        });
    }
}