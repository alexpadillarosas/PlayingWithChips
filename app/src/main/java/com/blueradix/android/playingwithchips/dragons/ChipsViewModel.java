package com.blueradix.android.playingwithchips.dragons;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.blueradix.android.playingwithchips.dragons.Dragon;
import com.blueradix.android.playingwithchips.dragons.Sign;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChipsViewModel extends AndroidViewModel {

    private List<Dragon> listOfDragons;
    private List<Dragon> listOfDragonsToRemove;

    private List<Dragon> listOfCheckedDragons;
    private List<String> listOfDragonsType;
    private List<Dragon> listOfFilteredDragonsPerType;

    public ChipsViewModel(@NonNull Application application) {
        super(application);
        listOfDragons = populateDragons();
        listOfDragonsToRemove = populateDragons();
        listOfCheckedDragons = new ArrayList<>();
        listOfDragonsType = populateDragonsType();
        listOfFilteredDragonsPerType = new ArrayList<>();
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

        return list;
    }

    private List<String> populateDragonsType(){
        /* populateDragons()
                .stream()
                .collect(Collectors.groupingBy( d -> d.getType()))
                .values()
                .stream().flatMap(e -> e.stream().limit(1))
                .collect(Collectors.toList());

         */
        Set<String> set = populateDragons().stream().map(Dragon::getType).collect(Collectors.toSet());
        return set.stream().collect(Collectors.toList());

    }


    public void restoreRemovedDragons(){
        listOfDragonsToRemove = populateDragons();
    }

    public List<Dragon> getListOfDragons() {
        return listOfDragons;
    }


    public List<Dragon> getListOfDragonsToRemove() {
        return listOfDragonsToRemove;
    }

    public void setListOfDragonsToRemove(List<Dragon> listOfDragonsToRemove) {
        this.listOfDragonsToRemove = listOfDragonsToRemove;
    }

    public List<String> getListOfDragonsType() {
        return listOfDragonsType;
    }

    public void setListOfDragonsType(List<String> listOfDragonsType) {
        this.listOfDragonsType = listOfDragonsType;
    }

    public List<Dragon> getListOfFilteredDragonsPerType() {
        return listOfFilteredDragonsPerType;
    }

    public void setListOfFilteredDragonsPerType(List<Dragon> listOfFilteredDragonsPerType) {
        this.listOfFilteredDragonsPerType = listOfFilteredDragonsPerType;
    }

    public void setListOfDragons(List<Dragon> listOfDragons) {
        this.listOfDragons = listOfDragons;
    }

    public List<Dragon> getListOfCheckedDragons() {
        return listOfCheckedDragons;
    }

    public void setListOfCheckedDragons(List<Dragon> listOfCheckedDragons) {
        this.listOfCheckedDragons = listOfCheckedDragons;
    }
}
