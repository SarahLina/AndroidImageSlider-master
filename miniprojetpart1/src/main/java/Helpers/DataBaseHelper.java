package Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by msia on 23/06/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    /// paramettre spécifique la db
    public static final String db_name = "db1";
    public static final int db_version = 4;
    private Context context;
    //-------------------------------------

    ///Card table
    public static final String CARD_KEY = "id_card";

    public static final String CARD_TABLE_NAME = "Card";

    ///// query
    public static final String CARD_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + CARD_TABLE_NAME + " (" +
                    CARD_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT);";
    /////-----------------------------------

    ///Card table
    public static final String PRODUCT_KEY = "id_product";
    public static final String PRODUCT_NAME = "name_product";
    public static final String PRODUCT_CLIENT = "client_product";
    public static final String PRODUCT_CATEGORY = "category_product";


    public static final String PRODUCT_TABLE_NAME = "Product";

    ///// query
    public static final String PRODUCT_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + PRODUCT_TABLE_NAME + " (" +
                    PRODUCT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                    PRODUCT_NAME+" TEXT" +
                    PRODUCT_CLIENT+ " TEXT" +
                    PRODUCT_CATEGORY+ "TEXT );";
    ///MAZEL la foreign key

    /////-----------------------------------


    ///COLOR table
    public static final String COLOR_KEY = "id_color";
    public static final String COLOR_NAME = "designation_color";



    public static final String COLOR_TABLE_NAME = "Color";

    ///// query
    public static final String COLOR_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + COLOR_TABLE_NAME + " (" +
                    COLOR_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                    COLOR_NAME+" TEXT);";
    /////-----------------------------------

    ///SIZE table
    public static final String SIZE_KEY = "id_size";
    public static final String SIZE_NAME = "designation_size";



    public static final String SIZE_TABLE_NAME = "Size";


    ///// query
    public static final String SIZE_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + SIZE_TABLE_NAME + " (" +
                    SIZE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                    SIZE_NAME+" TEXT);";
    /////-----------------------------------







    public DataBaseHelper (Context context) {
        super(context, db_name, null, db_version);
        this.context = context;
        // fctory c'est le type de curseur est ici c'est par default
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /// on crééer toute les tabeles au niveau de cette methode
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /// on crééer toute les tabeles au niveau de cette methode
        db.execSQL  ("DROP TABLE IF EXISTS Card");
        onCreate(db);
    }
}
