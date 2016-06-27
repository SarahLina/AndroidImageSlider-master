package com.example.mac.myapplication.backend.Servlet;

import com.example.mac.myapplication.backend.DataBaseService.DataBaseService;
import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mac on 27/06/16.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password =req.getParameter("password");


        int state = new DataBaseService().login(username,password);
        resp.getWriter().write(new Gson().toJson(state));
    }
}
