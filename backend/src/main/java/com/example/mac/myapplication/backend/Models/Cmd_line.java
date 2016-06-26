package com.example.mac.myapplication.backend.Models;

/**
 * Created by mac on 26/06/16.
 */
public class Cmd_line {

    Integer Cmd_id;
    FullProduct FullProduct;
    Integer quantity_Cmd_line;

    public Integer getCmd_id() {
        return Cmd_id;
    }

    public void setCmd_id(Integer cmd_id) {
        Cmd_id = cmd_id;
    }

    public com.example.mac.myapplication.backend.Models.FullProduct getFullProduct() {
        return FullProduct;
    }

    public void setFullProduct(com.example.mac.myapplication.backend.Models.FullProduct fullProduct) {
        FullProduct = fullProduct;
    }

    public Integer getQuantity_Cmd_line() {
        return quantity_Cmd_line;
    }

    public void setQuantity_Cmd_line(Integer quantity_Cmd_line) {
        this.quantity_Cmd_line = quantity_Cmd_line;
    }
}
