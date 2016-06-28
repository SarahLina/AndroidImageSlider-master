package Metier;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by mac on 26/06/16.
 */
public class FullProduct {
    int id_fullProduct;
    Product product;
    String size;
    String color;
    int quantity;//// quantite dipo


    public int getId_fullProduct() {
        return id_fullProduct;
    }

    public void setId_fullProduct(int id_fullProduct) {
        this.id_fullProduct = id_fullProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
