package com.example.mac.myapplication.backend.Models;

import java.util.List;

/**
 * Created by mac on 26/06/16.
 */
public class ObjectCard {
    User user;

    List<Cmd_line> listCmdLine;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Cmd_line> getListCmdLine() {
        return listCmdLine;
    }

    public void setListCmdLine(List<Cmd_line> listCmdLine) {
        this.listCmdLine = listCmdLine;
    }
}
