package com.example.mac.miniprojetpart1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.msia.julina.AlarmService;
import com.example.msia.julina.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import Adapters.ArticlePannierCutomAdapter;
import Metier.ArticlePannier;
import Metier.FullProduct;
import Repository.CardLineRepo;
import Repository.ProductRepo;
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




        ListView listView = (ListView) v.findViewById(R.id.listView);
        List<ArticlePannier> list = getArticleList();

        cutomAdapter = new ArticlePannierCutomAdapter(getActivity(),getArticleList());
        listView.setAdapter(cutomAdapter);


        final Button ajouter = (Button) v.findViewById(R.id.ajouterAuPanier);
        ajouter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("------valider  dagui----");

                new AlarmService().activateBroadcast(getActivity());
                new AlarmService().launcherAlarmRepeat(getContext());

                Intent intent = new Intent(v.getContext(),LoginActivity.class);
                startActivity(intent);

            }
        });


        return v;

    }
    public List<ArticlePannier> getArticleList() {
        CardLineRepo cardLineRepo = new CardLineRepo(this.getContext());
        List<ArticlePannier> articlePannierList = new ArrayList<>();
        articlePannierList = cardLineRepo.getCardLine();
         return articlePannierList;
    }






}
