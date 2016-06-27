package com.example.mac.miniprojetpart1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.ArticlePannierCutomAdapter;
import Metier.ArticlePannier;
import Services.GetDisponibiliteTask;
import Services.UpdateDispoTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ArticlePannierCutomAdapter cutomAdapter ;
    ListView  listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_main,null);

       // new UpdateDispoTask(getActivity()).execute(1,"bleu","XL", 2);



        ListView listView = (ListView) v.findViewById(R.id.listView);
        cutomAdapter = new ArticlePannierCutomAdapter(getActivity(),getArticleList());
        listView.setAdapter(cutomAdapter);
        return v;

    }
    public List<ArticlePannier> getArticleList() {
        String[] listSummary = {"Article1","Article2"};
        //String[] listSummary = getResources().getStringArray(R.array.summary);
        List<ArticlePannier> articlePannierList = new ArrayList<ArticlePannier>();
        // le 1er livre
        ArticlePannier articlePannier = new ArticlePannier();
        articlePannier.setNom("T-shirt 1");
        //List authors = new ArrayList();

        articlePannier.setCouleur("Rouge");
        articlePannier.setTaille("2200 Da");
        //articlePannier.setIconCover(R.drawable.ic_menu_camera);

        articlePannierList.add(articlePannier);

        articlePannier = new ArticlePannier();

        articlePannier.setNom("T-shirt Lacoste");
        //List authors = new ArrayList();

        articlePannier.setCouleur("Noir");
        articlePannier.setTaille("4000 Da");
        //articlePannier.setIconCover(R.drawable.ic_menu_camera);

        articlePannierList.add(articlePannier);

        return articlePannierList;
    }

    public void AjouterPanier (View view)
    {
        new GetDisponibiliteTask(getActivity()).execute(1,"bleu","XS", 2 );
    }


}
