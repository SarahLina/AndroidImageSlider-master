package com.example.mac.myapplication.backend.Models;

/**
 * Created by msia on 27/04/2016.
 */
public class Cmd {
    private String nameCmd;
    private String date;
    private String etat;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameCmd() {
        return nameCmd;
    }

    public void setNameCmd(String nameCmd) {
        this.nameCmd = nameCmd;
    }


}
