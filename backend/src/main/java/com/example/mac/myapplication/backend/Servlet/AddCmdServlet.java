package com.example.mac.myapplication.backend.Servlet;

import com.example.mac.myapplication.backend.DataBaseService.DataBaseService;
import com.example.mac.myapplication.backend.Models.Cmd_line;
import com.example.mac.myapplication.backend.Models.ObjectCard;
import com.example.mac.myapplication.backend.Models.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mac on 26/06/16.
 */
public class AddCmdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("ani ha nedkhol1");

        String data ;
        StringBuilder result = new StringBuilder();
        InputStream inputStream = req.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((data = reader.readLine()) != null) {
            result.append(data);
        }

        ObjectCard objectCard = new Gson().fromJson(result.toString(),ObjectCard.class);
        System.out.print("le username de lobjet"+objectCard.getUsername());
        // i= 1 ou i = -1 selon si l'insertion dans la bdd a été faite ou pass
        int i = new DataBaseService().addCmd(objectCard);

        resp.getWriter().write(String.valueOf(i));
    }


}
