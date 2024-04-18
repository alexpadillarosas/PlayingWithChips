package com.blueradix.android.playingwithchips;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blueradix.android.playingwithchips.databinding.ActivityMainBinding;
import com.blueradix.android.playingwithchips.dragons.Dragon;
import com.blueradix.android.playingwithchips.dragons.Sign;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//    private ActivityMainBinding binding;
//    private List<Dragon> listOfDragons;
//    private List<Dragon> listOfDragonsToRemove;
//    private List<Dragon> listOfDragonsToCheck;
//    private List<Dragon> listOfCheckedDragons;
//    private List<String> listOfDragonsFilter;
//    private List<Dragon> listOfFilteredDragons;
//
//    private List<Sign> listOfSigns;

    //http://www.schoolofdragons.com/how-to-train-your-dragon/screenshots-gallery/dragon-pictures

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



//        Toolbar toolbar = findViewById(R.id.toolbar);
//        Toolbar toolbar =
//        setSupportActionBar(toolbar);

        listOfCheckedDragons = new ArrayList<>();


        addChipsProgrammaticallyToRemoveChipsList();
        addChipsProgrammaticallyToCheckChipsList();
        addChipsProgrammaticallyToHorizontalScrollChips();
        addChipsProgrammaticallyToFilterChipsList();


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


        addChipsProgrammaticallyToActionChipsList();

        binding.restoreRemovedChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.removeChipGroup.removeAllViews();
                addChipsProgrammaticallyToRemoveChipsList();
            }
        });

        binding.showRemovedChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer();

                for(Dragon dragon : listOfDragonsToRemove){
                    sb.append(dragon.getName()).append(" ");
                }

                Snackbar.make(view, sb, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        binding.showCheckedChipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                for (final Dragon dragon : listOfCheckedDragons){

                    sb.append(dragon.getName()).append("-").append(dragon.getId()).append(" / ");

                }
                Toast.makeText(view.getContext(), "selected dragons: " + sb, Toast.LENGTH_SHORT).show();
            }
        });

        binding.entryChipEditText.addTextChangedListener(new TextWatcher() {
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
                        binding.entryChipEditText.setText("");
                    }else if(editable.toString().length() > 1 && editable.toString().endsWith(",")){
                        binding.entryChipEditText.setText("");
                    }

                }
            }
        });

    }

    private void addChipToGroup(String text) {
        Chip chip = new Chip(this);
        chip.setCloseIconVisible(true);
        chip.setClickable(true);
        chip.setText(text);


        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.entryChipGroup.removeView(view);
            }
        });
        binding.entryChipGroup.addView(chip);
    }


    private void addChipsProgrammaticallyToActionChipsList() {
        listOfSigns = populateSigns();

        for(final Sign sign : listOfSigns){
            Chip chip = new Chip(this);
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

    private List<Sign> populateSigns(){
        List<Sign> signs = new ArrayList<>();
        signs.add(new Sign(1L, "Sun", R.drawable.ic_baseline_wb_sunny_24));
        signs.add(new Sign(2L, "Eye", R.drawable.ic_baseline_visibility_24));
        signs.add(new Sign(3L, "Verified", R.drawable.ic_baseline_verified_24));

        return signs;
    }

    private void showMessageSnackbar(View v) {
        Snackbar.make(v, ((Chip)v).getText().toString() , Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void addChipsProgrammaticallyToHorizontalScrollChips() {

        listOfDragons = populateDragons();

        for (final Dragon dragon : listOfDragons) {
            final Chip chip = new Chip(this);
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCheckable(true);

            binding.horizontalChipGroup.addView(chip);
        }

    }

    private void   addChipsProgrammaticallyToFilterChipsList() {
        listOfFilteredDragons = new ArrayList<>();

        listOfDragonsFilter = new ArrayList<>();
        listOfDragonsFilter.add("Stoker");
        listOfDragonsFilter.add("Boulder");
        listOfDragonsFilter.add("Fear");
        listOfDragonsFilter.add("Sharp");
        listOfDragonsFilter.add("Tidal");
        listOfDragonsFilter.add("Mystery");
        listOfDragonsFilter.add("Strike");

        for (final String dragonType : listOfDragonsFilter){
            final Chip chip = new Chip(this);
            chip.setText(dragonType);
            chip.setCheckable(true);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        addChipsToFilteredList(buttonView.getText().toString());
                    else
                        removeChipsFromFilteredList(buttonView.getText().toString());
                }
            });


            binding.filterChipGroup.addView(chip);
        }
    }

    private void addChipsToFilteredList(String type) {

        for(final Dragon dragon : listOfDragons){
            if(dragon.getType().equals(type)){
                final Chip chip = new Chip(this);
                chip.setText(dragon.getName());
                //here we are adding the whole dragon object as a tag!!!
                chip.setTag(dragon);
                binding.filteredChipGroup.addView(chip);

                listOfFilteredDragons.add(dragon);
            }

        }


    }

    private void removeChipsFromFilteredList(String type) {

        for( int i = binding.filteredChipGroup.getChildCount() - 1 ; i >= 0 ; i-- ){
            View view = binding.filteredChipGroup.getChildAt(i);
            Dragon dragon = (Dragon)view.getTag();
            if( dragon.getType().equals(type) ) {
                binding.filteredChipGroup.removeView(view);
                listOfFilteredDragons.remove(dragon);
            }

        }

    }

    private List<Dragon> populateDragons(){
        List<Dragon> list = new ArrayList<>();
        list.add(new Dragon("Night Fury", 1, "Strike"));
        list.add(new Dragon("Stormfly", 11, "Fear"));
        list.add(new Dragon("Boneknapper", 3, "Mystery"));
        list.add(new Dragon("Meatlug", 16, "Stoker"));
        list.add(new Dragon("Hookfang", 17, "Stoker"));
        list.add(new Dragon("Skullcrusher", 18, "Boulder"));
        list.add(new Dragon("Grump", 19, "Tidal"));
        list.add(new Dragon("Shattermaster", 20, "Tidal"));
        list.add(new Dragon("Sleuther", 21, "Tidal"));
        list.add(new Dragon("Cloudjumper", 22, "Mystery"));
        list.add(new Dragon("Gronckle", 12, "Boulder"));
        list.add(new Dragon("Deadly Nadder", 8, "Sharp"));


//        list.add(new Dragon("Skrill", 2, "Strike"));
//
//        list.add(new Dragon("Changewing", 4, "Mystery"));
//        list.add(new Dragon("Thunderdrum", 5, "Tidal"));
//        list.add(new Dragon("Scauldron", 6, "Tidal"));
//        list.add(new Dragon("Timberjack", 7, "Sharp"));
//
//        list.add(new Dragon("Hideous Zippleback", 9, "Fear"));
//        list.add(new Dragon("Snaptrapper", 10, "Fear"));
//
//        list.add(new Dragon("Whispering Death", 13, "Boulder"));
//
//        list.add(new Dragon("Terrible Terror", 14, "Stoker"));
//        list.add(new Dragon("Monstrous Nightmare", 15, "Stoker"));


        return list;
    }

    private void addChipsProgrammaticallyToCheckChipsList() {

        listOfDragonsToCheck =  populateDragons();


        for (final Dragon dragon : listOfDragonsToCheck){
            final Chip chip = new Chip(this);
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCheckable(true);
            chip.setCheckedIconVisible(true);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        listOfCheckedDragons.add(dragon);
                        if( binding.showMessageCheckedChipsSwitch.isChecked()) {
                            Snackbar.make(buttonView.getRootView(), dragon.getName() + " : you clicked me!", Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        listOfCheckedDragons.remove(dragon);
                    }


                }
            });

            binding.checkedChipGroup.addView(chip);
        }

    }

    private void addChipsProgrammaticallyToRemoveChipsList() {

        listOfDragonsToRemove = populateDragons();

        for (final Dragon dragon : listOfDragonsToRemove){
            final Chip chip = new Chip(this);
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCloseIconVisible(true);

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.removeChipGroup.removeView(chip);
                    listOfDragonsToRemove.remove(dragon);
                }
            });
            binding.removeChipGroup.addView(chip);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

}
