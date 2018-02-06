package com.esgi.guitton.candice.ftaproject;

/**
 * Created by candiceguitton on 28/01/2018.
 */

public class Item {

    public static final String TABLE_NAME = "item";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PATH = "path";
    public static final String COL_ID_USER = "id_user";

    private String name;
    private String path;
    private String id_user;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Item(String name, String path, String id_user) {
        this.name = name;
        this.path = path;
        this.id_user = id_user;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
                ", path='" + path + '\'' +
                ", id_user='" + id_user + '\'' +
                '}';
    }
}
