package Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mac on 27/06/16.
 */
public class UpdateDispoTask extends AsyncTask<Object,Void,String> {
    private Context context;
    ProgressDialog pd;

    public UpdateDispoTask(Context context) {
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
    protected String doInBackground(Object... params) {
        StringBuilder result = new StringBuilder();
        String data;
        try {
            URL url = new URL("http://192.168.1.6:8080/UpdateDisponilibite?id_product="+params[0]+"&color="+params[1]+"&size="+params[2]+"&quantity="+params[3]);
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
        }
        else {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }
}
