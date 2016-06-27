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
public class GetDisponibiliteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_product =req.getParameter("id_product");
        String color =req.getParameter("color");
        String size =req.getParameter("size");
        String quant =req.getParameter("quantity");
        int quantity =  Integer.parseInt(quant);

        Boolean disp = new DataBaseService().checkDispo(id_product,color,size,quantity);
        resp.getWriter().write(new Gson().toJson(disp));
    }
}
