package Metier;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 26/04/16.
 */
public class Product implements Serializable {
    public static String categorie_current;

    private String categorie;
    private String typeClient;      //Hommes ou femmes ou enfants
    private String name;
    private String ref;
    private List<String> sizes;
    private List<String> colors;
    private float price;
    private int img;
    private List<Integer>  tab_img;


    public void setTab_img(List<Integer> tab_img) {
        this.tab_img = tab_img;
    }




    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public static String getCurrent_caretorie() {
        return categorie_current;
    }

    public static void setCategorie_current(String current_caretorie) {
        Product.categorie_current = current_caretorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public List<Integer> getTab_img() {
        return tab_img;
    }

    public Integer getTab_img(int pos) {
        return tab_img.get(pos);
    }




    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
