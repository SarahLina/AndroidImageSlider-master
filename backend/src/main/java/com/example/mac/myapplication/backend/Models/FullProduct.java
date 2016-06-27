package com.example.mac.myapplication.backend.Models;

/**
 * Created by mac on 26/06/16.
 */
public class FullProduct {
    Product product;
    String size;
    String color;
    int quantity;//// quantite dipo

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

