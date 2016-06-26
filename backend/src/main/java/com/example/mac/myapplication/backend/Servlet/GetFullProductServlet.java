package com.example.mac.myapplication.backend.Servlet;

import com.example.mac.myapplication.backend.DataBaseService.DataBaseService;
import com.example.mac.myapplication.backend.Models.FullProduct;
import com.example.mac.myapplication.backend.Models.Product;
import com.google.gson.Gson;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mac on 26/06/16.
 */
public class GetFullProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String density=req.getParameter("density");
        //List<FullProduct> fullProductList = new DataBaseService().getListFullProduct();
        List <Product> productList= new DataBaseService().getListProduct();
        resp.getWriter().write(new Gson().toJson(productList));
    }
}
