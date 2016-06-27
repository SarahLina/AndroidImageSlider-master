package Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import Helpers.DataBaseHelper;

/**
 * Created by msia on 26/06/2016.
 */
public class CoverRepo {
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private SQLiteDatabase db;

    public CoverRepo(Context context){
        dataBaseHelper = new DataBaseHelper(context);
        this.context=context;
    }
    public void addCover(int id_prod,String cover,String cover1,String cover2,String cover3){
        db = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id_product",id_prod);
        contentValues.put("iconcover_productimage",cover);
        contentValues.put("cover1_productimage",cover1);
        contentValues.put("cover2_productimage",cover2);
        contentValues.put("cover3_productimage",cover3);
        db.insert("ProductImages",null,contentValues);
        db.close();
    }




}
