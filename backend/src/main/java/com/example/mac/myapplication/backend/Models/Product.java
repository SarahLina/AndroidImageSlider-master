package com.example.mac.myapplication.backend.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 26/04/16.
 */
public class Product implements Serializable {
    public static String categorie_current;

    private int id_product;
    private String category;
    private String client;      //Hommes ou femmes ou enfants
    private String name;
    private String ref;
    private float price;
    private String cover;
    private String cover1;
    private String cover2;
    private String cover3;

    public static String getCategorie_current() {
        return categorie_current;
    }

    public static void setCategorie_current(String categorie_current) {
        Product.categorie_current = categorie_current;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover1() {
        return cover1;
    }

    public void setCover1(String cover1) {
        this.cover1 = cover1;
    }

    public String getCover2() {
        return cover2;
    }

    public void setCover2(String cover2) {
        this.cover2 = cover2;
    }

    public String getCover3() {
        return cover3;
    }

    public void setCover3(String cover3) {
        this.cover3 = cover3;
    }
}