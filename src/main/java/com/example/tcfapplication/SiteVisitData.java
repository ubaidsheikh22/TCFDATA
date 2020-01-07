package com.example.tcfapplication;

import android.net.Uri;

public class SiteVisitData {

    public String Locationtxt,Regiontxt,Imagetype,Uploadbytxt;
    public Uri imageuri;

    public SiteVisitData(String locationtxt, String regiontxt, String imagetype, String uploadbytxt ,Uri uri) {
        Locationtxt = locationtxt;
        Regiontxt = regiontxt;
        Imagetype = imagetype;
        Uploadbytxt = uploadbytxt;
        imageuri=uri;
    }

    public SiteVisitData() {

    }

    public Uri getImageuri() {
        return imageuri;
    }

    public void setImageuri(Uri imageuri) {
        this.imageuri = imageuri;
    }

    public String getLocationtxt() {
        return Locationtxt;
    }

    public void setLocationtxt(String locationtxt) {
        Locationtxt = locationtxt;
    }

    public String getRegiontxt() {
        return Regiontxt;
    }

    public void setRegiontxt(String regiontxt) {
        Regiontxt = regiontxt;
    }

    public String getImagetype() {
        return Imagetype;
    }

    public void setImagetype(String imagetype) {
        Imagetype = imagetype;
    }

    public String getUploadbytxt() {
        return Uploadbytxt;
    }

    public void setUploadbytxt(String uploadbytxt) {
        Uploadbytxt = uploadbytxt;
    }
}