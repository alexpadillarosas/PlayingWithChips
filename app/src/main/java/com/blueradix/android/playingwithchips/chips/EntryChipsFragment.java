package com.blueradix.android.playingwithchips.chips;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueradix.android.playingwithchips.R;
import com.blueradix.android.playingwithchips.databinding.EntryChipsFragmentBinding;
import com.google.android.material.chip.Chip;


public class EntryChipsFragment extends Fragment {


    private EntryChipsFragmentBinding binding;

    public static EntryChipsFragment newInstance() {
        return new EntryChipsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = EntryChipsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.entryChipsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence)){
                    if(charSequence.length() > 1 && charSequence.charAt(charSequence.length() -1) == ',') {
                        addChipToGroup(charSequence.toString().replace(",", ""));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())){
                    if(editable.toString().length() == 1 && editable.toString().endsWith(",")){
                        binding.entryChipsEditText.setText("");
                    }else if(editable.toString().length() > 1 && editable.toString().endsWith(",")){
                        binding.entryChipsEditText.setText("");
                    }

                }
            }
        });


    }

    private void addChipToGroup(String text) {
        Chip chip = new Chip(getContext());
        chip.setCloseIconVisible(true);
        chip.setClickable(true);
        chip.setText(text);

        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.entryChipsGroup.removeView(view);
            }
        });
        binding.entryChipsGroup.addView(chip);
    }
}

