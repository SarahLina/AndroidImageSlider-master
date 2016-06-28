package Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Helpers.DataBaseHelper;
import Metier.FullProduct;
import Metier.Product;

/**
 * Created by msia on 26/06/2016.
 */
public class FullProductRepo {
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private SQLiteDatabase db;
    public FullProductRepo(Context context){
        dataBaseHelper = new DataBaseHelper(context);
        this.context=context;
    }
    public void addFullProduct(int id_prod,String color,String size){
        db = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Color",color);
        contentValues.put("Size",size);
        contentValues.put("id_product",id_prod);
        db.insert("FullProduct",null,contentValues);
        db.close();
    }
    public FullProduct getFullProduct(int idFullkey){
        db = dataBaseHelper.getReadableDatabase();
        String query = "select * from FullProduct where id_fullKey=?";
        Cursor cursor = db.rawQuery(query,new String[]{Integer.toString(idFullkey)});
        FullProduct fullProduct = new FullProduct();
        ProductRepo productRepo = new ProductRepo(this.context);
        if(cursor.moveToFirst()){
        System.out.println("there is only one full prod");
        fullProduct.setProduct(productRepo.getProductsById(cursor.getInt(1)));
        fullProduct.setColor(cursor.getString(2));
        fullProduct.setSize(cursor.getString(3));
        }else
        fullProduct=null;
        return fullProduct;
    }
    public int getIdProduct (int idFullkey){
        db = dataBaseHelper.getReadableDatabase();
        String query = "select id_product from FullProduct where id_fullKey=?";
        Cursor cursor = db.rawQuery(query,new String[]{Integer.toString(idFullkey)});
        int id=-1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        return id;

    }
    public int getIdFullProd(int idProd,String couleur ,String size){
        db = dataBaseHelper.getReadableDatabase();
        String query = "select id_fullKey from FullProduct where Size like ? and id_product=? and Color like?";
        Cursor cursor = db.rawQuery(query,new String[]{size,Integer.toString(idProd),couleur});
        int id=-1;
        if (cursor.moveToFirst()){
            id=cursor.getInt(0);
        }
        return id;
    }
    public boolean existSizesProd(String size,int idProd){
       // List<FullProduct>  listFullProduct = new ArrayList<>();
        db = dataBaseHelper.getReadableDatabase();
        String query = "select * from FullProduct where Size like ? and id_product=?";
        Cursor cursor = db.rawQuery(query,new String[]{size,Integer.toString(idProd)});
        //cursor est une liste


        if(cursor.moveToFirst()){
            db.close();
            return true;
        }else{
            db.close();
        return false;
        }
    }
    public boolean existColorProd(String color,int idProd){
        //List<FullProduct>  listFullProduct = new ArrayList<>();
        db = dataBaseHelper.getReadableDatabase();
        String query = "select * from FullProduct where Color like ? and id_product=?";
        Cursor cursor = db.rawQuery(query,new String[]{color,Integer.toString(idProd)});
        //cursor est une liste
        ;

        if(cursor.moveToFirst()){
            db.close();
            return true;
        }else{
            db.close();
            return false;
        }

    }


/*
    public List<FullProduct> getFullProduct(String client){
        List<FullProduct>  listFullProduct = new ArrayList<>();
        productRepo = new ProductRepo(this.context);
        List<Product> listProd = new ArrayList<>();
        db = dataBaseHelper.getReadableDatabase();
        String query = "select * from Product where e_client_product like ?";
        Cursor cursor = db.rawQuery(query,new String[]{client});
        //cursor est une liste
        cursor.moveToFirst();
        while (!cursor.isLast()){
            Product product = new Product();
            product.setName(cursor.getString(0));
            product.setTypeClient(cursor.getString(1));
            product.setCategorie(cursor.getString(2));
            product.setPrice(cursor.getFloat(3));
            product.setRef(cursor.getString(4));
            listProd.add(product);
            cursor.moveToNext();
        }
        db.close();




        return listFullProduct;
    }*/

}
