package com.example.mac.miniprojetpart1;

import java.util.List;

import Metier.ArticlePannier;

/**
 * Created by mac on 26/06/16.
 */
public class ObjectCard {
    String username;

    List<Cmd_line> listCmdLine;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Cmd_line> getListCmdLine() {
        return listCmdLine;
    }

    public void setListCmdLine(List<Cmd_line> listCmdLine) {
        this.listCmdLine = listCmdLine;
    }
}
