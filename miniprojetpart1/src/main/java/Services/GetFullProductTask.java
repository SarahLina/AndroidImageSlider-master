package Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Metier.FullProduct;
import Metier.FullProducttmp;
import Metier.Product;
import Repository.FullProductRepo;
import Repository.ProductRepo;

/**
 * Created by mac on 27/06/16.
 */
public class GetFullProductTask extends AsyncTask<Object,Void,String> {
        private Context context;
        ProgressDialog pd;
        ProductRepo productRepo;
        FullProductRepo fullProductRepo;

    public GetFullProductTask(Context context) {
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
            URL url = new URL("http://192.168.8.101:8080/GetFullProduct?density="+params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Attendre 5 secondes max pour établir la connexion
            conn.setConnectTimeout(5000);
            // Attendre 1 minute max pour lire les données
            conn.setReadTimeout(600000);
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
        productRepo=new ProductRepo(this.context);
        fullProductRepo = new FullProductRepo(this.context);
        List <FullProducttmp>  fullProductList=new ArrayList<>();

        if (!s.equals("")) {

            try {
                JSONArray jsonArray = new JSONArray(s);
                Log.i("message","la taille est de " +jsonArray.length());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    FullProducttmp fullProducttmp = new FullProducttmp();


                    Product product = new Product();
                    FullProduct fullProduct= new FullProduct();


                    product.setId_product(jsonObject.getInt("id_product"));
                    product.setCategorie(jsonObject.get("categorie").toString());
                    product.setTypeClient(jsonObject.get("typeClient").toString());
                    product.setName(jsonObject.get("name").toString());
                    product.setRef(jsonObject.get("ref").toString());
                 //   product.setCover(jsonObject.get("cover").toString());
                 //   product.setCover1(jsonObject.get("cover1").toString());
                 //   product.setCover2(jsonObject.get("cover2").toString());
                 //   product.setCover3(jsonObject.get("cover3").toString());
                 //   product.setPrice((float) jsonObject.getDouble("price"));

                    if (productRepo.getProductsById(jsonObject.getInt("id_product")) == null)
                    productRepo.addProduct(product);

                    fullProduct.setProduct(product);
                    fullProduct.setColor(jsonObject.get("color").toString());
                    fullProduct.setSize(jsonObject.get("size").toString());

                    String color= jsonObject.get("color").toString();
                    String size = jsonObject.get("size").toString();
                    int id= jsonObject.getInt("id_product");

                    if ( (fullProductRepo.existColorProd(color, id)== false ) && (fullProductRepo.existSizesProd(size, id) == false))
                    fullProductRepo.addFullProduct(jsonObject.getInt("id_product"),jsonObject.get("color").toString(),jsonObject.get("size").toString());


                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }


        }
        else {
            Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT).show();
        }
    }


}

