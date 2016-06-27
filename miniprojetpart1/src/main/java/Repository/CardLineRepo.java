package Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Helpers.DataBaseHelper;
import Metier.ArticlePannier;
import Metier.FullProduct;
import Metier.Product;

/**
 * Created by msia on 23/06/2016.
 */
public class CardLineRepo   {

    private DataBaseHelper dataBaseHelper;
    private Context context;
    private SQLiteDatabase db;
    public CardLineRepo(Context context){
        dataBaseHelper = new DataBaseHelper(context);
        this.context=context;
    }
    public void addCardLineRepo(FullProduct fullProduct,int quantite){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        /*String query = "select id_product from Product where id_product=?";
        Cursor cursor = db.rawQuery(query,new String[]{id_product});
        int id = -1;
        if (cursor.moveToFirst()){
            id = cursor.getInt(0);
        }*/
        FullProductRepo fullProductRepo = new FullProductRepo(this.context);
        contentValues.put("id_fullKey",fullProductRepo.getIdFullProd(fullProduct.getProduct().getId_product(),fullProduct.getColor(),fullProduct.getSize()));
        contentValues.put("quantity_line_card",quantite);

        db.insert("Card_line",null,contentValues);
        db.close();
    }
    public List<ArticlePannier> getCardLine(){
        List<ArticlePannier> listCardLine = new ArrayList<>();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String query = "select * from Card_line";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        FullProductRepo fullProductRepo = new FullProductRepo(this.context);
        ProductRepo productRepo = new ProductRepo(this.context);

        while (!cursor.isLast()){
            ArticlePannier articlePannier = new ArticlePannier();
            articlePannier.setProduct(productRepo.getProductsById(fullProductRepo.getIdProduct(cursor.getInt(1))));
            articlePannier.setCouleur(fullProductRepo.getFullProduct(cursor.getInt(1)).getColor());
            articlePannier.setTaille(fullProductRepo.getFullProduct(cursor.getInt(1)).getSize());
            articlePannier.setQuantite(cursor.getInt(2));
            listCardLine.add(articlePannier);
            cursor.moveToNext();
        }

        return listCardLine;

    }
    public void  updateProduitQuantity(String id_prod,int qte) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity_line_card",qte);
        db.update("Product",contentValues,"where id_product=?",new String[]{id_prod});
        db.close();

    }





}
