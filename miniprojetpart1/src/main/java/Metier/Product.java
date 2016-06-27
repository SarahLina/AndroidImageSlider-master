package Metier;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 26/04/16.
 */
public class Product implements Serializable {
    public static String categorie_current;
    private int id_product;

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    private String categorie;
    private String typeClient;      //Hommes ou femmes ou enfants
    private String name;
    private String ref;
    private List<String> sizes;
    private List<String> colors;

    public List<String> getSizes() {
        return sizes;
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

    private float price;
   // private int img;
    private String cover;
    private String cover1;
    private String cover2;
    private String cover3;





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

    /*public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
