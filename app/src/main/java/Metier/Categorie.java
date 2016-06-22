package Metier;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 25/04/16.
 */
public class Categorie implements Serializable{
    private String Type;
    private int ico;
    private String name;
    private List<Product> list_product;
    public static String categorie_current;

    public Categorie() {
    }

    public Categorie(String type, int ico, String name, List<Product> list_product) {
        Type = type;
        this.ico = ico;
        this.name = name;
        this.list_product = list_product;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getList_product() {
        return list_product;
    }

    public void setList_product(List<Product> list_product) {
        this.list_product = list_product;
    }
}
