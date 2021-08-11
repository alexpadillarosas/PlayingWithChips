package com.blueradix.android.playingwithchips;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Dragon> listOfDragons;
    private List<Dragon> listOfDragonsToRemove;
    private List<Dragon> listOfDragonsToCheck;
    private List<Dragon> listOfCheckedDragons;
    private List<String> listOfDragonsFilter;
    private List<Dragon> listOfFilteredDragons;
    private ChipGroup chipGroup1;
    private ChipGroup chipGroup2;
    private ChipGroup filterChipGroup;
    private ChipGroup filteredChipGroup;
    private ChipGroup horizontalChipGroup;
    private Switch messageSwitch;

    //http://www.schoolofdragons.com/how-to-train-your-dragon/screenshots-gallery/dragon-pictures



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listOfCheckedDragons = new ArrayList<>();
        filteredChipGroup = findViewById(R.id.filtered_chip_group);


        addChipsProgrammaticallyToRemoveChipsList();
        addChipsProgrammaticallyToCheckChipsList();
        addChipsProgrammaticallyToHorizontalScrollChips();
        addChipsProgrammaticallyToFilterChipsList();


        Chip rock = findViewById(R.id.rock_chip);
        Chip scissor =findViewById(R.id.scissor_chip);
        Chip paper = findViewById(R.id.paper_chip);

        //Create an OnClickListener
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageSnackbar(v);
            }
        };


        rock.setOnClickListener(onClickListener);
        paper.setOnClickListener(onClickListener);
        scissor.setOnClickListener(onClickListener);

        messageSwitch = findViewById(R.id.messageSwitch);


    }

    private void showMessageSnackbar(View v) {
        Snackbar.make(v, ((Chip)v).getText().toString() , Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void addChipsProgrammaticallyToHorizontalScrollChips() {

        horizontalChipGroup = findViewById(R.id.horizontal_chip_group);

        listOfDragons = populateDragons();

        for (final Dragon dragon : listOfDragons) {
            final Chip chip = new Chip(this);
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCheckable(true);

            horizontalChipGroup.addView(chip);
        }

    }

    private void addChipsProgrammaticallyToFilterChipsList() {
        listOfFilteredDragons = new ArrayList<>();
        filterChipGroup = findViewById(R.id.filter_chip_group);

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


            filterChipGroup.addView(chip);
        }
    }

    private void addChipsToFilteredList(String type) {

        for(final Dragon dragon : listOfDragons){
            if(dragon.getType().equals(type)){
                final Chip chip = new Chip(this);
                chip.setText(dragon.getName());
                //here we are adding the whole dragon object as a tag!!!
                chip.setTag(dragon);
                filteredChipGroup.addView(chip);

                listOfFilteredDragons.add(dragon);
            }

        }


    }

    private void removeChipsFromFilteredList(String type) {

        for( int i = filteredChipGroup.getChildCount() - 1 ; i >= 0 ; i-- ){
            View view = filteredChipGroup.getChildAt(i);
            Dragon dragon = (Dragon)view.getTag();
            if( dragon.getType().equals(type) ) {
                filteredChipGroup.removeView(view);
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

        /*
        list.add(new Dragon("Skrill", 2, "Strike"));

        list.add(new Dragon("Changewing", 4, "Mystery"));
        list.add(new Dragon("Thunderdrum", 5, "Tidal"));
        list.add(new Dragon("Scauldron", 6, "Tidal"));
        list.add(new Dragon("Timberjack", 7, "Sharp"));

        list.add(new Dragon("Hideous Zippleback", 9, "Fear"));
        list.add(new Dragon("Snaptrapper", 10, "Fear"));

        list.add(new Dragon("Whispering Death", 13, "Boulder"));

        list.add(new Dragon("Terrible Terror", 14, "Stoker"));
        list.add(new Dragon("Monstrous Nightmare", 15, "Stoker"));
        */

        return list;
    }

    private void addChipsProgrammaticallyToCheckChipsList() {
        chipGroup1 = findViewById(R.id.checked_chip_group);
        //when the switch is on everytime you check a chip, it will display a message.
//        messageSwitch = findViewById(R.id.messageSwitch);

        listOfDragonsToCheck =  populateDragons();



        for (final Dragon dragon : listOfDragonsToCheck){
            final Chip chip = new Chip(this);
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCheckable(true);


            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        listOfCheckedDragons.add(dragon);
                        if(messageSwitch.isChecked()) {
                            Snackbar.make(buttonView.getRootView(), dragon.getName() + " : you clicked me!", Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        listOfCheckedDragons.remove(dragon);
                    }


                }
            });

            chipGroup1.addView(chip);
        }

    }

    private void addChipsProgrammaticallyToRemoveChipsList() {
        chipGroup2 = findViewById(R.id.remove_chip_group);

        listOfDragonsToRemove = populateDragons();

        for (final Dragon dragon : listOfDragonsToRemove){
            final Chip chip = new Chip(this);
            chip.setText(dragon.getName());
            chip.setTag(dragon.getId());
            chip.setCloseIconVisible(true);

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chipGroup2.removeView(chip);
                    listOfDragonsToRemove.remove(dragon);
                }
            });
            chipGroup2.addView(chip);
        }
    }

    public void showSelectedChips(View view) {
        StringBuilder sb = new StringBuilder();
        for (final Dragon dragon : listOfCheckedDragons){

            sb.append(dragon.getName()).append("-").append(dragon.getId()).append(" / ");

        }
        Toast.makeText(this, "selected dragons: " + sb, Toast.LENGTH_SHORT).show();
    }

    public void showNonRemovedChips(View view) {
        StringBuffer sb = new StringBuffer();

        for(Dragon dragon : listOfDragonsToRemove){
            sb.append(dragon.getName()).append(" ");
        }

        Snackbar.make(view, sb, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void restoreRemoveChipList(View view) {
        chipGroup2.removeAllViews();
        addChipsProgrammaticallyToRemoveChipsList();

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


}
