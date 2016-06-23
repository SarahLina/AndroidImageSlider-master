package Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Helpers.DataBaseHelper;

/**
 * Created by msia on 23/06/2016.
 */
public class CardRepo   {

    private DataBaseHelper dataBaseHelper;
    Context context;
    public CardRepo(Context context){
        dataBaseHelper = new DataBaseHelper(context);
        this.context=context;
    }

}
