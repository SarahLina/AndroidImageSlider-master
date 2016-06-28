package Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.text.BoringLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mac on 27/06/16.
 */
public class GetDisponibiliteTask extends AsyncTask<Object,Void,Boolean> {
    private Context context;
    ProgressDialog pd;
    private  int id_product;
    private String color;
    private  String size;
    private int quantity;

    public GetDisponibiliteTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        // Création et affichage du ProgressDialog
        pd = new ProgressDialog(context);
        pd.setTitle("Merci de patienter...");
        pd.setMessage("Chargement...");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        StringBuilder result = new StringBuilder();
        String data;
        try {
            this.id_product= (int) params[0];
            this.color= (String) params[1];
            this.size= (String) params[2];
            this.quantity= (int) params[3];


            URL url = new URL("http://192.168.8.101:8080/GetDisponibilite?id_product="+params[0]+"&color="+params[1]+"&size="+params[2]+"&quantity="+params[3]);
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
        if (result.toString().equals("true")) return true;
        else return false;

    }

    @Override
    protected void onPostExecute(Boolean s) {
        pd.dismiss();
        if (s= false)
        {
            Toast.makeText(context,"La quantité demandée n'est pas disponible",Toast.LENGTH_LONG);
        }
        else
        {
            new UpdateDispoTask(context).execute(id_product,color,size,quantity );
        }
    }
}
