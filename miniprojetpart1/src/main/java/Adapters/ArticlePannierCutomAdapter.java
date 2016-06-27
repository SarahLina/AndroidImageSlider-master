package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.mac.miniprojetpart1.R;

import java.util.List;

import Metier.ArticlePannier;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.panier_item, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.articleIcon);
        TextView textNom = (TextView) convertView.findViewById(R.id.nom);
        TextView textCouleur = (TextView) convertView.findViewById(R.id.couleur);
        TextView textTaille = (TextView) convertView.findViewById(R.id.taille);
        ImageConverter ic = new ImageConverter();

        //coverIcon.setImageResource(articleList.get(position).getIconCover());
        coverIcon.setImageResource(R.drawable.ic_tshirt1);
        //coverIcon.setImageBitmap(ic.StringToBitMap(articleList.get(position).getProduct().getCover()));
        textNom.setText(articleList.get(position).getProduct().getName());
        textCouleur.setText(articleList.get(position).getCouleur());
        textTaille.setText(articleList.get(position).getTaille());

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
