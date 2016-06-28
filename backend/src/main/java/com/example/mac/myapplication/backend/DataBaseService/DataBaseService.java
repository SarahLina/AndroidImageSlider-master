package com.example.mac.myapplication.backend.DataBaseService;

import com.example.mac.myapplication.backend.Models.Cmd;
import com.example.mac.myapplication.backend.Models.Cmd_line;
import com.example.mac.myapplication.backend.Models.FullProduct;
import com.example.mac.myapplication.backend.Models.FullProducttmp;
import com.example.mac.myapplication.backend.Models.ObjectCard;
import com.example.mac.myapplication.backend.Models.Product;
import com.example.mac.myapplication.backend.Models.User;
import com.sun.javafx.binding.StringFormatter;

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
    private static int id_encours =2;

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine = "jdbc:mysql://localhost:3306/JulinaDB";
    public static final String username = "root";
    public static final String password = "root";

    public Connection connecter()
    {

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

    public List<FullProducttmp> getListFullProduct(String density)
    {
        List<FullProducttmp> listProducts = new ArrayList<FullProducttmp>();
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
                    FullProducttmp fullProducttmpt= new FullProducttmp();
                    fullProducttmpt.setId_product(rs.getInt("id_product"));
                    fullProducttmpt.setRef(rs.getString("ref_product"));
                    fullProducttmpt.setPrice(rs.getFloat("price_product"));
                    fullProducttmpt.setCategorie(rs.getString("category_product"));
                    fullProducttmpt.setTypeClient(rs.getString("client_product"));
                    fullProducttmpt.setName(rs.getString("name_product"));
                 //   fullProducttmpt.setCover(Base64.encodeBase64String(rs.getBytes("iconcover")));
                  //  fullProducttmpt.setCover1(Base64.encodeBase64String(rs.getBytes("iconcover1")));
                  //  fullProducttmpt.setCover2(Base64.encodeBase64String(rs.getBytes("iconcover2")));
                  //  fullProducttmpt.setCover3(Base64.encodeBase64String(rs.getBytes("iconcover3")));

                    fullProducttmpt.setColor(rs.getString("color"));
                    fullProducttmpt.setSize(rs.getString("size"));

                    listProducts.add(fullProducttmpt);
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

    public List<Product> getListProduct(int id_product)
    {
        List<Product> listProducts = new ArrayList<Product>();
        Connection conn = null;
        PreparedStatement pst = null;

        String query = "select * from Product where id_product=?";

        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setInt(1,id_product);
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

        String user= objectCard.getUsername();


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        String formattedDate = df.format(c.getTime());


        Connection connection= connecter();

        String query= "insert into Cmd values (?,?,?,?)";
        PreparedStatement st= null;     //Prepared statement sont utilisé pour les requestes paramétrées
        int i =-1;

        try {
            st = connection.prepareStatement(query);
            st.setInt(1,id_encours);
            id_encours++;
            st.setString(2,formattedDate);
            st.setString(3,user);
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





        for (int j=0; j< listCmdLine.size(); j++)
        {
            connection= connecter();
            query= "insert into Cmd_line values (?,?,?,?,?)";
             st= null;     //Prepared statement sont utilisé pour les requestes paramétrées
             i =-1;

            try {
                st = connection.prepareStatement(query);
                st.setInt(1,this.id_encours);
                st.setInt(2,listCmdLine.get(j).getId_product());
                st.setString(4,listCmdLine.get(j).getColor());
                st.setString(5,listCmdLine.get(j).getSize());
                st.setInt(3,listCmdLine.get(j).getQuantity_Cmd_line());

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
            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    etat =rs.getString(1);
                    rs.next();
                }
            }


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
        int quantite=0;

        conn = connecter();
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(id_product));
            pst.setString(2, color);
            pst.setString(3, size);

            ResultSet rs = pst.executeQuery();

            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    quantite =rs.getInt("quantity");
                    rs.next();
                }
            }

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

    public int updateDispo(String id_product, String color, String size, int quantity)
    {
        Connection connection= connecter();
        String query= "UPDATE FullProduct SET quantity=? WHERE id_product=? and color=? and size=?";
        PreparedStatement st= null;     //Prepared statement sont utilisé pour les requestes paramétrées
        int i =-1;
        int qt = 0;


        qt = getDispo(id_product,color,size,quantity);

        if (qt>=quantity) {

            try {
                st = connection.prepareStatement(query);
                int nvquantite = qt - quantity;
                st.setInt(1, nvquantite);
                st.setString(2, id_product);
                st.setString(3, color);
                st.setString(4, size);
                i = st.executeUpdate();
                i=1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (st != null) try {
                st.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        return i;
    }

    public int getDispo (String id_product, String color, String size, int quantity)
    {
        Connection connection= connecter();
        PreparedStatement st= null;     //Prepared statement sont utilisé pour les requestes paramétrées
        int qt = 0;

        String query = "select quantity from FullProduct where id_product=? and color=? and size=?";
        Connection conn = connecter();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(id_product));
            pst.setString(2, color);
            pst.setString(3, size);

            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    qt =rs.getInt("quantity");
                    rs.next();
                }
                }
            System.out.print(qt);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (pst!=null) try {
            pst.close();
            if (conn!=null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qt;
    }

    public List<Cmd> getListCmd(String username)
    {
        List<Cmd> cmdList = new ArrayList<Cmd>();
        Connection conn = null;
        PreparedStatement pst = null;

        String query = "select * from Cmd where username=?";

        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setString(1,username);
            ResultSet rs = pst.executeQuery();

            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    Cmd cmd= new Cmd();
                    cmd.setNameCmd(rs.getString("id_cmd"));
                    cmd.setDate(rs.getString("date_cmd"));
                    cmd.setEtat(rs.getString("state"));

                    cmdList.add(cmd);
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
        return cmdList;

    }


    public int login (String username, String password)
    {
        Connection conn= connecter();
        String query= "Select * from User where username=? and password=?";
        PreparedStatement pst= null;     //Prepared statement sont utilisé pour les requestes paramétrées
        int i =-1;

        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                i=1;
                while (!rs.isAfterLast()) {
                    rs.next();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (pst!=null) try {
            pst.close();
            if (conn!=null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
