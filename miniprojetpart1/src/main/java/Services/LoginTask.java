package Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.miniprojetpart1.Cmd_line;
import com.example.mac.miniprojetpart1.MainActivity;
import com.example.mac.miniprojetpart1.ObjectCard;
import com.example.mac.miniprojetpart1.R;
import com.example.msia.julina.LoginActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Helpers.DataBaseHelper;
import Metier.ArticlePannier;
import Metier.Product;
import Repository.CardLineRepo;

/**
 * Created by mac on 27/06/16.
 */
public class LoginTask extends AsyncTask<Object,Void,String> {
    private Context context;
    ProgressDialog pd;


    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setTitle("Merci de patienter...");
        pd.setMessage("Chargement...");
        pd.show();
    }




    @Override
    protected String doInBackground(Object... params) {
        StringBuilder result = new StringBuilder();
        String data;
        try {
            URL url = new URL("http://192.168.8.101:8080/Login?username="+params[0]+"&password="+params[1]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Attendre 5 secondes max pour établir la connexion
            conn.setConnectTimeout(5000);
            // Attendre 1 minute max pour lire les données
            conn.setReadTimeout(60000);
            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((data = reader.readLine()) != null) {
                    result.append(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        pd.dismiss();
        if(s.equals("1")) {
            Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show();
            ObjectCard objectCard= new ObjectCard();

            objectCard.setUsername(LoginActivity.username_current);
            Log.i("message", "onPostExecute: "+ LoginActivity.username_current);
            CardLineRepo cardLineRepo= new CardLineRepo(context);
            List<ArticlePannier> list = new ArrayList<>();
            list=cardLineRepo.getCardLine();

            List <Cmd_line> listcmdline= new ArrayList<>();

            Log.i("message", "onPostExecute2: "+ String.valueOf(list.size()));

            for (int i=0; i<listcmdline.size(); i++)
            {
                listcmdline.get(i).setId_product(list.get(i).getProduct().getId_product());
                listcmdline.get(i).setColor(list.get(i).getCouleur());
                listcmdline.get(i).setSize(list.get(i).getTaille());
                listcmdline.get(i).setQuantity_Cmd_line(list.get(i).getQuantite());
            }

            objectCard.setListCmdLine(listcmdline);
            Gson gson= new Gson();
            new AddCmdTask(context).execute(gson.toJson(objectCard));

            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
            SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS Card_line");
            db.execSQL("CREATE TABLE Card_line( id_line_card INTEGER PRIMARY KEY AUTOINCREMENT, id_fullKey  REFERENCES  FullProduct(id_fullKey),"+
                    "quantity_line_card INTEGER);");

            MainActivity.idMainActivity= R.id.nav_cmd;

            Intent intent= new Intent(context, MainActivity.class);
            context.startActivity(intent);

        }

        else {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }


}
