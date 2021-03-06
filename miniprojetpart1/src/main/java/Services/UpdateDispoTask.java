package Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.InstrumentationInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mac.miniprojetpart1.DetailsActivity;
import com.example.mac.miniprojetpart1.MainActivity;
import com.example.mac.miniprojetpart1.MainFragment;
import com.example.mac.miniprojetpart1.ProductActivity;
import com.example.mac.miniprojetpart1.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Metier.FullProduct;
import Metier.Product;
import Repository.CardLineRepo;
import Repository.ProductRepo;

/**
 * Created by mac on 27/06/16.
 */
public class UpdateDispoTask extends AsyncTask<Object,Void,String> {
    private Context context;
    ProgressDialog pd;
    private int id = 0;
    private String color="";
    private String size="";
    private CardLineRepo cardLineRepo;
    private int quantite=0;
    private ProductRepo productRepo;

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
            this.id= (int) params[0];
            this.color = (String) params[1];
            this.size= (String) params[2];
            this.quantite= (int)params[3];

            URL url = new URL("http://192.168.8.101:8080/UpdateDisponilibite?id_product="+params[0]+"&color="+params[1]+"&size="+params[2]+"&quantity="+params[3]);
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
            cardLineRepo= new CardLineRepo(context);
            productRepo= new ProductRepo(context);

            Toast.makeText(context, "Le produit a été ajouté au panier", Toast.LENGTH_LONG).show();
            FullProduct fullProduct= new FullProduct();
            Product product= productRepo.getProductsById(id);
            fullProduct.setProduct(product);
            fullProduct.setColor(color);
            fullProduct.setSize(size);
            cardLineRepo.addCardLineRepo(fullProduct,quantite);


            MainActivity.idMainActivity=R.id.nav_monpanier;

            Intent intent = new Intent(context, MainActivity.class);
             context.startActivity(intent);

        }
        else {
            Toast.makeText(context, "La quantité demandée n'est pas disponible", Toast.LENGTH_SHORT).show();
        }
    }
}
