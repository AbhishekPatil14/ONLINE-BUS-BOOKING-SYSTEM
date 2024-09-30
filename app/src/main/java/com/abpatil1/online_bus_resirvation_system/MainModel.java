package com.abpatil1.online_bus_resirvation_system;

public class MainModel {

    String email,name,surl;

    MainModel()
    {

    }
    public MainModel(String email, String name, String surl) {
        this.email = email;
        this.name = name;
        this.surl = surl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
