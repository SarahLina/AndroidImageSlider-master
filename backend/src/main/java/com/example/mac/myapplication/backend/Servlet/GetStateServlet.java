package com.example.mac.myapplication.backend.Servlet;

import com.example.mac.myapplication.backend.DataBaseService.DataBaseService;
import com.example.mac.myapplication.backend.Models.FullProduct;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mac on 26/06/16.
 */
public class GetStateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id_cmd");
        int id_cmd= Integer.parseInt(id);

        String username=req.getParameter("username");

        String state = new DataBaseService().getStateCmd(id_cmd,username);
        resp.getWriter().write(new Gson().toJson(state));
    }
}
