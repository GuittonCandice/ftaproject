package com.esgi.guitton.candice.ftaproject;

/**
 * Created by candiceguitton on 28/01/2018.
 */

public class Item {
    private String name;


    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
