package com.example.mac.myapplication.backend.DataBaseService;

import com.example.mac.myapplication.backend.Models.Cmd_line;
import com.example.mac.myapplication.backend.Models.FullProduct;
import com.example.mac.myapplication.backend.Models.ObjectCard;
import com.example.mac.myapplication.backend.Models.Product;
import com.example.mac.myapplication.backend.Models.User;

import org.apache.commons.codec.binary.Base64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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


    public List<FullProduct> getListFullProduct(String density)  {
        List<FullProduct> listProducts = new ArrayList<FullProduct>();
        Connection conn = null;
        PreparedStatement pst = null;

        String query = "select * from Product natural join FullProduct natural join Cover where density=?";

        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setString(1,density);
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
                    product.setCover(Base64.encodeBase64String(rs.getBytes("iconcover")));
                    product.setCover1(Base64.encodeBase64String(rs.getBytes("iconcover1")));
                    product.setCover2(Base64.encodeBase64String(rs.getBytes("iconcover2")));
                    product.setCover3(Base64.encodeBase64String(rs.getBytes("iconcover3")));

                    fullProduct.setProduct(product);
                    fullProduct.setColor(rs.getString("color"));
                    fullProduct.setSize(rs.getString("size"));
                    fullProduct.setQuantity(rs.getInt("quantity"));

                    listProducts.add(fullProduct);
                    rs.next();
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
                    product.setCategory(rs.getString("category_product"));
                    product.setClient(rs.getString("client_product"));
                    product.setName(rs.getString("name_product"));


                    listProducts.add(product);
                    rs.next();
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


    public int addCmd(ObjectCard objectCard)
    {

        List <Cmd_line> listCmdLine = objectCard.getListCmdLine();
        User user= objectCard.getUser();


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        String formattedDate = df.format(c.getTime());


        Connection connection= connecter();

        String query= "insert into Cmd values (?,?,?,?)";
        PreparedStatement st= null;     //Prepared statement sont utilisé pour les requestes paramétrées
        int i =-1;

        try {
            st = connection.prepareStatement(query);
            st.setString(2,formattedDate);
            st.setString(3,user.getUsername());
            st.setString(4,"En attente");
            i= st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (st!=null) try {
            st.close();
            if (connection!=null) connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        connection= connecter();
        String query2 ="select id_cmd from Cmd where date_cmd= ? and username=?";
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(query2);
            st.setString(2,formattedDate);
            st.setString(3,user.getUsername());
            rs = st.executeQuery();
            i= st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (st!=null) try {
            st.close();
            if (connection!=null) connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        connection= connecter();
        for (int j=0; j< listCmdLine.size(); j++)
        {
             query= "insert into Cmd_line values (?,?,?,?)";
             st= null;     //Prepared statement sont utilisé pour les requestes paramétrées
             i =-1;

            try {
                st = connection.prepareStatement(query);
                st.setString(2,rs.getString(1));
                st.setInt(3,listCmdLine.get(j).getFullProduct().getProduct().getId_product());
                st.setInt(4,listCmdLine.get(i).getQuantity_Cmd_line());
                i= st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (st!=null) try {
                st.close();
                if (connection!=null) connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return i;

    }

    public String getStateCmd(int id_cmd, String username)
    {
        Connection conn = null;
        PreparedStatement pst = null;
        String etat= null;

        String query = "select state from Cmd where id_cmd=? and username=?";

        conn = connecter();
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1,id_cmd);
            pst.setString(2, username);
            ResultSet rs = pst.executeQuery();
            etat =rs.getString(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (pst!=null) try {
            pst.close();
            if (conn!=null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etat;
    }

    public boolean checkDispo (String id_product, String color, String size, int quantity)
    {
        Connection conn = null;
        PreparedStatement pst = null;
        Boolean dispo = false;
        String query = "select quantity from FullProduct where id_product=? and color=? and size=?";

        conn = connecter();
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(id_product));
            pst.setString(2, color);
            pst.setString(3, size);

            ResultSet rs = pst.executeQuery();
            String qt =rs.getString(1);
            int quantite = Integer.parseInt(qt);

            if(quantite >= quantity) dispo=true;
            else dispo=false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (pst!=null) try {
            pst.close();
            if (conn!=null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dispo;

    }

    public int updateDispo (String id_product, String color, String size, int quantity)
    {
        Connection connection= connecter();
        String query= "UPDATE FullProduct SET quantity=? WHERE id_product=? and color=? and size=?";
        PreparedStatement st= null;     //Prepared statement sont utilisé pour les requestes paramétrées
        int i =-1;
        String qt = null;


        String query2 = "select quantity from FullProduct where id_product=? and color=? and size=?";
        Connection conn = connecter();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(id_product));
            pst.setString(2, color);
            pst.setString(3, size);

            ResultSet rs = pst.executeQuery();
            qt =rs.getString(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (pst!=null) try {
            pst.close();
            if (conn!=null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




        try {
            st = connection.prepareStatement(query);
            int nvquantite = Integer.parseInt(qt);
            nvquantite= nvquantite-quantity;
            st.setInt(1,nvquantite);
            st.setString(2,id_product);
            st.setString(3,color);
            st.setString(4, size);
            i= st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (st!=null) try {
            st.close();
            if (connection!=null) connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

}
