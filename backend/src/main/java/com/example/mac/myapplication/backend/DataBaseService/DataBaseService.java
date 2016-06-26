package com.example.mac.myapplication.backend.DataBaseService;

import com.example.mac.myapplication.backend.Models.FullProduct;
import com.example.mac.myapplication.backend.Models.Product;

import org.apache.commons.codec.binary.Base64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 26/06/16.
 */
public class DataBaseService {

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine = "jdbc:mysql://localhost:3306/JulinaDB";
    public static final String username = "root";
    public static final String password = "root";

    public Connection connecter() {

        Connection con = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection(chaine, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    public List<FullProduct> getListFullProduct()  {
        List<FullProduct> listProducts = new ArrayList<FullProduct>();
        Connection conn = null;
        PreparedStatement pst = null;

        String query = "select * from Product";

        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            //pst.setString(1,density);
            ResultSet rs = pst.executeQuery();

            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    FullProduct fullProduct= new FullProduct();
                    Product product = new Product();
                    product.setRef(rs.getString("ref_product"));
                    product.setPrice(rs.getFloat("price_product"));
                    product.setCategory(rs.getString("category_product"));
                    product.setClient(rs.getString("client_product"));
                    product.setName(rs.getString("name_product"));
                 //   product.setCover(Base64.encodeBase64String(rs.getBytes("iconcover")));
                 //   product.setCover1(Base64.encodeBase64String(rs.getBytes("iconcover1")));
                 //   product.setCover2(Base64.encodeBase64String(rs.getBytes("iconcover2")));
                 //   product.setCover3(Base64.encodeBase64String(rs.getBytes("iconcover3")));


                  //  fullProduct.setProduct(product);
                  //  fullProduct.setColor(rs.getString("color"));
                  //  fullProduct.setSize(rs.getString("size"));
                  //  fullProduct.setQuantity(rs.getInt("quantity"));

                    listProducts.add(fullProduct);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pst!=null) pst.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProducts;

    }



    public List<Product> getListProduct()  {
        List<Product> listProducts = new ArrayList<Product>();
        Connection conn = null;
        PreparedStatement pst = null;

        String query = "select * from Product ";

        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            //pst.setString(1,density);
            ResultSet rs = pst.executeQuery();

            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    Product product = new Product();
                    product.setRef(rs.getString("ref_product"));
                    product.setPrice(rs.getFloat("price_product"));
                    //product.setCategory(rs.getString("category_product"));
                    //product.setClient(rs.getString("client_product"));
                    product.setName(rs.getString("name_product"));


                    listProducts.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pst!=null) pst.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProducts;

    }


}
