package com.example.mac.miniprojetpart1;

/**
 * Created by mac on 26/06/16.
 */
public class Cmd_line {

    Integer Cmd_id;

    int id_product;
    String size;
    String color;
    Integer quantity_Cmd_line;

    public Integer getCmd_id() {
        return Cmd_id;
    }

    public void setCmd_id(Integer cmd_id) {
        Cmd_id = cmd_id;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity_Cmd_line() {
        return quantity_Cmd_line;
    }

    public void setQuantity_Cmd_line(Integer quantity_Cmd_line) {
        this.quantity_Cmd_line = quantity_Cmd_line;
    }
}
