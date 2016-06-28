package Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.miniprojetpart1.R;

import java.util.List;

import Metier.ArticlePannier;
import Metier.FullProduct;
import Metier.Product;
import Repository.CardLineRepo;
import Repository.ProductRepo;
import Services.ImageConverter;


public class ArticlePannierCutomAdapter extends BaseAdapter  {
    private Context context;
    private List<ArticlePannier> articleList;


    public ArticlePannierCutomAdapter(Context context, List<ArticlePannier> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.panier_item, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.articleIcon);
        TextView textNom = (TextView) convertView.findViewById(R.id.nom);
        TextView textCouleur = (TextView) convertView.findViewById(R.id.couleur);
        final TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView textTaille = (TextView) convertView.findViewById(R.id.taille);
        final ProgressDialog progress = new ProgressDialog(this.context);

// To dismiss the dialog

        //final Button ajouter = (Button) view.findViewById(R.id.ajouter);
        ImageButton btnPlus = (ImageButton)  convertView.findViewById(R.id.btnPlus);
        ImageButton btnMoin = (ImageButton)  convertView.findViewById(R.id.moins);
        Button btnSupp = (Button) convertView.findViewById(R.id.supprimer);
        btnSupp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                CardLineRepo cardLineRepo =new CardLineRepo(context);
                ProductRepo productRepo = new ProductRepo(context);

               int id= productRepo.getProductsByRef(articleList.get(position).getProduct().getRef()).getId_product();
                cardLineRepo.deleteLine(id,Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(context,"suppression",Toast.LENGTH_SHORT).show();

            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                CardLineRepo cardLineRepo =new CardLineRepo(context);
                quantity.setText(String.valueOf(Integer.parseInt(quantity.getText().toString()) - 1));
                cardLineRepo.updateProduitQuantity(String.valueOf(articleList.get(position).getProduct().getId_product()),Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(context,"Modification en cours",Toast.LENGTH_SHORT).show();

            }
        });

        btnMoin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //if (ok server)

                CardLineRepo cardLineRepo =new CardLineRepo(context);
                quantity.setText(String.valueOf(Integer.parseInt(quantity.getText().toString())+1));
                cardLineRepo.updateProduitQuantity(String.valueOf(articleList.get(position).getProduct().getId_product()),Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(context,"Modification en cours",Toast.LENGTH_SHORT).show();
                //}else{Toast   impossinme}
            }
        });

        //coverIcon.setImageResource(articleList.get(position).getIconCover());


        coverIcon.setImageResource(R.drawable.ic_tshirt1);
        //ImageConverter ic = new ImageConverter();
        //coverIcon.setImageBitmap(ic.StringToBitMap(articleList.get(position).getProduct().getCover()));
        textNom.setText(articleList.get(position).getProduct().getName());
        textCouleur.setText(articleList.get(position).getCouleur());
        textTaille.setText(articleList.get(position).getTaille());
        String quan = String.valueOf(articleList.get(position).getQuantite());

        quantity.setText(quan);
        //Spinner spinnerQuant = (Spinner) convertView.findViewById(R.id.s)
        //textNom.setText(articleList.get(position).getTitle());
        // Récupérer la liste des auteurs
        //List<String> bookAuthors = articleList.get(position).getAuthors();
        // Séparer la liste des auteurs par une virgule
        //String authors = bookAuthors.get(0);
       /* int listSize = bookAuthors.size();
        if (listSize > 1) {
            for (int i = 1; i < listSize; i++) {
                authors = authors + ", " + bookAuthors.get(i);
            }
            textAuthors.setText("Auteur(s): "+ authors);
        }
        textEditor.setText("Editeur: " + bookList.get(position).getEditor());*/

        return convertView;
    }



}
