package Services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mac.miniprojetpart1.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.CmdCustomAdapter;
import Adapters.CustomAdapter;
import Metier.Cmd;

/**
 * Created by mac on 27/06/16.
 */
public class GetListCmdTask extends AsyncTask<Object,Void,String> {
        private Context context;
        ProgressDialog pd;

    public GetListCmdTask(Context context) {
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
            URL url = new URL("http://192.168.1.6:8080/GetCmd?username="+params[0]);
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
        return result.toString();    }

    @Override
    protected void onPostExecute(String s) {
        pd.dismiss();
        if (!s.equals("")) {
            List<Cmd> cmdList= new ArrayList<>();
            Cmd[] cmds= new Gson().fromJson(s, Cmd[].class);
            cmdList = Arrays.asList(cmds);


            ListView listView =
                    (ListView) ((Activity)context).findViewById(R.id.listView);
            CmdCustomAdapter customAdapter =
                    new CmdCustomAdapter (context, cmdList);
            listView.setAdapter(customAdapter);
        }
        else {
            Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT).show();
        }
    }

}

