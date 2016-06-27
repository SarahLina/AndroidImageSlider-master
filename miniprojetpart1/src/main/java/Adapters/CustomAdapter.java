package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.miniprojetpart1.R;

import java.util.ArrayList;
import java.util.List;

import Metier.Book;
import Metier.Product;

/**
 * Created by mac on 26/04/16.
 */
public class CustomAdapter extends BaseAdapter implements Filterable

{
    private Context context;
    private List<Product> productList;
    // Pour le fitre
    private ValueFilter  valueFilter;
    private List<Product> mFilterList;

    public CustomAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        // créer une copie de ProductList pour le filtre
        this.mFilterList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.list_items, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.coverIcon);
        TextView textTitle = (TextView) convertView.findViewById(R.id.title);
        TextView textPrice = (TextView) convertView.findViewById(R.id.price);
        TextView textSize = (TextView) convertView.findViewById(R.id.sizes);
        TextView textColor = (TextView) convertView.findViewById(R.id.colors);


        //coverIcon.setImageResource(productList.get(position).getImg());
        textTitle.setText(productList.get(position).getName());

        // Récupérer la liste des tailles
        List<String> productSize = productList.get(position).getSizes();
        // Séparer la liste des tailles par une virgule
        int listSize;
        if (productSize.size()>0 ) {
        String size = productSize.get(0);
        listSize = productSize.size();
        if (listSize > 0) {
            for (int i = 1; i < listSize; i++) {
                size = size + ", " + productSize.get(i);
            }
            textSize.setText("Taille(s): " + size);
        }
    }
        // Récupérer la liste des tailles
        List<String> productcolor = productList.get(position).getColors();
        // Séparer la liste des tailles par une virgule
        String color = productcolor.get(0);
        listSize = productcolor.size();
        if (listSize > 0) {
            for (int i = 1; i < listSize; i++) {
                color = color + ", " + productcolor.get(i);
            }
            textColor.setText("Couleurs(s): "+ color);
        }

        textPrice.setText("Prix: " + productList.get(position).getPrice());
        return convertView;
    }

    /* Implementation du filtre
       On doit redéfinir la méthode getFilter()
    */
    @Override
    public Filter getFilter() {
        // La méthode retourne un objet de type Filter
        if(valueFilter==null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    // Une nouvelle classe de type Filter est définie
    // Quand on va instancier ValueFilter, deux méthodes sont appelées :  : performFiltering et publishResults

    private class ValueFilter extends Filter {

        // cette méthode effecute le filtre sur la liste des livres
        // une copie de cette liste mFilterList est utilisée

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Product product;
            List<Product> listFilter = new ArrayList<Product>();
            FilterResults filterResults = new FilterResults();
            // véirifer si le texte n'est pas vide
            if ((constraint != null && constraint.length() > 0) && (constraint.toString().compareTo("Tous")!=0))
                    {
                for (int i = 0; i < mFilterList.size(); i++) {
                    product = mFilterList.get(i);
                    // Utiliser le titre et l'éditeur  comme filtre
                    if (product.getCategorie().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        listFilter.add(product);
                    }
                }
                filterResults.count = listFilter.size();
                filterResults.values = listFilter;
            } else {
                filterResults.count = mFilterList.size();
                filterResults.values = mFilterList;
            }
            return filterResults;




        }

        // Cette méthode permet d'afficher la nouvelle listView en appelant notifyDataSetChanged()
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList = (List<Product>) results.values;
            notifyDataSetChanged();

        }
    }
}
