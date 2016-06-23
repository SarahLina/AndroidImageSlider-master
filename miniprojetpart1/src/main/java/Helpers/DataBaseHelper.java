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

    // -------
    //client_type table
   /*public static final String CLIENT_TYPE_KEY = "id_clientType";
    public static final String CLIENT_TYPE_NAME = "designation_clientType";



    public static final String CLIENT_TYPE_TABLE_NAME = "ClientType";

    ///// query
    public static final String CLIENT_TYPE_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + CLIENT_TYPE_TABLE_NAME + " (" +
                    CLIENT_TYPE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                    CLIENT_TYPE_NAME+" TEXT);";
    */
    ///Cover table
    public static final String COVER_KEY = "id_cover";
    public static final String COVER_IMAGE = "image_cover";

    public static final String COVER_TABLE_NAME = "Cover";


    ///// query
    public static final String COVER_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + COVER_TABLE_NAME + " (" +
                    COVER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COVER_IMAGE+" BLOB);";
    /////-----------------------------------

    ///ProductImages table
    public static final String PRODUCT_IMAGES_KEY = "id_productimage";
    public static final String PRODUCT_IMAGES_DENSITY = "density_productimage";
    public static final String PRODUCT_IMAGES_COVER_ICON = "iconcover_productimage";
    public static final String PRODUCT_IMAGES_COVER_1 = "cover1_productimage";
    public static final String PRODUCT_IMAGES_COVER_2 = "cover2_productimage";
    public static final String PRODUCT_IMAGES_COVER_3 = "cover3_productimage";


    public static final String PRODUCT_IMAGES_TABLE_NAME = "ProductImages";

    ///// query
    public static final String PRODUCT_IMAGES_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + PRODUCT_IMAGES_TABLE_NAME + " (" +
                    PRODUCT_IMAGES_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                    PRODUCT_IMAGES_DENSITY+"TEXT CHECK(" +PRODUCT_IMAGES_DENSITY+" IN ('ldpi','mdpi','hdpi','xhdpi','xxhdpi','xxxhdpi') ) DEFAULT 'ldpi', "+
                    PRODUCT_IMAGES_COVER_ICON+" REFERENCES " + COVER_TABLE_NAME+"("+COVER_KEY+"), "+
                    PRODUCT_IMAGES_COVER_1+" REFERENCES " + COVER_TABLE_NAME+"("+COVER_KEY+"), "+
                    PRODUCT_IMAGES_COVER_2+" REFERENCES " + COVER_TABLE_NAME+"("+COVER_KEY+"), "+
                    PRODUCT_IMAGES_COVER_3+" REFERENCES " + COVER_TABLE_NAME+"("+COVER_KEY+") "+
                    ");";
    ///MAZEL la foreign key

    /////-----------------------------------


    //COLOR table
    public static final String COLOR_KEY = "id_color";
    public static final String COLOR_NAME = "designation_color";



    public static final String COLOR_TABLE_NAME = "Color";

    ///// query
    public static final String COLOR_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + COLOR_TABLE_NAME + " (" +
                    COLOR_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLOR_NAME+" TEXT);";
    /////-----------------------------------

    ///SIZE table
    public static final String SIZE_KEY = "id_size";
    public static final String SIZE_NAME = "designation_size";



    public static final String SIZE_TABLE_NAME = "Size";


    ///// query
    public static final String SIZE_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + SIZE_TABLE_NAME + " (" +
                    SIZE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SIZE_NAME+" TEXT" +
                    ");";
    /////-----------------------------------



    ///Product table
    public static final String PRODUCT_KEY = "id_product";
    public static final String PRODUCT_NAME = "name_product";
    public static final String PRODUCT_REF = "ref_product";
    public static final String PRODUCT_CLIENT = "e_client_product";
    public static final String PRODUCT_CATEGORY = "e_category_product";
    public static final String PRODUCT_PRICE= "price_product";


    public static final String PRODUCT_TABLE_NAME = "Product";

    ///// query
    public static final String PRODUCT_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + PRODUCT_TABLE_NAME + " (" +
                    PRODUCT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PRODUCT_NAME+" TEXT," +
                    PRODUCT_CLIENT+ " TEXT CHECK( " +PRODUCT_CLIENT+" IN ('enfant','femme','homme') ) DEFAULT 'enfant', "+
                    PRODUCT_CATEGORY+ " TEXTCHECK( " +PRODUCT_CATEGORY+" IN ('tous','top & t-shirt','robes et jupe','pantalons &jeans','accesoires','chaussueres') ) DEFAULT 'tous', "+
                    PRODUCT_PRICE+" INTEGER, "+
                    PRODUCT_REF+" TEXT, "+
                    PRODUCT_IMAGES_KEY + " REFERENCES " + PRODUCT_TABLE_NAME+"("+PRODUCT_KEY+")"+
                    " );";
    ///MAZEL la foreign key

    /////-----------------------------------








    ///FULL_PRODUCT table


    public static final String  FULL_PRODUCT_KEY = "id_fullKey";
    public static final String FULL_PRODUCT_TABLE_NAME = "FullProduct";


    ///// query
    public static final String FULL_PRODUCT_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + FULL_PRODUCT_TABLE_NAME + " (" +
                    FULL_PRODUCT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PRODUCT_KEY + " REFERENCES " + PRODUCT_TABLE_NAME+"("+PRODUCT_KEY+"), "+
                    COLOR_KEY+" REFERENCES " + COLOR_TABLE_NAME+"("+COLOR_KEY+"), "+
                    SIZE_KEY+" REFERENCES " + SIZE_TABLE_NAME+"("+SIZE_KEY+")"+
                    ");";
    /////-----------------------------------

    ///Card table
    public static final String CARD_LINE_KEY = "id_line_card";
    public static final String CARD_LINE_QUANTITY = "quantity_line_card";


    public static final String CARD_LINE_TABLE_NAME = "Card_line";


    ///// query
    public static final String CARD_LINE_TABLE_NAME_TABLE_NAME =
            "CREATE TABLE " + CARD_LINE_TABLE_NAME + " (" +
                    CARD_LINE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FULL_PRODUCT_KEY+" REFERENCES" + FULL_PRODUCT_TABLE_NAME+"("+FULL_PRODUCT_KEY+")"+
                    CARD_LINE_QUANTITY+"INTEGER"+
                    ");";
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
