package com.blueradix.android.playingwithchips.dragons;

public class Dragon {

    private String name;
    private Integer id;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Dragon(String name, Integer id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }
}
