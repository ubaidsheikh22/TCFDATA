package com.example.tcfapplication;

import java.net.URL;

public class User {
    public String Username;
    public URL Imageurl;

    public User(String username, URL imageurl) {
        Username = username;
        Imageurl = imageurl;
    }

    public User() {

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public URL getImageurl() {
        return Imageurl;
    }

    public void setImageurl(URL imageurl) {
        Imageurl = imageurl;
    }
}

