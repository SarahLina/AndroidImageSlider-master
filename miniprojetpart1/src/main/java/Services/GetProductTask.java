package Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Repository.ProductRepo;

/**
 * Created by mac on 27/06/16.
 */
public class GetProductTask extends AsyncTask<String,Void,String> {
    private Context context;
    ProgressDialog pd;
    ProductRepo productRepo;

    public GetProductTask(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        // Création et affichage du ProgressDialog
        pd = new ProgressDialog(context);
        pd.setTitle("Merci de patienter...");
        pd.setMessage("Chargement...");
        pd.show();    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        String data;
        try {
            URL url = new URL("http://192.168.1.6:8080/GetProduct?density="+params[0]);
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
        super.onPostExecute(s);
    }
}
