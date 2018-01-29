package com.esgi.guitton.candice.ftaproject;

import java.util.Arrays;

/**
 * Created by candiceguitton on 28/01/2018.
 */

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String avatarPath;
    private Item[] items;

    public User(String firstname, String lastname, String email, String avatarPath, Item[] items) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatarPath = avatarPath;
        this.items = items;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
