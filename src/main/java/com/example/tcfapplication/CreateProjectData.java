package com.example.tcfapplication;

public class CreateProjectData {

    String  ProjectId,Locationtxt,areatxt,campusnametxt,
            Regiontxt,projectinchargetxt,Categorytxt,executiontxt,accountmanagertxt,
            primaraytxt,hsecondarytxt,coverdareatxt,
            plotsizetxt,secondarytxt,projectcodetxt,
            sessiondatetxt,buildyeartxt,requestiondatetxt,donordatetxt;

    public CreateProjectData(){

    }

    public String getRegiontxt() {
        return Regiontxt;
    }

    public String getProjectinchargetxt() {
        return projectinchargetxt;
    }

    public String getCategorytxt() {
        return Categorytxt;
    }

    public String getExecutiontxt() {
        return executiontxt;
    }

    public String getAccountmanagertxt() {
        return accountmanagertxt;
    }

    public String getLocationtxt() {
        return Locationtxt;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public String getAreatxt() {
        return areatxt;
    }

    public String getCampusnametxt() {
        return campusnametxt;
    }

    public String getPrimaraytxt() {
        return primaraytxt;
    }

    public String getHsecondarytxt() {
        return hsecondarytxt;
    }

    public String getCoverdareatxt() {
        return coverdareatxt;
    }

    public String getPlotsizetxt() {
        return plotsizetxt;
    }

    public String getSecondarytxt() {
        return secondarytxt;
    }

    public String getProjectcodetxt() {
        return projectcodetxt;
    }

    public String getSessiondatetxt() {
        return sessiondatetxt;
    }

    public String getBuildyeartxt() {
        return buildyeartxt;
    }

    public String getRequestiondatetxt() {
        return requestiondatetxt;
    }

    public String getDonordatetxt() {
        return donordatetxt;
    }

    public CreateProjectData(String Locationtxt,String ProjectId, String areatxt, String campusnametxt,
                             String primaraytxt, String hsecondarytxt, String coverdareatxt,
                             String plotsizetxt, String secondarytxt, String projectcodetxt,
                             String sessiondatetxt, String buildyeartxt, String requestiondatetxt,
                             String donordatetxt, String Regiontxt,String projectinchargetxt,
                             String Categorytxt,String executiontxt,String accountmanagertxt){



        this.Locationtxt=Locationtxt;
        this.ProjectId=ProjectId;
        this.areatxt=areatxt;
        this.Regiontxt=Regiontxt;
        this.projectinchargetxt=projectinchargetxt;
        this.Categorytxt=Categorytxt;
        this.executiontxt=executiontxt;
        this.accountmanagertxt=accountmanagertxt;
        this.campusnametxt=campusnametxt;
        this.primaraytxt=primaraytxt;
        this.hsecondarytxt=hsecondarytxt;
        this.coverdareatxt=coverdareatxt;
        this.plotsizetxt=plotsizetxt;
        this.secondarytxt=secondarytxt;
        this.projectcodetxt=projectcodetxt;
        this.sessiondatetxt=sessiondatetxt;
        this.buildyeartxt=buildyeartxt;
        this.requestiondatetxt=requestiondatetxt;
        this.donordatetxt=donordatetxt;

    }

}
