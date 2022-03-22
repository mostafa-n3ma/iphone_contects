package com.example.iphone_contects.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String first_name;
    private String lastname;
    private String number_home;
    private String number_mobile;
    private String email;
    private String address;
    private boolean fav=false;

    public void setId(int id) {
        this.id = id;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNumber_home() {
        return number_home;
    }

    public String getNumber_mobile() {
        return number_mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean isFav() {
        return fav;
    }

    public Contact(String first_name, String lastname, String number_home, String number_mobile, String email, String address, boolean fav) {
        this.first_name = first_name;
        this.lastname = lastname;
        this.number_home = number_home;
        this.number_mobile = number_mobile;
        this.email = email;
        this.address = address;
        this.fav = fav;
    }

    public Contact() {
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNumber_home(String number_home) {
        this.number_home = number_home;
    }

    public void setNumber_mobile(String number_mobile) {
        this.number_mobile = number_mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
