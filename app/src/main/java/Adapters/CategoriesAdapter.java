package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.miniprojet.R;

import java.util.List;

import Metier.Categorie;

/**
 * Created by mac on 25/04/16.
 */
public class CategoriesAdapter extends BaseAdapter{

    private Context context;
    private List<Categorie> categorieList;


    public CategoriesAdapter(Context context, List<Categorie> categorieList) {
        this.context = context;
        this.categorieList = categorieList;
    }

    @Override
    public int getCount() {
        return this.categorieList.size();
    }

    @Override
    public Object getItem(int position) {
         return this.categorieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.list_categories, null);
        }

        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.iconCat);
        TextView textTitle = (TextView) convertView.findViewById(R.id.nomCat);
       // Button bouton= (Button)convertView.findViewById(R.id.voir);

        coverIcon.setImageResource(categorieList.get(position).getIco());
        textTitle.setText(categorieList.get(position).getName());
        // Récupérer la liste des auteurs


        return convertView;
    }
}
