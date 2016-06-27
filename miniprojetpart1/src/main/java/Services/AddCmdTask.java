package Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by mac on 27/06/16.
 */
public class AddCmdTask extends AsyncTask<String,Void,String> {
    private Context context;
    ProgressDialog pd;

    public AddCmdTask(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {
        String result="";
        URL url= null;
        try {
            url = new URL("http://192.168.1.6:8080/AddCmd");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            //on doit autoriser l ecriture
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

            OutputStream outputStream= connection.getOutputStream();
            //param [0] c est la chaine GSON
            outputStream.write(params[0].getBytes("UTF-8"));
            if(connection.getResponseCode() == 200)
            {
                //lire la reponse
                InputStream is= connection.getInputStream();
                BufferedReader reader= new BufferedReader (new InputStreamReader(is, "UTF-8"));
                //Le result contient le i qui est soit 1 ou -1 selon si l'insetion a  été faite dans la bdd ou pas
                result = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        if(s.equals("1")) {
            Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }
}
