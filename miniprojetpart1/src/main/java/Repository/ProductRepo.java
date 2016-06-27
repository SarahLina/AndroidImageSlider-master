package Repository;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Helpers.DataBaseHelper;
import Metier.Product;

/**
 * Created by msia on 26/06/2016.
 */
public class ProductRepo {
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private SQLiteDatabase db;
    public ProductRepo(Context context){
        dataBaseHelper = new DataBaseHelper(context);
        this.context=context;
    }
    public void addProduct(Product product){
        db = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_product",product.getName());
        contentValues.put("price_product",product.getPrice());
        contentValues.put("e_client_product",product.getTypeClient());
        contentValues.put("e_category_product",product.getCategorie());
        contentValues.put("ref_product",product.getRef());
       // contentValues.put("id_productimage",product.getImg());
        db.insert("Product",null,contentValues);
        db.close();
    }

    public List<Product> getProducts(){
        List<Product>  list = new ArrayList<>();
        String query = "select * from Product";
        db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        //cursor est une liste
        cursor.moveToFirst();
        while (!cursor.isLast()){
            Product product = new Product();
            product.setName(cursor.getString(0));
            product.setTypeClient(cursor.getString(1));
            product.setCategorie(cursor.getString(2));
            product.setPrice(cursor.getFloat(3));
            product.setRef(cursor.getString(4));

            list.add(product);
            cursor.moveToNext();
        }
        db.close();
        return list;
    }
    public List<Product> getProductsType(String client){
        List<Product>  list = new ArrayList<>();
        db = dataBaseHelper.getReadableDatabase();
        String query = "select * from Product where e_client_product like ?";
        Cursor cursor = db.rawQuery(query,new String[]{client});
        //cursor est une liste
        cursor.moveToFirst();

        while (!cursor.isLast()){
            Product product = new Product();
            product.setId_product(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setTypeClient(cursor.getString(2));
            product.setCategorie(cursor.getString(3));
            product.setPrice(cursor.getFloat(4));
            product.setRef(cursor.getString(5));

            list.add(product);
            cursor.moveToNext();
        }
        db.close();
        return list;
    }
    public Product getProductsByRef(String ref){
        // List<Product>  list = new ArrayList<>();
        db = dataBaseHelper.getReadableDatabase();
        String query = "select * from Product where ref_product like ?";
        Cursor cursor = db.rawQuery(query,new String[]{ref});
        //cursor est une liste
        cursor.moveToFirst();
        Product product = new Product();
        product.setId_product(cursor.getInt(0));
        product.setName(cursor.getString(1));
        product.setTypeClient(cursor.getString(2));
        product.setCategorie(cursor.getString(3));
        product.setPrice(cursor.getFloat(4));
        product.setRef(cursor.getString(5));
        /*
        list.add(product);
        cursor.moveToNext();
        while (!cursor.isLast()){
            Product product = new Product();
            product.setId_product(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setTypeClient(cursor.getString(2));
            product.setCategorie(cursor.getString(3));
            product.setPrice(cursor.getFloat(4));
            product.setRef(cursor.getString(5));

            list.add(product);
            cursor.moveToNext();
        }*/
        db.close();
        return product;
    }
    public Product getProductsById(int id){
       // List<Product>  list = new ArrayList<>();
        db = dataBaseHelper.getReadableDatabase();
        String query = "select * from Product where id_priduct=?";
        Cursor cursor = db.rawQuery(query,new String[]{Integer.toString(id)});
        //cursor est une liste
        cursor.moveToFirst();
        Product product = new Product();
        product.setId_product(cursor.getInt(0));
        product.setName(cursor.getString(1));
        product.setTypeClient(cursor.getString(2));
        product.setCategorie(cursor.getString(3));
        product.setPrice(cursor.getFloat(4));
        product.setRef(cursor.getString(5));
        /*
        list.add(product);
        cursor.moveToNext();
        while (!cursor.isLast()){
            Product product = new Product();
            product.setId_product(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setTypeClient(cursor.getString(2));
            product.setCategorie(cursor.getString(3));
            product.setPrice(cursor.getFloat(4));
            product.setRef(cursor.getString(5));

            list.add(product);
            cursor.moveToNext();
        }*/
        db.close();
        return product;
    }
}
