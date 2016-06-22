package com.example.mac.miniprojet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

import Adapters.CategoriesAdapter;
import Metier.Categorie;


public class ListCategoriesFragment extends Fragment {
    ListAdapter listAdapter ;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_categories,null);
        TextView textView= (TextView) v.findViewById(R.id.Title);
        textView.setText("Catégorie  " + Categorie.categorie_current);
        ListView listView = (ListView) v.findViewById(R.id.listView);

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(v.getContext(),getCategoriesList());
        listView.setAdapter(categoriesAdapter);

        return v;    }

    public List<Categorie> getCategoriesList() {


        List<Categorie> categorieList = new ArrayList<Categorie>();
        // categorie femme
        if (Categorie.categorie_current.compareTo("Femmes")==0) {
            Categorie categorie = new Categorie();
            categorie.setName("Top & T-shirt");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Robes & Jupes");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Pantalons");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Accessoires");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Chaussures");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);
        }
        // categorie homme
        if (Categorie.categorie_current.compareTo("Hommes")==0) {
            Categorie categorie = new Categorie();
            categorie.setName("T-shirt");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Shorts & Pantalons");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Costumes");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Vetements de sport");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Chaussures");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);
        }

        // categorie enfant
        if (Categorie.categorie_current.compareTo("Enfants")==0) {
            Categorie categorie = new Categorie();
            categorie.setName("T-shirt");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Pantalons & Shorts");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Pulls et gilets");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Vetements de sport");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);

            categorie = new Categorie();
            categorie.setName("Chaussures");
            categorie.setIco(R.drawable.ic_action_bag);
            categorieList.add(categorie);
        }




        return categorieList;
    }


}
