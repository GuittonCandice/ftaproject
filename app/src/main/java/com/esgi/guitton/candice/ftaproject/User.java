package com.esgi.guitton.candice.ftaproject;

import java.util.Arrays;

/**
 * Created by candiceguitton on 28/01/2018.
 */

public class User {

    public static final String TABLE_NAME = "user";
    public static final String COL_ID = "id";
    public static final String COL_LASTNAME = "lastname";
    public static final String COL_FIRSTNAME = "firstname";
    public static final String COL_EMAIL = "email";
    public static final String COL_ID_USER = "id_user";



    private String firstname;
    private String lastname;
    private String email;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public User(String lastname, String firstname, String email, String user_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.user_id = user_id;
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


    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
