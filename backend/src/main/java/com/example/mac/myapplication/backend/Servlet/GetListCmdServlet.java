package com.example.mac.myapplication.backend.Servlet;

import com.example.mac.myapplication.backend.DataBaseService.DataBaseService;
import com.example.mac.myapplication.backend.Models.Cmd;
import com.example.mac.myapplication.backend.Models.FullProduct;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mac on 27/06/16.
 */
public class GetListCmdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        List<Cmd> cmdList= new DataBaseService().getListCmd(username);
        resp.getWriter().write(new Gson().toJson(cmdList));
    }
}
